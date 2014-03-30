package slap;
import java.util.ArrayList ;
import java.io.Serializable ;

/**
 * A course with announcements
 */
public class Course implements Managable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String code ;
    private String name ;
    private String professor ;
    private String description ;

    public ArrayList<String> students ;
    public Manager<String, Announcement> announcements ;

    public Course()
    {
        this.code = "code" ;
        this.name = "name" ;
        this.professor = "professor" ;
        this.description = "description" ;
        announcements = new Manager<String, Announcement>() ;
        //students = new ArrayList<String>() ; 
    }

    public Course(String code, String name, String professor, String description)
    {
        this.code = code ;
        this.name = name ;
        this.professor = professor ;
        this.description = description ;   
        announcements = new Manager<String, Announcement>() ;
        //students = new ArrayList<String>() ;
    }
    
    public Course(String code, String name, String professor, String description, Manager<String, Announcement> announcements)
    {
        this.code = code ;
        this.name = name ;
        this.professor = professor ;
        this.description = description ;   
        this.announcements = announcements ;
        //students = new ArrayList<String>() ;
    }
    
    public String getID()
    {
        return getCode() ;
    }
    
    public String getCode()
    {
        return code ;
    }

    public String getName()
    {
        return name ;
    }

    public String getProfessor()
    {
        return professor ;
    }

    public String getDescription()
    {
        return description ;
    }
    
    public Managable[] getAnnouncements()
    {
        return announcements.getItemArray() ;
    }
    
    protected void setCode(String code)
    {
        this.code = code ;
    }
    
    protected void setName(String name)
    {
        this.name = name ;
    }

    protected void setProfessor(String professor)
    {
        this.professor = professor ;   
    }

    protected void setDescription(String description)
    {
        this.description = description ;
    }

    @Override
    public String toString()
    {
        return "Name: " + getName() + "\nCode: " + getCode() + "\nProfessor: " + getProfessor() ;
    }
}
