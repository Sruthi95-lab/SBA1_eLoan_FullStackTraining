<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.iiht.evaluation.eloan.dto.UserInfo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>User Home Page</title>
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
#c{
background-color:black;}
</Style>
</head>
<body>
<jsp:include page="header.jsp"/>
<%
	UserInfo userDetails = (UserInfo) session.getAttribute("userInfo");

%>
<div align="right"><a href="index.jsp"><b>Logout</b></a></div>
<h3>Hi <%=userDetails.getFirstName()%>,</h3>
<br>

<center>
<h4><b>USER  DASH BOARD</b></h4>
</center>
<br>
<div class="container"></div>
<ul class="nav nav-tabs nav-justified">
<li id="z"><a href="user?action=application&state=new" data-toggle="tab">Apply for Loan</a></li>
<li id="b"><a href="trackloan.jsp" data-toggle="tab">Track Loan Application</a></li>
<li id="c"><a href="editloan.jsp" data-toggle="tab">Edit Loan Application</a></li>
<br>
<br>
<br>
<br>
<br>
<br>
<jsp:include page="footer.jsp"/>
</body>
</html>