/**
 * 
 */
package slap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Kevin
 *
 */
@SuppressWarnings("serial")
public class SLAPAssignInstructionsTab extends JPanel{

	private SLAP slap;
	private SLAPFrame frame;
	private SLAPAssignmentTab sat;
	
	private JPanel mainPanel;
	private JPanel textPanel;
	
	private boolean itemVisibility = false;
	private JTextArea instructionText;
	private JScrollPane sp;
	
	JPanel main;
	private JPanel buttonPanel;
	private JButton saveButton;
	private JButton editButton;
	
	private final Color FIELD_COLOUR = Color.WHITE ;
	private final Color SAVE_COLOUR = Color.WHITE ;
	private final Color EDIT_COLOUR = Color.PINK ;
	//private final Font FIELD_FONT = new Font("Helvetica", Font.BOLD, 22) ;
	
	private boolean isEditable;
	
	public SLAPAssignInstructionsTab(SLAP slap, SLAPFrame frame, SLAPAssignmentTab sat) {
		this.slap = slap;
		this.frame = frame;
		this.sat = sat;
		initialize();
	}
	
	private void initialize() {		
		mainPanel = new JPanel();
		textPanel = new JPanel();
		
		this.setLayout(new BorderLayout());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
		
		instructionText = new JTextArea() ;
		instructionText.setBackground(FIELD_COLOUR) ;
		
		instructionText.setLineWrap(true) ;
		instructionText.setWrapStyleWord(true) ;
		
		sp = new JScrollPane(instructionText);
		
		refresh();
	}
	
	private void setUp() {
		textPanel.add(sp);
		mainPanel.add(textPanel);
		
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	private void setUpButtons(JPanel panel) {
		main = new JPanel() ;
		main.setLayout(new BorderLayout()) ;
		buttonPanel = new JPanel() ;
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)) ;				
		saveButton = new JButton("Save") ;		
		editButton = new JButton("Edit") ;	
		
		JPanel savePanel = new JPanel() ;
		savePanel.setLayout(new BorderLayout()) ; 
		savePanel.add(saveButton, BorderLayout.CENTER) ;
		JPanel editPanel = new JPanel() ;
		editPanel.setLayout(new BorderLayout()) ; 
		editPanel.add(editButton, BorderLayout.CENTER) ;
		
		class ButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource() ;
				if(button.equals(saveButton)) {
					if( sat.getComboBox().getSelectedItem() == null ) {
						
					}
					else {
						SLAPDocument doc = (SLAPDocument) sat.getComboBox().getSelectedItem() ;
						doc.setInfo(instructionText.getText()) ;
						//frame.coursesRefresh() ;
						frame.refresh() ;
						setInfoEnabled(false) ;
					}				
				}
				else if(button.equals(editButton)) {
					if(!isEditable) {
						setInfoEnabled(true);
					}
				}
			}
		}
		ButtonListener listener = new ButtonListener() ;
 		saveButton.addActionListener(listener) ;
 		editButton.addActionListener(listener) ;

 		buttonPanel.add(savePanel) ;
 		buttonPanel.add(editPanel) ;
 		buttonPanel.add(Box.createVerticalGlue()) ;
		
 		main.add(buttonPanel, BorderLayout.NORTH) ;
 		panel.add(main, BorderLayout.EAST) ;
 		//setButtonsEnabled(false) ;
	}
	
	protected void setButtonsEnabled(Boolean enabled) {
		saveButton.setEnabled(enabled);
		editButton.setEnabled(enabled);
	}
	
	protected void setInfoEnabled(Boolean enabled) {
		instructionText.setEditable(enabled) ;
		if(enabled) {
			setFieldBackgrounds(EDIT_COLOUR);
		}
		else {
			setFieldBackgrounds(SAVE_COLOUR);
		}
		isEditable = enabled ;
	}
	
	private void setFieldBackgrounds(Color colour) {
		instructionText.setBackground(colour) ;
	}
	
	protected JTextArea getInstructionText() {
		return instructionText;
	}
	
	/**
	 * Refresh the items in the tab
	 */
	protected void refresh() {
		if(slap.getCurrentUser() == null) {
			setInfoEnabled(false) ;
		}
		
		removeAll() ;
		
		setUp();
		setUpButtons(this);
		
		//Check if user has selected an assignment
		setButtonsEnabled(true);
		
		Account user = slap.getCurrentUser() ;
		if(user != null) {
			switch(user.getRole()) {
				case student: itemVisibility = false ; main.setVisible(itemVisibility) ; setButtonsEnabled(false); setInfoEnabled(false); break ;
				case instructor: itemVisibility = true ; main.setVisible(itemVisibility) ; break ;
				case administrator: itemVisibility = true ; main.setVisible(itemVisibility) ; break ;
				default: itemVisibility = false ; main.setVisible(itemVisibility) ; break ;
			}
		}
		else {
			setButtonsEnabled(false);
			itemVisibility = false ;
		}
		

		if(slap.getCurrentCourse() == null) {
			setButtonsEnabled(false);
			instructionText.setText("Please select a course!");
		} else {
			setButtonsEnabled(true);
			if(sat.getComboBox().getSelectedItem() == null)
				instructionText.setText("Please select an assignment!");
		}
		
		this.validate();
		frame.refresh();
	}
}