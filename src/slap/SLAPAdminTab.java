package slap;

import java.awt.BorderLayout;

import javax.swing.* ;

public class SLAPAdminTab extends JPanel {
	
	private JTabbedPane tabbedPane ;
	private SLAPAdminUserTab userTab ;
	private SLAPAdminCourseTab courseTab ;
	
	public SLAPAdminTab(SLAP slap, SLAPFrame frame) {
		setLayout(new BorderLayout()) ;
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT) ;
		userTab = new SLAPAdminUserTab(slap, frame) ;
		courseTab = new SLAPAdminCourseTab(slap, frame) ;
		tabbedPane.addTab("User Actions", userTab) ;
		tabbedPane.addTab("Course Actions", courseTab) ;
		add(tabbedPane, BorderLayout.CENTER) ;
	}
	
	protected void refresh() {
		userTab.refresh() ;
		courseTab.refresh() ;
	}
}
