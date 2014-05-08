<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Percentage of Marks</h1>
	<form action="s10form.jsp">
		Enter marks for English:<input type="text" name="english"
			value="${param.english}" size="5" maxlength="2">/100<br />
		Enter marks for Science <input type="text" name="science"
			value="${param.science }" size="5" maxlength="2">/100 <br />
		Enter marks for History <input type="text" name="history"
			value="${param.history }" size="5" maxlength="2">/100 <br />
		Enter marks for Geography <input type="text" name="geography"
			value="${param.geography }" size="5" maxlength="2">/100 <br />
		Enter marks for Math <input type="text" name="math"
			value="${param.math }" size="5" maxlength="2">/100 <br />
		Total: ${param.english+ param.science+param.history+param.geography+param.math }<br />
		Avg (%): ${(param.english+ param.science+param.history+param.geography+param.math)/500*100 }<br />
		<input type="submit" value="Calculate" />




	</form>
</body>
</html>