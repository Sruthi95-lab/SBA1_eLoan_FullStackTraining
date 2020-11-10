<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Track Loan</title>
<Style>
body{
background-color:#e6e6e6;
}
</Style>
</head>
<body>
	<!-- write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information
	-->
	
	<jsp:include page="header.jsp"/>
	<div align="right"><a href="index.jsp"><b>Logout</b></a></div>
	<form action="user?action=trackloan" method="POST">
	<div>
				<center>
				<div>
					<label for="Application Number">Please Enter your application number</label>
				</div>
				<div>
					<input type="text" id="applicationnumber" name="applicationnumber" required placeholder="App Num">
				</div>
				
				<br>
				
				<div>
					<input type="submit" id="trackapplication" name="trackapplication" value="TRACK" class="btn btn-danger btn-sm">
				</div>
				</center>
	</div>
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<a href="userhome1.jsp"><input type="button" name="navigateback" value="Back" class="btn btn-danger btn-sm"></a>
	
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="footer.jsp"/>
	

</body>
</html>