package slap;

public class SLAP {
	
	@SuppressWarnings("unused")
	private SLAPFrame frame ;
	
	public SLAP() {
		
		Manager<String, Course> courseManager = new Manager<String, Course>() ;
		
		/*
		 * FOR USE IN DEVELOPMENT/TESTING ONLY
		 * Create user accounts 
		 */
		AccountManager.createAccount("Student1", "Test", "student", "student", Role.student);
		AccountManager.createAccount("Instructor1", "Test", "instructor", "instructor", Role.instructor);
		AccountManager.createAccount("Administrator1", "Test", "admin", "admin", Role.administrator);
		AccountManager.createAccount("z", "z", "z", "z", Role.administrator) ;
		
		frame = new SLAPFrame() ;
	}
}
