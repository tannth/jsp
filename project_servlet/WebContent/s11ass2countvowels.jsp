<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="aCount" value="${0}" scope="page" />
<c:set var="oCount" value="${0}" scope="page" />
<c:set var="eCount" value="${0}" scope="page" />
<c:set var="uCount" value="${0}" scope="page" />
<c:set var="iCount" value="${0}" scope="page" />
<c:set var="Count" value="${0}" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="s11ass2countvowels.jsp">
		Enter a sentence <input type="text" name="txtsentence"
			value="${pageScope.Sentence }"> <input type="submit"
			value="Go">
	</form>
	<%
		String str = request.getParameter("txtsentence");
	%>
	<c:set var="Sentence" value="<%=str%>" scope="page" />
	<c:if test="${Sentence!=null}">
		<%
			for (int i = 0; i < str.length(); i++) {
		%>
		<c:set var="Char" value="<%=str.substring(i, i + 1)%>" />
		<c:choose>
			<c:when test="${Char =='a' }">
				<c:set var="aCount" value="${aCount+1 }" />
			</c:when>
			<c:when test="${Char =='o' }">
				<c:set var="oCount" value="${oCount+1 }" />
			</c:when>
			<c:when test="${Char =='e' }">
				<c:set var="eCount" value="${eCount+1 }" />
			</c:when>
			<c:when test="${Char =='u' }">
				<c:set var="uCount" value="${uCount+1 }" />
			</c:when>
			<c:when test="${Char =='i' }">
				<c:set var="iCount" value="${iCount+1 }" />
			</c:when>
			<c:otherwise>

				<c:set var="Count" value="${Count+1 }" />

			</c:otherwise>
		</c:choose>
		<%
			}
		%>
		
		<h3> In the sentence: ${Sentence }</h3>
		<font face="Courier New">
		No of vowels =${aCount+eCount+oCount+iCount+uCount }<br>
		No of A = ${aCount }<br>
		No of E = ${eCount }<br>
		No of O = ${oCount }<br>
		No of U = ${uCount }<br>
		No of I = ${iCount }<br>
		Other characters = ${Count }<br>
		
		
		</font>
	</c:if>

</body>
</html>