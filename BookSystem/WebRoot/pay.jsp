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

<title>Pay</title>

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
		//将当前购物车信息及所填收件人信息整合至表t_order和t_odetail中
		request.setCharacterEncoding("utf-8");
		String receivername = request.getParameter("receivername");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String totalprice = request.getParameter("tp");
		String uname = request.getParameter("uname");
		String uid = request.getParameter("uid");
		Integer oid = null;
		Connection conn = BaseDao.getConn();
		PreparedStatement ps1, ps2 = null;
		ResultSet rs1, rs2 = null;
		String sql1 = "INSERT INTO t_order (orderid,receivername,phone,address,sumprice,userid,paymethod,status,logistics,paid,exchanged,ordertime) VALUES (order_seq.NEXTVAL,?,?,?,?,?,'未定','未支付','无物流信息',0,'未申请',TO_CHAR(SYSDATE,'yyyy-MM-dd hh24:mi:ss'))";
		Object[] o1 = { receivername, phone, address, totalprice, uid };
		if (BaseDao.update(sql1, o1)) {
			ps1 = conn.prepareStatement("SELECT orderid FROM t_order WHERE userid = " + uid + " AND status = '未支付'");
			rs1 = ps1.executeQuery();
			if (rs1.next()) {
				oid = rs1.getInt("orderid");
			}
			ps2 = conn.prepareStatement("SELECT * FROM t_cart WHERE username = '" + uname + "'");
			rs2 = ps2.executeQuery();
			List<CartItem> cis = new ArrayList<CartItem>();
			String sql2 = "INSERT INTO t_odetail (bookid,bookname,bookqty,bookpr,orderid) VALUES (?,?,?,?,?)";
			while (rs2.next()) {
				CartItem c = new CartItem();
				c.setBookId(rs2.getInt("BOOKID"));
				c.setBookName(rs2.getString("BOOKNAME"));
				c.setBookQty(rs2.getInt("BOOKQTY"));
				c.setBookPrice(rs2.getDouble("BOOKPR"));
				cis.add(c);
				Object[] o2 = { c.getBookId(), c.getBookName(), c.getBookQty(), c.getBookPrice(), oid };
				if (BaseDao.update(sql2, o2)) {
					continue;
				} else break;
			}
		}
		//清空当前用户的购物车
		CartDao cd = new CartDao();
		if (cd.clearCart(uname)) {
			out.print("<h3>订单总金额："+totalprice+"元</h3>");
			out.print("请选择支付方式：<br /><br />");
			out.print("<form action='dopay.jsp' method='post'>");
			out.print("<input type='hidden' name='price' value='"+totalprice.toString()+"'>");
			out.print("<input type='hidden' name='orderid' value='"+oid+"'>");
			out.print("<input type='radio' name='paymethod' value='微信支付'>微信支付");
			out.print("<input type='radio' name='paymethod' value='支付宝支付'>支付宝支付");
			out.print("<input type='radio' name='paymethod' value='网银支付'>网银支付<br /><br />");
			out.print("<input type='submit' value='继续'></form>");
			out.print("<br />注：在线支付单笔订单不足150元需支付10元运费，不支持货到付款。<br /><br />");
		}
		else {
			out.print("对不起，由于服务器原因导致操作失败。<br /><br />");
			out.print("点此<a href='javascript:history.go(-1)'>返回上一页</a>以重试。<br /><br />");
		}
	%>
	<a href="myorder.jsp?userid=<%=uid%>">查看我的订单</a>&nbsp;&nbsp;&nbsp;
	<a href="PagingServlet?curpg=1">返回图书首页</a>&nbsp;&nbsp;&nbsp;
</body>
</html>
