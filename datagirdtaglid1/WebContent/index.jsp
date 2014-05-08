<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="myLib"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Tag file demo</h1>
	<h3>Kết nối tĩnh với truy vấn không tham số</h3>

	<myLib:myDataGird url="jdbc:oracle:thin:@localhost:1521:xe"
		driver="oracle.jdbc.driver.OracleDriver" user="hr" password="hr"
		sql="select * from books" />
	<h3>Kết nối tĩnh với truy vấn có tham số</h3>

	<myLib:myDataGird url="jdbc:oracle:thin:@localhost:1521:xe"
		driver="oracle.jdbc.driver.OracleDriver" user="hr" password="hr"
		sql="select * from books where id = ?" parUsername="1001" />
	<h3>Kết nối tĩnh với truy vấn có tham số truyen wild card</h3>

	<myLib:myDataGird url="jdbc:oracle:thin:@localhost:1521:xe"
		driver="oracle.jdbc.driver.OracleDriver" user="hr" password="hr"
		sql="select * from books where id like ?" parUsername="%001" />




</body>
</html>