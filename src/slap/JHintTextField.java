package slap;

import javax.swing.* ;

import java.awt.event.* ;

/**
 * A custom JTextField which adds hint functionality
 */
@SuppressWarnings("serial")
public class JHintTextField extends JTextField implements FocusListener
{	
	private String hint ;
    private boolean hintEnabled ;

    public JHintTextField(String hint)
    {
        super() ;
        setupListeners() ;
        this.hint = hint ;
        enableHint() ;
    }
    
    public JHintTextField(String hint, int columns)
    {
        super(columns) ;
        setupListeners() ;
        this.hint = hint ;
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
        setText(hint) ;
    }
    
    private void disableHint()
    {
        hintEnabled = false ;
        setText("") ;
    }
    
    public void clearText()
    {
        enableHint() ;
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