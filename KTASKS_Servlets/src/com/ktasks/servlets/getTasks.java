package com.ktasks.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ejb.EJB;
import com.ktasks.data.*;

import org.json.*;

/**
 * Servlet implementation class getTasks
 */
@WebServlet("/getTasks")
public class getTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB com.ktasks.service.TaskBean taskBean = new com.ktasks.service.TaskBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTasks() {
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
		//set headers
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/javascript");
		
		JSONArray resultList = null;
		
		// get parameters
		String username	= request.getParameter("username");
		
		//get data
		List<TaskData> result = taskBean.getUsersTask(username);
		resultList = new JSONArray(result);
		
		//post data
		PrintWriter out = response.getWriter();
		out.println(resultList);
	}

}
