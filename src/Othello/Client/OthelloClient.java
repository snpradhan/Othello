package Othello.Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Scanner;

import Othello.Model.Pair;
import Othello.Model.Player;
import Othello.State.Draw;
import Othello.State.GameState;
import Othello.State.OTurn;
import Othello.State.OWon;
import Othello.State.XTurn;
import Othello.State.XWon;

/**
 * The client controller that connects with the server 
 * @author Sachin Pradhan
 *
 */
public class OthelloClient {

	private static ClientView clientView;
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		// The client socket
		Socket clientSocket = null;
		// The output stream
		ObjectOutputStream oos = null;
		// input stream
		ObjectInputStream ois = null;

		// The port number
		int portNumber;
		// The host game server
		String host = null;
		
		Properties prop = new Properties();
		Player player = null;
		clientView = new ClientView();

		/*
		 * Open a socket on a given host and port. Open input and output
		 * streams.
		 */
		try {
			prop.load(new FileInputStream(new File(
					".."+File.separator+"properties"+File.separator+"othello.properties")));
			host = prop.getProperty("host");
			//host = "54.214.235.211";
			portNumber = Integer.parseInt(prop.getProperty("port"));
			//portNumber = 2222;
			clientSocket = new Socket(host, portNumber);
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream(clientSocket.getInputStream());
			Object obj = null;
			String msg;
			Pair pair = null;
			GameState gameState = null;
			//run until game is over
			while (true) {
				//read objects from the model
				obj = ois.readObject();
				
				if (obj instanceof Player) {
					//model notification about which player the client is
					player = (Player) obj;
					clientView.printMessage("You are Player " + player.getStoneColor());
				} else if (obj instanceof String) {
					//model notification about invalid move
					msg = (String) obj;
					clientView.printMessage(msg);
				} else if (obj instanceof GameState) {
					//model notification about new game state
					gameState = (GameState) obj;
					//trigger the view to print the board
					clientView.printBoard(gameState.getXScore(), gameState.getOScore(), gameState.getStones());
					//check whose turn it is
					if (isMyTurn(gameState, player)) {
						// make a move
						pair = makeAMove(scan);
						//notify the model about the move
						oos.writeObject(pair);
						oos.flush();
					//check if game is over	
					}else if(isGameOver(gameState, player)){
						//end the loop
						break;
					}
				}
			}
			//close the connection with the server
			scan.close();
			ois.close();
			oos.close();
			clientSocket.close();

		} catch (final UnknownHostException e) {
			System.err.println("Unknown host " + host);
		} catch (final IOException e) {
			System.err.println("Couldn't connect to host " + host);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Checks if it is the clients turn to make the move
	 * @param gameState
	 * @param player
	 * @return boolean
	 */
	public static boolean isMyTurn(GameState gameState, Player player) {
		//check what the game state says about whose turn it is
		if (gameState.getClass() == XTurn.class) {
			if (player.getStoneColor().equals("X")) {
				return true;
			} else {
				return false;
			}
		} else if (gameState.getClass() == OTurn.class) {
			if (player.getStoneColor().equals("O")) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}
	
	/**
	 * Check if game is over
	 * @param gameState
	 * @param player
	 * @return
	 */
	public static boolean isGameOver(GameState gameState, Player player) {
		//check if game state is draw, X won or O won
		if (gameState.getClass() == Draw.class) {
			clientView.printMessage("It's a draw");
			return true;
		} else if (gameState.getClass() == XWon.class) {
			if (player.getStoneColor().equals("X")) {
				clientView.printMessage("You Won :)");
				return true;
			} else {
				clientView.printMessage("You Lost :(");
				return true;
			}
		} else if (gameState.getClass() == OWon.class) {
			if (player.getStoneColor().equals("O")) {
				clientView.printMessage("You Won :)");
				return true;
			} else {
				clientView.printMessage("You Lost :(");
				return true;
			}
		}
		return false;

	}
	
	/**
	 * Get move input from player
	 * @param scan
	 * @return
	 */
	public static Pair makeAMove(Scanner scan) {
		//prompt the player to make a move
		clientView.printMessage("It's your turn");
		clientView.printMessage("Enter horizontal coordinate(A-H): ");
		String h = scan.nextLine();
		while (!h.matches("[A-H]")) {
			clientView.printMessage("Invalid horizontal coordinate. Enter again:");
			h = scan.nextLine();
		}
		clientView.printMessage("Enter vertical coordinate(1-8): ");
		String v = scan.nextLine();
		while (!v.matches("[1-8]")) {
			clientView.printMessage("Invalid vertical coordinate. Enter again:");
			v = scan.nextLine();
		}
		//clientView.printMessage(Integer.parseInt(v) - 1 + " "+ ((h.toCharArray()[0]) - 65));
		//return the coordinates of the game board
		return new Pair(Integer.parseInt(v) - 1, (h.toCharArray()[0]) - 65);

	}

}
