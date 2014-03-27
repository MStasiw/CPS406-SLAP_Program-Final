package slap;

public class SLAPDriver {
	public static void main(String[] args) {
		
		/*
		 * FOR USE IN DEVELOPMENT/TESTING ONLY
		 * Create user accounts 
		 */
		AccountManagement.createAccount("Student1", "Test", "student", "student", Role.student);
		AccountManagement.createAccount("Instructor1", "Test", "instructor", "instructor", Role.instructor);
		AccountManagement.createAccount("Administrator1", "Test", "admin", "admin", Role.administrator);
		
		new SLAP() ;
	}
}
