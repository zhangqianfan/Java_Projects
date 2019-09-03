<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Delete Book</title>
</head>
<body style="font-family:Microsoft YaHei">
	<h3>删除图书</h3>
	请输入要删除图书的ID：<br />
	<form action="dodeletebook.jsp" method="post">
		<input type="text" name="bookid"><br /><br />
		<input type="submit" value="确定">&nbsp;&nbsp;&nbsp;
		<input type="reset" value="清除"><br /><br />
		<a href="javascript:history.go(-1)">返回上一页</a>
	</form>
</body>
</html>
