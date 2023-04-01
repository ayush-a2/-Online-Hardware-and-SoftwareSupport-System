package com.masai.dao;

import com.masai.exception.InvalidCredentialsException;
import com.masai.exception.NoRecordFoundException;

public interface HodDao {
	   public String login(String username, String password) throws InvalidCredentialsException,NoRecordFoundException;
}
