package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import entity.Book;

public class FenYeServlet extends HttpServlet {
	private int size = 5;
	
	public FenYeServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDao bd = new BookDao();
		String currpage1 = request.getParameter("currpage");
		Integer maxpage = bd.getMaxPage(size);
		Integer currpage = 0;
		if (currpage1 == null || Integer.valueOf(currpage1) < 1) {
			currpage = 1;
		} else if (Integer.valueOf(currpage1) > maxpage) {
			currpage = maxpage;
		} else {
			currpage = Integer.valueOf(currpage1);
		}
		List<Book> books = bd.selectByPage(currpage, size);

		request.setAttribute("books", books);
		request.setAttribute("currpage", currpage);
		request.setAttribute("maxpage", maxpage);
		request.getRequestDispatcher("showbooks.jsp")
				.forward(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */

}
