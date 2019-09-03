<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加图书</title>
</head>
<script>
function checkData() {
	var date = document.getElementById("publishdate").value;
	var inv = document.getElementById("inventory").value;
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
<body style="font-family:Microsoft YaHei">
	<h3>请填写图书信息：</h3>
	<form action="doaddbook.jsp" method="post" enctype="multipart/form-data" onsubmit="return checkData();">
		<br /> 图书名称：<input type="text" name="bookname"><br /><br />
		图书作者：<input type="text" name="author"><br /><br />
		图书价格：<input type="text" name="price"><br /><br />
		出版日期：<input type="text" name="publishdate" id="publishdate"><br />
		（注：出版日期的合法格式应为yyyy-MM-dd。）<br /><br />
		库存数量：<input type="text" name="inventory" id="inventory"><br /><br />
		上传封面：<input type="file" name="img"><br /> <br />
		<input type="submit" value="添加">&nbsp;&nbsp;&nbsp;
		<input type="reset" value="清空">
		<a href="javascript:history.go(-1)">返回上一页</a>
	</form>
</body>
</html>
