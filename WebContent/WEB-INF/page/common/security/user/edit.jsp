<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<html>
	<head>
		<title>系统用户信息</title>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.min.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.easyui.min.js"/>'></script>
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/default/easyui.css"/>' title="default">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/css/pagebase.css"/>' title="default">
		<script type="text/javascript">
			 $(function(){
				if(document.all.eventOP.value=='add'){
					var recordList = document.all.addrecordlist.value;
					if(recordList!='')
					parent.queryAddRecordList(recordList.split(","));
					
				}else if(document.all.eventOP.value=='upd'){
					if(document.all.accountName.value==''){
						parent.queryReload();
						parent.closeWindow('#edit-window');
					}
				}
			 });
		</script>
	</head>
	<body >
		
		<f:form action="save.do" method="post" commandName="user">
			<table>
				<tr>
					<td>用户账号：</td>
					<td>
					<c:choose>
					   <c:when test="${eventOP=='add'}">
					   		<f:input path="accountName" class="inputtext"/><label style="color: red;">*</label>
					   </c:when>  
					   <c:otherwise>
					   		<f:input path="accountName" readonly="true" class="inputdisabled"/><label style="color: red;">*</label>
					   </c:otherwise> 
					</c:choose>
					</td>
					<td>
					<font color=red><f:errors path="accountName"/></font>
					</td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td>
						<f:input path="age" class="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>真实姓名：</td>
					<td>
						<f:input path="niceName" class="inputtext"/>
					</td>
				</tr>								
			</table>
			<input type="hidden" name="addrecordlist" value="${addrecordlist}"/>
			<input type="hidden" name="eventOP" value="${eventOP}"/>
		</f:form>
	</body>
</html>