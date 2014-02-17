/**
 * 
 */
package slap;

/**
 * @author Michael
 *
 */
public class SLAP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account predefined1 = new Student("Student1", "Test", "student1", "password");
		Account predefined2 = new Instructor("Instructor1", "Test", "instructor1", "password");
		
		System.out.println("predfined1 = " + predefined1.role);
		System.out.println("predfined2 = " + predefined2.role);
	}

}
