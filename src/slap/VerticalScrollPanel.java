package slap;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.* ;

@SuppressWarnings("serial")
class VerticalScrollPanel extends JPanel implements Scrollable {
	
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize() ;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 1 ; //The default value
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 1 ; //The default value
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
