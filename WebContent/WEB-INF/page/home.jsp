<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<script type="text/javascript" src='<c:url value="/comresource/css/pagebase.css"/>'></script>
        <script type="text/javascript">
            var _home = new home();
            $(function(){
            	var clock = new Clock();
            	clock.display(document.getElementById("clock"));
                _home.init({
                    user:'<s:url action="user" namespace="/account"/>',
                    password:'<s:url action="password" namespace="/account"/>',
                    exit:'<s:url value="/logout.do"/>'
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
			        			<td height="30px" width="97%" style="text-align: right"><span style="font-size:13px;font-weight: bold;color:white;">标签1|标签2|标签3|<s:property value="realName"/> </span></td>
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
                <div id="user-menu" iconCls="icon-edit">修改用户信息</div>
                <div id="password-menu" iconCls="icon-password">修改密码</div>
                <div class="menu-sep"></div>
                <div id="exit-menu" iconCls="icon-exit">退出</div>
             </div>
		</div>
		<div data-options="region:'west',split:true,title:'菜单'" style="width:180px;padding:1px;overflow:hidden;">
			<div  class="easyui-accordion"  fit=true border="false">
               	<div title="报表管理" style="overflow:auto;">
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('系统用户','security/user/index.do')">
                            <img src='<c:url value="comresource/image/user.png"/>' style="border:0"/><br/>
                            <span>系统用户</span>
                        </a>
               	    </div>                  	
               	</div>
        	</div>  
		</div>        
      	<div data-options="region:'center'" style="overflow:hidden;">
            <div class="easyui-tabs" id="systemtab" fit="true" border="false">
                <div title="首页" style="padding:5px;overflow:hidden;text-align:center">
                  <h1> 欢迎试用本系统!</h1>
                </div>
            </div>      	
      	</div>
    </body>
</html>