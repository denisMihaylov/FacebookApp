package com.facebook.servlet.dto;

import com.facebook.core.model.User;

public class UserDTO {
	
	private String firstName;
	private String lastName;
	private int id;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.id = user.getId();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
