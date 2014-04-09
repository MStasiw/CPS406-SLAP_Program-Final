/**
 * 
 */
package slap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

/**
 * @author Kevin
 *
 */
public class SLAPAssignmentTab extends JPanel{

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane ;
	private VerticalScrollPanel vs_panel ;
	private SLAP slap ;
	private SLAPFrame frame;
	
	public SLAPAssignmentTab(SLAPFrame sframe, SLAP s) {
		this.slap = s;
		this.frame = sframe;
		
		setLayout(new BorderLayout()) ;
		vs_panel = new VerticalScrollPanel() ;
		vs_panel.setLayout(new BoxLayout(vs_panel, BoxLayout.Y_AXIS)) ;
		scrollPane = new JScrollPane() ;
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) ;
		scrollPane.setViewportView(vs_panel);
		add(scrollPane);
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
		
		JTabbedPane selectDisplay = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		selectDisplay.addTab("Instructions", new JPanel()) ;
		selectDisplay.addTab("Submit", new JPanel());
		vs_panel.add(Box.createVerticalStrut(10));
		vs_panel.add(selectAssign);
		vs_panel.add(Box.createVerticalStrut(10));
		vs_panel.add(new JSeparator());
		vs_panel.add(Box.createVerticalStrut(5));
		vs_panel.add(selectDisplay);
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
		vs_panel.removeAll() ;
		
		if(slap.getCurrentUser() != null) { //check if user is null, if so, there isn't anyone logged in
			if(slap.getCurrentUser().getRole() == Role.student) {
				setupStudentAssignmentGUI();
			} else if(slap.getCurrentUser().getRole() == Role.instructor) {
				setupInstructorAssignmentGUI();
			} else if(slap.getCurrentUser().getRole() == Role.administrator) {
				setupAdministratorAssignmentGUI();
			}
		}
		
		frame.refresh() ;
	}
}