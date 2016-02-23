import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


/**
 * Cache dictionary: 
 * 	1. check() if need to update cache file(by compare the date of source file and cache file)
 * 	2. update() the cache file(get data from source file and sort them alphabetically)
 * 	3. ask user to input something, but have to be only letter(no number, no single letter)
 * 	4. checkValid() if the user input a valid word( a valid word should be found in the cache dictionary)
 * 
 * 
 * @author Jasmine
 *
 */

public class manager {
	//create an array of File to hold all the source files
	public File fileList[]; 
	public HashMap<String,String> source;
	public ArrayList<String> writeList;

	/**
	 * constructor
	 */
	public manager() {
		/*
		 * first, store all the source files in one File array
		 */
		File folder = new File("./dict-2");
		fileList = folder.listFiles();
		source = new HashMap();
		writeList = new ArrayList<String>();
		System.out.println("create manager ........");
		
	}

	/**
	 * cache method
	 * 1. check if cache exist
	 * 2. create cache file
	 * 3. update cache
	 */
	public void cache(){
		
		/*
		 * second, check if there is a cache file
		 * if yes, then check date
		 * if no, then create a cache file 
		*/
		if(cacheExist()){
			//check date and decide which file to update
			System.out.println(" hahahaha, cache already there! ");
			
			File cache = new File("./cache.txt");
			source = readFile("./cache.txt",source);
			System.out.println("the size of source hashmap : "+ source.size());
			
			for(int i = 1; i < fileList.length;i++){
				//if this file need to be updated, write the data to source array
				if(needToUpdate(fileList[i], cache)){
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					System.out.println("S: "+ sdf.format(fileList[i].lastModified()) + "    c: "+ sdf.format(cache.lastModified()));
					source = readFile(fileList[i].getPath(), source);
				}
			}
			//after checking the whole source file and add the new data to source array
			//then sort the source array and write it to cache
			sort(source);
			try
			{
			    String filename= "./cache.txt";
			    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
			    for(int j = 0; j < writeList.size(); j++){
					fw.write(writeList.get(j));
				}
			    fw.close();
			}
			catch(IOException ioe)
			{
			    System.err.println("IOException: " + ioe.getMessage());
			}
				
		}
		//there are is no cache, need to create a cache file
		else{
			System.out.println(" create new cache file !");
			//create cache file and copy sort the data from source file
			//1. read all the source file and store the data to an arrayList
			for(int i = 1; i < fileList.length; i++){
				source = readFile(fileList[i].getPath(), source);			
			}
			sort(source);
//			System.out.println(source);
			
			//2.write the data to the cache file
			PrintWriter writer;
			try {
				writer = new PrintWriter("cache.txt", "UTF-8");			
				writer.println(writeList.size());
				for(int j = 0; j < writeList.size(); j++){
					writer.println(writeList.get(j));
				}
				writer.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		 
		
	}
	
	/**
	 * check if there is a cache file 
	 * if the cache is exist, return true and then check if need to update or not
	 * if the cache is not exist, return false and then create the cache file and update data from source file
	 */
	public boolean cacheExist(){
		File f = new File("./cache.txt");
		if(f.exists() && !f.isDirectory()){
			//if the cache is exist
			return true;
		}
		return false;
	}
	
	/**
	 * check the date of two files
	 * if the last modified date of source file is earlier than the cache file, then do not need to update the cache file
	 * if the last modified date of source file is later then cache file, then we need to update the cache file
	 */
	public boolean needToUpdate(File source, File cache) {
		if(source.lastModified() < cache.lastModified() ){
			return false;    //dosen't need to update
		}
		return true;   // need to update
	}

	/**
	 * readFile method will gather the data from the source file and then put them into an arrayList of string
	 * only add the string that only contain letters( and the string more than one letter)
	 */
	public HashMap readFile(String filePath, HashMap source){
		ArrayList<String> temp1 = new ArrayList<String>();
		ArrayList<String> temp2 = new ArrayList<String>();
		BufferedReader br = null;
		
		try {
			String sCurrentLine;
			
			// "Users/Jasmine/Documents/Eclipse/CacheDictionary/src/english.txt"
			br = new BufferedReader(new FileReader(filePath)); 
			
			//str.matches(".*\\d+.*"); ==> string that contains numbers
			//.matches("[a-zA-Z]+"); ==> string that only contains letter
			while ((sCurrentLine = br.readLine()) != null) {
				
				/*
				 * if the source file itself is not one word per line, we need to split the string
				 * only letter(not single) will be stored in the array
				 */
				//
				if(sCurrentLine.matches(".*([ \t]).*")){  //check if the current line is a single word or not
					temp1.add(sCurrentLine);
				}
				else if(sCurrentLine.matches("[a-zA-Z]+") && sCurrentLine.length()>1){
					temp2.add(sCurrentLine);
				}
			}// end of while loop
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		if(!temp1.isEmpty()){
			for(int i = 0; i< temp1.size(); i++){
				String thisLine[] = temp1.get(i).split(" ");
				//for each word in this line
				for(int j = 0; j < thisLine.length; j++){
					//if it is a valid word
					if(thisLine[j].matches("[a-zA-Z]+") && thisLine[j].length()>1 ){
						if( source.get(thisLine[j]) == null){
							source.put(thisLine[j].toLowerCase(),thisLine[j].toLowerCase());
						}
								
					} // end of if current word i valid	
				}
			}	
		} // end of temp1
		
		if(!temp2.isEmpty()){
			for(int i = 0; i< temp2.size(); i++){
				if(temp2.get(i).matches("[a-zA-Z]+") && temp2.get(i).length()>1){
					if(source.get(temp2.get(i)) == null){
						source.put(temp2.get(i).toLowerCase(),temp2.get(i).toLowerCase());
					}
							
				} 
			}
			
		}
		return source;
	}
	
	/**
	 * sort the arrayList
	 */
	public void sort(HashMap source){
	//	writeList = (ArrayList)source.keySet();
		// Converting HashMap keys into ArrayList
		writeList = new ArrayList<String>(source.keySet());
		Collections.sort(writeList, String.CASE_INSENSITIVE_ORDER);
		
	}
   
	/**
	 * check if the player input a valid word
	 * valid word: only letter, more than two 
	 */
	public boolean validWord(String input){
		//if it is a valid word, return true
		if(input.matches("[a-zA-Z]+") && input.length()>1){
			return true;
		}
		return false;
	}
	
	
	/**
	 * check if the word is in the cache or not
	 */
	public boolean inCache(String word){
	//	System.out.println("checking word is:  "+"-"+word+"-");
	//	System.out.println(source.containsValue(word));
		if(source.get(word) != null){
	//		System.out.println("This word is in the cache dictionary ");
			return true;
		}
	//	System.out.println("this word is not in the cache dictionary ");
		return false;
	}

}