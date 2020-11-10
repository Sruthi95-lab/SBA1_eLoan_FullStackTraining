
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="resources/css/customerHome.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<Style>h3{
color: blue;}
.b{
background-color:#525252;
color:white; }
body{
background-color:#e6e6e6;
}

</Style> 
<script>
function myFunction() {
  alert("User Registered Successfully!!!");
}
</script>

<title>New User Registration</title>
</head>
	
<body>
	
	<%
		
		
	%>
	
	<jsp:include page="header.jsp"/>
	
	<div class="table-responsive" class ="col-md-6 a"></div>
	<form action="user?action=registernewuser" method="post">
		<table  border="4" width="50%" height="40px" align= "center">
			<tr>
				<th colspan="2" class ="b"><center>DETAILS</center></th>
			</tr>
			<tr>
				<th>UserName for the Account</th><td><input type="text" name = "UserName"></td>
			</tr>
			<tr>
				<th>Password for the Account : </th><td><input type="text" name = "Password"></td>
			</tr>
			<tr>
				<th>First Name:</th><td><input type="text"  name = "FirstName"></td>
			</tr>
			<tr>
				<th>Last Name:</th><td><input type="text"  name = "LastName"></td>
			</tr>
			
			<tr>
				<th>Date of Birth:</th><td><input type="text"  name = "DateofBirth"></td>
				
			</tr>
			
			<tr>
				<th>Mobile:</th><td><input type="text" name="Mobile"></td>
				
			</tr>
			<tr>
				<th>Email:</th><td><input type="text" name="Email"></td>
				
			</tr>
			<tr>
				<th>City:</th><td><input type="text"  name = "City" ></td>
			</tr>
			<tr>
				<th>State:</th><td><input type="text"  name = "State" ></td>
			</tr>
			<tr>
				<th>Country:</th><td><input type="text"   name = "Country" ></td>
			</tr>
			<tr>
				<th>Pincode:</th><td><input type="text"   name = "Pincode" ></td>
			</tr>
			<tr>
				<th>Adress:</th><td><TextArea id="address" name="address"></TextArea></td>
			</tr>
		
			
		</table>
		<br>
		<br>
		<center>
			<input type = "submit" onclick="myFunction()" value ="REGISTER" class="btn btn-danger btn-sm">
		</center>
	</form>	
			<b><a href="register.jsp" id="navigate back">Navigate back</a></b>


<jsp:include page="footer.jsp"/>
	
</body>
</html>