package ca.mcgill.ecse321.TAMAS.Web.Controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.DBmanager;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

@WebServlet(urlPatterns = "/EvalTa.jsp")

public class EvalTAServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/pages/EvalTA.jsp").forward(

				request, response);

	}
	protected void doPost(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {
		String fileName = "output/data.xml";
		final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);

		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		String jobPosition = request.getParameter("jobTitle");

		String dl = request.getParameter("deadLine");

		Date deadlineDate = new Date(Integer.parseInt(dl.substring(0, 3)), Integer.parseInt(dl.substring(5, 6)),
				Integer.parseInt(dl.substring(8, 9)));

		String exp = request.getParameter("perferredExperience");
		double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));

		String cname = request.getParameter("course");
		System.out.println(cname);
		Course aCourse = cmap.get(cname);

		try {
			TamasController c = new TamasController(ms);
			c.createJobPosting(jobPosition, deadlineDate, exp, hourlyRate, aCourse);
			// PersistenceXStream.saveToXMLwithXStream(ms);
			DBmanager.updateDB(objToXML(ms));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}