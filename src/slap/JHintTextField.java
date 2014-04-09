package slap;

import javax.swing.* ;

import java.awt.Color;
import java.awt.event.* ;

/**
 * A custom JTextField which adds hint functionality
 */
@SuppressWarnings("serial")
public class JHintTextField extends JTextField implements FocusListener
{	
	private final Color normalColour = Color.BLACK ;
	private final Color hintColour = Color.GRAY ;
	
	private String hint ;
    private boolean hintEnabled ;

    public JHintTextField(String hint)
    {
        super() ;     
        this.hint = hint ;
        initialize() ;
    }
    
    public JHintTextField(String hint, int columns)
    {
        super(columns) ;
        this.hint = hint ;
        initialize() ;
    }
    
    private void initialize() {
    	setForeground(hintColour) ;
        setupListeners() ;
        enableHint() ;
    }
    
    private void setupListeners()
    {
        addFocusListener(this) ;
    }

    public void setHint(String hint)
    {
        this.hint = hint ;
    }
    
    public String getHint()
    {
        return hint ;
    }
    
    private void enableHint()
    {
        hintEnabled = true ;
        setForeground(hintColour) ;
        super.setText(hint) ;
    }
    
    private void disableHint()
    {
        hintEnabled = false ;
        setForeground(normalColour) ;
        super.setText("") ;
    }
    
    public void clearText()
    {
        enableHint() ;
    }
    
    public void setText(String text) {
    	if(hintEnabled) {
    		disableHint() ;
    	}
    	super.setText(text) ;
    }
    
    public String getText()
    {
        if(hintEnabled)
        {
            return "" ;
        }
        return super.getText() ;
    }
    
    //Listeners
    
    public void focusGained(FocusEvent event)
    {
        if(hintEnabled)
        {
            disableHint() ;
        }
    }
    
    public void focusLost(FocusEvent event)
    {
        if(getText().equals(""))
        {
            enableHint() ;
        }
    }
}