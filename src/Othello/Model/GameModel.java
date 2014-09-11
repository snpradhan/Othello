package Othello.Model;

import java.util.HashMap;
import Othello.State.GameState;
import Othello.State.XTurn;

/**
 * The model of the game
 * @author Sachin Pradhan
 *
 */
public class GameModel {
	//instance variables
	private GameBoard gameBoard;
	private Player player1;
	private Player player2;
	//list of observers
	private HashMap<String, Observer> observerList;
	//state of the game
	private GameState gameState;
	
	/**
	 * Constructor
	 */
	public GameModel() {
		//create a board
		gameBoard = GameBoard.getInstance();
		//instantiate observerlist
		observerList = new HashMap<String, Observer>();
		//initialize the state of the game
		gameState = new XTurn(2, 2, gameBoard.getStones());
	}
	
	/**
	 * Return the game board
	 * @return
	 */
	public GameBoard getGameBoard() {
		return gameBoard;
	}
	
	/**
	 * Return the first player
	 * @return
	 */
	public Player getPlayer1() {
		return player1;
	}
	
	/**
	 * Set the first player
	 * @param player1
	 */
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	
	/**
	 * Return the second player
	 * @return
	 */
	public Player getPlayer2() {
		return player2;
	}
	
	/**
	 * Set the second player
	 * @param player2
	 */
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	/**
	 * Add an observer to the observer list
	 * @param o
	 */
	public void addObserver(Observer o) {
		observerList.put(o.getName(), o);
	}
	
	/**
	 * Remove an observer
	 * @param name
	 */
	public void removeObserver(String name) {
		observerList.remove(name);
	}
	
	/**
	 * Return the observer list
	 * @return
	 */
	public HashMap<String, Observer> getObservers(){
		return observerList;
	}
	
	/**
	 * Return a specific observer
	 * @param name
	 * @return
	 */
	public Observer getObserver(String name){
		return observerList.get(name);
	}
	
	/**
	 * Notify all observers that the game state has changed
	 */
	public void notifyObservers() {
		for (Observer observer : observerList.values()) {
			observer.notify(gameState);
		}
	}
	
	/**
	 * Return the game state
	 * @return
	 */
	public GameState getGameState() {
		return gameState;
	}
	
	/**
	 * Set the game state
	 * @param gameState
	 */
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	/**
	 * Put a stone at the specified position
	 * @param color - X or O
	 * @param v - vertical coordinate
	 * @param h - horizontal coordinate
	 * @return
	 */
	public boolean putStone(String color, int v, int h) {
		//add the stone on the game board
		if (gameBoard.addStone(color, v, h)) {
			//transition the state of the game
			gameState.move(this);
			return true;
		} else {
			//invalid move
			return false;
		}
	}
	
	/**
	 * Return the stones from the board
	 * @return
	 */
	public Stone[][] getStones() {
		return gameBoard.getStones();
	}
	
	/**
	 * Close connection with all players;
	 */
	public void closeConnections(){
		for (Observer observer : observerList.values()) {
			 observer.close();
		}
	}

}
