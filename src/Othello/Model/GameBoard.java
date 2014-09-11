package Othello.Model;

import java.util.ArrayList;

/**
 * GameBoard class that represents the board of the game
 * @author Sachin Pradhan
 *
 */
public class GameBoard {
	
	//instance variables
	private final int DIMENSIONV = 8;
	private final int DIMENSIONH = 8;
	private Stone[][] stones;
	private static GameBoard instance;
	
	/**
	 * Private constructor
	 */
	private GameBoard() {
		//instantiate the 2d stone array
		stones = new Stone[DIMENSIONV][DIMENSIONH];
		//add stones to the center of the board
		stones[3][3] = new Stone("X", 3, 3);
		stones[4][3] = new Stone("O", 4, 3);
		stones[3][4] = new Stone("O", 3, 4);
		stones[4][4] = new Stone("X", 4, 4);

	}
	
	/**
	 * Returns the instance of the class.
	 * Creates one first if one does not exist
	 * @return
	 */
	public static GameBoard getInstance() {
		if (instance == null) {
			instance = new GameBoard();
		}
		return instance;
	}
	
	/**
	 * Add a stone to to coordinate specified in the
	 * parameter
	 * @param color either X or O
	 * @param v - vertical coordinate
	 * @param h - horizontal coordinate
	 * @return
	 */
	public boolean addStone(String color, int v, int h) {
		//System.out.println("v:" + v + " h:" + h);
		// check if the move is valid
		if (isMoveValid(color, v, h)) {
			//add the stone to the specified position
			stones[v][h] = new Stone(color, v, h);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Flip the stone at the specified position
	 * @param v - vertical position
	 * @param h - horizontal position
	 */
	public void flipStone(int v, int h) {
		if (stones[v][h] != null) {
			stones[v][h].flipColor();
		}
	}
	
	/**
	 * Checks if the move is valid
	 * @param color - X or O
	 * @param v - vertical coordinate
	 * @param h - horizontal coordinate
	 * @return
	 */
	public boolean isMoveValid(String color, int v, int h) {
		//check if the position is already taken
		if (stones[v][h] != null) {
			System.out.println("position already taken");
			return false;
		}
		//a list to add stones to flip
		ArrayList<Pair> stonesToFlip = new ArrayList<Pair>();
		//validate on each direction
		validateNorth(stonesToFlip, color, v, h);
		validateSouth(stonesToFlip, color, v, h);
		validateEast(stonesToFlip, color, v, h);
		validateWest(stonesToFlip, color, v, h);
		validateNE(stonesToFlip, color, v, h);
		validateNW(stonesToFlip, color, v, h);
		validateSE(stonesToFlip, color, v, h);
		validateSW(stonesToFlip, color, v, h);
		
		//if there are no stones to flip the move is invalid
		if (stonesToFlip.isEmpty()) {
			return false;
		} else {
			// flip stones in the list
			for (Pair p : stonesToFlip) {
				flipStone(p.getVertical(), p.getHorizontal());
			}
			return true;
		}
	}
	
	/**
	 * Validate the North direction from the coordinate
	 * @param stoneToFlip
	 * @param color
	 * @param v
	 * @param h
	 */
	public void validateNorth(ArrayList<Pair> stoneToFlip, String color, int v,	int h) {
		System.out.println("Validating North from v:" + v + " h" + h);
		//create a temporary list
		ArrayList<Pair> tempList = new ArrayList<Pair>();
		int i = v - 1;
		//iterate until the north bounds is reached
		//or a stone of same color is encountered
		//or an empty position is encountered which ever comes first
		while (i >= 0 && stones[i][h] != null
				&& !stones[i][h].getStoneColor().equals(color)) {
			//add the position of the opposing stone in the temp list
			tempList.add(new Pair(i, h));
			i--;
		}
		//check if the opposing stones are trapped by this move
		if (!tempList.isEmpty() && i>=0 && stones[i][h] != null
				&& stones[i][h].getStoneColor().equals(color)) {
			// stones trapped
			// add temp list to stoneToFlip
			stoneToFlip.addAll(tempList);
			System.out.println("North stones");
		}

	}
	/**
	 * Validate the South direction from the coordinate
	 * @param stoneToFlip
	 * @param color
	 * @param v
	 * @param h
	 */
	public void validateSouth(ArrayList<Pair> stoneToFlip, String color, int v, int h) {
		System.out.println("Validating South from v:" + v + " h" + h);
		//create a temporary list
		ArrayList<Pair> tempList = new ArrayList<Pair>();
		int i = v + 1;
		//iterate until the south bounds is reached
		//or a stone of same color is encountered
		//or an empty position is encountered which ever comes first
		while (i < DIMENSIONV && stones[i][h] != null
				&& !stones[i][h].getStoneColor().equals(color)) {
			//add the position of the opposing stone in the temp list
			tempList.add(new Pair(i, h));
			i++;
		}
		//check if the opposing stones are trapped by this move
		if (!tempList.isEmpty() && i< DIMENSIONV && stones[i][h] != null
				&& stones[i][h].getStoneColor().equals(color)) {
			// stones trapped
			// add temp list to stoneToFlip
			stoneToFlip.addAll(tempList);
			System.out.println("South stones");
		}
	}
	/**
	 * Validate the East direction from the coordinate
	 * @param stoneToFlip
	 * @param color
	 * @param v
	 * @param h
	 */
	public void validateEast(ArrayList<Pair> stoneToFlip, String color, int v, int h) {
		System.out.println("Validating East from v:" + v + " h" + h);
		//create a temporary list
		ArrayList<Pair> tempList = new ArrayList<Pair>();
		int j = h + 1;
		//iterate until the east bounds is reached
		//or a stone of same color is encountered
		//or an empty position is encountered which ever comes first
		while (j < DIMENSIONH && stones[v][j] != null
				&& !stones[v][j].getStoneColor().equals(color)) {
			tempList.add(new Pair(v, j));
			j++;
		}
		//check if the opposing stones are trapped by this move
		if (!tempList.isEmpty() && j < DIMENSIONH && stones[v][j] != null
				&& stones[v][j].getStoneColor().equals(color)) {
			// stones trapped
			// add temp list to stoneToFlip
			stoneToFlip.addAll(tempList);
			System.out.println("East stones");
		}
	}
	
	/**
	 * Validate the West direction from the coordinate
	 * @param stoneToFlip
	 * @param color
	 * @param v
	 * @param h
	 */
	public void validateWest(ArrayList<Pair> stoneToFlip, String color, int v, int h) {
		System.out.println("Validating West from v:" + v + " h" + h);
		//create a temporary list
		ArrayList<Pair> tempList = new ArrayList<Pair>();
		int j = h - 1;
		//iterate until the west bounds is reached
		//or a stone of same color is encountered
		//or an empty position is encountered which ever comes first
		while (j >= 0 && stones[v][j] != null
				&& !stones[v][j].getStoneColor().equals(color)) {
			tempList.add(new Pair(v, j));
			j--;
		}
		//check if the opposing stones are trapped by this move
		if (!tempList.isEmpty() && j >= 0 && stones[v][j] != null
				&& stones[v][j].getStoneColor().equals(color)) {
			// stones trapped
			// add temp list to stoneToFlip
			stoneToFlip.addAll(tempList);
			System.out.println("West stones");
		}
	}
	
	/**
	 * Validate the NE direction from the coordinate
	 * @param stoneToFlip
	 * @param color
	 * @param v
	 * @param h
	 */
	public void validateNE(ArrayList<Pair> stoneToFlip, String color, int v, int h) {
		System.out.println("Validating NE from v:" + v + " h" + h);
		//create a temporary list
		ArrayList<Pair> tempList = new ArrayList<Pair>();
		int i = v - 1;
		int j = h + 1;
		//iterate until the northest bounds is reached
		//or a stone of same color is encountered
		//or an empty position is encountered which ever comes first
		while (i >= 0 && j < DIMENSIONH && stones[i][j] != null
				&& !stones[i][j].getStoneColor().equals(color)) {
			tempList.add(new Pair(i, j));
			i--;
			j++;
		}
		//check if the opposing stones are trapped by this move
		if (!tempList.isEmpty() && i >= 0 && j < DIMENSIONH && stones[i][j] != null
				&& stones[i][j].getStoneColor().equals(color)) {
			// stones trapped
			// add temp list to stoneToFlip
			stoneToFlip.addAll(tempList);
			System.out.println("NE stones");
		}
	}

	/**
	 * Validate the NW direction from the coordinate
	 * @param stoneToFlip
	 * @param color
	 * @param v
	 * @param h
	 */
	public void validateNW(ArrayList<Pair> stoneToFlip, String color, int v, int h) {
		System.out.println("Validating NW from v:" + v + " h" + h);
		//create a temporary list
		ArrayList<Pair> tempList = new ArrayList<Pair>();
		int i = v - 1;
		int j = h - 1;
		//iterate until the Northwest bounds is reached
		//or a stone of same color is encountered
		//or an empty position is encountered which ever comes first
		while (i >= 0 && j >= 0 && stones[i][j] != null
				&& !stones[i][j].getStoneColor().equals(color)) {
			tempList.add(new Pair(i, j));
			i--;
			j--;
		}
		//check if the opposing stones are trapped by this move
		if (!tempList.isEmpty() && i >= 0 && j >= 0 && stones[i][j] != null
				&& stones[i][j].getStoneColor().equals(color)) {
			// stones trapped
			// add temp list to stoneToFlip
			stoneToFlip.addAll(tempList);
			System.out.println("NW stones");
		}
	}

	/**
	 * Validate the SE direction from the coordinate
	 * @param stoneToFlip
	 * @param color
	 * @param v
	 * @param h
	 */
	public void validateSE(ArrayList<Pair> stoneToFlip, String color, int v, int h) {
		System.out.println("Validating SE from v:" + v + " h" + h);
		//create a temporary list
		ArrayList<Pair> tempList = new ArrayList<Pair>();
		int i = v + 1;
		int j = h + 1;
		//iterate until the southeast bounds is reached
		//or a stone of same color is encountered
		//or an empty position is encountered which ever comes first
		while (i < DIMENSIONV && j < DIMENSIONH && stones[i][j] != null
				&& !stones[i][j].getStoneColor().equals(color)) {
			tempList.add(new Pair(i, j));
			i++;
			j++;
		}
		//check if the opposing stones are trapped by this move
		if (!tempList.isEmpty() && i < DIMENSIONV && j < DIMENSIONH && stones[i][j] != null
				&& stones[i][j].getStoneColor().equals(color)) {
			// stones trapped
			// add temp list to stoneToFlip
			stoneToFlip.addAll(tempList);
			System.out.println("SE stones");
		}
	}

	/**
	 * Validate the SW direction from the coordinate
	 * @param stoneToFlip
	 * @param color
	 * @param v
	 * @param h
	 */
	public void validateSW(ArrayList<Pair> stoneToFlip, String color, int v, int h) {
		System.out.println("Validating SW from v:" + v + " h" + h);
		//create a temporary list
		ArrayList<Pair> tempList = new ArrayList<Pair>();
		int i = v + 1;
		int j = h - 1;
		//iterate until the southwest bounds is reached
		//or a stone of same color is encountered
		//or an empty position is encountered which ever comes first
		while (i < DIMENSIONV && j >= 0 && stones[i][j] != null
				&& !stones[i][j].getStoneColor().equals(color)) {
			tempList.add(new Pair(i, j));
			i++;
			j--;
		}
		//check if the opposing stones are trapped by this move
		if (!tempList.isEmpty() && i < DIMENSIONV && j >= 0 && stones[i][j] != null
				&& stones[i][j].getStoneColor().equals(color)) {
			// stones trapped
			// add temp list to stoneToFlip
			stoneToFlip.addAll(tempList);
			System.out.println("SW stones");
		}
	}
	
	/**
	 * return the stones on the board
	 * @return
	 */
	public Stone[][] getStones() {
		return stones;
	}

}
