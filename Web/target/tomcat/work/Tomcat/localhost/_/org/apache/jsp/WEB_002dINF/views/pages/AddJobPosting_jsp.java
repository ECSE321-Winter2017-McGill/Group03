/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-03-31 05:58:02 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class AddJobPosting_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<meta name=\"description\" content=\"\">\r\n");
      out.write("<meta name=\"author\" content=\"\">\r\n");
      out.write("<title>DashBoard</title>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js\"></script>\r\n");
      out.write("<link href=\"../css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\">\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id=\"wrapper\">\r\n");
      out.write("\t\t<div class=\"navbar-default sidebar\" role=\"navigation\">\r\n");
      out.write("\t\t\t<div class=\"sidebar-nav navbar-collapse\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"navbar-header\">\r\n");
      out.write("\t\t\t\t\t<a class=\"navbar-brand\" href=\"dashboard.php\">TAMAS</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<h6 class=\"welcome\">Welcome ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</h6>\r\n");
      out.write("\t\t\t\t<h6 class=\"welcome\">\r\n");
      out.write("\t\t\t\t\t<a href=\"logout.php\">Sign Out</a>\r\n");
      out.write("\t\t\t\t</h6>\r\n");
      out.write("\t\t\t\t<ul class=\"nav\" id=\"side-menu\">\r\n");
      out.write("\t\t\t\t\t<li><a href=\"Dashboard.do\"><i\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"fa fa-dashboard fa-fw\"></i> Dashboard</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"ViewAllJobPosting.jsp\"><i\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"fa fa-dashboard fa-fw\"></i> View Job Postings</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"EvalTa.jsp\"><i class=\"fa fa-dashboard fa-fw\"></i>\r\n");
      out.write("\t\t\t\t\t\t\tTA Evaluaion</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"viewAllApplication.jsp\"><i\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"fa fa-dashboard fa-fw\"></i> View Application</a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"page-wrapper\">\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-12\">\r\n");
      out.write("\t\t\t\t\t<h1 class=\"page-header\">Add Job Postings</h1>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"mydiv\" class=\"row\">\r\n");
      out.write("\t\t\t\t<div class=\"box box-info col-lg-12\">\r\n");
      out.write("\t\t\t\t\t<div class=\"box-header with-border\"></div>\r\n");
      out.write("\t\t\t\t\t<div class=\"box-body\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<form id=\"addjob\">\r\n");
      out.write("\t\t\t\t\t\t\t<label>Select a course:</label> <span class=\"error\"> </span> <select\r\n");
      out.write("\t\t\t\t\t\t\t\tname=\"course\" class=\"form-control\" id=\"\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${courses}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</select> <br>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label>Job Title: </label> <span class=\"error\"> </span> <select\r\n");
      out.write("\t\t\t\t\t\t\t\t\tname=\"jobTitle\" class=\"form-control\" id=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<option value=\"TA\">TA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<option value=\"Grader\">Grader</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t</p>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label>Dead Line: </label> <input class=\"form-control\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttype=\"date\" name=\"deadLine\" value=\"\" /> <span class=\"error\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label>Perferred Experience: </label>\r\n");
      out.write("\t\t\t\t\t\t\t\t<textarea class=\"form-control\" name=\"perferredExperience\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t\t\t<span class=\"error\"> </span>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label>Hourly Rate: </label> <input class=\"form-control\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttype=\"text\" name=\"hourlyRate\" value=\"\" /> <span class=\"error\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input class=\"btn btn-sm btn-info btn-flat pull-left\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttype=\"submit\" value='Publish' />\r\n");
      out.write("\t\t\t\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<br> <br> <br>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\t$(\"#addjob\").submit(function(e) {\r\n");
      out.write("\t\t\tvar url = \"AddJobPosting.do\";\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\turl : url,\r\n");
      out.write("\t\t\t\tdata : $(\"#addjob\").serialize(),\r\n");
      out.write("\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\tif (data.search(\"success\") != -1) {\r\n");
      out.write("\t\t\t\t\t\twindow.location.replace('viewAllJobPostings.php');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$(\"#mydiv\").load(location.href + \" #mydiv\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
