package unsw.enrolment;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * A course in the enrolment system.
 * @author Robert Clifton-Everest
 *
 */
public class Course {

    private String courseCode;
    private String title;
    private int uoc;
    private List<Course> prereqs;
    private List<CourseOffering> courseOfferings;


    public Course(String courseCode, String title) {
        this.courseCode = courseCode;
        this.prereqs = new ArrayList<Course>();
    }


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseCode == null) {
			if (other.courseCode != null)
				return false;
		} else if (!courseCode.equals(other.courseCode))
			return false;
		return true;
	}


	public void addPrereq(Course course) {
        prereqs.add(course);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getUOC() {
        return uoc;
    }
	public CourseOffering offers(String term) {
	        
	    	for (CourseOffering courseOffering: courseOfferings) {
	            if (courseOffering.hasOffering(term)) {
	                return courseOffering;
	            }
	        }
	        return null;
	}
	public boolean meetsPreReqs(Student student) {
        for (Course preReq: prereqs) {
            if (!student.courseComplete(preReq)) {
                return false;
            };  
        }
        return true;
    }
	public void addcourseOffering(CourseOffering c) {
		courseOfferings.add(c);
	}
	public List<CourseOffering> getCourseOfferings() {
		return courseOfferings;
	}


	public void setCourseOfferings(List<CourseOffering> courseOfferings) {
		this.courseOfferings = courseOfferings;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Course: " +this.courseCode ;
	}
    

}
