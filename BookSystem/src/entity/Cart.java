package entity;

public class Cart {
	private Book book;
	private Integer quantity;
	
	public Cart(Book bk, Integer i) {
		this.book = bk;
		this.quantity = i;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void addQuantity(Integer qty) {
		quantity += qty;
	}
	
}