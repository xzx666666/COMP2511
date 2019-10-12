package unsw.enrolment;

import java.util.ArrayList;


public class EnrolmentSystem {
	 private ArrayList<Course> courses;
	    private ArrayList<Student> students;
	    private ArrayList<Enrolment> enrolments;

	    public EnrolmentSystem() {
	        courses = new ArrayList<Course>();
	        students = new ArrayList<Student>();
	        enrolments = new ArrayList<Enrolment>();
	    }
	    public void addEnrolment(Enrolment e) {
	    	enrolments.add(e);
	    }

	    public void addCourse(Course c) {
	        courses.add(c);
	    }

	    public void addStudent(Student s) {
	        students.add(s);
	    }
	    public void displayCourseOfferings(String term) {
	    	for (Course course : courses) {
	    		CourseOffering co = new CourseOffering(course,term);
	    		String s = co.toString();
	    		System.out.println(s);
	    	}
	    }
	    public String displayCourses() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("Courses are:\n");
	        for (Course course : courses) {
	            sb.append(course.toString()).append("\n");
	        }
	        String result = new String(sb);
	        System.out.println(result);
	        return result;
	    }
	    public void displayGrade(Enrolment e) {
	    	System.out.println(e.getGrade().toString());
	    }
	    public Enrolment enrolStudent(Student student, CourseOffering co) {
	    	if(student.checkEnrol(co.getCourse())) {
	    		Enrolment em = new Enrolment(co,student);
	    		student.addEnrolment(em);
	    		return em;
	    	}
	    	return null;
	    }

}
