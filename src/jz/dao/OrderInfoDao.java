package jz.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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

	//插入OrderInfo
	public int insertOrderInfo(double orderDiscount) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "insert into OrderInfo(orderTime,orderDiscount)"
					+ "values(Current_Timestamp,?)";// 插入语句
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, orderDiscount);
			int ret = ps.executeUpdate();// 执行PreparedStatement
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DbUtils.close(rs, ps, conn);
		}
	}

	public static void main(String[] args) {
		OrderInfoDao dao = new OrderInfoDao();
		/*
		 * OrderInfo[] infos=dao.findAllOrderInfos(); for(OrderInfo info:infos){
		 * System.out.println(info.getOrderTime()+"  "+info.getOrderDiscount());
		 * }
		 */

		System.out.println(dao.insertOrderInfo(0.8));
	}
}
