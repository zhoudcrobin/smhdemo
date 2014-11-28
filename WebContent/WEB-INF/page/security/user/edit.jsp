<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<html>
	<head>
		<title>文章分类属性</title>
		<script type="text/javascript" src='<c:url value="/comresource/css/pagebase.css"/>'></script>
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
			<input type="hidden"  name="addrecordlist"/>
			<input type="hidden" name="eventOP">
		</f:form>
	</body>
</html>