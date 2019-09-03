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

<title>Welcome（Ordinary User）</title>

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
	<h3>您好，${user.username}！您的身份为普通用户。点此<a href="index.jsp">退出登录</a></h3>
	<h3>欢迎来到图书商城！</h3>
	下面是所有的图书信息：<br /><br />
	<%
	User u0 = (User)session.getAttribute("user");
	String uname = u0.getUsername();
	BookDao bd = new BookDao();
	List<Book> booklist = bd.showAllBooks();
	out.print("<table width='600px' align='center' bgcolor='#D2F0FA' border='1' cellspacing='0' cellpadding='0'>");
	out.print("<tr><td align='center'>图书编号</td>");
	out.print("<td align='center'>图书名称</td>");
	out.print("<td align='center'>价格</td>");
	out.print("<td align='center'>库存</td>");
	out.print("<td align='center'>封面</td>");
	out.print("<td align='center'>操作</td></tr>");
	String curpage = request.getParameter("curpg");
	Integer curpg = Integer.parseInt(curpage);
	//分页显示所有图书信息，这里设置为每页显示5本书
	//a为一临时变量，用于存放booklist.size()和5*curpg的最小值，防止下面访问时发生数组越界
	//curpg为当前页
	Integer a = (5 * curpg >= booklist.size()) ? booklist.size() : 5 * curpg;
	for (int i = 5 * (curpg - 1); i < a; i++) {
		Book bk = booklist.get(i);
		out.print("<tr><td align='center'>"+bk.getId()+"</td>");
		out.print("<td align='center'>"+bk.getTitle()+"</td>");
		out.print("<td align='center'>"+bk.getPrice()+"</td>");
		out.print("<td align='center'>"+bk.getInventory()+"</td>");
		out.print("<td align='center'><img src='"+bk.getImgurl()+"'></td>");
		out.print("<td align='center'>&nbsp;"
		+"<a href='bookdetail.jsp?bid="+bk.getId()+"&uname="+uname+"&inventory="+bk.getInventory()+"'>查看"+"</a>&nbsp;&nbsp;"
		+"<a href='addtocart.jsp?bookid="+bk.getId()+"&username="+uname+"'>加入购物车"+"</a>&nbsp;"+"</td></tr>");
		//注意：加入购物车未结算之前库存并未减少！
	}
	out.print("</table>");
	%>
	<br />
	<p align="center">
	<a href="PagingServlet?curpg=1">首页</a>&nbsp;
	<a href="PagingServlet?curpg=${(curpg==1)?curpg:curpg-1}">上页</a>&nbsp;
	当前为第${curpg}页&nbsp;
	<a href="PagingServlet?curpg=${(curpg==maxpage)?curpg:curpg+1}">下页</a>&nbsp;
	<a href="PagingServlet?curpg=${maxpage}">尾页</a>&nbsp;
	</p>
	<p align="center">
	<a href="cartdetail.jsp?userid=${user.userid}">我的购物车</a>&nbsp;
	<a href="myorder.jsp?userid=${user.userid}">我的订单</a>
	</p>
</body>
</html>
