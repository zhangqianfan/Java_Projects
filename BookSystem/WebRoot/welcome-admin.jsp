<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Welcome（Administrator）</title>
<%
	session.setAttribute("username", request.getAttribute("username"));
	String name = (String) session.getAttribute("username");
	if (name == null) {
		response.sendRedirect("index.jsp");
	}
%>
</head>
<body style="font-family:Microsoft YaHei">
	<h3>
		您好，${user.username}！您的身份为图书管理员。点此<a href="index.jsp">退出登录</a>
	</h3>
	请选择以下操作：
	<br />
	<p style="font-size:16px;color:red">
		<a href="addbook.jsp">增加图书</a>&nbsp;&nbsp;&nbsp;
		<a href="deletebook.jsp">删除图书</a>&nbsp;&nbsp;&nbsp;
		<a href="modifybook1.jsp">修改图书</a>&nbsp;&nbsp;&nbsp;
		<a href="FenYeServlet?currpage=1">查询图书</a>
	</p>
</body>
</html>
