import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

public class GUI extends JFrame{
	
	JPanel mainPanel;
	JButton openFileButton, parseFileButton, saveFileButton;
	JFileChooser jfc;
	JTextArea log;
	
	public GUI() {
		super("Linear Problem Parser");
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
	
}
