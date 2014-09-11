package Othello.State;

import Othello.Model.GameModel;
import Othello.Model.Stone;

/**
 * Game state XTurn
 * This state is reached when not all 64 positions are filled and 
 * either Player O just made a move and Player X has valid positions to make a move
 * or Player X just made a move and Player O has no valid positions to make a move
 * @author Sachin Pradhan
 *
 */
public class XTurn extends GameState{

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * @param oSc - O Score
	 * @param xSc - X Score
	 * @param stones - 2d array of stones\
	 */
	public XTurn(int oSc, int xSc, Stone[][] stoneList) {
		super(oSc, xSc, stoneList);
	}

	@Override
	/**
	 * Change the state of the game
	 */
	public void move(GameModel gameModel) {
		//update the score
		makeMove(gameModel);
		//check if its a draw
		if (oScore == 32 && xScore == 32) {
			gameModel.setGameState(new Draw(oScore, xScore, stones));
		} 
		//check if the game is over and someone won
		else if (oScore + xScore == 64) {
			//check who won
			if (oScore > xScore) {
				gameModel.setGameState(new OWon(oScore, xScore, stones));
			} else {
				gameModel.setGameState(new XWon(oScore, xScore, stones));
			}
		} else {
			//check if opponent has valid moves
			if (playerHasValidMoves("O", gameModel.getGameBoard())) {
				gameModel.setGameState(new OTurn(oScore, xScore, stones));
			}

		}
	}
}
