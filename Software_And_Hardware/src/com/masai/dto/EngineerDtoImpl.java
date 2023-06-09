package com.masai.dto;

public class EngineerDtoImpl implements  EngineerDto{
	  private String username;
	    private String password;
	    private String category;
		public EngineerDtoImpl(String username, String password, String category) {
			super();
			this.username = username;
			this.password = password;
			this.category = category;
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
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		@Override
		public String toString() {
			return "Engineer  username=" + username + ", password=" + password + ", category=" + category + " ";
		}
	    
}
