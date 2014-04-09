/**
 * 
 */
package slap;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

/**
 * @author Kevin
 *
 */
public class SLAPAssignmentTab extends JPanel{

	private static final long serialVersionUID = 1L;

	private SLAP slap ;
	private SLAPFrame frame;
	private JPanel mainPanel;
	
	private SLAPAssignInstructionsStudent studentInstruct;
	
	public SLAPAssignmentTab(SLAPFrame sframe, SLAP s) {
		this.slap = s;
		this.frame = sframe;
		
		mainPanel = new JPanel();
		studentInstruct = new SLAPAssignInstructionsStudent(slap, frame);
		
		setLayout(new BorderLayout());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	}
	
	private void setupStudentAssignmentGUI() {
		//Set up the JComboBox used to select a course
		JPanel selectAssign = new JPanel();
		selectAssign.setLayout(new BoxLayout(selectAssign, BoxLayout.X_AXIS));
		JLabel comboLabel = new JLabel();
		comboLabel.setText("Select an assignment: ");
		JComboBox selectCombo = new JComboBox();
		
		selectCombo.setPreferredSize(new Dimension(300, 25));
		selectCombo.setMaximumSize(selectCombo.getPreferredSize());
		selectAssign.add(Box.createHorizontalGlue()) ;
		selectAssign.add(comboLabel);
		selectAssign.add(selectCombo);
		selectAssign.add(Box.createHorizontalGlue()) ;
		
		//Set up the JTabbedPane to the left
		JTabbedPane selectDisplay = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
		SLAPAdminUserTab userTab = new SLAPAdminUserTab(slap, frame) ;
		selectDisplay.addTab("Instructions", userTab);
		selectDisplay.addTab("Submit", studentInstruct);
		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(selectAssign);
		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(selectDisplay);
		add(mainPanel, BorderLayout.CENTER);
	}
	
	private void setupInstructorAssignmentGUI() {
		
	}
	
	private void setupAdministratorAssignmentGUI() {
		setupInstructorAssignmentGUI();
	}

	/**
	 * Refresh the items in the tab
	 */
	protected void refresh() {
		removeAll() ;
		
		if(slap.getCurrentUser() != null) { //check if user is null, if so, there isn't anyone logged in
			if(slap.getCurrentUser().getRole() == Role.student) {
				setupStudentAssignmentGUI();
			} else if(slap.getCurrentUser().getRole() == Role.instructor) {
				setupInstructorAssignmentGUI();
			} else if(slap.getCurrentUser().getRole() == Role.administrator) {
				setupAdministratorAssignmentGUI();
			}
		}
		
		this.validate();
		frame.refresh() ;
	}
}