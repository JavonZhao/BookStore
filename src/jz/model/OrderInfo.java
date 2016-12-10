package jz.model;

import java.sql.Date;

public class OrderInfo {

	private int orderId;
	private Date orderTime;
	private double orderDiscount;
	
	public OrderInfo(){
		
	}
	
	

	public OrderInfo(int orderId, Date orderTime, double orderDiscount) {
		super();
		this.orderId = orderId;
		this.orderTime = orderTime;
		this.orderDiscount = orderDiscount;
	}



	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public double getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(double orderDiscount) {
		this.orderDiscount = orderDiscount;
	}
	
	
}
