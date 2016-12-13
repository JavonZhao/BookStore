package jz.model;

import org.apache.jasper.tagplugins.jstl.core.If;

public class ShoppingCarInfo {

	private int bookId;
	private String bookName;
	private double bookPrice;
	private int orderQuantity;
	private double totalPrice;

	public ShoppingCarInfo() {
		
	}

	public ShoppingCarInfo(int bookId, String bookName, double bookPrice,
			int orderQuantity, double totalPrice) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.orderQuantity = orderQuantity;
		this.totalPrice = totalPrice;
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

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	public double setTotalPrice(){
		return bookPrice*orderQuantity;
	}

}
