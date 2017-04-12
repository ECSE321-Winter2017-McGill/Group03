package ca.mcgill.ecse321.TAMAS.Web.Controller;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.DBmanager;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

@WebServlet(urlPatterns = "/login.do")

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -609435102250998676L;

	protected void doGet(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(

				request, response);

	}

	protected void doPost(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {

		DBmanager.writeFile(DBmanager.getDB());
		String fileName = "output/data.xml";
		@SuppressWarnings("unused")
		final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
		String name = request.getParameter("username");

		String password = request.getParameter("password");

		request.getSession().setAttribute("name", name);
		if (password.equals("password")) {

			request.getSession().setAttribute("name", name);
			request.getRequestDispatcher("/WEB-INF/views/pages/dashboard.jsp").forward(request, response);

		} else {

			request.setAttribute("errorMessage", "Invalid Credentials!");

			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(

					request, response);

		}

	}

}