<%@ page import="dao.*"%>
<%@ page import="entity.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>Cart Modify</title>

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
function doDecrease() {
	var num = document.getElementById("num").value;
	if (num > 1) {
		num--;
	}
	else num = 1;
	document.getElementById("num").value = num;
}
function doIncrease() {
	var num = document.getElementById("num").value;
	var maxnum = document.getElementById("inv").innerHTML;
	if (num < parseInt(maxnum)) {
		num++;
	}
	else num = parseInt(maxnum);
	document.getElementById("num").value = num;
}
function checkNumber() {
	var num = document.getElementById("num").value;
	var maxnum = parseInt(document.getElementById("inv").innerHTML);
	var reg = new RegExp("^[0-9]*$");
	if (num < 0) {
		alert("购买数量不能为负数！");
		return false;
	}
	else if (num > maxnum) {
		alert("购买数量不能超过库存数量！");
		return false;
	}
	else if (!(reg.test(num))) {
		alert("输入的数据不是整数！");
		return false;
	}
	return true;
}
</script>
<body style="font-family:Microsoft YaHei">
	<%
		String bid = request.getParameter("bookid");
		String uname = request.getParameter("username");
		String qtystr = request.getParameter("bookqty");
		BookDao bd = new BookDao();
		Book bk = bd.getBookById(Integer.parseInt(bid));
		Integer inventory = bk.getInventory();
		Integer qty = Integer.parseInt(qtystr);
	%>
	<h3>您选择的图书为：《<%=bk.getTitle()%>》</h3>
	<form action="ModifyCartServlet?bookid=<%=bid%>" onsubmit="return checkNumber();">
	请修改购买数量：（库存<span id="inv"><%=inventory%></span>本）
	<button type="button" id="decrease" onclick="doDecrease()">-</button>
	<input type="text" style="width:45px" name="bnum" id="num" value="<%=qty%>">
	<button type="button" id="increase" onclick="doIncrease()">+</button>&nbsp;&nbsp;
	<input type="hidden" name="bid" value="<%=bid%>">
	<input type="hidden" name="uname" value="<%=uname%>"><br><br>
	<input type="submit" id="ok" value="确定">&nbsp;&nbsp;
	<a href="PagingServlet?curpg=1">返回图书首页</a>
	</form>
</body>
</html>
