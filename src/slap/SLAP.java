package slap;

public class SLAP {
	
	@SuppressWarnings("unused")
	private SLAPFrame frame ;
	private Manager<String, Course> courseManager ;
	
	public SLAP() {
		
		courseManager = new Manager<String, Course>() ;
		
		Announcement a1 = new Announcement("Class this week", "There will be no class this week.") ;
        Announcement a2 = new Announcement("Upcoming midterm", "Study for your midterms that are thursday next week.") ;
        Announcement a3 = new Announcement("Cheating Policy", "Any students found cheating will be sorry.") ;

        Manager<String, Announcement> announcements = new Manager<String, Announcement>() ;
        announcements.add(a1.getID(), a1) ;
        announcements.add(a2.getID(), a2) ;
        announcements.add(a3.getID(), a3) ;
		
		Course c1 = new Course("CPS310", "Computer Organization II", "Timorabadi, Hamid", "This course provides an introduction to assembly language and computer architecture. Students are taught programming skills in assembly language. Assembly language is the link between a high level language and machine language. CPS310 follows CPS213 and connects state machines that form the machine language to processors.", announcements) ;
        Course c2 = new Course("CPS406", "Introduction to Software Engineering", "Misic, Vojislav", "This course introduces the study of software engineering. A major portion of the course is a team project, taking a system from the initial requirements definition stage through implementation and maintenance.", announcements) ;
        Course c3 = new Course("CPS590", "Introduction to Operating Systems", "Woit, Denise", "CPS 590 Computer Science: Introduction to Operating Systems Lecture: 3 hrs./Lab: 1 hr. Introduction to O/S (system calls, interrupts, synchronous and asynchronous traps, O/S structure), using processes (process communication and synchronization), primitive communications (signals and signal management calls), pipes, messages, semaphores, shared memory, memory management, file systems, and (time permitting) remote procedure calls. Prerequisite: CPS305.", announcements) ;
        Course c4 = new Course("GMS200", "Introduction to Global Management", "de Lange, Deborah", "This course introduces the concepts and complexities of the contemporary global business environment with an emphasis on global competitiveness and the main functional areas of management: planning, organizing, controlling, and leadership. Topics include: origins of management, forms of business ownership, entrepreneurship and intrapreneurship, organization structure, strategy, and international management. A computer-based management simulation will be used. Lecture: 3 hrs.", announcements) ;
        Course c5 = new Course("MTH304", "Probability and Statistics I", "Escobar-Anel, Marcos", "Brief Introduction to Statistics. Description of Numerical Data. Elements of Probability Theory. Discrete Probability Distribution. (Hyper-geometric, Binomial, Poisson). Normal Distribution and its applications. Sampling Distributions. The t-distribution and the Chi-Square distribution. Confidence Interval and Hypothesis Testing concerning the mean, variance and proportion of a single population. Confidence Interval and Hypothesis Testing concerning the mean and proportion of two populations. The F-distribution. Main objective: To introduce the student to some of the key concepts used in statistics, and to allow the student to use this knowledge for realistic applications science.", announcements) ;
		
        courseManager.add(c1.getID(), c1) ;
        courseManager.add(c2.getID(), c2) ;
        courseManager.add(c3.getID(), c3) ;
        courseManager.add(c4.getID(), c4) ;
        courseManager.add(c5.getID(), c5) ;
        
		/*
		 * FOR USE IN DEVELOPMENT/TESTING ONLY
		 * Create user accounts 
		 */
		AccountManager.createAccount("Student1", "Test", "student", "student", Role.student);
		AccountManager.createAccount("Instructor1", "Test", "instructor", "instructor", Role.instructor);
		AccountManager.createAccount("Administrator1", "Test", "admin", "admin", Role.administrator);
		AccountManager.createAccount("z", "z", "z", "z", Role.administrator) ;
		
		frame = new SLAPFrame(this) ;
	}
	
	protected Manager<String, Course> getCourseManager() {
		return courseManager ;
	}
}
