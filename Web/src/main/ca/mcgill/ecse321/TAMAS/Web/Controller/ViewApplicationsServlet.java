package ca.mcgill.ecse321.TAMAS.Web.Controller;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.DBmanager;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

@WebServlet(urlPatterns = "/viewAllApplication.jsp")

public class ViewApplicationsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {
		String fileName = "output/data.xml";
		DBmanager.writeFile(DBmanager.getDB());
		final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
		String result = "";
		// <th>Applicant Name</th>
		// <th>U/G</th>
		// <th>Job Title</th>
		// <th>Course Code</th>
		// <th>Application Status</th>

		for (Applicant app : ms.getApplicants()) {
			for (Application apt : app.getApplications()) {
				result += "<tr>";
				result += "<td><span class='label label-info'>" + apt.getJobPosting().getJobTitle() + "</span></td>";
				result += "<td>" + app.getName() + "</td>";
				// result += "<td><span class='label label-success'>
				// Grader</span></td>";
				if (app.getIsUnderGraduated()) {
					result += "<td>" + "UnderGraduate" + " </td>";
				} else {
					result += "<td>" + "Graduate" + " </td>";
				}
				result += "<td>" + apt.getJobPosting().getJobTitle() + " </td>";
				result += "<td>" + apt.getJobPosting().getCourse().getCourseCode() + " </td>";
				String s = apt.getStatus().toString();
				if (s.equals("Submitted")) {
					result += "<td><span class='label label-info'>" + "Submitted" + "</span></td>";
				} else if (s.equals("Accpeted")||s.equals("Accepted")) {
					result += "<td><span class='label label-success'>" + "Accepted" + "</span></td>";
				} else if (s.equals("Rejected")) {
					result += "<td><span class='label label-danger'>" + "Rejected" + "</span></td>";
				}else{
					result += "<td><span class='label label-danger'>" + s+ "</span></td>";
				}
				//result += "<td>" + apt.getApplicationStatus() + "</td>";
				result += "</tr>";
			}
		}

		// <option value="COMP 251">COMP 251</option>
		// <option value="ECSE 321">ECSE 321</option>
		// <option value="ECSE 211">ECSE 211</option>
		//
		request.getSession().setAttribute("applications", result);

		request.getRequestDispatcher("/WEB-INF/views/pages/viewAllApplication.jsp").forward(

				request, response);

	}

}