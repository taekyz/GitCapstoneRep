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
 * Servlet implementation class addContact
 */
@WebServlet("/addContact")
public class addContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB com.ktasks.service.ContactsBean contactsBean = new com.ktasks.service.ContactsBean();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addContact() {
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
		String username = request.getParameter("username1");
		String username2 = request.getParameter("username2");

		//add contact
		contactsBean.addContact(username, username2);
	}

}
