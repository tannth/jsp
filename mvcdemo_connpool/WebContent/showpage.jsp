<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List</title>
</head>
<body>
	<h1>Show Result</h1>
	<c:set var="info" value="${requestScope.INFO }" />
	<c:if test="${not empty info}">
		<table border="1">
			<thead>
				<tr>
					<th>No.</th>
					<th>UserName</th>
					<th>LastName</th>
					<th>Roles</th>
					<th>Delete</th>
					<th>Update</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="count" value="0" />
				<c:forEach var="rows" items="${info}">
				<form action="Controller">
					<c:set var="count" value="${count + 1}" />
					<tr>
						<td>${count}</td>
						<!-- ví dụ ${rows.username}) đây là field chúng ta lấy từ các thuộc tính có trong LoginBean và nó được tạo ra các get/set method trong tập tin LoginBean class (ở đây là tập tin LoginBean.java) -->
						<td>${rows.username}<input type="hidden" name="txtUsername"
							value="${rows.username}" />
						</td>
						<td><input type="text" name="txtLastname"
							value="${rows.lastname}" /></td>
						<td><input type="checkbox" name="chkAdmin" value="1"
							<c:if test="${rows.roles == '1'}">checked</c:if> /></td>
						<c:url var="delete" value="Controller">
							<c:param name="btnAction" value="Delete" />
							<c:param name="username" value="${rows.username}" />
							<c:param name="txtsearch" value="${param.txtsearch }" />
						</c:url>
						<td><a href="${delete}">Delete</a></td>
						<td><input type="hidden" name="txtsearch"
							value="${param.txtsearch}" /> <input type="submit"
							value="Update" name="btnAction" /></td>
					</tr>
					</form>
				</c:forEach>
				
			</tbody>
		</table>

	</c:if>
</body>
</html>