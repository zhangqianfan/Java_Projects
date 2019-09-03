<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body style="font-family:Microsoft YaHei">
	<!-- 取到所有用户的集合 -->
	<%
		List<User> users = (List<User>) request.getAttribute("users");
	%>
	<h3 align="center">用户信息：</h3>
	<table width="600" align='center' bgcolor='#D2F0FA' border='1' cellspacing='0' cellpadding='0'>
		<!-- 表格 -->
		<tr>
			<!-- 行 -->
			<th>用户id</th>
			<th>用户名</th>
			<th>角色id</th>
			<th>角色</th>
		</tr>

		<%
			for (User u :users) {
		%>
		<tr>
			<td align="center"><%=u.getUserid()%></td>
			<td align="center"><%=u.getUsername()%></td>
			<td align="center"><%=u.getRoleid()%></td>
			<td align="center"><%=u.getRolename()%></td>
		</tr>
		<%
			}
		%>
	</table><br />
	<div align="center">
	<a href="SelectUser?currpage=1">首页</a>&nbsp;&nbsp;
	<a href="SelectUser?currpage=${currpage-1}">上一页</a>&nbsp;&nbsp;
	当前为第${currpage}页&nbsp;&nbsp;
	<a href="SelectUser?currpage=${currpage+1}">下一页</a>&nbsp;&nbsp;
	<a href="SelectUser?currpage=${maxpage}">末页</a><br><br>
	<a href="javascript:history.go(-1)">后退</a>&nbsp;&nbsp;
	<a href="superM_index.jsp">返回管理首页</a></div>
</body>
</html>
