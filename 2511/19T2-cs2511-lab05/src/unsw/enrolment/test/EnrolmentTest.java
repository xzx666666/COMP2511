package unsw.enrolment.test;

import java.time.DayOfWeek;
import java.util.Observable;
import java.time.LocalTime;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Enrolment;
import unsw.enrolment.Grade;
import unsw.enrolment.Grades;
import unsw.enrolment.CompsiteGrades;
import unsw.enrolment.ConcreteGrade;
import unsw.enrolment.Lecture;
import unsw.enrolment.Log;

import unsw.enrolment.Student;
import unsw.enrolment.Tutorial;

public class EnrolmentTest {

    public static void main(String[] args) {

        // Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");
        //comp1511.addOffering(comp1511Offering);
        
        // Create some sessions for the courses
        Lecture lecture1511 = new Lecture(comp1511Offering, "Rex Vowels", DayOfWeek.TUESDAY, LocalTime.of(12, 0),LocalTime.of(14, 0), "Andrew Taylor");
        Lecture lecture1531 = new Lecture(comp1531Offering, "CLB 5", DayOfWeek.MONDAY, LocalTime.of(9, 0),LocalTime.of(11, 0), "Aarthi Natarajan");
        Lecture lecture2521 = new Lecture(comp2521Offering, "Clancy Auditorium", DayOfWeek.FRIDAY, LocalTime.of(15, 0),LocalTime.of(17, 0), "Ashesh Mahidadia");

        Tutorial tute1531 = new Tutorial(comp1531Offering, "Quad 1041", DayOfWeek.WEDNESDAY, LocalTime.of(18, 0), LocalTime.of(19,0), "Hugh Chan");
        /**
         * design observe pattern 
         */
        
        // Create a student
        Student student = new Student("z5555555");
        Student student1 = new Student("z5555552");

        // Enrol the student in COMP1511 for T1 (this should succeed)
        Enrolment comp1511Enrolment = comp1511Offering.enrol(student, lecture1511);
        //add comp1511 to observed
        
        //comp1511Enrolment.addObserver(comp1511Offering.enrol(student, lecture1511));
        
        
        if (comp1511Enrolment != null)
            System.out.println("Enrolled in COMP1511");
        
        // Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        Enrolment comp1531Enrolment = comp1531Offering.enrol(student, lecture1531, tute1531);

        if (comp1531Enrolment == null)
            System.out.println("Can't enrol in COMP1531");
        
        // Give the student a passing grade for COMP1511
        //comp1511Enrolment.assignMark(65);

        // Assign marks for comp1511
        // TODO Give the student a passing mark for assignment 1
       
        ConcreteGrade ass1= new ConcreteGrade("ass1",8,10);
        
        // TODO Give the student a passing mark for milestone 1 of assignment 2
   
        ConcreteGrade m1 = new ConcreteGrade("m1",20,30);
       
        // TODO Give the student a passing mark for milestone 2 of assignment 2
        ConcreteGrade m2 = new ConcreteGrade("m2",23,30);
        
        // TODO Give the student an assignment 2 mark which is the average of
        // milestone 1 and 2
        CompsiteGrades ass2= new CompsiteGrades("ass2",30);
        ass2.addGrade(m1);
        ass2.addGrade(m2);
        
        // TODO Give the student a prac mark which is the sum of assignment 1
        // and 2
        CompsiteGrades prac = new CompsiteGrades("prac",40);
        prac.addGrade(ass2);
        prac.addGrade(ass1);
        
        System.out.println("prac mark   "+ prac.getMark());
        
        // TODO Give the student a passing exam mark.
        ConcreteGrade exam = new ConcreteGrade("exam",40,60);
        
        System.out.println("exam mark   "+ exam.getMark());
        CompsiteGrades f = new CompsiteGrades("final", 100);
        f.addGrade(exam);
        f.addGrade(prac);
        
        System.out.println("final mark   "+f.getMark());
        comp1511Enrolment.assignMark(f);
        // Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        
        Enrolment comp2521Enrolment = comp2521Offering.enrol(student, lecture2521);

        if (comp2521Enrolment != null)
            System.out.println("Enrolled in COMP2521");
        
        
        
        
        
    }
}
