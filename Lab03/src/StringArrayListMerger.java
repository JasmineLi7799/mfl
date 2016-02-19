import java.util.ArrayList;

public class StringArrayListMerger {
	
	public ArrayList<String> main;
	
	/**
	 * Constructor
	 * @param mainArray
	 */
	public StringArrayListMerger(ArrayList<String> mainArray){
		
		main = mainArray;
	}
	
	/**
	 * merges two string ArrayLists together 
	 * @param otherArray
	 */
	public void merge(ArrayList<String> otherArray){
		
		int counter = 0;
		while(counter < otherArray.size()){
			main.add(otherArray.get(counter));
			counter ++;
		}
	}

}
