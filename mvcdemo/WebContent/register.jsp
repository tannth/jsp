<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<h1>Register Page</h1>
	<form action="Controller" method="post">
		Username <input type="text" name="txtusername" value="" /> <br />
		Password <input type="password" name="txtpassword" value="" /> <br />
		LastName <input type="text" name="txtlname" value="" /><br /> Admin
		<input type="checkbox" name="chkAdmin" value="1" /><br /> <input
			type="submit" value="Register" name="btnAction" />
	</form>
</body>
</html>