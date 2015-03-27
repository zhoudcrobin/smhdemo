<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>数据源设置</title>
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
	</head>
	<body>
		<div class="easyui-tabs" id="systemtab" border="false" fit="true">
			<div title="JDBC数据源">
				<iframe src='<c:url value="/common/datasource/jdbc/index.do"/>' id="editjdbcifr"  name="editjdbcifr" class="editifr" scrolling="no"></iframe>
			</div>			
			<div title="JNDI数据源" >
				<iframe src='<c:url value="/common/datasource/jndi/index.do"/>' id="editjndiifr"  name="editjndiifr" class="editifr" scrolling="no"></iframe>	
			</div>
			<div title="BEAN数据源" >
				<iframe src='<c:url value="/common/datasource/bean/index.do"/>' id="editbeanifr"  name="editbeanifr" class="editifr" scrolling="no"></iframe>	
			</div>					
			<div title="CUSTOM数据源" >
			    <iframe src='<c:url value="/common/datasource/custom/index.do"/>' id="editcustomifr"  name="editcustomifr" class="editifr" scrolling="no"></iframe> 
			</div>
		</div>	
	</body>
</html>