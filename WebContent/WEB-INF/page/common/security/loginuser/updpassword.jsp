<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
    <head>
        <title>修改密码</title>
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/default/easyui.css"/>' title="default">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/css/pagebase.css"/>' title="default">
    </head>
    <body>
        <f:form action="updpassword.do">
             <table class="formtable">
                 <tr>
                     <td class='title'>现有密码:</td>
                     <td>
                         <table class="table_none" border="0" cellpadding="2" cellspacing="0" >
                             <tr>
                                 <td class="input_t"><input type="password" name="oldPassword" class="input"/></td>
                                 <td></td>
                             </tr>
                         </table>
                     </td>
                </tr>
                <tr>
                    <td class='title'>新密码:</td>
                    <td>
                         <table class="table_none" border="0" cellpadding="2" cellspacing="0">
                             <tr>
                                 <td class="input_t"><input type="password" name="newPassword" class="input"/></td>
                                 <td></td>
                             </tr>
                             <tr>
                                 <td class='password-help' colspan="2">6-16位字符组成的字符串</td>
                             </tr>
                         </table>
                    </td>
                </tr>
                <tr>
                    <td class='title'>确认密码:</td>
                    <td>
                        <table class="table_none" border="0" cellpadding="2" cellspacing="0">
                             <tr>
                                 <td class="input_t"><input type="password" name="againPassword" class="input"/></td>
                                 <td></td>
                             </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                    	<span class="error">
							${message}
			            </span>	
                    </td>
                </tr>
            </table>
        </f:form>
    </body>
</html>