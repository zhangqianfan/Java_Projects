<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="dao.*"%>
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

<title>Paying</title>

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
	function colorRandom() {
		// 随机生成验证码每位数字的颜色，返回一个由16进制表示的6位颜色代码
		var r = Math.floor(Math.random() * 256).toString(16);
		var g = Math.floor(Math.random() * 256).toString(16);
		var b = Math.floor(Math.random() * 256).toString(16);
		if (r.length == 1) {
			r = "0" + r;
		}
		if (g.length == 1) {
			g = "0" + g;
		}
		if (b.length == 1) {
			b = "0" + b;
		}
		return "#" + r + g + b;
	}
	function generateVerifyCode() {
		//设置要作为验证码的四位数字
		var a = Math.floor(Math.random() * 10);
		var b = Math.floor(Math.random() * 10);
		var c = Math.floor(Math.random() * 10);
		var d = Math.floor(Math.random() * 10);
		document.getElementById("aa").innerHTML = a;
		document.getElementById("bb").innerHTML = b;
		document.getElementById("cc").innerHTML = c;
		document.getElementById("dd").innerHTML = d;
		//设置这四位数字的颜色（16进制表示的RGB值）
		document.getElementById("aa").style.color = colorRandom();
		document.getElementById("bb").style.color = colorRandom();
		document.getElementById("cc").style.color = colorRandom();
		document.getElementById("dd").style.color = colorRandom();
	}
	function checkCode() {
		var vercode = document.getElementById("verify").value;
		var code_a = document.getElementById("aa").innerHTML;
		var code_b = document.getElementById("bb").innerHTML;
		var code_c = document.getElementById("cc").innerHTML;
		var code_d = document.getElementById("dd").innerHTML;
		var code2 = code_a + code_b + code_c + code_d;
		if (vercode != code2) {
			alert("验证码错误！");
			generateVerifyCode();
			return false;
		}
		return true;
	}
</script>

<body style="font-family:Microsoft YaHei" onload="generateVerifyCode()">
	<%
		request.setCharacterEncoding("utf-8");
		String paymethod = request.getParameter("paymethod");
		String pricestr = request.getParameter("price");
		String oidstr = request.getParameter("orderid");
		Double price = Double.valueOf(pricestr);
		// 单笔订单不满150元需加收10元运费
		if (price < 150) {
			price += 10;
		}
		out.print("需支付金额：" + price + "元");
		out.print("（注：单笔订单不满150元需加收10元运费）。<br /><br />");
		out.print("<span style='font-weight:bold;color:red;'>您已选择用"
				+ paymethod + "方式支付。请确认当前页面的链接，谨防电信诈骗！</span>");
		String sql = "UPDATE t_order SET paid = ? WHERE orderid = ?";
		Object[] o = { price, Integer.parseInt(oidstr) };
		if (BaseDao.update(sql, o)) {
	%>
	<br />
	<br />
	<form action="PayServlet?price=<%=price%>&orderid=<%=oidstr%>&paymethod=<%=paymethod%>"
		method="post" onsubmit="return checkCode()">
		<table>
			<tr>
				<td>输入支付密码：</td>
				<td><input type="password" name="paypwd" id="paypwd"></td>
				<td></td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td><input type="text" name="verify" id="verify"></td>
				<td><div id="code"
						style="background-color:#648264;width:60px;height:25px;font-color:blue;font-family:Arial;font-size:20px;text-align:center">
						<span id="aa"></span><span id="bb"></span><span id="cc"></span><span
							id="dd"></span>
					</div></td>
			</tr>
			<tr>
				<td><button type="button" onclick="generateVerifyCode()">刷新验证码</button></td>
				<td><input type="submit" value="确认支付">&nbsp;
				<input type="reset" value="重新输入"></td>
			</tr>
		</table>
	</form>
	<br />
	<a href="javascript:history.go(-1)">返回重选支付方式</a>
	<%
		} else {
			out.print("操作失败。点此<a href='javascript:history:go(-1)'>返回上一页</a>以重试。");
		}
	%>
</body>
</html>
