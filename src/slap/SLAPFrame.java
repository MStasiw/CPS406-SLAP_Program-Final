package slap;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.* ;

@SuppressWarnings("serial")
public class SLAPFrame extends JFrame implements KeyListener {
	
	private SLAP slap ;
		
	private final int FRAME_WIDTH = 900 ;
    private final int FRAME_HEIGHT = 600 ;
    private final int MIN_FRAME_WIDTH = 600 ;
    private final int MIN_FRAME_HEIGHT = 500 ;
    
    private final String LOGIN_CARD_ID = "login" ;
    private final String TABS_CARD_ID = "tabs" ;  
    
    private ImageIcon logoIcon ;
    protected final String logoIconPath = "/resources/SLAP_LOGO.png" ;
    private final int LOGO_ICON_SIZE = 64 ;
    
    private JLabel userLabel ;
    private JLabel courseLabel ;
    private ImageIcon coursesIcon ;
    private final String coursesIconPath = "/resources/courses.png" ;
    private final int MENU_ICON_SIZE = 20 ;  
    
    private JPanel cards ;
    private CardLayout cardLayout ;
    
    private SLAPLoginPanel slp ;
    private JTabbedPane tabbedPane ;
    
    private JMenuBar menuBar ;
    private JMenu coursesMenu ;
    private JButton logoutButton ;
    
    //Tab panels
    private SLAPDescriptionTab descriptionTab ;
    private SLAPAnnouncementTab announcementTab ;
    private SLAPAssignmentTab assignmentTab;
    private Email emailTab ;
    	
    /**
     * Makes a new frame
     * @param slap the slap from which to control the frame
     */
	public SLAPFrame(SLAP slap) {
		this.slap = slap ;
		initialize() ;		
		setupMenuBar() ;
		setupLoginPanel() ;
		setupTabbedPane() ;
		logout() ;
		setVisible(true) ;
	}	
	
