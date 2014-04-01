/**
 * 
 */
package slap;

/**
 * @author Michael
 *
 */
class Administrator extends Account {

	/**
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 */
	protected Administrator(String firstName, String lastName, String username,
			String password) {
		super(firstName, lastName, username, password, Role.administrator);
		// TODO Auto-generated constructor stub
	}

}
