package com.java.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.employee.dao.UserDao;
import com.java.employee.jdbc.JdbcConnection;
import com.java.pojo.Employee;


public class SecurityFilter implements Filter {
	
	private Connection connection=null;
    private ServletContext context =null;
    private Employee employee=null;

	@Override
	public void init(FilterConfig config) throws ServletException {
		context = config.getServletContext();
		String url = context.getInitParameter("url");
		String username=context.getInitParameter("username");
		String password = context.getInitParameter("password");
	    
		connection = JdbcConnection.getConnection(url, username, password);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/html");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String user = UserDao.validateUser(connection, username, password);
		if(user!=null && (user.equals("administrator")||user.equals("librarian"))) {
			
//			HttpServletResponse response = (HttpServletResponse) resp;
//			Cookie cookie = new Cookie("role", user);
//			response.addCookie(cookie);
			
			HttpServletRequest request = (HttpServletRequest) req;
			HttpSession session = request.getSession();
			session.setAttribute("role", user);
			chain.doFilter(req, resp);
		}
		else {
			writer.print("<h2 style="+"color:red text-align=center"+">Invalid user details</h2>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
	}
	
	@Override
	public void destroy() {
		
		
	}
	
}



