package com.zs.apsg.dto;

public class LoginDto {

	String userName;
	String password;

	public LoginDto() {
		System.out.println(this.getClass().getSimpleName() + " Constructor Called");
	}

	@Override
	public String toString() {
		return "LoginDto [userName=" + userName + ", password=" + password + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
