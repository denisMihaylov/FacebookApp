package com.facebook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.core.error.FacebookAppException;
import com.facebook.core.model.types.ViewRequest;

public class ViewFeedServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ViewRequest viewRequest = fromJson(request, ViewRequest.class);
		try {
			getFacebookFeedService().viewFeed(viewRequest.getUserId(), viewRequest.getFeedId());
		} catch (FacebookAppException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
		}
	}
}
