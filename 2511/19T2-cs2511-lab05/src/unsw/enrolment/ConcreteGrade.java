package unsw.enrolment;

import java.util.Observable;

public class ConcreteGrade implements Grades {
	private int mark;
	private String name;

	public ConcreteGrade(String string, int mark,int max) {
		this.setName(string);
		if(mark<=max) {
			this.setMark(mark);
		}
		else {
			this.setMark(max);
		}
	}
	@Override
	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
