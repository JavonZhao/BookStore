package jz.service;

import java.util.List;

import jz.dao.OrderDetailDao;
import jz.dao.OrderInfoDao;
import jz.model.ShoppingCarInfo;

public class OrderService {

	private OrderInfoDao orderInfoDao = new OrderInfoDao();
	private OrderDetailDao orderDetailDao = new OrderDetailDao();
	int orderId=-1;

	/**
	 * 
	 * @param list
	 *            注意list的类型是List<ShoppingCarInfo>
	 */
	public void SubmitOrder(List<ShoppingCarInfo> list) {
		// 增加一条OrderInfo信息
		orderInfoDao.insertOrderInfo(0);
		orderId=orderInfoDao.countRows();
		for (ShoppingCarInfo shoppingCarInfo : list) {
			orderDetailDao.insertOrderDetail(orderId,
					shoppingCarInfo.getBookId(),
					shoppingCarInfo.getOrderQuantity());
		}
	}

}
