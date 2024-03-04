package com.java.security;

import java.io.IOException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.employee.dao.EmployeeDao;
import com.java.employee.jdbc.JdbcConnection;
import com.java.pojo.Employee;
import com.mysql.cj.protocol.a.DebugBufferingPacketReader;


public class SecurityServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private static Connection connection=null;
    private static ServletContext context =null;
    private static Employee employee=null;
    private static Employee emp=null;
    private static Enumeration<String> parameterNames = null;
	
    @Override
	public void init(ServletConfig config) throws ServletException {
    	context = config.getServletContext();
		String url = context.getInitParameter("url");
		String username=context.getInitParameter("username");
		String password = context.getInitParameter("password");
	    connection = JdbcConnection.getConnection(url, username, password);
	}
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count=0;
		response.setContentType("text/html");
		String url = request.getRequestURI();
		HttpSession session = request.getSession();
	    if(!(session.getAttribute("role").equals("administrator")
	        ||session.getAttribute("role").equals("librarian"))) {
	    	session.removeAttribute("role");
	    	request.getRequestDispatcher("login.html").forward(request, response);
	    }
		url = url.substring(url.lastIndexOf("/")+1,url.length()).trim();
		if(url.equals("servlet"))response.sendRedirect("index.jsp");
		else if(url.equals("registerEmployee")) {
			count = registerEmployee(request,response);
		}
		if(count>0)response.sendRedirect("index.jsp");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		url = url.substring(url.lastIndexOf("/")+1,url.length()).trim();
		HttpSession session = request.getSession();
		session.removeAttribute("search");
	    if(session!=null && !(session.getAttribute("role")!=null && session.getAttribute("role").equals("administrator")
	        ||session.getAttribute("role").equals("librarian"))) {
	    	session.removeAttribute("role");
	    	request.getRequestDispatcher("login.html").forward(request, response);
	    }
		if(url.equals("register"))response.sendRedirect("register.html");
		
		else if(url.equals("view")) {
			response.sendRedirect("view.jsp");
		}
		else if(url.equals("search")) {
			getEmployee(request, response);
			session.setAttribute("search", "search");
			response.sendRedirect("view.jsp");
		}

	}
	
	public static synchronized int registerEmployee(HttpServletRequest request,HttpServletResponse response){
        parameterNames = request.getParameterNames();
        
		if(parameterNames.hasMoreElements()) {
			emp = new Employee();
			emp.setFirstname(request.getParameter("firstname"));                                                //firstName
			emp.setMiddlename(request.getParameter("middlename"));                                              //middleName
			emp.setLastname(request.getParameter("lastname"));                                                  //lastName
			String dob[] = request.getParameter("dob").split("-");                                              //dob
			Date date = new Date(Integer.parseInt(dob[0]), Integer.parseInt(dob[0]), Integer.parseInt(dob[0]));
			emp.setDob(date);
			emp.setCountry(request.getParameter("country"));                                                    //country 
			emp.setLocation(request.getParameter("state"));                                                     //state
			emp.setAddress(request.getParameter("address"));                                                    //address
			if(request.getParameter("isExperienced").equals("yes")) {                                           //isExpericenced
				emp.setExperience(Integer.parseInt(request.getParameter("experience")));                        //experience
			}
			emp.setEducation(request.getParameter("education"));                                                //Education
		}
		
//		System.out.println(emp);
		return EmployeeDao.saveEmployee(emp, connection);
    }
	
	public static void getEmployee(HttpServletRequest request,HttpServletResponse response) {
		
		parameterNames=request.getParameterNames();	
		int empid = Integer.parseInt(request.getParameter(parameterNames.nextElement()));
		if(empid>0) {
			if(request.getParameter("all")!=null && (request.getParameter("all").equals("all"))) {
			    if(request.getParameter(parameterNames.nextElement()).equals("all")) {
			    	EmployeeDao.getEmployeeList(connection, empid,"*");
			    } 
			}
			else {
				String str="";
			    while(parameterNames.hasMoreElements()) {
			    	str+=parameterNames.nextElement()+",";
			    }
			   if(str.length()!=0 && str.length()>1) str=str.substring(0,str.length()-1);
			    EmployeeDao.getEmployeeList(connection, empid,str);
			    request.setAttribute("employeeMap", EmployeeDao.getEmployeeMap());
			}
		}
	}
}



