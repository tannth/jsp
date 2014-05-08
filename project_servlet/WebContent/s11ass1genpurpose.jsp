<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="Honda" var="car0" scope="page" />
<c:set value="Porsche" var="car1" scope="page" />
<c:set value="Toyota" var="car2" scope="page" />
<c:set value="${1+2}" var="count" scope="page" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Total:
	<c:out value="${count }" />
	<br /> First Car:
	<c:out value="${car0 }" />
	<br /> Second:
	<c:out value="${car1 }" />
	<br /> Third:
	<c:out value="${car2 }" />
	<br />
</body>
</html>