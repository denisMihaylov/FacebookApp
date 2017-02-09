package com.facebook.core.dao;

public class DaoFactory {

	private static UserDao userDao;
	private static FacebookFeedDao facebookFeedDao;

	public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	public static FacebookFeedDao getFacebookFeedDao() {
		if (facebookFeedDao == null) {
			facebookFeedDao = new FacebookFeedDao();
		}
		return facebookFeedDao;
	}
}
