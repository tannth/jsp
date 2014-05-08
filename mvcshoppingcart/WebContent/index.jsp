<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h1>MVC Shopping Cart Demo</h1>
	<form action="Controller">
		Please, choose your favourite book:<br /> <select name="cboBook"
			size="20">
			<option selected="selected">Common Gateway Interface - CGI</option>
			<option>Servlet</option>
			<option>JavaServe Page - JSP</option>
			<option>Tomcat Server</option>
			<option>Struts</option>
			<option>JavaServer Faces - JSF</option>
			<option>Integrating Java with XML - IXJ</option>
			<option>Java Web Services - JWS</option>
			<option>Enterprise Java Beans -EJB</option>
			<option>Jboss Server</option>
			<option>Glassfish Server</option>
		</select><br /> 
		<input type="submit" value="View Cart" name="action" /> 
		<input type="submit" value="Add to Cart" name="action" />
	</form>
</body>
</html>