package com.masai.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class Dbutilis {
	public static Connection connectToDb() throws SQLException,ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		//step-2
		ResourceBundle rb = ResourceBundle.getBundle("dbdetail");
		return DriverManager.getConnection(rb.getString("url"), rb.getString("username"), rb.getString("password"));
	}
	public static void closeConnection(Connection conn) throws SQLException,ClassNotFoundException{
		if(conn!=null) {
			conn.close();
		}
	}
	
	public static boolean isResultEmpty(ResultSet rs) throws SQLException,ClassNotFoundException{
		
		if(!rs.isBeforeFirst() && rs.getRow()==0) 
			
			return true;
		return false;
		
			
		
	}
}
