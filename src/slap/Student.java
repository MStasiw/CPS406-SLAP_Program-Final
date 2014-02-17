/**
 * 
 */
package slap;

/**
 * @author Michael
 *
 */
class Student extends Account {
	protected int studentID = -1;

	/**
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 */
	protected Student(String firstName, String lastName, String username,
			String password) {
		super(firstName, lastName, username, password, Role.student);
		this.generateStudentID();
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param name
	 * @param studentID
	 */
	protected Student(String firstName, String lastName, String username,
			String password, int studentID) {
		super(firstName, lastName, username, password, Role.student);
		this.studentID = studentID;
	}

	/**
	 * @return the studentID
	 */
	protected int getStudentID() {
		return studentID;
	}

	/**
	 * @param studentID the studentID to set
	 */
	protected void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
	/**
	 * 
	 */
	private void generateStudentID() {
		//this.studentID = 
	}

}
