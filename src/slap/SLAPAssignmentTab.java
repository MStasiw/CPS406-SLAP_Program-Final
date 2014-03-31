/**
 * 
 */
package slap;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		//setupAddButton(this) ;
		if(slap.getCurrentUser() != null) { //check if user is null, if so, there isn't anyone logged in
			if(slap.getCurrentUser().getRole() == Role.administrator) {
				setupStudentAssignmentGUI();
				refresh();
			}
		}
	}
	
	private void setupStudentAssignmentGUI() {
		//if(slap.getCurrentUser().getRole() == Role.administrator) {
		JButton test = new JButton("I'm a button");
		this.add(test);
		//}
	}

	/*
	private void setupAddButton(JPanel panel) {
		addButton = new JButton("+") ;
		addButton.setFont(ADD_BUTTON_FONT) ;
		class AddListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(slap.getCurrentCourse() != null) {
					Announcement temp = new Announcement("title", "content") ;
					slap.getCurrentCourse().getAnnouncements().add(temp.getID(), temp) ;
					refresh() ;
				}
			}	
		}
		addButton.addActionListener(new AddListener()) ;
		panel.add(addButton, BorderLayout.NORTH) ;
	}
	*/
	/**
	 * Refresh the items in the tab
	 */
	protected void refresh() {
		//panel.removeAll() ;
		
		if(slap.getCurrentUser() != null) { //check if user is null, if so, there isn't anyone logged in
			if(slap.getCurrentUser().getRole() == Role.administrator) {
				setupStudentAssignmentGUI();
			}
		}
		
		frame.refresh() ;
	}

}
