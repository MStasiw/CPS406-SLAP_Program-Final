package slap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.* ;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class SLAPLoginPanel extends JPanel {
	
	private final int FIELD_LENGTH = 15 ;
	
	private JPanel panel ;
	
	private JHintTextField usernameField ;
	private JHintTextField passwordField ;
	private JPanel buttonPanel ;
	private JButton loginButton ;
	
	private Dimension rigidDimension ;
	private Font largeFont ;
	
	public SLAPLoginPanel() {
		setLayout(new GridBagLayout()) ;
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
		add(panel) ;
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
		buttonPanel.add(loginButton, BorderLayout.CENTER) ;
		panel.add(buttonPanel) ;
	}
}
