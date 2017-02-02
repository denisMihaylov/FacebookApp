package com.facebook.core.service.impl;

import java.util.List;

import com.facebook.core.dao.DaoFactory;
import com.facebook.core.dao.UserDao;
import com.facebook.core.model.User;
import com.facebook.core.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User getUserById(int id) {
		return getDao().getUserById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		return getDao().getUserByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		return getDao().getAllUsers();
	}

	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private UserDao getDao() {
		return DaoFactory.getUserDao();
	}

}
