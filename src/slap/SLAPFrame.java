package slap;

import java.awt.Dimension;

import javax.swing.* ;

public class SLAPFrame extends JFrame {
	
	private final int FRAME_WIDTH = 900 ;
    private final int FRAME_HEIGHT = 600 ;
    private final int MIN_FRAME_WIDTH = 500 ;
    private final int MIN_FRAME_HEIGHT = 300 ;
	
	public SLAPFrame() {
		initialize() ;
		
		setVisible(true) ;
	}	
	
	private void initialize() {
		setTitle("SLAP") ;
        setSize(FRAME_WIDTH, FRAME_HEIGHT) ;
        setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT)) ;
        setResizable(true) ;
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;        
	}
}
