import java.util.ArrayList;

public class StringArrayListMerger {
	
	public ArrayList<String> main;
	
	public StringArrayListMerger(ArrayList<String> mainArray){
		
		main = mainArray;
	}
	
	public void merge(ArrayList<String> otherArray){
		
		int counter = 0;
		while(counter < otherArray.size()){
			main.add(otherArray.get(counter));
		}
	}

}
