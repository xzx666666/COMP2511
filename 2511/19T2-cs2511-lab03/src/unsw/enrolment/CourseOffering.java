package unsw.enrolment;
import java.util.ArrayList;

import java.util.List;

public class CourseOffering {

    private Course course;
    private String term;
    private List<Session> sessions;
    private List<Enrolment> enrolments;

    public CourseOffering(Course course, String term) {
        this.course = course;
        this.term = term;
        this.sessions = new ArrayList<Session>();
        this.enrolments = new ArrayList<Enrolment>();
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public Course getCourse() {
        return course;
    }

    public String getTerm() {
        return term;
    }
    public boolean hasOffering(String term) {
        return this.term.equals(term);
    }
    @Override
	public boolean equals(Object otherObj) {
		
		if (this == otherObj) return true;
		if (otherObj == null) return false;
		if (getClass() != otherObj.getClass()) return false;
		CourseOffering co = (CourseOffering)otherObj;
		return (this.course.equals(co.course) && this.term.equals(co.term));
		
	}
    public void addEnrolment(Enrolment e) {
    	this.enrolments.add(e);
    }
	
	 @Override
	    public String toString() {
	        return "CourseOffering{" +
	                "courseCode='" + course.getCourseCode() + '\'' +
	               
	                ", term='" + term + '\'' +
	                '}';
	    }

}
