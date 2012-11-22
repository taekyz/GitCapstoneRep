package com.ktasks.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ktasks.data.*;

/**
 * Servlet implementation class deleteTask
 */
@WebServlet("/deleteTask")
public class deleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB com.ktasks.service.TaskBean taskBean = new com.ktasks.service.TaskBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");


		String result = taskBean.deleteTask(id, username);
		
		//post data
		PrintWriter out = response.getWriter();
		out.println(result);
		return;
	}

}
