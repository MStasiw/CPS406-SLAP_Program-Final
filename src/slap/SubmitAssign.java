package slap;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class SubmitAssign {

	private JFrame frmAssignmentSubmission;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnExit;

	/**
	 * Launch the application.
	 */
	public void execute() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubmitAssign window = new SubmitAssign();
					window.frmAssignmentSubmission.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public SubmitAssign() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAssignmentSubmission = new JFrame();
		frmAssignmentSubmission.setTitle("Assignment Submission");
		frmAssignmentSubmission.setBounds(100, 100, 629, 432);
		frmAssignmentSubmission.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAssignmentSubmission.getContentPane().setLayout(null);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(514, 345, 89, 23);
		frmAssignmentSubmission.getContentPane().add(btnSubmit);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(10, 345, 89, 23);
		frmAssignmentSubmission.getContentPane().add(btnExit);
		btnExit.addActionListener(new LogoutListener());
		
		textField = new JTextField();
		textField.setBounds(33, 50, 86, 20);
		frmAssignmentSubmission.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblAssignmentName = new JLabel("Assignment name:");
		lblAssignmentName.setBounds(32, 33, 134, 14);
		frmAssignmentSubmission.getContentPane().add(lblAssignmentName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(33, 105, 530, 229);
		frmAssignmentSubmission.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAssignment = new JLabel("Assignment");
		lblAssignment.setBounds(33, 80, 86, 14);
		frmAssignmentSubmission.getContentPane().add(lblAssignment);
	}
	
    public class LogoutListener implements ActionListener{
		
		 public void actionPerformed (ActionEvent e){
			 
			 if (e.getSource() == btnExit){
				 frmAssignmentSubmission.setVisible(false);
				 
			}
					
		 }
				 
	}
	
	
}
