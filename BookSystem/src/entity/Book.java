package entity;
//import java.sql.Date;
import java.util.Date;
public class Book {
	private Integer id;
	private String title;
	private String author;
	private Double price;
	private Date publishDate;
	private String imgurl;
	private Integer Inventory;
	
	public Integer getInventory() {
		return Inventory;
	}
	public void setInventory(Integer inventory) {
		Inventory = inventory;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishdate) {
		this.publishDate = publishdate;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
}
