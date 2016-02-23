import static org.junit.Assert.*;

import org.junit.Test;


public class PuzzleTest {

	@Test
	public void testTotalLength() {
		String[][] testString = new String [2][2];
		testString[0][0] = "a";
		testString[1][0] = "b";
		testString[0][1] = "c";
		testString[1][1] = "d";
		
		Puzzle testPuzzle = new Puzzle(10,10,testString);
		
		assertEquals(100, testPuzzle.totalLength());
		 
	}

	@Test
	public void testGetLetter(){
		String[][] testString = new String [2][2];
		testString[0][0] = "a";
		testString[1][0] = "b";
		testString[0][1] = "c";
		testString[1][1] = "d";
		
		int[] coords = new int[2];
		
		
		Puzzle testPuzzle = new Puzzle(10,10,testString);
		
		assertEquals("a", testPuzzle.getLetter(coords));
	}

}
