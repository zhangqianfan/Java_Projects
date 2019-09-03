<%@page import="entity.Book"%>
<%@page import="dao.BookDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
/* 处理普通用户登录之后分页显示书籍信息的功能 */
	Integer size = 5;
	BookDao bd = new BookDao();
	String curpage = request.getParameter("curpg");
	Integer maxpage = bd.getMaxPage(size);
	Integer curpg = 0;
	// 判断当前页是几
	if (curpage == null || Integer.valueOf(curpage) < 1) {
		curpg = 1;
	} else if (Integer.valueOf(curpage) > maxpage) {
		curpg = maxpage;
	} else {
		curpg = Integer.valueOf(curpage);
	}
	// 根据当前页数和每页显示数据量查询图书
	List<Book> books = bd.selectByPage(curpg, size);
	// 转发传递数据
	request.setAttribute("books", books);
	request.setAttribute("curpg", curpg);
	request.setAttribute("maxpage", maxpage);
	request.getRequestDispatcher("ordinary_index.jsp").forward(
			request, response);
%>
