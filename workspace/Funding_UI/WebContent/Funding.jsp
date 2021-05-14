<%@page import="model.FundingServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Funding Management - GadgetBadget</title>
	
		<link href="myStyle.css" rel="stylesheet" />
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="Components/jquery-3.5.0.min.js"></script>
		<script src="Components/Funding.js"></script>

	</head>
	
	<body>
		<div class="container">
	
			<p class="font-weight-bold">
				<center>
					<h1><u><i><b>Funding Management - GadgetBadget</b></i></u></h1>
				</center>
			</p>
			<br><br>
			
			<fieldset>
	
				<legend><b>Add Funding Details</b></legend>
					<form id="FUNDING" name="FUNDING" class="border border-light p-5">
						
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Post ID:</label>
						    <input type="text" id="postID" class="form-control" name="postID">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Post Title:</label>
						    <input type="text" id="postTitle" class="form-control" name="postTitle">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Content:</label>
						    <input type="text" id="content" class="form-control" name="content">						    
						</div>
						
						
						
						
						
						
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg btn-block"> 
						<input type="hidden" id="hidPostIDSave" name="hidPostIDSave" value="">
					</form>
				
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>			
			</fieldset>
			
			<br> 
			
			<div class="container" id="PostGrid">
				<fieldset>
					<legend><b>View Post Details</b></legend>
					<form method="post" action="Funding.jsp" class="table table-striped">
						<%
						FundingServlet viewPost = new FundingServlet();
							out.print(viewPost.readPost());
						%>
					</form>
					<br>
				</fieldset>
			</div>
		</div>
	</body>
</html>



