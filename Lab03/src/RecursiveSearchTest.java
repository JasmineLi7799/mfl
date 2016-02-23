import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class RecursiveSearchTest {

	@Test
	public void testSearch() {
		RecursiveSearch r = new RecursiveSearch();
		ArrayList<int[]> usedSet = new ArrayList<int[]>();
		String currentWord;
		String[][] testString = new String [2][2];
		testString[0][0] = "a";
		Puzzle testP = new Puzzle(10,10, testString);
		
		r.search(1, 0, 0, usedSet, testP);
		assertEquals(true, r.strLst.isEmpty());
		assertEquals(0, r.strLst.size());
	}
	
	

}
