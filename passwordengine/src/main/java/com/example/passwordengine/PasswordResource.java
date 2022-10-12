package com.example.passwordengine;

public class PasswordResource {

	@PasswordConstraint()
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
