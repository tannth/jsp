<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home page</title>
</head>
<body>
	<font color="blue">Welcome, ${sessionScope.USERNAME}</font>
	<h1>Welcome to MVC World</h1>
	<form action="Controller">
		Name <input type="text" name="txtsearch" value="" /><br /> <input
			type="submit" value="Search" name="btnAction" />
	</form>
</body>
</html>