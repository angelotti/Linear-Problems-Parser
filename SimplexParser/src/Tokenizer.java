import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


public class Tokenizer {
	String[] numberOfVars;
	String[] problemArray;
	String[] minMax = new String[1];
	ArrayList<String> varArray = new ArrayList<String>(); //here are stored all the variables

	Pattern testMinMax = Pattern.compile("^(min|max).*$"); 
	Pattern testST = Pattern.compile("^s\\.t\\..*");
	Pattern testVar = Pattern.compile("(\\d+\\*?x\\d*)");
	Pattern testOp = Pattern.compile("(\\+|-)");
	Matcher matchMinMax, matchST, matchVar, matchOp;


	public Tokenizer(ArrayList<String> linesArray){
		problemArray = new String[linesArray.size()]; 
		problemArray = linesArray.toArray(problemArray);  //convert arrayList to a String array
		matchMinMax = testMinMax.matcher(problemArray[0]);
		matchST = testST.matcher(problemArray[1]);
		matchVar = testVar.matcher(problemArray[0]);
		matchOp = testOp.matcher(problemArray[0]);
	}

	//finds and stores the type of problem
	public void findMinMax(){
		while(matchMinMax.find()){
			System.out.println("found:"+matchMinMax.group(1));
			minMax[0] = matchMinMax.group(1);			
		}
		System.out.println("minMax[1] = "+minMax[0]);
	}

	//finds and stores the variables
	public void findVar(){
		int n,i=0;
		while(matchVar.find()){
			varArray.add(matchVar.group(1));
		}
		System.out.println("Vars = "+varArray+" ");

		numberOfVars = varArray.get(varArray.size()-1).split("\\d+\\*?x"); //extract the size of the problem
		while(matchOp.find()){
			System.out.println(" "+matchOp.group(1));
			i++;
		}
		n = Integer.parseInt(numberOfVars[1]);
		System.out.println(""+n+" "+i);
		if((n-1)!= i){
			JOptionPane.showMessageDialog(null, "Error wrong number of operands between variables");
			i=0;
		}
	}


	//tests if the "s.t." exists in the right place
	public boolean existsST(){
		if(matchST.matches())
			return true;
		else
			return false;
	}

}
