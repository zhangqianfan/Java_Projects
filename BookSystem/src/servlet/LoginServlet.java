package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;

public class LoginServlet extends HttpServlet {

	public LoginServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �������Ա�������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rolename = request.getParameter("rolename");
		User u = new User();
		UserDao ud=new UserDao();
		u = ud.login(username,password,rolename);
		if (u.getUserid() == null) {
			request.setAttribute("message", "��½ʧ�ܣ�ԭ�����Ϊ���˻������ڻ�����������Լ�ѡ���û���ɫ����.");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
		} else {
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.setAttribute("rolename", rolename);
			
			HttpSession hs = request.getSession();
			hs.setAttribute("rolename", rolename);
			hs.setAttribute("user", u);
			request.setAttribute("username", u.getUsername());
			request.setAttribute("user", u);
			if(ud.roletoid(rolename)==1){
				request.setAttribute("role", "�߼�����Ա");
				request.getRequestDispatcher("superM_index.jsp").forward(request, response);
			}else if(ud.roletoid(rolename)==2){
				request.setAttribute("role", "ͼ�����Ա");
				request.getRequestDispatcher("manager_index.jsp").forward(request, response);
			}else{
				response.sendRedirect("PagingServlet?curpg=1");
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
