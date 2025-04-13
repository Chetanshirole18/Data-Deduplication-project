package com.deduplication.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deduplication.dao.Sql;
import com.deduplication.db.ConnectionFactory;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String filename=request.getParameter("filename");
		String hash=request.getParameter("hash");
		String status=request.getParameter("status");
		String filePath=request.getParameter("filepath");
		if(status.equalsIgnoreCase("duplicate"))
		{
			try 
			{
				String q="delete from filestorage where id='"+id+"'";
				Connection conn=ConnectionFactory.getInstance().getConnection();
				Statement stmt=conn.createStatement();
				int res=stmt.executeUpdate(q);
				if(res>0)
				{
					String query="delete from dupindex where filename='"+filename+"'";
					Statement stmt1=conn.createStatement();
					int counter=stmt1.executeUpdate(query);
					if(counter>0)
					{
						PrintWriter out = response.getWriter();  
						response.setContentType("text/html");  
						out.println("<script type=\"text/javascript\">");  
						out.println("alert('File Delete Sucessfully...');");
						out.println("location='Own_files.jsp';");
						out.println("</script>");
					}
					
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
		if(status.equalsIgnoreCase("duplicateBlock"))
		{
			try 
			{
				String q="delete from filestorage where id='"+id+"'";
				Connection conn=ConnectionFactory.getInstance().getConnection();
				Statement stmt=conn.createStatement();
				int res=stmt.executeUpdate(q);
				if(res>0)
				{
					ArrayList<String> flist=new ArrayList<String>();
					String query1="select blockName from blockindex where filename='"+filename+"'";
					Statement smt2=conn.createStatement();
					ResultSet rs2=smt2.executeQuery(query1);
					while(rs2.next())
					{
						flist.add(rs2.getString(1));
					}
					
				    String query2="delete from blockindex where filename='"+filename+"'";
					Statement stmt1=conn.createStatement();
					int counter=stmt1.executeUpdate(query2);
					if(counter>0)
					{
						File folder = new File("C:/Deduplication/BlockWise/");
				        File[] listOfFiles = folder.listFiles();

				        for (File file : listOfFiles) 
				        {
				                Iterator<String> itr=flist.iterator();
				            	while(itr.hasNext())
				            	{
				            		if(file.getName().equalsIgnoreCase(itr.next()))
					            	{
					            		file.delete();
					            		System.out.println("delete");
					            	}
				            	}
				            	
				            
				        }
						PrintWriter out = response.getWriter();  
						response.setContentType("text/html");  
						out.println("<script type=\"text/javascript\">");  
						out.println("alert('File Delete Sucessfully...');");
						out.println("location='Own_files.jsp';");
						out.println("</script>");
					}
					
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
		if(status.equalsIgnoreCase("original"))
		{
			try 
			{
				int tmpid = 0;
				String q="select * from filestorage where hash='"+hash+"' and status='duplicate'";
				Connection conn=ConnectionFactory.getInstance().getConnection();
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery(q);
				int c=0;
				while(rs.next())
				{
					tmpid=rs.getInt(1);
					c++;
				}
				if(c>0)
				{
					String q2="update filestorage set status='original' where id='"+tmpid+"' ";
					Statement smt=conn.createStatement();
					int res=smt.executeUpdate(q2);
					if(res>0)
					{
						String sql="delete from filestorage where id='"+id+"'";
						Statement stmt1=conn.createStatement();
						int r=stmt1.executeUpdate(sql);
						if(r>0)
						{
							PrintWriter out = response.getWriter();  
							response.setContentType("text/html");  
							out.println("<script type=\"text/javascript\">");  
							out.println("alert('File Delete Sucessfully...');");
							out.println("location='Own_files.jsp';");
							out.println("</script>");
						}
					}
				}
				else
				{
					String sql="delete from filestorage where id='"+id+"'";
					Statement stmt1=conn.createStatement();
					int r=stmt1.executeUpdate(sql);
					if(r>0)
					{
						PrintWriter out = response.getWriter();  
						response.setContentType("text/html");  
						out.println("<script type=\"text/javascript\">");  
						out.println("alert('File Delete Sucessfully...');");
						out.println("location='Own_files.jsp';");
						out.println("</script>");
					}
					File f2=new File(filePath);
					f2.delete();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
