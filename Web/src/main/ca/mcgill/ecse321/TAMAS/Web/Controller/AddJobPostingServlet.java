package ca.mcgill.ecse321.TAMAS.Web.Controller;

import ca.mcgill.ecse321.TAMAS.controller.TController;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.DBmanager;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;
import com.thoughtworks.xstream.XStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@WebServlet(urlPatterns = "/AddJobPosting.jsp")

public class AddJobPostingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private static XStream xstream = new XStream();
    HashMap<String, Course> cmap = new HashMap<String, Course>();
    private String error = "";

    public static String objToXML(Object obj) {
        xstream.setMode(XStream.ID_REFERENCES);
        String xml = xstream.toXML(obj); // save our xml file
        return xml;

    }

    protected void doGet(HttpServletRequest request,

                         HttpServletResponse response) throws ServletException, IOException {

        String fileName = "output/data.xml";

        // error = "";
        final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);

        String courses = "";

        for (Course c : ms.getCourses()) {
            String key = c.getCourseCode();
            cmap.put(key, c);
            courses += "<option value=" + key + ">" + key + "</option>";

        }

        request.getSession().setAttribute("courses", courses);
        request.setAttribute("error", error);
        request.getRequestDispatcher("/WEB-INF/views/pages/AddJobPosting.jsp").forward(

                request, response);

    }

    @SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request,

                          HttpServletResponse response) throws ServletException, IOException {
        String fileName = "output/data.xml";
        final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
        error = "";
        @SuppressWarnings("unused")
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String jobPosition = request.getParameter("jobTitle");

        String dl = request.getParameter("deadLine");
        Date deadlineDate = null;
        double hourlyRate = -1.0;
        try {
            deadlineDate = new Date(Integer.parseInt(dl.substring(0, 3)), Integer.parseInt(dl.substring(5, 6)),
                    Integer.parseInt(dl.substring(8, 9)));
            hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));

        } catch (Exception e) {
            error = "Non Valid Date";
        }
        for (Course c : ms.getCourses()) {
            String key = c.getCourseCode();
            cmap.put(key, c);
        }
        String exp = request.getParameter("perferredExperience");

        String cname = request.getParameter("course");
        System.out.println("cName+" + cname);
        System.out.println(cmap.containsKey(cname));
        Course aCourse = cmap.get(cname);
        //System.out.println("course aa"+aCourse.getCourseName());
        try {
            TController c = new TController(ms);
            c.createJobPosting(jobPosition, deadlineDate, exp, hourlyRate, aCourse);
            // PersistenceXStream.saveToXMLwithXStream(ms);
            DBmanager.updateDB(objToXML(ms));
        } catch (Exception e) {

            error = e.getMessage();
            System.out.println("eeeee" + error);
            request.setAttribute("error", error);

            request.getRequestDispatcher("/WEB-INF/views/pages/AddJobPosting.jsp").forward(

                    request, response);

            // e.printStackTrace();
        }
    }

}