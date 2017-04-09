package ca.mcgill.ecse321.TAMAS.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class TestTAMASPersistence {
	
	private ManagementSystem ms;

	@Before
	public void setUp() throws Exception {
		ms = new ManagementSystem();
		
		//create Instructor
		Instructor i1 = new Instructor("Danial Varro", ms);
		Instructor i2 = new Instructor("Ken Ragan", ms);
		
		//create course
		 Course c1 = new Course("Winter2017", "ECSE", "321", 1, 1, 300, 3, 5,
					2, 2, 3, 10, 10.0,i1, ms);
		 Course c2 = new Course("Fall2018", "COMP", "251", 2, 2, 200, 4, 6,
					3, 1, 2, 10, 15.0,i2, ms);
		 
		//create Applicant
		 Applicant ap1 = new Applicant(263121231, "Kaiyue", "good", true, "SE", "2121", "PHYS142", "MATH133", "no"
 					,"GOOD",11, ms);
		 Applicant ap2 = new Applicant(260000000, "Anqi", "good", false, "CE", "1930", "PHYS131", "MATH140", "no"
 					,"",10, ms);
		 
	    //create JobPosting
		 Calendar cal1 = Calendar.getInstance();
		cal1.set(2013,Calendar.MARCH,28,9,80,0);
		Date date1 = new Date(cal1.getTimeInMillis());
		    
		 Calendar cal2 = Calendar.getInstance();
		 cal2.set(2015,Calendar.SEPTEMBER,15,8,30,0);
		 Date date2 = new Date(cal2.getTimeInMillis());
		    
 		JobPosting jp1 = new JobPosting("TA", date1, "very good", 12.1, ms, c1); 
 		JobPosting jp2 = new JobPosting("Grader", date2, "not so good", 13.6, ms, c2); 

		//create Application
		 Application app1 = new Application(jp1, ap1);
		 Application app2 = new Application(jp2, ap2);

		
	}

	@After
	public void tearDown() throws Exception {
		ms.delete();
	}

	@Test
	public void test() {
	    PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
	    if (!PersistenceXStream.saveToXMLwithXStream(ms))
	        fail("Could not save file.");
	    
	    // clear the model in memory
	    ms.delete();
	    assertEquals(0, ms.getInstructors().size());
	    assertEquals(0, ms.getCourses().size());
	    assertEquals(0, ms.getApplicants().size());
	    assertEquals(0, ms.getJobPostings().size());
	    
	    // load model
	    ms = (ManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
	    if (ms == null)
	        fail("Could not load file.");
	    
	    //check Instructor
	    assertEquals(2, ms.getInstructors().size());
	    assertEquals("Danial Varro", ms.getInstructor(0).getName());
	    assertEquals("Ken Ragan", ms.getInstructor(1).getName());
	    
	    //check Course
	    assertEquals(2, ms.getCourses().size());
	    assertEquals("Winter2017", ms.getCourse(0).getSemester());
	    assertEquals("ECSE", ms.getCourse(0).getCourseName());
	    assertEquals("321", ms.getCourse(0).getCourseCode());
	    assertEquals(1, ms.getCourse(0).getNumTutorial());
	    assertEquals(1, ms.getCourse(0).getNumLab());
	    assertEquals(300, ms.getCourse(0).getNumStudent());
	    assertEquals(3, ms.getCourse(0).getCredit());
	    assertEquals(5, ms.getCourse(0).getNumTaNeeded());
	    assertEquals(2, ms.getCourse(0).getNumGraderNeeded());
	    assertEquals(2, ms.getCourse(0).getLabHour());
	    assertEquals(3, ms.getCourse(0).getTutorialHour());
	    assertEquals(10, ms.getCourse(0).getTotalGraderHour());

	    assertEquals("Fall2018", ms.getCourse(1).getSemester());
	    assertEquals("COMP", ms.getCourse(1).getCourseName());
	    assertEquals("251", ms.getCourse(1).getCourseCode());
	    assertEquals(2, ms.getCourse(1).getNumTutorial());
	    assertEquals(2, ms.getCourse(1).getNumLab());
	    assertEquals(200, ms.getCourse(1).getNumStudent());
	    assertEquals(4, ms.getCourse(1).getCredit());
	    assertEquals(6, ms.getCourse(1).getNumTaNeeded());
	    assertEquals(3, ms.getCourse(1).getNumGraderNeeded());
	    assertEquals(1, ms.getCourse(1).getLabHour());
	    assertEquals(2, ms.getCourse(1).getTutorialHour());
	    assertEquals(10, ms.getCourse(1).getTotalGraderHour());

	    //check applicant
	    assertEquals(2, ms.getApplicants().size());
	    
	    assertEquals(263121231, ms.getApplicant(0).getStudentID());
	    assertEquals("Kaiyue", ms.getApplicant(0).getName());
	    assertEquals("good", ms.getApplicant(0).getPreviousExperience());
	    assertEquals(true, ms.getApplicant(0).getIsUnderGraduated());
	    assertEquals("SE", ms.getApplicant(0).getMajor());
	    assertEquals("2121", ms.getApplicant(0).getYear());
	    assertEquals("PHYS142", ms.getApplicant(0).getFirstChoice());
	    assertEquals("MATH133", ms.getApplicant(0).getSecondChoice());
	    assertEquals("no", ms.getApplicant(0).getThirdChoice());
	    assertEquals("GOOD", ms.getApplicant(0).getEvaluation());
	    assertEquals(11, ms.getApplicant(0).getTotalAppointmentHours());

	    assertEquals(260000000, ms.getApplicant(1).getStudentID());
	    assertEquals("Anqi", ms.getApplicant(1).getName());
	    assertEquals("good", ms.getApplicant(1).getPreviousExperience());
	    assertEquals(false, ms.getApplicant(1).getIsUnderGraduated());
	    assertEquals("CE", ms.getApplicant(1).getMajor());
	    assertEquals("1930", ms.getApplicant(1).getYear());
	    assertEquals("PHYS131", ms.getApplicant(1).getFirstChoice());
	    assertEquals("MATH140", ms.getApplicant(1).getSecondChoice());
	    assertEquals("no", ms.getApplicant(1).getThirdChoice());
	    assertEquals("", ms.getApplicant(1).getEvaluation());
	    assertEquals(10, ms.getApplicant(1).getTotalAppointmentHours());
	    
	    //check jobposting
	    assertEquals(2, ms.getJobPostings().size());
	    
	    Calendar cal1 = Calendar.getInstance();
		cal1.set(2013,Calendar.MARCH,28,9,80,0);
	    Date date1 = new Date(cal1.getTimeInMillis());
	    
	    Calendar cal2 = Calendar.getInstance();
		cal2.set(2015,Calendar.SEPTEMBER,15,8,30,0);
		Date date2 = new Date(cal2.getTimeInMillis());
		
	    assertEquals("TA", ms.getJobPosting(0).getJobTitle());
	    assertEquals(date1.toString(), ms.getJobPosting(0).getSubmissionDeadline().toString());
	    assertEquals("very good", ms.getJobPosting(0).getPerferredExperience());
	    
	    assertEquals("Grader", ms.getJobPosting(1).getJobTitle());
	    assertEquals(date2.toString(), ms.getJobPosting(1).getSubmissionDeadline().toString());
	    assertEquals("not so good", ms.getJobPosting(1).getPerferredExperience());

	    //check application
	    assertEquals(ms.getJobPosting(0), ms.getApplicant(0).getApplication(0).getJobPosting());
	    assertEquals(ms.getJobPosting(1), ms.getApplicant(1).getApplication(0).getJobPosting());

	    
	}

}
