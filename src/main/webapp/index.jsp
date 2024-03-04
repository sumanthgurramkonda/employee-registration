<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		 <style>
	        .button{
	            height: 50px;
	            width: 80px;
	        }
	    </style>
	</head>
	<body>
	   <div align="center">
	   <h1>Employee Management Portal</h1>
	   <%String role = (String)session.getAttribute("role"); %>
	   <h2>User : <%=role %> </h2>
	     <input type="hidden" id="session" value=<%=role%>>
	     <table>
	          <tr>
	             <td>
	                <%if(role!=null && !role.equals("librarian")){ %>
		                 <a href="register"><button id="register" class="button">Register</button></a>
		             <% } %>
	             </td>
	            
	             <td>
		                <a href="view"> <button id="view" class="button">View</button></a> 
	             </td>
	          </tr>
	     </table>
	   </div>
	   <script>
	   
	 
	   </script>
	
	</body>
</html>



