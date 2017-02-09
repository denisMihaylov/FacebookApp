package com.facebook.core.service.impl;

import java.util.List;

import com.facebook.core.dao.DaoFactory;
import com.facebook.core.dao.UserDao;
import com.facebook.core.error.FacebookAppException;
import com.facebook.core.model.User;
import com.facebook.core.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User getUserById(int id) throws FacebookAppException {
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
	public int registerUser(User user) throws FacebookAppException {
		return getDao().addUser(user);
	}

	@Override
	public User getUserByFacebookId(long facebookId) throws FacebookAppException {
		return getDao().getUserByFacebookId(facebookId);
	}

	@Override
	public void updateUserAccessToken(int userId, String accessToken) throws FacebookAppException {
		getDao().updateAccessToken(userId, accessToken);
	}

	private UserDao getDao() {
		return DaoFactory.getUserDao();
	}
}
