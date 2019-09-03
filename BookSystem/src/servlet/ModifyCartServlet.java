package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.CartDao;
import entity.Book;

public class ModifyCartServlet extends HttpServlet {
	
	public ModifyCartServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bidstr = request.getParameter("bid");
		String numstr = request.getParameter("bnum");
		String invstr = request.getParameter("inv");
		Integer bookid = Integer.parseInt(bidstr);
		Integer bnum = Integer.parseInt(numstr);
		Integer inv = Integer.parseInt(invstr);
		BookDao bd = new BookDao();
		Book bk = bd.getBookById(bookid);
		CartDao cd = new CartDao();
		if (cd.modifyCart(bk, bnum, inv)) {
			response.sendRedirect("ok-ordinary.jsp");
		}
		else {
			response.sendRedirect("fail.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
