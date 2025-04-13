package com.deduplication.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;






import net.codejava.crypto.*;

import com.deduplication.dao.Sql;
import com.itextpdf.text.pdf.codec.Base64.InputStream;

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)
@WebServlet("/GenerateTag")
public class GenerateTag extends HttpServlet {
	private static final long serialVersionUID = -1445651683541116182L;
	private static final String SAVE_DIR="Deduplication";
	Connection con=null;
	String message = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
            {
    	try
    	{
    			response.setContentType("text/html;charset=UTF-8");
            	PrintWriter out=response.getWriter();
            	HttpSession session=request.getSession(true);
            
                String savePath = "C:" + File.separator + SAVE_DIR+File.separator+"File";
                String encryptPath = "C:" + File.separator + SAVE_DIR+File.separator+"EncryptFile";
                
                
               
                	File fileSaveDir=new File(savePath);
                    if(!fileSaveDir.exists())
                    {
                        fileSaveDir.mkdir();
                    }
                    File fileSaveDir1=new File(encryptPath);
                    if(!fileSaveDir1.exists())
                    {
                        fileSaveDir1.mkdir();
                    }
                    
				
                Part part=request.getPart("file");
                String fileName=extractFileName(part);
                
                ResultSet rs=Sql.checkfilename(fileName);
                if(rs.next())
                {
                	out.println("<script type=\"text/javascript\">");  
    				out.println("alert('File Name Already exists.Please change File Name.......');");  
    				out.println("</script>");    
    				request.getRequestDispatcher("/file_upload.jsp").include(request, response);
                }
                else
                {	
                	String filePath= savePath + File.separator + fileName ;
                	part.write(filePath);
                	
                	File inputFile = new File(filePath);
                	String encryptfilePath= encryptPath + File.separator + fileName ;
                	
                	File file1 = new File(encryptfilePath); 
            		if (!file1.exists()) 
            		{
            			file1.createNewFile();
            		}
                	
                    File encryptedFile = new File(encryptfilePath);
                    File File = new File(filePath);
                    
                                		
            		try 
            		{
            			
            			String k=null;
            			k= HashGeneratorUtils.generateSHA256(inputFile);
                		System.out.println(k);
                		String key=null;
                		key=(String) k.subSequence(0, 16);
                		System.out.println(key);
            			CryptoUtils.encrypt(key, inputFile, encryptedFile);
            			System.out.println("Done");
            			session.setAttribute("key", key);
            		} catch (CryptoException ex) {
            			System.out.println(ex.getMessage());
            			ex.printStackTrace();
            		}
            		
                    /*
                    String k = HashGeneratorUtils.generateSHA256(File);
                    String key=(String) k.subSequence(0, 16);
                    System.out.println(k);
                    System.out.println(key);
                    //String kk=k.substring(1, 16);
                    //String key = "Bar12345Bar12345"; // 128 bit key
                    
                    try 
                    {
                        CryptoUtils.encrypt(key, inputFile, encryptedFile);
                        session.setAttribute("key", key);
                    } catch (CryptoException ex) 
                    {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
*/                
                	String[] fn=fileName.split("\\.");
                	System.out.println("extension="+fn[1]);
                 
                	if(fn[1].equals("pdf"))
                		{
                			System.out.println("this is pdf file");
                			out.println("<script type=\"text/javascript\">");  
            				out.println("alert('This is pdf file.......');");  
            				out.println("</script>");
                			String pdf=filePath;
                			String text=savePath+File.separator+fn[0]+".txt";
                			Sql.parsePdf(pdf, text);
                			File f=new File(pdf);
                			f.delete();
                	
                			session.setAttribute("filepath", text);
                			session.setAttribute("filename", fn[0]+".txt");
                			response.sendRedirect("pdfservlet");
                	
                		}
                	else
                		{
                			session.setAttribute("filepath", filePath);
                			session.setAttribute("encryptfilePath", encryptfilePath);
                			session.setAttribute("filename", fileName);
                			System.out.println("this is text file");
                			response.sendRedirect("textservlet");
                		}
                }
       	}
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    }
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}