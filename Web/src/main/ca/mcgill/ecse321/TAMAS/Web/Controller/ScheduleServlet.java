package ca.mcgill.ecse321.TAMAS.Web.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.sql.Date;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse321.TAMAS.controller.TController;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.DBmanager;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

@WebServlet(urlPatterns = "/Schedule.jsp")

public class ScheduleServlet extends HttpServlet {
	/**
	 * 
	 */
//	private static XStream xstream = new XStream();
//	private static final long serialVersionUID = 1L;
//	HashMap<String, Course> cmap = new HashMap<String, Course>();

	protected void doGet(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {
		
//		String fileName = "output/data.xml";
//		final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
//
//		String courses = "";
//
//		for (Course c : ms.getCourses()) {
//			String key = c.getCourseCode();
//			cmap.put(key, c);
//			courses += "<option value=" + key + ">" + key + "</option>";
//
//		}
//
		String a="<script>$(document).ready(function() {$('#calendar').fullCalendar({defaultDate: '2017-04-12',editable: false,eventLimit: true,events: [{title:'a',start: '2017-04-01'}]});});</script>";
		request.getSession().setAttribute("test", a);
//
		request.getRequestDispatcher("/WEB-INF/views/pages/ViewSchedule.jsp").forward(

				request, response);

	}
	protected void doPost(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {

		String cname = request.getParameter("event");
		System.out.println(cname);
		request.getRequestDispatcher("/WEB-INF/views/pages/ViewSchedule.jsp").forward(

				request, response);
	}

}