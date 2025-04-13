package com.deduplication.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deduplication.db.ConnectionFactory;

/**
 * Servlet implementation class Mailcheck
 */
@WebServlet("/Mailcheck")
public class Mailcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mailcheck() {
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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String e=request.getParameter("email");
		System.out.println(e);
		
		try 
		{
			Connection con=ConnectionFactory.getInstance().getConnection();
			Statement st=con.createStatement();
			String q="select * from user";
			System.out.println(q);
			ResultSet i=st.executeQuery(q);
			while (i.next())
			{
			if(i.getString(3).contains(e))
			{
				HttpSession session=request.getSession(true);
				session.setAttribute("Email", e);
				System.out.println("Email Exist");
				RequestDispatcher rd=request.getRequestDispatcher("SendPassword.jsp?name="+i.getString(4));
				rd.include(request, response);
			}
			else 
			{
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('Email ID doesnot exist. Provide Correct Email iD');");  
				out.println("</script>");    
				request.getRequestDispatcher("/user_signin.jsp").include(request, response);
			}
			}
		} 
		catch (Exception e2)
		{
			e2.printStackTrace();
		}
	}

}
