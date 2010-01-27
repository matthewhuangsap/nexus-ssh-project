<#global c=JspTaglibs["/WEB-INF/coolie-tags.tld"]/> 
<html>
	<head>
		<title>Edit User Page</title>
		<@c.head compressed="false" uiTheme="flick" locale="zh-CN" />

	</head>
	
	<body topmargin="0">
		<#include "/it/coolie/template/freemarker/buttongroup.ftl" />
		<hr>
		<@c.title id="dept_id" dmoID="dmo.id" name="title"></@c.title>
		<@s.form action="save">
			<@s.hidden name="dmo.id"></@s.hidden>
			<@s.hidden name="dmo.version"></@s.hidden>
			<@s.hidden name="dmo.level"></@s.hidden>
			<@s.hidden name="dmo.spell"></@s.hidden>
			<@s.hidden name="dmo.available"></@s.hidden>
			<@s.hidden name="dmo.isLock"></@s.hidden>
			<@s.hidden name="dmo.lockReason"></@s.hidden>
			<@s.hidden name="dmo.createDate"></@s.hidden>
			<@s.hidden name="dmo.modifyDate"></@s.hidden>
			<@s.textfield name="dmo.name" label="名称"></@s.textfield>
			<@s.textfield name="dmo.remark" label="摘要"></@s.textfield>
			<@c.choicebox id="dept_parent" action="../../data/datakind"
				name="dmo.parent" label="上级部门" datakind="部门"
				displayValue="dmo.parent.name" value="dmo.parent.id" />
			<@s.submit/>
		</@s.form>
	</body>
</html>
