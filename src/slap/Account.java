/**
 * 
 */
package slap;

/**
 * @author Michael
 *
 */
class Account{
	
	protected long id = -1;
	protected String firstName = "unspecified";
	protected String lastName = "unspecified";
	protected String username = "unspecified";
	protected String psw = "";
	protected Role role = null;
	
	protected Account(String firstName, String lastName, String username, String password, Role name) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.psw = password;
		this.role = name;
		//generateID();
	}
	
	/**
	 * @param id the id to set
	 */
	protected void setId(long id) {
		this.id = id;
	}

	/**
	 * @param firstName the firstName to set
	 */
	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @param username the username to set
	 */
	protected void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @param psw the password to set
	 */
	protected void setPsw(String psw) {
		this.psw = psw;
	}
	
	/**
	 * @param role the role to set
	 */
	protected void setRole(Role role) {
		this.role = role;
	}
	
	
	/**
	 * @return the id
	 */
	protected long getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	protected String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	protected String getLastName() {
		return lastName;
	}
	
	/**
	 * @return the username
	 */
	protected String getUsername() {
		return username;
	}
	
	/**
	 * @return the password
	 */
	protected String getPsw() {
		return psw;
	}
	
	/**
	 * @return the role
	 */
	protected Role getRole() {
		return role;
	}
	
	/**
	 * Authenticates username given on login screen
	 * Note: Temporary implementation just for testing 
	 * @param username
	 * @param psw
	 */
	protected int authenticate(String username, String psw) {
		switch(username.toLowerCase()) {
			case "administrator1": if (psw.equals("password3")) return 0;
		}
		return 1;
	}
}
