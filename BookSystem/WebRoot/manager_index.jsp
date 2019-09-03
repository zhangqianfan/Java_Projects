<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>普通管理员界面</title>
  </head>
  <body style="font-family:Microsoft YaHei">
 <h3>欢迎你，${username}！你的身份是:${role}，点击此处<a href="index.jsp">退出登录</a></h3>
  <h4>管理图书：</h4>
      <ul>
           <li><a href="addbook.jsp">新增图书信息</a></li></br>
           <li><a href="updatebook1.jsp">修改图书信息</a></li></br>
           <li><a href="FenYeServlet?currpage=1">查询图书信息</a></li></br>
      </ul>
  </body>
</html>