package slap;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;


public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField textField_1;
	private JRadioButton rdbtnStudent;
	private JRadioButton rdbtnStaff;
	private JRadioButton rdbtnAdmin;
	private JButton btnLogIn;
	
	private String[] studentInfo = new String[2];
	private String[] staffInfo = new String[2];
	private String[] adminInfo = new String[2];
	
	ButtonGroup group = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public void execute(){	
		frame.setVisible(true);
	}
	public void hide(){
		frame.setVisible(false);
	}
	

	/**
	 * Create the application.
	 */
	public Login(String[] info) {
	
		studentInfo[0] = info[0];
		studentInfo[1] = info[1];
		staffInfo[0] = info[2];
		staffInfo[1] = info[3];
		adminInfo[0] = info[4];
		adminInfo[1] = info[5];
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(165, 70, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.addActionListener(new logInListener());
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(165, 108, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.addActionListener(new logInListener());
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(106, 73, 46, 14);
		frame.getContentPane().add(lblUser);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(87, 111, 65, 14);
		frame.getContentPane().add(lblPassword);
		
		rdbtnStudent = new JRadioButton("Student");
		rdbtnStudent.setBounds(154, 148, 109, 23);
		frame.getContentPane().add(rdbtnStudent);
		
		rdbtnStaff = new JRadioButton("Staff");
		rdbtnStaff.setBounds(154, 174, 109, 23);
		frame.getContentPane().add(rdbtnStaff);
		
		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBounds(154, 200, 109, 23);
		frame.getContentPane().add(rdbtnAdmin);
		
		btnLogIn = new JButton("Log in"); 
		btnLogIn.setBounds(162, 228, 89, 23);
		frame.getContentPane().add(btnLogIn);
		btnLogIn.addActionListener(new logInListener());
		
		JLabel lblLogInScreen = new JLabel("Log in Screen");
		lblLogInScreen.setBounds(177, 11, 86, 14);
		frame.getContentPane().add(lblLogInScreen);
		
		group.add(rdbtnStudent);
		group.add(rdbtnStaff);
		group.add(rdbtnAdmin);
		
		
	}
	
	public class logInListener implements ActionListener{
		
		 public void actionPerformed (ActionEvent e){
			 
			 if (e.getSource() == btnLogIn){
				 
				 if (rdbtnStudent.isSelected()){
					 
					if (isPasswordCorrect(studentInfo[1],textField_1.getPassword()) && textField.getText().equals(studentInfo[0])){
					 
						 StudentUI student = new StudentUI();
						 student.execute();
						 rdbtnStudent.setSelected(false);
						 textField_1.setText("");
						 textField.setText("");
					}
					else {
						 JOptionPane.showMessageDialog (null, "Incorrect login credentials, credentials are case sensitive", "Error", JOptionPane.INFORMATION_MESSAGE);
						 rdbtnStudent.setSelected(false);
						 textField_1.setText("");
						 textField.setText("");
					 }
					
				 }
				 
				 else if (rdbtnStaff.isSelected()){
					 
					 if (isPasswordCorrect(staffInfo[1],textField_1.getPassword()) && textField.getText().equals(staffInfo[0])){
					 
						 InstructorUI staff = new InstructorUI();
						 staff.execute();
						 rdbtnStaff.setSelected(false);
						 textField_1.setText("");
						 textField.setText("");
						 
					 
					 }
					 else {
						 JOptionPane.showMessageDialog (null, "Incorrect login credentials, credentials are case sensitive", "Error", JOptionPane.INFORMATION_MESSAGE);
						 rdbtnStaff.setSelected(false);
						 textField_1.setText("");
						 textField.setText("");
					 }
					 
				 }
				 
				 else if (rdbtnAdmin.isSelected()){
					 
					 if (isPasswordCorrect(adminInfo[1],textField_1.getPassword()) && textField.getText().equals(adminInfo[0])){
					 
						 AdministratorUI admin = new AdministratorUI();
						 admin.execute();
						 rdbtnAdmin.setSelected(false);
						 textField_1.setText("");
						 textField.setText("");
						 
					 
					 }
					 else {
						 JOptionPane.showMessageDialog (null, "Incorrect login credentials, credentials are case sensitive", "Error", JOptionPane.INFORMATION_MESSAGE);
						 rdbtnAdmin.setSelected(false);
						 textField_1.setText("");
						 textField.setText("");
					 }
					 
				 }
				 else {
					 JOptionPane.showMessageDialog (null, "Incorrect login credentials, credentials are case sensitive", "Error", JOptionPane.INFORMATION_MESSAGE);
					 rdbtnStudent.setSelected(false);
					 rdbtnAdmin.setSelected(false);
					 rdbtnStaff.setSelected(false);
					 textField_1.setText("");
					 textField.setText("");
				 }
				 
			 }
		     
		 }
		 
	}
	
	 private static boolean isPasswordCorrect(String realPassword, char [] input) {
	        boolean isCorrect = true;
	        char[] correctPassword = realPassword.toCharArray();

	        if (input.length != correctPassword.length) {
	            isCorrect = false;
	        } else {
	            for (int i = 0; i < input.length; i++) {
	                if (input[i] != correctPassword[i]) {
	                    isCorrect = false;
	                }
	            }
	        }

	        //Zero out the password.
	        for (int i = 0; i < correctPassword.length; i++) {
	            correctPassword[i] = 0;
	        }

	        return isCorrect;
	    }
	
	
	
}


