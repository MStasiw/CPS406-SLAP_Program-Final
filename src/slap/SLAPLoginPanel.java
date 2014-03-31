package slap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.* ;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class SLAPLoginPanel extends JPanel {
	
	private SLAPFrame frame ;
	private SLAP slap ;
	
	private ImageIcon logoIcon ;
	private final int LOGO_ICON_SIZE = 128 ;
	
	private final int FIELD_LENGTH = 15 ;
	
	private JPanel outerPanel ;
	private JPanel panel ;
	
	private JHintTextField usernameField ;
	private JHintTextField passwordField ;
	private JPanel buttonPanel ;
	private JButton loginButton ;
	
	private Dimension rigidDimension ;
	private Dimension smallDimension ;
	private Font largeFont ;
	
	/**
	 * Make a new login panel
	 * @param frame the frame to display the login panel on
	 * @param slap the slap from which to control login
	 */
	public SLAPLoginPanel(SLAPFrame frame, SLAP slap) {
		this.frame = frame ;
		this.slap = slap ;
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
		smallDimension = new Dimension(0, FIELD_LENGTH / 2) ;
		//panel.add(Box.createRigidArea(smallDimension)) ;
		setLogo() ;
		panel.add(Box.createRigidArea(smallDimension)) ;
		setupFields() ;
		setupButton() ;
		panel.add(Box.createRigidArea(rigidDimension)) ;
		outerPanel.add(panel) ;
		add(outerPanel) ;
	}
	
	/**
	 * Sets the logo
	 */
	private void setLogo() {
		try{
			logoIcon = new ImageIcon(getClass().getResource(frame.logoIconPath)) ;
			logoIcon = new ImageIcon(
					logoIcon.getImage().getScaledInstance(
							LOGO_ICON_SIZE, 
							LOGO_ICON_SIZE, 
							java.awt.Image.SCALE_SMOOTH));
			JPanel logoPanel = new JPanel() ;
			logoPanel.add(new JLabel(logoIcon, JLabel.RIGHT)) ;
			panel.add(logoPanel) ;
		}
		catch(Exception e) {
			//
		}
	}
	
	/**
	 * Setup the username and password fields
	 */
	private void setupFields() {
		usernameField = new JHintTextField("Username", FIELD_LENGTH) ;
		passwordField = new JHintTextField("Password", FIELD_LENGTH) ;
		usernameField.setFont(largeFont) ;
		passwordField.setFont(largeFont) ;
		panel.add(usernameField) ;
		panel.add(passwordField) ;
	}
	
	/**
	 * Setup the login button
	 */
	private void setupButton() {
		buttonPanel = new JPanel() ;
		buttonPanel.setLayout(new BorderLayout());
		loginButton = new JButton("Login") ;
		loginButton.setFont(largeFont) ;
		loginButton.setMargin(new Insets(5, 0, 5, 0)) ;
		//loginButton.setMnemonic(KeyEvent.VK_ENTER);
		class LoginListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Check login credentials
				//System.out.println(AccountManagemer.DEBUG_listAccounts()); //Test if user accounts are accessible
				login() ;
			}
		}
		loginButton.addActionListener(new LoginListener());
		class EnterListener implements KeyListener {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode() ;
				if(code == KeyEvent.VK_ENTER) {
					//loginButton.doClick() ;
					login() ;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}			
		}
		EnterListener listener = new EnterListener() ;
		loginButton.addKeyListener(listener) ;
		usernameField.addKeyListener(listener) ;
		passwordField.addKeyListener(listener) ;
		buttonPanel.add(loginButton, BorderLayout.CENTER) ;
		panel.add(buttonPanel) ;
	}
	
	/**
	 * Get the value from the username field
	 * @return the username entered
	 */
	protected String getUsername() {
		return usernameField.getText() ;
	}
	
	/**
	 * Get the value from the password field
	 * @return the password entered
	 */
	protected String getPassword() {
		return passwordField.getText() ;
	}
	
	/**
	 * Clear the text from the username and password fields
	 */
	protected void clearText() {
		usernameField.clearText() ;
		passwordField.clearText();
	}
	
	/**
	 * Login if the values from the username and password fields are valid
	 */
	private void login() {
		Account user = AccountManager.authenticate(getUsername(), getPassword()) ;
		if (user != null) {
			slap.setCurrentUser(user);
			frame.login() ;
		}
		else {
			frame.displayError("Login credentials incorrect") ;
			passwordField.clearText() ;
		}
	}
}
