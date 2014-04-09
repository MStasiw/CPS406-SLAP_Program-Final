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

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author STS-i5
 *
 */
public class ViewSubmissionTab extends JPanel {
	
	private SLAP slap ;
	private SLAPFrame frame ;
	
	private final Color SAVE_COLOUR = Color.WHITE ;
	private final Color EDIT_COLOUR = Color.PINK ;
	private final Font LABEL_FONT = new Font("Helvetica", Font.BOLD, 22) ;
	private JPanel userPanel ;

	//Box.createHorizontalGlue(), Box.createVerticalStrut(10)
	private JPanel panel1;		//panel contains "name" jLabel, "name" jComboBox, "grade" label, "grade" jTextField
	private JLabel studentNameLabel;
	private JComboBox studentNameComboBox;
	private String defaultComboBoxString = "---select student---";
	private JLabel gradeLabel;
	private JTextField gradeTextField;
	
	private JPanel panel2; 	//panel contains horizontal glue, "save" jButton
	private JButton saveButton;
	
	private JPanel panel3;
	private JTextArea submisssionTextArea;
	
	
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
	//private JButton saveButton ;
	private JButton editButton ;
	private JButton removeButton ;
	
	private boolean isEditable ;
	
	//private Course selectedCourse ;
	
	public ViewSubmissionTab(SLAP slap, SLAPFrame frame){
		
		this.slap = slap ;
		this.frame = frame ;
		//selectedCourse = null ;
		initialize() ;
	}
	
	private void initialize() {
		setLayout(new BorderLayout()) ;
		setupCourses(this) ;
		setupButtons(this) ;
		refresh() ;
	}
	
	private void setupCourses(JPanel panel) {
		userPanel = new JPanel() ;
		userPanel.setLayout(new BorderLayout()) ;
		setupCourseEditor(userPanel) ;
		setupList(userPanel) ;
		panel.add(userPanel, BorderLayout.CENTER) ;
	}
	
	private void setupCourseEditor(JPanel panel) {
		userEditor = new JPanel() ;
		userEditor.setLayout(new BoxLayout(userEditor, BoxLayout.Y_AXIS)) ;
		userEditor.setBorder(new EtchedBorder()) ;
		JPanel labelPanel = new JPanel() ;
		usernameLabel = new JLabel("", JLabel.LEFT) ;
		usernameLabel.setFont(LABEL_FONT) ;
		labelPanel.add(usernameLabel, JPanel.LEFT_ALIGNMENT) ;
		labelPanel.setBorder(new EtchedBorder()) ;
		firstnameField = new JHintTextField("First name") ;
		lastnameField = new JHintTextField("Last name") ;
		userEditor.add(labelPanel) ;
		userEditor.add(firstnameField) ;
		userEditor.add(lastnameField) ;
		
		panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.setBorder(new EtchedBorder());
		studentNameLabel = new JLabel("Student Name", JLabel.LEFT);
		studentNameComboBox = new JComboBox();
		studentNameComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { defaultComboBoxString }));
		gradeLabel = new JLabel("View Submission", JLabel.LEFT);
		gradeTextField = new JTextField("");
		panel1.add(studentNameLabel);
		panel1.add(studentNameComboBox);
		panel1.add(Box.createHorizontalGlue());
		panel1.add(gradeLabel);
		panel1.add(gradeTextField);
		userEditor.add(panel1);
		
		setInfoEnabled(false) ;
		panel.add(userEditor, BorderLayout.SOUTH) ;
	}
	
	private void setupList(JPanel panel) {
		listModel = new DefaultListModel<String>() ;
		userList = new JList<String>(listModel) ;
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION) ;
		class CourseListener implements ListSelectionListener {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				setInfoEnabled(false) ;
				if (e.getValueIsAdjusting() == false) {
			        if (userList.getSelectedIndex() == -1) {
			        	setCourseInfo(null) ;
			        	setButtonsEnabled(false) ;
			        } 
			        else {
			        	setButtonsEnabled(true) ;
			        	String code = userList.getSelectedValue() ;
			        	Course course = (Course) slap.getCourseManager().get(code) ;
			        	//setCourseInfo(course) ;
			        }
			    }
			}	
		}
		userList.addListSelectionListener(new CourseListener()) ;
		scrollPane = new JScrollPane(userList) ;
		panel.add(scrollPane, BorderLayout.CENTER) ;
	}
	
	private void setupButtons(JPanel panel) {
		JPanel main = new JPanel() ;
		main.setLayout(new BorderLayout()) ;
		buttonPanel = new JPanel() ;
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)) ;	
		usernameField = new JHintTextField("Username", 10) ;			
		addButton = new JButton("Add") ;		
		saveButton = new JButton("Save") ;		
		editButton = new JButton("Edit") ;	
		removeButton = new JButton("Remove") ;	
		//
		JPanel codePanel = new JPanel() ;
		codePanel.setLayout(new BorderLayout()) ; 
		codePanel.add(usernameField, BorderLayout.CENTER) ;
		JPanel addPanel = new JPanel() ;
		addPanel.setLayout(new BorderLayout()) ; 
		addPanel.add(addButton, BorderLayout.CENTER) ;
		JPanel savePanel = new JPanel() ;
		savePanel.setLayout(new BorderLayout()) ; 
		savePanel.add(saveButton, BorderLayout.CENTER) ;
		JPanel editPanel = new JPanel() ;
		editPanel.setLayout(new BorderLayout()) ; 
		editPanel.add(editButton, BorderLayout.CENTER) ;
		JPanel removePanel = new JPanel() ;
		removePanel.setLayout(new BorderLayout()) ; 
		removePanel.add(removeButton, BorderLayout.CENTER) ;
		//
		class AddListener implements KeyListener {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode() ;
				if(code == KeyEvent.VK_ENTER) {
					addButton.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}
		}
		usernameField.addKeyListener(new AddListener());
		//
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
 		addButton.addActionListener(listener) ;
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
		firstnameField.setEditable(enabled) ;
		lastnameField.setEditable(enabled) ;
		if(enabled) {
			setFieldBackgrounds(EDIT_COLOUR) ;
		}
		else {
			setFieldBackgrounds(SAVE_COLOUR) ;
		}
		isEditable = enabled ;
	}
	
	private void setFieldBackgrounds(Color colour) {
		firstnameField.setBackground(colour) ;
		lastnameField.setBackground(colour) ;
	}
	
	@SuppressWarnings("rawtypes")
	protected void refresh() {
		if(slap.getCurrentUser() == null) {
			setInfoEnabled(false) ;
		}
		AccountMap map = slap.getAccountMap() ;
		if(map != null) {
			listModel.removeAllElements() ;
			/*for(Account account : map.) { // Need array returned for accounts
				listModel.addElement(account.getUsername()) ;
			}*/
		}
		else {
			listModel.removeAllElements() ;
		}
		userList.validate() ;
		frame.refresh() ;
	}	
	
}
