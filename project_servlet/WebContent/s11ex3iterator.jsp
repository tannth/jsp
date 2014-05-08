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
	<c:set var="language" value="Core Java:Servlet;JSP:EJB" scope="page" />
	<%!String[] names = { "Sun Microsystems", "Microsoft", "Oracle", "IBM" };%>
	<b>Company</b>
	<br />
	<c:forEach var="company" items="<%=names%>">
		<c:out value="${company}" />
		<br />
	</c:forEach>
	<hr />
	<b>Language</b>
	<br />
	<c:forTokens items="${language}" delims=":;" var="lang">
		<c:out value="${lang}" />
		<br />
	</c:forTokens>
	<hr />
	<b>Counter</b>
	<c:forEach begin="1" end="10" step="1" var="counter">
		<c:out value="${11 - counter}" />
	</c:forEach>
</body>
</html>