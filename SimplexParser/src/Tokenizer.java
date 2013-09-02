import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.xml.ws.spi.Invoker;


public class Tokenizer {
	String[] numberOfVars,c1;
	String[] problemArray;
	String[] minMax = new String[1];
	String[] c, b, Eqin;
	String[][] A;
	ArrayList<String> varArray = new ArrayList<String>(); //here are stored all the variables
	ArrayList<String> cArray = new ArrayList<String>();
	ArrayList<String> eqinArray = new ArrayList<String>();
	ArrayList<String> bArray = new ArrayList<String>();
	ArrayList<Integer> index = new ArrayList<Integer>();

	Pattern testMinMax = Pattern.compile("^(min|max).*$"); 
	Pattern testST = Pattern.compile("^s\\.t\\..*");
	Pattern testVar = Pattern.compile("((\\s?[-]?\\s?\\d+\\*?)x\\d*)"); //(\\d+\\*?x\\d*)");
	Pattern testOp = Pattern.compile("(\\+|-)");
	Pattern testCon = Pattern.compile(".*(<=?|=|=?>)\\s?(\\d+)");
	Matcher matchMinMax, matchST, matchVar, matchOp, matchCon;


	public Tokenizer(ArrayList<String> linesArray){
		problemArray = new String[linesArray.size()]; 
		problemArray = linesArray.toArray(problemArray);  //convert arrayList to a String array
		matchMinMax = testMinMax.matcher(problemArray[0]);
		matchST = testST.matcher(problemArray[1]);
		//matchVar = testVar.matcher(problemArray[0]);
		//matchOp = testOp.matcher(problemArray[0]);

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
		int n,i;
		for(int k=0;k<problemArray.length-1;k++){
			i=0;
			matchVar = testVar.matcher(problemArray[k]);
			matchOp = testOp.matcher(problemArray[k]);

			while(matchVar.find()){
				varArray.add(matchVar.group(1));
				cArray.add(matchVar.group(2));
			}
			System.out.println("Vars = "+cArray+" ");

			numberOfVars = varArray.get(varArray.size()-1).split("\\d+\\*?x"); //extract the size of the problem
			while(matchOp.find()){
				//System.out.println(" "+matchOp.group(1));
				i++;
			}
			index.add(i);
			System.out.println("index size: "+index.size());

			n = Integer.parseInt(numberOfVars[1]);
			System.out.println(""+n+" "+i);
			if(((n-1)!= i) && (problemArray[k].matches("\\S+"))){
				JOptionPane.showMessageDialog(null, "Error wrong number of operands between variables");
				i=0;
			}	
		}
	}

	public void findConstraints(){
		int k=1;
		while((k<problemArray.length-1) ){//&& !(problemArray[k].matches("\\S+"))){
			matchCon = testCon.matcher(problemArray[k]);

			while(matchCon.find()){
				eqinArray.add(matchCon.group(1));
				bArray.add(matchCon.group(2));
			}
			k++;
		}
		System.out.println("Con1: "+eqinArray+" "+bArray);
	}
	
	
	//eliminates white spaces and creates the table which is to be written to the file
	public ArrayList<String> eliminateWhiteSpaces(ArrayList<String> input){
		//ArrayList<String> input = new ArrayList<String>(input);
		
		Pattern eliminateWhiteSpaces = Pattern.compile("(\\s)");
		for(String in:input){
			Matcher m = eliminateWhiteSpaces.matcher(in);
			in = m.replaceAll("#");
		}
		ArrayList output = new ArrayList<String>(input);
		System.out.println("output: "+output);
		return output;

	}

	public void createFinalTables(){
		c = new String[index.get(0)+1];
		for(int i=0;i<=index.get(0);i++){
			c[i] = cArray.get(i);
		}
		
		b = new String[index.size()-1];
		Eqin = new String[index.size()-1];
		for(int i=0;i<index.size()-1;i++){
			b[i] = bArray.get(i);
			if(eqinArray.get(i).matches("\\s?<=?"))
				Eqin[i] = "-1";
			else if(eqinArray.get(i).matches("\\s?=?>"))
				Eqin[i] = "1";
			else
				Eqin[i] = "0";
		}
		
		A = new String[index.size()-1][4];
		int k = 4;
		for(int i=1;i<index.size();i++){
			for(int j=0;j<=index.get(i);j++){
				A[i-1][j] = cArray.get(k);
				k++;
			}
		}
		System.out.println("Vars = "+Arrays.toString(c)+" ");
		System.out.println("Eqin = "+Arrays.toString(Eqin)+" ");
		System.out.println("A = "+Arrays.deepToString(A)+" ");
	}
	//tests if the "s.t." exists in the right place
	public boolean existsST(){
		if(matchST.matches())
			return true;
		else
			return false;
	}

	public String[] getC() {
		return c;
	}

	public void setC(String[] c) {
		this.c = c;
	}

	public String[] getB() {
		return b;
	}

	public void setB(String[] b) {
		this.b = b;
	}

	public String[] getEqin() {
		return Eqin;
	}

	public void setEqin(String[] eqin) {
		Eqin = eqin;
	}

	public String[][] getA() {
		return A;
	}

	public void setA(String[][] a) {
		A = a;
	}

}

