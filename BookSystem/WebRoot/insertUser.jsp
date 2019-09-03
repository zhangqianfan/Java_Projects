<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>创建用户</title>
  </head> 
  <body style="background-image: url('img/u=4147206523,1119900303&fm=21&gp=0.jpg');">
    <form action="InsertUser" method="post">
    <table align="center" width="500" height="400" border="0" cellpadding="4">
			<tr>
				<td><div align="right"><span style="color: white">用户名：</span></div></td>
				<td><div align="left">
						<input type="text" name="username" style="width:250">
					</div></td>
			</tr>
			<tr>
				<td><div align="right"><span style="color: white">密码：</span></div></td>
				<td><div align="left">
						<input type="text" name="password" style="width:250">
					</div></td>
			</tr>
			<tr>
				<td><div align="right"><span style="color: white">角色：</span></div></td>
				<td><div align="left"> 
						<input type="radio" name="rolename" value="manager" /><span style="color: white">普通管理员</span>
						 <input type="radio" name="rolename" value="ordinary_user" /><span style="color: white">普通用户</span>
					</div></td>
			</tr>
			<tr>
				<td colspan="2"><div align="center">
						<input type="submit" value="新增"> <input type="reset"
							value="重填">
							 <a href="javascript:history.go(-1)"><span style="color: white">返回上一页</span></a>
					</div></td>
			</tr>
		</table>
    </form>
  </body>
</html>
