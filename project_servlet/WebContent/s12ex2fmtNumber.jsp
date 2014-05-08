<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<fmt:setLocale value="en_IN"/>
Salary: <fmt:formatNumber value="${40000 }" type="currency" minFractionDigits="5"/><br/>
Today: <fmt:formatDate value="<%=new Date() %>"type="both" dateStyle="long" timeStyle="short"/><br/>

<fmt:setLocale value="vi_VN"/>
Salary: <fmt:formatNumber value="${40000 }" type="currency" minFractionDigits="5"/><br/>
Today: <fmt:formatDate value="<%=new Date() %>"type="both" dateStyle="full" timeStyle="short"/><br/>

<fmt:setLocale value="fr_FR"/>
Salary: <fmt:formatNumber value="${40000 }" type="currency" minFractionDigits="2"/><br/>
Today: <fmt:formatDate value="<%=new Date() %>"type="both" dateStyle="medium" timeStyle="short" pattern="dd-MM-yyyy"/><br/>

<fmt:setLocale value="en_US"/>
Salary: <fmt:formatNumber value="${40000 }" type="currency" minFractionDigits="2"/><br/>
Today: <fmt:formatDate value="<%=new Date() %>"type="both" dateStyle="long" timeStyle="short"/><br/>

<fmt:setLocale value="zh_CN"/>
Salary: <fmt:formatNumber value="${40000 }" type="currency" minFractionDigits="2"/><br/>
Today: <fmt:formatDate value="<%=new Date() %>"type="both" dateStyle="long" timeStyle="short"/><br/>

<fmt:setLocale value="hi_IN"/>
Salary: <fmt:formatNumber value="${40000 }" type="currency" minFractionDigits="5"/><br/>
Today: <fmt:formatDate value="<%=new Date() %>"type="both" dateStyle="long" timeStyle="short"/><br/>
</body>
</html>