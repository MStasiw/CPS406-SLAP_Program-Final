/**
 * 
 */
package slap;

/**
 * @author Michael
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account predefined1 = new Student("Student1", "Test", "student1", "password1");
		Account predefined2 = new Instructor("Instructor1", "Test", "instructor1", "password2");
		Account predefined3 = new Administrator("Administrator1", "Test", "administrator1", "password3");
		
		System.out.println("predfined1 = " + predefined1.role);
		System.out.println("predfined2 = " + predefined2.role);
		System.out.println("predfined3 = " + predefined3.role);
	}

}
