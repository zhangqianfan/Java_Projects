<%@page import="dao.BookDao"%>
<%@page import="entity.Book"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String bid = request.getParameter("bookid");
	BookDao bd = new BookDao();
	if (bd.isExist(Integer.valueOf(bid))) {
		request.setAttribute("bookid", bid);
		request.getRequestDispatcher("updatebook03.jsp").forward(request,
				response); 
	} else {
		request.setAttribute("message", "数据库中无此书信息，无法更新");
		request.getRequestDispatcher("fail01.jsp").forward(request,
				response);
	}
%>