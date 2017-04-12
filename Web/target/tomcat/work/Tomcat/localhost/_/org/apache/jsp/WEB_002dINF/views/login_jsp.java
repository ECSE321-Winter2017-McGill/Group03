/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-04-12 02:54:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("\n");
      out.write("\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=10.000\" />\n");
      out.write("\t<title>TAMAS Log In</title>\n");
      out.write("\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />\n");
      out.write("\t<style>\n");
      out.write("\t\t.illustrationClass {\n");
      out.write("\t\t\tbackground-image: url(./img/mcimg.jpg);\n");
      out.write("\t\t}\n");
      out.write("\t</style>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body dir=\"ltr\" class=\"body\">\n");
      out.write("\t<div id=\"fullPage\">\n");
      out.write("\t\t<div id=\"brandingWrapper\" class=\"float\">\n");
      out.write("\t\t\t<div id=\"branding\" class=\"illustrationClass\"></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div id=\"contentWrapper\" class=\"float\">\n");
      out.write("\t\t\t<p>\n");
      out.write("\t\t\t\t<br />\n");
      out.write("\t\t\t</p>\n");
      out.write("\t\t\t<div id=\"content\">\n");
      out.write("\t\t\t\t<div id=\"header\">\n");
      out.write("\t\t\t\t\t<img class=\"logoImage\" src=\"./img/mclogo.png\" alt=\"McGill University\" />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div id=\"workArea\">\n");
      out.write("\t\t\t\t\t<div id=\"authArea\" class=\"groupMargin\">\n");
      out.write("\t\t\t\t\t\t<div id=\"loginArea\">\n");
      out.write("\t\t\t\t\t\t\t<p>\n");
      out.write("\t\t\t\t\t\t\t\t<br /> <br />\n");
      out.write("\t\t\t\t\t\t\t</p>\n");
      out.write("\t\t\t\t\t\t\t<div id=\"loginMessage\" class=\"groupMargin\">TAMAS Log in:</div>\n");
      out.write("\t\t\t\t\t\t\t<form role=\"form\" action=\"/login.do\" method=\"post\" id=\"loginForm\" autocomplete=\"off\">\n");
      out.write("\t\t\t\t\t\t\t\t<div id=\"error\" class=\"fieldMargin error smallText\" style=\"display: none;\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label id=\"errorText\" for=\"\"></label>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<div id=\"formsAuthenticationArea\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<div id=\"userNameArea\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input id=\"userNameInput\" name=\"username\" type=\"text\" value=\"\" tabindex=\"1\" class=\"text fullWidth\" spellcheck=\"false\" placeholder=\"Instructor Name:\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\t autocomplete=\"off\" />\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t<div id=\"passwordArea\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input id=\"passwordInput\" name=\"password\" type=\"password\" tabindex=\"2\" class=\"text fullWidth\" placeholder=\"Default Pasword: password\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\t autocomplete=\"off\" />\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"submit\" id=\"submitButton\" class=\"submit\" value=\"Log in\" />\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t\t\t");

							String error="";
out.println(error);
out.println("");
error="Aa";
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t<span class=\"error\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${errorMessage}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(" </span>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t<div id=\"introduction\" class=\"groupMargin\">\n");
      out.write("\t\t\t\t\t\t\t<br />\n");
      out.write("\t\t\t\t\t\t\t<p>Please sign in with your Username and Password.</p>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<br />\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<p>Make sure you have internet connection, Databases and some CSS/JS needs internet connection.</p>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</body>\n");
      out.write("\n");
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
