package com.facebook.core.service;

import java.util.List;

import com.facebook.core.model.User;

public interface UserService {
	
	public User getUserById(int id);
	public User getUserByEmail(String email);
	public List<User> getAllUsers();
	public void registerUser(User user);

}
