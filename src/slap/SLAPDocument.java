package slap;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class SLAPDocument implements Managable, Serializable {

	private Date time ;
	private String id ;
	private String filename ;
	private File file ;
	
	public SLAPDocument(String filename) {
		time = Calendar.getInstance().getTime() ;
		id = Long.toString(time.getTime()) + " " + getFilename() ;
		file = new File(filename) ;
	}
	
	public String getID() {
		return id ;
	}
	
	public String getFilename() {
		return filename ;
	}
	
	public File getFile() {
		return file ;
	}
}
