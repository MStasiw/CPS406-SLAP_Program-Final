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
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setPsw(password);
		setRole(name);
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @param psw the password to set
	 */
	public void setPsw(String psw) {
		this.psw = psw;
	}
	
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @return the password
	 */
	public String getPsw() {
		return psw;
	}
	
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
}
