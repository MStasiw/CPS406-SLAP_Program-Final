/**
 * 
 */
package slap;

/**
 * @author Michael
 *
 */
abstract class AccountManagement {
	
	private static AccountMap userMap = new AccountMap();

	/**
	 * 
	 */
	private AccountManagement() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param role
	 * @return
	 */
	protected static Account createAccount(String firstName, String lastName, String username, String password, Role role) {
		Account newAccount = null;
		
		switch(role) {
		case student: newAccount = new Student(firstName, lastName, username, password);
			break;
		case instructor: newAccount = new Instructor(firstName, lastName, username, password);
			break;
		case administrator: newAccount = new Administrator(firstName, lastName, username, password);
			break;
		default:
			break;
		}
		if (userMap.addAccount(newAccount)) { //add to AccountMap
			return newAccount;
		}
		return null;
	}
	
	/**
	 * @return
	 */
	protected static String listAccounts() {
		return userMap.toString();
	}
	
	/**
	 * Authenticates username given on login screen
	 * Note: Temporary implementation just for testing
	 * @param username
	 * @param psw
	 * @return
	 */
	protected int authenticate(String username, String psw) {
		switch(username.toLowerCase()) {
			case "administrator1": if (psw.equals("password3")) return 0;
		}
		return 1;
	}

}
