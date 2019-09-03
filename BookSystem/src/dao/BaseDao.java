package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BaseDao {
	private static ResourceBundle rb = ResourceBundle.getBundle("jdbc");
	private static final String DRIVERNAME = rb.getString("jdbc.driverName");
	private static final String URL = rb.getString("jdbc.url");
	private static final String USERNAME = rb.getString("jdbc.username");
	private static final String PASSWORD = rb.getString("jdbc.password");
	public static boolean update(String sql, Object[] o) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = getConn();
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < o.length; i++) {
				ps.setObject(i + 1, o[i]);
			}
			int num = ps.executeUpdate();
			return num > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return false;
	}

	// 建立连接
	public static Connection getConn() {
		try {
			Class.forName(DRIVERNAME);
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 关闭连接
	public static void closeAll(Connection conn, PreparedStatement ps,
			ResultSet rs) {
		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (ps != null && (!ps.isClosed())) {
				ps.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}