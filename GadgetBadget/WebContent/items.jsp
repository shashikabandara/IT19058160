<%@page import="admin.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Items Management</title>

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Send Message(Reply)</h1>
<form id="formItem" name="formItem" action="items.jsp">
 From: 
 <input id="from" name="from" type="text" 
 class="form-control form-control-sm">
 <br> To: 
 <input id="to" name="to" type="text" 
 class="form-control form-control-sm">
 <br> Subject: 
 <input id="subject" name="subject" type="text" 
 class="form-control form-control-sm">
 <br> Message: 
 <input id="message" name="message" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>

<div id="divItemsGrid">
 <%
 Admin itemObj = new Admin(); 
 out.print(itemObj.displaysendmessage()); 
 %>
</div>
<h1>Received message</h1>
<div id="divItemsGrid">
 <%
 Admin itemObj2 = new Admin(); 
 out.print(itemObj2.displayreceivemessage()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>