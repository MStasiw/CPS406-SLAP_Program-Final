/**
 * 
 */
package slap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JPanel selectAssign;
	private JTabbedPane selectDisplay;
	
	private JButton add;
	private JButton remove;
	
	private SLAPAssignInstructionsTab instruct;
	private SLAPViewAssignmentSubmissionTab viewsubs;
	
	public SLAPAssignmentTab(SLAPFrame sframe, SLAP s) {
		this.slap = s;
		this.frame = sframe;
		
		mainPanel = new JPanel();
		instruct = new SLAPAssignInstructionsTab(slap, frame, this);
		viewsubs = new SLAPViewAssignmentSubmissionTab(slap, frame);
		
		setLayout(new BorderLayout());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		setUpMainAssignmentGUI();
	}
	
	private void setUpMainAssignmentGUI() {
		//Set up the JComboBox used to select a course
		selectAssign = new JPanel();
		selectAssign.setLayout(new BoxLayout(selectAssign, BoxLayout.X_AXIS));
		JLabel comboLabel = new JLabel();
		comboLabel.setText("Select an Assignment: ");
		@SuppressWarnings("rawtypes")
		JComboBox selectCombo = new JComboBox();
		
		selectCombo.setPreferredSize(new Dimension(300, 25));
		selectCombo.setMaximumSize(selectCombo.getPreferredSize());
		selectAssign.add(Box.createHorizontalGlue()) ;
		selectAssign.add(comboLabel);
		selectAssign.add(selectCombo);
		
		//Set up the JTabbedPane to the left
		selectDisplay = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	
	private void setupStudentAssignmentGUI() {
		selectAssign.add(Box.createHorizontalGlue()) ;
		
		selectDisplay.addTab("Instructions", instruct);
		//selectDisplay.addTab("Submit", instruct);
		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(selectAssign);
		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(selectDisplay);
		add(mainPanel, BorderLayout.CENTER);
	}
	
	private void setupInstructorAssignmentGUI() {
		selectDisplay.addTab("Instructions", instruct);
		selectDisplay.addTab("View Submissions", viewsubs);
		
		add = new JButton("New Assignment");
		remove = new JButton("Remove");
		
		selectAssign.add(Box.createRigidArea(new Dimension(20, 1)));
		selectAssign.add(remove);
		selectAssign.add(Box.createHorizontalGlue());
		//selectAssign.add(Box.createHorizontalStrut(10));
		selectAssign.add(add);
		selectAssign.add(Box.createRigidArea(new Dimension(5, 1)));
		
		setUpButtonListeners();
		
		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(selectAssign);
		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(selectDisplay);
		add(mainPanel, BorderLayout.CENTER);
	}
	
	private void setupAdministratorAssignmentGUI() {
		setupInstructorAssignmentGUI();
	}

	private void setUpButtonListeners() {
		class ButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource() ;
				if(button.equals(add)) {
					if(slap.getCurrentCourse() == null) {
						JOptionPane.showMessageDialog(frame,"Please select a course.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {					
					String name = (String) JOptionPane.showInputDialog(frame, "Enter Assignment Name:", "New Assignment", 
							JOptionPane.INFORMATION_MESSAGE, null, null, null);
					
					SLAPDocument assign = new SLAPDocument(name, "");
					
					slap.getCurrentCourse().assignments.add(assign.getID(), assign);
					}
				}
				else if(button.equals(remove)) {
					
				}
			}
		}
		ButtonListener listener = new ButtonListener() ;
 		add.addActionListener(listener) ;
 		remove.addActionListener(listener) ;
	}
	
	/**
	 * Refresh the items in the tab
	 */
	protected void refresh() {
		removeAll();
		mainPanel.removeAll();
		selectDisplay.removeAll();
		selectAssign.removeAll();
		
		setUpMainAssignmentGUI();
		
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
		//frame.refresh();
		instruct.refresh();
		viewsubs.refresh();
	}
}