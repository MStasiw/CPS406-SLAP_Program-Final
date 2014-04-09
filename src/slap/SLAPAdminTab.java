package slap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.* ;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class SLAPAdminTab extends JPanel {

	private SLAP slap ;
	private SLAPFrame frame ;
	
	private final Color SAVE_COLOUR = Color.WHITE ;
	private final Color EDIT_COLOUR = Color.PINK ;
	private final Font LABEL_FONT = new Font("Helvetica", Font.BOLD, 22) ;
	
	private JPanel coursePanel ;
	
	private JScrollPane scrollPane ;
	private DefaultListModel<String> listModel ;
	private JList<String> courseList ;
	
	private JPanel courseEditor ;
	private JLabel codeLabel ;
	private JHintTextField nameField ;
	private JHintTextField profField ;
	private JTextArea descArea ;
	
	private JPanel buttonPanel ;
	private JHintTextField codeField ;
	private JButton addButton ;
	private JButton saveButton ;
	private JButton editButton ;
	private JButton removeButton ;
	
	//private Course selectedCourse ;
	
	public SLAPAdminTab(SLAP slap, SLAPFrame frame) {
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
		coursePanel = new JPanel() ;
		coursePanel.setLayout(new BorderLayout()) ;
		setupCourseEditor(coursePanel) ;
		setupList(coursePanel) ;
		panel.add(coursePanel, BorderLayout.CENTER) ;
	}
	
	private void setupCourseEditor(JPanel panel) {
		courseEditor = new JPanel() ;
		courseEditor.setLayout(new BoxLayout(courseEditor, BoxLayout.Y_AXIS)) ;
		courseEditor.setBorder(new EtchedBorder()) ;
		JPanel labelPanel = new JPanel() ;
		codeLabel = new JLabel("", JLabel.LEFT) ;
		codeLabel.setFont(LABEL_FONT) ;
		labelPanel.add(codeLabel, JPanel.LEFT_ALIGNMENT) ;
		labelPanel.setBorder(new EtchedBorder()) ;
		nameField = new JHintTextField("Name") ;
		profField = new JHintTextField("Professor") ;
		descArea = new JTextArea() ;
		descArea.setLineWrap(true) ;
		descArea.setWrapStyleWord(true) ;
		descArea.setBorder(new EmptyBorder(5, 5, 5, 5)) ;
		courseEditor.add(labelPanel) ;
		courseEditor.add(nameField) ;
		courseEditor.add(profField) ;
		courseEditor.add(descArea) ;
		setInfoEnabled(false) ;
		panel.add(courseEditor, BorderLayout.SOUTH) ;
	}
	
	private void setupList(JPanel panel) {
		listModel = new DefaultListModel<String>() ;
		courseList = new JList<String>(listModel) ;
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION) ;
		class CourseListener implements ListSelectionListener {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				setInfoEnabled(false) ;
				if (e.getValueIsAdjusting() == false) {
			        if (courseList.getSelectedIndex() == -1) {
			        	setCourseInfo(null) ;
			        } 
			        else {
			        	String code = courseList.getSelectedValue() ;
			        	Course course = (Course) slap.getCourseManager().get(code) ;
			        	setCourseInfo(course) ;
			        }
			    }
			}	
		}
		courseList.addListSelectionListener(new CourseListener()) ;
		scrollPane = new JScrollPane(courseList) ;
		panel.add(scrollPane, BorderLayout.CENTER) ;
	}
	
	private void setupButtons(JPanel panel) {
		JPanel main = new JPanel() ;
		main.setLayout(new BorderLayout()) ;
		buttonPanel = new JPanel() ;
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)) ;	
		codeField = new JHintTextField("Course Code", 10) ;			
		addButton = new JButton("Add") ;		
		saveButton = new JButton("Save") ;		
		editButton = new JButton("Edit") ;	
		removeButton = new JButton("Remove") ;	
		//
		JPanel codePanel = new JPanel() ;
		codePanel.setLayout(new BorderLayout()) ; 
		codePanel.add(codeField, BorderLayout.CENTER) ;
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
		class ButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource() ;
				if(button.equals(addButton)) {
					if(! codeField.getText().equals("")) {
						String code = codeField.getText() ;
						Course course = new Course(code) ;
						slap.getCourseManager().add(course.getID(), course) ;
						refresh() ;
						courseList.setSelectedValue(code, true) ;
						setInfoEnabled(true) ;
					}
				}
				else if(button.equals(saveButton)) {
					if(courseList.getSelectedIndex() != -1) {
						Course course = (Course) slap.getCourseManager().get(courseList.getSelectedValue()) ;
						course.setName(nameField.getText()) ;
						course.setProfessor(profField.getText()) ;
						course.setDescription(descArea.getText()) ;
						frame.coursesRefresh() ;
						frame.refresh() ;
					}
					setInfoEnabled(false) ;
				}
				else if(button.equals(editButton)) {
					if(courseList.getSelectedIndex() != -1) {
						setInfoEnabled(true) ;
					}
				}
				else if(button.equals(removeButton)) {
					if(courseList.getSelectedIndex() != -1) {
						setInfoEnabled(false) ;
						setCourseInfo(null) ;
						slap.getCourseManager().remove(courseList.getSelectedValue()) ;
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
	}
	
	private void setCourseInfo(Course course) {
		if(course != null) {
			codeLabel.setText(course.getCode()) ;
			nameField.setText(course.getName()) ;
			profField.setText(course.getProfessor()) ;
			descArea.setText(course.getDescription()) ;
		}
		else {
			codeLabel.setText("") ;
			nameField.setText("") ;
			profField.setText("") ;
			descArea.setText("") ;
		}
	}
	
	private void setInfoEnabled(Boolean enabled) {
		nameField.setEditable(enabled) ;
		profField.setEditable(enabled) ;
		descArea.setEditable(enabled) ;
		if(enabled) {
			setFieldBackgrounds(EDIT_COLOUR) ;
		}
		else {
			setFieldBackgrounds(SAVE_COLOUR) ;
		}		
	}
	
	private void setFieldBackgrounds(Color colour) {
		nameField.setBackground(colour) ;
		profField.setBackground(colour) ;
		descArea.setBackground(colour);
	}
	
	@SuppressWarnings("rawtypes")
	protected void refresh() {
		if(slap.getCurrentUser() == null) {
			setInfoEnabled(false) ;
		}
		Manager<String, Course> cm = slap.getCourseManager() ;
		if(cm != null) {
			listModel.removeAllElements() ;
			for(Comparable courseCode : cm.getIDArray()) {
				listModel.addElement(courseCode.toString()) ;
			}
		}
		else {
			listModel.removeAllElements() ;
		}
		courseList.validate() ;
		frame.refresh() ;
	}
}
