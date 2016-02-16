import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;


public class managerTest {

	@Test
	public void test() {
		
	}
	
	@Test
    public void testneedToUpdate() {
		manager managerTest  = new manager();
		File source = new File("./dict-2/10.txt");
		File cache = new File("./dic-2/3.txt");
		assertEquals(true, managerTest.needToUpdate(source, cache));
		
	}
	
	@Test
	public void testReadFile(){
		manager managerTest = new manager();
		String path = "./testFile.txt";
		HashMap<String,String> s =new HashMap();
		if(path!=null && path!=""){
			managerTest.readFile(path, s);
			System.out.println("==");
		}
		System.out.println(s.toString());
		assertEquals(true, s.size() == 4);
		
		
	}
	
	@Test
	public void testSort(){
		manager mana = new manager();
		ArrayList<String> test = new ArrayList<String>();
		HashMap<String,String> s =new HashMap();
		
		test.add("a");
		test.add("b");
		test.add("c");
		test.add("d");
		
		s.put("c","c");
		s.put("a","a");
		s.put("d","d");
		s.put("b","b");
		
		mana.sort(s);
		assertEquals(true ,mana.writeList.toString().equals(test.toString()));	
	}
	
		@Test
	public void testCacheExist(){
		manager mana = new manager ();
		assertEquals(false ,mana.cacheExist());		
	}

	


}
