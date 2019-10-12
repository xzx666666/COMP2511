package unsw.enrolment.test;

import java.time.DayOfWeek;



import java.time.LocalTime;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Enrolment;
import unsw.enrolment.EnrolmentSystem;
import unsw.enrolment.Grade;
import unsw.enrolment.Lab;
import unsw.enrolment.Lecture;
import unsw.enrolment.Session;
import unsw.enrolment.Student;
import unsw.enrolment.Tutorial;

public class EnrolmentTest {

    public static void main(String[] args) {
    	
    	 EnrolmentSystem enrolSys = new EnrolmentSystem();
    	 
        // Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        // TODO Create some sessions for the courses
         
        Session lecture1 = new Lecture ("CLB7", DayOfWeek.TUESDAY, LocalTime.now(), LocalTime.now(), "cindy");
        Session tutorial1 = new Tutorial ("qua104", DayOfWeek.FRIDAY, LocalTime.now(), LocalTime.now(), "frank");
        Session lab1 = new Lab ("cse303", DayOfWeek.THURSDAY, LocalTime.now(), LocalTime.now(), "tom","ass");
        
        Session lecture2 = new Lecture ("Mattew A", DayOfWeek.MONDAY, LocalTime.now(), LocalTime.now(), "matt");
        Session tutorial2 = new Tutorial ("law302", DayOfWeek.TUESDAY, LocalTime.now(), LocalTime.now(), "kitty");
        Session lab2 = new Lab ("cse308", DayOfWeek.THURSDAY, LocalTime.now(), LocalTime.now(), "yammy","ass");
        
        Session lecture3 = new Lecture ("Jhon sir", DayOfWeek.MONDAY, LocalTime.now(), LocalTime.now(), "andrew");
        Session tutorial3 = new Tutorial ("newtown301", DayOfWeek.MONDAY, LocalTime.now(), LocalTime.now(), "gray");
        Session lab3 = new Lab ("k17 g7", DayOfWeek.THURSDAY, LocalTime.now(), LocalTime.now(), "steph","ass");
        
        //add Session into course offering for 1511
        comp1511Offering.addSession(lecture1);
        comp1511Offering.addSession(tutorial1);
        comp1511Offering.addSession(lab1);
        
        //add Session into course offering for 1531
        comp1531Offering.addSession(lecture2);
        comp1531Offering.addSession(tutorial2);
        comp1531Offering.addSession(lab2);
        
        //add Session into course offering for 2521
        comp2521Offering.addSession(lecture3);
        comp2521Offering.addSession(tutorial3);
        comp2521Offering.addSession(lab3);
        
        // TODO Create a student
        Student s1 = new Student("z123456");
        
        enrolSys.addCourse(comp1531);
        enrolSys.addCourse(comp2521);
        enrolSys.addCourse(comp1511);
        enrolSys.displayCourses();
        
        //further enrolment with comp1511 and comp1531
        System.out.println("List CourseOfferring:");
        enrolSys.displayCourseOfferings("19T1");
        enrolSys.addStudent(s1);
        // TODO Enrol the student in COMP1511 for T1 (this should succeed)
        
        Enrolment se1511 = enrolSys.enrolStudent(s1, comp1511Offering);
        
        
        // TODO Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        Enrolment se1531 = enrolSys.enrolStudent(s1, comp1531Offering);
        
        
        // TODO Give the student a passing grade for COMP1511
        se1511.setGrade(new Grade(90,"HD"));
        
        // TODO Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        Enrolment se2521 = enrolSys.enrolStudent(s1, comp2521Offering);
        
        System.out.println("\nSucceed Enrolment:");
        s1.displayEnrolments();
        
    }
}
