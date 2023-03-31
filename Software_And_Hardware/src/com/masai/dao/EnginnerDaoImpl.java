package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.dto.EngineerDto;
import com.masai.dto.EngineerDtoImpl;

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
		    String msg = null;
		    try {
		        conn = Dbutilis.connectToDb();
		        
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
		            String deleteProblemQuery = "DELETE FROM problem WHERE engineer_id = ?";
		            PreparedStatement deleteProblemStmt = conn.prepareStatement(deleteProblemQuery);
		            deleteProblemStmt.setInt(1, engineer_id);
		            int numDeleted = deleteProblemStmt.executeUpdate();
		            deleteProblemStmt.close();
//		            msg = "Deleted " + numDeleted + " related problem records. ";
		        }
		        
		        // Delete the engineer record
		        String deleteEngineerQuery = "DELETE FROM engineer WHERE engineer_id = ?";
		        PreparedStatement deleteEngineerStmt = conn.prepareStatement(deleteEngineerQuery);
		        deleteEngineerStmt.setInt(1, engineer_id);
		        int numDeleted = deleteEngineerStmt.executeUpdate();
		        deleteEngineerStmt.close();
		        msg = "Deleted " + numDeleted + " engineer record(s).";
		        
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
}