package com.ktasks.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktasks.data.TaskData;

/**
 * Servlet implementation class updateTaskComplete
 */
@WebServlet("/updateTaskComplete")
public class updateTaskComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB com.ktasks.service.TaskBean taskBean = new com.ktasks.service.TaskBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateTaskComplete() {
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
		
		// get parameters
		int id	= Integer.parseInt(request.getParameter("id"));
		byte completed = (byte) Integer.parseInt(request.getParameter("completed"));
		
		// add task
		taskBean.taskCompleted(id, completed);
	}

}
