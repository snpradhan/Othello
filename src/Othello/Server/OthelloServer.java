package Othello.Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import Othello.Model.GameModel;
import Othello.Model.Pair;
import Othello.Model.Player;
import Othello.State.Draw;
import Othello.State.OTurn;
import Othello.State.OWon;
import Othello.State.XTurn;
import Othello.State.XWon;

/**
 * The game server
 * @author Sachin Pradhan
 *
 */
public class OthelloServer {

	// The client socket.
	private static Socket clientSocket = null;
	// This game server can accept up to maxClientsCount clients' connections.
	private static final int maxClientsCount = 2;
	// The server socket.
	private static ServerSocket serverSocket = null;
	private static GameModel gameModel;

	public static void main(String args[]) {

		// The port number.
		int portNumber;
		//instantiate game model
		gameModel = new GameModel();
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream(new File(
					".."+File.separator+"properties"+File.separator+"othello.properties")));
			portNumber = Integer.parseInt(prop.getProperty("port"));
			//create server socket
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Game server waiting for clients");
			//wait for 2 clients to connect
			ClientProxy clientProxy;
			while (gameModel.getObservers().size() < maxClientsCount) {

				clientSocket = serverSocket.accept();
				clientProxy = new ClientProxy(clientSocket);
				if (gameModel.getObservers().size() <= 0) {
					//name the first player X
					clientProxy.setName("X");
					System.out.println("Player X joined");
					// notify client waiting for another
					clientProxy.sendMessage("Waiting for another player.");
				} else {
					//name the second player O
					clientProxy.setName("O");
					System.out.println("Player O joined");
				}

				gameModel.addObserver(clientProxy);
			}

			// assign each player a stone
			gameModel.setPlayer1(new Player("X"));
			gameModel.setPlayer2(new Player("O"));
			//send each player their identity
			gameModel.getObserver("X").sendIdentity(gameModel.getPlayer1());
			gameModel.getObserver("O").sendIdentity(gameModel.getPlayer2());
			Pair input;
			//loop until game is over
			while (true) {
				// notify all observers
				gameModel.notifyObservers();
				Thread.sleep(1000);
				//check the game state whose turn it is
				if (gameModel.getGameState().getClass() == XTurn.class) {
					//get input from player X
					input = gameModel.getObserver("X").readInput();
					//validate move 
					if (!gameModel.putStone("X", input.getVertical(), input.getHorizontal())) {
						System.out.println("Invalid move by X");
						//notify player x that move was invalid
						gameModel.getObserver("X").sendMessage("Invalid move");
					}

				} else if (gameModel.getGameState().getClass() == OTurn.class) {
					//get input from player O
					input = gameModel.getObserver("O").readInput();
					//validate move
					if (!gameModel.putStone("O", input.getVertical(), input.getHorizontal())) {
						System.out.println("Invalid move by O");
						//notify player O that move was invalid
						gameModel.getObserver("O").sendMessage("Invalid move");
					}
				} else {
					//check if the game is over
					if(gameModel.getGameState().getClass() == XWon.class){
						System.out.println("X Won");
					}
					else if(gameModel.getGameState().getClass() == OWon.class){
						System.out.println("O Won");
					}
					else if(gameModel.getGameState().getClass() == Draw.class){
						System.out.println("It's a Draw");
					}
					break;
				}
			}
			//close all connections
			serverSocket.close();
			gameModel.closeConnections();

		} catch (final IOException e) {
			System.out.println(e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
