<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exception Demo</title>
</head>
<body>
	<b>Exception Demo</b>
	<form>
		Num 1: <input type="text" name="num1" value="${param.num1}" /><br />
		Num 2: <input type="text" name="num2" value="${param.num2}" /><br />
		<c:catch var="ee">
			<c:if test="${not empty param.num1 and not empty param.num2}">
				<c:set var="division" value="${param.num1 / param.num2}" />                 Division: <c:out
					value="${division}" />
				<br />
			</c:if>
			<br />
		</c:catch>
		<input type="submit" value="Divide" />
		<c:if test="${not empty ee}">             Error occurred<br />
			<c:out value="${ee}" />
			<br />
		</c:if>
	</form>
</body>
</html>