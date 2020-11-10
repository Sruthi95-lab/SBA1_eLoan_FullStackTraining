<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Application Status Display</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<%if(request.getAttribute("status").equals(""))
		{		
		%>	
			<h1>Please Enter a valid Application ID and proceed</h1>
			<a href="trackloan.jsp"><input type="button" name="navigateback" value="Back" class="btn btn-danger btn-sm"></a>
			
		<% }
		else
		{ %>
		
			<h3>Your Loan application is in <%=request.getAttribute("status") %>  Stage</h3>
			<a href="trackloan.jsp"><input type="button" name="navigateback" value="Back" class="btn btn-danger btn-sm"></a>
			<a href="userhome1.jsp"><input type="button" name="home" value="Home" class="btn btn-danger btn-sm"></a>
			
	<% } %> 
	
<br>
<br>
<br>
<jsp:include page="footer.jsp"/>

</body>
</html>