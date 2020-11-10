<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>ADMIN HOME</title>
<Style>
body{
background-color:#e6e6e6;
}
li{
font-weight: bold;
color:red;}
#z{
background-color:black;}
#b{
background-color:black;}

</Style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div align="right"><a href="index.jsp">Logout</a></div>
<h4>Admin Dash Board</h4>
<div class="container"></div>
<ul class="nav nav-tabs nav-justified">
<li id="z"><a href="admin?action=listall" data-toggle="tab">LIST ALL</a></li>
<li id="b"><a href="process.jsp" data-toggle="tab">PROCESS LOAN</a></li>
<br>
<br>
<br>
<br>
<br>
<br>
<jsp:include page="footer.jsp"/>
</body>
</html>
