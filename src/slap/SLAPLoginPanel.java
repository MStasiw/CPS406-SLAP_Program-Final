package slap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.* ;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class SLAPLoginPanel extends JPanel {
	
	private SLAPFrame frame ;
	
	private final int FIELD_LENGTH = 15 ;
	
	private JPanel outerPanel ;
	private JPanel panel ;
	
	private JHintTextField usernameField ;
	private JHintTextField passwordField ;
	private JPanel buttonPanel ;
	private JButton loginButton ;
	
	private Dimension rigidDimension ;
	private Font largeFont ;
	
	public SLAPLoginPanel(SLAPFrame frame) {
		this.frame = frame ;
		setLayout(new GridBagLayout()) ;
		outerPanel = new JPanel() ;
		outerPanel.setBorder(new EtchedBorder()) ;
		panel = new JPanel() ;		
		largeFont = new Font("Helvetica", Font.PLAIN, 20) ;
		panel.setBorder(new CompoundBorder(
				new TitledBorder(new EtchedBorder(), 
						"Enter Login Credentials", 
						TitledBorder.CENTER, 
						TitledBorder.CENTER, largeFont), 
				new EmptyBorder(10, 10, 10, 10))) ;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)) ;
		rigidDimension = new Dimension(0, FIELD_LENGTH) ;
		panel.add(Box.createRigidArea(rigidDimension)) ;
		setupFields() ;
		setupButton() ;
		panel.add(Box.createRigidArea(rigidDimension)) ;
		outerPanel.add(panel) ;
		add(outerPanel) ;
	}
	
	private void setupFields() {
		usernameField = new JHintTextField("Username", FIELD_LENGTH) ;
		passwordField = new JHintTextField("Password", FIELD_LENGTH) ;
		usernameField.setFont(largeFont) ;
		passwordField.setFont(largeFont) ;
		panel.add(usernameField) ;
		panel.add(passwordField) ;
	}
	
	private void setupButton() {
		buttonPanel = new JPanel() ;
		buttonPanel.setLayout(new BorderLayout());
		loginButton = new JButton("Login") ;
		loginButton.setFont(largeFont) ;
		loginButton.setMargin(new Insets(5, 0, 5, 0)) ;
		class LoginListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Check login credentials
				//System.out.println(AccountManagement.DEBUG_listAccounts()); //Test if user accounts are accessible
				if (AccountManagement.authenticate(getUsername(), getPassword())) {
					frame.login() ;
				}else{
					frame.displayError("Login credentials incorrect") ;
				}
			}
		}
		loginButton.addActionListener(new LoginListener());
		buttonPanel.add(loginButton, BorderLayout.CENTER) ;
		panel.add(buttonPanel) ;
	}
	
	protected String getUsername() {
		return usernameField.getText() ;
	}
	
	protected String getPassword() {
		return passwordField.getText() ;
	}
}
