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
	   <% String role = (String)session.getAttribute("role"); 
	      String url = (String)request.getRequestURI();
	     String search = (String)session.getAttribute("search");
	   %>
	   <h2>User : <%=role %> </h2>
	     <input type="hidden" id="session" value=<%=role%>>
	    <table>
	          <tr>
	             <td>
	                <%if(!role.equals("librarian")){ %>
		                 <a href="register"><button id="register" class="button">Register</button></a>
		             <% } %>
	             </td>
	            
	             <td>
		                <a href="view"> <button id="view" class="button">View</button></a> 
	             </td>
	          </tr>
	     </table>
	     
	<h1>Employee Details</h1>
	
	<form action="search" id="form">
		<label><b>Employee Id : </b></label> <input type="number" id="userid" name="userid">
		<table>
		  <tr>
		      <td>
		         <input type="checkbox" id="all" name="all" value="all"> <label>All</label>
		      </td>
		      <td>
		         <input type="checkbox" id="firstname" name="firstname" value="firstname"><label>First Name</label>
		      </td>
		       <td>
		         <input type="checkbox" id="middlename" name="middlename" value="middlename"><label>Middle Name</label>
		      </td>
		      <td>
		         <input type="checkbox" id="lastname" name="lastname" value="lastname"> <label>Last Name</label>
		      </td>
		  </tr>
		  <tr>
		     <td>
		        <input type="checkbox" id="country" name="country" value="country"><label>Country</label>  
		      </td>
		      <td>
		        <input type="checkbox" id="location" name="location" value="location"><label>Location</label>  
		      </td>
		      <td>
		        <input type="checkbox" id="address" name="address" value="address"><label>Address</label>  
		      </td>
		      <td>
		        <input type="checkbox" id="experience" name="experience" value="experience"><label>Experience</label>  
		      </td>
		  </tr>  
		  <tr>
		       <td>
		        <input type="checkbox" id="education" name="education" value="education"><label>Education</label>  
		      </td>
		  </tr>
		  
		</table>
		  <input id="search" class="button" type="submit" value="Search">
	 </form> 
	 <pre></pre>
	
	</table>
	<%if(search!=null){ %>
	<%@ include file="display.jsp" %>
	<%} %>
	
	<script type="text/javascript">
	     var all = document.getElementById("all");
         var checkBox = document.querySelectorAll("[type='checkbox']");
		 var userid = document.getElementById("userid")
		 var validateForm = document.getElementById("form");
		 
		 console.log(all, checkBox, userid, validateForm);
		 
         all.addEventListener("click",()=>{
            for(i=1;i<checkBox.length;i++){
                if(all.checked===true)
			       checkBox[i].style.visibility="collapse";
				else  checkBox[i].style.visibility="visible"
            }  
         });
		
        function validateId(){
			if(userid.value<=0 || userid.value===""|| userid.value===null||userid.style.color==="red"){
				alert("Please enter valid Id");
				return false;
			}
			return true;
		}
        function validateAttributes(){
			if(all.checked===true) return true;
			for(i=1;i<checkBox.length;i++){
				if(checkBox[i].checked===true)return true;
			} 
			alert("Please click any of the field");
			return false;
		}
		validateForm.onsubmit= () =>{
			return validateId() && validateAttributes();
			// return false;
        };
		all.addEventListener("click",()=>{
			for(i=1;i<checkBox.length;i++){
				if(all.checked==true)
					checkBox[i].style.visibility="collapse";
				else  checkBox[i].style.visibility="visible"
			}  
         });
			
	    userid.oninput = () =>{
			var val = userid.value;
			if(val<=0){
				userid.style.color="red";
			}
			else userid.style.color="black";
        };
        
	</script>
	
	</body>
</html>



