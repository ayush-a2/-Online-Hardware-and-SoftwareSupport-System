package com.masai.dao;

import java.sql.SQLException;
import java.util.List;

import com.masai.dto.EmployeeDto;
import com.masai.dto.EngineerDto;
import com.masai.dto.ProblemDto;
import com.masai.exception.InvalidCredentialsException;
import com.masai.exception.SomethingWentWrongException;

public interface EmployeeDao {
public	String addEmployee(EmployeeDto employee) throws SomethingWentWrongException;
public String login(String username, String password) throws InvalidCredentialsException;
public int registerComplaint(String problemDesc, int employeeId,int complaint_Id) throws SQLException, ClassNotFoundException;
public ProblemDto getProblemByComplainId(int complainId) throws SQLException, ClassNotFoundException;
EngineerDto getEngineerById(int engineerId) throws SQLException, ClassNotFoundException;
public String getStatusByComplainId(int complainId, int employeeId) throws SQLException, ClassNotFoundException;
public String getComplaintHistory(int employeeId) throws SQLException, ClassNotFoundException;
public void changePassword(int  employee_id, String Password, String newPassword) throws InvalidCredentialsException;
}
