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
	private VerticalScrollPanel panel ;
	private SLAP slap ;
	
	public SLAPAssignmentTab(SLAPFrame frame, SLAP slap) {
		this.slap = slap;
		setLayout(new BorderLayout()) ;
		panel = new VerticalScrollPanel() ;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)) ;
		scrollPane = new JScrollPane() ;
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) ;
		scrollPane.setViewportView(panel);
		add(scrollPane);
		
		if(slap.getCurrentUser() != null) { //check if user is null, if so, there isn't anyone logged in
			if(slap.getCurrentUser().getRole() == Role.student) {
				setupStudentAssignmentGUI();
				JHintTextField passwordField = new JHintTextField("Password", 15) ;
				this.add(passwordField);
			}
		}
	}
	
	private void setupStudentAssignmentGUI() {
		
	}

}
