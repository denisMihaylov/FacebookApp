package com.facebook.core.service;

import com.facebook.core.service.impl.FacebookFeedServiceImpl;
import com.facebook.core.service.impl.UserServiceImpl;

public class ServiceFactory {

	private static UserService userService;
	private static FacebookFeedService facebookFeedService;

	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserServiceImpl();
		}
		return userService;
	}

	public static FacebookFeedService getFacebookFeedService() {
		if (facebookFeedService == null) {
			facebookFeedService = new FacebookFeedServiceImpl();
		}
		return facebookFeedService;
	}

}