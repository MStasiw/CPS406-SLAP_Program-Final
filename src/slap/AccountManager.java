/**
 * 
 */
package slap;

/**
 * @author Michael
 *
 */
abstract class AccountManager {
	
	private static AccountMap userMap = null;

	/**
	 * Workaround to prevent abstract class from being instantiated as a new object
	 */
	private AccountManager() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
	private static boolean initialize() {
		if (userMap == null) {
			userMap = new AccountMap();
			return true;
		}
		return false;
	}
	
	/**
	 * Clears user account database,
	 * deleting all user Account objects stored in database
	 */
	protected static void deleteAllUsers() {
		initialize();
		userMap.reset();
	}
	
	/**
	 * Gets the AccountMap object, database of user accounts
	 * @return userMap the AccountMap object
	 */
	protected static AccountMap getAccountMap() {
		initialize();
		return userMap;
	}
	
	protected static boolean setAccountMap(AccountMap savedMap) {
		if (savedMap == null) {
			initialize();
			return false;
		}
		userMap = savedMap;
		return true;
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
		initialize();
		Account newAccount = null;
		
		switch(role) {
		case student: newAccount = new Student(firstName, lastName, username, password);
			break;
		case instructor: newAccount = new Instructor(firstName, lastName, username, password);
			break;
		case administrator: newAccount = new Administrator(firstName, lastName, username, password);
			break;
		default: return null;
		}
		if (userMap.addAccount(newAccount)) { //add to AccountMap
			//System.err.println(newAccount.toString());
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
		initialize();
		return userMap.changeUsername(currentName, newName);
	}
	
	/**
	 * Change Password
	 * @param username
	 * @param psw
	 * @return true if successfully changed password, false on any error
	 */
	protected static boolean changePassword(String username, String psw) {
		initialize();
		return userMap.changePassword(username, psw);
	}
	
	/**
	 * Given username return corresponding user's account object
	 * @param username
	 * @return Account object, else null
	 */
	protected static Account getAccountObj(String username) {
		initialize();
		return userMap.getAccountObj(username);
	}
	
	/**
	 * Display String representation of AccountMap; usernames it contains
	 * @return String representation of AccountMap
	 */
	protected static String listAccounts() {
		initialize();
		return userMap.toString();
	}
	
	/**
	 * Display String representation of AccountMap, usernames and corresponding passwords
	 * @return String representation of AccountMap
	 */
	protected static String DEBUG_listAccounts() {
		initialize();
		return userMap.DEBUG_toString();
	}
	
	/**
	 * Authenticates username given on login screen
	 * @param username inputed by user
	 * @param psw inputed by user
	 * @return Account object if successfully authenticated user, else null
	 */
	protected static Account authenticate(String username, String psw) {
		initialize();
		Account temp = null;
		temp = userMap.getAccountObj(username);
		if (temp != null) {
			if (psw.equals(temp.getPassword()))
					return temp;
		}
		return null;
	}
	
	/**
	 * Check if variable is null or empty.
	 * @param field
	 * @return true blank, else false
	 */
	protected static boolean isBlank(String field) {
		if (field.equals(null) || field.isEmpty()) {
			return true;
		}
		return false;
	}
	
}
