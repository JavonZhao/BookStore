package jz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jz.dao.BookInfoDao;
import jz.model.BookInfo;
import jz.util.DbUtils;
import jz.util.JsonUtils;

@WebServlet("/servlet/BookListPagedServlet")
public class BookListPagedServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		String bookName = req.getParameter("bookName");
		int pageIndex = 0;
		try {
			pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
			System.out.println("pageIndex=" + pageIndex);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookInfoDao dao = new BookInfoDao();
		int count = dao.countRowsByBookName(bookName);
		int pageCount = 0;
		if (count % DbUtils.PAGE_SIZE == 0)
			pageCount = count / DbUtils.PAGE_SIZE;
		else
			pageCount = count / DbUtils.PAGE_SIZE + 1;
		if (pageIndex >= pageCount)
			pageIndex = pageCount - 1;

		// 计算分页后的图书对象
		BookInfo[] books = dao
				.findPagedBookInfosByBookName(pageIndex, bookName);
		for(BookInfo book:books){
			System.out.println(book.getBookName());
		}

		// 将对象数组转换为json字符串
		resp.setCharacterEncoding("utf-8");
		String strJson = JsonUtils.objectToJson(books);
		resp.getWriter().write(strJson);
		System.out.println("BookListPagedServlet运行成功");
	}

}
