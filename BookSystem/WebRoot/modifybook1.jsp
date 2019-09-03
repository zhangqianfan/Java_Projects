<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>更新图书</title>
</head>
<body style="font-family:Microsoft YaHei">
	<%
		String name = (String) session.getAttribute("username");
		if (name == null) {
			response.sendRedirect("index.jsp");
		}
	%>

	<h3>更新图书信息</h3>
	请输入要更新图书的ID：
	<form action="updatedo.jsp" method="post">
		<input type="text" name="bookid"><br />
		<br /> <input type="submit" value="确定">&nbsp;&nbsp;&nbsp; <input
			type="reset" value="清除"> <a href="javascript:history.go(-1)">返回上一页</a>
	</form>
</body>
</html>