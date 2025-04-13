<!DOCTYPE html>
<%@page import="java.sql.Statement"%>
<%@page import="com.deduplication.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<html lang="en">
<body>
<% 
	if(session.getAttribute("userName")==null)
	{
		 out.print("Please login first");  
         request.getRequestDispatcher("user_signin.jsp").include(request, response);  
	}
	else
	{
	session.setAttribute("Username", session.getAttribute("userName"));
    String username=(String)session.getAttribute("userName"); 
	String filename=request.getParameter("file");
	String ownerName=request.getParameter("owner");
	session.setAttribute("file", filename);
	
	String id=null;
	String hash=null;
	String status=null;
	String filePath=null;
	
	Connection con=ConnectionFactory.getInstance().getConnection();
	Statement smt=con.createStatement();
	int c=smt.executeUpdate("insert into keyrequest(ownername,filename,username)values('"+ownerName+"','"+filename+"','"+username+"')");
	if(c>0)
	{
		id=request.getParameter("id");
		hash=request.getParameter("hash");
		status=request.getParameter("status");
		filePath=request.getParameter("filepath");
		
		response.setContentType("text/html");  
		out.println("<script type=\"text/javascript\">");  
		out.println("alert('Request Send Sucessfully...');");
		out.println("location='View_files.jsp';");
		out.println("</script>");
	}
	else
	{
		response.setContentType("text/html");  
		out.println("<script type=\"text/javascript\">");  
		out.println("alert('Error Please retry...');");
		out.println("location='View_files.jsp';");
		out.println("</script>");
	}

}
%>
	

</body>
</html>
