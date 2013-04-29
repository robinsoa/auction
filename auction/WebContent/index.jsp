<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean class="helper.AuctionHelper" id="auctionHelper" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adrian Robinson's Auction Page</title>
</head>
<body>
<h3>Welcome Guest!</h3>
Click <a href="auctionitem.jsp">here</a> to view auction items.
<br>
<br>

<h3>Administrators login below.</h3><br>
<% if (request.getAttribute("message") != null) { %>
<p style="color:red"><%= request.getAttribute("message") %></p>
<% } %>
<form method="post" action="AuctionController">
<p>Username:  <input type="text" name="userName"></p>
<p>Password:  <input type="text" name="password"></p>
<input type="hidden" name="control" value="login">
<input type="submit" value="Login">
</form>
</body>
</html>