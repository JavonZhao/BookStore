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

import jz.util.JsonUtils;

@WebServlet("/servlet/CalculateTotalPriceServlet")
public class CalculateTotalPriceServlet extends HttpServlet {

	// 折扣枚举类
	private enum Discount {
		DISCOUNT1(3), DISCOUNT2(8);

		private int nCode;

		private Discount(int _nCode) {
			this.nCode = _nCode;
		}

		@Override
		public String toString() {
			return String.valueOf(this.nCode);
		}
	};

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// 读取req传来的json数据
		StringBuffer jsonBuffer = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			System.out.println("getReaderchenggong");
			while ((line = reader.readLine()) != null) {
				jsonBuffer.append(line); // TODO jsonBuffer竟然为空
				System.out.println("增加一行数据");
			}

		} catch (Exception e) {
			jsonBuffer = null;
		}
		System.out.println("传来的json价格数据" + jsonBuffer);
		// 获取jsonString
		String jsonString = jsonBuffer.toString();
		System.out.println(jsonString);

		// 计算各种价格
		int totalPrice = 0;
		int discount = 0;
		int finalPrice = 0;

		/*List<Object> priceList = new ArrayList<Object>();
		priceList = JsonUtils.jsonToList(jsonString, String.class); // TODO 这里不能转换成jsonarray
		
		for (Object price : priceList) {
			totalPrice += Integer.parseInt((String) price);
		}
		if (totalPrice >= 50 && totalPrice < 100) {
			discount = Integer.parseInt(Discount.DISCOUNT1.toString());
		} else if (totalPrice >= 100) {
			discount = Integer.parseInt(Discount.DISCOUNT2.toString());
		} else {
			discount = 0;
		}
		finalPrice = totalPrice - discount;*/
		
		int[] separatedPriceArr=(int[]) JsonUtils.jsonToObject(jsonString, int[].class);
		for(int i=0;i<separatedPriceArr.length;i++){
			totalPrice+=separatedPriceArr[i];
		}
		if (totalPrice >= 50 && totalPrice < 100) {
			discount = Integer.parseInt(Discount.DISCOUNT1.toString());
		} else if (totalPrice >= 100) {
			discount = Integer.parseInt(Discount.DISCOUNT2.toString());
		} else {
			discount = 0;
		}
		finalPrice = totalPrice - discount;

		// 各种价格 → json数据 → 发送
		int[] priceArr = { totalPrice, discount, finalPrice };
		String json_back = JsonUtils.objectToJson(priceArr);
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(json_back);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	public static void main(String[] args) {
		int[] priceArr = { 3, 5, 6 };
		String json_back = JsonUtils.objectToJson(priceArr);
		System.out.println(json_back);
	}
}
