<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<html>
	<head>
		<title>用户信息修改</title>
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/default/easyui.css"/>' title="default">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/css/pagebase.css"/>' title="default">
	</head>
	<body >
		<f:form action="updinfo.do" method="post" commandName="userInfo">
			<table>
				<tr>
					<td>真实姓名：</td>
					<td>
						<f:input path="realName" class="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>出生日期：</td>
					<td>
						<f:input path="birthday" class="inputtext"/>
					</td>
				</tr>	
				<tr>
					<td>联系电话：</td>
					<td>
						<f:input path="phone" class="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>email邮件：</td>
					<td>
						<f:input path="email" class="inputtext"/>
					</td>
				</tr>	
				<tr>
                    <td colspan="2">
                    	<span class="error">
							${message}
			            </span>	
                    </td>
                </tr>														
			</table>
			<f:hidden path="id"/>
		</f:form>
	</body>
</html>