package ca.mcgill.ecse321.TAMAS.Web.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.DBmanager;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

@WebServlet(urlPatterns = "/ViewAllJobPosting.jsp")

public class ViewJobPostingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ManagementSystem ms = new ManagementSystem();

	protected void doGet(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {

		// out=response.getWriter();
		String result = "";
		String fileName = "output/data.xml";
		DBmanager.writeFile(DBmanager.getDB());
		final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
		// System.out.println("aaaaa");
		for (JobPosting js : ms.getJobPostings()) {
			result += "<tr>";
			if (js.getJobTitle().equals("TA")) {
				result += "<td><span class='label label-info'> TA</span></td>";
			} else {
				result += "<td><span class='label label-success'> Grader</span></td>";
			}
			result += "<td>" + js.getCourse().getCourseCode() + " </td>";
			result += "<td>" + js.getCourse().getHourRequiredTa() + " </td>";
			result += "<td>" + js.getHourRate() + " </td>";
			result += "<td>" + js.getPerferredExperience() + " </td>";
			result += "<td>" + js.getSubmissionDeadline() + " </td>";
			result += "</tr>";
		}

		request.setAttribute("result", result);
		request.getRequestDispatcher("/WEB-INF/views/pages/ViewAllJobPosting.jsp").forward(

				request, response);

	}

}