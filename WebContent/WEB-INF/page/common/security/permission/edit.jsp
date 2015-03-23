<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<html>
	<head>
		<title>权限信息编辑</title>
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
					if(document.all.id.value==''){
						parent.queryReload();
						parent.closeWindow('#edit-window');
					}
				}
			 });
		</script>
	</head>
	<body >
		<f:form action="save.do" method="post" commandName="permission">
			<table >
				<tr>
					<td>权限名称：</td>
					<td>
						<f:input path="permissionName" class="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>所属权限组：</td>
					<td>
				        <f:select path="role.id">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${roleList}" itemValue="id" itemLabel="roleName"/>  
	        			</f:select> 
					</td>
				</tr>					
				<tr>
					<td>备注：</td>
					<td>
						<f:input path="remark" class="inputtext"/>
					</td>
				</tr>															
			</table>

			<f:hidden path="id"/>
			<input type="hidden" name="addrecordlist" value="${addrecordlist}"/>
			<input type="hidden" name="eventOP" value="${eventOP}"/>
		</f:form>
	</body>
</html>