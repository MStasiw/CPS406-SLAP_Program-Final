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
	protected final String USER_FILE_NAME = "um" ;
	
	/**
	 * Makes a new SLAP
	 */
	@SuppressWarnings("unchecked")
	public SLAP() {
		
		dd = new DefaultData(this) ;
		courseManager = (Manager<String, Course>) ObjectIO.objectIn(DIR_NAME, CM_FILE_NAME) ;
		if(courseManager == null) {
			dd.loadCourseData() ;
		}
		if(! AccountManager.setAccountMap((AccountMap) ObjectIO.objectIn(DIR_NAME, USER_FILE_NAME))) {
			dd.loadUserData() ;
		}
		
		//System.out.println(AccountManager.listAccounts());
		
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
	
	/**
	 * Get the SLAP frame
	 * @return frame the SLAP frame
	 */
	protected SLAPFrame getFrame() {
		return frame ;
	}
	
	/**
	 * Set the account map
	 * @param savedMap
	 */
	protected void setAccountMap(AccountMap savedMap) {
		AccountManager.setAccountMap(savedMap) ;
	}
	
	/**
	 * Get the account map
	 * @return the account map
	 */
	protected AccountMap getAccountMap() {
		return AccountManager.getAccountMap() ;
	}
}
