<%@page import="dao.BookDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entity.Book"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	// 获取表单数据
	String iddel = request.getParameter("iddel");
	Book book = new Book();
	book.setId(Integer.valueOf(iddel));
	BookDao bd = new BookDao();
	if (bd.delete(book)) {
		request.getRequestDispatcher("success.jsp").forward(request, response);
	} else {
		request.setAttribute("message", "删除失败，原因：数据库中无此书信息。");
		request.getRequestDispatcher("fail.jsp").forward(request, response);
	}
%>
