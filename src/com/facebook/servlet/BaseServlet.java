package com.facebook.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.core.service.ServiceFactory;
import com.facebook.core.service.UserService;
import com.google.gson.Gson;

public abstract class BaseServlet extends HttpServlet {
	protected static Gson gson = new Gson();

	private static final long serialVersionUID = 1L;

	protected UserService getUserService() {
		return ServiceFactory.getUserService();
	}

	protected <T> T fromJson(HttpServletRequest request, Class<T> clazz) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.fromJson(jb.toString(), clazz);
	}

	protected void returnJsonInResponse(HttpServletResponse response, Object object) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(object));
		out.flush();
	}

}
