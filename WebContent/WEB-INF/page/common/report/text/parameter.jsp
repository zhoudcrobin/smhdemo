<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<html>
	<head>
		<title>报表信息编辑</title>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.min.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.easyui.min.js"/>'></script>
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/default/easyui.css"/>' title="default">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/css/pagebase.css"/>' title="default">
	</head>
	<body >
		<f:form action="parameter.do" method="post">
			<table width="100%" border=1>
				<tr>
					<td>编号</td>
					<td>参数名</td>
					<td>中文说明</td>
					<td>默认值</td>
					<td>数据输入方式</td>
					<td>辅助数据</td>
				</tr>
				<c:forEach items="${parameterList}" var="parameter" varStatus="vs">  
				<tr>
					<td><c:out value="${parameter.id}"/><input type="hidden" name="parameterList[${vs.index}].id" value="${parameter.id}"></td>
					<td><c:out value="${parameter.enName}"/></td>
					<td><input type="text" name="parameterList[${vs.index}].cnName" value="${parameter.cnName}"></td>
					<td><input type="text" name="parameterList[${vs.index}].defaultValue" value="${parameter.defaultValue}"></td>
					<td>
						<select name="parameterList[${vs.index}].type">
							<option value="">--Please Select--</option>
							<c:forEach items="${typeEnum}" var="type">
								<c:if test="${parameter.type.name==type.name}">
									<option value="${type.name}" selected="selected">${type.description}</option>
								</c:if>
								<c:if test="${parameter.type.name!=type.name}">
									<option value="${type.name}">${type.description}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td><input type="text" name="parameterList[${vs.index}].value" value="${parameter.value}"></td>
				</tr>
				</c:forEach>	
			</table>
			<input type="hidden" name="textId" value="${textId}"/>
		</f:form>
	</body>
</html>