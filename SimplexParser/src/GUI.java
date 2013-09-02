import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.*;

public class GUI extends JFrame{
	
	JPanel mainPanel;
	JButton openFileButton, parseFileButton, saveFileButton;
	JFileChooser fc;
	JTextArea log;
	Console console;
	
	private Tokenizer tkz;
	private ArrayList<String> linesArray = new ArrayList<String>();
	//Path path = Paths.get()
	Scanner s = null;
	
	public GUI() {
		super("Linear Problem Parser");
		openFile();

		tkz = new Tokenizer(linesArray);
		console = System.console();
		mainPanel = new JPanel();
		log = new JTextArea(23,45);
        //log.setMargin(new Insets(5,5,5,5));
		
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        
        
        
        //openFileButton = new JButton("Open File..");
        //parseFileButton = new JButton("Parse File");
        //saveFileButton = new JButton("Save File");
        
        //mainPanel.add(openFileButton);
        //mainPanel.add(parseFileButton);
        //mainPanel.add(saveFileButton);
        mainPanel.add(log);
        
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
        
	}
	
	public void appendTxtField(){
		System.out.println(linesArray.size());
		log.append("(LP-1)\n");
		for(String str:linesArray)
			log.append(str+"\n");
	}
	
	
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
	
	public void parseFile(){
		appendTxtField();
		tkz.findMinMax();
		tkz.findVar();
		tkz.findConstraints();
		tkz.createFinalTables();
		writeFile();
	}
	public void writeFile(){
		try{
			File file = new File("problem(LP-2).txt");
			if(!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("c = "+Arrays.toString(tkz.getC()));
			bw.newLine();
			bw.write("A = "+Arrays.deepToString(tkz.getA()));
			bw.newLine();
			bw.write("b = "+Arrays.toString(tkz.getB()));
			bw.newLine();
			bw.write("Eqin = "+Arrays.toString(tkz.getEqin()));
			bw.close();
			
			log.append("\n(LP-2)\nc = "+Arrays.toString(tkz.getC())+
					"\nA = "+Arrays.deepToString(tkz.getA())+
					"\nb = "+Arrays.toString(tkz.getB())+
					"\nEqin = "+Arrays.toString(tkz.getEqin()));
			
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> getLinesArray() {
		return linesArray;
	}
}
