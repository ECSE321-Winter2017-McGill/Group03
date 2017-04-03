package ca.mcgill.ecse321.TAMAS.Web.Controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse321.TAMAS.controller.TController;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.DBmanager;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

@WebServlet(urlPatterns = "/EvalTa.jsp")

public class EvalTAServlet extends HttpServlet {
	private static XStream xstream = new XStream();
	HashMap<String, Applicant> map = new HashMap<String, Applicant>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {
		DBmanager.writeFile(DBmanager.getDB());
		String fileName = "output/data.xml";
		final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
		
		String TAs = "";
		String data = "";
		for (Applicant app : ms.getApplicants()) {
			//System.out.println(app.getName()+"eval:"+app.getEvaluation());
			if (app.getEvaluation()!=null&&app.getEvaluation()!="") {
				data += "<tr>";
				data += "<td>" + app.getName() + "</td>";
				data += "<td>" + app.getEvaluation() + "</td>";
				data += "</tr>";
			}
			String key = app.getName();
			map.put(key, app);
			TAs += "<option value=" + key + ">" + key + "</option>";
		}
		request.getSession().setAttribute("data", data);
		request.getSession().setAttribute("TAs", TAs);
		
		request.getRequestDispatcher("/WEB-INF/views/pages/EvalTA.jsp").forward(

				request, response);

	}

//	protected void doPost(HttpServletRequest request,
//
//			HttpServletResponse response) throws ServletException, IOException {
//		
//		String fileName = "output/data.xml";
//
//		String taName = request.getParameter("TA");
//		String Eval = request.getParameter("evaluation");
//
//		final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
//
//		try {
//			TController c = new TController(ms);
//			System.out.println(taName+"eval1:"+Eval);
//			c.createTAEval(taName, Eval);
//			System.out.println(taName+"eval2:"+Eval);
//			DBmanager.updateDB(objToXML(ms));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			
//			request.setAttribute("evalError", "evalError");
//			
//			request.getRequestDispatcher("/WEB-INF/views/AddEval.jsp").forward(
//					request, response);
//			e.printStackTrace();
//		}
//	}

	public static String objToXML(Object obj) {
		xstream.setMode(XStream.ID_REFERENCES);
		String xml = xstream.toXML(obj); // save our xml file
		return xml;

	}

}