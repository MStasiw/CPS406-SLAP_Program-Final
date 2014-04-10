package slap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.* ;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class SLAPAdminUserTab extends JPanel {

	private SLAP slap ;
	private SLAPFrame frame ;
	
	private final Color SAVE_COLOUR = Color.WHITE ;
	private final Color EDIT_COLOUR = Color.PINK ;
	private final Font LABEL_FONT = new Font("Helvetica", Font.BOLD, 22) ;
	
	private JPanel userPanel ;
	
	private JScrollPane scrollPane ;
	private DefaultListModel<String> listModel ;
	private JList<String> userList ;
	
	private JPanel userEditor ;
	private JLabel usernameLabel ;
	private JHintTextField firstnameField ;
	private JHintTextField lastnameField ;
	
	private JPanel buttonPanel ;
	private JHintTextField usernameField ;
	private JHintTextField passwordField ;
	private JComboBox<String> roleBox ;
	private JButton addButton ;
	private JButton saveButton ;
	private JButton editButton ;
	private JButton removeButton ;
	
	private boolean isEditable ;
	
	private final String STUDENT = "student" ;
	private final String INSTRUCTOR = "instructor" ;
	private final String ADMINISTRATOR = "administrator" ;
	
	public SLAPAdminUserTab(SLAP slap, SLAPFrame frame) {
		this.slap = slap ;
		this.frame = frame ;
		initialize() ;
	}
	
	private void initialize() {
		setLayout(new BorderLayout()) ;
		setupUsers(this) ;
		setupButtons(this) ;
		refresh() ;
	}
	
	private void setupUsers(JPanel panel) {
		userPanel = new JPanel() ;
		userPanel.setLayout(new BorderLayout()) ;
		setupUserEditor(userPanel) ;
		setupList(userPanel) ;
		panel.add(userPanel, BorderLayout.CENTER) ;
	}
	
	private void setupUserEditor(JPanel panel) {
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
		setInfoEnabled(false) ;
		panel.add(userEditor, BorderLayout.SOUTH) ;
	}
	
	private void setupList(JPanel panel) {
		listModel = new DefaultListModel<String>() ;
		userList = new JList<String>(listModel) ;
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION) ;
		class UserListener implements ListSelectionListener {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				setInfoEnabled(false) ;
				if (e.getValueIsAdjusting() == false) {
			        if (userList.getSelectedIndex() == -1) {
			        	setUserInfo(null) ;
			        	setButtonsEnabled(false) ;
			        } 
			        else {
			        	setButtonsEnabled(true) ;
			        	String username = userList.getSelectedValue() ;
			        	Account account = AccountManager.getAccountObj(username) ;
			        	setUserInfo(account) ;
			        }
			    }
			}	
		}
		userList.addListSelectionListener(new UserListener()) ;
		scrollPane = new JScrollPane(userList) ;
		panel.add(scrollPane, BorderLayout.CENTER) ;
	}
	
	private void setupButtons(JPanel panel) {
		JPanel main = new JPanel() ;
		main.setLayout(new BorderLayout()) ;
		buttonPanel = new JPanel() ;
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)) ;	
		usernameField = new JHintTextField("Username", 10) ;
		passwordField = new JHintTextField("Password", 10) ;
		roleBox = new JComboBox<String>(new String[] {STUDENT, INSTRUCTOR, ADMINISTRATOR}) ;
		addButton = new JButton("Add") ;		
		saveButton = new JButton("Save") ;		
		editButton = new JButton("Edit") ;	
		removeButton = new JButton("Remove") ;	
		//
		JPanel usernamePanel = new JPanel() ;
		usernamePanel.setLayout(new BorderLayout()) ; 
		usernamePanel.add(usernameField, BorderLayout.CENTER) ;
		JPanel passwordPanel = new JPanel() ;
		passwordPanel.setLayout(new BorderLayout()) ; 
		passwordPanel.add(passwordField, BorderLayout.CENTER) ; 
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
		AddListener listener = new AddListener() ;
		usernameField.addKeyListener(listener) ;
		passwordField.addKeyListener(listener) ;
		//
		class ButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource() ;
				if(button.equals(addButton)) {
					if(! usernameField.getText().equals("") && ! passwordField.getText().equals("")) {
						String username = usernameField.getText() ;
						String password = passwordField.getText();
						if(! slap.getAccountMap().userExists(username)) {
							Role role ;
							if(roleBox.getSelectedItem().equals(INSTRUCTOR)) role = Role.instructor ;
							else if(roleBox.getSelectedItem().equals(ADMINISTRATOR)) role = Role.administrator ;
							else role = Role.student ;
							Account account = new Account("", "", username, password, role) ;
							slap.getAccountMap().addAccount(account) ;
							refresh() ;
							userList.setSelectedValue(username, true) ;
							setInfoEnabled(true) ;
						}
						else {
							frame.displayError("Username " + username + " is already registered.") ;
						}
					}
					else {
						frame.displayError("Please enter the username and password for the user to be added.") ;
					}
				}
				else if(button.equals(saveButton)) {
					if(userList.getSelectedIndex() != -1 && isEditable) {
						Account account = AccountManager.getAccountObj(userList.getSelectedValue()) ;
						account.setFirstName(firstnameField.getText()) ;
						account.setLastName(lastnameField.getText()) ;
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
						if(! AccountManager.getAccountObj(userList.getSelectedValue()).equals(slap.getCurrentUser())) {
							setInfoEnabled(false) ;
							setUserInfo(null) ;
							AccountManager.getAccountMap().removeAccount(userList.getSelectedValue()) ;
							frame.coursesRefresh() ;
							refresh() ;
						}
						else {
							frame.displayError("Unable to remove an account that is logged in.") ;
						}
					}
				}
			}
		}
		ButtonListener buttonListener = new ButtonListener() ;
 		addButton.addActionListener(buttonListener) ;
 		saveButton.addActionListener(buttonListener) ;
 		editButton.addActionListener(buttonListener) ;
 		removeButton.addActionListener(buttonListener) ;
 		buttonPanel.add(usernamePanel) ;
 		buttonPanel.add(passwordPanel) ;
 		buttonPanel.add(roleBox) ;
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
	
	private void setUserInfo(Account account) {
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
		AccountMap map = AccountManager.getAccountMap() ;
		if(map != null) {
			listModel.removeAllElements() ;
			for(Account account : map.getAccounts()) { // Need array returned for accounts
				listModel.addElement(account.getUsername()) ;
			}
		}
		else {
			listModel.removeAllElements() ;
		}
		userList.validate() ;
		frame.refresh() ;
	}
}
