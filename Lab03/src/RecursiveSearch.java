
public class RecursiveSearch {

	public RecursiveSearch(int m, int r, int c,int[] usedSet, Puzzle puzzle){
		
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
		
		if(m <= 0 || r<0 || r > puzzle.length || c<0 || c > puzzle.length || contains == true){
			
		}
	}
}
