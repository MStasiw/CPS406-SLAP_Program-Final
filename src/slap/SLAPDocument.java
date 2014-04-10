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
	
	public SLAPDocument(String title, String info) {
		time = Calendar.getInstance().getTime() ;
		this.title = title ;
		this.info = info ;
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
	
	@Override
	public String toString() {
		return title ;
	}
}
