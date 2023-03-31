package com.masai.dao;

import java.util.List;

import com.masai.dto.EngineerDto;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface  Enginnerdao {
	 public String addEngineer(EngineerDto engineer) throws SomethingWentWrongException;
	  public List<EngineerDto> getAllEngineers() throws NoRecordFoundException;
	  public String deleteEngineer(int engineer_Id) throws NoRecordFoundException;

}
