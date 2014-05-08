<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="user" class="s9.ex2.UserdData" scope="session" />

	<b>Your details are:</b>
	<br>
	<br>
	<br> Name: <%=user.getUsername()%><br>
	 Email: <%= user.getEmail() %><br>
	 Age: <%= user.getAge() %>
	
</body>
</html>