package com.ktasks.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktasks.data.TaskData;
import com.ktasks.data.UserData;

/**
 * Servlet implementation class updateTaskDetails
 */
@WebServlet("/updateTaskDetails")
public class updateTaskDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB com.ktasks.service.TaskBean taskBean = new com.ktasks.service.TaskBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateTaskDetails() {
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
		String name = request.getParameter("name");
		String note = request.getParameter("note");
		byte completed = (byte) Integer.parseInt(request.getParameter("completed"));
		//byte shared = (byte) Integer.parseInt(request.getParameter("shared"));
		String username = request.getParameter("username");
		String alert = request.getParameter("alert");
		
		// set parameters
		
		TaskData data = new TaskData();
		data.setTaskId(id);
		data.setCompleted(completed);
		data.setTaskName(name);
		data.setTaskNotes(note);
		data.setShared(0);
		data.setUsername(username);
		data.setTaskAlert(alert);
		
		//update task
		String result = taskBean.updateTask(data);
		//post data
		PrintWriter out = response.getWriter();
		out.println(result);
		return;
	}
}
