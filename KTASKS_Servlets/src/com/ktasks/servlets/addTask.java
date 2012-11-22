package com.ktasks.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktasks.data.TaskData;

/**
 * Servlet implementation class addTask
 */
@WebServlet("/addTask")
public class addTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB com.ktasks.service.TaskBean taskBean = new com.ktasks.service.TaskBean();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addTask() {
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
		String name = request.getParameter("name");
		String note = request.getParameter("note");
		byte completed = (byte) Integer.parseInt(request.getParameter("completed"));
		byte shared = (byte) Integer.parseInt(request.getParameter("shared"));
		String username = request.getParameter("username");
		String alert = request.getParameter("alert");
		
		// set parameters
		TaskData data = new TaskData();
		data.setCompleted(completed);
		data.setTaskName(name);
		data.setTaskNotes(note);
		data.setShared(shared);
		data.setUsername(username);
		data.setTaskAlert(alert);
		
		//add task
		String result = taskBean.addTask(data);
		
		//post data
		PrintWriter out = response.getWriter();
		out.println(result);
		return;
	}

}
