package net.codejava.crypto;
import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  

public class EmailDemo {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		 String host="smtp.gmail.com";  
		  final String user="avinashgund95@gmail.com";  
		  final String password="wrtarlvmudbbdayu";
		    
		  String to="avinashbgund@gmail.com"; 
		  
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
  		   return new PasswordAuthentication("avinashbgund@gmail.com","wrtarlvmudbbdayu");//change accordingly  
  		   }  
  		  });  
  		   
		  
		   //Compose the message  
		    try {  
		     MimeMessage message = new MimeMessage(ses);  
		     message.setFrom(new InternetAddress(user));  
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		     message.setSubject("javatpoint");  
		     message.setText("This is simple program of sending email using JavaMail API");  
		       
		    //send the message  
		     Transport.send(message);  
		  
		     System.out.println("message sent successfully...");  
		   
		     } catch (MessagingException e) {e.printStackTrace();}

	}

}
