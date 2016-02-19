import java.util.ArrayList;

public class RecursiveSearch {

	public RecursiveSearch(){
		;
	}
	
	public ArrayList<String> search(int m, int r, int c, ArrayList<int[]> usedSet, Puzzle puzzle, String currentWord){
		
		//will check if r,c coordinates have been used
		boolean contains;
		int counter = 0;
		while(counter < usedSet.size()){
			if(c == usedSet.get(counter)[0] && r == usedSet.get(counter)[1]){
				contains = true;
			}
			else{
				contains = false;
			}
		}
		
		//adds current coordinates to the usedSet list
		int[] coordinates = {r,c};
		usedSet.add(coordinates);
		
		//For adding words to and returning
		ArrayList<String> strLst = new ArrayList<String>();
		
		//for error checking, failure results in empty list being returned
		if(m <= 0 || r<0 || r > puzzle.length || c<0 || c > puzzle.length || contains == true){
			return strLst;
		}
		
		if(m == 1){
			//code for adding single letter to list
			return strLst;
		}
		
		//recursion of method
		if(currentWord.length() <= m){
			
			//will merge two String Array Lists
			StringArrayListMerger merger = new StringArrayListMerger(strLst);
			
			RecursiveSearch rs = new RecursiveSearch();
			
			merger.merge(rs.search(m, r, c, usedSet, puzzle));
			merger.merge(rs.search(m, r, c +1, usedSet, puzzle));
			merger.merge(rs.search(m, r -1, c, usedSet, puzzle));
			merger.merge(rs.search(m, r +1, c, usedSet, puzzle));
			merger.merge(rs.search(m, r -1, c -1, usedSet, puzzle));
			merger.merge(rs.search(m, r +1, c +1, usedSet, puzzle));
			merger.merge(rs.search(m, r +1, c -1, usedSet, puzzle));
			merger.merge(rs.search(m, r -1, c +1, usedSet, puzzle));
		}
		else{
			return strLst;
		}
		//add elements from Recursive Search to Array List
		
	}
}
