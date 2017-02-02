package com.facebook.core.dao;

public class DaoFactory {
	
	private static UserDao userDao = new UserDao();

	public static UserDao getUserDao() {
		return userDao;
	}
}
