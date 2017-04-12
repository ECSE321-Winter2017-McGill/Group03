package ca.mcgill.ecse321.TAMAS.Web.Controller;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/Schedule.jsp")

public class ScheduleServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3293314311955723071L;
	/**
	 * 
	 */

	protected void doGet(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {
		

		String a="<script>$(document).ready(function() {$('#calendar').fullCalendar({defaultDate: '2017-04-12',editable: false,eventLimit: true,events: [{title:'a',start: '2017-04-01'}]});});</script>";
		request.getSession().setAttribute("test", a);
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