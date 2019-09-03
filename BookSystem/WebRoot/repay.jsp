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

<title>Repay</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body style="font-family:Microsoft YaHei">
	<%
		String pricestr = request.getParameter("price");
		String oidstr = request.getParameter("orderid");
		out.print("<h3>订单总金额：" + pricestr + "元</h3>");
		out.print("请选择支付方式：<br /><br />");
		out.print("<form action='dopay.jsp?price=" + pricestr + "' method='post'>");
		out.print("<input type='radio' name='paymethod' value='微信支付'>微信支付");
		out.print("<input type='radio' name='paymethod' value='支付宝支付'>支付宝支付");
		out.print("<input type='radio' name='paymethod' value='网银支付'>网银支付<br /><br />");
		out.print("<input type='hidden' name='orderid' value='" + oidstr + "'>");
		out.print("<input type='submit' value='继续'></form>");
		out.print("<br />注：在线支付单笔订单不足150元需支付10元运费。<br /><br />");
	%>
	<a href="PagingServlet?curpg=1">返回图书首页</a>
</body>
</html>
