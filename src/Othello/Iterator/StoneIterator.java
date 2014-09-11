package Othello.Iterator;

import java.io.Serializable;

import Othello.Model.Stone;

/**
 * StoneIterator class to iterate through 2d array 
 * of stones 
 * @author Sachin Pradhan
 * 
 */
public class StoneIterator implements Iterator, Serializable{

	private static final long serialVersionUID = 1L;
	//instance variables
	private Stone[][] stoneList;
	private int currPosX;
	private int currPosY;
	private int count;

	/**
	 * Constructor
	 * @param compList- list to iterate over
	 */
	public StoneIterator(Stone[][] stoneList) {
		this.stoneList = stoneList;
		//set the current position
		this.currPosX = 0;
		this.currPosY = 0;
		this.count = 0;
	}

	@Override
	/**
	 * Positions the index to the first element
	 */
	public void first() {
		currPosX = 0;
		currPosY = 0;
		count = 0;
	}

	@Override
	/**
	 * Increments the index to the next element
	 */
	public void next() {

		currPosY++;
		if (currPosY >= stoneList[0].length) {
			currPosX++;
			currPosY = 0;
		}
		count++;
	}

	@Override
	/**
	 * Checks if the iteration has reached the 
	 * end of the list
	 */
	public boolean isDone() {
		return (count >= stoneList.length * stoneList[0].length);
	}

	@Override
	/**
	 * Returns the item on the current index
	 */
	public Stone currItem() {
		// TODO Auto-generated method stub
		return stoneList[currPosX][currPosY];
	}

}
