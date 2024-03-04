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
	                <%if(role!=null&& !role.equals("librarian")){ %>
		                 <a href="register"><button id="register" class="button">Register</button></a>
		             <% } %>
	             </td>
	            
	             <td>
		                <a href="view"> <button id="view" class="button">View</button></a> 
	             </td>
	          </tr>
	     </table>
	      <form id="registerEmployee" method="post">
                <table border="1" style="border-radius:5px;">
             <tr>
                    <td>
                        <label >Firstname</label>
                    </td>
                    <td>
                        <input type="text" id="fname" name="firstname" placeholder="ex:Robert" size="30" required>
                    </td>
             </tr>
             <tr>   
                    <td>
                        <label >MiddleName</label>
                    </td>
                    <td>
                        <input type="text" id="mname" name="middlename" placeholder="ex: downy" size="30" >
                    </td>
              </tr> 
              <tr> 
                    <td>
                        <label >LastName</label>
                    </td>
                    <td>
                        <input type="text" id="lname" name="lastname" placeholder="ex:junior" size="30" required>
                    </td>
             </tr>
                <tr>
                    <td>
                        <label >DOB</label>
                    </td>
                    <td>
                        <input type="date" id="dob" name="dob"  required>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label >Country</label>
                    </td>
                    <td>
                        <select id="country" name="country" required>
                            <option value="select" >--select--</option>
                            <option id="india" name="india" value="india">India</option>
                            <option id="US" name="US" value="US">US</option>
                            <option id="UK" name="UK" value="UK">UK</option>
                            <option id="other" name="other" value="other">Other</option>
                        </select>
                        <select id="state" name="state" required>
                            <option id="temp1" ></option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label >Address</label>
                    </td>
                    <td>
                        <input type="text" id="location" name="location" size="30" required>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <label>Experience</label>
                    </td>
                    <td>
                        <label>Yes</label>
                        <input  type="radio" value="yes" name="isExperienced" id="isExperienced" />
                        <label>No</label>
                        <input  type="radio" value="no" name="isExperienced" id="isExperienced" required/>
                    </td>
                </tr>
                    <tr id="exp">
                        <td>
                            <label>Experience</label>
                            <!-- <div id ="exp"></div> -->
                        </td>
                        <td>
                            <input  type="number" name="experience" id="experience" required/>
                        </td>
                    </tr>
                <tr>
                    <td>
                        <label>Education</label>
                    </td>
                    <td>
                        <select name="education">
                            <option value="">--select--</option>
                            <option value="EEE">EEE</option>
                            <option value="MEC">MEC</option>
                            <option value="ECE">ECE</option>
                            <option value="CSE">ECE</option>
                            <option value="IT">IT</option>
                        </select>
                    </td>
                </tr>

                </table>
                <input type="submit">
            </form>
	   </div>
	   <script>
	       var bool =true;
	       var fname = document.getElementById("fname");
	       var error = document.createElement("p");
	       fname.oninput=()=>{
	            var val = fname.value;
	            for(index=0;index<val.length;index++){
	                console.log([val[index]]);
	                if(val[index]>='a' && val[index]<='z' || val[index]>='A' && val[index]<='Z' ){
	                        console.log([val[index]]);
	                        fname.style.color="black";
	                     }
	                     else {
	                        fname.style.color="red";
	                        error.innerText="Enter valid name";
	                        error.style="color:red";
	                        fname.appendChild(error);
	                        console.log(error);
	                        break;
	                     }
	            };
	       }
	       var isExperienced = document.querySelectorAll("[id=isExperienced]");
	       var countryElement = document.getElementById("country");
	       var stateElement = document.getElementById("state");
	       stateElement.name="state";
	       var stateOptins = document.createElement("option");
	
	       var states = {
	        "india" : ["",,"AP","TS","TN","MP","UP"],
	        "US"    : ["","California","Texos",],
	        "UK"    : ["","Bristol","CornWall"],
	        "other" : [""]
	       };
	       
	       countryElement.addEventListener("click",()=>{
	                 var option;
	                 states[countryElement.value].forEach((val,index)=>{
	                	
	                       option += `<option val=${val} id=${val} name="state" >${val}</option>`
	                 });
	                 stateElement.innerHTML=option;
	       });
	       
	       for( i=0;i<isExperienced.length;i++){
	            
	            var experience = document.getElementById("exp");
	            experience.style.visibility="collapse";
	
	            if(isExperienced[i].value==="yes"){
	                        console.log(experience);
	                        isExperienced[i].addEventListener("click",()=>{
	                            experience.style.visibility="visible";
	                    });
	            }
	            else {
	                isExperienced[i].addEventListener("click",()=>{
	                            experience.style.visibility="collapse";
	                    });
	            }
	       }
	    </script>   
	
	</body>
</html>



