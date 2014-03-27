package slap;

import java.awt.Dimension;

import javax.swing.* ;

public class SLAPFrame extends JFrame {
	
	private final int FRAME_WIDTH = 900 ;
    private final int FRAME_HEIGHT = 600 ;
    private final int MIN_FRAME_WIDTH = 500 ;
    private final int MIN_FRAME_HEIGHT = 300 ;
    
    private JTabbedPane tabbedPane ;
    
    private JMenuBar menuBar ;
    private JMenu coursesMenu ;
    //private JMenu logoutMenu ;
    private JButton logoutButton ;
	
	public SLAPFrame() {
		initialize() ;
		setupMenuBar() ;
		setupTabbedPane() ;
		
		setVisible(true) ;
	}	
	
	private void initialize() {
		//setLookAndFeel() ;
		setDefaultLookAndFeelDecorated(true) ;
		setTitle("SLAP") ;
        setSize(FRAME_WIDTH, FRAME_HEIGHT) ;
        setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT)) ;
        setResizable(true) ;       
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;        
	}
	
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.basic.BasicLookAndFeel") ;
		}
		catch(Exception e) {
			//
		}
	}
	
	private void setupTabbedPane() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT) ;
		add(tabbedPane) ;
		tabbedPane.addTab("Text", new JLabel("Label")) ;
	}
	
	private void setupMenuBar() {
		menuBar = new JMenuBar() ;
		setupCoursesMenu(menuBar) ;
		menuBar.add(Box.createHorizontalGlue()) ;
		//setupLogoutMenu(menuBar) ;
		setupLogoutButton(menuBar) ;
		setJMenuBar(menuBar) ;
	}
	
	private void setupCoursesMenu(JMenuBar jmb) {
		coursesMenu = new JMenu("Courses") ;
		jmb.add(coursesMenu) ;
	}
	
	/*private void setupLogoutMenu(JMenuBar jmb) {
		logoutMenu = new JMenu("Logout") ;
		jmb.add(logoutMenu) ;
	}*/
	
	private void setupLogoutButton(JMenuBar jmb) {
		logoutButton = new JButton("Logout") ;
		jmb.add(logoutButton) ;
	}
	
}
