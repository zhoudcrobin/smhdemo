<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<html>
	<head>
		<title>文章分类属性</title>
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/default/easyui.css"/>' title="default">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/gray/easyui.css"/>' title="gray">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/black/easyui.css"/>' title="black">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/bootstrap/easyui.css"/>' title="bootstrap">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/metro/easyui.css"/>' title="metro">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/icon.css"/>'>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.min.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.easyui.min.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/skin/skin.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/css/pagebase.css"/>'></script>	
		<script type="text/javascript" src='<c:url value="/comresource/js/ewcms.base.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/js/ewcms.func.js"/>'></script>        
	</head>
	<body>
		<f:form action="save.do" method="post" modelAttribute="user">
			<table class="formtable" >
				<tr>
					<td>用户账号：</td>
					<td>
						<f:input path="accountName"/>
					</td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td>
						<f:input path="age"/>
					</td>
				</tr>
				<tr>
					<td>真实姓名：</td>
					<td>
						<f:input path="niceName"/>
					</td>
				</tr>								
			</table>
			<c:if test="${empty selections}">
				<input type="hidden" id="selections" name="selections" value=""/>
			</c:if>
			<c:forEach var="selection" items="${selections}">
				<input type="hidden" id="selections" name="selections" value="${selection}"/>
			</c:forEach>
		</f:form>
	</body>
</html>