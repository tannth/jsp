<%@ tag language="java" pageEncoding="UTF-8"
	description="put the tag description"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@attribute name="url"%>
<%@attribute name="driver"%>
<%@attribute name="user"%>
<%@attribute name="password"%>
<%@attribute name="dataSource"%>
<%@attribute name="sql"%>

<%@tag dynamic-attributes="dynaParams"%>

<c:if test="${not empty url and not empty driver }">
	<sql:setDataSource url="${url }" driver="${driver }" user="${user }"
		password="${password }" var="con" />
</c:if>
<c:if test="${not empty  dataSource}">
	<sql:setDataSource dataSource="${dataSource }" var="con" />
</c:if>

<c:if test="${not empty con }">
	<sql:query dataSource="${con }" var="rs">
		${sql }
		<c:if test="${not empty dynaParams}">
			<c:forEach var="dynaParam" items="${dynaParams }">
				<sql:param value="${dynaParam.value }" />
			</c:forEach>
		</c:if>
	</sql:query>
</c:if>

<c:if test="${not empty rs}">
	<table border="1">
		<thead>
			<tr>
				<th>No.</th>
				<c:forEach var="colName" items="${rs.columnNames }">
					<th>${colName}</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:set var="count" value="0" />
			<c:forEach var="rows" items="${rs.rowsByIndex}">
				<c:set var="count" value="${count + 1 }" />
				<tr>
					<td>${count }</td>
					<c:forEach var="colValue" items="${rows }">
						<td>${colValue }</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</c:if>