<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean class="helper.AuctionHelper" id="auctionHelper" scope="session"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Auction Administration Page</title>
</head>
<body>
<h1>Administration Page</h1>

<div style="width:400px;position:fixed;background-color:lightyellow">
<h2>Current High Bids</h2>

<c:forEach items="${auctionHelper.categoryList }" var="auction">
<%-- LI element with categories and items --%>

<h3>${auction.categoryName }</h3>

<table border="1">
<c:forEach items="${auction.itemList }" var="item" varStatus="loop">

<tr>
<td>${item.title }</td>  
<td>$${item.bid }</td>
<td>${item.bidderName }</td>
<td>${item.bidderEmail }</td>
</tr>

</c:forEach>
</table>


</c:forEach>

</div>

<div style="padding-left:410px">

<h2>Enter a new Auction Item</h2>
<form method="post" action="AuctionController">
Title: <input type="text" name="title"><br>
Description: <TEXTAREA NAME="description" COLS=50 ROWS=10></TEXTAREA><br>
Category: <input type="text" name="category"><br>
<input type="hidden" name="control" value="addItem">
<input type="submit" value="Add Item">
</form>
</div>

</body>
</html>