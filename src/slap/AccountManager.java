/**
 * 
 */
package slap;

/**
 * @author Michael
 *
 */
abstract class AccountManager {
	
	private static AccountMap userMap = new AccountMap();

	/**
	 * Workaround to prevent abstract class from being created a a new object
	 */
	private AccountManager() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Create new user account and add it to account map
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param role
	 * @return Account object which was created, if successful, else null
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
	 * Change Username
	 * @param currentName
	 * @param newName
	 * @return true if username successfully changed, false on any error
	 */
	protected static boolean changeUsername(String currentName, String newName) {
		return userMap.changeUsername(currentName, newName);
	}
	
	/**
	 * Change Password
	 * @param username
	 * @param psw
	 * @return true if successfully changed password, false on any error
	 */
	protected static boolean changePassword(String username, String psw) {
		return userMap.changePassword(username, psw);
	}
	
	/**
	 * Given username return corresponding user's account object
	 * @param username
	 * @return Account object, else null
	 */
	protected static Account getAccountObj(String username) {
		return userMap.getAccountObj(username);
	}
	
	/**
	 * Display String representation of AccountMap, usernames and corresponding passwords
	 * @return String representation of AccountMap
	 */
	protected static String DEBUG_listAccounts() {
		return userMap.DEBUG_toString();
	}
	
	/**
	 * Authenticates username given on login screen
	 * @param username inputed by user
	 * @param psw inputed by user
	 * @return true if successfully authenticated user, else false
	 */
	protected static boolean authenticate(String username, String psw) {
		Account temp = null;
		temp = userMap.getAccountObj(username);
		if (temp != null) {
			if (psw.equals(temp.getPassword()))
					return true;
		}
		return false;
	}
	
}
