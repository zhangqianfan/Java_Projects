package servlet;

import java.io.IOException;
import java.sql.*;
import dao.*;
import entity.Order;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayServlet extends HttpServlet {

	public PayServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String pricestr = request.getParameter("price");
		Double price = Double.valueOf(pricestr);
		Double remain = new Double(0);
		String oidstr = request.getParameter("orderid");
		String paypwd1 = request.getParameter("paypwd");
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		String sql1 = "SELECT * FROM t_order WHERE orderid = " + oidstr;
		conn = BaseDao.getConn();
		try {
			ps1 = conn.prepareStatement(sql1);
			rs1 = ps1.executeQuery();
			if (rs1.next()) {
				Integer userid = rs1.getInt("userid");
				String sql2 = "SELECT * FROM t_account WHERE userid = " + userid;
				ps2 = conn.prepareStatement(sql2);
				rs2 = ps2.executeQuery();
				if (rs2.next()) {
					String paypwd2 = rs2.getString("paypwd");
					if (!(paypwd1.equals(paypwd2))) {
						response.sendRedirect("pay-fail1.jsp");
					}
					else {
						OrderDao od = new OrderDao();
						Order or = od.getOrderById(Integer.parseInt(oidstr));
						Double tp = or.getTotalpr();
						String paymethod = request.getParameter("paymethod");
						remain = rs2.getDouble("funds");
						String sql3 = "UPDATE t_order SET status = '已支付', paymethod = ? WHERE orderid = ?";
						Object[] o1 = {paymethod, Integer.parseInt(oidstr)};
						String sql4 = "UPDATE t_order SET status = '支付失败' WHERE orderid = ?";
						Object[] o2 = {Integer.parseInt(oidstr)};
						if (price <= remain) {
							remain -= price;
							String sql5 = "UPDATE t_account SET funds = ? WHERE userid = " + userid;
							Object[] o3 = {remain};
							if (BaseDao.update(sql3, o1) && BaseDao.update(sql5, o3)) {
								request.setAttribute("info", "账户原余额：" + (remain + price) + "元；可用余额：" + remain + "元。");
								request.setAttribute("orderid", oidstr);
								request.getRequestDispatcher("pay-ok.jsp").forward(request, response);
							}
						}
						else {
							if (BaseDao.update(sql4, o2)) {
								request.setAttribute("info", "支付未成功。原因：账户余额不足（" + remain + "元）。");
								request.setAttribute("price", tp);
								request.setAttribute("orderid", oidstr);
								request.getRequestDispatcher("pay-fail2.jsp").forward(request, response);
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps1, rs1);
			BaseDao.closeAll(conn, ps2, rs2);
		}
			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
