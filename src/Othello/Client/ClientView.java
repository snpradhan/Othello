package Othello.Client;

import Othello.Model.Stone;

/**
 * Client View that print the game board on the console
 * @author Sachin Pradhan
 *
 */
public class ClientView {

	private final String HEADER = "     A   B   C   D   E   F   G   H";
	private final String HLINE = "   +---+---+---+---+---+---+---+---+";
	private final String SEP = "------------------------------------";
	
	/**
	 * Constructor
	 */
	public ClientView() {
	}
	
	/**
	 * Print the game board on the console
	 * @param x number of x stones
	 * @param o number of o stones
	 * @param stones 2d array of stones
	 */
	public void printBoard(int x, int o, Stone[][] stones) {
		//print the score
		StringBuffer strBuffer = new StringBuffer(SEP+"\n");
		strBuffer.append("Player X score:" + x + "\n");
		strBuffer.append("Player O score:" + o + "\n");
		strBuffer.append(SEP+"\n");
		
		//print the board
		strBuffer.append(HEADER + "\n");
		strBuffer.append(HLINE + "\n");
		for (int v = 0; v < stones.length; v++) {
			strBuffer.append(" "+(v+1));
			for (int h = 0; h < stones[v].length; h++) {
				if (stones[v][h] != null) {
					strBuffer.append(" | " + stones[v][h].getStoneColor());
				} else {
					strBuffer.append(" |  ");
				}
			}
			strBuffer.append(" |\n");
			strBuffer.append(HLINE + "\n");
		}

		System.out.println(strBuffer.toString());

	}
	
	/**
	 * Prints the message on the console
	 * @param msg
	 */
	public void printMessage(String msg) {
		System.out.println(msg);
	}

}
