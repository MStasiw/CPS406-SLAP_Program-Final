/**
 * 
 */
package slap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		//instructionText.setFont(FIELD_FONT) ;
		instructionText.setLineWrap(true) ;
		instructionText.setWrapStyleWord(true) ;
		//instructionText.setBorder(new EmptyBorder(5, 5, 5, 5)) ;
		
		sp = new JScrollPane(instructionText);
		
		setUpButtons(this);
		refresh();
	}
	
	private void setUp() {
		
		//textPanel.add(Box.createHorizontalGlue());
		//textPanel.add(Box.createHorizontalStrut(10));
		textPanel.add(sp);
		//textPanel.add(Box.createHorizontalStrut(10));
		//textPanel.add(Box.createHorizontalGlue());
		
		//mainPanel.add(Box.createVerticalGlue());
		//mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(textPanel);
		//mainPanel.add(Box.createVerticalStrut(10));
		//mainPanel.add(Box.createVerticalGlue());
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	private void setUpButtons(JPanel panel) {
		JPanel main = new JPanel() ;
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
					//frame.coursesRefresh() ;
					frame.refresh() ;
					setInfoEnabled(false) ;
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
	
	private void setButtonsEnabled(Boolean enabled) {
		saveButton.setEnabled(enabled);
		editButton.setEnabled(enabled);
	}
	
	private void setInfoEnabled(Boolean enabled) {
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
	
	/**
	 * Refresh the items in the tab
	 */
	protected void refresh() {
		if(slap.getCurrentUser() == null) {
			setInfoEnabled(false) ;
		}
		
		removeAll() ;
		
		/*Account user = slap.getCurrentUser() ;
		if(user != null) {
			switch(user.getRole()) {
				case student: itemVisibility = false ; //saveButton.setVisible(itemVisibility) ; break ;
				case instructor: itemVisibility = true ; //saveButton.setVisible(itemVisibility) ; break ;
				case administrator: itemVisibility = true ; //saveButton.setVisible(itemVisibility) ; break ;
				default: itemVisibility = false ; //saveButton.setVisible(itemVisibility) ; break ;
			}
		}
		else {
			itemVisibility = false ;
		}*/
		
		//Check if user has selected an assignment
		setButtonsEnabled(true);

		setUp();
		setUpButtons(this);
		this.validate();
		frame.refresh();
	}
}