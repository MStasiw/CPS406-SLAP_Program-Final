/**
 * 
 */
package slap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author STS-i5
 *
 */
public class SLAPViewAssignmentSubmissionTab extends JPanel {
	
	private SLAP slap ;
	private SLAPFrame frame ;
	
	private final Color SAVE_COLOUR = Color.WHITE ;
	private final Color EDIT_COLOUR = Color.PINK ;
	private final Font LABEL_FONT = new Font("Helvetica", Font.BOLD, 12) ;
	private JPanel userPanel ;

	//Box.createHorizontalGlue(), Box.createVerticalStrut(10)
	private JPanel mainSubmissionPanel;
	private JPanel panel1;		//panel contains "name" jLabel, "name" jComboBox, "grade" label, "grade" jTextField
	private JLabel studentNameLabel;
	private JComboBox studentNameComboBox;
	private String defaultComboBoxString = "---select student---";
	private JLabel gradeLabel;
	private JTextField gradeTextField;
	
	private JPanel panel2; 	//panel contains horizontal glue, "save" jButton
	private JButton saveButton1;
	
	private JPanel panel3;
	private JTextArea submissionTextArea;
	
	
	private JScrollPane scrollPane ;
	private DefaultListModel<String> listModel ;
	private JList<String> userList ;
	
	private JPanel userEditor ;
	private JLabel usernameLabel ;
	private JHintTextField firstnameField ;
	private JHintTextField lastnameField ;
	
	private JPanel buttonPanel ;
	private JHintTextField usernameField ;
	private JButton addButton ;
	private JButton saveButton ;
	private JButton editButton ;
	private JButton removeButton ;
	
	private boolean isEditable ;
	
	//private Course selectedCourse ;
	
	public SLAPViewAssignmentSubmissionTab(SLAP slap, SLAPFrame frame){
		
		this.slap = slap ;
		this.frame = frame ;
		//selectedCourse = null ;
		initialize() ;
	}
	
	private void initialize() {
		setLayout(new BorderLayout()) ;
		//setupCourses(this) ;
		//setupButtons(this) ;
		
		setupAssignments(this);
		refresh() ;
	}
	
	private void setupAssignments(JPanel panel){
		mainSubmissionPanel = new JPanel();
		mainSubmissionPanel.setLayout(new BoxLayout(mainSubmissionPanel, BoxLayout.Y_AXIS));
		setupAssignmentsViewer(mainSubmissionPanel);
		panel.add(mainSubmissionPanel, BorderLayout.CENTER);
	}
	
	private void setupAssignmentsViewer(JPanel mPanel){
		panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.setBorder(new EtchedBorder());
		studentNameLabel = new JLabel("Student Name", JLabel.LEFT);
		studentNameLabel.setFont(LABEL_FONT) ;

		studentNameComboBox = new JComboBox();
		studentNameComboBox.setModel(new DefaultComboBoxModel(new String[] { defaultComboBoxString }));
		studentNameComboBox.setMinimumSize(new Dimension(150, 23));
		studentNameComboBox.setPreferredSize(new Dimension(200, 23));
		studentNameComboBox.setMaximumSize(new Dimension(200, 23));
		studentNameComboBox.setFont(LABEL_FONT) ;
		gradeLabel = new JLabel("Grade", JLabel.LEFT);
		gradeLabel.setFont(LABEL_FONT) ;
		gradeTextField = new JTextField("");
		gradeTextField.setMinimumSize(new Dimension(50, 23));
		gradeTextField.setPreferredSize(new Dimension(65, 23));
		gradeTextField.setMaximumSize(new Dimension(80, 23));
		gradeTextField.setFont(LABEL_FONT) ;
		//panel1.add(Box.createHorizontalGlue());
		panel1.add(studentNameLabel);
		panel1.add(Box.createHorizontalStrut(10));
		panel1.add(studentNameComboBox);
		panel1.add(Box.createHorizontalGlue());
		panel1.add(gradeLabel);
		panel1.add(Box.createHorizontalStrut(10));
		panel1.add(gradeTextField);
		//panel1.add(Box.createHorizontalGlue());
		mPanel.add(panel1);
		//mPanel.add(Box.createVerticalStrut(2));
		
		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.setBorder(new EtchedBorder());
		saveButton1 = new JButton("Save");
		saveButton1.setFont(LABEL_FONT) ;
		panel2.add(Box.createHorizontalGlue());
		panel2.add(saveButton1);
		mPanel.add(panel2);
		//mPanel.add(Box.createVerticalStrut(2));

		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.setBorder(new EtchedBorder());
		submissionTextArea = new JTextArea();
		JScrollPane textAreaScroll = new JScrollPane(submissionTextArea);
		panel3.add(textAreaScroll, BorderLayout.CENTER);
		mPanel.add(panel3);
		
		
	}
	
