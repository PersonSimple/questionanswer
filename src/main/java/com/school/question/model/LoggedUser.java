package com.school.question.model;

import java.util.List;

public class LoggedUser {
	private String userName;
	private List<String> roles;
	
	
	public LoggedUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public LoggedUser(String userName, List<String> roles) {
		super();
		this.userName = userName;
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	

}
