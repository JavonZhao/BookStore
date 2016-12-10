package jz.model;

public class OrderDetail {

	private int detailId;
	private int orderId;
	private int bookId;
	private  int orderQantity;
	
	public OrderDetail(){
		
	}
	
	public OrderDetail(int detailId, int orderId, int bookId, int orderQantity) {
		super();
		this.detailId = detailId;
		this.orderId = orderId;
		this.bookId = bookId;
		this.orderQantity = orderQantity;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getOrderQantity() {
		return orderQantity;
	}

	public void setOrderQantity(int orderQantity) {
		this.orderQantity = orderQantity;
	}
	
	
}
