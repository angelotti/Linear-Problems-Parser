import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	
	//Path path = Paths.get()
	Scanner s = null;
	int i;
	
	public static void main(String[] args) {
		ArrayList<String> linesArray = new ArrayList<String>();
		GUI g =new GUI();
		
		//g = new GUI();
		g.openFile();
		g.appendTxtField();
		linesArray = g.getLinesArray();
		Tokenizer tkz = new Tokenizer(linesArray);
		tkz.findMinMax();
		
	}

	

}
