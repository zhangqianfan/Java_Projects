package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import entity.Book;

public class PagingServlet extends HttpServlet {
	private Integer size = 5;
	public PagingServlet() {
		super();
	}
	public void destroy() {
		super.destroy();
	}
	//ע��curpageΪString�ͱ�����curpgΪInteger�ͱ�����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDao bd = new BookDao();
		String curpage = request.getParameter("curpg");
		Integer maxpage = bd.getMaxPage(size);
		Integer curpg = 0;
		// �жϵ�ǰҳ�Ǽ�
		if (curpage == null || Integer.valueOf(curpage) < 1) {
			curpg = 1;
		} else if (Integer.valueOf(curpage) > maxpage) {
			curpg = maxpage;
		} else {
			curpg = Integer.valueOf(curpage);
		}
		// ���ݵ�ǰҳ����ÿҳ��ʾ��������ѯͼ��
		List<Book> books = bd.selectByPage(curpg, size);
		// ת����������
		request.setAttribute("books", books);
		request.setAttribute("curpg", curpg);
		request.setAttribute("maxpage", maxpage);
		request.getRequestDispatcher("welcome-ordinary.jsp").forward(request,
				response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	public Integer getSize() {
		return size;
	}

}
