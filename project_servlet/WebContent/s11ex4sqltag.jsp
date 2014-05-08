<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jstl/sql_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%-- <%
		try {
			// Step 1. Load the JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Step 2. //Create a Connection object
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hr",
					"hr");

			System.out.println("got connection");

			// Step 3. Create a Statement object and call its executeUpdate
			// method to insert a record
			Statement s = con.createStatement();
			/* String sql = "INSERT INTO test VALUES ('TEST PURPOSE')";
			s.executeUpdate(sql); */

			// Step 4. Use the same Statement object to obtain a ResultSet object
		String	sql = "SELECT FIRST_NAME FROM EMPLOYEES";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				out.println(rs.getString(1) + "<br>");
			}
			rs.close();
			s.close();
			con.close();
		} catch (ClassNotFoundException e1) {
			// JDBC driver class not found, print error message to the console
			System.out.println(e1.toString());
		} catch (SQLException e2) {
			// Exception when executing java.sql related commands, print error message to the console
			System.out.println(e2.toString());
		} catch (Exception e3) {
			// other unexpected exception, print error message to the console
			System.out.println(e3.toString());
		}
	%> --%>

	<sql:setDataSource var="sqlDS" driver="oracle.jdbc.driver.OracleDriver"
		url="jdbc:oracle:thin:@localhost:1521:xe" user="hr" password="hr" />
	<sql:query dataSource="${sqlDS}" var="emp"> 
	SELECT EMPLOYEE_ID,FIRST_NAME,PHONE_NUMBER FROM EMPLOYEES
	</sql:query>
	<table border="1">
		<tr>
			<c:forEach var="colName" items="${emp.columnNames}">
				<th><c:out value="${colName}" /></th>
			</c:forEach>
		</tr>
		 <c:forEach var="row" items="${emp.rowsByIndex}">
			<tr>
				<c:forEach var="column" items="${row}">
					<td><c:out value="${column}" /></td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>