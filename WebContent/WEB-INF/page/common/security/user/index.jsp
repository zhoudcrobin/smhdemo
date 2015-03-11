<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
	<head>
		<title>系统用户</title>	
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
			ewcmsBOBJ.setQueryURL('<c:url value="/common/security/user/query.do"/>');
			//ewcmsBOBJ.delToolItem("修改");
			//ewcmsBOBJ.addToolItem("修改", "icon-edit","updCallBackSelf");
			//ewcmsBOBJ.delToolItem("删除");
			//ewcmsBOBJ.addToolItem("删除", "icon-remove","delCallBackSelf");
			ewcmsBOBJ.addToolItem("权限分配", "icon-remove","permissionAllocate");
			ewcmsBOBJ.openDataGrid('#tt',{
                columns:[[
						{field:'id',title:'序号',width:50,sortable:true},
						{field:'accountName',title:'用户帐号',width:150,sortable:true},
		                {field:'userInfo.realName',title:'真实姓名',width:150,formatter:function(val,row){
		                    return row.userInfo.realName;
		                }},
		                {field:'userInfo.birthday',title:'出生日期',width:150,formatter:function(val,row){
		                	return row.userInfo.birthday;
		                }}
                  ]],
                  idField:"id",
                  singleSelect:true
			});

			ewcmsOOBJ = new EwcmsOperate();
			ewcmsOOBJ.setQueryURL(ewcmsBOBJ.getQueryURL());
			ewcmsOOBJ.setInputURL('<c:url value="/common/security/user/edit.do"/>');
			ewcmsOOBJ.setDeleteURL('<c:url value="/common/security/user/delete.do"/>');
			
		});
		//function updCallBackSelf(){
			//ewcmsOOBJ.updOperateBack('',{idname:"accountName"});
		//}
		//function delCallBackSelf(){
			//ewcmsOOBJ.delOperateBack("accountName");
		//}
		
		function permissionAllocate(){
				var rows = $('#tt').datagrid('getSelections');
			    if(rows.length == 0){
			        $.messager.alert('提示','请选择修改记录','info');
			        return;
			    }
			    if(rows.length > 1){
			        $.messager.alert('提示','不能选择多条记录进行修改','info');
			        return;
			    }
			    var url = '<c:url value="/common/security/user/roleallocate.do"/>?userID='+rows[0].id;            
			  
			    openWindow('#edit-window',{url:url,iframeID:'#editifr'});			
		}
		</script>		
	</head>
	<body class="easyui-layout">
		<div region="center" style="padding:2px;" border="false">
	 		<table id="tt" fit="true"></table>	
	 	</div>
        <div id="edit-window" class="easyui-window" closed="true" icon="icon-winedit" title="编辑-用户信息" style="display:none;">
            <div class="easyui-layout" fit="true">
                <div region="center" border="false">
                   <iframe id="editifr"  name="editifr" class="editifr" frameborder="0" onload="iframeFitHeight(this);"  scrolling="no"></iframe>
                </div>
                <div region="south" border="false" style="text-align:center;height:28px;line-height:28px;background-color:#f6f6f6">
                    <a class="easyui-linkbutton" icon="icon-save" href="javascript:void(0)" onclick="saveOperator()">保存</a>
                    <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="closeWindow('#edit-window');">取消</a>
                </div>
            </div>
        </div>	
        <div id="query-window" class="easyui-window" closed="true" icon='icon-search' title="查询-用户信息"  style="display:none;">
            <div class="easyui-layout" fit="true"  >
                <div region="center" border="false" >
                <f:form id="queryform">
                	<table class="formtable">
                            <tr>
                                <td class="tdtitle">用户帐号：</td>
                                <td class="tdinput">
                                    <input type="text" id="accountName" name="accountName" class="inputtext"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="tdtitle">真实姓名：</td>
                                <td class="tdinput">
                                    <input type="text" name="realName" class="inputtext"/>
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