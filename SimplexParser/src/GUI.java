import java.awt.Component;
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.*;

public class GUI extends JFrame{
	
	JPanel mainPanel;
	JButton openFileButton, parseFileButton, saveFileButton;
	JFileChooser jfc;
	JTextArea log;
	Console console;
	
	private ArrayList<String> linesArray = new ArrayList<String>();
	//Path path = Paths.get()
	Scanner s = null;
	
	public GUI() {
		super("Linear Problem Parser");
		console = System.console();
		mainPanel = new JPanel();
		log = new JTextArea(13,25);
        //log.setMargin(new Insets(5,5,5,5));
		
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        
        
        openFileButton = new JButton("Open File..");
        parseFileButton = new JButton("Parse File");
        saveFileButton = new JButton("Save File");
        
        mainPanel.add(openFileButton);
        mainPanel.add(parseFileButton);
        mainPanel.add(saveFileButton);
        mainPanel.add(log);
        
        this.setContentPane(mainPanel);
        this.setSize(300,280);
        this.setVisible(true);
        
	}
	
	public void appendTxtField(){
		System.out.println(linesArray.size());
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
	
	public ArrayList<String> getLinesArray() {
		return linesArray;
	}
}
