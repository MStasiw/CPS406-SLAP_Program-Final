package slap;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.* ;

public class SLAPAnnouncementTab extends JPanel {
	
private SLAP slap ;
	
	private JScrollPane scrollPane ;
	private VerticalScrollPanel panel ;
	
	private ArrayList<SLAPAnnouncementTabItem> items ;
	
	/*
	 * Make a new tab
	 */
	public SLAPAnnouncementTab(SLAP slap) {
		this.slap = slap ;
		items = new ArrayList<SLAPAnnouncementTabItem>() ;
		setLayout(new BorderLayout()) ;
		panel = new VerticalScrollPanel() ;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)) ;
		scrollPane = new JScrollPane() ;
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) ;
		scrollPane.setViewportView(panel);
		add(scrollPane) ;
	}
	
	/**
	 * Add an item to the tab
	 * @param item the component to added to the tab
	 */
	public void addItem(SLAPAnnouncementTabItem item) {
		items.add(item) ;
		refresh() ;
	}
	
	/**
	 * Refresh the items in the tab
	 */
	protected void refresh() {
		panel.removeAll() ;
		items.clear() ;
		Course course = slap.getCurrentCourse() ;
		if(course != null) {
			for(Managable m : course.getAnnouncements()) {
				Announcement a = (Announcement) m ;
				items.add(new SLAPAnnouncementTabItem(a)) ;
			}				
		}
		for(SLAPAnnouncementTabItem item : items) {
			panel.add(item) ;
		}
	}
}
