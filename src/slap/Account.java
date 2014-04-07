/**
 * 
 */
package slap;

import java.io.Serializable;

/**
 * User Account
 * @author Michael
 *
 */
class Account implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 1L;
	protected long id = -1;
	protected String firstName = "unspecified";
	protected String lastName = "unspecified";
	protected String username = "unspecified";
	protected String password = "";
	protected Role role = null;
	protected Manager<String, SLAPEmail> email = null;
	
	/**
	 * Creates new user account object
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param name
	 */
	protected Account(String firstName, String lastName, String username, String password, Role name) {
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setPassword(password);
		setRole(name);
	}
	
	/**
	 * @param id the id to set
	 */
	protected void setId(long id) {
		this.id = id;
	}

	/**
	 * Set user's First Name
	 * @param firstName the firstName to set
	 */
	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Set user's Last Name
	 * @param lastName the lastName to set
	 */
	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Set user's Username
	 * @param username the username to set
	 */
	protected void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Set user's Password
	 * @param password the password to set
	 */
	protected void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Sets user's Role
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
	 * Gets user's First Name
	 * @return the firstName
	 */
	protected String getFirstName() {
		return firstName;
	}

	/**
	 * Gets user's Last Name
	 * @return the lastName
	 */
	protected String getLastName() {
		return lastName;
	}
	
	/**
	 * Gets user's username
	 * @return the username
	 */
	protected String getUsername() {
		return username;
	}
	
	/**
	 * Gets user's password
	 * @return the password
	 */
	protected String getPassword() {
		return password;
	}
	
	/**
	 * Gets user's Role
	 * @return the role
	 */
	protected Role getRole() {
		return role;
	}
	
	/**
	 * Implementation of Cloneable  
	 */
	protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (this.equals(null)) return null;
		String retVal = null;
		retVal = "Username: " + this.getUsername() + "\n"
				+ "Name: " + this.getLastName() + ", " + this.getFirstName() + "\n"
				+ "Role: " + this.getRole();
		return retVal;
	}
}
