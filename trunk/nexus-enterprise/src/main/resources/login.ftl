<#ftl attributes={"content_type":"text/html; charset=UTF-8"}>  
<html>
	<title>登录页面</title>

	<head>
		<script type="text/javascript">
			function Redirect() 
			{ 
			    if(top.location.href!=location.href) 
					top.location.href=location.href; 
			} 
			
		</script>
	</head>

	<body onload="Redirect();">
		<div align="center">
			<div align="center">
				<h2>
					<font color="blue">登录页面</font>
				</h2>
			</div>
			<div>
				<form action="login" method="post">
					<table>
						<tr>
							<td align="right">
								用户名：
							</td>
							<td>
								<input name="dmo.username" type="text" width="90" />
							</td>
						</tr>
						<tr>
							<td align="right">
								密码：
							</td>
							<td>
								<input name="dmo.password" type="password" width="100" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="submit" value="登录" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>