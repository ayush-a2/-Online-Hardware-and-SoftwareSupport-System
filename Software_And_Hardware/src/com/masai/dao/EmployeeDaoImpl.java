package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.masai.dto.EmployeeDto;
import com.masai.dto.EngineerDto;
import com.masai.dto.EngineerDtoImpl;
import com.masai.dto.ProblemDto;
import com.masai.dto.ProblemDtoImpl;
import com.masai.exception.InvalidCredentialsException;
import com.masai.exception.SomethingWentWrongException;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public String addEmployee(EmployeeDto employee) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		Connection conn=null;
		String msg="Soory Unable To registetr";
		try {
			conn=Dbutilis.connectToDb();
			   String query = "INSERT INTO employee (username, password) VALUES (?, ?)";
			PreparedStatement ps= conn.prepareStatement(query);
		      ps.setString(1, employee.getUsername());
		        ps.setString(2, employee.getPassword());
		        int affectedRows = ps.executeUpdate();
	
		
		if (affectedRows == 0) {
            throw new SomethingWentWrongException("Employee registration failed, no rows affected.");
        }else {
        msg="Employee_Register_Suceesfully"+" "+employee.getUsername();	
        }
	
		
		
	}catch(SQLException | ClassNotFoundException e) {
		  throw new SomethingWentWrongException("Error in registration");
	}finally {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
return msg;
	}

	@Override
	public String login(String username, String password) throws InvalidCredentialsException {
		// TODO Auto-generated method stub
		Connection conn=null;
		String login="Invalid Credential";
		try {
			conn=Dbutilis.connectToDb();
			String query="SELECT * from employee WHERE username = ? AND password = ?";
			PreparedStatement ps= conn.prepareStatement(query);
		ps.setString(1,username);
		ps.setString(2, password);
		ResultSet rs=ps.executeQuery();			
		if(Dbutilis.isResultEmpty(rs)) {
			System.out.println("No data found");
			throw new InvalidCredentialsException("Incorret Username And Password");
		}
		if(rs.next()) {
			login="Welcome"+" "+rs.getString("username");		
			
		}
		
		
	}catch(SQLException | ClassNotFoundException e) {
		  throw new InvalidCredentialsException("Error in login");
	}finally {
		if(conn!=null) {
			try {
				try {
					Dbutilis.closeConnection(conn);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
return login;
	}
	@Override
    public int registerComplaint(String problemDesc, int employeeId,int complaint_Id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        int complaintId = -1;
        try {
            conn = Dbutilis.connectToDb();
            String query = "INSERT INTO problem(complain_id, problem_desc, engineer_id) VALUES(?, ?, NULL)";
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, complaint_Id);
            ps.setString(2, problemDesc);
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Adding complaint failed, no rows affected.");
            }
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                complaintId = generatedKeys.getInt(1);
                System.out.println("Problem registered successfully with ID: " + complaintId);
            } else {
                throw new SQLException("Adding complaint failed, no ID obtained.");
            }
            query = "INSERT INTO employee_problem(employee_id, problem_id) VALUES(?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, employeeId);
            ps.setInt(2, complaintId);
            rowsInserted = ps.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Adding complaint to employee failed, no rows affected.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    Dbutilis.closeConnection(conn);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return complaintId;
    }

    @Override
    public ProblemDto getProblemByComplainId(int complainId) throws SQLException, ClassNotFoundException {
    	Connection connection=Dbutilis.connectToDb();
    
        String query = "SELECT * FROM problem WHERE complain_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, complainId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int problemId = resultSet.getInt("problem_id");
                    String problemDesc = resultSet.getString("problem_desc");
                    String status = resultSet.getString("status");
                    int engineerId = resultSet.getInt("engineer_id");
                    return new ProblemDtoImpl(problemId, complainId, problemDesc, status, engineerId);
                } else {
                    return null;
                }
            }
        }
	
}

    @Override
    public EngineerDto getEngineerById(int engineerId) throws SQLException, ClassNotFoundException {
    	 Connection conn = null;
 	    conn = Dbutilis.connectToDb();
        String query = "SELECT * FROM engineer WHERE engineer_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, engineerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String category = resultSet.getString("category");
                    return new EngineerDtoImpl(username, password, category);
                }
                return null;
            }
        }
}

	@Override
	public String getStatusByComplainId(int complainId, int employeeId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		 
		 Connection conn = null;
	 	    conn = Dbutilis.connectToDb();
		String query = "SELECT * FROM employee_problem ep JOIN problem p ON ep.problem_id = p.problem_id WHERE ep.employee_id = ? AND p.complain_id = ?";
		    try (PreparedStatement statement = conn.prepareStatement(query)) {
		        statement.setInt(1, employeeId);
		        statement.setInt(2, complainId);
		        try (ResultSet resultSet = statement.executeQuery()) {
		            if (resultSet.next()) {
		                int engineerId = resultSet.getInt("engineer_id");
		                EmployeeDao ed=new EmployeeDaoImpl();
		                EngineerDto engineerDto = ed.getEngineerById(engineerId);
		                String engineerName = (engineerDto != null) ? engineerDto.getUsername() : "Not Assigned";
		                String status = resultSet.getString("status");
		                return "Status: " + status + ", Assigned Engineer: " + engineerName;
		            } else {
		                return "No problem found with complain ID: " + complainId + " and employee ID: " + employeeId;
		            }
		        }
		    }
	}
	public String getComplaintHistory(int employeeId) throws SQLException, ClassNotFoundException {
		  Connection conn = null;
		    String result = "";
		    try {
		        conn = Dbutilis.connectToDb();
		        String query = "SELECT p.problem_id, p.complain_id, p.problem_desc, p.status, e.username as engineer_username " +
		                       "FROM problem p " +
		                       "JOIN engineer e ON p.engineer_id = e.engineer_id " +
		                       "JOIN employee_problem ep ON p.problem_id = ep.problem_id " +
		                       "WHERE ep.employee_id = ?";
		        PreparedStatement stmt = conn.prepareStatement(query);
		        stmt.setInt(1, employeeId);
		        ResultSet rs = stmt.executeQuery();
		        if (!rs.next()) {
		            result = "No complaints raised by this employee.";
		        } else {
		            result = "Complaint history for employee " + employeeId + ":\n";
		            do {
		                int problem_id = rs.getInt("problem_id");
		                int complain_id = rs.getInt("complain_id");
		                String problem_desc = rs.getString("problem_desc");
		                String status = rs.getString("status");
		                String engineer_username = rs.getString("engineer_username");
		                result += String.format("Problem ID: %d, Complain ID: %d, Description: %s, Status: %s, Engineer: %s\n", problem_id, complain_id, problem_desc, status, engineer_username);
		            } while (rs.next());
		        }
		        rs.close();
		        stmt.close();
		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		        result = "Error: " + e.getMessage();
		    } finally {
		        try {
		            if (conn != null) {
		                conn.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return result;
	}

	@Override
	public void changePassword(int employee_id, String Password, String newPassword)
			throws InvalidCredentialsException {
		// TODO Auto-generated method stub
		 Connection conn = null;
		 try {
		     conn = Dbutilis.connectToDb();
		     String query = "SELECT username FROM employee  WHERE employee_id = ?";
		     PreparedStatement ps = conn.prepareStatement(query);
		     ps.setInt(1, employee_id);
		     ResultSet rs = ps.executeQuery();
		     String username = "";
		     if (rs.next()) {
		         username = rs.getString("username");
		     }
		     query = "UPDATE employee SET password = ? WHERE employee_id = ?";
		     ps = conn.prepareStatement(query);
		     ps.setString(1, newPassword);
		     ps.setInt(2, employee_id);
		     int rowsUpdated = ps.executeUpdate();
		     if (rowsUpdated == 0) {
		         throw new SQLException("Password update failed, please try again later.");
		     }
		     System.out.println("Password updated successfully for employee " + username + " with ID: " + employee_id);
		 } catch (SQLException | ClassNotFoundException e) {
		     throw new InvalidCredentialsException("Error changing password");
		 } finally {
		     if (conn != null) {
		         try {
		             Dbutilis.closeConnection(conn);
		         } catch (SQLException | ClassNotFoundException e) {
		             e.printStackTrace();
		         }
		     }
		 }
		 
		 
	 }
	}

