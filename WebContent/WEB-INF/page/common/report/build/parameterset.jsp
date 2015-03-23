<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
	<head>
		<title>报表参数输入</title>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.min.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.easyui.min.js"/>'></script>
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/default/easyui.css"/>' title="default">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/css/pagebase.css"/>' title="default">
		<script type="text/javascript">
			function checkBoxValue(name){
				var strValue = '';
				var list = document.getElementsByName(name);
				for (var i = 0; i < list.length; i++){
					if (list[i].type == 'checkbox'){
						if (list[i].checked == true) {
							listValue = list[i].value;
							if(strValue != '')strValue += ',';
							if (isNumber(listValue)){
								strValue += listValue;
							}
							else{
								strValue += "'" + listValue + "'";
							}
						}
					}
				}
				obName = "paraMap['" + name + "']";
				document.all[obName].value = strValue;
			}
			
			function isNumber(str){
			  var patrn=/^\d*$/;    
			  if(patrn.test(str))   {  
			  	return true;    
			  }else{  
			  	return false;  
			  }   
			}
		</script>	
	</head>
	<body >
		<f:form action="make.do" method="post" target="_blank">
			<table>
				<tr>
					<td>
						<table width="100%">
							<c:forEach items="${parameters}" var="parameter" varStatus="vs">
							 <tr>
								 <td>
									<c:choose>
									   <c:when test="${not empty parameter.cnName}">
									   		<c:out value="${parameter.cnName}"/>
									   </c:when>  
									   <c:otherwise>
									   		<c:out value="${parameter.enName}"/>
									   </c:otherwise> 
									</c:choose>							 
								 </td>
							 	 <td>
							 	 <c:if test="${parameter.type.name=='TEXT'}">
									<input type="text" name="paraMap['${parameter.enName}']" value="${parameter.defaultValue}">
								</c:if>
								<c:if test="${parameter.type.name=='BOOLEAN'}">
									<input type="checkbox" name="paraMap['${parameter.enName}']" value="${parameter.defaultValue}">
								</c:if>
								<c:if test="${parameter.type.name=='LIST'}">
									<select name="paraMap['${parameter.enName}']">
										<option value="">--Please Select--</option>
										<c:forEach items="${parameter.value}" var="item">
											<option value="${item.key}">${item.value}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${parameter.type.name=='CHECK'}">
									<c:forEach items="${parameter.value}" var="item">
										<input type="checkbox" name="paraMap['${parameter.enName}']" value="${item.key}"  onclick="checkBoxValue('${parameter.enName}');"><c:out value="${item.value}" />
									</c:forEach>
								</c:if>
							 	 <c:if test="${parameter.type.name=='DATE'}">
									<input type="text" name="paraMap['${parameter.enName}']">
								</c:if>	
							 	 <c:if test="${parameter.type.name=='SESSION'}">
									<input type="text" name="paraMap['${parameter.enName}']" value="<shiro:principal/>">
								</c:if>	
							 	 <c:if test="${parameter.type.name=='SQL'}">
									<input type="text" name="paraMap['${parameter.enName}']" value="${parameter.defaultValue}">
								</c:if>																																
							 	 </td>
							 </tr>
							</c:forEach>	
							<c:if test="${reportType=='text'}">
							<tr><td>报表文件类型：</td>
							<td>
									<select name="textType">
										<c:forEach items="${typeEnum}" var="type">
											<option value="${type.name}">${type.description}</option>
										</c:forEach>
									</select>
								</td></tr>
							</c:if>					
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" name="reportType" value="${reportType}"/>
			<input type="hidden" name="reportId" value="${reportId}"/>
		</f:form>
	</body>
</html>