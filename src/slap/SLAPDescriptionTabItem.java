package slap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.* ;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SLAPDescriptionTabItem extends JPanel {
	
	private JPanel panel ;
	private JPanel textPanel ;
	private JTextArea infoArea ;
	private JTextArea textArea ;
	private JPanel buttonPanel ;
	private JButton saveButton ;
	private JButton editButton ;
	
	private SLAP slap ;
	private SLAPDescriptionTab sdt ;
	
	private final Color SAVE_COLOUR = Color.WHITE ;
	private final Color EDIT_COLOUR = Color.PINK ;
	private final Color FIELD_COLOUR = Color.LIGHT_GRAY ;
	private final Font FIELD_FONT = new Font("Helvetica", Font.BOLD, 22) ;
	
	private boolean itemVisibility = false ;
	
	public SLAPDescriptionTabItem(SLAP slap, SLAPDescriptionTab sdt) {
		this.slap = slap ;
		this.sdt = sdt ;
		initialize() ;
	}
	
	private void initialize() {
		setLayout(new BorderLayout()) ;
		panel = new JPanel() ;		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)) ;
		infoArea = new JTextArea() ;
		infoArea.setBackground(FIELD_COLOUR) ;
		infoArea.setFont(FIELD_FONT) ;
		infoArea.setLineWrap(true) ;
		infoArea.setWrapStyleWord(true) ;
		infoArea.setBorder(new EmptyBorder(5, 5, 5, 5)) ;
		//
		infoArea.setEditable(false) ;
		textArea = new JTextArea() ;
		textArea.setEditable(false) ;
		//textArea.setEnabled(false) ;
		textArea.setLineWrap(true) ;
		textArea.setWrapStyleWord(true) ;
		textArea.setBorder(new EmptyBorder(5, 5, 5, 5)) ;
		textPanel = new JPanel() ;
		textPanel.setLayout(new BorderLayout()) ;
		textPanel.add(infoArea, BorderLayout.NORTH) ;
		textPanel.add(textArea, BorderLayout.CENTER) ;
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
		class ButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource() ;
				if(button.equals(saveButton)) {
					//textField.setEditable(false) ;
					textArea.setEditable(false) ;
					//textArea.setEnabled(false) ;
					//save values
					Course course = slap.getCurrentCourse() ;
					if(course != null) course.setDescription(textArea.getText()) ;
					sdt.refresh() ;
				}
				else if(button.equals(editButton)) {
					//textField.setEditable(true) ;
					textArea.setEditable(true) ;
					textArea.setBackground(EDIT_COLOUR) ;
					//textArea.setEnabled(true) ;
				}
			}	
		}
		ButtonListener listener = new ButtonListener() ;
 		saveButton.addActionListener(listener) ;
 		editButton.addActionListener(listener) ;
		buttonPanel.add(saveButton) ;
		buttonPanel.add(editButton) ;
		panel.add(buttonPanel) ;
	}
	
	protected void refresh() {
		Account user = slap.getCurrentUser() ;
		if(user != null) {
			switch(user.getRole()) {
				case student: itemVisibility = false ; break ;
				case instructor: itemVisibility = true ; break ;
				case administrator: itemVisibility = true ; break ;
				default: itemVisibility = false ; break ;
			}
		}
		else {
			itemVisibility = false ;
		}
		setEditVisibility(itemVisibility) ;
		Course course = slap.getCurrentCourse() ;
		if(course != null) {
			//textField.setText(course.getCode()) ; //Show just the code?
			infoArea.setText(course.toString()) ;
			textArea.setText(course.getDescription()) ;
			textArea.setBackground(SAVE_COLOUR) ;
		}
		else {
			infoArea.setText("Select a Course") ;
			textArea.setText("") ;
			textArea.setBackground(SAVE_COLOUR) ;
		}
	}
	
	protected void setEditVisibility(Boolean visibility) {
		saveButton.setVisible(visibility) ;
		editButton.setVisible(visibility) ;
		saveButton.setEnabled(visibility) ;
		editButton.setEnabled(visibility) ;
	}
}