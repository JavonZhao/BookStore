package jz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jz.dao.BookInfoDao;
import jz.model.BookInfo;
import jz.util.JsonUtils;

@WebServlet("/servlet/BookChosenServlet")
public class BookChosenServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int bookId = Integer.parseInt(req.getParameter("bookId"));
		BookInfoDao dao = new BookInfoDao();
		BookInfo[] bookInfo = dao.findBookInfosByBookId(bookId);
		resp.setCharacterEncoding("utf-8");
		String strJson = JsonUtils.objectToJson(bookInfo);
		resp.getWriter().write(strJson);
	}
}
