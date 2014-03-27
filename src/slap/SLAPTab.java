package slap;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.* ;

@SuppressWarnings("serial")
public class SLAPTab extends JPanel {
	
	private JScrollPane scrollPane ;
	private JPanel panel ;
	
	private ArrayList<SLAPTabItem> items ;
	
	public SLAPTab() {
		items = new ArrayList<SLAPTabItem>() ;
		setLayout(new BorderLayout()) ;
		panel = new JPanel() ;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)) ;
		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) ;
		add(scrollPane, BorderLayout.CENTER) ;
		//add(panel) ;
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
