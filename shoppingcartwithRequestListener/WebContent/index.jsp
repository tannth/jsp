
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h1>Shopping cart with listener</h1>
	<form action="ProcessServlet">
		<select name="cboBook">
			<option>Servlet</option>
			<option>Tomcat</option>
			<option>Struts</option>
			<option>JSP</option>
			<option>EJB</option>
			<option>SQL</option>
			<option>C#</option>
		</select><br /> <input type="submit" value="Add to Cart" name="action">
		 <input type="submit" value="View Cart" name="action">
	</form>
</body>
</html>