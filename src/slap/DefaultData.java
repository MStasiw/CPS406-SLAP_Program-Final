package slap;

public class DefaultData {
	
	private SLAP slap ;
	
	public DefaultData(SLAP slap) {
		this.slap = slap ;
	}
	
	protected void loadUserData() {
		/*
		 * FOR USE IN DEVELOPMENT/TESTING ONLY
		 * Create user accounts 
		 */
		AccountManager.createAccount("Student1", "Test", "student", "student", Role.student);
		AccountManager.createAccount("Instructor1", "Test", "instructor", "instructor", Role.instructor);
		AccountManager.createAccount("Administrator1", "Test", "admin", "admin", Role.administrator);
		AccountManager.createAccount("xavier", "xats", "x", "x", Role.student) ;
		AccountManager.createAccount("yolanda", "yantee", "y", "y", Role.instructor) ;
		AccountManager.createAccount("zoolander", "zebadia", "z", "z", Role.administrator) ;
	}
	
	protected void loadCourseData() {
		Manager<String, Course> cm = new Manager<String, Course>() ;
		
		Announcement a1 = new Announcement("CPS310 Class this week", "There will be no class this week.") ;
        Announcement a2 = new Announcement("CPS310 Upcoming midterm", "Study for your midterms that are thursday next week.") ;
        Announcement a3 = new Announcement("CPS310 Cheating Policy", "Any students found cheating will be sorry.") ;
        
        Announcement a4 = new Announcement("CPS406 Class this week", "There will be no class this week.") ;
        Announcement a5 = new Announcement("CPS406 Upcoming midterm", "Study for your midterms that are thursday next week.") ;
        Announcement a6 = new Announcement("CPS406 Cheating Policy", "Any students found cheating will be sorry.") ;
        
        Announcement a7 = new Announcement("CPS590 Class this week", "There will be no class this week.") ;
        Announcement a8 = new Announcement("CPS590 Upcoming midterm", "Study for your midterms that are thursday next week.") ;
        Announcement a9 = new Announcement("CPS590 Cheating Policy", "Any students found cheating will be sorry.") ;
        
        Announcement a10 = new Announcement("GMS200 Class this week", "There will be no class this week.") ;
        Announcement a11 = new Announcement("GMS200 Upcoming midterm", "Study for your midterms that are thursday next week.") ;
        Announcement a12 = new Announcement("GMS200 Cheating Policy", "Any students found cheating will be sorry.") ;
        
        Announcement a13 = new Announcement("MTH304 Class this week", "There will be no class this week.") ;
        Announcement a14 = new Announcement("MTH304 Upcoming midterm", "Study for your midterms that are thursday next week.") ;
        Announcement a15 = new Announcement("MTH304 Cheating Policy", "Any students found cheating will be sorry.") ;

        Manager<String, Announcement> an1 = new Manager<String, Announcement>() ;
        an1.add(a1.getID(), a1) ;
        an1.add(a2.getID(), a2) ;
        an1.add(a3.getID(), a3) ;
        
        Manager<String, Announcement> an2 = new Manager<String, Announcement>() ;
        an2.add(a4.getID(), a4) ;
        an2.add(a5.getID(), a5) ;
        an2.add(a6.getID(), a6) ;
        
        Manager<String, Announcement> an3 = new Manager<String, Announcement>() ;
        an3.add(a7.getID(), a7) ;
        an3.add(a8.getID(), a8) ;
        an3.add(a9.getID(), a9) ;
        
        Manager<String, Announcement> an4 = new Manager<String, Announcement>() ;
        an4.add(a10.getID(), a10) ;
        an4.add(a11.getID(), a11) ;
        an4.add(a12.getID(), a12) ;
        
        Manager<String, Announcement> an5 = new Manager<String, Announcement>() ;
        an5.add(a13.getID(), a13) ;
        an5.add(a14.getID(), a14) ;
        an5.add(a15.getID(), a15) ;
		
		Course c1 = new Course("CPS310", "Computer Organization II", "Timorabadi, Hamid", "This course provides an introduction to assembly language and computer architecture. Students are taught programming skills in assembly language. Assembly language is the link between a high level language and machine language. CPS310 follows CPS213 and connects state machines that form the machine language to processors.", an1) ;
        Course c2 = new Course("CPS406", "Introduction to Software Engineering", "Misic, Vojislav", "This course introduces the study of software engineering. A major portion of the course is a team project, taking a system from the initial requirements definition stage through implementation and maintenance.", an2) ;
        Course c3 = new Course("CPS590", "Introduction to Operating Systems", "Woit, Denise", "CPS 590 Computer Science: Introduction to Operating Systems Lecture: 3 hrs./Lab: 1 hr. Introduction to O/S (system calls, interrupts, synchronous and asynchronous traps, O/S structure), using processes (process communication and synchronization), primitive communications (signals and signal management calls), pipes, messages, semaphores, shared memory, memory management, file systems, and (time permitting) remote procedure calls. Prerequisite: CPS305.", an3) ;
        Course c4 = new Course("GMS200", "Introduction to Global Management", "de Lange, Deborah", "This course introduces the concepts and complexities of the contemporary global business environment with an emphasis on global competitiveness and the main functional areas of management: planning, organizing, controlling, and leadership. Topics include: origins of management, forms of business ownership, entrepreneurship and intrapreneurship, organization structure, strategy, and international management. A computer-based management simulation will be used. Lecture: 3 hrs.", an4) ;
        Course c5 = new Course("MTH304", "Probability and Statistics I", "Escobar-Anel, Marcos", "Brief Introduction to Statistics. Description of Numerical Data. Elements of Probability Theory. Discrete Probability Distribution. (Hyper-geometric, Binomial, Poisson). Normal Distribution and its applications. Sampling Distributions. The t-distribution and the Chi-Square distribution. Confidence Interval and Hypothesis Testing concerning the mean, variance and proportion of a single population. Confidence Interval and Hypothesis Testing concerning the mean and proportion of two populations. The F-distribution. Main objective: To introduce the student to some of the key concepts used in statistics, and to allow the student to use this knowledge for realistic applications science.", an5) ;
		
        cm.add(c1.getID(), c1) ;
        cm.add(c2.getID(), c2) ;
        cm.add(c3.getID(), c3) ;
        cm.add(c4.getID(), c4) ;
        cm.add(c5.getID(), c5) ;
        
        slap.setCourseManager(cm) ;
	}
}
