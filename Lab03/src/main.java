import java.io.File;
import java.util.Scanner;


/**
 * Cache dictionary: 
 * 	1. check if need to update cache file(by compare the date of source file and cache file)
 * 	2. update the cache file(get data from source file and sort them alphabetically)
 * 	3. ask user to input something, but have to be only letter(no number, no single letter)
 * 	4. check if the user input a valid word( a valid word should be found in the cache dictionary)
 * 
 * @author Jasmine
 *
 */

/**
 * hahaha    hahaaha hahha 
 * @author Jasmine
 *
 */

//Just adding a comment here to make sure I can push changes! -Megan


public class main {
	
	public static void main(String[] args) {
		//first check update
		manager mana = new manager();
		mana.cache();
		
		//ask input
		System.out.println(" Please type in a word. ");
		Scanner reader = new Scanner(System.in);  
		String input = reader.nextLine();
		while(!mana.validWord(input)){
			System.out.println(" Please type in a valid word. ");
			input = reader.nextLine();
		}
		
		//check if the word exist in the cache dictionary
		mana.inCache(input);
		
	}


	//

}
