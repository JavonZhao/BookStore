package jz.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jz.model.OrderInfo;
import jz.util.DbUtils;

public class OrderInfoDao {

	// ROMapping
	public OrderInfo[] roMapping(ResultSet rs) {
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		try {
			while (rs.next()) {
				OrderInfo orderInfo = new OrderInfo(rs.getInt("orderId"),
						rs.getDate("orderTime"), rs.getDouble("orderDiscount"));
				orderInfos.add(orderInfo);
			}
			return orderInfos.toArray(new OrderInfo[orderInfos.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OrderInfo[0];
		}
	}

	// 查找所有 Order 信息
	public OrderInfo[] findAllOrderInfos() {
		Connection conn = DbUtils.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			String sql = "select * from OrderInfo";
			rs = st.executeQuery(sql);
			OrderInfo[] array = this.roMapping(rs);
			return array;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OrderInfo[0];
		} finally {
			DbUtils.close(rs, st, conn);
		}
	}
	
	public static void main(String[] args) {
		OrderInfoDao dao=new OrderInfoDao();
		OrderInfo[] infos=dao.findAllOrderInfos();
		for(OrderInfo info:infos){
			System.out.println(info.getOrderTime()+"  "+info.getOrderDiscount());
		}
	}

}
