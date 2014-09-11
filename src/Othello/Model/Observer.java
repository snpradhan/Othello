package Othello.Model;

import Othello.State.GameState;

/**
 * Observer interface
 * @author Sachin Pradhan
 *
 */
public interface Observer {

	/**
	 * Notify the observer
	 * @param gameState
	 */
	public void notify(GameState gameState);
	
	/**
	 * Set the name of the observer
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * Return the name
	 * @return
	 */
	public String getName();
	
	/**
	 * Send identify of the observer
	 * @param p
	 */
	public void sendIdentity(Player p);
	
	/**
	 * Read the move of the observer
	 * @return
	 */
	public Pair readInput();
	
	/**
	 * Send message to the observer
	 * @param msg
	 */
	public void sendMessage(String msg);
	
	/**
	 * Close connection with client
	 */
	public void close();
}
