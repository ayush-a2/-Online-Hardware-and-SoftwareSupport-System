package com.masai.dto;

public class EmployeeDtoImpl implements EmployeeDto {
	private String username;
    private String password;
	public EmployeeDtoImpl(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "EmployeeDtoImpl [username=" + username + ", password=" + password + "]";
	}

}
