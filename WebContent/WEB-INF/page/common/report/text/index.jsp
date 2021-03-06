<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
	<head>
		<title>文字报表管理</title>	
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
			ewcmsBOBJ.setQueryURL('<c:url value="/common/report/text/query.do"/>');
			ewcmsBOBJ.addToolItem('参数设置', 'icon-article-preview', parameterSetOperate);
			ewcmsBOBJ.addToolItem('预览', 'icon-article-preview', previewOperate);
			ewcmsBOBJ.openDataGrid('#tt',{
                columns:[[
						{field:'id',title:'序号',width:50,sortable:true},
						{field:'name',title:'报表名称',width:200},
		                {field:'createDate',title:'创建时间',width:145},
		                {field:'updateDate',title:'更新时间',width:145},
		                {field:'remark',title:'备注',width:200},
		                {field:'hidden',title:'是否隐藏',width:60},
		                {field:'download',title:'下载',width:50,
		                	formatter:function(val,rec){
		                		return "&nbsp;<a href='javascript:void(0);'><img src=<c:url value='/comresource/image/report/download.png' /> onclick='download(" +  rec.id + ");' width='13px' height='13px' title='下载' style='border:0'/></a>";
		                	}
		                }
                  ]],                
                  idField:"id",
                  singleSelect:true
			});

			ewcmsOOBJ = new EwcmsOperate();
			ewcmsOOBJ.setQueryURL(ewcmsBOBJ.getQueryURL());
			ewcmsOOBJ.setInputURL('<c:url value="/common/report/text/edit.do"/>');
			ewcmsOOBJ.setDeleteURL('<c:url value="/common/report/text/delete.do"/>');
		});
		
		function download(id){
			window.open('<c:url value="/common/report/text/download.do"/>?textId=' + id,'popup','width=1280,height=700,left=' + (window.screen.width - 1280)/ 2 + ',top=' + (window.screen.height - 700) / 2);

		}
		function previewOperate(){
			var rows = $('#tt').datagrid('getSelections');
			if (rows.length == 0) {
				$.messager.alert('提示', '请选择预览记录', 'info');
				return;
			}
			if (rows.length > 1) {
				$.messager.alert('提示', '只能选择一个预览', 'info');
				return;
			}
			var url = '<c:url value="/common/report/build/make.do"/>?reportType=text&reportId='+ rows[0].id;
			$('#editifr').attr('src',url);
			ewcmsBOBJ.openWindow("#edit-window");
		}
		
		function parameterSetOperate(){
			var rows = $('#tt').datagrid('getSelections');
			if (rows.length == 0) {
				$.messager.alert('提示', '请选择报表记录', 'info');
				return;
			}
			if (rows.length > 1) {
				$.messager.alert('提示', '只能选择一条记录', 'info');
				return;
			}
			var url = '<c:url value="/common/report/text/parameter.do"/>?textId='+ rows[0].id;
			$('#editifr').attr('src',url);
			ewcmsBOBJ.openWindow("#edit-window",{width:800,height:400,title:"参数设置"});
		}
		</script>		
	</head>
	<body class="easyui-layout">
		<div region="center" style="padding:2px;" border="false">
	 		<table id="tt" fit="true"></table>	
	 	</div>
        <div id="edit-window" class="easyui-window" closed="true" icon="icon-winedit" title="编辑-报表信息" style="display:none;">
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
        <div id="query-window" class="easyui-window" closed="true" icon='icon-search' title="查询-报表信息"  style="display:none;">
            <div class="easyui-layout" fit="true"  >
                <div region="center" border="false" >
                <f:form id="queryform">
                	<table class="formtable">
                            <tr>
                                <td class="tdtitle">报表名称：</td>
                                <td class="tdinput">
                                    <input type="text"  name="reportName" class="inputtext"/>
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