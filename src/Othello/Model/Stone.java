package Othello.Model;

import java.io.Serializable;

/**
 * Stone class
 * @author Sachin Pradhan
 *
 */
public class Stone implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String stoneColor; // X or O
	private int x_pos; //vertical coordinate
	private int y_pos; //horizontal coordinate
	
	/**
	 * Constructor
	 * @param stoneColor
	 * @param x_pos
	 * @param y_pos
	 */
	public Stone(String stoneColor, int x_pos, int y_pos) {
		this.stoneColor = stoneColor;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}
	
	/**
	 * Return stone color
	 * @return
	 */
	public String getStoneColor() {
		return stoneColor;
	}
	
	/**
	 * Set stone color
	 * @param stoneColor
	 */
	public void setStoneColor(String stoneColor) {
		this.stoneColor = stoneColor;
	}
	
	/**
	 * Return vertical coordinate
	 * @return
	 */
	public int getX_pos() {
		return x_pos;
	}
	
	/** 
	 * Set vertical coordinate
	 * @param x_pos
	 */
	public void setX_pos(int x_pos) {
		this.x_pos = x_pos;
	}
	
	/**
	 * Return horizontal coordinate
	 * @return
	 */
	public int getY_pos() {
		return y_pos;
	}
	
	/**
	 * Set horizontal coordinate
	 * @param y_pos
	 */
	public void setY_pos(int y_pos) {
		this.y_pos = y_pos;
	}
	
	/**
	 * Flip the stone color
	 * X to O or O to X
	 */
	public void flipColor() {
		if (stoneColor.equals("X")) {
			stoneColor = "O";
		} else {
			stoneColor = "X";
		}
	}

}
