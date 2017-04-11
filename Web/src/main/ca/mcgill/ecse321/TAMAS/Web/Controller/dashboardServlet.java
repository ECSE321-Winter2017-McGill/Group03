package ca.mcgill.ecse321.TAMAS.Web.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns="/")
public class dashboardServlet  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2284316088403230528L;

	protected void doGet(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// respond to http get request for Dashboard.do

		request.getRequestDispatcher("/WEB-INF/views/pages/dashboard.jsp").forward(

				request, response);

	}
}
