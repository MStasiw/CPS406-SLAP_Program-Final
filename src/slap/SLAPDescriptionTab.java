package slap;

import java.awt.BorderLayout;

import javax.swing.* ;

@SuppressWarnings("serial")
public class SLAPDescriptionTab extends JPanel {

	@SuppressWarnings("unused")
	private SLAP slap ;
	@SuppressWarnings("unused")
	private SLAPFrame frame ;
	
	private JScrollPane scrollPane ;
	private VerticalScrollPanel panel ;
	
	private SLAPDescriptionTabItem sdti ;
	
	public SLAPDescriptionTab(SLAP slap, SLAPFrame frame) {
		this.slap = slap ;
		this.frame = frame ;
		sdti = new SLAPDescriptionTabItem(slap, this) ;
		initialize() ;
		refresh() ;
	}
	
	private void initialize() {
		setLayout(new BorderLayout()) ;
		panel = new VerticalScrollPanel() ;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)) ;
		scrollPane = new JScrollPane() ;
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) ;
		scrollPane.setViewportView(panel);
		panel.add(sdti) ;
		add(scrollPane) ;
	}
	
	protected void refresh() {
		sdti.refresh() ;
	}
}
