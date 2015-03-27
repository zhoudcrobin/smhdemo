<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
	<head>
		<title>custom 数据源管理</title>	
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
		<script type="text/javascript" src='<c:url value="/comresource/easyui/datagrid.detailview.js"/>'></script>		
		<script type="text/javascript">
		$(function(){
			ewcmsBOBJ = new EwcmsBase();
			ewcmsBOBJ.setQueryURL('<c:url value="/common/datasource/custom/query.do"/>');
			ewcmsBOBJ.openDataGrid('#tt',{
                columns:[[
							{field:'id',title:'编号',width:50,sortable:true},
			                {field:'name',title:'名称',width:100},
			                {field:'customName',title:'custom名称',width:200},
			                {field:'customMethod',title:'custom方法',width:200},
			                {field:'remark',title:'说明',width:100},
			                {field:'connectTest',title:'测试',width:30,
			                	formatter : function(val, rec) {
			                		return "&nbsp;<a href='javascript:void(0);' onclick='test(" +  rec.id + ");'><img src=<c:url value='/comresource/image/datasource/connect_test.png'/> width='13px' height='13px' title='测试' style='border:0'/></a>";
			                	}
			                }
	                  ]],               
                  idField:"id",
                  singleSelect:true
			});

			ewcmsOOBJ = new EwcmsOperate();
			ewcmsOOBJ.setQueryURL(ewcmsBOBJ.getQueryURL());
			ewcmsOOBJ.setInputURL('<c:url value="/common/datasource/custom/edit.do"/>');
			ewcmsOOBJ.setDeleteURL('<c:url value="/common/datasource/custom/delete.do"/>');
		});
		function test(id){
			$.post('<c:url value="/common/datasource/connect.do"/>', {"id":id}, function(data) {
				$.messager.alert('提示', data, 'info');
			});
			return false;
		}
		</script>		
	</head>
	<body class="easyui-layout">
		<div region="center" style="padding:2px;" border="false">
	 		<table id="tt" fit="true"></table>	
	 	</div>
        <div id="edit-window" class="easyui-window" closed="true" icon="icon-winedit" title="编辑-custom数据源 " style="display:none;">
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
        <div id="query-window" class="easyui-window" closed="true" icon='icon-search' title="查询-custom数据源"  style="display:none;">
            <div class="easyui-layout" fit="true"  >
                <div region="center" border="false" >
                <f:form id="queryform">
                	<table class="formtable">
                            <tr>
                                <td class="tdtitle">数据源名称：</td>
                                <td class="tdinput">
                                    <input type="text"  name="name" class="inputtext"/>
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