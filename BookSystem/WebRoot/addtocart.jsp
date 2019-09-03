<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="dao.*"%>
<%@ page language="java" import="entity.*"%>
<%@ page language="java" import="servlet.*"%>
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

<title>Add to cart</title>

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
	//设置库存防止购买数目无限增大
	var num = document.getElementById("num").value;
	var maxnum = parseInt(document.getElementById("inv").innerHTML);
	if (num < maxnum) {
		num++;
	}
	else num = maxnum;
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
		String idstr = request.getParameter("bookid");
		String nmstr = request.getParameter("username");
		//String inventory = request.getParameter("inventory");
		Integer bookid = Integer.parseInt(idstr);
		BookDao bd = new BookDao();
		Book bk = bd.getBookById(bookid);
		if (bk.getInventory() <= 0) {
			out.print("对不起，您选择的图书没有库存了。");
			out.print("点此<a href='PagingServlet?curpg=1'>返回图书首页</a>。");
		}
		else {
			out.print("<h3>您选择的图书为：《" + bk.getTitle() + "》</h3>");
			out.print("<form action='AddToCartServlet?bookid="+bookid+"' onsubmit='return checkNumber();'>");
			out.print("请输入购买数量：");
			out.print("<button type='button' id='decrease' onclick='doDecrease()'>-</button>");
			out.print("<input type='text' style='width:45px' name='bnum' id='num' value='1'>");
			out.print("<button type='button' id='increase' onclick='doIncrease()'>+</button>");
			out.print("（库存<span id='inv'>"+bk.getInventory()+"</span>本）");
			out.print("<input type='hidden' name='bid' value='"+bookid+"'>");
			out.print("<input type='hidden' name='inv' value='"+bk.getInventory()+"'>");
			out.print("<input type='hidden' name='uname' value='"+nmstr+"'><br><br>");
			out.print("<input type='submit' id='ok' value='确定'>&nbsp;&nbsp;");
			out.print("<a href='PagingServlet?curpg=1'>返回图书首页</a></form>");
		}
	%>
</body>
</html>