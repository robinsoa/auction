<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean class="helper.AuctionHelper" id="auctionHelper" scope="session"/>

<html>
<head>
<style type="text/css">
BODY {font-family:Sans-serif}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Dance Society Auction</title>
</head>
<body>

<div style="width:300px;position:fixed;background-color:lightyellow">

<c:forEach items="${auctionHelper.categoryList }" var="auction">
<%-- LI element with categories and items --%>

<h3>${auction.categoryName }</h3>
<ul>
<c:forEach items="${auction.itemList }" var="item" varStatus="loop">
<li>
<a href="AuctionController?id=${item.id }">${item.title }</a>
</li>
</c:forEach>
</ul>
</c:forEach>
</div>

<div style="padding-left:300px">
<h1>Dance Society Auction</h1>

<h2><c:out value="${auctionHelper.getItem(id).title }"/></h2>
<p><c:out value="${auctionHelper.getItem(id).description }"/></p>

<p>High bid so far: $<c:out value="${auctionHelper.getItem(id).bid }"/></p>
<h3>Bid on this item</h3>

<% if (request.getAttribute("message") != null) { %>
<p style="color:red"><%= request.getAttribute("message") %></p>
<% } %>
<br>
<form method ="post" action="AuctionController">
<input type="hidden" name="id" value=${id }>
<input type="hidden" name="control" value="updateBid">
<table><tr><td>Your name:</td><td><input type="text" size="20" name="bidderName"></td></tr>
       <tr><td>Your email:</td><td><input type="text" size="20" name="bidderEmail"></td></tr>
       <tr><td>Your bid:</td><td>$<input type="text" size="20" name="bid"></td></tr>
       <tr><td colspan="2"><input type="submit" value="Enter your bid"></td></tr>
</table></form>
</div>
</body>
</html>