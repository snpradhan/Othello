package Othello.State;

import java.io.Serializable;
import java.util.ArrayList;

import Othello.Iterator.StoneIterator;
import Othello.Model.GameBoard;
import Othello.Model.GameModel;
import Othello.Model.Pair;
import Othello.Model.Stone;

/**
 * The base class for the state of the game
 * @author Sachin Pradhan
 *
 */
public class GameState implements Serializable {

	private static final long serialVersionUID = 1L;
	//instance variables
	protected int oScore; //score of player O
	protected int xScore; //score of player X
	protected Stone[][] stones; //2d array of stones
	private StoneIterator sIter; //stone iterator

	/**
	 * Constructor
	 * @param oSc - current score of Player O
	 * @param xSc - current score of Player X
	 * @param stoneList - current stone list
	 */
	public GameState(int oSc, int xSc, Stone[][] stoneList) {
		oScore = oSc;
		xScore = xSc;
		stones = stoneList;
	}

	/**
	 * Update the state of the game
	 * @param gameModel
	 */
	protected void makeMove(GameModel gameModel) {
		//get current list of stones
		stones = gameModel.getStones();
		//create iterator of the stones
		sIter = new StoneIterator(stones);
		//initialize scores
		int oSc = 0, xSc = 0;
		Stone stone;
		sIter.first();
		//iterate through the stonelist
		while (!sIter.isDone()) {
			stone = sIter.currItem();
			if (stone != null) {
				if (stone.getStoneColor().equals("O")) {
					oSc++;
				} else {
					xSc++;
				}
			}
			sIter.next();
		}
		//update the score
		oScore = oSc;
		xScore = xSc;

	}

	/**
	 * Check if player specified in the parameter has valid moves
	 * @param stoneColor - Player O or X
	 * @param gameBoard
	 * @return
	 */
	protected boolean playerHasValidMoves(String stoneColor, GameBoard gameBoard) {
		ArrayList<Pair> stonesToFlip = new ArrayList<Pair>();
		//iterate through the entire board and check if there is at least one
		//position on the board which makes the move valid
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if(stones[x][y] == null){
					//for each positon validate in all directions
					gameBoard.validateNorth(stonesToFlip, stoneColor, x, y);
					gameBoard.validateSouth(stonesToFlip, stoneColor, x, y);
					gameBoard.validateEast(stonesToFlip, stoneColor, x, y);
					gameBoard.validateWest(stonesToFlip, stoneColor, x, y);
					gameBoard.validateNE(stonesToFlip, stoneColor, x, y);
					gameBoard.validateNW(stonesToFlip, stoneColor, x, y);
					gameBoard.validateSE(stonesToFlip, stoneColor, x, y);
					gameBoard.validateSW(stonesToFlip, stoneColor, x, y);
				}
			}
		}
		
		if (stonesToFlip.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Return stones
	 * @return
	 */
	public Stone[][] getStones() {
		return stones;
	}
	
	/**
	 * Return O score
	 * @return
	 */
	public int getOScore() {
		return oScore;
	}
	
	/**
	 * Return X score
	 * @return
	 */
	public int getXScore() {
		return xScore;
	}
	
	/**
	 * Move implemented by sub classes
	 * @param gameModel
	 */
	public void move(GameModel gameModel) {
	}

}
