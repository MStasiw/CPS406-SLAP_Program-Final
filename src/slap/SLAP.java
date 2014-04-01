package slap;

public class SLAP {
	
	@SuppressWarnings("unused")
	
	private DefaultData dd ;
	private SLAPFrame frame ;
	private Account currentUser = null ;
	private Course currentCourse = null ;
	private Manager<String, Course> courseManager ;
	
	protected final String DIR_NAME = "res" ;
	protected final String CM_FILE_NAME = "cm" ;
	
	/**
	 * Makes a new SLAP
	 */
	@SuppressWarnings("unchecked")
	public SLAP() {
		
		dd = new DefaultData(this) ;
		courseManager = (Manager<String, Course>) ObjectIO.objectIn(DIR_NAME, CM_FILE_NAME) ;
		if(courseManager == null) {
			dd.loadData() ;
		}
        
		/*
		 * FOR USE IN DEVELOPMENT/TESTING ONLY
		 * Create user accounts 
		 */
		AccountManager.createAccount("Student1", "Test", "student", "student", Role.student);
		AccountManager.createAccount("Instructor1", "Test", "instructor", "instructor", Role.instructor);
		AccountManager.createAccount("Administrator1", "Test", "admin", "admin", Role.administrator);
		AccountManager.createAccount("xavier", "xats", "x", "x", Role.administrator) ;
		AccountManager.createAccount("yolanda", "yantee", "y", "y", Role.administrator) ;
		AccountManager.createAccount("zoolander", "zebadia", "z", "z", Role.administrator) ;
		
		frame = new SLAPFrame(this) ;
	}
	
	/**
	 * Set the current user
	 * @param user the new current user
	 */
	protected void setCurrentUser(Account user) {
		currentUser = user ;
	}
	
	/**
	 * Get the current user
	 * @return the current user
	 */
	protected Account getCurrentUser() {
		return currentUser ;
	}
	
	/**
	 * Sets the course manager
	 * @param cm the course manager to set it to
	 */
	protected void setCourseManager(Manager<String, Course> cm) {
		courseManager = cm ;
	}
	
	/**
	 * Get the course manager
	 * @return the course manager
	 */
	protected Manager<String, Course> getCourseManager() {
		return courseManager ;
	}
	
	/**
	 * Set the current course
	 * @param course the new current course
	 */
	protected void setCurrentCourse(Course course) {
		currentCourse = course ;
	}
	
	/**
	 * Get the current course
	 * @return the current course
	 */
	protected Course getCurrentCourse() {
		return currentCourse ;
	}
	
	protected SLAPFrame getFrame() {
		return frame ;
	}
}
