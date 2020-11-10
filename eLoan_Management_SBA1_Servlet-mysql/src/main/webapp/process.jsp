<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="resources/css/background.css" />
<link rel="stylesheet" type="text/css" href="resources/css/customerHome.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<Style>h3{
color: blue;}
color:white; }
body{
background-color:#e6e6e6;
}</Style> 
<title>Process  Loan Application</title>
</head>
<%@ include file = "header.jsp" %>
<body>

	<!-- read the application number to edit from user and send to 
	     user controller to edit info
	   <%
		//String username = session.getAttribute("username").toString();
		//Customer customerData = (Customer) session.getAttribute("customerInfo");
	%>
	-->
<br>
<br>
<center>
<div align="right"><a href="index.jsp"><h4>Logout</h4></a></div>
<form class="loginform" method="post" action ="admin?action=process">
<h4>Enter your loan Details</h4><br>
<b>Enter Loan Application ID:</b> <input type="text" placeholder="App Num" name = APPLID>
<br>
<br>
<br>
<br>
<br>

<button class="btn btn-danger btn-sm"><b>SEARCH</b></button>
</center>
</form>
</body>
<br>
<br>
<br>
<br>
<%@ include file = "footer.jsp" %>
</html>
