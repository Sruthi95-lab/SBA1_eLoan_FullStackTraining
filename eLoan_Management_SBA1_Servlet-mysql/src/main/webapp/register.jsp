<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="js/viewDetails.js"></script>
<title>User Registration Page</title>
<Style>
body{
background-color:#e6e6e6;
}
</Style>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1 id="welcomeText" align="center">eLoan Login</h1>
	<div class="tab" align="center"> 
		<input type="button" class="btn btn-danger btn-sm" class="tablinks"  value="Customer" onclick="viewInfo(event, 'Customer')" id="defaultOpen" >
		<input type="button" class="btn btn-danger btn-sm" class="tablinks"  value="Admin" onclick="viewInfo(event, 'Admin')">
	</div>

	<div id="Customer" class="tabcontent" align="center">
		<form class="custLoginform" action="user?action=validate&type=customer" method="post">
			<div>
				<div><label for="loginid">Enter Login ID</label> </div>
				<div><input type="text" id="loginid" name="loginID"> </div>
			</div>
			<br>
			<div>
				<div><label for="password">Enter Password</label> </div>
				<div><input type="password" id="password" name="password"> </div>
			</div>
			<br/>
			<div>
				<div><input type="submit" value="Login" id="login" class="btn btn-danger btn-sm"></div>
			</div>
			
			<a href="newuserui.jsp" id="register">New User? Please register here</a>
			<br/><br/>
		</form>
	</div>

	<div id="Admin" class="tabcontent" align="center">
		<form class="adminLoginform" action="user?action=validate&type=admin" method="post">	
			<div>
				<div><label for="adminID">Enter Admin ID</label> </div>
				<div><input type="text" id="adminID" name="adminID"> </div>
			</div>
			<div>
				<div><label for="password">Enter Password</label> </div>
				<div><input type="password" id="password" name="adminpassword"> </div>
			</div>
			<br/>
			<div>
				<div><input type="submit" value="Login" id="login" class="btn btn-danger btn-sm"> </div>
			</div>
			
		<br/>
		</form>
	</div>
	<script>document.getElementById("defaultOpen").click();</script>
<hr/>
<jsp:include page="footer.jsp"/>
</body>
</html>