package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
public class Enrolment extends Observable{

    private CourseOffering offering;
    private Grade grade;
    private Student student;
    private List<Session> sessions;

    public Enrolment(CourseOffering offering, Student student, Session... sessions) {
        this.offering = offering;
        this.setStudent(student);
        this.grade = null;
        student.addEnrolment(this);
        offering.addEnrolment(this);
        this.sessions = new ArrayList<>();
        for (Session session : sessions) {
            this.sessions.add(session);
        }
        
        this.addObserver(new Log());
        
    }
    
    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    public boolean hasPassed() {
        return grade != null && grade.isPassing();
    }

//    Whole course marks can no longer be assigned this way.
    public void assignMark(Grades mark) {
    	
        grade = new Grade(mark.getMark());
        this.setChanged();
        this.notifyObservers();
    }
    
    public int getMark() {
    	return this.grade.getMark();
    }

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		
		this.student = student;
		
	}

	

}
