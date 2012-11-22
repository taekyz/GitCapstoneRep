package com.ktasks.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.ktasks.data.TaskData;
import com.ktasks.data.UserData;

/**
 * Servlet implementation class getUserDetails
 */
@WebServlet("/getUserDetails")
public class getUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB com.ktasks.service.UserBean userBean = new com.ktasks.service.UserBean();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUserDetails() {
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
		
		// get data
		List<UserData> result = new LinkedList<UserData>();
		result.add(userBean.getUserDetails(username));
		try{
			resultList = new JSONArray(result);
		} catch (Exception e){
			System.out.print("DEBUG: OMG OMG");
			return;
		}
		
		//post data
		PrintWriter out = response.getWriter();
		out.println(resultList); 
		return;
	}

}
