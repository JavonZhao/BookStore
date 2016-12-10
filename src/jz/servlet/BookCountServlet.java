package jz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jz.dao.BookInfoDao;

@WebServlet("/servlet/BooksCountServlet")
public class BookCountServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String bookName=req.getParameter("bookName");
		BookInfoDao dao=new BookInfoDao();
		int count=dao.countRowsByBookName(bookName);
		resp.setCharacterEncoding("utf-8");
		System.out.println("countRowsByBookName="+count);
		String countString=String.valueOf(count);
		resp.getWriter().write(countString);
	}
	
}
