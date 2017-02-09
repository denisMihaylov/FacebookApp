package com.facebook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.core.error.FacebookAppException;
import com.facebook.core.model.User;
import com.facebook.core.model.types.Access;

import facebook4j.FacebookException;

public class RegistrationServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User newUser;
		try {
			if (request.getContentType().contains("application/json")) {
				// Register with Facebook
				newUser = fromJson(request, User.class);
				newUser.setAccessToken(getFacebookClient(newUser.getAccessToken()).renewAccessToken());
				newUser.setPassword("FROM FACEBOOK");
			} else { // Register from the form
				newUser = new User();
				newUser.setFirstName(request.getParameter("firstName"));
				newUser.setLastName(request.getParameter("lastName"));
				newUser.setEmail(request.getParameter("email"));
				newUser.setPassword(request.getParameter("password"));
				newUser.setAccessToken(request.getParameter("accessToken"));
			}
			newUser.setAccess(Access.USER);
			int id = getUserService().registerUser(newUser);
			returnJsonInResponse(response, id);
		} catch (FacebookAppException e) {
			// TODO: check if the login is from facebook -> the user might be in
			// the system
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (FacebookException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
		}
	}

}
