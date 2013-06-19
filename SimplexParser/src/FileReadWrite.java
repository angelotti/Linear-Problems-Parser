import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FileReadWrite {

	private ArrayList<String> linesArray = new ArrayList<String>();
	//Path path = Paths.get()
	Scanner s = null;
	
	public FileReadWrite(){}
	
	public void openFile() {
		try {
			s = new Scanner(new BufferedReader(new FileReader("problem.txt")));
			while(s.hasNextLine()){
			linesArray.add(s.nextLine());
			}
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found !");
		}
		finally{if(s != null) System.out.println("Closing PrintReader..."); s.close();}
	}
	
	
	public ArrayList<String> getLinesArray() {
		return linesArray;
	}
	public void setLinesArray(ArrayList<String> linesArray) {
		this.linesArray = linesArray;
	}
	
	
	
}

