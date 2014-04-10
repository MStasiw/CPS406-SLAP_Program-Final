package slap;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class SLAPDocument implements Managable, Serializable {

	private Date time ;
	private String id ;
	private String title ;
	private String info ;
	private String filename ;
	private File file ;
	private Double grade;
	
	public SLAPDocument(String title, String info) {
		time = Calendar.getInstance().getTime() ;
		this.title = title ;
		this.info = info ;
		grade = null ;
		id = getTitle() + " " + Long.toString(time.getTime()) ;		
	}
	
	public String getID() {
		return id ;
	}
	
	public void setTitle(String title) {
		this.title = title ;
	}
	
	public String getTitle() {
		return title ;
	}
	
	public void setInfo(String info) {
		this.info = info ;
	}
	
	public String getInfo() {
		return info ;
	}
	
	public String getFilename() {
		return filename ;
	}
	
	public File getFile() {
		return file ;
	}
	
	public Double getGrade() {
		return grade;
	}
	
	public boolean setGrade(Double mark) {
		if(mark != null && mark.doubleValue() >= 0 && mark.doubleValue() <= 100) {
			grade = mark ;
			return true ;
		}
		return false ;
	}
	
	@Override
	public String toString() {
		return title ;
	}
}
