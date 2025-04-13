<%@page import="java.util.Date"%>
<%@page import="com.deduplication.dao.Sql"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="javax.mail.PasswordAuthentication"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.Properties"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.MessagingException"%>
<%@page import="javax.mail.PasswordAuthentication"%>
<%@page import="javax.mail.Session"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="com.deduplication.db.ConnectionFactory"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String filename=request.getParameter("filename");
String ownername=request.getParameter("owner");
String userName=request.getParameter("username");
int id=Integer.parseInt(request.getParameter("id"));
/* System.out.println("File Name "+ filename);
System.out.println("Owner Name "+ownername);
System.out.println("User Name "+userName); */
Connection connection =ConnectionFactory.getInstance().getConnection();
Statement statement = connection.createStatement();
String key=null;
ResultSet rs =statement.executeQuery("select pow from filestorage where filename='"+filename+"' and username='"+ownername+"'") ; 
while(rs.next())
{
	key=rs.getString(1);
}
Statement s=connection.createStatement();
int c=s.executeUpdate("update keyrequest set status='send' where id='"+id+"'");
if(c>0)
{
		  //Send Mail
		  String subject;
  		  String addtext;
  		  addtext=filename+ " Download Token is: "+key;
  		  subject="File Download Token ";
  		  
  		  //Get the session object  
  		  Properties props = new Properties();  
  		  props.put("mail.smtp.host", "smtp.gmail.com");  
  		  props.put("mail.smtp.socketFactory.port", "465");  
  		  props.put("mail.smtp.socketFactory.class",  
  		            "javax.net.ssl.SSLSocketFactory");  
  		  props.put("mail.smtp.auth", "true");  
  		  props.put("mail.smtp.port", "465");  
  		    		
  		  Session ses = Session.getInstance(props,  
  		   new javax.mail.Authenticator() 
  		  {  
  		   protected PasswordAuthentication getPasswordAuthentication() 
  		   {  
  		   return new PasswordAuthentication("javaproject333@gmail.com","project333");//change accordingly  
  		   }  
  		  });  
  		   
  		  //compose message  
  		  try 
  		  {  
  		   MimeMessage message = new MimeMessage(ses);  
  		   message.setFrom(new InternetAddress());//change accordingly  
  		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(userName));  
  		   message.setSubject(subject);  
  		   //message.setText(addtext); 
  		   
  		     
  		   message.setText(addtext);
  		   //send message  
  		   Transport.send(message);  
  		  
  		    System.out.println("message sent successfully");  
  		 	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
 			Calendar cal = Calendar.getInstance();
 			String currTime=dateFormat.format(cal.getTime());
 			java.sql.Time currentTime=null;
 			currentTime= new java.sql.Time(dateFormat.parse(currTime).getTime());
 		 	System.out.println(currentTime);
 			
 		 	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 		 	Date dateobj = new Date();
 		 	String currDate=df.format(dateobj);
 		 	java.sql.Date currentDate=null;
 		 	currentDate= new java.sql.Date(df.parse(currDate).getTime());
 		 	System.out.println(currentDate);
 		 	
 		 	//Substract two minutes from current time.
 		 	cal = Calendar.getInstance();
 		 	cal.add(Calendar.MINUTE, 2);
 		 	String threeminutesAfter=dateFormat.format(cal.getTime());
 		 	System.out.println(threeminutesAfter);
 		 	java.sql.Time threeMinutesAfterTime=null;
 		 	threeMinutesAfterTime= new java.sql.Time(dateFormat.parse(threeminutesAfter).getTime());
 		 	System.out.println(threeMinutesAfterTime);
 		 	
 		 	
 		 	Connection con=ConnectionFactory.getInstance().getConnection();
 		 	Statement smt=con.createStatement();
 		 	int res=smt.executeUpdate("insert into otpvalidation(date, start, end,otp)values('"+currentDate+"','"+currentTime+"','"+threeMinutesAfterTime+"','"+key+"')");
			if(res>0)
			{
				System.out.println("OTP Validation sucessfully...");
			}
  		  }
  		  catch (MessagingException e) 
  		  {
  			e.printStackTrace();
  		  }
  		response.setContentType("text/html");  
		out.println("<script type=\"text/javascript\">");  
		out.println("alert('Key Send Sucessfully...');");
		out.println("location='downloadRequest.jsp';");
		out.println("</script>");
}     
      


        
%>
</body>
</html>