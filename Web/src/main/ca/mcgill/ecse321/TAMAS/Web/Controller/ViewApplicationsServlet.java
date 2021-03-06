package ca.mcgill.ecse321.TAMAS.Web.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.DBmanager;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

@WebServlet(urlPatterns = "/viewAllApplication.jsp")

public class ViewApplicationsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2909744365295318220L;

	protected void doGet(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {
		String fileName = "output/data.xml";
		DBmanager.writeFile(DBmanager.getDB());
		final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
		String result = "";
		for (Applicant app : ms.getApplicants()) {
			for (Application apt : app.getApplications()) {
				result += "<tr>";
				if (apt.getJobPosting().getJobTitle().equals("TA")) {
					result += "<td><span class='label label-info'>" + apt.getJobPosting().getJobTitle()
							+ "</span></td>";
				} else {
					result += "<td><span class='label label-success'>" + apt.getJobPosting().getJobTitle()
							+ "</span></td>";
				}
				result += "<td>" + app.getName() + "</td>";
				if (app.getIsUnderGraduated()) {
					result += "<td>" + "UnderGraduate" + " </td>";
				} else {
					result += "<td>" + "Graduate" + " </td>";
				}
				result += "<td>" + apt.getJobPosting().getJobTitle() + " </td>";
				result += "<td>" + apt.getJobPosting().getCourse().getCourseCode() + " </td>";
				String s = apt.getStatus().toString();
				if (s.equals("pending") || s.equals("PENDING")) {
					result += "<td><span class='label label-warning'>" + "Pending" + "</span></td>";
				} else if (s.equals("OFFER_ACCEPTED") || s.equals("Accepted")) {
					result += "<td><span class='label label-success'>" + "Offer Accepted" + "</span></td>";
				} else if (s.equals("OFFER_DECLINED")) {
					result += "<td><span class='label label-danger'>" + "Offer Rejected" + "</span></td>";
				} else if (s.equals("SELECTED")) {
					result += "<td><span class='label label-info'>" + "Selected" + "</span></td>";
				} else {
					result += "<td><span class='label label-danger'>" + s + "</span></td>";
				}
				result += "</tr>";
			}
		}

		request.getSession().setAttribute("applications", result);

		request.getRequestDispatcher("/WEB-INF/views/pages/viewAllApplication.jsp").forward(

				request, response);

	}

}