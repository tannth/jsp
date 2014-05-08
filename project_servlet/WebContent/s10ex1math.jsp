<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<B>EL EXPRESSION LANGUAGE</B>
	<table border="1">
		<tr>
			<td>Concept</td>
			<td>Expression</td>
			<td>Result</td>
		</tr>
		<tr>
			<td>Literal</td>
			<td>${"${"}10}</td>
			<td>${10 }</td>
		</tr>
		<tr>
			<td>Addition</td>
			<td>${"${"}10+10}</td>
			<td>${10+10}</td>
		</tr>
		<tr>
			<td>Subtraction</td>
			<td>${"${"}10-10}</td>
			<td>${10-10}</td>
		</tr>
		<tr>
			<td>Multiplication</td>
			<td>${"${"}10*10}</td>
			<td>${10*10}</td>
		</tr>
		<tr>
			<td>Divition</td>
			<td>${"${"}10/3}</td>
			<td>${10/3}</td>
		</tr>
		<tr>
			<td>Modulus</td>
			<td>${"${"}10%3}</td>
			<td>${10%3}</td>
		</tr>
		<tr>
			<td>Divide by Zero</td>
			<td>${"${"}10/0}</td>
			<td>${10/0}</td>
		</tr>
		<tr>
			<td>Exponential</td>
			<td>${"${"}2E2}</td>
			<td>${2E2}</td>
		</tr>
		<tr>
			<td>Unary Minus</td>
			<td>${"${"}-10}</td>
			<td>${-10}</td>
		</tr>
	</table>
</body>
</html>