<html>
	<head>
	</head>

	<body>
		<s:property value="title" />列表
		<br>
		<!-- 
		<s:a href="list">未审核</s:a>
		<s:a href="list">已审核</s:a>
		<s:a href="list">已完毕</s:a>
		<s:a href="list">已作废</s:a>
		 -->
		<table border=".5">
			<tr>
				<td>
					序号
				</td>
				<td>
					名称
				</td>
				<td>
					摘要
				</td>
				<td colspan="2">
					操作
				</td>
			</tr>
			<@s.iterator value="lists" id="us">
				<tr>
					<td>
						<@s.property value="#us.id" />
					</td>
					<td>
						<@s.property value="#us.username" />
					</td>
					<td>
						<@s.property value="#us.remark" />
						&nbsp
					</td>

					<td>
						<@s.a href="remove?dmo.id=%{#us.id}">删除用户</@s.a>
					</td>
					<td>
						<@s.a href="edit?dmo.id=%{#us.id}">修改密码</@s.a>
					</td>
				</tr>
			</@s.iterator>
		</table>
		<@s.a href="create">添加</@s.a>
	</body>
</html>