<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="dao.*"%>
<%@ page language="java" import="entity.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String oidstr = request.getParameter("orderid");
	OrderDao od = new OrderDao();
	boolean flag = od.cancelOrderById(Integer.parseInt(oidstr));
	String url1 = "PagingServlet?curpg=1";
	String url2 = "javascript:history.go(-1)";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Cancel Order</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="refresh" content="2;url=<%=flag?url1:url2%>">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body style="font-family:Microsoft YaHei">
	<%
		if (flag) {
			out.print("订单取消成功！2秒钟后将跳转至图书首页。<br><br>");
			out.print("如果您的浏览器没有自动跳转，请点击");
			out.print("<a href='" + url1 + "'>这里</a>。");
		} else {
			out.print("订单取消失败。2秒钟后将返回上一页。<br><br>");
			out.print("如果您的浏览器没有自动跳转，请点击");
			out.print("<a href='" + url2 + "'>这里</a>，重新操作。");
		}
	%>
</body>
</html>
