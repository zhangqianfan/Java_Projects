<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>OK</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="refresh" content="2;url=PagingServlet?curpg=1">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body style="font-family:Microsoft YaHei">
	操作成功！2秒钟后将跳转至图书首页。<br><br>
	如果您的浏览器没有自动跳转，请点击
	<a href="PagingServlet?curpg=1">这里</a>。
</body>
</html>
