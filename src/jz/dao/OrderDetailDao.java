package jz.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jz.model.BookInfo;
import jz.model.OrderDetail;
import jz.util.DbUtils;

public class OrderDetailDao {

	// ROMapping
	public OrderDetail[] roMapping(ResultSet rs) {
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		try {
			while (rs.next()) {
				OrderDetail OrderDetail = new OrderDetail(
						rs.getInt("detailId"), rs.getInt("orderId"),
						rs.getInt("bookId"), rs.getInt("orderQuantity"));
				orderDetails.add(OrderDetail);
			}
			return orderDetails.toArray(new OrderDetail[orderDetails.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OrderDetail[0];
		}
	}
	
	// 查找所有 orderDetails 信息
		public OrderDetail[] findAllOrderDetails() {
			Connection conn = DbUtils.getConnection();
			Statement st = null;
			ResultSet rs = null;
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from OrderDetail");
				OrderDetail[] array = this.roMapping(rs);
				return array;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new OrderDetail[0];
			} finally {
				DbUtils.close(rs, st, conn);
			}
		}
		
		public static void main(String[] args) {
			OrderDetailDao dao=new OrderDetailDao();
			OrderDetail[] details=dao.findAllOrderDetails();
			for(OrderDetail detail:details){
				System.out.println(detail.getDetailId()+"  "+detail.getBookId()+"  "+detail.getOrderQantity());
			}
		}
}
