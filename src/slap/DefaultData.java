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
		
		Account hamid = new Account("Hamid", "Timorabadi", "htimorab", "htimorab", Role.instructor) ;
		Account misic = new Account("Vojislav", "Misic", "vmisic", "vmisic", Role.instructor) ;
		Account woit = new Account("Denise", "Woit", "dwoit", "dwoit", Role.instructor) ;
		Account debbie = new Account("Deborah", "de Lange", "ddelange", "ddelange", Role.instructor) ;
		Account escobar = new Account("Marcos", "Escobar-Anel", "mescobar", "mescobar", Role.instructor) ;
		AccountManager.getAccountMap().addAccount(hamid) ;
		AccountManager.getAccountMap().addAccount(misic) ;
		AccountManager.getAccountMap().addAccount(woit) ;
		AccountManager.getAccountMap().addAccount(debbie) ;
		AccountManager.getAccountMap().addAccount(escobar) ;
		
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
		
		Course c1 = new Course("CPS310", "Computer Organization II", hamid, "This course provides an introduction to assembly language and computer architecture. Students are taught programming skills in assembly language. Assembly language is the link between a high level language and machine language. CPS310 follows CPS213 and connects state machines that form the machine language to processors.", an1) ;
        Course c2 = new Course("CPS406", "Introduction to Software Engineering", misic, "This course introduces the study of software engineering. A major portion of the course is a team project, taking a system from the initial requirements definition stage through implementation and maintenance.", an2) ;
        Course c3 = new Course("CPS590", "Introduction to Operating Systems", woit, "CPS 590 Computer Science: Introduction to Operating Systems Lecture: 3 hrs./Lab: 1 hr. Introduction to O/S (system calls, interrupts, synchronous and asynchronous traps, O/S structure), using processes (process communication and synchronization), primitive communications (signals and signal management calls), pipes, messages, semaphores, shared memory, memory management, file systems, and (time permitting) remote procedure calls. Prerequisite: CPS305.", an3) ;
        Course c4 = new Course("GMS200", "Introduction to Global Management", debbie, "This course introduces the concepts and complexities of the contemporary global business environment with an emphasis on global competitiveness and the main functional areas of management: planning, organizing, controlling, and leadership. Topics include: origins of management, forms of business ownership, entrepreneurship and intrapreneurship, organization structure, strategy, and international management. A computer-based management simulation will be used. Lecture: 3 hrs.", an4) ;
        Course c5 = new Course("MTH304", "Probability and Statistics I", escobar, "Brief Introduction to Statistics. Description of Numerical Data. Elements of Probability Theory. Discrete Probability Distribution. (Hyper-geometric, Binomial, Poisson). Normal Distribution and its applications. Sampling Distributions. The t-distribution and the Chi-Square distribution. Confidence Interval and Hypothesis Testing concerning the mean, variance and proportion of a single population. Confidence Interval and Hypothesis Testing concerning the mean and proportion of two populations. The F-distribution. Main objective: To introduce the student to some of the key concepts used in statistics, and to allow the student to use this knowledge for realistic applications science.", an5) ;
		
        SLAPDocument iter3 = new SLAPDocument("Iteration 3", "Iteration 3 consists of finishing the team project you have undertaken in the previous iteration (iteration 2). Instructions are to be followed to the letter; failure to do so may result in the submission not being marked, so please read carefully to the end.\n1. How to do it?\nYou are free to choose traditional or agile approach to development, regardless of how you did iterations 1 and 2.\n2. Starting from what? and should the teams change?\nThe teams may form at will, which is why there will be no labels this time (and why full team composition is a must!). Any member can bring in her or his iteration 2 as the\nstarting point to use in iteration 3, but this must be clearly stated in the accompanying document (see 3.c below).\n3. What you need to submit?\nThe deliverables should include\na) a standalone application (executable, JAR, or scripted web page) that works;\nb) source code from which the standalone application can be re-created, together with instructions sufficient to accomplish this if necessary; and\nc) an accompanying document containing the information about the initial product (i.e., from which team's iteration you're starting) and the summary of functionality included in the deliverable and tests it was subjected to, similar to the XP deliverable template you are familiar with.\nAlso,\nd) the document MUST contain the names and student IDs of all team member that have taken part in this iteration and only the team members that have taken part in this iteration; and\ne) all members of the team must submit the same set of deliverables, identical to that of the other members.\nPlease note that \na) submissions without one or more of the deliverables above will NOT receive any marks;\nb) all member of a team will receive the same mark for this iteration; however,\nc) submissions that differ from one team member to another will incur a penalty of 20% for ALL members of the team; and, importantly,\nd) team members that did not contribute should not be listed in the accompanying document - aiding and abetting freeloading might seem acceptable in school, in view of a \ncommon enemy (the instructor) but it is nonetheless unethical, and I'm sure you will find it rather bothersome in your workplace later on.\n4. Is individual submission possible?\nNo, each team must have at least three members.\n5. Deadline?\nThe deadline for submitting the iteration 3 is Thursday April 10 at 12:00 noon. You will be allowed one (1) submission attempt only, so be careful what you submit. Late submissions will be penalized 10% for 0:00:01 - 24:00:00 (hours:minutes:seconds) of delay, 20% for 24:00:01 to 48:00:00 h:m:s, and 50% for 48:00:01  or more delay.") ;
        SLAPDocument iter4 = new SLAPDocument("Iteration 4", "The assignment should summarize your experiences with Traditional and Agile approaches to development, and your opinion as to the applicability of each in the kind of project that was used for iterations 1, 2, and 3. The assignment should be done individually. The assignment should be submitted as a Word or PDF file of not more that two pages, typeset in 10 pt or larger font size, with 1 inch margin on each side and at least 1.5 line spacing between lines. (There are free PDF converters on the Internet, please check.) This is a Turnitin assigment, which means it will be checked for originality via Turnitin and a similarity score thus created will be attached. Assignments with similarity above 50% will be marked with zero points. Assignments with similarity score between 25 and 49.99%, both inclusive, will incur a penalty of 50% of the graded score. The deadline to submit the assignment is Thursday April 10 at 23:59:00.") ;
        c2.assignments.add(iter3.getID(), iter3) ;
        c2.assignments.add(iter4.getID(), iter4) ;
        
        cm.add(c1.getID(), c1) ;
        cm.add(c2.getID(), c2) ;
        cm.add(c3.getID(), c3) ;
        cm.add(c4.getID(), c4) ;
        cm.add(c5.getID(), c5) ;
        
        slap.setCourseManager(cm) ;
	}
}
