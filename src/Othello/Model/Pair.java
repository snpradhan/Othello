package Othello.Model;

import java.io.Serializable;

/**
 * Pair class the represents the coordinates of the game board
 * @author Sachin Pradhan
 *
 */
public class Pair implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int v;
	private int h;
	
	/**
	 * Constructor
	 * @param v - vertical coordinate
	 * @param h - horizontal coordinate
	 */
	public Pair(int v, int h) {
		this.v = v;
		this.h = h;
	}
	
	/**
	 * Return vertical coordinate
	 * @return
	 */
	public int getVertical() {
		return v;
	}
	
	/**
	 * Set vertical coordinate
	 * @param v - vertical coordinate
	 */
	public void setVertical(int v) {
		this.v = v;
	}

	/**
	 * Return horizontal coordinate
	 * @return
	 */
	public int getHorizontal() {
		return h;
	}
	
	/**
	 * Set horizontal coordinate
	 * @param h - horizontal coordinate
	 */
	public void setHorizontal(int h) {
		this.h = h;
	}

}
