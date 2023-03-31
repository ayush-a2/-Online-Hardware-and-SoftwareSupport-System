package com.masai.dao;

import java.sql.SQLException;
import java.util.List;

import com.masai.dto.ProblemDto;
import com.masai.exception.NoRecordFoundException;

public interface Problemdao {
	List<ProblemDto> getAllProblems() throws NoRecordFoundException, SQLException, ClassNotFoundException;
 void updateProblem(ProblemDto problem) throws SQLException;
 public String assignProblem(int problemId, int engineerId) throws NoRecordFoundException ;	 

public ProblemDto getProblemById(int problem_Id) throws SQLException, ClassNotFoundException, NoRecordFoundException;
}
