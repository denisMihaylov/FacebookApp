package com.facebook.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.core.error.FacebookAppException;
import com.facebook.core.model.User;
import com.facebook.core.model.types.Access;
import com.facebook.core.service.ServiceFactory;
import com.facebook.core.service.UserService;
import com.google.gson.Gson;

public class RegistrationServlet extends HttpServlet {
	private static Gson gson = new Gson();
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User newUser;
		if (request.getContentType().contains("application/json")) { //Register with Facebook
			StringBuffer jb = new StringBuffer();
			String line = null;
			try {
				BufferedReader reader = request.getReader();
				while ((line = reader.readLine()) != null)
					jb.append(line);
			} catch (Exception e) {
				e.printStackTrace();
			}
			newUser = gson.fromJson(jb.toString(),User.class);
			newUser.setPassword("FROM FACEBOOK");
		} else { //Register from the form
			newUser = new User();
			newUser.setFirstName(request.getParameter("firstName"));
			newUser.setLastName(request.getParameter("lastName"));
			newUser.setEmail(request.getParameter("email"));
			newUser.setPassword(request.getParameter("password"));
			newUser.setAccessToken(request.getParameter("accessToken"));
		}
		newUser.setAccess(Access.USER);
		try {
			int id = getUserService().registerUser(newUser);
//			response.setContentType("text/xml");
//			response.getWriter()
//	            .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
//	            .printf("<partial-response><redirect url=\"%s\"></redirect></partial-response>", "home.jsp?id=" + id);
//			response.sendRedirect("home.jsp?id=" + id);
//			request.getRequestDispatcher("/home.jsp").forward(request, response);
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(id);
			out.flush();
		} catch (FacebookAppException e) {
			//TODO: check if the login is from facebook -> the user might be in the system
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
	
	private UserService getUserService() {
		return ServiceFactory.getUserService();
	}
	
}
