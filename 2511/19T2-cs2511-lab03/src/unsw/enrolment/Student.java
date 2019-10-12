package unsw.enrolment;
import java.util.ArrayList;


public class Student {

    private String zid;
    private ArrayList<Enrolment> enrolments;

	public Student(String zid) {
        this.zid = zid;
        this.enrolments = new ArrayList<Enrolment>();
    }

	public String getZID() {
		return zid;
	}
	
	public boolean checkEnrol(Course c) {
    	if(this.courseComplete(c)) {
    		return false;
    	}
    	else if(c.meetsPreReqs(this)) {
    		return true;
    		
    	}
    	else {
    		return false;
    	}
    }

	
	public void addEnrolment(Enrolment e) {
		this.enrolments.add(e);
	}

	public boolean courseComplete(Course course) {
        for (Enrolment e: enrolments) {
            if (e.getGrade().checkpass() && e.getCourse().equals(course)) {
                return true;
            }
        }
        return false;
    }
	
	public void displayEnrolments() {
        for(Enrolment e:enrolments) {
        	System.out.println(e.toString());
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "zid='" + zid + '\'' +
                '}';
    }
	
	
	

}
