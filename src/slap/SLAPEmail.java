
package slap;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Michael
 *
 */
public class SLAPEmail implements Managable {

	private String id ;
	private Date sentTime ;
	private Account from;
	private Role to;
	private String subject;
	private String body;
	
	public SLAPEmail(Account from, Role to, String subject, String body ) {
		sentTime = Calendar.getInstance().getTime() ; 
		this.from=from;
		this.to = to ;
		this.subject=subject;
		this.body=body;
		id = Long.toString(sentTime.getTime()) + " " + getSubject() ;
	}
	
	public String getID() {
		return id ;
	}

	/**
	 * @return the to form from email
	 */
	protected Role getTo() {
		return to;
	}

	/**
	 * @param to the to to set from email
	 */
	protected void setTo(Role to) {
		this.to = to;
	}

	/**
	 * @return the from form from email
	 */
	protected Account getFrom() {
		return from;
	}

	/**
	 * @param from the from to set from email
	 */
	protected void setFrom(Account from) {
		this.from = from;
	}

	/**
	 * @return the subject form from email
	 */
	protected String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set from email
	 */
	protected void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the body
	 */
	protected String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	protected void setBody(String body) {
		this.body = body;
	}
	
	

}
