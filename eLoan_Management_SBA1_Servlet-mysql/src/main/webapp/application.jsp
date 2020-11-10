<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>eLoan Application Form</title>
<Style>
.b{
background-color:#525252;
color:white; }
bodybody{
background-color:#e6e6e6;
}
</Style>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body onload="myFunction()">

	<!--
	write the html code to accept laon info from user and send to placeloan servlet
-->


	<jsp:include page="header.jsp" />
	
	
  	<% if(request.getAttribute("state").equals("new") ){ 
		//request.setAttribute("state", "submitted");
	%>
	
	<form action="user?action=placeloan" method="POST">
	<div align="right"><a href="index.jsp"><b>Logout</b></a></div>
		<div align="center">
		<div class="table-responsive" class ="col-md-6 a"></div>
		<table  border="4" width="35%" height="40px" align= "center">
		
			<tr>
				<th colspan="2" class ="b"><center>PROVIDE YOUR DETAILS</center></th>
			</tr>
			
		
			<tr>
				<th>Enter your full name</th><td><input type="text" name = "Fullname"></td>
			</tr>
			
			<tr>
				<th>Loan Name</th><td><input type="text" name = "loanname"></td>
			</tr>
			
			
			<tr>
				<th>Amount</th><td><input type="text" name = "loanamount"></td>
			</tr>
			
			<tr>
				<th>Loan Application Date</th><td><input type="text" name = "loanapplicationdate"></td>
			</tr>
		
			
			<tr>
				<th>BusinessStruture</th><td><input type="radio" value="Individual" name="BusinessStruture"/>Individual
				<input type="radio" value="Oraganisation" name="BusinessStruture"/>Oraganisation</td>
			</tr>
			
			<tr>
				<th>Billing Indicator</th><td><input type="radio" value="Yes" name="BillingIndicator"/>Yes
				<input type="radio" value="No" name="BillingIndicator"/>No</td>
			</tr>
			
			<tr>
				<th>Tax Indicator</th><td><input type="radio" value="Yes" name="TaxIndicator"/>Yes
				<input type="radio" value="No" name="TaxIndicator"/>No</td>
			</tr>
			
			<tr>
				<th>Adress</th><td><TextArea id="address" name="address"></TextArea></td>
			</tr>
			
			<tr>
				<th>CONTACT</th><td><input type="number" name = "mobile"></td>
			</tr>
			
			<tr>
				<th>Email</th><td><input type="email" name = "email"></td>
			</tr>
			
		</table>
			<br>
			<br>
			<center>
			<input type="submit" value="SUBMIT" class="btn btn-danger btn-sm">
			</center>
		
	</form>
	
	  <% } else { 
		
		String state = request.getAttribute("state").toString();
	  	
		
		if(state.equalsIgnoreCase("submitted"))
		{
			LoanInfo loanData =	(LoanInfo) request.getAttribute("loanDetails");
			String applicationID = loanData.getApplno();
		
		%>
		<center>
			<h2><b>Your Loan details are submitted successfully !!</b></h2>
			<h3>Your loan Application ID : <%=applicationID %></h3>
			<h3><b>Our Representative will be contacting you for further process</b></h3>
			<h3><b>THANK YOU!!</b></h3>
		</center>	
		<% }
		else
		{ %>
			<p>Loan details submission Unsuccessful !!</p>
			
	<% } %>
		
	<a href="userhome1.jsp">Navigate Back</a>
	<% } %>
	
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="footer.jsp" />


</body>
</html>