package slap;
import java.util.* ;
import java.io.* ;
import java.text.* ;

/**
 * An announcement with a date
 */
public class Announcement implements Managable, Serializable
{
	private static final long serialVersionUID = 1L;

	private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private String title ;
    private Date datePosted ;
    private String content ;
    
    private String id ;
    
    private ArrayList<File> files ;

    public Announcement(String title, String content)
    {
        datePosted = Calendar.getInstance().getTime() ;     
        this.title = title ;
        this.content = content ;
        id = Long.toString(datePosted.getTime()) + " " + getTitle() ;
        setFiles(new ArrayList<File>()) ;
    }
    
    public Announcement(String title, String content, ArrayList<File> files)
    {
        datePosted = Calendar.getInstance().getTime() ;
        this.title = title ;
        this.content = content ;
        this.setFiles(files) ;
    }
    
    public String getID()
    {
        //return Long.toString(datePosted.getTime()) + " " + getTitle() ; //removed to stop title updates interrupting item removal
    	return id ;
    }
    
    public String getDatePosted()
    {
        return DATE_FORMAT.format(datePosted);
    }
    
    private void setDatePosted()
    {
        datePosted.setTime(Calendar.getInstance().getTime().getTime()) ;
    }
    
    public String getTitle()
    {
        return title ;
    }
    
    public void setTitle(String title)
    {
        setDatePosted() ;
        this.title = title ;
    }
    
    public String getContent()
    {
        return content ;
    }
    
    public void setContent(String content)
    {
        //setDatePosted() ; //Removed for now since it update the key value without updating the key
        this.content = content ;
    }
    
    public ArrayList<File> getFiles() 
    {
		return files;
	}
    
	public void setFiles(ArrayList<File> files) 
	{
		this.files = files;
	}

	@Override
    public String toString()
    {
        String tab = "    " ;
        return "Date posted: " + getDatePosted() + "\n\n" + getTitle() + "\n\n" + tab + getContent() ;
    }
}
