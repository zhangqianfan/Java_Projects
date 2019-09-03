<%@ page import="dao.*"%>
<%@ page import="entity.*"%>
<%@ page import="servlet.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
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

<title>My Order</title>

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
		Integer uid = Integer.parseInt(uidstr);
		UserDao ud = new UserDao();
		User u = ud.getUserById(uid);
		String uname = u.getUsername();
	%>
	<h3>用户<%=uname%>的订单：</h3>	
	<%
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs1, rs2 = null;
		conn = BaseDao.getConn();
		try {
			String sql = "SELECT * FROM t_order WHERE userid = " + uidstr;
			ps = conn.prepareStatement(sql);
			rs1 = ps.executeQuery();
			Order od = new Order();
			if (rs1.next()) {
				out.print("<p align='center'>提示：点击订单号可查看订单详情。</p>");
				out.print("<table width='600px' align='center' bgcolor='#D2F0FA' border='1' cellspacing='0' cellpadding='0'>");
				out.print("<tr><td align='center'>订单号</td>");
				out.print("<td align='center'>订单总金额</td>");
				out.print("<td align='center'>实付金额</td>");
				out.print("<td align='center'>下单时间</td>");
				out.print("<td align='center'>状态</td>");
				out.print("<td align='center'>操作</td></tr>");
				rs2 = ps.executeQuery();
				while (rs2.next()) {
					od.setOrderid(rs2.getInt("orderid"));
					od.setTotalpr(rs2.getDouble("sumprice"));
					od.setPaid(rs2.getDouble("paid"));
					od.setUserid(rs2.getInt("userid"));
					od.setReceivername(rs2.getString("receivername"));
					od.setPhone(rs2.getString("phone"));
					od.setAddress(rs2.getString("address"));
					od.setPaymethod(rs2.getString("paymethod"));
					od.setOrdertime(rs2.getString("ordertime"));
					od.setStatus(rs2.getString("status"));
					od.setLogistics(rs2.getString("logistics"));
					out.print("<tr><td align='center'><a href='odetail.jsp?orderid="+od.getOrderid()+"'>"+od.getOrderid()+"</a></td>");
					out.print("<td align='center'>"+od.getTotalpr()+"</td>");
					out.print("<td align='center'>"+od.getPaid()+"</td>");
					out.print("<td align='center'>"+od.getOrdertime()+"</td>");
					out.print("<td align='center'>"+od.getStatus()+"</td>");
					Double tp = (od.getTotalpr() < 150) ? od.getTotalpr() + 10 : od.getTotalpr();
					if (od.getStatus().equals("未支付")) {
						out.print("<td align='center'><a href='cancelorder.jsp?orderid="+od.getOrderid()+"'>取消订单</a>");
						out.print("&nbsp;<a href='repay.jsp?price="+tp+"&orderid="+od.getOrderid()+"'>去支付</a></td></tr>");
					}
					else if (od.getStatus().equals("支付失败")) {
						out.print("<td align='center'><a href='repay.jsp?price="+tp+"&orderid="+od.getOrderid()+"'>重新支付</a></td></tr>");
					}
					else if (od.getStatus().equals("已支付") || od.getStatus().equals("已出库") || od.getStatus().equals("已发货")) {
						out.print("<td align='center'><a href='logistics.jsp?orderid="+od.getOrderid()+"'>查看物流</a></td></tr>");
					}
					else if (od.getStatus().equals("已签收")) {
						out.print("<td align='center'><a href='exchanged.jsp?orderid="+od.getOrderid()+"'>申请退换货</a></td></tr>");
					}
					else if (od.getStatus().equals("已申请退货") || od.getStatus().equals("已申请换货")) {
						out.print("<td align='center'>请等待客服处理</td></tr>");
					}
					else {
						out.print("<td align='center'>程序内部异常</td></tr>");
					}
				}
				out.print("</table>");
				out.print("<p align='center'>");
				out.print("<a href='PagingServlet?curpg=1'>去购物</a>&nbsp;&nbsp;&nbsp;");
				out.print("<a href='index.jsp'>退出登录</a></p>");
			}
			else {
				out.print("您没有订单记录！<br /><br />");
				out.print("<a href='PagingServlet?curpg=1'>去购物</a>&nbsp;&nbsp;&nbsp;");
				out.print("<a href='index.jsp'>退出登录</a>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs2);
		}
	%>
</body>
</html>
