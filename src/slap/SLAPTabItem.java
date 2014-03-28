package slap;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.* ;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SLAPTabItem extends JPanel {
	
	private JPanel panel ;
	private JPanel textPanel ;
	private JTextArea textArea ;
	private JPanel buttonPanel ;
	private JButton saveButton ;
	private JButton editButton ;
	private JButton deleteButton ;
	
	private String longString = "This is some text and some more and some more\nhere is some more here ya go here ya go you like it don't you\nhere ya go hey where are you going you think you can escape from me you'll never get away!" ;
	
	public SLAPTabItem() {
		setLayout(new BorderLayout()) ;
		panel = new JPanel() ;		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)) ;
		textArea = new JTextArea(longString) ;
		textArea.setEditable(false) ;
		textArea.setEnabled(false) ;
		textArea.setLineWrap(true) ;
		textArea.setWrapStyleWord(true) ;
		textArea.setBorder(new EmptyBorder(5, 5, 5, 5)) ;
		textPanel = new JPanel() ;
		textPanel.setLayout(new BorderLayout()) ;
		textPanel.add(textArea) ;
        panel.add(textPanel) ;
        setupButtons() ;
		add(panel, BorderLayout.CENTER) ;
	}
	
	private void setupButtons() {
		buttonPanel = new JPanel() ;
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)) ;
		saveButton = new JButton("Save") ;
		editButton = new JButton("Edit") ;
		deleteButton = new JButton("Delete") ;
		class ButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource() ;
				if(button.equals(saveButton)) {
					textArea.setEditable(false);
					textArea.setEnabled(false) ;
					//save values
				}
				else if(button.equals(editButton)) {
					textArea.setEditable(true) ;
					textArea.setEnabled(true) ;
				}
				else if(button.equals(deleteButton)) {
					//delete this item
				}
			}	
		}
		ButtonListener listener = new ButtonListener() ;
 		saveButton.addActionListener(listener) ;
 		editButton.addActionListener(listener) ;
 		deleteButton.addActionListener(listener) ;
		buttonPanel.add(saveButton) ;
		buttonPanel.add(editButton) ;
		buttonPanel.add(deleteButton) ;
		panel.add(buttonPanel) ;
	}
}
