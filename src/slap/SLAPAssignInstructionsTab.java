/**
 * 
 */
package slap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

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
	//private final Font FIELD_FONT = new Font("Helvetica", Font.BOLD, 22) ;
	
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
		mainPanel.add(saveButton);
		//mainPanel.add(Box.createVerticalStrut(10));
		//mainPanel.add(Box.createVerticalGlue());
		add(mainPanel);
	}
	
	private void setUpButtons(JPanel panel) {
		JPanel main = new JPanel() ;
		main.setLayout(new BorderLayout()) ;
		buttonPanel = new JPanel() ;
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)) ;				
		saveButton = new JButton("Save") ;		
		editButton = new JButton("Edit") ;	
		
		
	}
	
	/**
	 * Refresh the items in the tab
	 */
	protected void refresh() {
		removeAll() ;
		
		Account user = slap.getCurrentUser() ;
		if(user != null) {
			switch(user.getRole()) {
				case student: itemVisibility = false ; saveButton.setVisible(itemVisibility) ; instructionText.setEditable(false); break ;
				case instructor: itemVisibility = true ; saveButton.setVisible(itemVisibility) ; instructionText.setEditable(true);break ;
				case administrator: itemVisibility = true ; saveButton.setVisible(itemVisibility) ; instructionText.setEditable(true);break ;
				default: itemVisibility = false ; saveButton.setVisible(itemVisibility) ; break ;
			}
		}
		else {
			itemVisibility = false ;
		}

		setUp();
		frame.refresh();
	}
}