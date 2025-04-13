package com.deduplication.dao;

import java.io.BufferedReader;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Random;

import com.deduplication.db.ConnectionFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class Sql 
{
	static Connection conn=null;
	static Statement stmt=null;
	static ResultSet rs=null;
	static PreparedStatement ps=null;
	
	public static ResultSet getcount() 
	{
		try 
		{
			String q="select count(*) from filestorage";
			conn=ConnectionFactory.getInstance().getConnection();
			stmt=conn.prepareStatement(q);
			rs=stmt.executeQuery(q);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return rs;	
	}
	
	public static int insertfile(String filename,String username,String filepath,String sha256Hash,String key,String status,String pow)
	{
		int i=0;
		try{
			
			conn=ConnectionFactory.getInstance().getConnection();
			stmt=(Statement) conn.createStatement();
			i=stmt.executeUpdate("insert into filestorage (filename,username,filepath,hash,encryptkey,status,pow) values('"+filename+"','"+username+"','"+filepath+"','"+sha256Hash+"','"+key+"','"+status+"','"+pow+"')");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}
	
	public static ResultSet checktoken(String token) 
	{
		try 
		{
			String q="select * from filestorage where hash='"+token+"'";
			conn=ConnectionFactory.getInstance().getConnection();
			stmt=conn.prepareStatement(q);
			rs=stmt.executeQuery(q);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return rs;	
	}
	
	public static int insertdup(String filename,String username,String token)
	{
		int i=0;
		try{
			
			conn=ConnectionFactory.getInstance().getConnection();
			stmt=(Statement) conn.createStatement();
			 i=stmt.executeUpdate("insert into dupindex(filename, username, hashvalue) values('"+filename+"','"+username+"','"+token+"')");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}
	
	
	 public static void parsePdf(String pdf, String txt) throws IOException {
	        PdfReader reader = new PdfReader(pdf);
	        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
	        PrintWriter out = new PrintWriter(new FileOutputStream(txt));
	        TextExtractionStrategy strategy;
	        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
	            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
	            out.println(strategy.getResultantText());
	        }
	        out.flush();
	        out.close();
	        reader.close();
	    }
	 
	 public static boolean texttopdf(File file,String filename)
	 {
		 FileInputStream fis=null;
		 DataInputStream in=null;
		 InputStreamReader isr=null;
		 BufferedReader br=null;
		 try 
		 {
			 Document pdfdoc=new Document();
			 String outputfile=file.getParent()+"/"+filename+".pdf";
			 System.out.println("get parent="+file.getParent());
			 PdfWriter writer=PdfWriter.getInstance(pdfdoc, new FileOutputStream(outputfile));
			 pdfdoc.open();
			 
			 pdfdoc.setMarginMirroring(true);
			 pdfdoc.setMargins(36, 72, 108, 180);
			 pdfdoc.topMargin();
			 
			 Font myfont=new Font();
			 Font bold_font=new Font();
			 bold_font.setStyle(Font.BOLD);
			 bold_font.setStyle(10);
			 myfont.setStyle(Font.NORMAL);
			 myfont.setStyle(10);
			 
			 if(file.exists())
			 {
				 fis=new FileInputStream(file);
				 in=new DataInputStream(fis);
				 isr=new InputStreamReader(in);
				 br=new BufferedReader(isr);
				 String strline;
				 
				 while((strline=br.readLine())!= null)
				 {
					Paragraph para=new Paragraph(strline+"\n",myfont);
					para.setAlignment(Element.ALIGN_JUSTIFIED);
					pdfdoc.add(para);
				 }
			 }
			 else
			 {
				 System.out.println("no such file exists");
				 return false;
			}
			 pdfdoc.close();
		 } 
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
		return true;
	}
	 
	 public static ResultSet checkmail(String email) 
		{
			try 
			{
				String q="select * from user where Email='"+email+"'";
				conn=ConnectionFactory.getInstance().getConnection();
				stmt=conn.prepareStatement(q);
				rs=stmt.executeQuery(q);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return rs;	
		}
	 
	 public static ResultSet checkfilename(String filename) 
		{
			try 
			{
				String q="select * from filestorage where filename='"+filename+"'";
				conn=ConnectionFactory.getInstance().getConnection();
				stmt=conn.prepareStatement(q);
				rs=stmt.executeQuery(q);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return rs;	
		}
	 
	 public static ResultSet gethash(String id) 
		{
			try 
			{
				String q="select count(*) from file_storage where Fileid='"+id+"'";
				conn=ConnectionFactory.getInstance().getConnection();
				stmt=conn.prepareStatement(q);
				rs=stmt.executeQuery(q);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return rs;	
		}
	 public static String getPow()
	 {
		
		 Random r=new Random();
         int n=r.nextInt();
         String key=n+"";
		 
		 return key;
	 }
	 public static boolean insertBlock(String filename, String blockName, String blockPath, String hash, String status) throws SQLException
		{
			boolean result=false;
			conn=ConnectionFactory.getInstance().getConnection();
			stmt=(Statement) conn.createStatement();
			int r=stmt.executeUpdate("insert into blockindex(filename, blockName, bolckPath, hash, status)values('"+filename+"','"+blockName+"','"+blockPath+"','"+hash+"','"+status+"')");
			if(r>0)
			{
				result=true;
			}
			return result;
			
		}
}
