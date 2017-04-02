package ca.mcgill.ecse321.TAMAS.Web;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.controller.TController;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;

import java.io.File;
import java.sql.Date;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
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

		@org.junit.Test
		public void testCreateTAEval() {
			assertEquals(0, ms.getApplicants().size());
			String evaluation = "He/She is very good";
			TController tc = new TController(ms);
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

		@org.junit.Test
		public void testCreateTAEvalNull() {
			assertEquals(0, ms.getApplicants().size());
			String evaluation = " ";
			String error = null;
			Applicant aApplicant = new Applicant(111, "abc", "", true, "", "","", "", "", "", 10, ms);
	        ms.addApplicant(aApplicant);
			assertEquals(1, ms.getApplicants().size());
			TController tc = new TController(ms);
			 try {
			     	tc.createTAEval(aApplicant.getName(), evaluation);
			     	
					} catch (InvalidInputException e) {
				    error = e.getMessage();
				  }
			 
			      error = error.trim();
				  assertEquals("A space cannot be an evaluation!", error);
			}
		
		@org.junit.Test
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


			  TController tc = new TController(ms);
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
		
		@org.junit.Test
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

			  TController tc = new TController(ms);
			  try {
				tc.createJobPosting(jobPosition, date, exp, hourlyRate, aCourse);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
	          error = error.trim();
			  assertEquals("Please specify a date!", error);

			  assertEquals(0, ms.getJobPostings().size());
			}
		
		@org.junit.Test
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

			  TController tc = new TController(ms);
			  try {
				tc.createJobPosting(jobPosition, date, exp, hourlyRate, aCourse);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
	          error = error.trim();
			  assertEquals("Please specify preferred experience!", error);

			  assertEquals(0, ms.getJobPostings().size());
			}

		@org.junit.Test
	   	public void testCreateJobPostingInvalidHourlyRate() {
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

	   		  TController tc = new TController(ms);
	   		  try {
	   			tc.createJobPosting(jobPosition, date, exp, hourlyRate, aCourse);
	   		} catch (InvalidInputException e) {
	   			error = e.getMessage();
	   		}
	             error = error.trim();
	   		  assertEquals("Please specify hourly wage!", error);

	   		  assertEquals(0, ms.getJobPostings().size());
	   		}
	    
		@org.junit.Test
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
			  
			  TController tc = new TController(ms);
			  try {
				tc.createApplication(jp, name, id, major, isUndergrad, year,
						exp, firstChoice, secondChoice, thirdChoice, totalAppointmentHour);
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  ManagementSystem ms1 = ms;
			  checkResultApplication(name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice, totalAppointmentHour, ms1);
		

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
	    
		@org.junit.Test
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
	   		  
	   		  TController tc = new TController(ms);
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
	    
		@org.junit.Test
	   	public void testCreateApplicationInvalidID() {
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
	   		
	   		  TController tc = new TController(ms);
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
	    
		@org.junit.Test
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
	   		  
	   		  TController tc = new TController(ms);
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
	    
		@org.junit.Test
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
	   		  
	   		  TController tc = new TController(ms);
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
	    
	    @org.junit.Test
 
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


	   		  TController tc = new TController(ms);
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
	    
	    @org.junit.Test

	    public void testRegisterApplicant() {
			  assertEquals(0, ms.getApplicants().size()); 
			  
			  TController tc = new TController(ms);
			  try {
				tc.registerApplicant("a");
				tc.registerApplicant("b");
				tc.registerApplicant("c");

			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  ManagementSystem ms1 = ms;
			  checkResultRegisterApplicant("a", ms1, 0);
			  checkResultRegisterApplicant("b", ms1, 1);
			  checkResultRegisterApplicant("c", ms1, 2);

			}
	     
	        private void checkResultRegisterApplicant( String name, ManagementSystem ms2, int index){
	  	
			  assertEquals(3, ms2.getApplicants().size());
			  assertEquals(0, ms2.getJobPostings().size());
			  assertEquals(0, ms2.getCourses().size());
			  assertEquals(0, ms2.getInstructors().size());
			  
			  assertEquals(name, ms2.getApplicant(index).getName());
			}
	       
	        @org.junit.Test

	       public void testRegisterApplicantNullName() {
			  assertEquals(0, ms.getApplicants().size()); 
			  
			  String name = null;
			  String error = null;
			  
	   		  TController tc = new TController(ms);
			  try {
				tc.registerApplicant(name);

			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				error = e.getMessage();
			}
			  
			  error = error.trim();
			  assertEquals("Name cannot be empty!", error);
			  
			  assertEquals(0, ms.getApplicants().size());
			}
	        
	        
	        @org.junit.Test

	       public void testRegisterApplicantDupUserAppName() {
			  assertEquals(0, ms.getApplicants().size()); 
			  
			  Applicant ap1 = new Applicant(111, "a", "", true, "", "","", "", "", "", 10, ms);
			  Applicant ap2 = new Applicant(111, "b", "", true, "", "","", "", "", "", 10, ms);
			  Applicant ap3 = new Applicant(111, "c", "", true, "", "","", "", "", "", 10, ms);

			  assertEquals(3, ms.getApplicants().size());
			  
			  String error = null;
			  
	   		  TController tc = new TController(ms);
			  try {
				tc.registerApplicant(ap1.getName());
				
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			  
			  error = error.trim();
			  assertEquals("This username already exists!", error);
			  
			  assertEquals(3, ms.getApplicants().size());
			}
	        
	        
	        
	       @org.junit.Test
	       public void testRegisterApplicantDupUserInstName() {
			  assertEquals(0, ms.getApplicants().size()); 
			  
			  Instructor Inst1 = new Instructor("a", ms);
			  Instructor Inst2 = new Instructor("b", ms);
			  Instructor Inst3 = new Instructor("c", ms);
			  
			  assertEquals(3, ms.getInstructors().size());
			  
			  String error = null;
			  
	   		  TController tc = new TController(ms);
			  try {
				tc.registerApplicant(Inst1.getName());
				
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			  
			  error = error.trim();
			  assertEquals("This username already exists!", error);
			  
			  assertEquals(3, ms.getInstructors().size());
			}
	        
	        @org.junit.Test
 
	    	public void testCreateCourse() {
	    		  assertEquals(0, ms.getCourses().size()); 
	    		
	    		  String semester = "Winter3000";
	    		  String courseName = "ECSE";
	    		  String courseCode = "23232";
	    		  int credit = 10;
	    		  int maxStudent = 1; 
	    		  String instructorName = "Gates";
	    		  int numLab = 0;
	    		  int numTut = 0;
	    		  int numTaNeeded = 0;
	    		  int numGraderNeeded = 0;
	    		  int hourTa = 0;
	    		  int hourGrader = 0;
	    		  
		   		  TController tc = new TController(ms);
	    		  try {
	    			tc.createCourse(semester, courseName, courseCode, credit, maxStudent,
	    					instructorName, numLab, numTut, numTaNeeded, numGraderNeeded,
	    					hourTa, hourGrader, ms);
	    		} catch (InvalidInputException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		  
	    		  ManagementSystem ms1 = ms;
	    		  checkResultCourse(semester, courseName, courseCode, credit, maxStudent,
	    				  instructorName, numLab, numTut, numTaNeeded, numGraderNeeded,
	  					hourTa, hourGrader, ms1);

	    		  ManagementSystem ms2 = (ManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
	    		  checkResultCourse(semester, courseName, courseCode, credit, maxStudent,
	    					instructorName, numLab, numTut, numTaNeeded, numGraderNeeded,
	      					hourTa, hourGrader, ms);
	    		  ms2.delete();
	    		}
	           
	        private void checkResultCourse(String semester, String courseName, String courseCode, int credit,
			  int maxStudent, String instructorName,int numLab, int numTut, int numTaNeeded, int numGraderNeeded,
		      int hourTa, int hourGrader, ManagementSystem ms2){
	        	
	    		  assertEquals(0, ms2.getApplicants().size());
	    		  assertEquals(0, ms2.getJobPostings().size());
	    		  assertEquals(1, ms2.getCourses().size());
	    		  assertEquals(1, ms2.getInstructors().size());
	    		  
	    		  assertEquals(semester, ms2.getCourse(0).getSemester());
	    		  assertEquals(courseName, ms2.getCourse(0).getCourseName());
	    		  assertEquals(courseCode, ms2.getCourse(0).getCourseCode());
	    		  assertEquals(credit, ms2.getCourse(0).getCredit());
	    		  assertEquals(maxStudent, ms2.getCourse(0).getNumStudent());
	    		  assertEquals(instructorName, ms2.getCourse(0).getInstructor().getName());
	    		  assertEquals(numLab, ms2.getCourse(0).getNumLab());
	    		  assertEquals(numTut, ms2.getCourse(0).getNumTutorial());
	    		  assertEquals(numTaNeeded, ms2.getCourse(0).getNumTaNeeded());
	    		  assertEquals(numGraderNeeded, ms2.getCourse(0).getNumGraderNeeded());
	    		  assertEquals(hourTa, ms2.getCourse(0).getHourRequiredTa());
	    		  assertEquals(hourGrader, ms2.getCourse(0).getHourRequiredGrader());
	    		}
	        
	        @org.junit.Test

	    	public void testCreateCourseNullCourseName() {
	     		  assertEquals(0, ms.getCourses().size()); 
	     		  
	     		  String error = null;

	    		  String semester = "Winter3000";
	    		  String courseName = null;
	    		  String courseCode = "23232";
	    		  int credit = 10;
	    		  int maxStudent = 1; 
	    		  String instructorName = "Gates";
	    		  int numLab = 0;
	    		  int numTut = 0;
	    		  int numTaNeeded = 0;
	    		  int numGraderNeeded = 0;
	    		  int hourTa = 0;
	    		  int hourGrader = 0;
	    		  
	     		  
		   		  TController tc = new TController(ms);
	     		  try {
	     			 tc.createCourse(semester, courseName, courseCode, credit, maxStudent,
	     					instructorName, numLab, numTut, numTaNeeded, numGraderNeeded,
	     					hourTa, hourGrader, ms);
	     			 
	     		} catch (InvalidInputException e) {
	     			// TODO Auto-generated catch block
	     			error = e.getMessage();
	     		}
	     		 error = error.trim();
	  		  assertEquals("Please specify the course name!", error);

	  		  assertEquals(0, ms.getCourses().size());
	     		}
	        
	        @org.junit.Test

	    	public void testCreateCourseNullCourseCode() {
	     		  assertEquals(0, ms.getCourses().size()); 
	     		  
	     		  String error = null;

	    		  String semester = "Winter3000";
	    		  String courseName = "ECSE";
	    		  String courseCode = null;
	    		  int credit = 10;
	    		  int maxStudent = 1; 
	    		  String instructorName = "Gates";
	    		  int numLab = 0;
	    		  int numTut = 0;
	    		  int numTaNeeded = 0;
	    		  int numGraderNeeded = 0;
	    		  int hourTa = 0;
	    		  int hourGrader = 0;
	    		  
	     		  
		   		  TController tc = new TController(ms);
	     		  try {
	     			 tc.createCourse(semester, courseName, courseCode, credit, maxStudent,
	     					instructorName, numLab, numTut, numTaNeeded, numGraderNeeded,
	     					hourTa, hourGrader, ms);
	     			 
	     		} catch (InvalidInputException e) {
	     			// TODO Auto-generated catch block
	     			error = e.getMessage();
	     		}
	     		 error = error.trim();
	  		  assertEquals("Please specify the course code!", error);

	  		  assertEquals(0, ms.getCourses().size());
	     		}
	        
	        @org.junit.Test

	    	public void testCreateCourseInvalidCredit() {
	     		  assertEquals(0, ms.getCourses().size()); 
	     		  
	     		  String error = null;

	    		  String semester = "Winter3000";
	    		  String courseName = "ECSE";
	    		  String courseCode = "232323";
	    		  int credit = -1;
	    		  int maxStudent = 1; 
	    		  String instructorName = "Gates";
	    		  int numLab = 0;
	    		  int numTut = 0;
	    		  int numTaNeeded = 0;
	    		  int numGraderNeeded = 0;
	    		  int hourTa = 0;
	    		  int hourGrader = 0;
	    		  
	     		  
		   		  TController tc = new TController(ms);
	     		  try {
	     			 tc.createCourse(semester, courseName, courseCode, credit, maxStudent,
	     					instructorName, numLab, numTut, numTaNeeded, numGraderNeeded,
	     					hourTa, hourGrader, ms);
	     			 
	     		} catch (InvalidInputException e) {
	     			// TODO Auto-generated catch block
	     			error = e.getMessage();
	     		}
	     		 error = error.trim();
	  		  assertEquals("Please specify the number of credits in the correct format!", error);

	  		  assertEquals(0, ms.getCourses().size());
	     		}
	        
	        @org.junit.Test

	    	public void testCreateCourseInvalidMaxStudent() {
	     		  assertEquals(0, ms.getCourses().size()); 
	     		  
	     		  String error = null;

	    		  String semester = "Winter3000";
	    		  String courseName = "ECSE";
	    		  String courseCode = "232323";
	    		  int credit = 10;
	    		  int maxStudent = -1; 
	    		  String instructorName = "Gates";
	    		  int numLab = 0;
	    		  int numTut = 0;
	    		  int numTaNeeded = 0;
	    		  int numGraderNeeded = 0;
	    		  int hourTa = 0;
	    		  int hourGrader = 0;
	    		  
	     		  
		   		  TController tc = new TController(ms);
	     		  try {
	     			 tc.createCourse(semester, courseName, courseCode, credit, maxStudent,
	     					instructorName, numLab, numTut, numTaNeeded, numGraderNeeded,
	     					hourTa, hourGrader, ms);
	     			 
	     		} catch (InvalidInputException e) {
	     			// TODO Auto-generated catch block
	     			error = e.getMessage();
	     		}
	     		 error = error.trim();
	  		  assertEquals("Please specify the maximum number of students in the correct format!", error);

	  		  assertEquals(0, ms.getCourses().size());
	     		}
	        
	        @org.junit.Test

	    	public void testCreateCourseNullInstructor() {
	     		  assertEquals(0, ms.getCourses().size()); 
	     		  
	     		  String error = null;

	    		  String semester = "Winter3000";
	    		  String courseName = "ECSE";
	    		  String courseCode = "232323";
	    		  int credit = 10;
	    		  int maxStudent = 1; 
	    		  String instructorName = null;
	    		  int numLab = 0;
	    		  int numTut = 0;
	    		  int numTaNeeded = 0;
	    		  int numGraderNeeded = 0;
	    		  int hourTa = 0;
	    		  int hourGrader = 0;
	     		  
		   		  TController tc = new TController(ms);
	     		  try {
	     			 tc.createCourse(semester, courseName, courseCode, credit, maxStudent,
	     					instructorName, numLab, numTut, numTaNeeded, numGraderNeeded,
	     					hourTa, hourGrader, ms);
	     			 
	     		} catch (InvalidInputException e) {
	     			// TODO Auto-generated catch block
	     			error = e.getMessage();
	     		}
	     		 error = error.trim();
	  		  assertEquals("Please specify the instructor!", error);

	  		  assertEquals(0, ms.getCourses().size());
	     		}
	        
	        @org.junit.Test

	    	public void testCreateCourseInvalidNumLabAndTut() {
	     		  assertEquals(0, ms.getCourses().size()); 
	     		  
	     		  String error = null;
	     		  String realError = ("Please specify the numebr of lab sessions in the correct format! "
	     		      + "Please specify the number of tutorial in the correct format! ").trim(); 

	    		  String semester = "Winter3000";
	    		  String courseName = "ECSE";
	    		  String courseCode = "232323";
	    		  int credit = 10;
	    		  int maxStudent = 1; 
	    		  String instructorName = "Gates";
	    		  int numLab = -1;
	    		  int numTut = -1;
	    		  int numTaNeeded = 0;
	    		  int numGraderNeeded = 0;
	    		  int hourTa = 0;
	    		  int hourGrader = 0;
	     		  
		   		  TController tc = new TController(ms);
	     		  try {
	     			 tc.createCourse(semester, courseName, courseCode, credit, maxStudent,
	     					instructorName, numLab, numTut, numTaNeeded, numGraderNeeded,
	     					hourTa, hourGrader, ms);
	     			 
	     		} catch (InvalidInputException e) {
	     			// TODO Auto-generated catch block
	     			error = e.getMessage();
	     		}
	     		 error = error.trim();
	     		 
	  		  assertEquals(realError, error);

	  		  assertEquals(0, ms.getCourses().size());
	     		}
	        
	        @org.junit.Test

	    	public void testCreateCourseInvalidNumTAndGraderNeeded() {
	     		  assertEquals(0, ms.getCourses().size()); 
	     		  
	     		  String error = null;
	     		  String realError = ("Please specify the number of TA needed in the correct format! "
	     		      + "Please specify the number of Grader needed in the correct format! ").trim(); 

	    		  String semester = "Winter3000";
	    		  String courseName = "ECSE";
	    		  String courseCode = "232323";
	    		  int credit = 10;
	    		  int maxStudent = 1; 
	    		  String instructorName = "Gates";
	    		  int numLab = 1;
	    		  int numTut = 1;
	    		  int numTaNeeded = -1;
	    		  int numGraderNeeded = -1;
	    		  int hourTa = 0;
	    		  int hourGrader = 0;
	     		  
		   		  TController tc = new TController(ms);
	     		  try {
	     			 tc.createCourse(semester, courseName, courseCode, credit, maxStudent,
	     					instructorName, numLab, numTut, numTaNeeded, numGraderNeeded,
	     					hourTa, hourGrader, ms);
	     			 
	     		} catch (InvalidInputException e) {
	     			// TODO Auto-generated catch block
	     			error = e.getMessage();
	     		}
	     		 error = error.trim();
	     		 
	  		  assertEquals(realError, error);

	  		  assertEquals(0, ms.getCourses().size());
	     		}
	        
	        @org.junit.Test

	    	public void testCreateCourseInvalidHourTAndGrader() {
	     		  assertEquals(0, ms.getCourses().size()); 
	     		  
	     		  String error = null;
	     		  String realError = ("Please specify the TA appointment hour in the correct format! "
	     		      + "Please specify the Grader appointment hour in the correct format! ").trim(); 

	    		  String semester = "Winter3000";
	    		  String courseName = "ECSE";
	    		  String courseCode = "232323";
	    		  int credit = 10;
	    		  int maxStudent = 1; 
	    		  String instructorName = "Gates";
	    		  int numLab = 1;
	    		  int numTut = 1;
	    		  int numTaNeeded = 1;
	    		  int numGraderNeeded = 1;
	    		  int hourTa = -1;
	    		  int hourGrader = -1;
	     		 
		   		  TController tc = new TController(ms);
	     		  try {
	     			 tc.createCourse(semester, courseName, courseCode, credit, maxStudent,
	     					instructorName, numLab, numTut, numTaNeeded, numGraderNeeded,
	     					hourTa, hourGrader, ms);
	     			 
	     		} catch (InvalidInputException e) {
	     			// TODO Auto-generated catch block
	     			error = e.getMessage();
	     		}
	     		 error = error.trim();
	     		 
	  		  assertEquals(realError, error);

	  		  assertEquals(0, ms.getCourses().size());
	     		}
	   
	        @org.junit.Test
 
	        public void testAcceptApplication(){
	        	assertEquals(0, ms.getApplicants().size()); 
	        	
	        	Applicant ap = new Applicant(111, "abc", "", true, "", "","", "", "", "", 10, ms);
	            ms.addApplicant(ap);
	    		assertEquals(1, ms.getApplicants().size());
	    		
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
	            
	            Application app = new Application("Submitted", jp,ap);
	     		ap.addApplication(app);

		   		  TController tc = new TController(ms);
	    		 tc.acceptApplication(app);
	    		  
	    		  ManagementSystem ms1 = ms;
	    		  checkResultAcceptApplication(app, ms1);

	    		}
	         
	            private void checkResultAcceptApplication(Application app, ManagementSystem ms2){
	      	
	    		  assertEquals(1, ms2.getApplicants().size());
	    		  assertEquals(1, ms2.getJobPostings().size());
	    		  assertEquals(1, ms2.getCourses().size());
	    		  assertEquals(1, ms2.getInstructors().size());
	    		  
	    		  assertEquals("Accepted", ms2.getApplicant(0).getApplication(0).getApplicationStatus());
	    		}
	            
	            @org.junit.Test
 
	            public void testRejectApplication(){
	            	assertEquals(0, ms.getApplicants().size()); 
	            	
	            	Applicant ap = new Applicant(111, "abc", "", true, "", "","", "", "", "", 10, ms);
	                ms.addApplicant(ap);
	        		assertEquals(1, ms.getApplicants().size());
	        		
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
	                
	                Application app = new Application("Submitted", jp,ap);
	         		ap.addApplication(app);

	  	   		  TController tc = new TController(ms);
	        		 tc.rejectApplication(app);
	        		  
	        		  ManagementSystem ms1 = ms;
	        		  checkResultRejectApplication(app, ms1);

	        		}
	             
	                private void checkResultRejectApplication(Application app, ManagementSystem ms2){
	          	
	        		  assertEquals(1, ms2.getApplicants().size());
	        		  assertEquals(1, ms2.getJobPostings().size());
	        		  assertEquals(1, ms2.getCourses().size());
	        		  assertEquals(1, ms2.getInstructors().size());
	        		  
	        		  assertEquals("Rejected", ms2.getApplicant(0).getApplication(0).getApplicationStatus());
	        		}
	}

