package slap;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.* ;

@SuppressWarnings("serial")
public class SLAPFrame extends JFrame implements KeyListener {
	
	private final int FRAME_WIDTH = 900 ;
    private final int FRAME_HEIGHT = 600 ;
    private final int MIN_FRAME_WIDTH = 500 ;
    private final int MIN_FRAME_HEIGHT = 400 ;
    
    private final String LOGIN_CARD_ID = "login" ;
    private final String TABS_CARD_ID = "tabs" ;  
    
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
    	
	public SLAPFrame() {
		initialize() ;		
		setupMenuBar() ;
		setupLoginPanel() ;
		setupTabbedPane() ;
		logout() ;
		setVisible(true) ;
	}	
	
	private void initialize() {
		setLookAndFeel() ;
		setupLayout() ;
		setTitle("SLAP") ;
        setSize(FRAME_WIDTH, FRAME_HEIGHT) ;
        setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT)) ;
        setResizable(true) ;    
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;        
	}
	
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel") ;
		}
		catch(Exception e) {}
	}
	
	private void setupLayout() {
		cardLayout  = new CardLayout() ;
		cards = new JPanel(cardLayout) ;
		add(cards) ;
	}
	
	private void setupLoginPanel() {
		slp = new SLAPLoginPanel(this) ;
		cards.add(slp, LOGIN_CARD_ID);
	}
	
	private void setupTabbedPane() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT) ;
		cards.add(tabbedPane, TABS_CARD_ID) ;
		tabbedPane.addTab("Description", new SLAPTab()) ;
		//
		SLAPTab stab = new SLAPTab() ;
		tabbedPane.addTab("Announcements", stab) ;
		stab.addItem(new SLAPTabItem()) ;
		stab.addItem(new SLAPTabItem()) ;		
		stab.addItem(new SLAPTabItem()) ;		
		stab.addItem(new SLAPTabItem()) ;	
		stab.addItem(new SLAPTabItem()) ;
		stab.addItem(new SLAPTabItem()) ;		
		stab.addItem(new SLAPTabItem()) ;		
		stab.addItem(new SLAPTabItem()) ;		
		//
		tabbedPane.addTab("Documents", new SLAPTab()) ;
		tabbedPane.addTab("Assignments", new SLAPTab()) ;
		tabbedPane.addTab("Grades", new SLAPTab()) ;
	}
	
	private void setupMenuBar() {
		menuBar = new JMenuBar() ;
		setupCoursesMenu(menuBar) ;
		menuBar.add(Box.createHorizontalGlue()) ;
		setupLogoutButton(menuBar) ;
		setJMenuBar(menuBar) ;
	}
	
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
	
	public void populateCourseMenu(ArrayList<String> codes)
    {
        removeAllCourseMenuItems() ;
        class CourseMenuItemListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
				JMenuItem courseMenuItem = (JMenuItem) event.getSource() ;
                //Get course based on courseMenuItem text
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
	
	public void removeAllCourseMenuItems()
    {
        coursesMenu.setEnabled(false) ;
        coursesMenu.removeAll() ;
    }
	
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
	
	protected void login() {
		logoutButton.setEnabled(true) ;
		logoutButton.setVisible(true);
		//set information
		populateCourseMenu(new ArrayList<String>()) ;
		slp.clearText();
		cardLayout.show(cards, TABS_CARD_ID) ;		
	}
	
	protected void logout() {
		logoutButton.setEnabled(false) ;
		logoutButton.setVisible(false);
		//clear information
		removeAllCourseMenuItems() ;
		//
		cardLayout.show(cards, LOGIN_CARD_ID) ;
	}
	
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
