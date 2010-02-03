<#global c=JspTaglibs["/WEB-INF/coolie-tags.tld"]/> 
<html>
	<head>
		<style tpye="text/css">
			body{
				FONT-SIZE:14px;
				color:#333;
				FONT-FAMILY: 'Lucida Grande','Lucida Sans Unicode','����','������',arial,verdana,sans-serif;
				LINE-HEIGHT:150%;
				padding:0;
				border:0;
				margin:0;
			}
		</style>
		<@c.head compressed="false" uiTheme="flick" locale="zh-CN" />
		
	</head>
	<body>      
		<!-- Add Button Group -->
		<#include "/components/buttongroup.ftl" />
		<form id="myform" action="save">
			<input name="dmo.id" type="hidden" value="1"/>
		</form>
	</body>
</html>
