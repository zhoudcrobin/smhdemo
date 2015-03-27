<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
    <head>
        <title>smhdemo</title>
        <script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.min.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.easyui.min.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/skin/skin.js"/>'></script>
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/default/easyui.css"/>' title="default">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/gray/easyui.css"/>' title="gray">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/black/easyui.css"/>' title="black">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/bootstrap/easyui.css"/>' title="bootstrap">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/metro/easyui.css"/>' title="metro">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/icon.css"/>'>
		<script type="text/javascript" src='<c:url value="/comresource/js/home.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/js/easyuicrud.js"/>'></script>
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/css/pagebase.css"/>'>
        <script type="text/javascript">
            var _home = new home();
            $(function(){
            	var clock = new Clock();
            	clock.display(document.getElementById("clock"));
                ewcmsBOBJ = new EwcmsBase();
                ewcmsOOBJ = new EwcmsOperate();
                _home.init({
                    user:'<c:url value="/common/security/loginuser/updinfo.do"/>',
                    password:'<c:url value="/common/security/loginuser/updpassword.do"/>',
                    exit:'<c:url value="/common/security/loginuser/logout.do"/>'
                });
                
            });
        </script>
        <style type="text/css">
	         .nav-item{text-align:center; height:80px;}
			 .nav-item img{border:0;}
			 a:link {text-decoration: none;color: #1B5978;}
			 a:visited {text-decoration: none;color: #1B5978;}
			 a:hover {text-decoration: underline;color: red;}
        </style>
    </head>
    <body class="easyui-layout">
		<div data-options="region:'north',border:false" style="height:60px;">
			<table width="100%" cellSpacing=0 cellPadding=0>
        		<tr style="background:url('<c:url value="/comresource/image/topbg.gif"/>');">
        			<td width="50%" style="text-align: left;"><font style="FONT-SIZE: 40px;color:white">  smhdemo 框架演示</font></td>
        			<td width="50%">
        				<table width="100%">
        					<tr>
			        			<td height="30px" width="97%" style="text-align: right"><span style="font-size:13px;font-weight: bold;color:white;">标签1|标签2|标签3|<shiro:principal/> </span></td>
        						<td width="2%"><a id="button-main" href="#" style="border:0;padding:0;"><img src="<c:url value='/comresource/image/exit.png'/>" width="17" height="17" style="border:0;"/></a></td>
        						<td width="1%"></td>
        					</tr>
        					<tr>
        						<td height="20px" colspan="2" >
        							<table width="100%">
        								<tr>
        								<td></td>
			        						<td width="350" style="text-align:right">
			        							<div class="bs">
													<a class="styleswitch a1" style="cursor: pointer" title="黄色" rel="metro"></a>
													<a class="styleswitch a2" style="cursor: pointer" title="浅蓝色" rel="bootstrap"></a>
													<a class="styleswitch a3" style="cursor: pointer" title="蓝色" rel="default"></a>	
													<a class="styleswitch a4" style="cursor: pointer" title="黑色" rel="black"></a>	
													<a class="styleswitch a5" style="cursor: pointer" title="灰色" rel="gray"></a>		
												</div>
												<span style="color:white;font-size:12px;" id="clock"></span>
			        						</td>
			        					</tr>
			        				</table>
			        			</td>
        					</tr>
        				</table>
        			</td>
        		</tr>
        	</table>
             <div id="mm" class="easyui-menu" style="width:120px;display:none;">
                <div id="user-menu" iconCls="icon-edit">用户信息修改</div>
                <div id="password-menu">密码修改</div>
                <div class="menu-sep"></div>
                <div id="exit-menu">退出</div>
             </div>
		</div>
		<div data-options="region:'west',split:true,title:'功能菜单列表'" style="width:180px;padding:1px;overflow:hidden;">
			<div  class="easyui-accordion"  fit=true border="false">
				<shiro:hasRole name="adminmanager">
	               	<div title="权限管理" style="overflow:auto;">
	               	    <div class="nav-item">
	                         <a href="javascript:_home.addTab('系统用户','common/security/user/index.do')">
	                            <img src='<c:url value="comresource/image/user.png"/>' style="border:0"/><br/>
	                            <span>系统用户</span>
	                        </a>
	               	    </div>  
	               	    <div class="nav-item">
	                         <a href="javascript:_home.addTab('权限组','common/security/role/index.do')">
	                            <img src='<c:url value="comresource/image/group.png"/>' style="border:0"/><br/>
	                            <span>权限组</span>
	                        </a>
	               	    </div>
	               	    <div class="nav-item">
	                         <a href="javascript:_home.addTab('权限明细','common/security/permission/index.do')">
	                            <img src='<c:url value="comresource/image/role.png"/>' style="border:0"/><br/>
	                            <span>权限明细</span>
	                        </a>
	               	    </div>
	               	    <div class="nav-item">
	                         <a href="javascript:_home.addTab('登录日志','common/security/loginlog/index.do')">
	                            <img src='<c:url value="comresource/image/loginlog.png"/>' style="border:0"/><br/>
	                            <span>登录日志</span>
	                        </a>
	               	    </div>  	               	       	               	                        	
	               	</div>
               	</shiro:hasRole>
	               	<div title="报表系统" style="overflow:auto;">
	               	    <div class="nav-item">
	                         <a href="javascript:_home.addTab('报表分类','common/report/category/index.do')">
	                            <img src='<c:url value="comresource/image/user.png"/>' style="border:0"/><br/>
	                            <span>报表分类</span>
	                        </a>
	               	    </div> 
	               	    <div class="nav-item">
	                         <a href="javascript:_home.addTab('文字报表','common/report/text/index.do')">
	                            <img src='<c:url value="comresource/image/user.png"/>' style="border:0"/><br/>
	                            <span>文字报表</span>
	                        </a>
	               	    </div> 
	               	    <div class="nav-item">
	                         <a href="javascript:_home.addTab('图形报表','common/report/chart/index.do')">
	                            <img src='<c:url value="comresource/image/user.png"/>' style="border:0"/><br/>
	                            <span>图形报表</span>
	                        </a>
	               	    </div>
	               	    <div class="nav-item">
	                         <a href="javascript:_home.addTab('报表制作','common/report/build/index.do')">
	                            <img src='<c:url value="comresource/image/user.png"/>' style="border:0"/><br/>
	                            <span>报表制作</span>
	                        </a>
	               	    </div>		               	    	               	    	               	     	               	       	               	                        	
	               	</div> 
	               	<div title="系统基本管理" style="overflow:auto;">
	               	    <div class="nav-item">
	                         <a href="javascript:_home.addTab('数据源','common/datasource/index.do')">
	                            <img src='<c:url value="comresource/image/user.png"/>' style="border:0"/><br/>
	                            <span>数据源</span>
	                        </a>
	               	    </div>		               	    	               	    	               	     	               	       	               	                        	
	               	</div>   	               	              		
        	</div>  
		</div>        
      	<div data-options="region:'center'" style="overflow:hidden;">
            <div class="easyui-tabs" id="systemtab" fit="true" border="false">
                <div title="首页" style="padding:5px;overflow:hidden;text-align:center">
                  <h1> 欢迎使用smhdemo系统!</h1>
                </div>
            </div>      	
      	</div>
        <div id="edit-window" class="easyui-window" closed="true" icon="icon-winedit" title="编辑窗口" style="display:none;">
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
    </body>
</html>