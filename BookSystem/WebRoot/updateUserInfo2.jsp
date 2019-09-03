<%@page import="dao.UserDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改用户信息</title>
</head>
<%
	Integer uid = Integer.valueOf(request.getParameter("userid"));
	UserDao ud = new UserDao();
	if (ud.isExist(uid) != true) {
		request.setAttribute("message", "该用户不存在，无法更新信息");
		request.getRequestDispatcher("fail01.jsp").forward(request, response);
	}
%>
<body style="font-family:Microsoft YaHei">
	<h4>修改用户信息：</h4>
	<form action="updateUserInfo3.jsp" method="post">
		<table width="300" align="cenetr" border="0" cellpadding="4">
			<tr>
				<td><div align="right">用户ID：</div></td>
				<td><div align="left">
						<input type="text" readonly="readonly" name="userid"
							value=<%=request.getParameter("userid")%>>
					</div></td>
			</tr>
			<tr>
				<td><div align="right">新用户名：</div></td>
				<td><div align="left">
						<input type="text" name="username">
					</div></td>
			</tr>
			<tr>
				<td><div align="right">新密码：</div></td>
				<td><div align="left">
						<input type="password" name="password">
					</div></td>
			</tr>

			<tr>
				<td><div align="right">角色：</div></td>
				<td><div align="left">
						<input type="radio" name="rolename" value="manager" />普通管理员 <input
							type="radio" name="rolename" value="ordinary_user" />普通用户
					</div></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><div>
						<input type="submit" value="更新"> <input type="reset"
							value="清空"> <a href="javascript:history.go(-1)">返回上一页</a>

					</div></td>
			</tr>
		</table>
	</form>
</body>
</html>
