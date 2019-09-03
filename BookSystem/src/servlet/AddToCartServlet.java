package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import entity.*;

public class AddToCartServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idstr = request.getParameter("bid");
		String numstr = request.getParameter("bnum");
		String username = request.getParameter("uname");
		String invstr = request.getParameter("inv");
		Integer bookid = Integer.parseInt(idstr);
		Integer bnum = Integer.parseInt(numstr);
		Integer inventory = Integer.parseInt(invstr);
		BookDao bd = new BookDao();
		Book bk = bd.getBookById(bookid);
		CartDao cd = new CartDao();
		if (cd.addToCart(username, bk, bnum, inventory)) {
			response.sendRedirect("ok-ordinary.jsp");
		}
		else {
			response.sendRedirect("fail.jsp");
		}	
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}