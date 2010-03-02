<#global c=JspTaglibs["/WEB-INF/coolie-tags.tld"]/>
<html>
	<head>
<style tpye="text/css">
	body{
		FONT-SIZE:14px;
		color:#333;
		FONT-FAMILY: 'Lucida Grande','Lucida Sans Unicode','宋体','新宋体',arial,verdana,sans-serif;
		LINE-HEIGHT:150%;
		padding:0;
		border:0;
		margin:0;
	}
	#left{
		background:url(../images/leftbg.jpg) repeat-y left top;
		float:left;
		height:100%;
		width:134px;
		margin:0px;
	}
</style>
		<title>My JSP 'checkboxlist.jsp' starting page</title>
		<@c.head compressed="false" uiTheme="flick" locale="zh-CN" />
		<#assign user="I'm Sagaris!!!HAHA!">
		<script type="text/javascript">
			$(document).ready(function(){
				$('#tab_container').tabs();
			});
		</script>
	</head>
	<body>
		<@s.form action="save" theme="simple">
		    <div><h4>基础信息</h4></div>
			<#include "/components/basetree.ftl" /><#nt/>
			<#assign itemCount = 0/>
			<@s.textfield name= "dmo.name" label="角色"/>
			<@c.choicebox id="role_parent" action="../data/datakind"
				name="dmo.temparent" label="上级角色" datakind="角色"
				displayValue="dmo.parent.name" value="dmo.parent.id" />
			<tr><th align="left" colspan="6">配置权限</th></tr>
		        <@s.set var="checkedValue" value="valueList" />
		        <div id="tab_container">
		        	<ul>
		        		<@s.iterator value="plugin_info" id="pi">
			        		<li><a href="#<@s.property value='key'/>"><span><@s.property value='value'/></span></a></li>
			        	</@s.iterator>
		            </ul>
		        	<@s.iterator value="access_group_map" status="it" id="us">
		        		<div id="<@s.property value='key'/>">
		        		<table>
		                	<@s.iterator value="value" status="st">		 
		                	<tr>
			                	<td align="right"><font color="red" ><@s.property value='key'/></font>
			                		<@s.hidden name="keyList" value="%{key}"></@s.hidden>
			                	</td>
			                	<td><@s.checkboxlist  name="valueList[${itemCount}]"
										list="%{value}">
										<@s.param name="checked" value="#checkedValue[${itemCount}]"/>
									</@s.checkboxlist>
									<@s.property value="checkedValue[${itemCount}]"/>
								</td>
							</tr>
								<#assign itemCount = itemCount + 1/>
		                	</@s.iterator>
		                </table>
		            	</div>
		        	</@s.iterator>
		        </div>
		        <@s.submit value="保存"/>
		</@s.form>
	</body>
</html>
