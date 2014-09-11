package Othello.State;

import Othello.Model.Stone;

/**
 * Game state Draw
 * This state is reached when all 64 positions are filled and 
 * there are 32 X stones and 32 O stones on the board.
 * The game ends when this state is reached
 * @author Sachin Pradhan
 *
 */
public class Draw extends GameState {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param oSc - O Score
	 * @param xSc - X Score
	 * @param stones - 2d array of stones
	 */
	public Draw(int oSc, int xSc, Stone[][] stones) {
		super(oSc, xSc, stones);
	}

}
