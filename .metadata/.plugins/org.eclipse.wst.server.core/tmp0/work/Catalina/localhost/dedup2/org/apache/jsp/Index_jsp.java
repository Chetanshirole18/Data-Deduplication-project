/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.99
 * Generated at: 2025-03-03 15:05:28 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(3);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
      return;
    }

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

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("	<meta charset=\"utf-8\">\r\n");
      out.write("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("	<title>Deduplication</title>\r\n");
      out.write("	<link rel=\"favicon\" href=\"assets1/images/favicon.png\">\r\n");
      out.write("	<link rel=\"stylesheet\" media=\"screen\" href=\"http://fonts.googleapis.com/css?family=Open+Sans:300,400,700\">\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"assets1/css/bootstrap.min.css\">\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"assets1/css/font-awesome.min.css\">\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"assets1/css/bootstrap-theme.css\" media=\"screen\">\r\n");
      out.write("	<link rel=\"stylesheet\" type=\"text/css\" href=\"assets1/css/da-slider.css\" />\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"assets1/css/style.css\">\r\n");
      out.write("	<script src=\"assets1/js/html5shiv.js\"></script>\r\n");
      out.write("	<script src=\"assets1/js/respond.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<div class=\"navbar navbar-inverse\" style=\"background-color: black;\">\r\n");
      out.write("		<div class=\"container\">\r\n");
      out.write("			<div class=\"navbar-header\">\r\n");
      out.write("				<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\"><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span></button>\r\n");
      out.write("				<a class=\"navbar-brand\" href=\"Index.jsp\">\r\n");
      out.write("				<h4>DEDUPLICATION</h4>\r\n");
      out.write("				</a>	\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"navbar-collapse collapse\">\r\n");
      out.write("				<ul class=\"nav navbar-nav pull-right mainNav\">\r\n");
      out.write("					<li><a href=\"Index.jsp\">Home</a></li>\r\n");
      out.write("					<li><a href=\"user_signin.jsp\">User</a></li>\r\n");
      out.write("					<li><a href=\"admin_signin.jsp\">Admin</a></li>\r\n");
      out.write("					<li><a href=\"contact.jsp\">Contact</a></li>\r\n");
      out.write("\r\n");
      out.write("				</ul>\r\n");
      out.write("			</div>\r\n");
      out.write("			<!--/.nav-collapse -->\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("	<!-- /.navbar -->\r\n");
      out.write("\r\n");
      out.write("	<!-- Header -->\r\n");
      out.write("	<header id=\"head\">\r\n");
      out.write("		<div class=\"container\">\r\n");
      out.write("			<div class=\"banner-content\">\r\n");
      out.write("				<div id=\"da-slider\" class=\"da-slider\">\r\n");
      out.write("					<div class=\"da-slide\">\r\n");
      out.write("						<h2>Data Storage Site</h2>\r\n");
      out.write("						<p>Store Data without Duplication</p>\r\n");
      out.write("						<div class=\"da-img\"></div>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div class=\"da-slide\">\r\n");
      out.write("						<h2>Secured site</h2>\r\n");
      out.write("						<p>Data Storage is secured..</p>\r\n");
      out.write("						<div class=\"da-img\"></div>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</header>\r\n");
      out.write("	<!-- /Header -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("	<div id=\"courses\">\r\n");
      out.write("		<div class=\"container\">\r\n");
      out.write("			<h2>About Us</h2>\r\n");
      out.write("			<p align=\"justify\">Data deduplication is a technique for eliminating duplicate copies of data, and has been widely used in cloud storage to\r\n");
      out.write("reduce storage space and upload bandwidth. However, there is only one copy for each file stored in cloud even if such a file is owned\r\n");
      out.write("by a huge number of users. As a result, deduplication system improves storage utilization while reducing reliability. Furthermore,\r\n");
      out.write("the challenge of privacy for sensitive data also arises when they are outsourced by users to cloud. Aiming to address the above\r\n");
      out.write("security challenges, we makes the first attempt to formalize the notion of distributed reliable deduplication system. We propose\r\n");
      out.write("new distributed deduplication systems with higher reliability in which the data chunks are distributed across multiple cloud servers.\r\n");
      out.write("The security requirements of data confidentiality and tag consistency are also achieved by introducing a deterministic secret sharing\r\n");
      out.write("scheme in distributed storage systems, instead of using convergent encryption as in previous deduplication systems.</p>\r\n");
      out.write("	</div>\r\n");
      out.write("	</div>\r\n");
      out.write("	<!-- container -->\r\n");
      out.write("	<section class=\"container\">\r\n");
      out.write("		<div class=\"heading\">\r\n");
      out.write("			<!-- Heading -->\r\n");
      out.write("			<h2>Our Features</h2>\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"row\">\r\n");
      out.write("			<div class=\"col-md-4\">\r\n");
      out.write("				<img src=\"assets1/images/1.png\" alt=\"\" class=\"img-responsive\">\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"col-md-8\">\r\n");
      out.write("				<p align=\"justify\">New secure deduplication systems are proposed to provide efficient deduplication with high reliability for file-level and block-level deduplication.</p>\r\n");
      out.write("				<p align=\"justify\">The secret splitting technique, instead\r\n");
      out.write("of traditional encryption methods, is utilized\r\n");
      out.write("to protect data confidentiality. Specifically, data are\r\n");
      out.write("split into fragments by using secure secret sharing\r\n");
      out.write("schemes and stored at different servers.</p>\r\n");
      out.write("				<p align=\"justify\">In\r\n");
      out.write("more details, confidentiality, reliability and integrity\r\n");
      out.write("can be achieved in our proposed system.</p>\r\n");
      out.write("				<p align=\"justify\">Two kinds\r\n");
      out.write("of collusion attacks are considered in our solutions.\r\n");
      out.write("These are the collusion attack on the data and the\r\n");
      out.write("collusion attack against servers.</p>\r\n");
      out.write("\r\n");
      out.write("				<blockquote class=\"blockquote-1\">\r\n");
      out.write("				<cite title=\"Source Title\"><b>Problem Statement</b></cite>\r\n");
      out.write("					<p>To remove the duplicated copies of data in cloud storage considering security parameters.</p>\r\n");
      out.write("				</blockquote>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</section>\r\n");
      out.write("	<footer id=\"footer\">\r\n");
      out.write("		<div class=\"container\">\r\n");
      out.write("			<div class=\"social text-center\">\r\n");
      out.write("				<a href=\"#\"><i class=\"fa fa-twitter\"></i></a>\r\n");
      out.write("				<a href=\"#\"><i class=\"fa fa-facebook\"></i></a>\r\n");
      out.write("				<a href=\"#\"><i class=\"fa fa-dribbble\"></i></a>\r\n");
      out.write("				<a href=\"#\"><i class=\"fa fa-flickr\"></i></a>\r\n");
      out.write("				<a href=\"#\"><i class=\"fa fa-github\"></i></a>\r\n");
      out.write("			</div>\r\n");
      out.write("\r\n");
      out.write("			<div class=\"clear\"></div>\r\n");
      out.write("			<!--CLEAR FLOATS-->\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"footer2\">\r\n");
      out.write("			<div class=\"container\">\r\n");
      out.write("					<div class=\"col-md-6 panel\">\r\n");
      out.write("						<div class=\"panel-body\">\r\n");
      out.write("							<p class=\"text-right\">\r\n");
      out.write("								Copyright &copy; 2022-23.\r\n");
      out.write("							</p>\r\n");
      out.write("						</div>\r\n");
      out.write("					</div>\r\n");
      out.write("\r\n");
      out.write("				</div>\r\n");
      out.write("				<!-- /row of panels -->\r\n");
      out.write("			</div>\r\n");
      out.write("	</footer>\r\n");
      out.write("\r\n");
      out.write("	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->\r\n");
      out.write("	<script src=\"assets1/js/modernizr-latest.js\"></script>\r\n");
      out.write("	<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>\r\n");
      out.write("	<script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js\"></script>\r\n");
      out.write("	<script src=\"assets1/js/jquery.cslider.js\"></script>\r\n");
      out.write("	<script src=\"assets1/js/custom.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
