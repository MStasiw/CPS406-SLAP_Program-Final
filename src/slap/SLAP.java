package slap;

public class SLAP {
	
	@SuppressWarnings("unused")
	private SLAPFrame frame ;
	
	public SLAP() {
		
		/*
		 * FOR USE IN DEVELOPMENT/TESTING ONLY
		 * Create user accounts 
		 */
		AccountManagement.createAccount("Student1", "Test", "student", "student", Role.student);
		AccountManagement.createAccount("Instructor1", "Test", "instructor", "instructor", Role.instructor);
		AccountManagement.createAccount("Administrator1", "Test", "admin", "admin", Role.administrator);
		AccountManagement.createAccount("z", "z", "z", "z", Role.administrator) ;
		
		frame = new SLAPFrame() ;
	}
}
