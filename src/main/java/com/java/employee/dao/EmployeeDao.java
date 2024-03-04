package com.java.employee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletContext;

import com.java.pojo.Employee;
import com.java.pojo.User;

public class EmployeeDao {
	
	private static Statement statement = null;
	private static PreparedStatement prepareStatement =null;
    private static ResultSet resultset=null;
    private static ServletContext context =null;
    private static final String PUT_QUERY = "INSERT INTO employee(firstname,middlename,lastname,dob,country,address,location,experience,education) VALUES(?,?,?,?,?,?,?,?,?)";
    private static String GET_EMP="SELECT * FROM employee WHERE employeeId = ?";
    private static Employee employee=null;
    private static String getQuery=null;
    private static List<Employee> list = new ArrayList<Employee>();
    private static Map<String, String> employeeMap = null;
	
	public static int saveEmployee(Employee emp,Connection connection){
		int count=0;
		try {
		prepareStatement = connection.prepareStatement(PUT_QUERY);
		prepareStatement.setString(1, emp.getFirstname());
		prepareStatement.setString(2, emp.getMiddlename());
		prepareStatement.setString(3, emp.getLastname());
		prepareStatement.setDate(4, (Date) emp.getDob());
		prepareStatement.setString(5, emp.getCountry());
		prepareStatement.setString(6, emp.getAddress());
		prepareStatement.setString(7, emp.getLocation());
		prepareStatement.setInt(8, emp.getExperience());
		prepareStatement.setString(9, emp.getEducation());
		count = prepareStatement.executeUpdate();
		list.add(emp);
		return count;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    return 0;
	}
	
	public static Map<String,String> getEmployeeMap(){
		return employeeMap;
	}
	
	public static void getEmployeeList(Connection connection, int id,String str) {
		try {
			
			GET_EMP = ("SELECT "+str+" FROM employee WHERE employeeId = ?");
			prepareStatement = connection.prepareStatement(GET_EMP);
			prepareStatement.setInt(1, id);
			resultset = prepareStatement.executeQuery();
			ResultSetMetaData metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();
			employeeMap=null;
			if(resultset.next()) {
				employeeMap = new HashMap<String, String>();
				for(int i=1;i<=columnCount;i++) {
					String columnName = metadata.getColumnName(i);
					String colType = metadata.getColumnClassName(i);
					if(colType.equals("java.lang.Integer")) {
//						System.out.println(columnName+" "+resultset.getInt(columnName));
						employeeMap.put(columnName,Integer.toString(resultset.getInt(columnName)));
					}
					else if(colType.equals("java.lang.String")) {
//						System.out.println(columnName+" "+resultset.getString(columnName));
						employeeMap.put(columnName,resultset.getString(columnName));
					}
//					else if(colType.equals("java.sql.Date")) {
//						System.out.println(columnName+" "+resultset.getString(columnName));
//						employeeMap.put(columnName,resultset.getString(columnName));
//					}
			   }
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
