<#global c=JspTaglibs["/WEB-INF/coolie-tags.tld"]/>  
<html>
	<head>
		<title>My JSP 'checkboxlist.jsp' starting page</title>
		<@c.head compressed="false" uiTheme="flick" locale="zh-CN" />
		<#assign user="I'm Sagaris!!!HAHA!">
		<script type="text/javascript">
			$(document).ready(function(){
				
			});
		</script>
	</head>
	<body>
		
		<html>
	<head>
	</head>

	<body>
		<@s.property value="title" />列表
		<br>
		<table border=".5">
			<tr>
				<td>
					序号
				</td>
				<td>
					名称
				</td>
				<td>
					上级角色
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
						<@s.a href="remove?dmo.id=%{#us.id}">删除</@s.a>
					</td>
					<td>
						<@s.a href="edit?dmo.id=%{#us.id}">更新</@s.a>
					</td>
				</tr>
			</@s.iterator>
		</table>
		<@s.a href="create">添加</@s.a>
	</body>
</html>

	</body>
</html>
