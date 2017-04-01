package ca.mcgill.ecse321.TAMAS.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class TestTAMASController {
	
	private ManagementSystem ms;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		  PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ms = new ManagementSystem();
	}

	@After
	public void tearDown() throws Exception {
		ms.delete();
	}

	@Test
	public void testCreateTAEval() {
		assertEquals(0, ms.getApplicants().size());
		String evaluation = "He/She is very good";
		TamasController tc = new TamasController(ms);
		Applicant aApplicant = new Applicant(111, "abc", "", true, "", "","", "", "", "", 10, ms);
        ms.addApplicant(aApplicant);
		assertEquals(1, ms.getApplicants().size());

		 try {
			 
				tc.createTAEval(aApplicant.getName(), evaluation);
				
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		   ManagementSystem ms1 = ms;
		  assertEquals(evaluation, ms1.getApplicants().get(0).getEvaluation());
		  
		  ManagementSystem ms2 = (ManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
		  assertEquals(evaluation, ms2.getApplicants().get(0).getEvaluation());
		  ms2.delete();
		}

	@Test
	public void testCreateTAEvalNull() {
		assertEquals(0, ms.getApplicants().size());
		String evaluation = "";
		String error = null;
		Applicant aApplicant = new Applicant(111, "abc", "", true, "", "","", "", "", "", 10, ms);
        ms.addApplicant(aApplicant);
		assertEquals(1, ms.getApplicants().size());
		TamasController tc = new TamasController(ms);
		 try {
		     	tc.createTAEval(aApplicant.getName(), evaluation);
		     	
				} catch (InvalidInputException e) {
			    error = e.getMessage();
			  }
			  assertEquals("A space cannot be an evaluation! ", error);
		}
	
	@Test 
	public void testCreateJobPosting() {
		  assertEquals(0, ms.getJobPostings().size()); // import Assert from the `org.junit` package
		  String jobTitle = "ta";
		  
		  Calendar c = Calendar.getInstance();
		  c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		  Date date = new Date(c.getTimeInMillis());
		  
		  String exp = "good";
		  double hourlyRate = 10.1; 
		  Instructor aInstructor = new Instructor("Danial", ms);
		  ms.addInstructor(aInstructor);
		  assertEquals(1, ms.getInstructors().size());
		  
		  Course aCourse = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10,
					10, 10, 10, 10,aInstructor, ms);
		  ms.addCourse(aCourse);
		  assertEquals(1, ms.getCourses().size());


		  TamasController tc = new TamasController(ms);
		  try {
			tc.createJobPosting(jobTitle, date, exp, hourlyRate, aCourse);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  ManagementSystem ms1 = ms;
		  checkResultJobPositing(jobTitle, date, exp, hourlyRate, ms1);

		  ManagementSystem ms2 = (ManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
		  checkResultJobPositing(jobTitle, date, exp, hourlyRate, ms2);

		  ms2.delete();
		}

	private void checkResultJobPositing(String jobTitle, Date deadline, String exp, double hourRate, ManagementSystem ms2){
		  assertEquals(0, ms2.getApplicants().size());
		  assertEquals(1, ms2.getJobPostings().size());
		  assertEquals(1, ms2.getCourses().size());
		  assertEquals(jobTitle, ms2.getJobPosting(0).getJobTitle());
		  assertEquals(deadline.toString(), ms2.getJobPosting(0).getSubmissionDeadline().toString());
		  assertEquals(exp, ms2.getJobPosting(0).getPerferredExperience());
		  assertEquals(String.valueOf(hourRate), String.valueOf(ms2.getJobPosting(0).getHourRate()));
		}
	
	@Test
	public void testCreateJobPostingNullDate() {
		  assertEquals(0, ms.getJobPostings().size()); 
		  String jobPosition = "ta";
		  String error = "";
		  Date date = null;
		  
		  String exp = "good";
		  double hourlyRate = 10.1; 
		  Instructor aInstructor = new Instructor("Danial", ms);
		  assertEquals(1, ms.getInstructors().size());
		  
		  Course aCourse = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10,
					10, 10, 10, 10,aInstructor, ms);
		  ms.addCourse(aCourse);
		  assertEquals(1, ms.getCourses().size());

		  TamasController tc = new TamasController(ms);
		  try {
			tc.createJobPosting(jobPosition, date, exp, hourlyRate, aCourse);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
          error = error.trim();
		  assertEquals("Please specify a date!", error);

		  assertEquals(0, ms.getJobPostings().size());
		}
	
    @Test
	public void testCreateJobPostingNullExp() {
		  assertEquals(0, ms.getJobPostings().size());
		  String jobPosition = "ta";
		  String error = "";
		  
		  Calendar c = Calendar.getInstance();
		  c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		  Date date = new Date(c.getTimeInMillis());
		  
		  String exp = null;
		  double hourlyRate = 10.1; 
		  Instructor aInstructor = new Instructor("Danial", ms);
		  assertEquals(1, ms.getInstructors().size());
		  
		  Course aCourse = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10,
					10, 10, 10, 10,aInstructor, ms);
		  ms.addCourse(aCourse);
		  assertEquals(1, ms.getCourses().size());

		  TamasController tc = new TamasController(ms);
		  try {
			tc.createJobPosting(jobPosition, date, exp, hourlyRate, aCourse);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
          error = error.trim();
		  assertEquals("Please specify preferred experience!", error);

		  assertEquals(0, ms.getJobPostings().size());
		}

    @Test
   	public void testCreateJobPostingNullHourlyRate() {
   		  assertEquals(0, ms.getJobPostings().size());
   		  String jobPosition = "ta";
   		  String error = "";
   		  
   		  Calendar c = Calendar.getInstance();
   		  c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
   		  Date date = new Date(c.getTimeInMillis());
   		  
   		  String exp = "good";
   		  double hourlyRate = -1; 
   		  Instructor aInstructor = new Instructor("Danial", ms);
   		  assertEquals(1, ms.getInstructors().size());
   		  
   		  Course aCourse = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10,
   					10, 10, 10, 10,aInstructor, ms);
   		  ms.addCourse(aCourse);
   		  assertEquals(1, ms.getCourses().size());

   		  TamasController tc = new TamasController(ms);
   		  try {
   			tc.createJobPosting(jobPosition, date, exp, hourlyRate, aCourse);
   		} catch (InvalidInputException e) {
   			error = e.getMessage();
   		}
             error = error.trim();
   		  assertEquals("Please specify hourly wage!", error);

   		  assertEquals(0, ms.getJobPostings().size());
   		}
    
    @Test 
	public void testCreateApplication() {
		  assertEquals(0, ms.getApplicants().size()); 
		  
		  Instructor i = new Instructor("Danial", ms);
		  ms.addInstructor(i);
		  assertEquals(1, ms.getInstructors().size());
		  
		  Course c = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10,
					10, 10, 10, 10,i, ms);
		  ms.addCourse(c);
		  assertEquals(1, ms.getCourses().size());
		  
		  JobPosting jp = new JobPosting("", null, "", 10.1, ms, c); 
		  ms.addJobPosting(jp);
		  assertEquals(1, ms.getJobPostings().size());
		  
		  String name = "abc";
		  int id = 111;
		  String major = "a";
		  boolean isUndergrad = true;
		  String year = "2111";
		  String exp = "no"; 
		  String firstChoice = "no" ;
		  String secondChoice = "no";
		  String thirdChoice ="no"; 
		  int totalAppointmentHour = 10;
		  
		  TamasController tc = new TamasController(ms);
		  try {
			tc.createApplication(jp, name, id, major, isUndergrad, year,
					exp, firstChoice, secondChoice, thirdChoice, totalAppointmentHour);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  ManagementSystem ms1 = ms;
		  checkResultApplication(name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice, totalAppointmentHour, ms);
	

		  ManagementSystem ms2 = (ManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
		  checkResultApplication(name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice, totalAppointmentHour, ms);

		  ms2.delete();
		}
       
    private void checkResultApplication( String name, int id, String major,boolean isUndergrad,String year,
	  String exp, String firstChoice, String secondChoice, String thirdChoice, int totalAppointmentHour, ManagementSystem ms2){
    	
		  assertEquals(1, ms2.getApplicants().size());
		  assertEquals(1, ms2.getJobPostings().size());
		  assertEquals(1, ms2.getCourses().size());
		  assertEquals(1, ms2.getInstructors().size());
		  
		  assertEquals(name, ms2.getApplicant(0).getName());
		  assertEquals(id, ms2.getApplicant(0).getStudentID());
		  assertEquals(major, ms2.getApplicant(0).getMajor());
		  assertEquals(isUndergrad, ms2.getApplicant(0).getIsUnderGraduated());
		  assertEquals(year, ms2.getApplicant(0).getYear());
		  assertEquals(exp, ms2.getApplicant(0).getPreviousExperience());
		  assertEquals(firstChoice, ms2.getApplicant(0).getFirstChoice());
		  assertEquals(secondChoice, ms2.getApplicant(0).getSecondChoice());
		  assertEquals(thirdChoice, ms2.getApplicant(0).getThirdChoice());
		  assertEquals(totalAppointmentHour, ms2.getApplicant(0).getTotalAppointmentHours());
		  assertEquals("Submitted", ms2.getApplicant(0).getApplication(0).getApplicationStatus());
		}
    
    @Test 
   	public void testCreateApplicationNullName() {
   		  assertEquals(0, ms.getApplicants().size()); 
   		  
   		  Instructor i = new Instructor("Danial", ms);
   		  ms.addInstructor(i);
   		  assertEquals(1, ms.getInstructors().size());
   		  
   		  Course c = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10,
   					10, 10, 10, 10,i, ms);
   		  ms.addCourse(c);
   		  assertEquals(1, ms.getCourses().size());
   		  
   		  JobPosting jp = new JobPosting("", null, "", 10.1, ms, c); 
   		  ms.addJobPosting(jp);
   		  assertEquals(1, ms.getJobPostings().size());
   		  
   		  String error =null;
   		  String name = null;
   		  int id = 111;
   		  String major = "a";
   		  boolean isUndergrad = true;
   		  String year = "2111";
   		  String exp = "no"; 
   		  String firstChoice = "no" ;
   		  String secondChoice = "no";
   		  String thirdChoice ="no"; 
   		  int totalAppointmentHour = 10;
   		  
   		  TamasController tc = new TamasController(ms);
   		  try {
   			tc.createApplication(jp, name, id, major, isUndergrad, year,
   					exp, firstChoice, secondChoice, thirdChoice, totalAppointmentHour);
   		} catch (InvalidInputException e) {
   			// TODO Auto-generated catch block
   			error = e.getMessage();
   		}
   		 error = error.trim();
		  assertEquals("Name cannot be empty!", error);

		  assertEquals(0, ms.getApplicants().size());
 
   		}
    
    @Test 
   	public void testCreateApplicationNullID() {
   		  assertEquals(0, ms.getApplicants().size()); 
   		  
   		  Instructor i = new Instructor("Danial", ms);
   		  ms.addInstructor(i);
   		  assertEquals(1, ms.getInstructors().size());
   		  
   		  Course c = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10,
   					10, 10, 10, 10,i, ms);
   		  ms.addCourse(c);
   		  assertEquals(1, ms.getCourses().size());
   		  
   		  JobPosting jp = new JobPosting("", null, "", 10.1, ms, c); 
   		  ms.addJobPosting(jp);
   		  assertEquals(1, ms.getJobPostings().size());
   		  
   		  String error =null;
   		  String name = "abc";
   		  int id = -1;
   		  String major = "a";
   		  boolean isUndergrad = true;
   		  String year = "2111";
   		  String exp = "no"; 
   		  String firstChoice = "no" ;
   		  String secondChoice = "no";
   		  String thirdChoice ="no"; 
   		  int totalAppointmentHour = 10;
   		
   		  TamasController tc = new TamasController(ms);
   		  try {
   			tc.createApplication(jp, name, id, major, isUndergrad, year,
   					exp, firstChoice, secondChoice, thirdChoice, totalAppointmentHour);
   		} catch (InvalidInputException e) {
   			// TODO Auto-generated catch block
   			error = e.getMessage();
   		}
   		 error = error.trim();
		  assertEquals("You must input a valid id!", error);

		  assertEquals(0, ms.getApplicants().size());
 
   		}
    
    @Test 
   	public void testCreateApplicationNullMajor() {
   		  assertEquals(0, ms.getApplicants().size()); 
   		  
   		  Instructor i = new Instructor("Danial", ms);
   		  ms.addInstructor(i);
   		  assertEquals(1, ms.getInstructors().size());
   		  
   		  Course c = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10,
   					10, 10, 10, 10,i, ms);
   		  ms.addCourse(c);
   		  assertEquals(1, ms.getCourses().size());
   		  
   		  JobPosting jp = new JobPosting("", null, "", 10.1, ms, c); 
   		  ms.addJobPosting(jp);
   		  assertEquals(1, ms.getJobPostings().size());
   		  
   		  String error =null;
   		  String name = "abc";
   		  int id = 111;
   		  String major = null;
   		  boolean isUndergrad = true;
   		  String year = "2111";
   		  String exp = "no"; 
   		  String firstChoice = "no" ;
   		  String secondChoice = "no";
   		  String thirdChoice ="no"; 
   		  int totalAppointmentHour = 10;
   		  
   		  TamasController tc = new TamasController(ms);
   		  try {
   			tc.createApplication(jp, name, id, major, isUndergrad, year,
   					exp, firstChoice, secondChoice, thirdChoice, totalAppointmentHour);
   		} catch (InvalidInputException e) {
   			// TODO Auto-generated catch block
   			error = e.getMessage();
   		}
   		 error = error.trim();
		  assertEquals("Major cannot be empty!", error);

		  assertEquals(0, ms.getApplicants().size());
 
   		}
    
    @Test 
   	public void testCreateApplicationNullExp() {
   		  assertEquals(0, ms.getApplicants().size()); 
   		  
   		  Instructor i = new Instructor("Danial", ms);
   		  ms.addInstructor(i);
   		  assertEquals(1, ms.getInstructors().size());
   		  
   		  Course c = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10,
   					10, 10, 10, 10,i, ms);
   		  ms.addCourse(c);
   		  assertEquals(1, ms.getCourses().size());
   		  
   		  JobPosting jp = new JobPosting("", null, "", 10.1, ms, c); 
   		  ms.addJobPosting(jp);
   		  assertEquals(1, ms.getJobPostings().size());
   		  
   		  String error =null;
   		  String name = "abc";
   		  int id = 111;
   		  String major = "abc";
   		  boolean isUndergrad = true;
   		  String year = "2111";
   		  String exp = null; 
   		  String firstChoice = "no" ;
   		  String secondChoice = "no";
   		  String thirdChoice ="no"; 
   		  int totalAppointmentHour = 10;
   		  
   		  TamasController tc = new TamasController(ms);
   		  try {
   			tc.createApplication(jp, name, id, major, isUndergrad, year,
   					exp, firstChoice, secondChoice, thirdChoice, totalAppointmentHour);
   		} catch (InvalidInputException e) {
   			// TODO Auto-generated catch block
   			error = e.getMessage();
   		}
   		 error = error.trim();
		  assertEquals("Past experience cannot be empty!", error);

		  assertEquals(0, ms.getApplicants().size());
   		}
    
    @Test 
   	public void testCreateApplicationGreaterThanThree() {
   		  assertEquals(0, ms.getApplicants().size()); 
   		  
   		  Instructor i = new Instructor("Danial", ms);
   		  ms.addInstructor(i);
   		  assertEquals(1, ms.getInstructors().size());
   		  
   		  Course c = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10,
   					10, 10, 10, 10,i, ms);
   		  ms.addCourse(c);
   		  assertEquals(1, ms.getCourses().size());
   		  
   		  JobPosting jp = new JobPosting("", null, "", 10.1, ms, c); 
   		  ms.addJobPosting(jp);
   		  assertEquals(1, ms.getJobPostings().size());
   		  
   		  String error = null;
   		  String name = "abc";
   		  int id = 111;
   		  String major = "a";
   		  boolean isUndergrad = true;
   		  String year = "2111";
   		  String exp = "no"; 
   		  String firstChoice = "no" ;
   		  String secondChoice = "no";
   		  String thirdChoice ="no"; 
   		  int totalAppointmentHour = 10;
   		  
   		  Applicant ap = new Applicant(id, name, exp, isUndergrad, major, year, firstChoice, secondChoice, thirdChoice
   					,"",totalAppointmentHour, ms);
   		  assertEquals(1, ms.getApplicants().size());
   		  
   		  Application app1 = new Application("Submitted", jp,ap);
   		  Application app2 = new Application("Submitted", jp,ap);
   		  Application app3 = new Application("Submitted", jp,ap);

   		  ap.addApplication(app1);
   		  ap.addApplication(app2);
   		  ap.addApplication(app3);


   		  TamasController tc = new TamasController(ms);
   		  try {
   			tc.createApplication(jp, name, id, major, isUndergrad, year,
   					exp, firstChoice, secondChoice, thirdChoice, totalAppointmentHour);
   		} catch (InvalidInputException e) {
   			error = e.getMessage();
   		}
    	  error = error.trim();
		  assertEquals("You cannot submit more applications!", error);
		  
		  assertEquals(3, ms.getApplicant(0).getApplications().size());
   		}
}
