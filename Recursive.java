/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <NAME1> and <NAME2),
 *  this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID:
 *  email address:
 *  Grader name:
 *  Section number:
 *
 *  Student 2
 *  UTEID:
 *  email address:
 *
 */

//imports

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Various recursive methods to be implemented. Given shell file for CS314
 * assignment.
 */
public class Recursive {

	/**
	 * Problem 1: convert a base 10 int to binary recursively. <br>
	 * pre: n != Integer.MIN_VALUE<br>
	 * <br>
	 * post: Returns a String that represents N in binary. All chars in returned
	 * String are '1's or '0's. Most significant digit is at position 0
	 * 
	 * @param n the base 10 int to convert to base 2
	 * @return a String that is a binary representation of the parameter n
	 */
	public static String getBinary(int n) {
		if (n == Integer.MIN_VALUE) {
			throw new IllegalArgumentException(
					"Failed precondition: " + "getBinary. n cannot equal " + "Integer.MIN_VALUE. n: " + n);
		}

		if (n == -1 || n == 0 || n == 1) {
			return n + "";
		}
		return getBinary(n / 2) + Math.abs(n % 2);
	}

	/**
	 * Problem 2: reverse a String recursively.<br>
	 * pre: stringToRev != null<br>
	 * post: returns a String that is the reverse of stringToRev
	 * 
	 * @param stringToRev the String to reverse.
	 * @return a String with the characters in stringToRev in reverse order.
	 */
	public static String revString(String stringToRev) {
		if (stringToRev == null) {
			throw new IllegalArgumentException("Failed precondition: " + "revString. parameter may not be null.");
		}
		if (stringToRev.length() == 0) {
			return "";
		}

		return revString(stringToRev.substring(1)) + stringToRev.charAt(0);
	}

	/**
	 * Problem 3: Returns the number of elements in data that are followed directly
	 * by value that is double that element. pre: data != null post: return the
	 * number of elements in data that are followed immediately by double the value
	 * 
	 * @param data The array to search.
	 * @return The number of elements in data that are followed immediately by a
	 *         value that is double the element.
	 */
	public static int nextIsDouble(int[] data) {
		if (data == null) {
			throw new IllegalArgumentException("Failed precondition: " + "revString. parameter may not be null.");
		}

		if (data.length == 0 || data.length == 1) {
			return 0;
		}

		int index = 0;
		int count = countDoubles(data, index);
		return count;
	}

	// CS314 students, add your nextIsDouble helper method here
	private static int countDoubles(int[] data, int index) {

		if (index == data.length - 1) {
			return 0;
		}

		if (data[index] * 2 == data[index + 1]) {
			return 1 + countDoubles(data, index + 1);
		} else {
			return countDoubles(data, index + 1);
		}

	}

	/**
	 * Problem 4: Find all combinations of mnemonics for the given number.<br>
	 * pre: number != null, number.length() > 0, all characters in number are
	 * digits<br>
	 * post: see tips section of assignment handout
	 * 
	 * @param number The number to find mnemonics for
	 * @return An ArrayList<String> with all possible mnemonics for the given number
	 */
	public static ArrayList<String> listMnemonics(String number) {
		if (number == null || number.length() == 0 || !allDigits(number)) {
			throw new IllegalArgumentException("Failed precondition: " + "listMnemonics");
		}

		ArrayList<String> results = new ArrayList<>(); // to store the mnemonics
		recursiveMnemonics(results, "", number);
		return results;
	}

	/*
	 * Helper method for listMnemonics mnemonics stores completed mnemonics
	 * mneominicSoFar is a partial (possibly complete) mnemonic digitsLeft are the
	 * digits that have not been used from the original number.
	 */
	private static void recursiveMnemonics(ArrayList<String> mnemonics, String mnemonicSoFar, String digitsLeft) {

		if (digitsLeft.length() == 0) {
			mnemonics.add(mnemonicSoFar);
		} else {
			String current = digitLetters(digitsLeft.charAt(0));
			for (int i = 0; i < current.length(); i++) {
				recursiveMnemonics(mnemonics, mnemonicSoFar + current.charAt(i), digitsLeft.substring(1));
			}

		}

	}

