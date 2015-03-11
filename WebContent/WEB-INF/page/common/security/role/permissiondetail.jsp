<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
	<head>
		<title>权限明细管理</title>	
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/default/easyui.css"/>' title="default">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/gray/easyui.css"/>' title="gray">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/black/easyui.css"/>' title="black">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/bootstrap/easyui.css"/>' title="bootstrap">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/metro/easyui.css"/>' title="metro">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/icon.css"/>'>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.min.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.easyui.min.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/js/easyuicrud.js"/>'></script>
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/css/pagebase.css"/>'>	
		<script type="text/javascript">
		$(function(){
			ewcmsBOBJ = new EwcmsBase();
			ewcmsBOBJ.delToolItem("修改");
			ewcmsBOBJ.delToolItem("删除");
			ewcmsBOBJ.delToolItem("查询");
			ewcmsBOBJ.delToolItem("新增");
			ewcmsBOBJ.delToolItem("缺省查询");
			ewcmsBOBJ.delFrozenItem("ck");
			ewcmsBOBJ.setQueryURL('<c:url value="/common/security/role/permissionquery.do?roleID=${roleID}"/>');
			ewcmsBOBJ.openDataGrid('#tt',{
                columns:[[
						{field:'id',title:'序号',width:50,sortable:true},
						{field:'permissionName',title:'权限名称',width:150},
		                {field:'remark',title:'备注',width:150}
                  ]],
                  idField:"id",
                  singleSelect:true
			});
		});
		</script>		
	</head>
	<body class="easyui-layout">
		<div region="center" style="padding:2px;" border="false">
	 		<table id="tt" fit="true"></table>	
	 	</div>
	</body>
</html>