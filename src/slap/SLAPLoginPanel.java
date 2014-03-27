package slap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.* ;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class SLAPLoginPanel extends JPanel {
	
	private final int FIELD_LENGTH = 15 ;
	
	private JHintTextField usernameField ;
	private JHintTextField passwordField ;
	private JPanel buttonPanel ;
	private JButton loginButton ;
	
	private Dimension rigidDimension ;
	private Font largeFont ;
	
	public SLAPLoginPanel() {
		largeFont = new Font("Helvetica", Font.PLAIN, 20) ;
		setBorder(new CompoundBorder(
				new TitledBorder(new EtchedBorder(), 
						"Enter Login Credentials", 
						TitledBorder.CENTER, 
						TitledBorder.CENTER, largeFont), 
				new EmptyBorder(10, 10, 10, 10))) ;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)) ;
		rigidDimension = new Dimension(0, FIELD_LENGTH) ;
		add(Box.createRigidArea(rigidDimension)) ;
		setupFields() ;
		setupButton() ;
		add(Box.createRigidArea(rigidDimension)) ;
	}
	
	private void setupFields() {
		usernameField = new JHintTextField("Username", FIELD_LENGTH) ;
		passwordField = new JHintTextField("Password", FIELD_LENGTH) ;
		usernameField.setFont(largeFont) ;
		passwordField.setFont(largeFont) ;
		add(usernameField) ;
		add(passwordField) ;
	}
	
	private void setupButton() {
		buttonPanel = new JPanel() ;
		buttonPanel.setLayout(new BorderLayout());
		loginButton = new JButton("Login") ;
		loginButton.setFont(largeFont) ;
		loginButton.setMargin(new Insets(5, 0, 5, 0)) ;
		buttonPanel.add(loginButton, BorderLayout.CENTER) ;
		add(buttonPanel) ;
	}
}
