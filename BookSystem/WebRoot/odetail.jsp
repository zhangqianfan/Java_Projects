<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<%@ page import="dao.*"%>
<%@ page import="entity.*"%>
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

<title>Order Detail</title>

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
		OrderDao od = new OrderDao();
		Order or = od.getOrderById(Integer.parseInt(oid));
		Double tp = or.getTotalpr();
	 %>
	<h3 align="center">订单号：<%=oid%></h3>
	<table align="center" bgcolor="#D2F0FA" border="1" cellspacing="0" cellpadding="0">
	<tr><td align="center">序号</td>
	<td align="center">图书ID</td>
	<td align="center">图书名称</td>
	<td align="center">图书数量</td>
	<td align="center">图书总价</td></tr>
	<%
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = BaseDao.getConn();
		try {
			String sql = "SELECT * FROM t_odetail WHERE orderid = " + oid;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			int item = 1;
			while (rs.next()) {
				CartItem c = new CartItem();
				c.setBookId(rs.getInt("bookid"));
				c.setBookName(rs.getString("bookname"));
				c.setBookQty(rs.getInt("bookqty"));
				c.setBookPrice(rs.getDouble("bookpr"));
				out.print("<tr><td align='center'>" + item + "</td>");
				out.print("<td align='center'>" + c.getBookId() + "</td>");
				out.print("<td align='center'>" + c.getBookName() + "</td>");
				out.print("<td align='center'>" + c.getBookQty() + "</td>");
				out.print("<td align='center'>" + c.getBookPrice() + "</td></tr>");
				item++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
	%>
	</table>
	<br />
	<table align="center" style="text-align:left;">
	<tr><td>
	订单总金额：<%=tp%>元<br>
	实付金额：<%=or.getPaid()%>元<br>
	支付方式：<%=or.getPaymethod()%><br>
	下单时间：<%=or.getOrdertime()%><br>
	收件人姓名：<%=or.getReceivername()%><br>
	收件人地址：<%=or.getAddress()%><br>
	收件人电话：<%=or.getPhone()%><br>
	订单状态：<%=or.getStatus()%><br>
	物流信息：<%=or.getLogistics()%><br>
	退换货信息：<%=or.getExchanged()%><br></td></tr></table><br />
	<div align="center"><a href="PagingServlet?curpg=1">返回图书首页</a>&nbsp;&nbsp;&nbsp;
	<a href="index.jsp" onclick="index.jsp">退出登录</a></div>
</body>
</html>
