package Othello.Iterator;

import Othello.Model.Stone;

/**
 * Iterator interface
 * 
 * @author Sachin Pradhan
 * 
 */
public interface Iterator {

	/**
	 * Positions the index to the first element
	 */
	public void first();

	/**
	 * Increments the index to the next element
	 */
	public void next();

	/**
	 * Checks if the iteration has reached the end of the list
	 */
	public boolean isDone();

	/**
	 * Returns the item on the current index
	 */
	public Stone currItem();

}
