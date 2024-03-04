package com.java.employee.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
	
	private static Connection connection=null;
	private JdbcConnection() {
		
	}
	public static Connection getConnection(String url,String username,String password) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if(connection==null || connection.isClosed())
			     connection= DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return connection;
	}
	

}
