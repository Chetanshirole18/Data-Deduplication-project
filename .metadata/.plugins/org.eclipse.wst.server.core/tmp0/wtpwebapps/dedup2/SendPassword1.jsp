<%@page import="jakarta.servlet.RequestDispatcher"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*,javax.activation.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<title>Send Email using JSP</title>
</head>
<body>
	<%
			String e=(String)session.getAttribute("Email");
			String i=request.getParameter("name"); 
      %>
	<%
   		/*String result;
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        
        String from = "avinashgund95@gmail.com";
        String pass = "wrtarlvmudbbdayu";
        String to = e;
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");     
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.timeout", "25000");
        Session mailsession = Session.getDefaultInstance(props);
   try
   {
        MimeMessage message = new MimeMessage(mailsession);       
        message.setFrom(new InternetAddress(from));   
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Forgot Password");
        
        message.setText("Your Password is:"+i);
        Transport transport = mailsession.getTransport("smtp");
        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        result = "Sent message successfully....";*/
        
        String host="smtp.gmail.com";  
        final String user="avinashgund95@gmail.com";//change accordingly  
        final String password="wrtarlvmudbbdayu";//change accordingly  
        String result;
        String to=e;//change accordingly  
        
         //Get the session object  
         Properties props = new Properties();  
         props.put("mail.smtp.host",host);  
         props.put("mail.smtp.auth", "true");  
           
         Session session1 = Session.getDefaultInstance(props,  
          new javax.mail.Authenticator() {  
            protected PasswordAuthentication getPasswordAuthentication() {  
          return new PasswordAuthentication(user,password);  
            }  
          });  
        
         //Compose the message  
          try {  
        	  MimeMessage message = new MimeMessage(session1);       
              message.setFrom(new InternetAddress(user));   
              message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
              message.setSubject("Forgot Password");
              
              message.setText("Your Password is:"+i);
              Transport transport = session1.getTransport("smtp");
              transport.connect(host, user, password);
              transport.sendMessage(message, message.getAllRecipients());
              transport.close();
              result = "Sent message successfully....";
            /*catch (MessagingException e) {e.printStackTrace();} */
        
        
        
        %>
	<script type="text/javascript">
        alert("Password send to mail Successfully.")
        <%
        //Type mismatch: cannot convert from javax.servlet.RequestDispatcher to jakarta.servlet.RequestDispatcher
        RequestDispatcher rd= (RequestDispatcher)request.getRequestDispatcher("user_signin.jsp");
        rd.include(request, response);
        %>
        </script>
	<%         
    }catch (MessagingException mex) {
      mex.printStackTrace();
      result = "Error: unable to send message....";
    }
%>
</body>
</html>
