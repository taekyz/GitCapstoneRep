package com.ktasks.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateTaskShared
 */
@WebServlet("/updateTaskShared")
public class updateTaskShared extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB com.ktasks.service.TaskBean taskBean = new com.ktasks.service.TaskBean();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateTaskShared() {
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
		byte shared = (byte) Integer.parseInt(request.getParameter("shared"));
		
		// add task
		taskBean.taskShared(id, shared);
	}
}
