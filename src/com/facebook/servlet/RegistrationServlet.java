package com.facebook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.core.error.FacebookAppException;
import com.facebook.core.model.User;
import com.facebook.core.model.types.Access;
import com.facebook.core.service.ServiceFactory;
import com.facebook.core.service.UserService;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User newUser = getUserFromRequest(request);
		newUser.setAccess(Access.USER);
		try {
			getUserService().registerUser(newUser);
			request.getRequestDispatcher("/home.jsp").forward(request, response);
		} catch (FacebookAppException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
	
	private UserService getUserService() {
		return ServiceFactory.getUserService();
	}
	
	private User getUserFromRequest(HttpServletRequest request) {
		User result = new User();
		result.setFirstName(request.getParameter("firstName"));
		result.setLastName(request.getParameter("lastName"));
		result.setEmail(request.getParameter("email"));
		result.setPassword(request.getParameter("password"));
		return result;
	}

}
