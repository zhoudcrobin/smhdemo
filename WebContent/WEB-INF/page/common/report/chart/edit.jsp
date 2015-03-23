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
				</tr>
				<tr>
					<td>SQL表达式：</td>
					<td>
						<f:textarea path="chartSql" class="inputtext"/>
					</td>
				</tr>				
				<tr>
					<td>图表类型：</td>
					<td>
				        <f:select path="type">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${typeEnum}" itemValue="name" itemLabel="description"/>  
	        			</f:select> 
					</td>
				</tr>
				<tr>
					<td>工具提示：</td>
					<td>
						<f:checkbox  path="showTooltips"/>
					</td>
				</tr>
				<tr>
					<td>图表标题：</td>
					<td>
						<f:input path="chartTitle" class="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>图表标题字体：</td>
					<td>
				        <f:select path="fontName">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontNameMap}" />  
	        			</f:select> 
	        			<f:select path="fontStyle">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontStyleMap}" />  
	        			</f:select> 
	        			<f:select path="fontSize">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontSizeMap}" />  
	        			</f:select> 
					</td>
				</tr>
				<tr>
					<td>横坐标轴标题：</td>
					<td>
						<f:input path="horizAxisLabel" class="inputtext"/>
					</td>
				</tr>	
				<tr>
					<td>纵坐标轴标题：</td>
					<td>
						<f:input path="vertAxisLabel" class="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>数据字体：</td>
					<td>
				        <f:select path="dataFontName">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontNameMap}"/>  
	        			</f:select> 
	        			<f:select path="dataFontStyle">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontStyleMap}" />  
	        			</f:select> 
	        			<f:select path="dataFontSize">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontSizeMap}"/>  
	        			</f:select> 
					</td>
				</tr>	
				<tr>
					<td>坐标轴字体：</td>
					<td>
				        <f:select path="axisFontName">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontNameMap}"/>  
	        			</f:select> 
	        			<f:select path="axisFontStyle">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontStyleMap}" />  
	        			</f:select> 
	        			<f:select path="axisFontSize">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontSizeMap}" />  
	        			</f:select> 
					</td>
				</tr>	
				<tr>
					<td>坐标轴尺值字体：</td>
					<td>
				        <f:select path="axisTickFontName">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontNameMap}" />  
	        			</f:select> 
	        			<f:select path="axisTickFontStyle">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontStyleMap}" />  
	        			</f:select> 
	        			<f:select path="axisTickFontSize">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontSizeMap}" />  
	        			</f:select> 
					</td>
				</tr>	
				<tr>
					<td>数据轴标签角度：</td>
					<td>
				        <f:select path="tickLabelRotate">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${rotateMap}" />  
	        			</f:select> 
					</td>
				</tr>
				<tr>
					<td>图示说明：</td>
					<td>
						<f:checkbox  path="showLegend"/>
					</td>
				</tr>
				<tr>
					<td>图示位置：</td>
					<td>
				        <f:select path="legendPosition">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${positionMap}" />  
	        			</f:select> 
					</td>
				</tr>	
				<tr>
					<td>图示字体：</td>
					<td>
				        <f:select path="legendFontName">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontNameMap}" />  
	        			</f:select> 
	        			<f:select path="legendFontStyle">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontStyleMap}" />  
	        			</f:select> 
	        			<f:select path="legendFontSize">  
	            			<f:option value="-1" label="--Please Select--"/>  
	            			<f:options items="${fontSizeMap}" />  
	        			</f:select> 
					</td>
				</tr>
				<tr>
					<td>图表高度：</td>
					<td>
						<f:input path="chartHeight" class="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>图表宽度：</td>
					<td>
						<f:input path="chartWidth" class="inputtext"/>
					</td>
				</tr>	
				<tr>
					<td>RGB背景色：</td>
					<td>
						<f:input path="bgColorR" class="inputtext"/>
						<f:input path="bgColorG" class="inputtext"/>
						<f:input path="bgColorB" class="inputtext"/>
					</td>
				</tr>	
				<tr>
					<td>备注：</td>
					<td>
						<f:input path="remark" class="inputtext"/>
					</td>
				</tr>																																								
			</table>
			<f:hidden path="id"/>
			<input type="hidden" name="addrecordlist" value="${addrecordlist}"/>
			<input type="hidden" name="eventOP" value="${eventOP}"/>
		</f:form>
	</body>
</html>