package com.deduplication.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.codejava.crypto.*;

import com.deduplication.dao.SplitAllFiles;
import com.deduplication.dao.SplitAllFiles3;
import com.deduplication.dao.SplitFile;
import com.deduplication.dao.SplitFile3;
import com.deduplication.dao.Sql;

@WebServlet("/textservlet")
public class textservlet extends HttpServlet 
{
	private static final long serialVersionUID = -1445651683541116182L;
	//private static final String SAVE_DIR="Deduplication";
	String encryptPath = "C:/Deduplication/EncryptFile/";
	Connection con=null;
	String message = null;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
            {
    	try
    	{
    		response.setContentType("text/html;charset=UTF-8");
            	PrintWriter out = response.getWriter();
            	
            HttpSession session1=request.getSession(false);
            String username= (String) session1.getAttribute("userName");
            String filePath=(String) session1.getAttribute("filepath");
            
            String fileName=(String) session1.getAttribute("filename");
            String encryptedPath=encryptPath+fileName;
            System.out.println("filename="+fileName);
            
            File f=new File(filePath);
        	long length=f.length();
            
          /*  String savePath = "C:" + "/" + SAVE_DIR+"/"+"File";
                  
                
                	File fileSaveDir=new File(savePath);
                    if(!fileSaveDir.exists())
                    {
                        fileSaveDir.mkdir();
                    }
				                */
        	    System.out.println("Encrypted path: "+encryptedPath);
                String sha256Hash = HashGeneratorUtils.generateSHA256(f);
    			System.out.println("SHA-256 Hash: " + sha256Hash);
    			/*
    			String tag=Proof_tag.calculateRFC2104HMAC(sha256Hash, nm);
    			System.out.println(tag);*/
    			
    			String key = (String)session1.getAttribute("key");
    			
    			ResultSet rs=Sql.getcount();
    			while(rs.next())
    			{
    				if(rs.getInt(1)==0)
    				{
    					String status="original";
    					String pow=Sql.getPow();
    					Sql.insertfile(fileName,username,encryptedPath, sha256Hash,key,status,pow);
    					
    					    					
    					File f1=new File(filePath);
						f1.delete();
						
        				out.println("<script type=\"text/javascript\">");  
                    	out.println("alert('File Uploaded successfully');");  
                    	out.println("</script>");    
                        getServletContext().getRequestDispatcher("/file_upload.jsp").include(request, response);
    				}
    				
    				else
    				{
    					ResultSet rst=Sql.checktoken(sha256Hash);
    					if(rst.next())
    					{
    						System.out.println("File Is Duplicate");
    						String status="duplicate";
    						Sql.insertdup(fileName, username, sha256Hash);
    						String pow=Sql.getPow();
    						Sql.insertfile(fileName,username,encryptedPath, sha256Hash,key,status,pow); 
    						
    						
    						File f1=new File(filePath);
    						f1.delete();
    						
    						
    						File f2=new File(encryptedPath);
    						f2.delete();
    						
    						out.println("<script type=\"text/javascript\">");  
    		            	out.println("alert('File is duplicate. perform file wise deduplication & maintain Index..');");  
    		            	out.println("</script>");    
    		                getServletContext().getRequestDispatcher("/file_upload.jsp").include(request, response);
    					}
    					else
    					{
    						File delFile=new File(encryptedPath);
    						delFile.delete();
    						String hash22="";
    						int dupCount=0;
    						SplitAllFiles.splitAllFiles();
//    						File f1=new File(file Path);
    						SplitFile.splitFile(filePath);
    						File folder3 = new File("C:/Deduplication/TMP/");
    				        File[] listOfFiles3 = folder3.listFiles();
    				        
    				           						
    						
    				        for (File file3 : listOfFiles3) 
    				        {
    				            if (file3.isFile()) 
    				            {
    				            	
    				            	String hash11 = HashGeneratorUtils.generateSHA256(file3);
       							 	File folder4 = new File("C:/Deduplication/Cache/");
       	    				        File[] listOfFiles4 = folder4.listFiles();
       	    						for (File file4 : listOfFiles4) 
       	    				        {
       	    				            if (file4.isFile()) 
       	    				            {
       	       							 hash22 = HashGeneratorUtils.generateSHA256(file4);
       	       							 if(hash11.equalsIgnoreCase(hash22))
       	       							 {
       	       								System.out.println(hash11);
       	       								System.out.println(hash22);
       	       								dupCount++;
       	       							 }
       	       							 
       	    				            }
       	    				        }
       	    						
    				            }
    				        }
    				        if(dupCount>0)
    				        {
    				        	
        						File f2=new File(encryptedPath);
        						f2.delete();
        						String fname=f2.getName();
        						String dupBlockName="";
        						String hash2="";
        						SplitAllFiles.splitAllFiles();
        						SplitAllFiles3.splitAllFiles();
        						//File f1=new File(file Path);
        						SplitFile.splitFile(filePath);
        						File originalFile=new File(filePath);
        						String pow=Sql.getPow();
        						Sql.insertfile(originalFile.getName(), username, "C:/Deduplication/EncryptFile/"+originalFile.getName(), HashGeneratorUtils.generateSHA256(originalFile), key, "duplicateBlock",pow);
        						
        						     						
        						File folder = new File("C:/Deduplication/TMP/");
        				        File[] listOfFiles = folder.listFiles();
        				       
        				        for (File file : listOfFiles) 
        				        {
        				            if (file.isFile()) 
        				            {
        				            	int count=0;
        				            	String hash1 = HashGeneratorUtils.generateSHA256(file);
           							 	File folder1 = new File("C:/Deduplication/Cache/");
           	    				        File[] listOfFiles1 = folder1.listFiles();
           	    						for (File file1 : listOfFiles1) 
           	    				        {
           	    				            if (file1.isFile()) 
           	    				            {
           	       							 hash2 = HashGeneratorUtils.generateSHA256(file1);
           	       							 if(hash1.equalsIgnoreCase(hash2))
           	       							 {
           	       								count++;
           	       								System.out.println(file.getName()+"  =  "+file1.getName());
           	       								System.out.println("Similar Block Found");
           	       								dupBlockName=file1.getName();
           	       								
           	       							 }
           	       							 
           	    				            }
           	    				        }
           	    						if(count>0)
           	    						{
           	    							Sql.insertBlock(fname, dupBlockName, "C:/Deduplication/EncryptFile/"+dupBlockName, hash2, "duplicate");
           	    						}
           	    						else
           	    						{
           	    							
           	    							//File inputFile=new File("C:/Deduplication/TMP/"+file.getName());
           	    							SplitFile3.splitFile("C:/Deduplication/TMP/"+file.getName());
           	    							File file2=new File("C:/Deduplication/TMP/TMP2/");
           	    							File[] lf2=file2.listFiles();
           	    							for (File fle2 : lf2) 
           	    							{
           	    								if (fle2.isFile()) 
               	    				            {
           	    									int c=0;
           	    									File inputFile=new File("C:/Deduplication/TMP/TMP2/"+fle2.getName());
           	    									String h1 = HashGeneratorUtils.generateSHA256(fle2);
                       							 	File f1 = new File("C:/Deduplication/Cache2/");
                       	    				        File[] lF1 = f1.listFiles();
                       	    						for (File file1 : lF1) 
                       	    				        {
                       	    				            if (file1.isFile()) 
                       	    				            {
                       	       							 String h2 = HashGeneratorUtils.generateSHA256(file1);
                       	       							 if(h1.equalsIgnoreCase(h2))
                       	       							 { 
                       	       								c++;
                       	       								System.out.println(fle2.getName()+"  =  "+file1.getName());
                       	       								System.out.println("Similar Block Found");
                       	       								dupBlockName=file1.getName();
                       	       								
                       	       							 }
                       	       							 
                       	    				            }
                       	    				        }
                       	    						if(c>0)
                       	    						{
                       	    							Sql.insertBlock(fname, dupBlockName, "C:/Deduplication/EncryptFile/"+dupBlockName, hash2, "duplicate");
                       	    						}
                       	    						else
                       	    						{
                       	    							File encryptedFile=new File("C:/Deduplication/BlockWise/"+fle2.getName());
                       	    							try 
                       	    							{
                       	    								String key1=(String) h1.subSequence(0, 16);
                       	    								CryptoUtils.encrypt(key1, inputFile, encryptedFile);
                   	    		                       
                       	    							} catch (CryptoException ex) 
                       	    							{
                       	    								System.out.println(ex.getMessage());
                       	    								ex.printStackTrace();
                       	    							}
                       	    							Sql.insertBlock(fname, fle2.getName(), "C:/Deduplication/BlockWise/"+fle2.getName(), hash1, "original");
                       	    						}
               	    				            }
           	    								
           	    							}
           	    							File TMP2Folder=new File("C:/Deduplication/TMP/TMP2/");
           	        				        File[] TMP2Files = TMP2Folder.listFiles();
           	   	    						for (File TMP2file : TMP2Files) 
           	   	    				        {
           	   	    				            if (TMP2file.isFile()) 
           	   	    				            {
           	   	    				            	TMP2file.delete();
           	   	    				            	
           	   	    				            }
           	   	    				        }
           	    							
               	    					}
        				            }
        				            
        				        }
        				        File firstFolder=new File("C:/Deduplication/TMP/");
        				        File[] listOfFiles1 = firstFolder.listFiles();
   	    						for (File file1 : listOfFiles1) 
   	    				        {
   	    				            if (file1.isFile()) 
   	    				            {
   	    				            	file1.delete();
   	    				            	
   	    				            }
   	    				        }
        				        File secondFolder=new File("C:/Deduplication/Cache/");
        				        File[] listOfFiles2 = secondFolder.listFiles();
   	    						for (File file1 : listOfFiles2) 
   	    				        {
   	    				            if (file1.isFile()) 
   	    				            {
   	    				            	file1.delete();
   	    				            	
   	    				            }
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
   	    						
        				        out.println("<script type=\"text/javascript\">");  
        		            	out.println("alert('File is duplicate. perform block wise deduplication & maintain Index..');");  
        		            	out.println("</script>");    
        		                getServletContext().getRequestDispatcher("/file_upload.jsp").include(request, response);
    				        }
    				        else
    				        {
    				        	
    				        	File origFile= new File(filePath);
    				        	File encryptedFile=new File("C:/Deduplication/EncryptFile/"+origFile.getName());
	    		                    try 
	    		                    {
	    		                        CryptoUtils.encrypt(key, origFile, encryptedFile);
	    		                       
	    		                    } catch (CryptoException ex) 
	    		                    {
	    		                        System.out.println(ex.getMessage());
	    		                        ex.printStackTrace();
	    		                    }
	    		                String pow=Sql.getPow();    
    				        	Sql.insertfile(origFile.getName(), username, "C:/Deduplication/EncryptFile/"+origFile.getName(), HashGeneratorUtils.generateSHA256(origFile), key, "original",pow);
    				        	File firstFolder=new File("C:/Deduplication/TMP/");
        				        File[] listOfFiles1 = firstFolder.listFiles();
   	    						for (File file1 : listOfFiles1) 
   	    				        {
   	    				            if (file1.isFile()) 
   	    				            {
   	    				            	file1.delete();
   	    				            	
   	    				            }
   	    				        }
        				        File secondFolder=new File("C:/Deduplication/Cache/");
        				        File[] listOfFiles2 = secondFolder.listFiles();
   	    						for (File file1 : listOfFiles2) 
   	    				        {
   	    				            if (file1.isFile()) 
   	    				            {
   	    				            	file1.delete();
   	    				            	
   	    				            }
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
    				        	out.println("<script type=\"text/javascript\">");  
        		            	out.println("alert('File is not duplicate.File upload Sucessfully..');");  
        		            	out.println("</script>");    
        		                getServletContext().getRequestDispatcher("/file_upload.jsp").include(request, response);
    				        }
    				        
    				       }
    				}
    				}
    			
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }
}