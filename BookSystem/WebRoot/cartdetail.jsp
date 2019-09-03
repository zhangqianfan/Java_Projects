<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
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

<title>Cart Detail</title>

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
		String uidstr = request.getParameter("userid");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = BaseDao.getConn();
		String uname = null;
		try {
			String sql = "SELECT * FROM t_user WHERE userid=" + uidstr;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				uname = rs.getString("username");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		out.print("<h3>用户"+uname+"的购物车明细：</h3>");
		//查询购物车记录时直接查表t_cart
		conn = BaseDao.getConn();
		List<CartItem> cis = new ArrayList<CartItem>();
		try {
			ps = conn.prepareStatement("SELECT * FROM t_cart WHERE username='" + uname + "'");
			rs = ps.executeQuery();
			while (rs.next()) {
				CartItem c = new CartItem();
				c.setBookId(rs.getInt("BOOKID"));
				c.setBookName(rs.getString("BOOKNAME"));
				c.setBookQty(rs.getInt("BOOKQTY"));
				c.setBookPrice(rs.getDouble("BOOKPR"));
				cis.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		Double total = new Double(0);
		if (cis.isEmpty()) {
			out.print("购物车是空的。&nbsp;&nbsp;&nbsp;");
			out.print("<a href='PagingServlet?curpg=1'>去购物</a>");
		}
		else {
			out.print("<table width='500px' align='center' bgcolor='#D2F0FA' border='1' cellspacing='0' cellpadding='0'>");
			out.print("<tr><td align='center'>序号</td>");
			out.print("<td align='center'>图书ID</td>");
			out.print("<td align='center'>图书名称</td>");
			out.print("<td align='center'>数量</td>");
			out.print("<td align='center'>价格</td>");
			out.print("<td align='center'>操作</td></tr>");
			for (int i = 0; i < cis.size(); i++) {
				CartItem c0 = cis.get(i);
				out.print("<tr><td align='center'>" + (i + 1) + "</td>");
				out.print("<td align='center'>" + c0.getBookId() + "</td>");
				out.print("<td align='center'>" + c0.getBookName() + "</td>");
				out.print("<td align='center'>" + c0.getBookQty() + "</td>");
				out.print("<td align='center'>" + c0.getBookPrice() + "元</td>");
				out.print("<td align='center'>&nbsp;<a href='cartmodify.jsp?bookid=" + c0.getBookId() + "&username=" + uname + "&bookqty=" + c0.getBookQty() + "'>修改</a>&nbsp;&nbsp;"
					+ "<a href='cartdelete.jsp?bookid=" + c0.getBookId() + "&bookqty=" + c0.getBookQty() + "'>删除</a>&nbsp;</td></tr>");
				total += c0.getBookPrice();
			}
			out.print("</table><br />");
			out.print("<div align='center'>");
			out.print("订单总金额："+ total +"元。<br /><br />");
			out.print("<form action='settlement.jsp' method='post'>");
			out.print("<a href='PagingServlet?curpg=1'>继续购物</a>&nbsp;&nbsp;&nbsp;");
			out.print("<input type='hidden' name='totalprice' value='"+total+"'>");
			out.print("<input type='hidden' name='uname' value='"+uname+"'>");
			out.print("<input type='hidden' name='uid' value='"+uidstr+"'>");
			out.print("<input type='submit' value='去结算'>");
			out.print("</form></div>");
		}
	%>
</body>
</html>
