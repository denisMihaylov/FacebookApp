package com.facebook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.core.error.FacebookAppException;
import com.facebook.core.model.User;

import facebook4j.FacebookException;

public class LoginServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = fromJson(request, User.class);
		try {
			String accessToken = user.getAccessToken();
			user = getUserService().getUserByFacebookId(user.getFacebookUserId());
			getUserService().updateUserAccessToken(user.getId(), getFacebookClient(accessToken).renewAccessToken());
			returnJsonInResponse(response, user.getId());
		} catch (FacebookAppException | FacebookException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
		}
	}

}
