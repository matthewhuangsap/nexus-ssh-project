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
		background:url() repeat-y left top;
		float:left;
		height:100%;
		width:134px;
		margin:0px;
	}
</style>
	<@c.head compressed="false" uiTheme="flick" locale="zh-CN" />
	<script type="text/javascript">
	$(document).ready(function(){
		$("#menu").treeview();
	});
	</script>
</head>
<body>
<div id="left" width="300px">
${output}
</div>
<div id="left1">
</div>
</body>
</html>
