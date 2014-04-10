package slap;

import java.awt.BorderLayout;

import javax.swing.* ;

public class SLAPAdminTab extends JPanel {
	
	private SLAP slap ;
	
	private ImageIcon logoIcon ;
	private final String LOGO_ICON_PATH = "/resources/SLAP_LOGO_II.png" ;
	private final int LOGO_ICON_WIDTH = 128 ;
	private final int LOGO_ICON_HEIGHT = 64 ;
	
	private JTabbedPane tabbedPane ;
	private SLAPAdminUserTab userTab ;
	private SLAPAdminCourseTab courseTab ;
	
	public SLAPAdminTab(SLAP slap, SLAPFrame frame) {
		this.slap = slap ;
		ImageIcon logo = getLogo() ;
		setLayout(new BorderLayout()) ;
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT) ;
		userTab = new SLAPAdminUserTab(slap, frame, logo) ;
		courseTab = new SLAPAdminCourseTab(slap, frame, logo) ;
		tabbedPane.addTab("User Actions", userTab) ;
		tabbedPane.addTab("Course Actions", courseTab) ;
		add(tabbedPane, BorderLayout.CENTER) ;
	}
	
	private ImageIcon getLogo() {
		try{
			logoIcon = new ImageIcon(getClass().getResource(LOGO_ICON_PATH)) ;
			logoIcon = new ImageIcon(
					logoIcon.getImage().getScaledInstance(
							LOGO_ICON_WIDTH, 
							LOGO_ICON_HEIGHT, 
							java.awt.Image.SCALE_SMOOTH));
			return logoIcon ;
		}
		catch(Exception e) {
			return null ;
		}
	}
	
	protected void refresh() {
		if(slap.getCurrentUser() == null) {
			tabbedPane.setSelectedComponent(userTab) ;
		}
		userTab.refresh() ;
		courseTab.refresh() ;
	}
}
