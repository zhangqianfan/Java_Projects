package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class DeleteUser extends HttpServlet {
	public DeleteUser() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uid = request.getParameter("userid");
		Integer userid = Integer.valueOf(uid);
		UserDao ud=new UserDao();
		if(ud.isExist(userid)){
			if(ud.delete(userid)){
				request.setAttribute("message", "���û��Ѿ�ɾ���ɹ�");
				request.getRequestDispatcher("fail01.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("message", "ɾ��ʧ�ܣ�ԭ�򣺲����ڸ��û�");
			request.getRequestDispatcher("fail01.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
	}

}
