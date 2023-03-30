package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.masai.exception.InvalidCredentialsException;

public class Hoddaoimpl implements HodDao {
	@Override
	public String login(String username, String password) throws InvalidCredentialsException {
		// TODO Auto-generated method stub
		Connection conn=null;
		String login="Invalid Credential";
		try {
			conn=Dbutilis.connectToDb();
			String query="SELECT * FROM hod WHERE username = ? AND password = ?";
			PreparedStatement ps= conn.prepareStatement(query);
		ps.setString(1,username);
		ps.setString(2, password);
		ResultSet rs=ps.executeQuery();			
		if(Dbutilis.isResultEmpty(rs)) {
			System.out.println("No data found");
		}
		if(rs.next()) {
			login="Welcome"+" "+rs.getString("username");		
			
		}
		
		
	}catch(SQLException | ClassNotFoundException e) {
		  throw new InvalidCredentialsException("Error in login");
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
}
