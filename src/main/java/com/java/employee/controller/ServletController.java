package com.java.employee.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.employee.dao.EmployeeDao;
import com.java.employee.jdbc.JdbcConnection;
import com.java.pojo.Employee;

public class ServletController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private static Connection connection=null;
    private static ServletContext context =null;
    private static Employee employee=null;
	
    @Override
	public void init(ServletConfig config) throws ServletException {
    	System.out.println("ServletController");
    	context = config.getServletContext();
		String url = context.getInitParameter("url");
		String username=context.getInitParameter("username");
		String password = context.getInitParameter("password");
	    connection = JdbcConnection.getConnection(url, username, password);
	}
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String url = request.getRequestURI();
    	url=url.substring(url.lastIndexOf("/")+1,url.length());
    	System.out.println(url);
//    	request.getRequestDispatcher("register.jsp").include(request, response);
//    	try {
////    		if(connection==null)throw new Exception("connection failed");
//				if(url.equals("register")) {
//					System.out.println(url);
//					request.getRequestDispatcher("register.jsp").include(request, response);
//					registerEmployee(request,response);
//				}
//				else if(url.equals("view")) {
//					System.out.println(url);
//					request.getRequestDispatcher("view.jsp").include(request, response);
//				}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		response.sendRedirect("response.html");
    	
    }
//    @Override
//    public void destroy() {
//    	
//    }
    
    public static void registerEmployee(HttpServletRequest request,HttpServletResponse response) {
    	Employee emp = new Employee();
		Enumeration<String> parameterNames = request.getParameterNames();
		if(parameterNames.hasMoreElements()) {
			emp.setFirstname(request.getParameter(parameterNames.nextElement()).trim());
			emp.setMiddlename(request.getParameter(parameterNames.nextElement()));
			emp.setLastname(request.getParameter(parameterNames.nextElement()));
			Date date = new Date();
		    String dob[] = request.getParameter(parameterNames.nextElement()).split("-");
		    date.setYear(Integer.parseInt(dob[0]));
		    date.setMonth(Integer.parseInt(dob[1]));
		    date.setDate(Integer.parseInt(dob[3]));
			emp.setDob(date);
			emp.setCountry(request.getParameter(parameterNames.nextElement()));
			emp.setLocation(request.getParameter(parameterNames.nextElement()));
			emp.setAddress(request.getParameter(parameterNames.nextElement()));;
			parameterNames.nextElement();
			String data = parameterNames.nextElement();
			
			if(data.equals("experience")) {
				emp.setEducation("");
				emp.setExperience(Integer.parseInt(request.getParameter(data)));
			}
			else {
				emp.setExperience(0);
				emp.setEducation(request.getParameter(data));
			}
		}
		EmployeeDao.saveEmployee(emp, connection);
    }
}
