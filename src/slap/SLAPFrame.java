package slap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.* ;

@SuppressWarnings("serial")
public class SLAPFrame extends JFrame {
	
	private final int FRAME_WIDTH = 900 ;
    private final int FRAME_HEIGHT = 600 ;
    private final int MIN_FRAME_WIDTH = 500 ;
    private final int MIN_FRAME_HEIGHT = 400 ;
    
    private ImageIcon coursesIcon ;
    private final String coursesIconPath = "/resources/courses.png" ;
    private final int MENU_ICON_SIZE = 20 ;   
    
    private JTabbedPane tabbedPane ;
    
    private JMenuBar menuBar ;
    private JMenu coursesMenu ;
    private JButton logoutButton ;
    	
	public SLAPFrame() {
		initialize() ;
		setupMenuBar() ;
		setupTabbedPane() ;
		
		setVisible(true) ;
	}	
	
	private void initialize() {
		setLookAndFeel() ;
		setLayout(new BorderLayout()) ;
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
	
	private void setupTabbedPane() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT) ;
		add(tabbedPane, BorderLayout.CENTER) ;
		SLAPLoginPanel slp = new SLAPLoginPanel() ;
		tabbedPane.addTab("Login", slp);
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
				//Logout
			}		
		}
		logoutButton.addActionListener(new LogoutListener());
		jmb.add(logoutButton) ;
	}
	
}
