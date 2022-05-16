<%@page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery.min.js"></script> 
<script src="Components/payment.js"></script> 
<title>User Management</title>
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col"> 
			<h1>USER DETAILS</h1>
				<form class="row g-3" id="formPayment" name="formPayment" method="post" action="Payment.jsp">  
					<div class="col-md-6">
						<label class="form-label">User ID:</label>  
	 	 				<input id="cusId" name="cusId" type="text"  class="form-control form-control-sm" placeholder="Enter Customer ID" required>
					</div>
					
					<div class="col-md-6">
						<label class="form-label">first Name:</label>
						<input id="telNo" name="telNo" type="tel" class="form-control form-control-sm" placeholder="123-45-678"  pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" required>
					</div>    
  					
  					<div class="col-md-6">
	  					<label class="form-label">Last Name:</label>
	  					<input id="date" name="date" type="text" class="form-control form-control-sm" placeholder="Enter Date" required>
  					</div>
					 
					<div class="col-md-6">
						<label class="form-label">Email:</label>
					  	<input id="amount" name="amount" type="text" class="form-control form-control-sm" placeholder="Enter Amount" required>
					</div>    
					
					
  					<div class="col-12">
	  					<input id="btnSave" name="btnSave" type="button" value="Submit User" class="btn btn-primary" required>  
						<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
  					</div>
					 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divUserGrid">
					<%
					User userObj = new User();
									out.print(userObj.readUser());
					%>
				</div>
			</div>
		</div>
</div>

</body>
</html>