	/**
	 * Runs the setup methods and instantiates basic values
	 */
	private void initialize() {
		//setLookAndFeel() ;
		setLogo() ;
		setupLayout() ;
		setTitle("SLAP") ;
        setSize(FRAME_WIDTH, FRAME_HEIGHT) ;
        setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT)) ;
        setResizable(true) ;    
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;        
	}
	
	/**
	 * Sets the logo
	 */
	private void setLogo() {
		try{
			logoIcon = new ImageIcon(getClass().getResource(logoIconPath)) ;
			logoIcon = new ImageIcon(
					logoIcon.getImage().getScaledInstance(
							LOGO_ICON_SIZE, 
							LOGO_ICON_SIZE, 
							java.awt.Image.SCALE_SMOOTH));
			setIconImage(logoIcon.getImage()) ;
		}
		catch(Exception e) {
			//
		}
	}
	
	/**
	 * Sets the look and feel
	 * Note, do not use on windows, there are known bugs!
	 */
	@SuppressWarnings("unused")
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel") ;
		}
		catch(Exception e) {}
	}
	
	/**
	 * Setup a card layout to switch panels
	 */
	private void setupLayout() {
		cardLayout  = new CardLayout() ;
		cards = new JPanel(cardLayout) ;
		add(cards) ;
	}
	
	/**
	 * Setup the login panel
	 */
	private void setupLoginPanel() {
		slp = new SLAPLoginPanel(this, slap) ;
		cards.add(slp, LOGIN_CARD_ID);
	}
	
	/**
	 * Setup the tabbed pane
	 */
	private void setupTabbedPane() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT) ;
		cards.add(tabbedPane, TABS_CARD_ID) ;
		//
		descriptionTab = new SLAPDescriptionTab(slap, this) ;
		tabbedPane.addTab("Description", descriptionTab) ;
		//
		announcementTab = new SLAPAnnouncementTab(slap, this) ;
		tabbedPane.addTab("Announcements", announcementTab) ;
		//announcementTab.addItem(new SLAPTabItem()) ;
		//
		tabbedPane.addTab("Documents", new SLAPTab(slap)) ;
		
		assignmentTab = new SLAPAssignmentTab(this, slap);
		tabbedPane.addTab("Assignments", assignmentTab) ;
		tabbedPane.addTab("Submit Assignments", new SubmitAssignUI2()) ;
		
		tabbedPane.addTab("Grades", new SLAPTab(slap)) ;
		
		emailTab = new Email(slap) ;
		tabbedPane.addTab("Email", emailTab) ;
	}
	
	/**
	 * Setup the menubar
	 */
	private void setupMenuBar() {
		menuBar = new JMenuBar() ;
		userLabel = new JLabel() ;
		courseLabel = new JLabel() ;
		setupCoursesMenu(menuBar) ;
		menuBar.add(Box.createHorizontalStrut(10)) ;
		menuBar.add(courseLabel) ;
		menuBar.add(Box.createHorizontalGlue()) ;
		menuBar.add(userLabel) ;
		menuBar.add(Box.createHorizontalStrut(10)) ;
		setupLogoutButton(menuBar) ;
		setJMenuBar(menuBar) ;
	}
	
	/**
	 * Setup the course menu
	 * @param jmb the menubar to which the menu is to be added
	 */
	private void setupCoursesMenu(JMenuBar jmb) {
		coursesMenu = new JMenu("Courses") ;
		try {
			coursesIcon = new ImageIcon(getClass().getResource(coursesIconPath)) ;
			coursesIcon = new ImageIcon(coursesIcon.getImage().getScaledInstance(MENU_ICON_SIZE, MENU_ICON_SIZE, java.awt.Image.SCALE_SMOOTH));
			coursesMenu.setIcon(coursesIcon) ;
		}
		catch(Exception e) {}
		jmb.add(coursesMenu) ;
	}
	
	/**
	 * populate the course menu with course codes
	 * @param codes the course codes to display in the menu
	 */
	public void populateCourseMenu(String[] codes)
    {
        removeAllCourseMenuItems() ;
        class CourseMenuItemListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
				JMenuItem courseMenuItem = (JMenuItem) event.getSource() ;
                String courseCode = courseMenuItem.getText() ;
                Course course = (Course) slap.getCourseManager().get(courseCode) ;
                slap.setCurrentCourse(course) ;
                courseLabel.setText(courseCode) ;
                //Put tab refreshes that are course dependent here
                descriptionTab.refresh() ;
                announcementTab.refresh() ;
                emailTab.refresh() ;
                //
            }
        }
        CourseMenuItemListener listener = new CourseMenuItemListener() ;
        for(String code : codes)
        {
            JMenuItem courseMenuItem = new JMenuItem(code) ;
            courseMenuItem.addActionListener(listener) ;
            coursesMenu.add(courseMenuItem) ;
            coursesMenu.setEnabled(false) ;
        }
        coursesMenu.setEnabled(true) ;
    }
	
	/**
	 * Removes all course codes from the course menu
	 */
	public void removeAllCourseMenuItems()
    {
        coursesMenu.setEnabled(false) ;
        coursesMenu.removeAll() ;
    }
	
	/**
	 * Setup the logout button
	 * @param jmb the menubar to which the logout button is added
	 */
	private void setupLogoutButton(JMenuBar jmb) {
		logoutButton = new JButton("Logout") ;
		class LogoutListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				logout() ;
			}		
		}
		logoutButton.addActionListener(new LogoutListener());
		class EnterListener implements KeyListener {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				//logout() ;
			}

			@Override
			public void keyReleased(KeyEvent e) {}
		}
		logoutButton.addKeyListener(new EnterListener()) ;
		jmb.add(logoutButton) ;
	}
	
	/**
	 * Run the login setup
	 */
	protected void login() {
		logoutButton.setEnabled(true) ;
		logoutButton.setVisible(true);
		//set information
		userLabel.setText(slap.getCurrentUser().getUsername()) ;
		userLabel.setVisible(true) ;
		courseLabel.setVisible(true) ;
		//TESTING ONLY
		@SuppressWarnings("rawtypes")
		Comparable[] courseCodes =  slap.getCourseManager().getIDArray() ;
		String[] codes = new String[courseCodes.length] ;
		int count = 0 ;
		for(@SuppressWarnings("rawtypes") Comparable comp : courseCodes) {
			codes[count++] = (String) comp ;
		}
		populateCourseMenu(codes) ;
		//Put refresh call to tabs that are user dependent here
		emailTab.refresh() ;
		//
		slp.clearText();
		cardLayout.show(cards, TABS_CARD_ID) ;
	}
	
	/**
	 * Run the logout takedown
	 */
	protected void logout() {
		logoutButton.setEnabled(false) ;
		logoutButton.setVisible(false);
		//clear information
		slap.setCurrentUser(null) ;
		userLabel.setText("") ;
		userLabel.setVisible(false) ;
		courseLabel.setText("") ;
		courseLabel.setVisible(false) ;
		removeAllCourseMenuItems() ;
		slap.setCurrentCourse(null) ;
		//
		descriptionTab.refresh() ;
		announcementTab.refresh() ;
		emailTab.refresh() ;
		//
		tabbedPane.setSelectedComponent(descriptionTab);
		//
		cardLayout.show(cards, LOGIN_CARD_ID) ;
	}
	
	/**
	 * Refresh the information
	 */
	protected void refresh() {
		//announcementTab.refresh(slap.getCurrentCourse()) ;
		tabbedPane.validate() ;
	}
	
	/**
	 * Display an error message
	 * @param errorMessage the message to display
	 */
	protected void displayError(String errorMessage)
    {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE) ;
    }

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode() ;
		System.out.println(code) ;
		if(code == KeyEvent.VK_ESCAPE) {
			System.exit(0) ;
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
