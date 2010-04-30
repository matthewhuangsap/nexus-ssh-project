<#global c=JspTaglibs["/WEB-INF/coolie-tags.tld"]/> 
<html>
	<head>
		<title>Edit User Page</title>		
		<@c.head compressed="false" uiTheme="flick" locale="zh-CN" />
	</head>

	<body topmargin="0" onkeydown="if(event.keyCode==13){return false;}">

		<@s.form action="save" theme="nexus">

			<@s.hidden name="dmo.id"></@s.hidden>
			<@s.hidden name="dmo.spell"></@s.hidden>
			<@s.hidden name="dmo.stoped"></@s.hidden>
			<@s.hidden name="dmo.version"></@s.hidden>

			<@s.textfield name="dmo.username" label="名称">
				<@s.param name="labelcolspan" value="%{1}" />
				<@s.param name="inputcolspan" value="%{1}" />
			</@s.textfield>
			<@s.password name="dmo.password" label="密码">
				<@s.param name="labelcolspan" value="%{1}" />
				<@s.param name="inputcolspan" value="%{1}" />
			</@s.password>
			<@s.password name="reinput" label="确认密码">
				<@s.param name="labelcolspan" value="%{1}" />
				<@s.param name="inputcolspan" value="%{1}" />
			</@s.password>
			
			<@c.choicebox id="user_role" action="../../data/datakind"
				name="dmo.role" label="角色" datakind="角色"
				displayValue="dmo.role.name" value="dmo.role.id" />
				
			<@s.textfield name="dmo.remark" label="摘要">
				<@s.param name="labelcolspan" value="%{1}" />
				<@s.param name="inputcolspan" value="%{1}" />
			</@s.textfield>
			<@s.checkbox name="dmo.isAdmin" label="系统管理员">
				<@s.param name="labelcolspan" value="%{1}" />
				<@s.param name="inputcolspan" value="%{1}" />
			</@s.checkbox>
			<@s.submit value="提交">
				<@s.param name="colspan" value="%{4}" />
				<@s.param name="align" value="%{'center'}" />
			</@s.submit>
		</@s.form>
	</body>
</html>