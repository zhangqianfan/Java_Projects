package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.User;

public class UserDao {
	//更新用户信息
	public boolean updateInfo(String username,String password,Integer userid) {
		String sql = "UPDATE t_user SET username=?,password=? WHERE userid=?";
		Object[] o = { username,password,userid};
		return BaseDao.update(sql, o);
	}
	
	//修改用户权限
	public boolean updateMinfo(Integer roleid,Integer userid) {
		String sql = "UPDATE t_roleanduser SET roleid=? WHERE userid=?";
		Object[] o = { roleid,userid};
		return BaseDao.update(sql, o);
	}
	// 用户登录
	public User login(String username, String password, String rolename) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User u = new User();
		conn = BaseDao.getConn();
		int roleid = roletoid(rolename);
		try {
			String sql = "select t_user.*,t_roleanduser.roleid from t_user,t_roleanduser where t_user.userid=t_roleanduser.userid and username = ? AND password = ? and t_roleanduser.roleid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, roleid);
			rs = ps.executeQuery();
			if (rs.next()) {
				u.setUserid(rs.getInt("userid"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setRoleid(rs.getInt("roleid"));
			}
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		return null;
	}

	// 插入用户
	public boolean insertUser(String username, String password, String rolename) {
		int roleid = roletoid(rolename);
		String sql1 = "insert into t_user (userid,username,password) values (userid_seq.nextval,?,?)";
		Object[] o1 = { username, password };

		String sql2 = "insert into t_roleanduser (userid,roleid) values (userid_seq.nextval-1,?)";
		Object[] o2 = { roleid };

		return BaseDao.update(sql1, o1) & BaseDao.update(sql2, o2);
	}

	// 查询所有用户信息，获取最大页数
	public Integer getMaxPage(Integer size) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM(SELECT rownum rn, t_user.*,t_role.rolename FROM t_user,t_role,t_roleanduser "
				+ "where t_role.roleid=t_roleanduser.roleid and t_roleanduser.userid=t_user.userid)";
		Integer num = 0;
		conn = BaseDao.getConn();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		return (num % size == 0) ? (num / size) : (num / size + 1);
	}

	// 分页查询用户信息

	public List<User> selectByPage(Integer currpage, Integer size) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM(SELECT rownum rn, t_user.userid,t_user.username,t_role.* FROM t_user,t_role,t_roleanduser "
				+ "where t_role.roleid=t_roleanduser.roleid and t_roleanduser.userid=t_user.userid) t "
				+ "WHERE t.rn>"
				+ (currpage - 1)
				* size
				+ " AND t.rn<= "
				+ currpage * size+" order by userid";
		conn = BaseDao.getConn();
		List<User> list = new ArrayList<User>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUserid(rs.getInt("userid"));
				u.setUsername(rs.getString("username"));
				u.setRoleid(rs.getInt("roleid"));
				u.setRolename(rs.getString("beizhu"));
				list.add(u);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		return null;
	}

	// 根据UID查询是否存在该用户
	public boolean isExist(Integer uid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = BaseDao.getConn();
		String sql = "select username from t_user where userid=" + uid;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUserid(uid);
				u.setUsername(rs.getString("username"));
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean delete(Integer uid) {
		String sql1 = "delete from t_user where userid=?";
		String sql2 = "delete from t_roleanduser where userid=?";
		Object[] o = { uid };
		return BaseDao.update(sql1, o) & BaseDao.update(sql2, o);
	}

	public User getUserById(Integer userid) {
		User u = new User();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = BaseDao.getConn();
		try {
			String sql = "SELECT * FROM t_user WHERE userid = "
					+ userid.toString();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				u.setUserid(userid);
				u.setUsername(rs.getString("username"));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		return null;
	}
	// 根据角色名返回角色id
		public int roletoid(String rolename) {
			int temp = 0;
			if (rolename.equals("superM")) {
				temp = 1;
			} else if (rolename.equals("manager")) {
				temp = 2;
			} else {
				temp = 3;
			}
			return temp;
		}
}
