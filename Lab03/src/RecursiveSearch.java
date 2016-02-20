import java.util.ArrayList;

public class RecursiveSearch {

	/**
	 * Do nothing constructor
	 */
	public RecursiveSearch(){
		;
	}
	
	/**
	 * Given an NxN puzzle, will check for all real words and return them as an ArrayList
	 * @param m max length of words to look for
	 * @param r row number in puzzle
	 * @param c column number in puzzle
	 * @param usedSet Array of integer lists containing used r,c coordinates
	 * @param puzzle Puzzle object to be searched
	 * @return
	 */
	public ArrayList<String> search(int m, int r, int c, ArrayList<int[]> usedSet, Puzzle puzzle){
		return search(m, r, c, usedSet, puzzle,"");
	}
	
	/**
	 * Given an NxN puzzle, will check for all real words and return them as an ArrayList
	 * @param m max length of words to look for
	 * @param r row number in puzzle
	 * @param c column number in puzzle
	 * @param usedSet Array of integer lists containing used r,c coordinates
	 * @param puzzle Puzzle object to be searched
	 * @param currentWord what the letters currently form
	 * @return
	 */
	private ArrayList<String> search(int m, int r, int c, ArrayList<int[]> usedSet, Puzzle puzzle, String currentWord){
		
		//for testing purposes
		System.out.println(r + ", " + c);
		
		//will check if r,c coordinates have been used
		boolean contains = false;
		int counter = 0;
		while(counter < usedSet.size()){
			if(c == usedSet.get(counter)[0] && r == usedSet.get(counter)[1]){
				contains = true;
				System.out.println("o");
			}
			else{
				contains = false;
			}
			counter ++;
		}
		
		//adds current coordinates to the usedSet list
		int[] coordinates = {r,c};
		usedSet.add(coordinates);
		
		//For adding words to and returning
		ArrayList<String> strLst = new ArrayList<String>();
		
		//for error checking, failure results in empty list being returned
		if(m <= 0 || r<0 || r > puzzle.side - 1 || c<0 || c > puzzle.side - 1 || contains == true){
			System.out.println("x");
			return strLst;
		}
		
		//code for adding single letter to list
		if(m == 1){
			return strLst;
		}
		
		if(currentWord.length() <= m){
			
			//will merge two String Array Lists
			StringArrayListMerger merger = new StringArrayListMerger(strLst);
			
			//recursion of method and merging of lists
			RecursiveSearch rs = new RecursiveSearch();
			
			//adds word to string list to be returned
			int[] coords = {c,r};
			
			String currentLetter = puzzle.getLetter(coords);
			String tempWord = currentWord + currentLetter;
			currentWord = tempWord;
			System.out.println("current word is: " + currentWord);
			
			manager mana = new manager();
			boolean isValid = mana.inCache(currentWord);
			if(isValid == true && rs.isInWordList(currentWord, strLst) == true){
				strLst.add(currentWord);
			}
			
			//performs recursion and merging of string lists
			//merger.merge(rs.search(m, r, c, usedSet, puzzle, currentWord));
			merger.merge(rs.search(m, r, c +1, usedSet, puzzle, currentWord));
			merger.merge(rs.search(m, r -1, c, usedSet, puzzle, currentWord));
			merger.merge(rs.search(m, r +1, c, usedSet, puzzle, currentWord));
			merger.merge(rs.search(m, r -1, c -1, usedSet, puzzle, currentWord));
			merger.merge(rs.search(m, r +1, c +1, usedSet, puzzle, currentWord));
			merger.merge(rs.search(m, r +1, c -1, usedSet, puzzle, currentWord));
			merger.merge(rs.search(m, r -1, c +1, usedSet, puzzle, currentWord));
		}
		else{
			return strLst;
		}
		
		//If all else fails
		return strLst;
		
		
	}
	
	/**
	 * checks an ArrayList to see if a word is already present in it
	 * @param word String to be checked for
	 * @param strLst ArrayList to see if word is in
	 * @return
	 */
	private boolean isInWordList(String word, ArrayList<String> strLst){
		
		int counter = 0;
		while(counter < strLst.size()){
			if(strLst.get(counter).compareTo(word) == 0){
				return false;
			}
			counter ++;
		}
		return true;
	}
}
