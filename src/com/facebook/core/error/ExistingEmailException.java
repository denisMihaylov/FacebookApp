package com.facebook.core.error;

public class ExistingEmailException extends FacebookAppException {

	private static final long serialVersionUID = 2936311253393393883L;

	public ExistingEmailException(String message) {
		super(message);
	}

}
