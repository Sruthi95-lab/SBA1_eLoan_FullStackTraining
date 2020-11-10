<%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
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
  alert("Details Updated Successfully");
}
</script>

<title>Process Loan Application</title>
</head>
	
<body>
	
	<%
		// fetch the data from request object
		
		LoanInfo loandetails = (LoanInfo)request.getAttribute("LoanInfo"); 
		//String LoanNumber = session.getAttribute("loanappnum").toString();''
		//if (loandetails!=null){
		String loannumber = loandetails.getApplno();
		String LoanStatus = loandetails.getStatus();
		String LoanAppDate = loandetails.getDoa();
		String LoanBussStr =loandetails.getBstructure();
		String billingInd = loandetails.getBindicator();
		String taxingInd = loandetails.gettindicator();
		String purpose = loandetails.getPurpose();
		String LoanAmtReq = loandetails.getAmtrequest();
		String Contact = loandetails.getMobile();
		String Address =loandetails.getAddress();
		String Email =loandetails.getEmail();
		String Username = loandetails.getUserName();
		
		
		//}
		
	%>
	
	<jsp:include page="header.jsp"/>
	<div align="right"><a href="index.jsp"><h4>Logout</h4></a></div>
	
	<div class="table-responsive" class ="col-md-6 a"></div>
	<form class="loginform" method="post" action ="admin?action=calemi">
		<table  border="4" width="50%" height="40px" align= "center">
			<tr>
				<th colspan="2" class ="b"><center>LOAN APPLICATION DETAILS</center></th>
			</tr>
			
			<tr>
				<th>Application Number</th><td><input type="text" name = "Appnum"  value=<%=loannumber%> readonly></td>
			</tr>
		
			<tr>
				<th>full name</th><td><input type="text" name = "Fullname" value= <%=Username%>></td>
			</tr>
			
			<tr>
				<th>Loan Name</th><td><input type="text" name = "loanname"value=  <%=purpose%>></td>
			</tr>
			
			
			
			
			<tr>
				<th>Amount</th><td><input type="text" name = "loanamount" value=  <%=LoanAmtReq%>></td>
			</tr>
			
			<tr>
				<th>Loan Application Date</th><td><input type="text" name = "loanapplicationdate" value=<%=LoanAppDate%> readonly></td>
			</tr>
		
			
			<tr>
				<th>BusinessStruture</th><td><input type="radio" value="Individual" name="BusinessStruture" <%=LoanBussStr.equals("Individual")?"checked":""%>/>Individual
				<input type="radio" value="Oraganisation" name="BusinessStruture" <%=LoanBussStr.equals("Oraganisation")?"checked":""%>/>Oraganisation</td>
			</tr>
			
			
			<tr>
				<th>Billing Indicator</th><td><input type="radio" value="Yes" name="BillingIndicator" <%=billingInd.equals("Yes")?"checked":""%>/>Yes
				<input type="radio" value="No" name="BillingIndicator" <%=billingInd.equals("No")?"checked":""%>/>No</td>
			</tr>
			
			<tr>
				<th>Tax Indicator</th><td><input type="radio" value="Yes" name="TaxIndicator" <%=taxingInd.equals("Yes")?"checked":""%>/>Yes
				<input type="radio" value="No" name="TaxIndicator" <%=taxingInd.equals("No")?"checked":""%>/>No</td>
			</tr>
			
			<tr>
				<th>Address</th><td><TextArea id="address" name="address"><%=Address%></TextArea></td>
			</tr>
			
			<tr>
				<th>CONTACT</th><td><input type="number" name = "mobile" value=<%=Contact%>></td>
			</tr>
			
			<tr>
				<th>Email</th><td><input type="email" name = "email" value= <%=Email%>></td>
			</tr>
			
			<tr>
				<th>Amount Sanction</th><td><input type="text" name = "AmountSanction"></td>
			</tr>
			
			<tr>
				<th>Term</th><td><input type="text" name = "Term" ></td>
			</tr>
			
			<tr>
				<th>EMI Amount</th><td><input type="text" name = "EMI"></td>
			</tr>
			
			<tr>
				<th>Loan Closure Date</th><td><input type="text" name = "LoanClosureDate"></td>
			</tr>
			
			<tr>
				<th>Payment Start Date</th><td><input type="text" name = "PayementStartDate"></td>
			</tr>
			
			<tr>
				<th>Intrest Rate</th><td><input type="text" name = "IntrestRate"></td>
			</tr>
			
			
			<td><label for="status">Loan Status</label></td>
					<td><select name="LoanStatus"><option value ="Processing">Processing</option>
					
					<option value ="Pending Approval">Pending Approval</option>
					<option value ="Accepted">Accepted</option>
					<option value ="Rejected">Rejected</option>
					</select>
			</td>		
			
		</table>
			<br>
			<br>
			
		<center>
			<input type = "submit" onclick="myFunction()" value ="SUBMIT CHANGES" class="btn btn-danger btn-sm">
		</center>
	</form>
		<br>
		<br>
		

	


	<jsp:include page="footer.jsp"/>
	
</body>
</html>