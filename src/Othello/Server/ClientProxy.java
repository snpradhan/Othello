package Othello.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Othello.Model.Observer;
import Othello.Model.Pair;
import Othello.Model.Player;
import Othello.State.GameState;

/**
 * The client proxy that acts as an observer on behalf of the
 * client on the server
 * @author Sachin Pradhan
 *
 */
public class ClientProxy implements Observer {
	
	//connection variables
	private Socket clientSocket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	//client's name
	private String name;
	
	/**
	 * Constructor
	 * @param clientSocket - socket connection with the client
	 */
	public ClientProxy(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			// create input output streams
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream(clientSocket.getInputStream());

		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Return client name
	 * @return
	 */
	public String getName() {
		return name;
	}

	@Override
	/**
	 * Set client name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	/**
	 * Send string message to the client
	 */
	public void sendMessage(String msg) {
		try {
			oos.writeObject(msg);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Return input stream
	 * @return
	 */
	public ObjectInputStream getOis() {
		return ois;
	}
	
	/**
	 * Return output stream
	 * @return
	 */
	public ObjectOutputStream getOos() {
		return oos;
	}

	@Override
	/**
	 * Read input from the client
	 * @return Pair - client's move
	 */
	public Pair readInput() {
		try {
			Object obj = ois.readObject();
			//client can only send a Pair object
			if (obj instanceof Pair) {
				return (Pair) obj;
			} else {
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	/**
	 * Notify the client of the game state
	 * @param gameState
	 */
	public void notify(GameState gameState) {
		try {
			oos.reset();
			oos.writeObject(gameState);
			oos.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	/**
	 * Notify the client of its identity
	 * @param p - player
	 */
	public void sendIdentity(Player p) {
		try {
			oos.writeObject(p);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Close connection with the client
	 */
	public void close(){
		try {
			ois.close();
			oos.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
