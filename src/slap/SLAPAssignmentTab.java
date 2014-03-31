/**
 * 
 */
package slap;

import java.awt.BorderLayout;
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
		//if(slap.getCurrentUser().getRole() == Role.administrator) {
		JButton test = new JButton("I'm a button");
		vs_panel.add(test);
		//}
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