<%@page import="dao.UserDao"%>
<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	Integer userid = Integer.valueOf(request.getParameter("userid"));
	String rolename=request.getParameter("rolename");
	UserDao ud = new UserDao();
	if (ud.updateInfo(username,password,userid) & ud.updateMinfo(ud.roletoid(rolename), userid)) {
		request.setAttribute("message", "更新用户信息成功");
		request.getRequestDispatcher("fail01.jsp").forward(request,
				response);
	} else {
		request.setAttribute("message", "更新用户信息失败");
		request.getRequestDispatcher("fail01.jsp").forward(request,
				response);
	}
%>
