package slap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.* ;

@SuppressWarnings("serial")
public class SLAPTab extends JPanel {
	
	private JScrollPane scrollPane ;
	private VerticalScrollPanel panel ;
	
	private ArrayList<SLAPTabItem> items ;
	
	public SLAPTab() {
		items = new ArrayList<SLAPTabItem>() ;
		setLayout(new BorderLayout()) ;
		panel = new VerticalScrollPanel() ;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)) ;
		scrollPane = new JScrollPane() ;
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) ;
		scrollPane.setViewportView(panel);
		add(scrollPane) ;
	}
	
	public void addItem(SLAPTabItem item) {
		items.add(item) ;
		refresh() ;
	}
	
	private void refresh() {
		panel.removeAll() ;
		for(SLAPTabItem item : items) {
			panel.add(item) ;
		}
	}
}
