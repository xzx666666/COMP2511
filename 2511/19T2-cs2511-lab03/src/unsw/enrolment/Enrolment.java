package unsw.enrolment;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;

    public Enrolment(CourseOffering offering, Student student) {
        this.offering = offering;
        this.setStudent(student);
        this.grade = new Grade(0,"null");
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	@Override
	public boolean equals(Object otherObj) {
		
		if (this == otherObj) return true;
		if (otherObj == null) return false;
		if (getClass() != otherObj.getClass()) return false;
		Enrolment e = (Enrolment)otherObj;
		return (this.offering.equals(e.offering));
	}
	@Override
	public String toString() {
		return "Student:"+this.student.getZID()+"  Course:" + this.getCourse().getCourseCode()+"  Term"+this.offering.getTerm();
	}
	
//	public boolean checkPass() {
//    	if(this.student.courseComplete(this.getCourse())) {
//    		return false;
//    	}
//    	else if(this.getCourse().meetsPreReqs(student)) {
//    		return true;
//    		
//    	}
//    	else {
//    		return false;
//    	}
//    }
	
	

}