	/*
	 * Static code blocks are run once when this class is loaded. Here we create an
	 * unmoddifiable list to use with the phone mnemonics method.
	 */
	private static final List<String> LETTERS_FOR_NUMBER;
	static {
		String[] letters = { "0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };
		ArrayList<String> lettersAsList = new ArrayList<>();
		for (String s : letters) {
			lettersAsList.add(s);
		}
		LETTERS_FOR_NUMBER = Collections.unmodifiableList(lettersAsList);

	}
	// used by method digitLetters

	/*
	 * helper method for recursiveMnemonics pre: ch is a digit '0' through '9' post:
	 * return the characters associated with this digit on a phone keypad
	 */
	private static String digitLetters(char ch) {
		if (ch < '0' || ch > '9') {
			throw new IllegalArgumentException("parameter " + "ch must be a digit, 0 to 9. Given value = " + ch);
		}
		int index = ch - '0';
		return LETTERS_FOR_NUMBER.get(index);
	}

	/*
	 * helper method for listMnemonics pre: s != null post: return true if every
	 * character in s is a digit ('0' through '9')
	 */
	private static boolean allDigits(String s) {
		if (s == null) {
			throw new IllegalArgumentException("Failed precondition: " + "allDigits. String s cannot be null.");
		}
		boolean allDigits = true;
		int i = 0;
		while (i < s.length() && allDigits) {
			allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
			i++;
		}
		return allDigits;
	}

	/**
	 * Problem 5: Draw a Sierpinski Carpet.
	 * 
	 * @param size  the size in pixels of the window
	 * @param limit the smallest size of a square in the carpet.
	 */
	public static void drawCarpet(int size, int limit) {
		DrawingPanel p = new DrawingPanel(size, size);
		Graphics g = p.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, size, size);
		g.setColor(Color.WHITE);
		drawSquares(g, size, limit, 0, 0);
	}

