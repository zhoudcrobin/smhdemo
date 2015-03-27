<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<html>
	<head>
		<title>用户权限分配</title>
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/default/easyui.css"/>' title="default">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/css/pagebase.css"/>' title="default">
	</head>
	<body>
		<c:if test="${empty actionResult}">
		<f:form action="roleallocate.do" method="post" commandName="roleSelect">
			<table cellPadding=1  cellSpacing=2  width=450>
				<tr>
					<td align="center">${accountName}用户的权限分配</td>
				</tr>	
				<tr>
					<td>
						<f:checkboxes path="roles" items="${roleMap}"/> 
					</td>
				</tr>																		
			</table>
			<input type="hidden" name="userID" value="${userID}"/>
		</f:form>	
		</c:if>
		<c:if test="${not empty actionResult}">
		<c:out value="${actionResult}"/>
		</c:if>
	</body>
</html>