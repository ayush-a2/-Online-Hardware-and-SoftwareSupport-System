package com.masai.dao;

import java.sql.SQLException;
import java.util.List;

import com.masai.dto.EngineerDto;
import com.masai.dto.ProblemDto;
import com.masai.exception.InvalidCredentialsException;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface  Enginnerdao {
 public String addEngineer(EngineerDto engineer) throws SomethingWentWrongException;
	 public List<EngineerDto> getAllEngineers() throws NoRecordFoundException;
	 public String deleteEngineer(int engineer_Id) throws NoRecordFoundException;
	 public String login(String username, String password) throws InvalidCredentialsException,NoRecordFoundException;
	 public List<ProblemDto> getProblemsByEngineerId(int engineerId) throws SQLException, ClassNotFoundException;
	  public void updateProblemStatus(int problem_Id, String Status) throws SQLException, ClassNotFoundException ;
	  public List<ProblemDto> getProblemsAttendedByEngineer(int engineerId) throws SQLException, ClassNotFoundException;
	  public void changePassword(int engineerId, String oldPassword, String newPassword) throws InvalidCredentialsException;
	  public String getComplaintsForEngineer(int engineerId) throws SQLException, ClassNotFoundException;
}
