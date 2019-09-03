package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.Book;
import entity.User;

public class SelectUser extends HttpServlet {
	private int size=7;

	public SelectUser() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currpage1 = request.getParameter("currpage");
		UserDao ud=new UserDao();
		Integer maxpage = ud.getMaxPage(size);
		Integer currpage = 0;
		if (currpage1 == null || Integer.valueOf(currpage1) < 1) {
			currpage = 1;
		} else if (Integer.valueOf(currpage1) > maxpage) {
			currpage = maxpage;
		} else {
			currpage = Integer.valueOf(currpage1);
		}
		List<User> users = ud.selectByPage(currpage, size);
		request.setAttribute("users", users);
		request.setAttribute("currpage", currpage);
		request.setAttribute("maxpage", maxpage);
		request.getRequestDispatcher("showusers.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
	}

}
