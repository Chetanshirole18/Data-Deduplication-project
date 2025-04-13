package com.deduplication.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deduplication.db.ConnectionFactory;

/**
 * Servlet implementation class Proof
 */
@WebServlet("/Proof")
public class Proof extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
    PreparedStatement pt;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Proof() {
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
		PrintWriter out=response.getWriter();
		String e=request.getParameter("email");
		System.out.println(e);
		HttpSession session=request.getSession(false);
		String nm=(String) session.getAttribute("Tag");
		System.out.println(nm);
		
		try 
		{
			con=ConnectionFactory.getInstance().getConnection();
			String queryString = "select * FROM file_storage WHERE Username='"+e+"'and Proof='"+nm+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(queryString);
			if(rs.next())
			{
				String m="File is stored at:"+rs.getString(4);
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('"+m+"');");  
				out.println("</script>");    
				request.getRequestDispatcher("/file_upload.jsp").include(request, response);
			}
			else
			{
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('You are not authenticated user');");  
			out.println("</script>");    
			request.getRequestDispatcher("/file_upload.jsp").include(request, response);
			}
			} 
		catch (Exception e2) 
		{
			e2.printStackTrace();
		}
	}

}
