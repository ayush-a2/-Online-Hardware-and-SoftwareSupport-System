package com.masai.dao;

import com.masai.exception.InvalidCredentialsException;

public interface HodDao {
	   public String login(String username, String password) throws InvalidCredentialsException;
}
