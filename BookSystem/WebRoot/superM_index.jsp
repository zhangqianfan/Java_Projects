<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>超级管理员界面</title>
</head>
<body style="font-family:Microsoft YaHei">
	 <h3>你好，欢迎用户： ${username },你的身份是：${role},点击此处<a href="index.jsp">退出登陆</a></h3>
	<h2>请选择你要进行的操作：</h2>
	<h4>管理用户：</h4>
	<ul>
		<li><a href="insertUser.jsp">新增使用用户</a></li>
		</br>
		<li><a href="deleteUser.jsp">删除用户信息</a></li>
		</br>
		<li><a href="updateUserInfo1.jsp">修改用户信息</a></li>
		</br>
		<li><a href="SelectUser?currpage=1">查询用户信息</a></li>
		</br>
	</ul>
	<h4>管理图书：</h4>
	<ul>
		<li><a href="addbook.jsp">新增图书信息</a></li>
		</br>
		<li><a href="deletebook.jsp">删除图书信息</a></li>
		</br>
		<li><a href="updatebook1.jsp">修改图书信息</a></li>
		</br>
		<li><a href="FenYeServlet?currpage=1">查询图书信息</a></li>
		</br>
	</ul>
</body>
</html>
