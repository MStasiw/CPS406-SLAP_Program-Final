/**
 * 
 */
package slap;

/**
 * @author Michael
 *
 */
class Instructor extends Account {

	/**
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 */
	protected Instructor(String firstName, String lastName, String username,
			String password) {
		super(firstName, lastName, username, password, Role.instructor);
		// TODO Auto-generated constructor stub
	}

}
