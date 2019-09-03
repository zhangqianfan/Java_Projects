<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="dao.*"%>
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

<title>Feedback</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<%
	String oidstr = request.getParameter("orderid");
	String cr = request.getParameter("exchanged");
	String sql = "UPDATE t_order SET exchanged = ? WHERE orderid = " + oidstr;
	Object[] o = {"已申请" + cr};
 %>
<body style="font-family:Microsoft YaHei">
	<%
		if (BaseDao.update(sql, o)) {
			out.print("您的" + cr + "申请已提交！感谢您对本图书系统的支持！我们将尽快处理。<br /><br />");
			out.print("<a href='java'>查看我的订单</a>&nbsp;&nbsp;");
			out.print("<a href='PagingServlet?curpg=1'>返回图书首页</a>");
		}
		else {
			out.print("操作失败。点此<a href='javascript:history.go(-1)'>返回上一页</a>以重试。");
		}
	 %>
</body>
</html>
