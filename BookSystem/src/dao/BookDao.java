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

public class BookDao {

	public boolean add(Book b) {
		String sql = "INSERT INTO t_book (bookid,title,price,publishdate,author,imgurl,Inventory)"
				+ "VALUES (book_seq.NEXTVAL,?,?,?,?,?,?)";
		Date date = new Date(b.getPublishDate().getTime());
		Object[] o = { b.getTitle(), b.getPrice(), date, b.getAuthor(), b.getImgurl(),b.getInventory()};
		return BaseDao.update(sql, o);
	}

	public boolean update(Book b) {
		String sql = "UPDATE t_book SET title=?,price=?,publishdate=?,author=?,imgurl=?,inventory=? WHERE bookid=?";
		Date date = new Date(b.getPublishDate().getTime());
		Object[] o = { b.getTitle(), b.getPrice(), date, b.getAuthor(),b.getImgurl(),b.getInventory(),b.getId()};
		return BaseDao.update(sql, o);
	}

	public boolean delete(Book b) {
		String sql = "DELETE FROM t_book WHERE bookid=?";
		Object[] o = { b.getId() };
		return BaseDao.update(sql, o);
	}
	
	public Book getBookById(Integer bookid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book bk = new Book();
		conn = BaseDao.getConn();
		try {
			String sql = "SELECT * FROM t_book WHERE bookid="+bookid.toString();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				bk.setId(rs.getInt("BOOKID"));
				bk.setAuthor(rs.getString("AUTHOR"));
				bk.setTitle(rs.getString("TITLE"));
				bk.setPrice(rs.getDouble("PRICE"));
				bk.setPublishDate(rs.getDate("PUBLISHDATE"));
				bk.setImgurl(rs.getString("IMGURL"));
				bk.setInventory(rs.getInt("Inventory"));
				return bk;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		return null;
		
	}

	public List<Book> showAllBooks() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = BaseDao.getConn();
		List<Book> list = new ArrayList<Book>();
		try {
			ps = conn.prepareStatement("SELECT * FROM t_book ORDER BY bookid");
			rs = ps.executeQuery();
			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("BOOKID"));
				b.setAuthor(rs.getString("AUTHOR"));
				b.setTitle(rs.getString("TITLE"));
				b.setPrice(rs.getDouble("PRICE"));
				b.setPublishDate(rs.getDate("PUBLISHDATE"));
				b.setImgurl(rs.getString("IMGURL"));
				b.setInventory(rs.getInt("Inventory"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		return list;
	}

	public Integer getMaxPage(Integer size) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM t_book";
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

	public List<Book> selectByPage(Integer currpage, Integer size) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM(SELECT ROWNUM rn, t_book.* FROM t_book) t"
				+ " WHERE t.rn>" + (currpage - 1) * size + " AND t.rn<="
				+ currpage * size+" order by bookid";
		conn = BaseDao.getConn();
		List<Book> list = new ArrayList<Book>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("BOOKID"));
				b.setTitle(rs.getString("TITLE"));
				b.setAuthor(rs.getString("AUTHOR"));
				b.setPrice(rs.getDouble("PRICE"));
				b.setPublishDate(rs.getDate("PUBLISHDATE"));
				b.setImgurl(rs.getString("IMGURL"));
				b.setInventory(rs.getInt("Inventory"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		return list;
	}
	//根据bID查询是否存在该本书
		public boolean isExist(Integer bid){
			Book book=new Book();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			conn=BaseDao.getConn();
			String sql="select title from t_book where bookid=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, bid);
				rs=ps.executeQuery();
				while(rs.next()){
					book.setTitle(rs.getString("title"));		
				}
				return book.getTitle()!=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
		}
}
