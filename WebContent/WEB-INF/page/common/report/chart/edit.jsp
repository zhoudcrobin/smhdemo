<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<html>
	<head>
		<title>图形报表信息编辑</title>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.min.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/comresource/easyui/jquery.easyui.min.js"/>'></script>
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/easyui/themes/default/easyui.css"/>' title="default">
		<link rel="stylesheet" type="text/css" href='<c:url value="/comresource/css/pagebase.css"/>' title="default">
		<script type="text/javascript">
			 $(function(){
				if(document.all.eventOP.value=='add'){
					var recordList = document.all.addrecordlist.value;
					if(recordList!='')
					parent.queryAddRecordList(recordList.split(","));
					
				}else if(document.all.eventOP.value=='upd'){
					if(document.all.id.value==''){
						parent.queryReload();
						parent.closeWindow('#edit-window');
					}
				}
			 });
		</script>
	</head>
	<body >
		<f:form action="save.do" method="post" commandName="chart">
			<table>
				<tr>
					<td>报表名称：</td>
					<td>
						<f:input path="name" class="inputtext"/>
					</td>
					<td>图表标题：</td>
					<td>
						<f:input path="chartTitle" class="inputtext"/>
					</td>					
				</tr>
				<tr>
					<td>SQL表达式：</td>
					<td colspan=3>
						<f:textarea path="chartSql"  cols="70" rows="4"/>
					</td>
				</tr>				
				<tr>
					<td>图表类型：</td>
					<td>
				        <f:select path="type">   
	            			<f:options items="${typeEnum}" itemValue="name" itemLabel="description"/>  
	        			</f:select> 
					<td>报表数据源：</td>
					<td>
				        <f:select path="baseDS.id">  
	            			<f:option value="-1" label="默认数据源"/>  
	            			<f:options items="${baseDSList}" itemValue="id" itemLabel="name"/>  
	        			</f:select> 
					</td>
				</tr>
				<tr>
					<td>横坐标轴标题：</td>
					<td>
						<f:input path="horizAxisLabel" class="inputtext"/>
					</td>
					<td>纵坐标轴标题：</td>
					<td>
						<f:input path="vertAxisLabel" class="inputtext"/>
					</td>					
				</tr>	
				<tr>
					<td>图表标题字体：</td>
					<td>
				        <f:select path="fontName">  
	            			<f:options items="${fontNameMap}" />  
	        			</f:select> 
	        			<f:select path="fontStyle">    
	            			<f:options items="${fontStyleMap}" />  
	        			</f:select> 
	        			<f:select path="fontSize">   
	            			<f:options items="${fontSizeMap}" />  
	        			</f:select> 
					</td>					
					<td>数据字体：</td>
					<td>
				        <f:select path="dataFontName">  
	            			<f:options items="${fontNameMap}"/>  
	        			</f:select> 
	        			<f:select path="dataFontStyle">  
	            			<f:options items="${fontStyleMap}" />  
	        			</f:select> 
	        			<f:select path="dataFontSize">  
	            			<f:options items="${fontSizeMap}"/>  
	        			</f:select> 
					</td>
				</tr>	
				<tr>
					<td>坐标轴字体：</td>
					<td>
				        <f:select path="axisFontName">  
	            			<f:options items="${fontNameMap}"/>  
	        			</f:select> 
	        			<f:select path="axisFontStyle">  
	            			<f:options items="${fontStyleMap}" />  
	        			</f:select> 
	        			<f:select path="axisFontSize">  
	            			<f:options items="${fontSizeMap}" />  
	        			</f:select> 
					</td>
					<td>坐标轴尺值字体：</td>
					<td>
				        <f:select path="axisTickFontName">  
	            			<f:options items="${fontNameMap}" />  
	        			</f:select> 
	        			<f:select path="axisTickFontStyle">  
	            			<f:options items="${fontStyleMap}" />  
	        			</f:select> 
	        			<f:select path="axisTickFontSize">  
	            			<f:options items="${fontSizeMap}" />  
	        			</f:select> 
					</td>
				</tr>	
				<tr>
					<td>数据轴标签角度：</td>
					<td>
				        <f:select path="tickLabelRotate">  
	            			<f:options items="${rotateMap}" />  
	        			</f:select> 
					</td>
					<td>图示说明：</td>
					<td>
						<f:checkbox  path="showLegend"/>
					</td>
				</tr>
				<tr>
					<td>图示位置：</td>
					<td>
				        <f:select path="legendPosition">  
	            			<f:options items="${positionMap}" />  
	        			</f:select> 
					</td>
					<td>图示字体：</td>
					<td>
				        <f:select path="legendFontName">  
	            			<f:options items="${fontNameMap}" />  
	        			</f:select> 
	        			<f:select path="legendFontStyle">  
	            			<f:options items="${fontStyleMap}" />  
	        			</f:select> 
	        			<f:select path="legendFontSize">  
	            			<f:options items="${fontSizeMap}" />  
	        			</f:select> 
					</td>
				</tr>
				<tr>
					<td>图表高度：</td>
					<td>
						<f:input path="chartHeight" class="inputtext"/>
					</td>
					<td>图表宽度：</td>
					<td>
						<f:input path="chartWidth" class="inputtext"/>
					</td>
				</tr>	
				<tr>
					<td>备注：</td>
					<td>
						<f:input path="remark" class="inputtext"/>
					</td>
					<td>工具提示：</td>
					<td>
						<f:checkbox  path="showTooltips"/>
					</td>					
				</tr>	
				<tr>
					<td>RGB背景色：</td>
					<td>
						<f:input path="bgColorR" class="inputtext"  size="4"/>&nbsp;
						<f:input path="bgColorG" class="inputtext"   size="4"/>&nbsp;
						<f:input path="bgColorB" class="inputtext"   size="4"/>
					</td>
					<td>

					</td>
					<td>

					</td>					
				</tr>																																											
			</table>
			<f:hidden path="id"/>
			<input type="hidden" name="addrecordlist" value="${addrecordlist}"/>
			<input type="hidden" name="eventOP" value="${eventOP}"/>
		</f:form>
	</body>
</html>