package com.springboot.webpush.exception;

public class InvalidFieldException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public InvalidFieldException(final String message) {
		this.setMessage(message);
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}