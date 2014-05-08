<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="shoppingCart" value="${sessionScope.SHOP}" />
	<c:if test="${empty shoppingCart }">
		<h1>Your Cart is empty</h1>
		<c:url var="URLWriting" value="ProcessServlet">
			<c:param name="action" value="Add More" />
		</c:url>
		<a href="${URLWriting }">Add More</a>
	</c:if>
	<c:if test="${not empty shoppingCart }">
		<c:set var="cart" value="${shoppingCart.cart}" />
		<c:if test="${empty cart }">
			<h1>Your Cart is empty</h1>
			<c:url var="URLWriting" value="ProcessServlet">
				<c:param name="action" value="Add More" />
			</c:url>
			<a href="${URLWriting }">Add More</a>
		</c:if>
		<c:if test="${not empty cart}">
			<h1>Your Cart</h1>
			<table border="1">
				<thead>
					<tr>
						<th>No.</th>
						<th>Title</th>
						<th>Quantity</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<form action="ProcessServlet" method="post">
						<c:forEach items="${cart}" var="item" varStatus="counter">
							<tr>
								<td>${counter.count }</td>
								<td><input type="hidden" name="txtTitle_${counter.count }"
									value="${item.key }">${item.key }</td>
								<td><input type="text" name="txtQuantity_${counter.count }"
									value="${item.value }"></td>
								<td><input type="checkbox" name="chkRemove"
									value="${item.key }" /></td>
								<td><input type="submit" name="action_${counter.count }"
									value="Update" /></td>
							</tr>
						</c:forEach>
					<tr>
						<td colspan="3"><c:url var="URLWriting"
								value="ProcessServlet">
								<c:param name="action" value="Add More" />
							</c:url> <a href="${URLWriting }">Add More</a></td>
						<td><input type="submit" value="Remove" name="action" /></td>
					</tr>
					</form>
				</tbody>
			</table>
		</c:if>
	</c:if>
</body>
</html>