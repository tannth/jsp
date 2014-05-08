<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show</title>
</head>
<body>
	<h1>Your Shopping Cart</h1>
	<c:set value="${sessionScope.SHOP}" var="shop" />
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
			<c:if test="${not empty shop}">
				<form action="Controller">
					<c:set var="count" value="0" />
					<c:forEach var="rows" items="${shop}">
						<c:set var="count" value="${count + 1 }" />
						<tr>

							<td>${count }</td>
							<td>${rows.value.title }</td>
							<td>${rows.value.quantity }</td>
							<td><input type="checkbox" name="rmv"
								value="${rows.value.title }" /></td>
						</tr>
					</c:forEach>
			</c:if>
			<tr>
				<c:url var="add" value="Controller">
					<c:param name="action" value="AddMore" />
				</c:url>
				<td colspan="2"><a href="${add}">Add more</a></td>
				<td><input type="submit" name="action" value="Remove" /></td>
			</tr>
			</form>
		</tbody>
	</table>
</body>
</html>