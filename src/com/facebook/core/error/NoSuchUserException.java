package com.facebook.core.error;

public class NoSuchUserException extends FacebookAppException {

	private static final long serialVersionUID = 1L;

	public NoSuchUserException(String message) {
		super(message);
	}

}
