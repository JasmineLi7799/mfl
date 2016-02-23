import static org.junit.Assert.*;

import org.junit.Test;


public class ViewTest {


	
	@Test
	public void testDetermineSize(){
		View v = new View();
		System.out.println(v.determineSizes(2)[2]);
		assertEquals(8, v.determineSizes(3).length);
		assertEquals(3, v.determineSizes(2).length);
		assertEquals("4", v.determineSizes(2)[2]);
	}
	

}
