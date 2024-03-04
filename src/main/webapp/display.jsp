<%@page import="java.util.Map"%>
<%@page import="com.java.employee.dao.EmployeeDao"%>
<%@page import="com.java.pojo.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
     <%
      Map<String,String> employeeMap = EmployeeDao.getEmployeeMap();
		%>
     <table border="2">
     <tr>
     <%
     if(employeeMap!=null){
	     for(Map.Entry<String,String> entry:employeeMap.entrySet()){ %>
		     	    <th><%=entry.getKey()%></th>
	     <%} %>
	     </tr>
	     
	      <tr>
	     <%for(Map.Entry<String,String> entry:employeeMap.entrySet()){ %>
		     	    <th><%=entry.getValue()%></th>
	     <%}
	  } 
     else{
	  %>
     </tr>
	  <h1><%="No employee found " %> </h1>
	  <%} %>
	  

</body>
</html>