package slap;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.* ;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SLAPAnnouncementTabItem extends JPanel {
	
	private JPanel panel ;
	private JPanel textPanel ;
	private JTextArea textArea ;
	private JPanel buttonPanel ;
	private JButton saveButton ;
	private JButton editButton ;
	private JButton deleteButton ;
	
	private SLAP slap ;
	private SLAPAnnouncementTab sat ;
	private Announcement a ;
		
	/**
	 * Makes a new item to add to a tab
	 */
	/*public SLAPAnnouncementTabItem() {
		initialize() ;
	}*/
	
	/**
	 * Makes a new item to add to a tab with a specified String
	 * @param text the text to initialize
	 */
	public SLAPAnnouncementTabItem(SLAP slap, SLAPAnnouncementTab sat, Announcement a) {
		this.slap = slap ;
		this.sat = sat ;
		this.a = a ;
		initialize() ;
		textArea.setText(a.getContent()) ;
	}
	
	private void initialize() {
		setLayout(new BorderLayout()) ;
		panel = new JPanel() ;		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)) ;
		textArea = new JTextArea() ;
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
	
	/*
	 * Setup the save, edit, and delete buttons
	 */
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
					a.setContent(textArea.getText()) ;
					sat.refresh() ;
				}
				else if(button.equals(editButton)) {
					textArea.setEditable(true) ;
					textArea.setEnabled(true) ;
				}
				else if(button.equals(deleteButton)) {
					//delete this item
					slap.getCurrentCourse().getAnnouncements().remove(a.getID()) ;
					sat.refresh() ;
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
