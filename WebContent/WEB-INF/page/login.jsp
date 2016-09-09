<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!-- 跳出Iframe，显示登录界面 -->
<script type="text/javascript">
        if(parent != self) {
            top.location='<c:url value="/login.do"/>';
        }
</script>
<html>
    <head>
        <title>sshdemo--用户登录</title>
        <script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.min.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.easyui.min.js"/>'></script>
        <link rel="stylesheet" type="text/css" href='<c:url value="/comresource/css/login.css"/>'></link>
        <script type="text/javascript">
		$(function() {
		    $('#id_checkcode').click(function() {
		        this.src = '<c:url value="/captcha-image.do"/>' + '?nocache=' + Math.random();
		        $('input[name=checkCode]').val("").focus();
		    });
		    $('input[name=checkCode]').keypress(function(event) {
		        if (event.which == '13') {
		        	document.forms[0].submit();
		        }
		    });
		    $('input[name=accountName]').keypress(function(event) {
		        if (event.which == '13') {
		            $('input[name=password]').focus();
		        }
		    });
		    $('input[name=password]').keypress(function(event) {
		        if (event.which == '13') {
		            $('input[name=checkCode]').focus();
		        }
		    });
    	});
</script>
    </head>
    <body id="userlogin_body">
   	<div></div>
		<div id="user_login">
			<dl>
		  		<dd id="user_top">
			  		<ul>
			    		<li class="user_top_l"></li>
			    		<li class="user_top_c"></li>
			    		<li class="user_top_r"></li>
			    	</ul>
		    	</dd>
		    	<f:form action="login.do" method="post">
		  		<dd id="user_main">
			  		<ul>
			    		<li class="user_main_l"></li>
			    		<li class="user_main_c">
			    			<div class="user_main_box">
			    				<ul>
			      					<li class="user_main_text">用户名：</li>
			      					<li class="user_main_input"><input class="txtusernamecssclass" type="text" name="accountName" /></li>
			      				</ul>
			    				<ul>
			      					<li class="user_main_text">密&nbsp;&nbsp;&nbsp;&nbsp;码：</li>
			      					<li class="user_main_input"><input class="txtpasswordcssclass" type="password" name="password"/></li>
			      				</ul>
			    				<ul>
			      					<li class="user_main_text">验证码：</li>
			      					<li class="user_main_input">
                            			<input class="txtvalidatecodecssclass" type="text" name="checkCode"/>
			        				</li>
			        				<li><img id="id_checkcode"  src='captcha-image.do' alt="checkcode.jpg" title="看不清,换一张" style="padding-left: 5px;margin-bottom: 3px" /></li>
			        			</ul>
			    				<ul>
			      					<li class="user_main_text">记&nbsp;&nbsp;&nbsp;&nbsp;住：</li>
			      					<li class="user_main_input">
			      						<input type="checkbox" name="_spring_security_remember_me" id="_spring_security_remember_me" value="false"/>
			        				</li>
			        			</ul>
			        			<ul>
									<li class="user_main_input">
			                            <span class="error">
											${message}
			                            </span>			 
	                            	</li>       			
			        			</ul>
			        		</div>
			        	</li>
			    		<li class="user_main_r">
			    			<img class="ibtnentercssclass" id="ibtnenter" style="cursor:pointer; 0px; border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px"  src='<c:url value="/comresource/image/login/user_botton.gif"/>' name="ibtnenter" onclick="document.forms[0].submit();"/> 
			    		</li>
			    	</ul>
		    	</dd>
		    	</f:form>
			    <dd id="user_bottom">
				  	<ul>
				    	<li class="user_bottom_l"></li>
				    	<li class="user_bottom_c" style="text-align: center;"><!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://218.65.14.14/user.doc"><font>工程领域操作手册下载</font></a> --></li>
					    <li class="user_bottom_r"></li>
					</ul>
				</dd>
			</dl>
		</div>
     </body>
</html>