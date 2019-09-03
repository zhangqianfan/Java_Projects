<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="dao.*"%>
<%@ page language="java" import="entity.*"%>
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

<title>My JSP 'exchanged.jsp' starting page</title>

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
		String oid = request.getParameter("orderid");
	 %>
	 我们对图书引起的质量问题深感抱歉。<br /><br />
	 请选择退货或换货：<br /><br />
	 <form action="feedback.jsp?orderid=<%=oid%>" method="post">
	 <input type="radio" name="exchanged" value="退货">退货
	 <input type="radio" name="exchanged" value="换货">换货<br /><br />
	 <input type="submit" value="提交申请">
	 </form>
	 <br />
	 <a href="javascript:history.go(-1)">返回上一页</a>
</body>
</html>
