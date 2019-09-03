<%@page import="entity.Book"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<body style="font-family:Microsoft YaHei">
	<!-- 取到所有书的集合 -->
	<%
		List<Book> books = (List<Book>) request.getAttribute("books");
	%>
	<h3 align="center">图书信息：</h3>
	<table width="600px" align='center' bgcolor='#D2F0FA' border='1' cellspacing='0' cellpadding='0'>
		<!-- 表格 -->
		<tr>
			<!-- 行 -->
			<th>编号</th>
			<!-- 列 -->
			<th>书名</th>
			<th>作者</th>
			<th>价格</th>
			<th>库存</th>
			<th>日期</th>
			<th>封面</th>
		</tr>

		<%
			for (Book b : books) {
		%>
		<tr>
			<td align="center"><%=b.getId()%></td>
			<td align="center"><%=b.getTitle()%></td>
			<td align="center"><%=b.getAuthor()%></td>
			<td align="center"><%=b.getPrice()%></td>
			<td align="center"><%=b.getInventory()%></td>
			<td align="center"><%=b.getPublishDate()%></td>
			<td align="center"><img alt="" src="<%=b.getImgurl()%>"></td>
		</tr>
		<%
			}
		%>
	</table><br />
	<div align="center">
	<a href="FenYeServlet?currpage=1">首页</a>&nbsp;&nbsp;
	<a href="FenYeServlet?currpage=${currpage-1}">上一页</a>&nbsp;&nbsp;
	当前为第${currpage}页&nbsp;&nbsp;
	<a href="FenYeServlet?currpage=${currpage+1}">下一页</a>&nbsp;&nbsp;
	<a href="FenYeServlet?currpage=${maxpage}">末页</a><br><br>
	<a href="javascript:history.go(-1)">后退</a>&nbsp;&nbsp;
	<a href="superM_index.jsp">返回管理首页</a></div>
</body>
</html>
