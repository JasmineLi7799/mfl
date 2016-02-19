
import java.util.ArrayList;

/**
 * 
 * The "puzzle" is based on a checkerboard of labels. It just displays the letters
 * that the user entered, as a visual representation of the matrix. It's simple and 
 * doesn't need to be interactive, since the program uses an algorithm to find results.
 * 
 * A few of these variables might be unnecessary; I didn't know what exactly would be needed 
 * at the time I originally wrote this code. Can edit later.
 * 
 * @author meganmayfield
 *
 */
public class Puzzle { //could be edited to extend ArrayList and make it more versatile if needed 
	int side;
	int targetLength; 
	String[][] letters;
	
	public Puzzle(int x, int y, String[][] input) {
		side = x;
		targetLength = y;
		letters = input;
	}
	
	public int totalLength() {
		return side*side;
	}

}
