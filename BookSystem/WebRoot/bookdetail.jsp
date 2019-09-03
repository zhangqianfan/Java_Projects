<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="entity.*"%>
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

<title>Book Details</title>

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
	<h3>图书信息：</h3>
	<%
		String uname = request.getParameter("uname");
		String idstr = request.getParameter("bid");
		BookDao bd = new BookDao();
		Book book = bd.getBookById(Integer.parseInt(idstr));
		request.setAttribute("book", book);
	 %>
	编号：${book.id}<br />
	书名：${book.title}<br />
	作者：${book.author}<br />
	出版日期：${book.publishDate}<br />
	价格：￥${book.price}<br />
	库存数量：${book.inventory}本<br />
	封面：<br />
	<img src="${book.imgurl}"><br /><br />
	<a href="addtocart.jsp?bookid=${book.id}&username=<%=uname%>">加入购物车</a>&nbsp;&nbsp;
	<a href="javascript:history.go(-1)">返回图书列表</a>
</body>
</html>
