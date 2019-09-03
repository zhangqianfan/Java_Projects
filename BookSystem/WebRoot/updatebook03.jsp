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
	var date = document.getElementById("newdate").value;
	var inv = document.getElementById("newinv").value;
	var regdate = new RegExp("\[0-9]{4}-\[0-9]{2}-\[0-9]{2}");
	var reginv = new RegExp("^[0-9]*$");
	if (!(reginv.test(inv))) {
		alert("输入的库存数量不合法！");
		return false;
	}
	if (!(regdate.test(date))) {
		alert("输入的日期格式不正确！");
		return false;
	}
	return true;
}
</script>
<%
	BookDao bd = new BookDao();
	Book book = bd.getBookById(Integer.valueOf(request.getParameter("bookid")));
%>
<body style="font-family:Microsoft YaHei">
	<h3>请填写要修改的图书信息：</h3>
	<form action="doupdatebook.jsp" method="post" enctype="multipart/form-data" onsubmit="return checkData();">
		<table width="600" border="0" cellpadding="6">
			<tr>
				<td><div align="right">ID：</div></td>
				<td><div align="left">
				<!-- readonly和disabled的区别在于后者完全禁止与设置该属性的对象交互(表现为不可改写、不可提交等)。 readonly是可以提交的 -->
						<input type="text" name="bookid" readonly="readonly" value=<%=request.getParameter("bookid") %>>
					</div></td>
			</tr>
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
				<td><div align="right">库存数量：</div></td>
				<td><div align="left">
						<input type="text" value=<%=book.getInventory()%> name="Inventory" id="newinv">
					</div></td>
			</tr>
			<tr>
				<td><div align="right">出版日期：</div></td>
				<td><div align="left">
						<input type="text" value=<%=book.getPublishDate()%> name="newdate"
							id="newdate"> （注：出版日期的合法格式应为yyyy-MM-dd。）
					</div></td>
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
