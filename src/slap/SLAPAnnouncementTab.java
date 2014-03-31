package slap;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.* ;

@SuppressWarnings("serial")
public class SLAPAnnouncementTab extends JPanel {
	
	private SLAP slap ;
	private SLAPFrame frame ;
	
	private JScrollPane scrollPane ;
	private VerticalScrollPanel panel ;
	
	private JButton addButton ;
	private final Font ADD_BUTTON_FONT = new Font("Helvetica", Font.BOLD, 22) ;
	
	private ArrayList<SLAPAnnouncementTabItem> items ;
	
	/*
	 * Make a new tab
	 */
	public SLAPAnnouncementTab(SLAP slap, SLAPFrame frame) {
		this.slap = slap ;
		this.frame = frame ;
		initialize() ;
	}
	
	private void initialize() {
		items = new ArrayList<SLAPAnnouncementTabItem>() ;
		setLayout(new BorderLayout()) ;
		setupAddButton(this) ;
		panel = new VerticalScrollPanel() ;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)) ;
		scrollPane = new JScrollPane() ;
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) ;
		scrollPane.setViewportView(panel);
		add(scrollPane) ;
	}
	
	private void setupAddButton(JPanel panel) {
		addButton = new JButton("+") ;
		addButton.setFont(ADD_BUTTON_FONT) ;
		class AddListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(slap.getCurrentCourse() != null) {
					Announcement temp = new Announcement("title", "content") ;
					slap.getCurrentCourse().getAnnouncements().add(temp.getID(), temp) ;
					refresh() ;
				}
			}	
		}
		addButton.addActionListener(new AddListener()) ;
		panel.add(addButton, BorderLayout.NORTH) ;
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
			for(Managable m : course.getAnnouncementsArray()) {
				Announcement a = (Announcement) m ;
				items.add(new SLAPAnnouncementTabItem(slap, this, a)) ;
			}				
		}
		//Trying instead to add them in reverse
		/*
		for(SLAPAnnouncementTabItem item : items) {
			panel.add(item) ;
		}*/
		//Add items in reverse
		for(int i = items.size() -1 ; i >= 0 ; i--) {
			panel.add(items.get(i)) ;
		}
		//
		//panel.validate() ;
		frame.refresh() ;
	}
}
