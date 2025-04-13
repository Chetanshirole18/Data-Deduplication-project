package com.deduplication.controller;

import net.codejava.crypto.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import com.deduplication.dao.SplitAllFiles;
import com.deduplication.dao.SplitAllFiles2;
import com.deduplication.dao.SplitAllFiles3;
import com.deduplication.dao.SplitFileForDownload;
import com.deduplication.dao.Sql;
import com.deduplication.db.ConnectionFactory;

/**
 * Servlet implementation class OtherFileDownload
 */
@WebServlet("/OtherFileDownload")
public class OtherFileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherFileDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filekey=request.getParameter("key");
		String id=request.getParameter("id");
		String filename=request.getParameter("filename");
		String hash=request.getParameter("hash");
		String status=request.getParameter("status");
		String filePath=request.getParameter("filepath");
		System.out.println(status);
		
		
		
        try 
        {
        	Connection connection =ConnectionFactory.getInstance().getConnection();
        	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String currTime=dateFormat.format(cal.getTime());
			java.sql.Time currentTime=null;
			currentTime= new java.sql.Time(dateFormat.parse(currTime).getTime());
		 	//System.out.println(currentTime);
			
		 	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 	Date dateobj = new Date();
		 	String currDate=df.format(dateobj);
		 	java.sql.Date currentDate=null;
		 	currentDate= new java.sql.Date(df.parse(currDate).getTime());
		 	//System.out.println(currentDate);
		 	Statement s=connection.createStatement(); 
        	ResultSet r=s.executeQuery("SELECT id FROM otpvalidation where date='"+currentDate+"' and start<'"+currentTime+"' and end>'"+currentTime+"' and otp='"+filekey+"'");
		 	boolean result=false;
        	while(r.next()==true)
		 	{
        		result=true;
		 		Statement statement = connection.createStatement();
				ResultSet resultset =statement.executeQuery("select id from filestorage where pow='"+filekey+"'") ;
				if(resultset.next()==true)
				{       
				
					if(status.equalsIgnoreCase("original"))
					{
						System.out.println("In");
						String key=(String) hash.subSequence(0, 16);
						//String key = "Bar12345Bar12345"; 
						System.out.println(filePath);
					
						File encryptedFile = new File(filePath);
			        
						File file2 = new File("C:/Deduplication/File/"+filename); 
						if (!file2.exists()) 
						{
							file2.createNewFile();
						}
						System.out.println("C:/Deduplication/File/"+filename);
						File decryptedFile = new File("C:/Deduplication/File/"+filename);
						try 
						{
							System.out.println("Start");
							CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
							System.out.println("done");
						} catch (CryptoException e) 
						{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					File downloadFile = new File("C:/Deduplication/File/"+filename);
			        FileInputStream inStream = new FileInputStream(downloadFile);
			         
			        // if you want to use a relative path to context root:
			        String relativePath = getServletContext().getRealPath("");
			        System.out.println("relativePath = " + relativePath);
			         
			        // obtains ServletContext
			        ServletContext context = getServletContext();
			         
			        // gets MIME type of the file
			        String mimeType = context.getMimeType(filePath);
			        if (mimeType == null) {        
			            // set to binary type if MIME mapping not found
			            mimeType = "application/octet-stream";
			        }
			        System.out.println("MIME type: " + mimeType);
			         
			        // modifies response
			        response.setContentType(mimeType);
			        response.setContentLength((int) downloadFile.length());
			         
			        // forces download
			        String headerKey = "Content-Disposition";
			        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			        response.setHeader(headerKey, headerValue);
			         
			        // obtains response's output stream
			        OutputStream outStream = response.getOutputStream();
			         
			        byte[] buffer = new byte[4096];
			        int bytesRead = -1;
			         
			        while ((bytesRead = inStream.read(buffer)) != -1) 
			        {
			            outStream.write(buffer, 0, bytesRead);
			        }
			        				
			        inStream.close();
			        outStream.close();     
			    	
			        //Update OTP
					/*Statement s2=connection.createStatement();
					String pow=Sql.getPow();
					s2.executeUpdate("update filestorage set pow='"+pow+"' where id='"+resultset.getInt(1)+"'");
					*/
					
				}
			
				if(status.equalsIgnoreCase("duplicate"))
				{
					
					String original_path = "";
					
					try 
					{
						String q="select * from filestorage where hash='"+hash+"' and status='original'";
						Connection conn=ConnectionFactory.getInstance().getConnection();
						Statement stmt=conn.createStatement();
						ResultSet rs=stmt.executeQuery(q);
						while(rs.next())
						{
							original_path=rs.getString(4);
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					System.out.println("In");
					
					String key=(String) hash.subSequence(0, 16);
					//String key = "Bar12345Bar12345"; 
					
					
			        File encryptedFile = new File(original_path);
			        
			        File file2 = new File("C:/Deduplication/File/"+filename); 
					if (!file2.exists()) 
					{
						file2.createNewFile();
					}
					System.out.println("C:/Deduplication/File/"+filename);
			        File decryptedFile = new File("C:/Deduplication/File/"+filename);
					try 
			        {
						System.out.println("Start");
						CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
						System.out.println("done");
					} catch (CryptoException e) 
			        {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					File downloadFile = new File("C:/Deduplication/File/"+filename);
			        FileInputStream inStream = new FileInputStream(downloadFile);
			         
			        // if you want to use a relative path to context root:
			        String relativePath = getServletContext().getRealPath("");
			        System.out.println("relativePath = " + relativePath);
			         
			        // obtains ServletContext
			        ServletContext context = getServletContext();
			         
			        // gets MIME type of the file
			        String mimeType = context.getMimeType(filePath);
			        if (mimeType == null) {        
			            // set to binary type if MIME mapping not found
			            mimeType = "application/octet-stream";
			        }
			        System.out.println("MIME type: " + mimeType);
			         
			        // modifies response
			        response.setContentType(mimeType);
			        response.setContentLength((int) downloadFile.length());
			         
			        // forces download
			        String headerKey = "Content-Disposition";
			        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			        response.setHeader(headerKey, headerValue);
			         
			        // obtains response's output stream
			        OutputStream outStream = response.getOutputStream();
			         
			        byte[] buffer = new byte[4096];
			        int bytesRead = -1;
			         
			        while ((bytesRead = inStream.read(buffer)) != -1) 
			        {
			            outStream.write(buffer, 0, bytesRead);
			        }
			        				
			        inStream.close();
			        outStream.close();    
			        
			      //Update OTP
					/*Statement s2=connection.createStatement();
					String pow=Sql.getPow();
					s2.executeUpdate("update filestorage set pow='"+pow+"' where id='"+resultset.getInt(1)+"'");
			    */
				}
				if(status.equalsIgnoreCase("duplicateBlock"))
				{
					
					List<File> list=new ArrayList<File>();
					try 
					{
						String q="select * from blockindex where filename='"+filename+"'";
						Connection conn=ConnectionFactory.getInstance().getConnection();
						Statement stmt=conn.createStatement();
						ResultSet rs=stmt.executeQuery(q);
						while(rs.next())
						{
							
							File file=new File("C:/Deduplication/Cache2/"+rs.getString(2));
							list.add(file);
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
					
					try 
					{
						SplitFileForDownload.splitAllFiles();
						SplitAllFiles3.splitAllFiles();
					} catch (CryptoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String key=(String) hash.subSequence(0, 16);
					//String key = "Bar12345Bar12345"; 
					
			        File BlockFolder=new File("C:/Deduplication/BlockWise/");
					File[] listofFolderFiles=BlockFolder.listFiles();
					for (File file : listofFolderFiles) 
			        {
			            if (file.isFile()) 
			            {
			            	File TargetFolder=new File("C:/Deduplication/Cache2/"+file.getName());
			            	File input=new File("C:/Deduplication/BlockWise/"+file.getName());
			            	try 
			            	{
								CryptoUtils.decrypt(key, input, TargetFolder);
								System.out.println("In");
							} catch (CryptoException e) 
			            	{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            }
			        }
					System.out.println(list);
					//Merge File
					File ofile = new File("C:/Deduplication/File2/"+filename);
					FileOutputStream fos;
					FileInputStream fis;
					byte[] fileBytes;
					int bytesRead = 0;
					
									
					try 
					{
					    fos = new FileOutputStream(ofile,true);
					    for (File file : list) 
					    {
					        fis = new FileInputStream(file);
					        fileBytes = new byte[(int) file.length()];
					        bytesRead = fis.read(fileBytes, 0,(int)  file.length());
					        assert(bytesRead == fileBytes.length);
					        assert(bytesRead == (int) file.length());
					        fos.write(fileBytes);
					        fos.flush();
					        fileBytes = null;
					        fis.close();
					        fis = null;
					    }
					    fos.close();
					    fos = null;
					}catch (Exception exception){
						exception.printStackTrace();
					}
					
					
			       	File downloadFile = new File("C:/Deduplication/File2/"+filename);
			        FileInputStream inStream = new FileInputStream(downloadFile);
			         
			        // if you want to use a relative path to context root:
			        String relativePath = getServletContext().getRealPath("");
			        System.out.println("relativePath = " + relativePath);
			         
			        // obtains ServletContext
			        ServletContext context = getServletContext();
			         
			        // gets MIME type of the file
			        String mimeType = context.getMimeType(filePath);
			        if (mimeType == null) {        
			            // set to binary type if MIME mapping not found
			            mimeType = "application/octet-stream";
			        }
			        System.out.println("MIME type: " + mimeType);
			         
			        // modifies response
			        response.setContentType(mimeType);
			        response.setContentLength((int) downloadFile.length());
			         
			        // forces download
			        String headerKey = "Content-Disposition";
			        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			        response.setHeader(headerKey, headerValue);
			         
			        // obtains response's output stream
			        OutputStream outStream = response.getOutputStream();
			         
			        byte[] buffer = new byte[4096];
			        int bytesRead1 = -1;
			         
			        while ((bytesRead1 = inStream.read(buffer)) != -1) 
			        {
			            outStream.write(buffer, 0, bytesRead1);
			        }
			        				
			        inStream.close();
			        outStream.close();
			        
			      //Update OTP
					/*Statement s2=connection.createStatement();
					String pow=Sql.getPow();
					s2.executeUpdate("update filestorage set pow='"+pow+"' where id='"+resultset.getInt(1)+"'");
			    */
				}
				File thirdFolder=new File("C:/Deduplication/File/");
				File[] listofFiles3 = thirdFolder.listFiles();
				for (File file1 : listofFiles3) 
			    {
			           if (file1.isFile()) 
			           {
			            	file1.delete();
			            	
			           }
			    }
				File CacheFolder=new File("C:/Deduplication/Cache/");
				File[] CachelistofFiles3 = CacheFolder.listFiles();
				for (File file1 : CachelistofFiles3) 
			    {
			           if (file1.isFile()) 
			           {
			            	file1.delete();
			            	
			           }
			    }
					File Cache2=new File("C:/Deduplication/Cache2/");
					File[] Cache2Files3 = Cache2.listFiles();
					for (File file1 : Cache2Files3) 
			        {
			            if (file1.isFile()) 
			            {
			            	file1.delete();
			            	
			            }
			        }
					File TempFile2=new File("C:/Deduplication/File2/");
					File[] TempFiles = TempFile2.listFiles();
					for (File file1 : TempFiles) 
			        {
			            if (file1.isFile()) 
			            {
			            	file1.delete();
			            	
			            }
			        }
			}
			else
			{
				PrintWriter out = response.getWriter();  
				response.setContentType("text/html");  
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('Key not Matched...');");
				out.println("location='Other_download.jsp';");
				out.println("</script>");
			}
		 }
        if(result==false)
        {
        	Statement s2=connection.createStatement();
			String pow=Sql.getPow();
			s2.executeUpdate("update filestorage set pow='"+pow+"' where id='"+id+"'");
			PrintWriter out = response.getWriter();  
			response.setContentType("text/html");  
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Please enter valid OTP...');");
			out.println("location='Other_download.jsp';");
			out.println("</script>");
        }
            
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}

}
