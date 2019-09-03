package entity;

public class CartItem {
	
	private Integer bookid;
	private String bookname;
	private Integer bookqty;
	private Double bookpr;
	
	public Integer getBookId() {
		return bookid;
	}
	public void setBookId(Integer bookid) {
		this.bookid = bookid;
	}
	public String getBookName() {
		return bookname;
	}
	public void setBookName(String bookname) {
		this.bookname = bookname;
	}
	public Integer getBookQty() {
		return bookqty;
	}
	public void setBookQty(Integer bookqty) {
		this.bookqty = bookqty;
	}
	public Double getBookPrice() {
		return bookpr;
	}
	public void setBookPrice(Double bookpr) {
		this.bookpr = bookpr;
	}
	
}
