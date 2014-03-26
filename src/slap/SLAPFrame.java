package slap;

import java.awt.Dimension;

import javax.swing.* ;

public class SLAPFrame extends JFrame {
	
	private final int FRAME_WIDTH = 900 ;
    private final int FRAME_HEIGHT = 600 ;
    private final int MIN_FRAME_WIDTH = 500 ;
    private final int MIN_FRAME_HEIGHT = 300 ;
    
    private JMenuBar menuBar ;
    private JMenu accountMenu ;
    private JMenu coursesMenu ;
    private JMenuItem signinMenuItem ;
    private JMenuItem signoutMenuItem ;
	
	public SLAPFrame() {
		initialize() ;
		setupMenuBar() ;
		
		setVisible(true) ;
	}	
	
	private void initialize() {
		setTitle("SLAP") ;
        setSize(FRAME_WIDTH, FRAME_HEIGHT) ;
        setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT)) ;
        setResizable(true) ;
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;        
	}
	
	private void setupMenuBar() {
		menuBar = new JMenuBar() ;
		setupAccountMenu(menuBar) ;
		setupCoursesMenu(menuBar) ;
		setJMenuBar(menuBar) ;
	}
	
	private void setupAccountMenu(JMenuBar jmb) {
		accountMenu = new JMenu("Account") ;
		setupSigninMenuItem(accountMenu) ;
		setupSignoutMenuItem(accountMenu) ;
		jmb.add(accountMenu) ;
	}
	
	private void setupCoursesMenu(JMenuBar jmb) {
		coursesMenu = new JMenu("Courses") ;
		jmb.add(coursesMenu) ;
	}
	
	private void setupSigninMenuItem(JMenu jm) {
		signinMenuItem = new JMenuItem("Sign in") ;
		jm.add(signinMenuItem) ;
	}
	
	private void setupSignoutMenuItem(JMenu jm) {
		signoutMenuItem = new JMenuItem("Sign out") ;
		jm.add(signoutMenuItem) ;
	}
}
