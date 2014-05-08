<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>
		<font color="Red">Welcome</font>
	</h1>
	<font size="5" color="blue"> <%
 	Calendar cal = Calendar.getInstance();
 	if (cal.get(Calendar.AM_PM) == Calendar.AM) {
 		out.print("Good Morning!");
 	} else {
 		out.print("Good Afternoon!");
 	}
 %> <br> Today's date is: <%=new Date()%> <br>
	</font>
</body>
</html>