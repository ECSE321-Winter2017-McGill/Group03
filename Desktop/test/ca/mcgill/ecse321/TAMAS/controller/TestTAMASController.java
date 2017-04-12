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
import ca.mcgill.ecse321.TAMAS.model.Application.Status;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class TestTAMASController {

	private ManagementSystem ms;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output" + File.separator + "data.xml");
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
		Applicant aApplicant = new Applicant(111, "abc", "", true, "", "", "", "", "", "", 10, ms);
		ms.addApplicant(aApplicant);
		assertEquals(1, ms.getApplicants().size());

		try {
			tc.createTAEval(aApplicant, evaluation, evaluation);

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
		Applicant aApplicant = new Applicant(111, "abc", "", true, "", "", "", "", "", "", 10, ms);
		ms.addApplicant(aApplicant);
		assertEquals(1, ms.getApplicants().size());
		TamasController tc = new TamasController(ms);
		try {
			tc.createTAEval(aApplicant, evaluation, evaluation);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Can not submit an empty Evaluation ", error);
	}

	@Test
	public void testCreateJobPosting() {
		assertEquals(0, ms.getJobPostings().size()); // import Assert from the
														// `org.junit` package
		String jobTitle = "ta";

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		Date date = new Date(c.getTimeInMillis());

		String exp = "good";
		double hourlyRate = 10.1;
		Instructor aInstructor = new Instructor("Danial", ms);
		ms.addInstructor(aInstructor);
		assertEquals(1, ms.getInstructors().size());

		Course aCourse = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10, 10, 10, 10, 10, 10.0, aInstructor, ms);
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

	private void checkResultJobPositing(String jobTitle, Date deadline, String exp, double hourRate,
			ManagementSystem ms2) {
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

		Course aCourse = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10, 10, 10, 10, 10, 10.0, aInstructor, ms);
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

		Course aCourse = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10, 10, 10, 10, 10, 10.0, aInstructor, ms);
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

		Course aCourse = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10, 10, 10, 10, 10, 10.0, aInstructor, ms);
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

		Course c = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10, 10, 10, 10, 10, 10.0, i, ms);
		ms.addCourse(c);
		assertEquals(1, ms.getCourses().size());

		JobPosting jp = new JobPosting("", null, "", 10.1, ms, c);
		ms.addJobPosting(jp);
		assertEquals(1, ms.getJobPostings().size());

		String name = "abc";
		String id = "111";
		String major = "a";
		boolean isUndergrad = true;
		String year = "2111";
		String exp = "no";
		String firstChoice = "no";
		String secondChoice = "no";
		String thirdChoice = "no";
		int totalAppointmentHour = 10;

		TamasController tc = new TamasController(ms);
		try {
			tc.createApplication(jp, name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice,
					totalAppointmentHour);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ManagementSystem ms1 = ms;
		checkResultApplication(name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice,
				totalAppointmentHour, ms1);

		ManagementSystem ms2 = (ManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
		checkResultApplication(name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice,
				totalAppointmentHour, ms);

		ms2.delete();
	}

	private void checkResultApplication(String name, String id, String major, boolean isUndergrad, String year, String exp,
			String firstChoice, String secondChoice, String thirdChoice, int totalAppointmentHour,
			ManagementSystem ms2) {

		assertEquals(1, ms2.getApplicants().size());
		assertEquals(1, ms2.getJobPostings().size());
		assertEquals(1, ms2.getCourses().size());
		assertEquals(1, ms2.getInstructors().size());
		assertEquals(1, ms2.getApplicant(0).getApplications().size());

		assertEquals(name, ms2.getApplicant(0).getName());
		assertEquals(id, ""+ ms2.getApplicant(0).getStudentID());
		assertEquals(major, ms2.getApplicant(0).getMajor());
		assertEquals(isUndergrad, ms2.getApplicant(0).getIsUnderGraduated());
		assertEquals(year, ms2.getApplicant(0).getYear());
		assertEquals(exp, ms2.getApplicant(0).getPreviousExperience());
		assertEquals(firstChoice, ms2.getApplicant(0).getFirstChoice());
		assertEquals(secondChoice, ms2.getApplicant(0).getSecondChoice());
		assertEquals(thirdChoice, ms2.getApplicant(0).getThirdChoice());
		assertEquals(totalAppointmentHour, ms2.getApplicant(0).getTotalAppointmentHours());
		assertEquals("PENDING", ms2.getApplicant(0).getApplication(0).getStatus().toString());
	}

	@Test
	public void testCreateApplicationGreaterThanThree() {
		assertEquals(0, ms.getApplicants().size());

		Instructor i = new Instructor("Danial", ms);
		ms.addInstructor(i);
		assertEquals(1, ms.getInstructors().size());

		Course c = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10, 10, 10, 10, 10, 10.0, i, ms);

		ms.addCourse(c);
		assertEquals(1, ms.getCourses().size());

		JobPosting jp = new JobPosting("", null, "", 10.1, ms, c);
		ms.addJobPosting(jp);
		assertEquals(1, ms.getJobPostings().size());

		String error = null;
		String name = "abc";
		String id = "111";
		int idNum = 111;
		String major = "a";
		boolean isUndergrad = true;
		String year = "2111";
		String exp = "no";
		String firstChoice = "no";
		String secondChoice = "no";
		String thirdChoice = "no";
		int totalAppointmentHour = 10;

		Applicant ap = new Applicant(idNum, name, exp, isUndergrad, major, year, firstChoice, secondChoice, thirdChoice,
				"", totalAppointmentHour, ms);
		assertEquals(1, ms.getApplicants().size());

		Application app1 = new Application(1,jp, ap);
		Application app2 = new Application(1,jp, ap);
		Application app3 = new Application(1,jp, ap);

		ap.addApplication(app1);
		ap.addApplication(app2);
		ap.addApplication(app3);

		TamasController tc = new TamasController(ms);
		try {
			tc.createApplication(jp, name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice,
					totalAppointmentHour);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		error = error.trim();
		assertEquals("You cannot submit more applications!", error);

		assertEquals(3, ms.getApplicant(0).getApplications().size());
	}
	
	@Test
	public void testCreateApplicant() {
		assertEquals(0, ms.getApplicants().size());

		String name = "Kaiyue";
		String id = "111";
		String major = "CE";
		boolean isUndergrad = true;
		String year = "2111";
		String exp = "no";
		String firstChoice = "no";
		String secondChoice = "no";
		String thirdChoice = "no";
		int totalAppointmentHour = 10;

		TamasController tc = new TamasController(ms);
		try {
			tc.createApplicant(name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice,
					totalAppointmentHour);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ManagementSystem ms1 = ms;
		checkResultApplicant(name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice,
				totalAppointmentHour, ms1);

		ManagementSystem ms2 = (ManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
		checkResultApplicant(name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice,
				totalAppointmentHour, ms2);

	}

	private void checkResultApplicant(String name, String id, String major, boolean isUndergrad, String year, String exp,
			String firstChoice, String secondChoice, String thirdChoice, int totalAppointmentHour,
			ManagementSystem ms2) {

		assertEquals(1, ms2.getApplicants().size());
		assertEquals(0, ms2.getJobPostings().size());
		assertEquals(0, ms2.getCourses().size());
		assertEquals(0, ms2.getInstructors().size());
		assertEquals(0, ms2.getApplicant(0).getApplications().size());

		assertEquals(name, ms2.getApplicant(0).getName());
		assertEquals(id, ms2.getApplicant(0).getStudentID()+"");
		assertEquals(major, ms2.getApplicant(0).getMajor());
		assertEquals(isUndergrad, ms2.getApplicant(0).getIsUnderGraduated());
		assertEquals(year, ms2.getApplicant(0).getYear());
		assertEquals(exp, ms2.getApplicant(0).getPreviousExperience());
		assertEquals(firstChoice, ms2.getApplicant(0).getFirstChoice());
		assertEquals(secondChoice, ms2.getApplicant(0).getSecondChoice());
		assertEquals(thirdChoice, ms2.getApplicant(0).getThirdChoice());
		assertEquals(totalAppointmentHour, ms2.getApplicant(0).getTotalAppointmentHours());
	}
	
	@Test
	public void testCreateApplicationNullName() {
		assertEquals(0, ms.getApplicants().size());

		String error = null;
		String name = null;
		String id = "123";
		String major = "a";
		boolean isUndergrad = true;
		String year = "2111";
		String exp = "no";
		String firstChoice = "no";
		String secondChoice = "no";
		String thirdChoice = "no";
		int totalAppointmentHour = 10;

		TamasController tc = new TamasController(ms);
		try {
			tc.createApplicant( name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice,
					totalAppointmentHour);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();
		assertEquals("Name cannot be empty!", error);

		assertEquals(0, ms.getApplicants().size());

	}
	
	@Test
	public void testCreateApplicantInvalidID() {
		assertEquals(0, ms.getApplicants().size());

		String error = null;
		String name = "abc";
		String id = "-1";
		String major = "a";
		boolean isUndergrad = true;
		String year = "2111";
		String exp = "no";
		String firstChoice = "no";
		String secondChoice = "no";
		String thirdChoice = "no";
		int totalAppointmentHour = 10;

		TamasController tc = new TamasController(ms);
		try {
			tc.createApplicant(name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice,
					totalAppointmentHour);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();
		assertEquals("You must input a valid id!", error);

		assertEquals(0, ms.getApplicants().size());

	}
	
	@Test
	public void testCreateApplicantNullMajor() {
		assertEquals(0, ms.getApplicants().size());

		String error = null;
		String name = "abc";
		String id = "112";
		String major = null;
		boolean isUndergrad = true;
		String year = "2111";
		String exp = "no";
		String firstChoice = "no";
		String secondChoice = "no";
		String thirdChoice = "no";
		int totalAppointmentHour = 10;

		TamasController tc = new TamasController(ms);
		try {
			tc.createApplicant(name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice,
					totalAppointmentHour);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();
		assertEquals("Major cannot be empty!", error);

		assertEquals(0, ms.getApplicants().size());

	}
	
	@Test
	public void testCreateApplicantNullExp() {
		assertEquals(0, ms.getApplicants().size());

		String error = null;
		String name = "abc";
		String id = "112";
		String major = "CE";
		boolean isUndergrad = true;
		String year = "2111";
		String exp = null;
		String firstChoice = "no";
		String secondChoice = "no";
		String thirdChoice = "no";
		int totalAppointmentHour = 10;

		TamasController tc = new TamasController(ms);
		try {
			tc.createApplicant(name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice,
					totalAppointmentHour);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();
		assertEquals("Past experience cannot be empty!", error);

		assertEquals(0, ms.getApplicants().size());

	}

	@Test
	public void testRegisterApplicant() {
		assertEquals(0, ms.getApplicants().size());

		TamasController tc = new TamasController(ms);
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

		ManagementSystem ms2 = (ManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
		checkResultRegisterApplicant("a", ms2, 0);
		checkResultRegisterApplicant("b", ms2, 1);
		checkResultRegisterApplicant("c", ms2, 2);

		ms2.delete();
	}

	private void checkResultRegisterApplicant(String name, ManagementSystem ms2, int index) {

		assertEquals(3, ms2.getApplicants().size());
		assertEquals(0, ms2.getJobPostings().size());
		assertEquals(0, ms2.getCourses().size());
		assertEquals(0, ms2.getInstructors().size());

		assertEquals(name, ms2.getApplicant(index).getName());
	}

	@Test
	public void testRegisterApplicantNullName() {
		assertEquals(0, ms.getApplicants().size());

		String name = null;
		String error = null;

		TamasController tc = new TamasController(ms);
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

	@Test
	public void testRegisterApplicantDupUserAppName() {
		assertEquals(0, ms.getApplicants().size());

		Applicant ap1 = new Applicant(111, "a", "", true, "", "", "", "", "", "", 10, ms);
		new Applicant(111, "b", "", true, "", "", "", "", "", "", 10, ms);
		new Applicant(111, "c", "", true, "", "", "", "", "", "", 10, ms);

		assertEquals(3, ms.getApplicants().size());

		String error = null;

		TamasController tc = new TamasController(ms);
		try {
			tc.registerApplicant(ap1.getName());

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		error = error.trim();
		assertEquals("This username already exists!", error);

		assertEquals(3, ms.getApplicants().size());
	}

	@Test
	public void testRegisterApplicantDupUserInstName() {
		assertEquals(0, ms.getApplicants().size());

		Instructor Inst1 = new Instructor("a", ms);
		new Instructor("b", ms);
		new Instructor("c", ms);

		assertEquals(3, ms.getInstructors().size());

		String error = null;

		TamasController tc = new TamasController(ms);
		try {
			tc.registerApplicant(Inst1.getName());

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		error = error.trim();
		assertEquals("This username already exists!", error);

		assertEquals(3, ms.getInstructors().size());
	}

	@Test
	public void testCreateCourse() {
		assertEquals(0, ms.getCourses().size());

		String semester = "Winter3000";
		String courseName = "ECSE";
		String courseCode = "232323";
		String credit = "10";
		String maxStudent = "1";
		String instructorName = "Gates";
		String numGraderNeeded = "0";
		String numLab = "0";
		String numTut = "0";
		String labHour = "0";
		String tutHour = "0";
		String totalGraderHour = "10";

		TamasController tc = new TamasController(ms);
		try {
			tc.createCourse(semester, courseName, courseCode, credit, maxStudent, instructorName, numGraderNeeded,
					numLab, numTut, labHour, tutHour, totalGraderHour);

		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ManagementSystem ms1 = ms;
		checkResultCourse(semester, courseName, courseCode, credit, maxStudent, instructorName, numGraderNeeded, numLab,
				numTut, labHour, tutHour, totalGraderHour, ms1);

		ManagementSystem ms2 = (ManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
		checkResultCourse(semester, courseName, courseCode, credit, maxStudent, instructorName, numGraderNeeded, numLab,
				numTut, labHour, tutHour, totalGraderHour, ms);
		ms2.delete();
	}

	private void checkResultCourse(String semester, String courseName, String courseCode, String credit, String maxStudent,
			String instructorName, String numGraderNeeded, String numLab, String numTut, String labHour, String tutHour,
			String totalGraderHour, ManagementSystem ms2) {

		assertEquals(0, ms2.getApplicants().size());
		assertEquals(0, ms2.getJobPostings().size());
		assertEquals(1, ms2.getCourses().size());
		assertEquals(1, ms2.getInstructors().size());

		assertEquals(semester, ms2.getCourse(0).getSemester());
		assertEquals(courseName, ms2.getCourse(0).getCourseName()+"");
		assertEquals(courseCode, ms2.getCourse(0).getCourseCode());
		assertEquals(credit, ms2.getCourse(0).getCredit()+"");
		assertEquals(maxStudent, ms2.getCourse(0).getNumStudent()+"");
		assertEquals(instructorName, ms2.getCourse(0).getInstructor().getName());
		assertEquals(numGraderNeeded, ms2.getCourse(0).getNumGraderNeeded()+"");
		assertEquals(numLab, ms2.getCourse(0).getNumLab()+"");
		assertEquals(numTut, ms2.getCourse(0).getNumTutorial()+"");
		assertEquals(labHour, ms2.getCourse(0).getLabHour()+"");
		assertEquals(tutHour, ms2.getCourse(0).getTutorialHour()+"");
		assertEquals(totalGraderHour, ms2.getCourse(0).getTotalGraderHour()+"");
	}

	@Test
	public void testCreateCourseNullCourseName() {
		assertEquals(0, ms.getCourses().size());

		String error = null;

		String semester = "Winter3000";
		String courseName = null;
		String courseCode = "232323";
		String credit = "10";
		String maxStudent = "1";
		String instructorName = "Gates";
		String numGraderNeeded = "0";
		String numLab = "0";
		String numTut = "0";
		String labHour = "0";
		String tutHour = "0";
		String totalGraderHour = "10";

		TamasController tc = new TamasController(ms);
		try {
			tc.createCourse(semester, courseName, courseCode, credit, maxStudent, instructorName, numGraderNeeded,
					numLab, numTut, labHour, tutHour, totalGraderHour);

		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();
		assertEquals("Please specify the course name!", error);

		assertEquals(0, ms.getCourses().size());
	}

	@Test
	public void testCreateCourseNullCourseCode() {
		assertEquals(0, ms.getCourses().size());

		String error = null;

		String semester = "Winter3000";
		String courseName = "ECSE";
		String courseCode = "";
		String credit = "10";
		String maxStudent = "1";
		String instructorName = "Gates";
		String numGraderNeeded = "0";
		String numLab = "0";
		String numTut = "0";
		String labHour = "0";
		String tutHour = "0";
		String totalGraderHour = "10";

		TamasController tc = new TamasController(ms);
		try {
			tc.createCourse(semester, courseName, courseCode, credit, maxStudent, instructorName, numGraderNeeded,
					numLab, numTut, labHour, tutHour, totalGraderHour);

		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();
		assertEquals("Please specify the course code!", error);

		assertEquals(0, ms.getCourses().size());
	}

	@Test
	public void testCreateCourseInvalidCredit() {
		assertEquals(0, ms.getCourses().size());

		String error = null;

		String semester = "Winter3000";
		String courseName = "ECSE";
		String courseCode = "232323";
		String credit = "-1";
		String maxStudent = "100";
		String instructorName = "Gates";
		String numGraderNeeded = "0";
		String numLab = "0";
		String numTut = "0";
		String labHour = "0";
		String tutHour = "0";
		String totalGraderHour = "10";

		TamasController tc = new TamasController(ms);
		try {
			tc.createCourse(semester, courseName, courseCode, credit, maxStudent, instructorName, numGraderNeeded,
					numLab, numTut, labHour, tutHour, totalGraderHour);

		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();
		assertEquals("Please specify the number of credits in the correct format!", error);

		assertEquals(0, ms.getCourses().size());
	}

	@Test
	public void testCreateCourseInvalidMaxStudent() {
		assertEquals(0, ms.getCourses().size());

		String error = null;

		String semester = "Winter3000";
		String courseName = "ECSE";
		String courseCode = "232323";
		String credit = "10";
		String maxStudent = "-1";
		String instructorName = "Gates";
		String numGraderNeeded = "0";
		String numLab = "0";
		String numTut = "0";
		String labHour = "0";
		String tutHour = "0";
		String totalGraderHour = "10";

		TamasController tc = new TamasController(ms);
		try {
			tc.createCourse(semester, courseName, courseCode, credit, maxStudent, instructorName, numGraderNeeded,
					numLab, numTut, labHour, tutHour, totalGraderHour);

		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();
		assertEquals("Please specify the maximum number of students in the correct format!", error);

		assertEquals(0, ms.getCourses().size());
	}

	@Test
	public void testCreateCourseNullInstructor() {
		assertEquals(0, ms.getCourses().size());

		String error = null;

		String semester = "Winter3000";
		String courseName = "ECSE";
		String courseCode = "232323";
		String credit = "10";
		String maxStudent = "1";
		String instructorName = null;
		String numGraderNeeded = "0";
		String numLab = "0";
		String numTut = "0";
		String labHour = "0";
		String tutHour = "0";
		String totalGraderHour = "10";

		TamasController tc = new TamasController(ms);
		try {
			tc.createCourse(semester, courseName, courseCode, credit, maxStudent, instructorName, numGraderNeeded,
					numLab, numTut, labHour, tutHour, totalGraderHour);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();
		assertEquals("Please specify the instructor!", error);

		assertEquals(0, ms.getCourses().size());
	}

	@Test
	public void testCreateCourseInvalidNumLab() {
		assertEquals(0, ms.getCourses().size());

		String error = null;
		String realError = ("Please specify the numebr of lab sessions in the correct format!").trim();

		String semester = "Winter3000";
		String courseName = "ECSE";
		String courseCode = "232323";
		String credit = "10";
		String maxStudent = "1";
		String instructorName = "Gates";
		String numGraderNeeded = "0";
		String numLab = "-1";
		String numTut = "1";
		String labHour = "0";
		String tutHour = "0";
		String totalGraderHour = "10";

		TamasController tc = new TamasController(ms);
		try {
			tc.createCourse(semester, courseName, courseCode, credit, maxStudent, instructorName, numGraderNeeded,
					numLab, numTut, labHour, tutHour, totalGraderHour);

		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();

		assertEquals(realError, error);

		assertEquals(0, ms.getCourses().size());
	}
	
	@Test
	public void testCreateCourseInvalidNumTut() {
		assertEquals(0, ms.getCourses().size());

		String error = null;
		String realError = ("Please specify the number of tutorials in the correct format!").trim();

		String semester = "Winter3000";
		String courseName = "ECSE";
		String courseCode = "232323";
		String credit = "10";
		String maxStudent = "1";
		String instructorName = "Gates";
		String numGraderNeeded = "0";
		String numLab = "1";
		String numTut = "-1";
		String labHour = "0";
		String tutHour = "0";
		String totalGraderHour = "10";

		TamasController tc = new TamasController(ms);
		try {
			tc.createCourse(semester, courseName, courseCode, credit, maxStudent, instructorName, numGraderNeeded,
					numLab, numTut, labHour, tutHour, totalGraderHour);

		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();

		assertEquals(realError, error);

		assertEquals(0, ms.getCourses().size());
	}

	@Test
	public void testCreateCourseInvalidNumGraderNeeded() {
		assertEquals(0, ms.getCourses().size());

		String error = null;
		String realError = ("Please specify the number of Grader needed in the correct format! ").trim();

		String semester = "Winter3000";
		String courseName = "ECSE";
		String courseCode = "232323";
		String credit = "10";
		String maxStudent = "1";
		String instructorName = "Gates";
		String numGraderNeeded = "-1";
		String numLab = "0";
		String numTut = "0";
		String labHour = "0";
		String tutHour = "0";
		String totalGraderHour = "10";

		TamasController tc = new TamasController(ms);
		try {
			tc.createCourse(semester, courseName, courseCode, credit, maxStudent, instructorName, numGraderNeeded,
					numLab, numTut, labHour, tutHour, totalGraderHour);

		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();

		assertEquals(realError, error);

		assertEquals(0, ms.getCourses().size());
	}

	@Test
	public void testCreateCourseInvalidHourTAndGrader() {
		assertEquals(0, ms.getCourses().size());

		String error = null;
		String realError = ("Please specify a correct lab hour! " + "Please specify a correct tutorial hour! ").trim();

		String semester = "Winter3000";
		String courseName = "ECSE";
		String courseCode = "232323";
		String credit = "10";
		String maxStudent = "1";
		String instructorName = "Gates";
		String numGraderNeeded = "0";
		String numLab = "0";
		String numTut = "0";
		String labHour = "-1";
		String tutHour = "-1";
		String totalGraderHour = "10";
		TamasController tc = new TamasController(ms);
		try {
			tc.createCourse(semester, courseName, courseCode, credit, maxStudent, instructorName, numGraderNeeded,
					numLab, numTut, labHour, tutHour, totalGraderHour);

		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		error = error.trim();

		assertEquals(realError, error);

		assertEquals(0, ms.getCourses().size());
	}

	@Test
	public void testAcceptApplication() {
		assertEquals(0, ms.getApplicants().size());

		Applicant ap = new Applicant(111, "abc", "", true, "", "", "", "", "", "", 10, ms);
		ms.addApplicant(ap);
		assertEquals(1, ms.getApplicants().size());

		Instructor i = new Instructor("Danial", ms);
		ms.addInstructor(i);
		assertEquals(1, ms.getInstructors().size());

		Course c = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10, 10, 10, 10, 10, 10.0, i, ms);
		ms.addCourse(c);
		assertEquals(1, ms.getCourses().size());

		JobPosting jp = new JobPosting("", null, "", 10.1, ms, c);
		ms.addJobPosting(jp);
		assertEquals(1, ms.getJobPostings().size());

		Application app = new Application(1, jp, ap);
		app.setStatus(Status.PENDING);
		ap.addApplication(app);

		new TamasController(ms);

		ManagementSystem ms1 = ms;
		checkResultAcceptApplication(app, ms1);

		ManagementSystem ms2 = (ManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
		checkResultAcceptApplication(app, ms2);

		ms2.delete();
	}


	private void checkResultAcceptApplication(Application app, ManagementSystem ms2) {

		assertEquals(1, ms2.getApplicants().size());
		assertEquals(1, ms2.getJobPostings().size());
		assertEquals(1, ms2.getCourses().size());
		assertEquals(1, ms2.getInstructors().size());

		assertEquals("OFFER_ACCEPTED", ms2.getApplicant(0).getApplication(0).getStatus().toString());
	}

	@Test
	public void testRejectApplication() {
		assertEquals(0, ms.getApplicants().size());

		Applicant ap = new Applicant(111, "abc", "", true, "", "", "", "", "", "", 10, ms);
		ms.addApplicant(ap);
		assertEquals(1, ms.getApplicants().size());

		Instructor i = new Instructor("Danial", ms);
		ms.addInstructor(i);
		assertEquals(1, ms.getInstructors().size());

		Course c = new Course("abc", "ecse", "321", 3, 10, 10, 10, 10, 10, 10, 10, 10, 10.0, i, ms);
		ms.addCourse(c);
		assertEquals(1, ms.getCourses().size());

		JobPosting jp = new JobPosting("", null, "", 10.1, ms, c);
		ms.addJobPosting(jp);
		assertEquals(1, ms.getJobPostings().size());

		Application app = new Application(1,jp, ap);
		app.setStatus(Status.PENDING);
		ap.addApplication(app);

		TamasController tc = new TamasController(ms);
		tc.rejectApplication(app);

		ManagementSystem ms1 = ms;
		checkResultRejectApplication(app, ms1);

		ManagementSystem ms2 = (ManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
		checkResultRejectApplication(app, ms2);

		ms2.delete();
	}

	private void checkResultRejectApplication(Application app, ManagementSystem ms2) {

		assertEquals(1, ms2.getApplicants().size());
		assertEquals(1, ms2.getJobPostings().size());
		assertEquals(1, ms2.getCourses().size());
		assertEquals(1, ms2.getInstructors().size());

		assertEquals("OFFER_DECLINED", ms2.getApplicant(0).getApplication(0).getStatus().toString());
	}

        
        @Test
        public void testRegisterInstructor() {
    		  assertEquals(0, ms.getInstructors().size());

    		  TamasController tc = new TamasController(ms);
    		  try {
    			tc.registerInstructor("a");
    			tc.registerInstructor("b");
    			tc.registerInstructor("c");

    		} catch (InvalidInputException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    		  ManagementSystem ms1 = ms;
    		  checkResultRegisterInstructor("a", ms1, 0);
    		  checkResultRegisterInstructor("b", ms1, 1);
    		  checkResultRegisterInstructor("c", ms1, 2);

    		  ManagementSystem ms2 = (ManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
    		  checkResultRegisterInstructor("a", ms2, 0);
    		  checkResultRegisterInstructor("b", ms2, 1);
    		  checkResultRegisterInstructor("c", ms2, 2);

    		  ms2.delete();
    		}

            private void checkResultRegisterInstructor( String name, ManagementSystem ms2, int index){

    		  assertEquals(3, ms2.getInstructors().size());
    		  assertEquals(0, ms2.getJobPostings().size());
    		  assertEquals(0, ms2.getCourses().size());
    		  assertEquals(0, ms2.getApplicants().size());

    		  assertEquals(name, ms2.getInstructor(index).getName());
    		}

            @Test
           public void testRegisterInstructorNullName() {
    		  assertEquals(0, ms.getInstructors().size());

    		  String name = null;
    		  String error = null;

    		  TamasController tc = new TamasController(ms);
    		  try {
    			tc.registerInstructor(name);

    		} catch (InvalidInputException e) {
    			// TODO Auto-generated catch block
    			error = e.getMessage();
    		}

    		  error = error.trim();
    		  assertEquals("Name cannot be empty!", error);

    		  assertEquals(0, ms.getInstructors().size());
    		}

            @Test
            public void testRegisterInstructorExistingApplicant() {
     		  assertEquals(0, ms.getInstructors().size());

     		  String error = null;
     		  Applicant ap1 = new Applicant(111, "a", "", true, "", "","", "", "", "", 10, ms);

     		  TamasController tc = new TamasController(ms);
     		  try {
     			tc.registerInstructor(ap1.getName());

     		} catch (InvalidInputException e) {
     			// TODO Auto-generated catch block
     			error = e.getMessage();
     		}

     		  error = error.trim();
     		  assertEquals("This username already exists!", error);

     		  assertEquals(0, ms.getInstructors().size());
     		}


            @Test
            public void testRegisterInstructorExistingInstructor() {
     		  assertEquals(0, ms.getInstructors().size());

     		  String error = null;
     		  Instructor Inst1 = new Instructor("a", ms);

     		  TamasController tc = new TamasController(ms);
     		  try {
     			tc.registerInstructor(Inst1.getName());

     		} catch (InvalidInputException e) {
     			// TODO Auto-generated catch block
     			error = e.getMessage();
     		}

     		  error = error.trim();
     		  assertEquals("This username already exists!", error);

     		  assertEquals(1, ms.getInstructors().size());
     		}

}
