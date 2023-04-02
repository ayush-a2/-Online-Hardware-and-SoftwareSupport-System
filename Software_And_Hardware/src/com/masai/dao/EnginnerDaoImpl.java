package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.dto.EngineerDto;
import com.masai.dto.EngineerDtoImpl;
import com.masai.dto.ProblemDto;
import com.masai.dto.ProblemDtoImpl;
import com.masai.exception.InvalidCredentialsException;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class EnginnerDaoImpl implements Enginnerdao{


	@Override
	public String addEngineer(EngineerDto engineer) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		Connection conn=null;
		String login="Soory Unable To registetr";
		try {
			conn=Dbutilis.connectToDb();
			   String query = "INSERT INTO engineer (username, password, category) VALUES (?, ?, ?)";
			PreparedStatement ps= conn.prepareStatement(query);
	       ps.setString(1, engineer.getUsername());
	        ps.setString(2, engineer.getPassword());
	        ps.setString(3, engineer.getCategory());
	
		if(ps.executeUpdate()==0) {
			throw new SomethingWentWrongException("Something Wronmg try Again later");
		}else {
		login ="Enginner_Register_Suceesfully"+" "+engineer.getUsername();
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
return login;
}

	@Override
	public List<EngineerDto> getAllEngineers() throws NoRecordFoundException {
		// TODO Auto-generated method stub
		Connection conn=null;
		List<EngineerDto> list=new ArrayList<>();
		try {
			conn=Dbutilis.connectToDb();
			 String query = "SELECT * FROM engineer";
			 PreparedStatement ps =conn.prepareStatement(query);
			 ResultSet rs =ps.executeQuery();
			 if(Dbutilis.isResultEmpty(rs)) {
				 System.out.println("No Enginner found");
			 }
			 while(rs.next()) {
				 
	                list.add(new EngineerDtoImpl(rs.getString("username"),rs.getString("password"),rs.getString("category") ));	 
			 }
		}catch(SQLException | ClassNotFoundException e) {
			throw new NoRecordFoundException("No Record Found");
			
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
		return list;
	}

	@Override
	public String deleteEngineer(int engineer_id) throws NoRecordFoundException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String msg ="";
		try {
		    conn = Dbutilis.connectToDb();

		    // Retrieve the engineer's name
		    String getEngineerNameQuery = "SELECT username FROM engineer WHERE engineer_id = ?";
		    PreparedStatement getEngineerNameStmt = conn.prepareStatement(getEngineerNameQuery);
		    getEngineerNameStmt.setInt(1, engineer_id);
		    ResultSet getEngineerNameRs = getEngineerNameStmt.executeQuery();
		    getEngineerNameRs.next();
		    String engineerName = getEngineerNameRs.getString("username");
		    getEngineerNameRs.close();
		    getEngineerNameStmt.close();

		    // Check if there are any related records in the problem table
		    String checkQuery = "SELECT COUNT(*) FROM problem WHERE engineer_id = ?";
		    PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
		    checkStmt.setInt(1, engineer_id);
		    ResultSet checkRs = checkStmt.executeQuery();
		    checkRs.next();
		    int numRelatedRecords = checkRs.getInt(1);
		    checkRs.close();
		    checkStmt.close();

		    // Delete any related records in the problem table
		    if (numRelatedRecords > 0) {
		        String deleteProblemQuery = "DELETE FROM Engineer WHERE engineer_id = ?";
		        PreparedStatement deleteProblemStmt = conn.prepareStatement(deleteProblemQuery);
		        deleteProblemStmt.setInt(1, engineer_id);
		        int numDeleted = deleteProblemStmt.executeUpdate();
		        deleteProblemStmt.close();
		        msg = "Deleted " + numDeleted + " related problem records. ";
		    }

		    // Delete the engineer record
		    String deleteEngineerQuery = "DELETE FROM engineer WHERE engineer_id = ?";
		    PreparedStatement deleteEngineerStmt = conn.prepareStatement(deleteEngineerQuery);
		    deleteEngineerStmt.setInt(1, engineer_id);
		    int numDeleted = deleteEngineerStmt.executeUpdate();
		    deleteEngineerStmt.close();
		    msg += "Deleted " + numDeleted + " engineer record(s) for " + engineerName + ".";

		} catch (SQLException | ClassNotFoundException e) {
		    throw new NoRecordFoundException("No record found");
		} finally {
		    try {
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		return msg;
}

	@Override
	public String login(String username, String password) throws InvalidCredentialsException, NoRecordFoundException {
		// TODO Auto-generated method stub
		Connection conn=null;
		String login="Invalid Credential";
		try {
			conn=Dbutilis.connectToDb();
			String query="SELECT * from engineer WHERE username = ? AND password = ?";
			PreparedStatement ps= conn.prepareStatement(query);
		ps.setString(1,username);
		ps.setString(2, password);
		ResultSet rs=ps.executeQuery();			
		if(Dbutilis.isResultEmpty(rs)) {
			System.out.println("No data found");
			throw new NoRecordFoundException("Invalid Username and Password");
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
	public List<ProblemDto> getProblemsByEngineerId(int engineer_Id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		   Connection conn = null;
		    List<ProblemDto> problems = new ArrayList<>();
		    try {
		        conn = Dbutilis.connectToDb();
		        String query = "SELECT * FROM problem WHERE engineer_Id = ?";
		        PreparedStatement ps = conn.prepareStatement(query);
		        ps.setInt(1, engineer_Id);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            ProblemDto problem = new ProblemDtoImpl(rs.getInt("problem_id"),rs.getInt("complain_id"),rs.getString("problem_desc"),rs.getString("status"),rs.getInt("engineer_id"));
		 
		            problems.add(problem);
		        }
		    } finally {
		        if (conn != null) {
		          Dbutilis.closeConnection(conn);
		        }
		    }
		    return problems;
	}
	 @Override
	    public void updateProblemStatus(int problem_Id, String Status) throws SQLException, ClassNotFoundException {
	        Connection conn = null;
	        PreparedStatement ps = null;
	        try {
	            conn = Dbutilis.connectToDb();
	            String query = "UPDATE problem SET status = ? WHERE problem_id = ?";
	            ps = conn.prepareStatement(query);
	            ps.setString(1, Status);
	            ps.setInt(2, problem_Id);
	            int updatedRows = ps.executeUpdate();
	            if (updatedRows == 0) {
	                throw new SQLException("No rows updated");
	            }
	            System.out.println("Updated SucessFully");
	        } finally {
	          Dbutilis.closeConnection(conn);
	        }
	    }
	 @Override
	 public List<ProblemDto> getProblemsAttendedByEngineer(int engineerId) throws SQLException, ClassNotFoundException {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    List<ProblemDto> problems = new ArrayList<>();
		    try {
		        conn = Dbutilis.connectToDb();
		        String query = "SELECT * FROM problem WHERE  engineer_id= ?";
		        stmt = conn.prepareStatement(query);
		        stmt.setInt(1, engineerId);
		        rs = stmt.executeQuery();
		        while (rs.next()) {
		            ProblemDto problem = new ProblemDtoImpl(rs.getInt("problem_id"), rs.getInt("complain_id"), rs.getString("problem_desc"), rs.getString("status"), rs.getInt("engineer_id"));
		            problem.setStatus(rs.getString("status"));
		            problems.add(problem);
		        }
		    } finally {
		      Dbutilis.closeConnection(conn);
		    }
		    return problems;
		}
	 @Override
	 public void changePassword(int  engineer_id, String Password, String newPassword) throws InvalidCredentialsException {
//		    Connection conn = null;
//		    try {
//		        conn = Dbutilis.connectToDb();
//		        String query = "SELECT * FROM engineer WHERE engineer_id = ? AND password = ?";
//		        PreparedStatement ps = conn.prepareStatement(query);
//		        ps.setInt(1,  engineer_id);
//		        ps.setString(2, Password);
//		        ResultSet rs = ps.executeQuery();
//		        if (Dbutilis.isResultEmpty(rs)) {
//		            throw new InvalidCredentialsException("Invalid old password");
//		        }
//		        query = "UPDATE engineer SET password = ? WHERE  engineer_id = ?";
//		        ps = conn.prepareStatement(query);
//		        ps.setString(1, newPassword);
//		        ps.setInt(2,  engineer_id);
//		        int rowsUpdated = ps.executeUpdate();
//		        if (rowsUpdated == 0) {
//		            throw new SQLException("Password update failed, please try again later.");
//		        }
//		        System.out.println("Password updated successfully for engineer with ID: " +  engineer_id);
//		    } catch (SQLException | ClassNotFoundException e) {
//		        throw new InvalidCredentialsException("Error changing password");
//		    } finally {
//		        if (conn != null) {
//		            try {
//		              Dbutilis.closeConnection(conn);
//		            } catch (SQLException | ClassNotFoundException e) {
//		                e.printStackTrace();
//		            }
//		        }
//		    }
//		}
		 Connection conn = null;
		 try {
		     conn = Dbutilis.connectToDb();
		     String query = "SELECT username FROM engineer WHERE engineer_id = ?";
		     PreparedStatement ps = conn.prepareStatement(query);
		     ps.setInt(1, engineer_id);
		     ResultSet rs = ps.executeQuery();
		     String username = "";
		     if (rs.next()) {
		         username = rs.getString("username");
		     }
		     query = "UPDATE engineer SET password = ? WHERE engineer_id = ?";
		     ps = conn.prepareStatement(query);
		     ps.setString(1, newPassword);
		     ps.setInt(2, engineer_id);
		     int rowsUpdated = ps.executeUpdate();
		     if (rowsUpdated == 0) {
		         throw new SQLException("Password update failed, please try again later.");
		     }
		     System.out.println("Password updated successfully for engineer " + username + " with ID: " + engineer_id);
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

	@Override
	public String getComplaintsForEngineer(int engineerId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		 Connection conn = null;
		    conn = Dbutilis.connectToDb();
		 StringBuilder result = new StringBuilder();
		    String query = "SELECT p.problem_id, p.complain_id, p.problem_desc, p.status, e.username as engineer_username " +
		                   "FROM problem p " +
		                   "JOIN engineer e ON p.engineer_id = e.engineer_id " +
		                   "WHERE p.engineer_id = ?";
		    try (PreparedStatement statement = conn.prepareStatement(query)) {
		        statement.setInt(1, engineerId);
		        try (ResultSet resultSet = statement.executeQuery()) {
		            while (resultSet.next()) {
		                int problemId = resultSet.getInt("problem_id");
		                int complainId = resultSet.getInt("complain_id");
		                String problemDesc = resultSet.getString("problem_desc");
		                String status = resultSet.getString("status");
		                String engineerUsername = resultSet.getString("engineer_username");
		                result.append(String.format("Problem ID: %d | Complain ID: %d | Problem Description: %s | Status: %s | Assigned Engineer: %s%n", 
		                	problemId, complainId, problemDesc, status, engineerUsername));
		            }
		        }
		    }
		    return result.toString();
	}
	
}