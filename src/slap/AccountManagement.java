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
	
	protected static boolean changeUsername(String currentName, String newName) {
		return userMap.changeUsername(currentName, newName);
	}
	
	protected static boolean changePassword(String username, String psw) {
		return userMap.changePassword(username, psw);
	}
	
	/**
	 * Given username return corresponding user's account object
	 * @param username
	 * @return Account object
	 */
	protected static Account getAccountObj(String username) {
		return userMap.getAccountObj(username);
	}
	
	/**
	 * @return
	 */
	protected static String listAccounts() {
		return userMap.toString();
	}
	
	/**
	 * Authenticates username given on login screen
	 * @param username inputed by user
	 * @param psw inputed by user
	 * @return true if successfully authenticated user, else false
	 */
	protected boolean authenticate(String username, String psw) {
		Account temp = null;
		temp = userMap.getAccountObj(username);
		if (temp != null) {
			if (psw.equals(temp.getPassword()))
					return true;
		}
		return false;
	}
	
}
