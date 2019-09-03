<%@page import="dao.BookDao"%>
<%@page import="entity.Book"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	// 获取表单数据
	String bookid = request.getParameter("bookid");
	BookDao bd = new BookDao();
	Book book = bd.getBookById(Integer.valueOf(bookid));
	if (book == null) {
		request.setAttribute("message", "无法更新，原因：数据库中无此书信息");
		request.getRequestDispatcher("fail.jsp").forward(request,response); 
	} else {
		request.getRequestDispatcher("modifybook2.jsp").forward(request, response);
	}
%>
