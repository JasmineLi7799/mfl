import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class StringArrayListMergerTest {


	
	@Test
	public void testConstructor(){
		ArrayList<String> mainArray = new ArrayList<String> ();
		mainArray.add("a");
		mainArray.add("b");
		
		StringArrayListMerger testM = new StringArrayListMerger(mainArray);
		
		assertTrue(!testM.main.isEmpty());
		assertEquals("a", testM.main.get(0));
		
		assertFalse(testM.main.isEmpty());
	}
	
	@Test
	public void testMerge(){
		ArrayList<String> mainArray = new ArrayList<String> ();
		mainArray.add("a");
		mainArray.add("b");
		
		ArrayList<String> otherArray = new ArrayList<String> ();
		mainArray.add("c");
		mainArray.add("d");
		
		
		StringArrayListMerger testM = new StringArrayListMerger(mainArray);
		testM.merge(otherArray);
		System.out.println(testM.main.toString());
		
		assertEquals(4, testM.main.size());
		assertEquals("c", testM.main.get(2));
		
		
	}

}
