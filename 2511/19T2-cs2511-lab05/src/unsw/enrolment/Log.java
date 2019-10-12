package unsw.enrolment;

import java.util.Observable;
import java.util.Observer;

public class Log implements Observer{
	
	@Override
	public void update(Observable o, Object arg) {
		Enrolment example = (Enrolment)o;
		System.out.println(example.getCourse().getCourseCode()+"-"+
		example.getTerm()+"-"+example.getStudent().getZID());
		
		
	}

}
