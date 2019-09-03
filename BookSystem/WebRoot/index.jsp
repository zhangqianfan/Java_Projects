<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>图书系统</title>
</head>
<body style="background-image: url('img/u=4147206523,1119900303&fm=21&gp=0.jpg');font-family:Microsoft YaHei">


<h2 align="center"><span style="color: white">在线小书城</span></h2>
	<form action="LoginServlet" method="post">
		<table align="center" width="500" height="500" border="0" cellpadding="1"  >
			<tr>
				<td><div align="right"><span style="color: white">用户名：</span></div></td>
				<td><div align="left">
						<input type="text" name="username" style="width:300" >
					</div></td>
			</tr>
			<tr>
				<td><div align="right"><span style="color: white">密码：</span></div></td>
				<td><div align="left">
						<input type="password" name="password" style="width:300">
					</div></td>
			</tr>
			<tr>
				<td><div align="right"><span style="color: white">角色：</span></div></td>
				<td><div align="left">
						<input type="radio" name="rolename" value="superM" /><span style="color: white">高级管理员</span> 
						<input type="radio" name="rolename" value="manager" /><span style="color: white">图书管理员</span>
						 <input type="radio" name="rolename" value="ordinary_user" /><span style="color: white">普通用户</span>
					</div></td>
			</tr>
			<tr>
				<td colspan="2"><div align="center">
						<input type="submit" value="登录"> <input type="reset"
							value="清空">
					</div></td>
			</tr>
		</table>
	</form>
</body>
</html>
