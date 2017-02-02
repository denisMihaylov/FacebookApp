package com.facebook.core.service;

import com.facebook.core.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	private static UserService userService;
	
	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserServiceImpl();
		}
		return userService;
	}

}