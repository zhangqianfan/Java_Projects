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

<title>Check-out</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<script type="text/javascript">
function checkForm() {
	var name = document.getElementById("receivername").value;
	var phone = document.getElementById("phone").value;
	var regname = new RegExp("^[\u4E00-\u9FA5]+$");
	var regphone = /^1[34578]{1}\d{9}/g;
	if (!(regname.test(name))) {
		alert("姓名格式不正确！");
		return false;
	}
	if (phone.match(regphone) == null) {
		alert("手机号码格式不正确！");
		return false;
	}
	return true;
}
</script>

<body style="font-family:Microsoft YaHei">
	<%
		String totalprice = request.getParameter("totalprice");
		//uname,uid就像两个接力棒
		String uname = request.getParameter("uname");
		String uid = request.getParameter("uid");
		BookDao bd = new BookDao();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql, invsql = null;
		boolean flag = true;
		conn = BaseDao.getConn();
		try {
			sql = "SELECT * FROM t_cart WHERE username = '" + uname + "'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer bid = rs.getInt("bookid");
				Integer qty = rs.getInt("bookqty");
				Book bk = bd.getBookById(bid);
				invsql = "UPDATE t_book SET inventory = ? WHERE bookid = ?";
				Object[] o = {bk.getInventory() - qty, bid};
				// 只有下单后才会引起书本库存的变化
				if (!(BaseDao.update(invsql, o))) {
					out.print("对不起，由于服务器问题，导致您下单失败。我们对此引起的不便深感抱歉。<br><br>");
					out.print("请点击<a href='PagingServlet?curpg=1'>这里</a>回到图书首页。");
					flag = false;
					break;
				}
			}
			if (flag) {
				out.print("<h3>订单总金额："+totalprice+"元。</h3>");
				out.print("请填写以下信息：<br /><br />");
				out.print("<form action='pay.jsp' method='post' onsubmit='return checkForm()'>");
				out.print("收件人姓名：<input type='text' name='receivername' id='receivername'><br /><br />");
				out.print("收件人地址：<input type='text' name='address'><br /><br />");
				out.print("收件人手机：<input type='text' name='phone' id='phone'><br /><br />");
				out.print("<input type='hidden' name='tp' value='"+totalprice+"'>");
				out.print("<input type='hidden' name='uname' value='"+uname+"'>");
				out.print("<input type='hidden' name='uid' value='"+uid+"'>");
				out.print("<input type='submit' value='提交订单'>&nbsp;&nbsp;");
				out.print("<input type='reset' value='重新填写'></form><br />");
				out.print("<a href='cartdetail.jsp?userid="+uid+"'>返回我的购物车</a>&nbsp;&nbsp;");
				out.print("<a href='myorder.jsp?userid="+uid+"'>查看我的订单</a>&nbsp;&nbsp;");
				out.print("<a href='PagingServlet?curpg=1'>返回图书首页</a>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
	%>
</body>
</html>
