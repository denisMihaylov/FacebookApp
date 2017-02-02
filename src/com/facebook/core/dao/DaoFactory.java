package com.facebook.core.dao;

public class DaoFactory {

	private static UserDao userDao;

	public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}
}
