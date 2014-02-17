/**
 * 
 */
package slap;

/**
 * @author Michael
 *
 */
class Student extends Account {

	/**
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 */
	public Student(String firstName, String lastName, String username,
			String password) {
		super(firstName, lastName, username, password, Role.student);
		// TODO Auto-generated constructor stub
	}

}
