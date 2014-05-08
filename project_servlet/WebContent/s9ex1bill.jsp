<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usebean DEMO</title>
</head>
<body>
	<h1 align="center">Welcome to Aptech</h1>
	<jsp:useBean id="beanalias" scope="session" class="s9.ex1.Billing" />
	<jsp:setProperty property="registrationcost" name="beanalias"
		value="300" />
	<jsp:setProperty property="quantity" name="beanalias" value="50" />

	<p>
		Cost of each registration is:
		<jsp:getProperty property="registrationcost" name="beanalias" /><br />
		Total number of employees registered:
		<jsp:getProperty property="quantity" name="beanalias" /><br />
		<br />

		<%
			int cost = beanalias.getRegistrationcost();
			int quantity = beanalias.getQuantity();

			int total = cost * quantity;

			out.print("Total amount to be paid: Rs." + total + "/-");
		%>
		<br />
		<br />
	</p>
</body>
</html>