package Othello.State;

import Othello.Model.Stone;

/**
 * Game state OWon
 * This state is reached when all 64 positions are filled and 
 * player O has more stones than player X on the board.
 * The game ends when this state is reached
 * @author Sachin Pradhan
 *
 */
public class OWon extends GameState{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param oSc - O Score
	 * @param xSc - X Score
	 * @param stones - 2d array of stones
	 */
	public OWon(int oSc, int xSc, Stone[][] stoneList) {
		super(oSc, xSc, stoneList);
	}

}
