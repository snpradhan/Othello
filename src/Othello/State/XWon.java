package Othello.State;

import Othello.Model.Stone;

/**
 * Game state XWon
 * This state is reached when all 64 positions are filled and 
 * player X has more stones than player O on the board.
 * The game ends when this state is reached
 * @author Sachin Pradhan
 *
 */
public class XWon extends GameState {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * @param oSc - O Score
	 * @param xSc - X Score
	 * @param stones - 2d array of stones
	 */
	public XWon(int oSc, int xSc, Stone[][] stoneList) {
		super(oSc, xSc, stoneList);
	}

}
