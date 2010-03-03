<#global c=JspTaglibs["/WEB-INF/coolie-tags.tld"]/> 
<html>
	<head>
		<title>Edit User Page</title>
		<@c.head compressed="false" uiTheme="flick" locale="zh-CN" />

	</head>
	
	<body topmargin="0">
        <@s.property name="error"/>
		<#include "/components/buttongroup.ftl" />
		<hr>
		<@s.bean name="java.util.HashMap" id="qTableLayout">
			<@s.param name="tablecolspan" value="%{2}" />
		</@s.bean>
		<@s.form action="save">
			<#include "/components/basetree.ftl" /><#nt/>
            <@s.hidden name="dmo.time"/>
			<@s.textfield name="dmo.name" label="名称"></@s.textfield>
			<@s.textfield name="dmo.remark" label="摘要"></@s.textfield>
			<@c.choicebox id="dept_parent" action="../../data/datakind"
				name="dmo.temparent" label="上级部门" datakind="部门"
				displayValue="dmo.parent.name" value="dmo.parent.id" />
			<@s.submit/>
		</@s.form>
	</body>
</html>
