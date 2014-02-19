package slap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;


public class Login {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JRadioButton rdbtnStudent;
	private JRadioButton rdbtnStaff;
	private JRadioButton rdbtnAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
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
		
		textField_1 = new JTextField();
		textField_1.setBounds(165, 108, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
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
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.setBounds(162, 228, 89, 23);
		frame.getContentPane().add(btnLogIn);
		
		JLabel lblLogInScreen = new JLabel("Log in Screen");
		lblLogInScreen.setBounds(177, 11, 86, 14);
		frame.getContentPane().add(lblLogInScreen);
	}
}
