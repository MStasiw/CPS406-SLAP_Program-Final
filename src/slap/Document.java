package slap;
/**
 * This class is used to create document objects that can be used to upload or open a file.
 * 
 * To create a new document use:
 * Document yourdocumentname = new Document();
 * When this line is called it will open a file selector to choose the file to upload and save it to the resources folder.
 * 
 * Each document has a name field and can be associated with a person object(student, instructor or admin)
 */

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @author Kevin
 *
 */
public class Document {
	private File file;
	private File copiedFile;
	private String fileName;
	//private Account account;
	
	
	public Document() {
		//Select a document and save it only if a document was actually selected
		if(selectDocument() == JFileChooser.APPROVE_OPTION) {
			saveDocument();
		}
		
		fileName = "file name not specified";
	}

	/*
	 * Used for selecting a document.
	 * @return the file chooser's return
	 */
	private int selectDocument() {
		JFileChooser fileChooser = new JFileChooser();
		
		int fcReturn = fileChooser.showOpenDialog(null);
		
		if (fcReturn == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
		}
		
		return fcReturn;
	}
	
	
	/* 
	 * Used for saving the document to the resources folder. 
	 */
	private void saveDocument() {
		Path source = file.toPath();
		Path dest = null;
		
		//Get the path to the destination folder (the resources folder located in the same directory as the executable/JAR file)
		try {
			dest = (new File (new File(Document.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() + "\\SLAP-resources\\" + file.getName()).toPath());
			
			//Make the resources directory if it does not exist.
			File dir = new File(new File(Document.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() + "\\SLAP-resources\\");
				if(!dir.exists()){
					dir.mkdir();
				}			
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Files.copy(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Object[] options = {"Yes", "No"};
			int n = JOptionPane.showOptionDialog(null, "File Already Exists. Would you like to override it?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			
			if(n == JOptionPane.YES_OPTION) {
				try {
					Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		//Set the copiedFile File
		try {
			copiedFile = new File(new File(Document.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() + "\\SLAP-resources\\" + file.getName());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Attempts to the open the document for viewing.
	 */
	public void open() {
		String cmds[] = null;
		
		if( System.getProperty("os.name").contains("Windows")) {
			cmds = new String[] {"cmd", "/c", file.getAbsolutePath()};
		} else if( System.getProperty("os.name").contains("Mac")) {
			cmds = new String[] {"open", file.getAbsolutePath()};
		} else if( System.getProperty("os.name").contains("Linux")) {
			cmds = new String[] {"xdg-open", file.getAbsolutePath()};
		} else {
			JOptionPane.showMessageDialog(null, "Error: Your OS is not supported to open files.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	
		try {
			Runtime.getRuntime().exec(cmds);
			}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Could not open file at: " + copiedFile.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	/*
	 * Checks if the copiedFile still exists.
	 * @return true the file exists
	 * @return false the file does not exist
	 */
	public boolean doesExist() {
		if(copiedFile.exists() && !copiedFile.isDirectory()) {
			return true;
		}
		return false;
	}
	
	
	/*
	 * Sets the file name.
	 */
	public void setFileName(String name) {
		fileName = name;
	}
	
	
	/*
	 * Gets the file name.
	 */
	public String getFileName() {
		return fileName;
	}
}
