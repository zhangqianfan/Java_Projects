package dao;

import entity.Book;
import entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
	public Order getOrderById(Integer orderid) {
		// 凭订单号获取订单
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Order od = new Order();
		conn = BaseDao.getConn();
		try {
			String sql = "SELECT * FROM t_order WHERE orderid = " + orderid;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				od.setOrderid(rs.getInt("orderid"));
				od.setTotalpr(rs.getDouble("sumprice"));
				od.setPaid(rs.getDouble("paid"));
				od.setUserid(rs.getInt("userid"));
				od.setReceivername(rs.getString("receivername"));
				od.setPhone(rs.getString("phone"));
				od.setAddress(rs.getString("address"));
				od.setPaymethod(rs.getString("paymethod"));
				od.setStatus(rs.getString("status"));
				od.setLogistics(rs.getString("logistics"));
				od.setExchanged(rs.getString("exchanged"));
				od.setOrdertime(rs.getString("ordertime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		return od;
	}
	
	public boolean cancelOrderById(Integer orderid) {
		// 凭订单号取消订单
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		String sql1 = "SELECT * FROM t_odetail WHERE orderid = " + orderid;
		String sql2, sql3 = null;
		boolean flag1 = true;
		boolean flag2 = true;
		conn = BaseDao.getConn();
		try {
			ps1 = conn.prepareStatement(sql1);
			rs1 = ps1.executeQuery();
			while (rs1.next()) {
				sql2 = "SELECT * FROM t_book WHERE bookid = "
						+ rs1.getInt("bookid");
				ps2 = conn.prepareStatement(sql2);
				rs2 = ps2.executeQuery();
				if (rs2.next()) {
					//先还原库存，再删除订单
					Integer inv = rs2.getInt("inventory");
					Integer qty = rs1.getInt("bookqty");
					sql3 = "UPDATE t_book SET inventory = ? WHERE bookid = "
							+ rs1.getInt("bookid");
					Object[] o = { inv + qty };
					if (!(BaseDao.update(sql3, o))) {
						flag1 = false;
						break;
					}
				} else {
					flag1 = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps1, rs1);
			BaseDao.closeAll(conn, ps2, rs2);
		}
		String delsql1 = "DELETE FROM t_odetail WHERE orderid = ?";
		String delsql2 = "DELETE FROM t_order WHERE orderid = ?";
		Object[] o2 = { orderid };
		if (!(BaseDao.update(delsql1, o2))
				|| !(BaseDao.update(delsql2, o2))) {
			flag2 = false;
		}
		return (flag1 && flag2);
	}
}
