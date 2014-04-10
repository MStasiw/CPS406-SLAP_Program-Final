/**
 * 
 */
package slap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Michael
 *
 */

public class AccountMap implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private SortedMap<String, Account> map = null;

	/**
	 * Constructor to create new instance of an AccountMap object
	 */
	public AccountMap() {
		map = new TreeMap<String, Account>();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Adds a user account to the account map
	 * @param userAccount Account object
	 * @return true if successfully added, false on any error
	 */
	protected boolean addAccount(Account userAccount) {
		if (userAccount == null) return false;
		
		if (!this.userExists(userAccount.getUsername())) {
			try {
				map.put(userAccount.getUsername(), userAccount);
				if (this.userExists(userAccount.getUsername())) {
					return true;
				}else{
					System.err.println("Error occured: " + userAccount.getUsername() + "was not added to map");
				}
			}catch(Exception e) {
				System.err.println("Error occured: " + userAccount.getUsername() + "was not added to map");
				return false;
			}
		}
		return false;
	}
	
	/**
	 * remove a user
	 * @param username the account to remove
	 * @return whether or not the account was removed successfully
	 */
	protected boolean removeAccount(String username) {
		if(this.userExists(username)) {
			map.remove(username) ;
			return true ;
		}
		return false ;
	}
	
	/**
	 * Check if user exists
	 * @param username
	 * @return true if exists, false if does not exist
	 */
	protected boolean userExists(String username) {
		if (username.equals(null) || username.isEmpty()) {
			System.err.println("Error: username cannot be blank");
			return false;
		}
		return map.containsKey(username);
	}
	
	/**
	 * Change Username 
	 * @param currentName
	 * @param newName
	 * @return true if username successfully changed, false on any error
	 */
	protected boolean changeUsername(String currentName, String newName) {
		if (newName.equals(null) || newName.isEmpty()) {
			System.err.println("Error: username cannot be blank");
			System.err.println("Error occured: username for: " + currentName + " was not changed.");
			return false;
		}
		Account oldAccount = null;
		Account newAccount = null;
		
		if (this.userExists(currentName) && !this.userExists(newName)) {
			try {
				oldAccount = map.get(currentName);
				newAccount = (Account)oldAccount.clone();
				newAccount.setUsername(newName);
				map.remove(currentName);
				this.addAccount(newAccount);
			}catch(Exception e) {
				System.err.println("Error occured: username for: " + currentName + " was not changed.");
				return false;
			}
		}else{
			System.err.println("Error occured: username for: " + currentName + " was not changed.");
			return false;
		}
		return true;
	}
	
	/**
	 * Change password 
	 * @param username
	 * @param psw
	 * @return true if successfully changed password, false on any error
	 */
	protected boolean changePassword(String username, String psw) {
		if (psw.equals(null) || psw.isEmpty()) {
			System.err.println("Error: password cannot be blank");
			System.err.println("Error occured: username for: " + username + " was not changed.");
			return false;
		}
		
		Account tempAccount = null;
		if (this.userExists(username)) {
			try {
				tempAccount = map.get(username);
				tempAccount.setPassword(psw);
			}catch(Exception e) {
				System.err.println("Error occured: username for: " + username + " was not changed.");
				return false;
			}
		}else{
			System.err.println("Error occured: username for: " + username + " was not changed.");
			return false;
		}
		return true;
	}
	
	/**
	 * Given username return corresponding user's account object
	 * @param username
	 * @return Account object, else null
	 */
	protected Account getAccountObj(String username) {
		if (this.userExists(username)) {
			try {
				return map.get(username);
			}catch(Exception e) {
				System.err.println("Error occured: could not retrieve user account for username: " + username + ".");
			}
		}else{
			System.err.println("Error occured: user account for username: " + (username.isEmpty() ? "\"\"" : username)+ " could not be found.");
		}
		return null;
	}
	
	/**
	 * Removes all key-value pair mappings; resets AccountMap state
	 */
	protected void reset() {
		if (!map.isEmpty())
			map.clear();
	}
	
	/**
	 * Get an array of all accounts
	 * @return accounts the array of accounts
	 */
	protected Account[] getAccounts() {
		Account[] accounts = new Account[map.size()] ;
		int count = 0 ;
        for(Map.Entry<String, Account> entry : map.entrySet()) 
        {
            accounts[count++] = entry.getValue() ;
        }
        return accounts ;
	}
	
	protected ArrayList<Account> getAccounts(Role role) {
		ArrayList<Account> accounts = new ArrayList<Account>() ;
		for(Map.Entry<String, Account> entry : map.entrySet()) {
			if(entry.getValue().getRole() == role) {
				accounts.add(entry.getValue()) ;
			}
		}
		return accounts ;
	}
	
	/*
	 *  (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		return this.getClass().getName() + " ["
				+ (map != null ? "map=" + toStringHelper(map.entrySet(), maxLen) : "")
				+ "]";
	}
	
	/**
	 * Help method for toString() method
	 * @param collection
	 * @param maxLen
	 * @return
	 */
	private String toStringHelper(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		String key = null;
		String username = "";
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext()
				&& i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			key = iterator.next().toString();
			username = key.substring(0, key.indexOf('='));
			builder.append(username);
		}
		builder.append(")");
		return builder.toString();
	}

	/*
	 * Internal Development and/or Testing method
	 * DO NOT USE in final product: SECURITY RISK
	 */
	protected String DEBUG_toString() {
		final int maxLen = 10;
		return "AccountMap ["
				+ (map != null ? "map=" + DEBUG_toStringHelper(map.entrySet(), maxLen) : "")
				+ "]";
	}

	/**
	 * Help method for DEBUG_toString() method
	 * @param collection
	 * @param maxLen
	 * @return
	 */
	private String DEBUG_toStringHelper(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[{");
		String key = null;
		String username = "";
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext()
				&& i < maxLen; i++) {
			if (i > 0)
				builder.append("}, {");
			key = iterator.next().toString();
			username = key.substring(0, key.indexOf('='));
			builder.append(key + ", ");
			builder.append(map.get(username).getPassword());
		}
		builder.append("}]");
		return builder.toString();
	}
}
