package unsw.enrolment;

import java.util.ArrayList;
import java.util.Observable;

public class CompsiteGrades implements Grades{
	private int mark;
	private String name;
	private ArrayList<Grades> grade;
	private int max;
	public CompsiteGrades(String string, int max) {
		this.name = string;
		this.max = max;
		this.grade = new ArrayList<Grades>();
		
	}

	@Override
	public int getMark() {
		int mark = 0;
		for(Grades g : grade) {
			
			mark = mark+g.getMark();
			
		}
		if(this.name.equals("ass2")) {
			mark = mark/grade.size();
		}
		if(max>=mark) {
			this.mark = mark;
			return mark;
		}
		
		this.mark = max;
		return this.max;
	}

	public ArrayList<Grades> getGrade() {
		return grade;
	}

	public void addGrade(Grades grade) {
		this.grade.add(grade);
	}
}
