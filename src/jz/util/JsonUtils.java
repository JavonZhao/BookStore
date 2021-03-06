package jz.util;

import java.util.ArrayList;
import java.util.List;

import jz.model.ShoppingCarInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonUtils {

	private static Gson gson = null;

	private JsonUtils() {
	}

	static {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
				.create();
	}

	// Object→Json
	public static String objectToJson(Object obj) {
		String str = gson.toJson(obj, obj.getClass());
		return str;
	}

	// Json→Object
	public static Object jsonToObject(String str, Class cls) {
		Object obj = gson.fromJson(str, cls);
		return obj;
	}

	// json→List
	/**
	 * 
	 * @param str
	 * @param cls
	 * @return List<Object> list
	 */
	public static List<Object> jsonToList(String str, Class cls) {
		gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(str); // 将json字符串转换成JsonElement
		// System.out.println(jsonElement);// ###
		JsonArray jsonArray = jsonElement.getAsJsonArray(); // 将JsonElement转换成JsonArray
		// System.out.println(jsonArray.get(0));// ###

		List<Object> list = new ArrayList<Object>();

		for (JsonElement ele : jsonArray) {
			str = ele.toString(); // JsonElement转换成String
			Object obj = jsonToObject(str, cls);
			list.add(obj);
		}
		return list;
	}

	public static void main(String[] args) {
		/*
		 * String str =
		 * "[{'bookId':1,'bookName':'Java语言程序','bookPrice':27,'orderQuantity':2,'totalPrice':54},{'bookId':2,'bookName':'Java语言程序01','bookPrice':27,'orderQuantity':2,'totalPrice':54}]"
		 * ; List<Object> infos = jsonToList(str, ShoppingCarInfo.class);
		 * ShoppingCarInfo info = (ShoppingCarInfo) infos.get(1);
		 * System.out.println(info.getBookName());
		 */
		/*int TotalPrice = -1;
		String jsonString = "['54','54']";
		List<Object> priceList = new ArrayList<Object>();
		priceList = JsonUtils.jsonToList(jsonString, String.class);
		for (Object price : priceList) {
			TotalPrice += Integer.parseInt((String) price);
		}
		System.out.println(TotalPrice);*/
		
		int[] priceArr={3,5,6};
		String json_back=JsonUtils.objectToJson(priceArr);
		System.out.println(json_back);
	}
}
