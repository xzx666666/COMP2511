package unsw.enrolment;

public class Grade {
    private int mark;
    private String grade;
    public Grade(int mark, String grade) {
    	this.mark = mark;
    	this.grade = grade;
    }
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "grade: "+ grade + " mark: "+mark;
	}
	public boolean checkpass() {
		if (getGrade().equals("HD")||getGrade().equals("DN")
               		 || getGrade().equals("CR")|| getGrade().equals("PS")) {
               return true;
           }
		return false;
	}
}
