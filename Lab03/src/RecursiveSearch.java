import java.util.ArrayList;

public class RecursiveSearch {

	public ArrayList<String> RecursiveSearch(int m, int r, int c,int[][] usedSet, Puzzle puzzle){
		
		//will check if r,c coordinates have been used
		boolean contains;
		int counter = 0;
		while(counter < usedSet.length){
			if(c == usedSet[counter][0] && r == usedSet[counter][1]){
				contains = true;
			}
			else{
				contains = false;
			}
		}
		
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
		RecursiveSearch rsL = new RecursiveSearch(m, r, c -1, usedSet, puzzle);
		RecursiveSearch rsR = new RecursiveSearch(m, r, c +1, usedSet, puzzle);
		RecursiveSearch rsU = new RecursiveSearch(m, r -1, c, usedSet, puzzle);
		RecursiveSearch rsD = new RecursiveSearch(m, r +1, c, usedSet, puzzle);
		RecursiveSearch rsUL = new RecursiveSearch(m, r -1, c -1, usedSet, puzzle);
		RecursiveSearch rsDR = new RecursiveSearch(m, r +1, c +1, usedSet, puzzle);
		RecursiveSearch rsDL = new RecursiveSearch(m, r +1, c -1, usedSet, puzzle);
		RecursiveSearch rsUR = new RecursiveSearch(m, r -1, c +1, usedSet, puzzle);
		
		//add elements from Recursive Search to Array List
		
	}
}
