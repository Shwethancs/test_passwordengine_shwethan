package com.example.passwordengine.exception;

public class InvalidPasswordException extends Exception {

	private static final long serialVersionUID = -2909846754381904113L;

	public InvalidPasswordException() {
	}

	public InvalidPasswordException(String message) {
		super(message);
	}

}
