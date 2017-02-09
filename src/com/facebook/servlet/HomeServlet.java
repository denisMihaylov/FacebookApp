package com.facebook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.core.error.FacebookAppException;
import com.facebook.servlet.dto.UserDTO;

public class HomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int userId = Integer.valueOf(request.getParameter("id"));
			returnJsonInResponse(response, new UserDTO(getUserService().getUserById(userId)));
		} catch (FacebookAppException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