	/*
	 * Helper method for drawCarpet Draw the individual squares of the carpet.
	 * 
	 * @param g The Graphics object to use to fill rectangles
	 * 
	 * @param size the size of the current square
	 * 
	 * @param limit the smallest allowable size of squares
	 * 
	 * @param x the x coordinate of the upper left corner of the current square
	 * 
	 * @param y the y coordinate of the upper left corner of the current square
	 */
	private static void drawSquares(Graphics g, int size, int limit, double x, double y) {

		if (size > limit) {

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {

					boolean isCenter = (i == 1 && j == 1);
					if (!isCenter) {

						drawSquares(g, size / 3, limit, x + size / 3 * i, y + size / 3 * j);
					} else {
						g.fillRect((int) (x + (size / 3)), (int) (y + (size / 3)), size / 3, size / 3);
					}

				}
			}
		}
	}

	/**
	 * Problem 6: Determine if water at a given point on a map can flow off the map.
	 * <br>
	 * pre: map != null, map.length > 0, map is a rectangular matrix, 0 <= row <
	 * map.length, 0 <= col < map[0].length <br>
	 * post: return true if a drop of water starting at the location specified by
	 * row, column can reach the edge of the map, false otherwise.
	 * 
	 * @param map The elevations of a section of a map.
	 * @param row The starting row of a drop of water.
	 * @param col The starting column of a drop of water.
	 * @return return true if a drop of water starting at the location specified by
	 *         row, column can reach the edge of the map, false otherwise.
	 */
	public static boolean canFlowOffMap(int[][] map, int row, int col) {

		if (map == null || map.length == 0 || !isRectangular(map) || !inbounds(row, col, map)) {
			throw new IllegalArgumentException("Failed precondition: " + "canFlowOffMap");
		}

		final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		if (row == 0 || col == 0 || row == map.length - 1 || col == map[0].length - 1) {
			return true;
		} else {
			// we are not at the end.
			boolean result = false;
			// get new Col and new Row
			for (int i = 0; i < directions.length; i++) {
				int newCol = col + directions[i][1];
				int newRow = row + directions[i][0];

				// at new Col and new Row check
				if (map[newRow][newCol] < map[row][col] && !result) {
					result = canFlowOffMap(map, newRow, newCol);
					if (result) {
						return result;
					}
				}

			}
			return result;

		}

	}

	/*
	 * helper method for canFlowOfMap - CS314 students you should not have to call
	 * this method, pre: mat != null,
	 */
	private static boolean inbounds(int r, int c, int[][] mat) {
		if (mat == null) {
			throw new IllegalArgumentException("Failed precondition: " + "inbounds. The 2d array mat may not be null.");
		}
		return r >= 0 && r < mat.length && mat[r] != null && c >= 0 && c < mat[r].length;
	}

	/*
	 * helper method for canFlowOfMap - CS314 students you should not have to call
	 * this method, pre: mat != null, mat.length > 0 post: return true if mat is
	 * rectangular
	 */
	private static boolean isRectangular(int[][] mat) {
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException("Failed precondition: " + "inbounds. The 2d array mat may not be null "
					+ "and must have at least 1 row.");
		}
		boolean correct = true;
		final int numCols = mat[0].length;
		int row = 0;
		while (correct && row < mat.length) {
			correct = (mat[row] != null) && (mat[row].length == numCols);
			row++;
		}
		return correct;
	}

	/**
	 * Problem 7: Find the minimum difference possible between teams based on
	 * ability scores. The number of teams may be greater than 2. The goal is to
	 * minimize the difference between the team with the maximum total ability and
	 * the team with the minimum total ability. <br>
	 * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams <br>
	 * post: return the minimum possible difference between the team with the
	 * maximum total ability and the team with the minimum total ability.
	 * 
	 * @param numTeams  the number of teams to form. Every team must have at least
	 *                  one member
	 * @param abilities the ability scores of the people to distribute
	 * @return return the minimum possible difference between the team with the
	 *         maximum total ability and the team with the minimum total ability.
	 *         The return value will be greater than or equal to 0.
	 */
	public static int minDifference(int numTeams, int[] abilities) {

		// check preconditions
		if (numTeams < 2 || abilities == null || abilities.length < numTeams) {
			throw new IllegalArgumentException("Cannot duthat");
		}

		int currentPerson = 0;
		int[] assignTeams = new int[numTeams];
		int[] assignAbilities = new int[numTeams];
		return minDifHelper(numTeams, abilities, assignTeams, assignAbilities, currentPerson);
	}

	private static int minDifHelper(int numTeams, int[] abilities, int[] assignTeams, int[] assignAbilities,
			int currentPerson) {

		// base case
		if (currentPerson == abilities.length) {
			int min = assignAbilities[0];
			int max = assignAbilities[0];
			for (int i = 0; i < assignTeams.length; i++) {
				if (assignTeams[i] == 0) {
					return Integer.MAX_VALUE;
				}

				if (assignAbilities[i] < min) {
					min = assignAbilities[i];
				}

				if (assignAbilities[i] > max) {
					max = assignAbilities[i];
				}

			}

			// assume teams are valid!
			return max - min;
			
		} else {
			
			int minDiff = Integer.MAX_VALUE;
		
			// recursive case if not base case
			for (int i = 0; i < assignTeams.length; i++) {
				assignTeams[i]++;
				assignAbilities[i] += abilities[currentPerson];
				
				int newDiff = minDifHelper(numTeams, abilities, 
						assignTeams, assignAbilities, currentPerson + 1);
				
				minDiff = Math.min(minDiff, newDiff);
				
				//backtrack and explore other options 
				assignTeams[i]--;
				assignAbilities[i] -= abilities[currentPerson];
				
				
			}
			
			return minDiff;

		}

	}

	/**
	 * Problem 8: Maze solver. <br>
	 * pre: board != null <br>
	 * pre: board is a rectangular matrix <br>
	 * pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*' <br>
	 * pre: there is a single 'S' character present <br>
	 * post: rawMaze is not altered as a result of this method. Return 2 if it is
	 * possible to escape the maze after collecting all the coins. Return 1 if it is
	 * possible to escape the maze but without collecting all the coins. Return 0 if
	 * it is not possible to escape the maze. More details in the assignment
	 * handout.
	 * 
	 * @param rawMaze represents the maze we want to escape. rawMaze is not altered
	 *                as a result of this method.
	 * 
	 *                //make copy and pass in
	 * @return per the post condition
	 */

	public static int canEscapeMaze(char[][] rawMaze) {

//		checkPreconditions(rawMaze);
		
		char[][] hardMaze = Arrays.copyOf(rawMaze, rawMaze.length);
		final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		
		int startingRow = 0;
		int startingCol = 0;
		int maxCoin = 0;
		for (int i = 0; i < hardMaze.length; i++) {
			for (int j = 0; j < hardMaze[0].length; j++) {
				if (hardMaze[i][j] == 'S') {
					startingRow = i;
					startingCol = j;
				}
				
				if (hardMaze[i][j] == '$') {
					maxCoin++;
				}
			}
		}
		
		int index = 0;
		
		int canEscape = mazeHelper(hardMaze, maxCoin, startingRow, startingCol, 0);
		
		return canEscape;
	}

	private static int mazeHelper(char[][]hardMaze, int maxCoin, int startingRow, int startingCol, int coins) {
		
		//mazeHelper function 
		if (hardMaze[startingRow][startingCol] == 'E') {
			if (coins == maxCoin) {
				return 2;
			} else {
				return 1;
			}
			
		} else {
			//recursive call 
			final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
			char backtrack = hardMaze[startingRow][startingCol];
			
				if (backtrack == '$') {
					hardMaze[startingRow][startingCol] = 'Y';
				} else if (backtrack == 'Y') {
					hardMaze[startingRow][startingCol] = '*';
				} else if (backtrack == 'G') {
					hardMaze[startingRow][startingCol] = 'Y';
				}
				boolean foundExit = false;
				// get new Col and new Row
				for (int i = 0; i < directions.length; i++) {
					int newCol = startingCol + directions[i][1];
					int newRow = startingRow + directions[i][0];

					
					//check if bounds are valid 
					if (newCol < hardMaze[0].length && newRow < hardMaze.length && newCol >= 0 && newRow >= 0) {
						int coinsToAdd = hardMaze[newRow][newCol] == '$' ? 1 : 0;
						if (backtrack != '*') {
							int result = mazeHelper(hardMaze, maxCoin, newRow, newCol, coins + coinsToAdd);
							
							if (result == 2) {
								return 2;
							} else if (result == 1) {
								foundExit = true;
							}
						}
						
						
					}
			
			}
				
				//backtrack!! 
				hardMaze[startingRow][startingCol] = backtrack; 
				
				if (foundExit) {
					return 1;
				}
				
				return 0;

		}
		
	}
	private static void checkPreconditions(char[][] board) {
		if (board == null || board[0].length == board.length) {
			throw new IllegalArgumentException("violation of preconditions");
		}

		String correctChars = "SE$GY*";
		int sCounter = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (!correctChars.contains("" + board[i][j])) {
					throw new IllegalArgumentException("ERRRR");
				}

				if (board[i][j] == correctChars.charAt(0)) {
					sCounter++;
				}

				if (sCounter > 1) {
					throw new IllegalArgumentException("BROSKI LETS GO TO SLEEP!!! ITS 1030 :(");
				}

			}
		}

	}
}
