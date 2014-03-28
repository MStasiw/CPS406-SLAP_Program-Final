package slap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.* ;

@SuppressWarnings("serial")
public class SLAPTab extends JPanel {
	
	private JScrollPane scrollPane ;
	private ScrollPanel panel ;
	
	private ArrayList<SLAPTabItem> items ;
	
	public SLAPTab() {
		items = new ArrayList<SLAPTabItem>() ;
		setLayout(new BorderLayout()) ;
		panel = new ScrollPanel() ;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)) ;
		/*scrollPane = new JScrollPane(panel, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) ;
		add(scrollPane, BorderLayout.CENTER) ;*/
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

	class ScrollPanel extends JPanel implements Scrollable {
		
		public ScrollPanel() {
			super() ;
		}	
		
		@Override
		public Dimension getPreferredScrollableViewportSize() {
			return getPreferredSize() ;
		}

		@Override
		public int getScrollableUnitIncrement(Rectangle visibleRect,
				int orientation, int direction) {
			return 10 ;
		}

		@Override
		public int getScrollableBlockIncrement(Rectangle visibleRect,
				int orientation, int direction) {
			return 20 ;
		}

		@Override
		public boolean getScrollableTracksViewportWidth() {
			return true ;
		}

		@Override
		public boolean getScrollableTracksViewportHeight() {
			return false ;
		}
	}
}
