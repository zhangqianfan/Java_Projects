package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class InsertUser extends HttpServlet {

	public InsertUser() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rolename = request.getParameter("rolename");
		UserDao ud=new UserDao();
		if(ud.insertUser(username, password, rolename)){
			request.setAttribute("message", "新增用户成功");
			request.getRequestDispatcher("fail01.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "新增用户失败,该用户已经存在！");
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
