package com.deduplication.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deduplication.db.ConnectionFactory;

/**
 * Servlet implementation class Adminsignin
 */
@WebServlet("/Adminsignin")
public class Adminsignin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adminsignin() {
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
		response.setContentType("text/html");
		String userName=request.getParameter("email");
		System.out.println(userName);
		String userPass=request.getParameter("password");
		
		try {
			String queryString = "select * FROM admin WHERE Email='"+userName+"'and Password='"+userPass+"'";
			connection =ConnectionFactory.getInstance().getConnection();
			ptmt = connection.prepareStatement(queryString);
			
			resultSet = ptmt.executeQuery();
			if(resultSet.next())
			{
				HttpSession session=request.getSession(true);
				session.setAttribute("userName",userName);
				String nme=(String) session.getAttribute("userName");
				System.out.println(nme);
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('Login Successfully');");  
				out.println("</script>");    
				request.getRequestDispatcher("/admin_home.jsp").include(request, response);
			
				
			}else{
					
				out.print("<h5 align='center'>Wrong Email ID and Password</h5>");
				RequestDispatcher dispatcher=request.getRequestDispatcher("/admin_signin.jsp");
				dispatcher.include(request, response);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
