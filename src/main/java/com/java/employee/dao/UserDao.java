package com.java.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class UserDao {

	private static PreparedStatement prepareStatement =null;
    private static ResultSet resultset=null;
    private static final String USER_QUERY = "SELECT role FROM user WHERE username = ? && password = ?";
    
	public static String validateUser(Connection connection,String userName,String password) {
		
		try {			
			prepareStatement = connection.prepareStatement(USER_QUERY);
			prepareStatement.setString(1, userName);
			prepareStatement.setString(2, password);
			resultset = prepareStatement.executeQuery();
			if(resultset.next()) {
				return resultset.getString("role");
			}
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
	}

}
