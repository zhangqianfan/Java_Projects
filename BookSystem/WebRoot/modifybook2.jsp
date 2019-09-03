<%@page import="dao.BookDao"%>
<%@page import="entity.Book"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改图书信息</title>
</head>
<script>
function checkData() {
	var newdate = document.getElementById("newdate").value;
	var newinv = document.getElementById("newinventory").value;
	var regdate = new RegExp("\[0-9]{4}-\[0-9]{2}-\[0-9]{2}");
	var reginv = new RegExp("^[0-9]*$");
	if (!(reginv.test(newinv))) {
		alert("输入的库存数量不合法！");
		return false;
	}
	if (!(regdate.test(newdate))) {
		alert("输入的日期格式不正确！");
		return false;
	}
	return true;
}
</script>
<body style="font-family:Microsoft YaHei">
	<%
		String name = (String) session.getAttribute("username");
		String bookid = request.getParameter("bookid");
		if (name == null) {
			response.sendRedirect("index.jsp");
		}
		BookDao bd = new BookDao();
		Book book = bd.getBookById(Integer.valueOf(bookid));
	%>
	<h3>请填写要修改的图书信息：</h3>
	<form action="domodify.jsp" method="post" enctype="multipart/form-data" onsubmit="return checkData();">
	<input type="hidden" value=<%=bookid%> name="bookid">
		<table width="800" border="0" cellpadding="4">
			<tr>
				<td><div align="right">名称：</div></td>
				<td><div align="left">
						<input type="text" value=<%=book.getTitle()%> name="newname">
					</div></td>
			</tr>
			<tr>
				<td><div align="right">作者：</div></td>
				<td><div align="left">
						<input type="text" value=<%=book.getAuthor()%> name="newauthor">
					</div></td>
			</tr>
			<tr>
				<td><div align="right">价格：</div></td>
				<td><div align="left">
						<input type="text" value=<%=book.getPrice()%> name="newprice">
					</div></td>
			</tr>
			<tr>
				<td><div align="right">库存：</div></td>
				<td><div align="left">
						<input type="text" value=<%=book.getInventory()%> name="newinventory" id="newinventory">
					</div></td>
			</tr>
			<tr>
				<td><div align="right">出版日期：</div></td>
				<td><div align="left">
						<input type="text" value=<%=book.getPublishDate()%> name="newdate" id="newdate">
						（注：出版日期的合法格式应为yyyy-MM-dd。）
					</div>
					</td>
			</tr>
			<tr>
				<td><div align="right">封面：</div></td>
				<td><div align="left">
						<input type="file" name="newimg">
					</div></td>
			</tr>
			<tr>
				<td colspan="2"><div>
						<input type="submit" value="更新"> <input type="reset"
							value="清空"> <a href="javascript:history.go(-1)">返回上一页</a>

					</div></td>
			</tr>
		</table>
	</form>
</body>
</html>
