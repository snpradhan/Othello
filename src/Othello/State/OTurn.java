package Othello.State;

import Othello.Model.GameModel;
import Othello.Model.Stone;

/**
 * Game state OTurn
 * This state is reached when not all 64 positions are filled and 
 * either Player X just made a move and Player O has valid positions to make a move
 * or Player O just made a move and Player X has no valid positions to make a move
 * @author Sachin Pradhan
 *
 */

public class OTurn extends GameState{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param oSc - O Score
	 * @param xSc - X Score
	 * @param stones - 2d array of stones\
	 */
	public OTurn(int oSc, int xSc, Stone[][] stones) {
		super(oSc, xSc, stones);
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
		//check if game is over and someone won
		else if (oScore + xScore == 64) {
			//check who won
			if (oScore > xScore) {
				gameModel.setGameState(new OWon(oScore, xScore, stones));
			} else {
				gameModel.setGameState(new XWon(oScore, xScore, stones));
			}
		} else {
			//game is not over
			//check whose turn it is
			if (playerHasValidMoves("X", gameModel.getGameBoard())) {
				//if X has valid moves left its X's turn o/w it's O's turn again
				gameModel.setGameState(new XTurn(oScore, xScore, stones));
			}

		}
	}

}
