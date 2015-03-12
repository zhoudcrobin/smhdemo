<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
	<head>
		<title>登录日志</title>	
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
			ewcmsBOBJ.setQueryURL('<c:url value="/common/security/loginlog/query.do"/>');
			ewcmsBOBJ.delToolItem("新增");
			ewcmsBOBJ.delToolItem("修改");
			ewcmsBOBJ.delToolItem("删除");
			ewcmsBOBJ.openDataGrid('#tt',{
                columns:[[
						{field:'id',title:'序号',width:50,sortable:true},
						{field:'accountName',title:'登录账号',width:150},
		                {field:'loginTime',title:'登录时间',width:150},
						{field:'ipAddress',title:'登录ip地址',width:150}
                  ]],
                  idField:"id",
                  singleSelect:true
			});

			ewcmsOOBJ = new EwcmsOperate();
			ewcmsOOBJ.setQueryURL(ewcmsBOBJ.getQueryURL());
			
		});
		</script>		
	</head>
	<body class="easyui-layout">
		<div region="center" style="padding:2px;" border="false">
	 		<table id="tt" fit="true"></table>	
	 	</div>
        <div id="query-window" class="easyui-window" closed="true" icon='icon-search' title="查询-登录日志"  style="display:none;">
            <div class="easyui-layout" fit="true"  >
                <div region="center" border="false" >
                <f:form id="queryform">
                	<table class="formtable">
                            <tr>
                                <td class="tdtitle">登录账号：</td>
                                <td class="tdinput">
                                    <input type="text"  name="accountName" class="inputtext"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="tdtitle">登录ip地址：</td>
                                <td class="tdinput">
                                    <input type="text" name="ipAddress" class="inputtext"/>
                                </td>
                            </tr>    
                            <tr>
                                <td class="tdtitle">登录日期从：</td>
                                <td class="tdinput">
                                    <input type="text" name="loginTimeStart" class="inputtext"/>至<input type="text" name="loginTimeEnd" />
                                </td>
                            </tr>                         
               		</table>
               	</f:form>
                </div>
                <div region="south" border="false" style="text-align:center;height:28px;line-height:28px;background-color:#f6f6f6">
                    <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="querySearch();">查询</a>
                    <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="closeWindow('#query-window');">取消</a>
                </div>
            </div>
        </div>      	
	</body>
</html>