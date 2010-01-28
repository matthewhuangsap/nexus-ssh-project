<html>
	<head>
	</head>

	<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
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
					上级部门
				</td>
				<td>
					摘要
				</td>
				<td colspan="2">
					操作
				</td>
			</tr>
			<@s.iterator value="lists" id="us">
				<tr <@s.if test="#st.odd">style="background-color:#bbbbbb"</@s.if>>
					<td>
						<@s.property value="#us.id" />
					</td>
					<td>
						<@s.property value="#us.name" />
					</td>
					<td>
						<@s.property value="#us.parent.name" />
						&nbsp
					</td>
					<td>
						<@s.property value="#us.remark" />
						&nbsp
					</td>

					<td>
						<@s.a href="remove?dept.id=%{#us.id}">删除</@s.a>
					</td>
					<td>
						<@s.a href="edit?dept.id=%{#us.id}">更新</@s.a>
					</td>
				</tr>
			</@s.iterator>
		</table>
		<@s.a href="create">添加</@s.a>
	</body>
</html>
