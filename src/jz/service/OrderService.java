package jz.service;

import java.util.List;

import jz.dao.OrderDetailDao;
import jz.dao.OrderInfoDao;
import jz.model.ShoppingCarInfo;

public class OrderService {

	private OrderInfoDao orderInfoDao = new OrderInfoDao();
	private OrderDetailDao orderDetailDao = new OrderDetailDao();

	/**
	 * 
	 * @param list
	 *            注意list的类型是List<ShoppingCarInfo>
	 */
	public void SubmitOrder(List<ShoppingCarInfo> list) {
		// 增加一条OrderInfo信息
		int orderId = orderInfoDao.insertOrderInfo(0);// 0为没有折扣（默认没有折扣）
		for (ShoppingCarInfo shoppingCarInfo : list) {
			orderDetailDao.insertOrderDetail(orderId,
					shoppingCarInfo.getBookId(),
					shoppingCarInfo.getOrderQuantity());
		}
	}

	/*
	 * public static void main(String[] args) { List<ShoppingCarInfo> list = new
	 * ArrayList<ShoppingCarInfo>(); list.add(new ShoppingCarInfo(1, "Java语言程序",
	 * 27, 1, 27)); OrderService orderService=new OrderService();
	 * orderService.SubmitOrder(list); }
	 */
}
