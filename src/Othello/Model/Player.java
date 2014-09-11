package Othello.Model;

import java.io.Serializable;

/** 
 * The player class
 * @author Sachin Pradhan
 *
 */
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	private String stoneColor;
	
	/**
	 * Constructor
	 * @param stoneColor X or O
	 */
	public Player(String stoneColor) {
		this.stoneColor = stoneColor;
	}
	
	/**
	 * Return stone color
	 * @return
	 */
	public String getStoneColor() {
		return stoneColor;
	}

	/**
	 * Set the stone color
	 * @param stoneColor
	 */
	public void setStoneColor(String stoneColor) {
		this.stoneColor = stoneColor;
	}

}
