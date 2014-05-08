<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="currlang" value="${param.language}" />
	<c:if test="${not empty currlang}">
		<fmt:setLocale value="${currlang}" scope="session" />
	</c:if>
	<fmt:setBundle basename="labels" scope="session" />
	<h1>
		<fmt:message key="title" />
	</h1>
	<fmt:message key="select_language" />
	<form action="s12ex3changeLang.jsp">
		<input type="radio" name="language" value="en_US"
			<c:if test="${currlang == 'en_US'}">checked</c:if>>
		<fmt:message key="english" />
		<br /> <input type="radio" name="language" value="vi_VN"
			<c:if test="${currlang == 'vi_VN'}">checked</c:if>>
		<fmt:message key="vietnamese" />
		<br /> <input type="submit" value="<fmt:message key='new_language' />" />
	</form>
</body>
</html>