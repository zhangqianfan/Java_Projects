package dao;

import java.sql.*;
import java.util.*;

import entity.*;

public class CartDao {
	private Map<Integer, Cart> carts = new HashMap<Integer, Cart>();
	// ��ͼ����빺�ﳵ
	public boolean addToCart(String u, Book bk, Integer qty, Integer inv) {
		Integer bid = bk.getId();
		String sql1, sql2 = null;
		carts = cartQuery(bid);
		if (carts.containsKey(bid)) {
			Cart ct = carts.get(bid);
			ct.addQuantity(qty);
			sql1 = "UPDATE t_cart SET bookqty=?, bookpr=? WHERE bookid=?";
			Object[] o1 = {ct.getQuantity(), ct.getQuantity() * bk.getPrice(), bid};
			sql2 = "UPDATE t_book SET inventory=? WHERE bookid=?";
			Object[] o2 = {inv - qty, bid};
			return BaseDao.update(sql1, o1) && BaseDao.update(sql2, o2);
		} else {
			carts.put(bid, new Cart(bk, qty));
			sql1 = "INSERT INTO t_cart (bookid,bookname,bookqty,bookpr,username) VALUES (?,?,?,?,?)";
			Object[] o1 = {bk.getId(), bk.getTitle(), qty, bk.getPrice() * qty, u};
			sql2 = "UPDATE t_book SET inventory=? WHERE bookid=?";
			Object[] o2 = {inv - qty, bid};
			return BaseDao.update(sql1, o1) && BaseDao.update(sql2, o2);
		}
		
	}
	// �޸Ĺ��ﳵ
	public boolean modifyCart(Book bk, Integer qty) {
		Integer bid = bk.getId();
		String sql1 = null;
		carts = cartQuery(bid);
		Cart ct = carts.get(bid);
		ct.setQuantity(qty);
		sql1 = "UPDATE t_cart SET bookqty=?, bookpr=? WHERE bookid=?";
		Object[] o1 = {ct.getQuantity(), ct.getQuantity() * bk.getPrice(), bid};
		return BaseDao.update(sql1, o1);
	}
	// ��ѯ���ﳵ
	public Map<Integer, Cart> cartQuery(Integer bid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = BaseDao.getConn();
		try {
			String sql = "SELECT * FROM t_cart WHERE bookid=" + bid.toString();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				BookDao bd = new BookDao();
				Book b = bd.getBookById(bid);
				carts.put(bid, new Cart(b, rs.getInt("bookqty")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		return carts;
	}
	// ��յ�ǰ�û��Ĺ��ﳵ
	public boolean clearCart(String u) throws SQLException {
		// ��Ҫ��ճ�Ա����cartsҲҪ��ձ�t_cart�е�����
		if (!(carts.isEmpty())) {
			carts.clear();
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = BaseDao.getConn();
		try {
			String sql = "DELETE FROM t_cart WHERE username=?";
			Object[] o = {u};
			return BaseDao.update(sql, o) && carts.isEmpty();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
	}
}
