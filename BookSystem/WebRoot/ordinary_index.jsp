<%@page import="entity.Book"%>
<%@page import="dao.BookDao"%>
<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>普通用户界面</title>
</head>
<body>
		<h3>你好，欢迎用户 ${user.username },你的身份是${role},点击此处<a href="index.jsp">退出登陆</a></h3>
	<h3>欢迎来到图书商城！</h3>
	<b>下面是所有的图书信息</b>(点击书名即可查看书籍的详细信息)：<br /><br />
	<%
	BookDao bd = new BookDao();
	List<Book> booklist = bd.showAllBooks();
	out.print("<table align='center' bgcolor='#D2F0FA' border='1' cellspacing='0' cellpadding='0' width='700'>");
	out.print("<tr><td align='center'>图书编号</td>");
	out.print("<td align='center'>图书名称</td>");
	out.print("<td align='center'>库存</td>");
	out.print("<td align='center'>封面</td>");
	
	String curpage = request.getParameter("curpg");
	Integer curpg = Integer.parseInt(curpage);
	//分页显示所有图书信息，这里设置为每页显示5本书
	//a为一临时变量，用于存放booklist.size()和5*curpg的最小值，防止下面访问时发生数组越界
	//curpg为当前页
	Integer a = (5 * curpg >= booklist.size()) ? booklist.size() : 5 * curpg;
	for (int i = 5 * (curpg - 1); i < a; i++) {
		Book bk = booklist.get(i);
		out.print("<tr><td align='center'>"+bk.getId()+"</td>");
		out.print("<td align='center'> <a href='bookdetail.jsp?bookid="+bk.getId()+"'>"+bk.getTitle()+"</a></td>");
		out.print("<td align='center'>"+bk.getInventory()+"</td>");
		out.print("<td align='center'><img src='"+bk.getImgurl()+"'></td></tr>");
	}
	out.print("</table>");
	%>
	<br />
	<p align="center">
	<a href="FenYe.jsp?curpg=1">首页</a>&nbsp;
	<a href="FenYe.jsp?curpg=${(curpg==1)?curpg:curpg-1}">上页</a>&nbsp;
	当前为第${curpg}页&nbsp;
	<a href="FenYe.jsp?curpg=${(curpg==maxpage)?curpg:curpg+1}">下页</a>&nbsp;
	<a href="FenYe.jsp?curpg=${maxpage}">尾页</a>&nbsp;
	</p>
	<p align="center">
	<a href="cartdetail.jsp?userid=${user.userid}&username=${user.username}">我的购物车</a>&nbsp;
	<a href="myorder.jsp?userid=${user.userid}">我的订单</a>
	</p>
</body>
</html>