		private void setupButtons(JPanel panel) {

		class ButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource() ;
				if(button.equals(addButton)) {
					if(! usernameField.getText().equals("")) {
						String username = usernameField.getText() ;
						//if(! slap.getAccountMap().contains(username)) {
							/*Account account = new Account(username, ) ;
							slap.getAccountMap().add(course.getID(), course) ;
							refresh() ;
							userList.setSelectedValue(code, true) ;
							setInfoEnabled(true) ;*/
						//}
						/*else {
							frame.displayError("Username " + username + " is already registered.") ;
						}*/
					}
					else {
						frame.displayError("Please enter the username and password for the user to be added.") ;
					}
				}
				else if(button.equals(saveButton)) {
					if(userList.getSelectedIndex() != -1 && isEditable) {
						//Course course = (Course) slap.getCourseManager().get(userList.getSelectedValue()) ;
						//course.setName(nameField.getText()) ;
						//course.setProfessor(profField.getText()) ;
						frame.coursesRefresh() ;
						frame.refresh() ;
					}
					setInfoEnabled(false) ;
				}
				else if(button.equals(editButton)) {
					if(userList.getSelectedIndex() != -1 && ! isEditable) {
						setInfoEnabled(true) ;
					}
				}
				else if(button.equals(removeButton)) {
					if(userList.getSelectedIndex() != -1) {
						setInfoEnabled(false) ;
						setCourseInfo(null) ;
						slap.getCourseManager().remove(userList.getSelectedValue()) ;
						frame.coursesRefresh() ;
						refresh() ;
					}
				}
			}
		}
		ButtonListener listener = new ButtonListener() ;
/* 		addButton.addActionListener(listener) ;
 		saveButton.addActionListener(listener) ;
 		editButton.addActionListener(listener) ;
 		removeButton.addActionListener(listener) ;
 		buttonPanel.add(codePanel) ;
 		buttonPanel.add(addPanel) ;
 		buttonPanel.add(savePanel) ;
 		buttonPanel.add(editPanel) ;
 		buttonPanel.add(removePanel) ;
 		buttonPanel.add(Box.createVerticalGlue()) ;
 		buttonPanel.add(Box.createVerticalStrut(200)) ;
 		main.add(buttonPanel, BorderLayout.NORTH) ;
 		panel.add(main, BorderLayout.EAST) ;
 		setButtonsEnabled(false) ;
 */
	}
	
	private void setCourseInfo(Account account) {
		if(account != null) {
			usernameLabel.setText(account.getUsername()) ;
			firstnameField.setText(account.getFirstName()) ;
			lastnameField.setText(account.getLastName()) ;
		}
		else {
			usernameLabel.setText("") ;
			firstnameField.setText("") ;
			lastnameField.setText("") ;
		}
	}
	
	private void setButtonsEnabled(Boolean enabled) {
		saveButton.setEnabled(enabled) ;
		editButton.setEnabled(enabled) ;
		removeButton.setEnabled(enabled) ;
	}
	
	private void setInfoEnabled(Boolean enabled) {
/*		firstnameField.setEditable(enabled) ;
		lastnameField.setEditable(enabled) ;
		if(enabled) {
			setFieldBackgrounds(EDIT_COLOUR) ;
		}
		else {
			setFieldBackgrounds(SAVE_COLOUR) ;
		}
		isEditable = enabled ;
*/		
	}
	
	private void setFieldBackgrounds(Color colour) {
		firstnameField.setBackground(colour) ;
		lastnameField.setBackground(colour) ;
	}
	
	@SuppressWarnings("rawtypes")
	protected void refresh() {
/*		if(slap.getCurrentUser() == null) {
			setInfoEnabled(false) ;
		}
		AccountMap map = slap.getAccountMap() ;
		if(map != null) {
			listModel.removeAllElements() ;
			//for(Account account : map.) { // Need array returned for accounts
				listModel.addElement(account.getUsername()) ;
			}//
		}
		else {
			listModel.removeAllElements() ;
		}
		
*/		
		//userList.validate() ;
		mainSubmissionPanel.validate();
		frame.refresh() ;
	}	
	
}
