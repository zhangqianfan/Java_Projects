<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
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

<title>Logistics Info</title>

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
	<h3>订单号：<%=oid%></h3>
	物流进度：
	<%
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_order WHERE orderid = " + oid;
		conn = BaseDao.getConn();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				out.print(rs.getString("logistics"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			BaseDao.closeAll(conn, ps, rs);
		}
	 %>
	 <br /><br />
	 <a href="javascript:history.go(-1)">返回我的订单</a>&nbsp;&nbsp;
	 <a href="PagingServlet?curpg=1">返回图书首页</a>
</body>
</html>
