package jz.model;

import java.util.Date;
public class BookInfo {
	private int bookId;
	private String bookName;
	private Date publishDate;
	private double bookPrice;
	
	public BookInfo() {

	}
	public BookInfo(int bookId, String bookName, Date publishDate,
			double bookPrice) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.publishDate = publishDate;
		this.bookPrice = bookPrice;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	


}
