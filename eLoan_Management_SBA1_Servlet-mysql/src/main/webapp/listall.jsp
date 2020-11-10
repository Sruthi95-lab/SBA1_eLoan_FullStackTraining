<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<head>
<style>
#loanInfo{
font-family:CALIBRI;
border-collapse:collapse;
width:100%;

}
#loanInfo td,tr,th{
border : 1px solid;
font-family:CALIBRI;
}
#loanInfo th{
background:black;
color:White;

}


</style>
<meta charset="ISO-8859-1">

<title>Display All Loans</title>


<body>
	
	<div align="right"><a href="index.jsp">Logout</a></div>
	
	<br>
	<br>
		<!-- write code to display all the loan details 
             which are received from the admin controllers' listall method
	-->

		<% ArrayList<LoanInfo> list_loans = (ArrayList) request.getAttribute("ListLoan");%>


		<table id="loanInfo">
			<thead>
				<tr>
					<th>User Name</th>
					<th>Application Number</th>
					<th>Purpose</th>
					<th>Amount Request</th>
					<th>Loan Date</th>
					<th>Business Structure</th>
					<th>Business Indicator</th>
					<th>Tax Indicator</th>
					<th>Address</th>
					<th>Email</th>
					<th>Contact</th>
					<th>Application Status</th>
					
				</tr>



			</thead>
			<tbody>

				<% for (int i = 0; i < list_loans.size(); i++) { %>
        	<tr>
            <td> <%   out.println(list_loans.get(i).getUserName()); %></td>
            <td> <%   out.println(list_loans.get(i).getApplno()); %></td>
            <td> <%   out.println(list_loans.get(i).getPurpose()); %></td>
            <td> <%   out.println(list_loans.get(i).getAmtrequest()); %></td>
            <td> <%   out.println(list_loans.get(i).getDoa()); %></td>
            <td> <%   out.println(list_loans.get(i).getBstructure()); %></td>
            <td> <%   out.println(list_loans.get(i).getBindicator()); %></td>
            <td> <%   out.println(list_loans.get(i).gettindicator());%></td>
            <td> <%   out.println(list_loans.get(i).getAddress()); %></td>
            <td> <%   out.println(list_loans.get(i).getEmail()); %></td>
            <td> <%   out.println(list_loans.get(i).getMobile()); %></td>
            <td> <%   out.println(list_loans.get(i).getStatus()); %></td>
            
            </tr>
           <% }
        %>
			</tbody>
		</table>
	

	</form>
	<br>
	<br>
	<br>
	<br>
	<a href="adminhome1.jsp"><input type="button" name="navigateback" value="BACK" class="btn btn-danger btn-sm"></a>
	</head>
</body>
<br>
<br>
<br>
<br>
<jsp:include page="footer.jsp" />

</html>