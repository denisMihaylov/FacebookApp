package com.facebook.core.model;

import com.facebook.core.model.types.Access;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Access access;
	private String accessToken;
	private long facebookUserId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getFacebookUserId() {
		return facebookUserId;
	}

	public void setFacebookUserId(long facebookUserId) {
		this.facebookUserId = facebookUserId;
	}

}