<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@page import="java.util.Date"%>

<c:if test="${param.language =='en' }">
	<fmt:setLocale value="en" scope="session" />
</c:if>
<c:if test="${param.language =='de' }">
	<fmt:setLocale value="de" scope="session" />
</c:if>
<c:if test="${param.language =='sv' }">
	<fmt:setLocale value="sv" scope="session" />
</c:if>
<fmt:setBundle basename="input" var="inputBundle" scope="session" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title" /></title>
</head>
<body bgcolor="white">
	<h1>
		<fmt:message key="title" />
	</h1>

	<fmt:message key="select_language" />
	<form action="s12ass1formatdata.jsp" method="post">
		<c:set var="currLang" value="${inputBundle.locale.language}" />

		<fmt:message key="english" />
		<br> <input type="radio" name="language" value="en"
			<c:if test="${currLang ='en' }">checked </c:if>>

		<fmt:message key="swedish" />
		<br> <input type="radio" name="language" value="de"
			<c:if test="${currLang ='de' }">checked </c:if>>

		<fmt:message key="german" />
		<br>
		<p>
			<input type="submit" value="<fmt:message key="new_language"/>">
	</form>


	<h1>Formatted Date and Number</h1>
	Date string converted to the internal Java Date type:
	<fmt:formatDate value="<%=new Date()%>" dateStyle="full" />
	<p>


		Number string converted to the internal Java Date type:
		<fmt:formatDate value="1000.9" dateStyle="####.00" />
</body>
</html>