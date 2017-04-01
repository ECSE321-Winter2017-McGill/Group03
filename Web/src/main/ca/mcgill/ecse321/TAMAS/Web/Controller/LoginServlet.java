package ca.mcgill.ecse321.TAMAS.Web.Controller;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse321.TAMAS.controller.TController;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.DBmanager;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

@WebServlet(urlPatterns = "/login.do")

public class LoginServlet extends HttpServlet {

	// private LoginService userValidationService = new LoginService();

	// private TodoService todoService = new TodoService();

	protected void doGet(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(

				request, response);

	}

	protected void doPost(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {

		DBmanager.writeFile(DBmanager.getDB());
		String fileName = "output/data.xml";
		final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
		String name = request.getParameter("username");

		String password = request.getParameter("password");
		boolean found = false;
		for (Applicant a : ms.getApplicants()) {
			if (a.getName().equals(name)) {
				found = true;
				String role = "applicant";
				request.getSession().setAttribute("role", role);
				break;
			}
		}
		for (Instructor a : ms.getInstructors()) {
			if (a.getName().equals(name)) {
				found = true;
				String role = "instructor";
				request.getSession().setAttribute("role", role);
				break;
			}
		}

		if (!found) {
			request.getSession().setAttribute("role", "department");
			found=true;
		}

		request.getSession().setAttribute("name", name);
		if (password.equals("t") && found) {

			request.getSession().setAttribute("name", name);
			request.getRequestDispatcher("/WEB-INF/views/pages/dashboard.jsp").forward(request, response);

		} else {

			request.setAttribute("errorMessage", "Invalid Credentials!");

			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(

					request, response);

		}

	}

}