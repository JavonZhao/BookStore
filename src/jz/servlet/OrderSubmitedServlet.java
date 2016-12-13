package jz.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jz.model.ShoppingCarInfo;
import jz.util.JsonUtils;

@WebServlet("/servlet/OrderSubmitedServlet")
public class OrderSubmitedServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		StringBuffer jsonBuffer = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jsonBuffer.append(line);
		} catch (Exception e) {
			jsonBuffer = null;
		}

		// 获取jsonString
		String jsonString = jsonBuffer.toString();
		System.out.println(jsonString);

		// 提取List<ShoppingCarInfo> 进行OrderService
		// 需要类型转换
		// TODO 工作标记点
		List<Object> list = JsonUtils.jsonToList(jsonString,
				ShoppingCarInfo.class);
		List<ShoppingCarInfo> shoppingCarInfos = new ArrayList<ShoppingCarInfo>();
		for (Object obj : list) {
			shoppingCarInfos.add((ShoppingCarInfo) obj);
		}
	}
	
	/*public static void main(String[] args) {
		String json = "[{'bookId':1,'bookName':'Java语言程序','bookPrice':27,'orderQuantity':2,'totalPrice':54},{'bookId':2,'bookName':'Java语言程序01','bookPrice':27,'orderQuantity':2,'totalPrice':54}]";
		List<Object> list = JsonUtils.jsonToList(json,
				ShoppingCarInfo.class);
		List<ShoppingCarInfo> shoppingCarInfos = new ArrayList<ShoppingCarInfo>();
		for (Object obj : list) {
			shoppingCarInfos.add((ShoppingCarInfo) obj);
		}
		System.out.println(shoppingCarInfos.get(0).getBookName());
	}*/
}
