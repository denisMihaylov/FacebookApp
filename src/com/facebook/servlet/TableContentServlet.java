package com.facebook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.core.error.FacebookAppException;
import com.facebook.core.model.FacebookFeedEntry;

public class TableContentServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int userId = Integer.valueOf(request.getParameter("id"));
			List<FacebookFeedEntry> result = getFacebookFeedService().getFacebookFeedFromAllGroups(userId);
			returnJsonInResponse(response, result);
		} catch (FacebookAppException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
