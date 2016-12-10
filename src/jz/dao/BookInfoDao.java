package jz.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jz.model.BookInfo;
import jz.util.DbUtils;

public class BookInfoDao {

	// ROMapping
	public BookInfo[] roMapping(ResultSet rs) {
		List<BookInfo> books = new ArrayList<BookInfo>();
		try {
			while (rs.next()) {
				BookInfo bookInfo = new BookInfo(rs.getInt("bookId"),
						rs.getString("bookName"), rs.getDate("publishDate"),
						rs.getDouble("bookPrice"));
				books.add(bookInfo);
			}
			return books.toArray(new BookInfo[books.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new BookInfo[0];
		}
	}

	// 查找所有图书信息
	public BookInfo[] findAllBookInfos() {
		Connection conn = DbUtils.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from BookInfo");
			BookInfo[] array = this.roMapping(rs);
			return array;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new BookInfo[0];
		} finally {
			DbUtils.close(rs, st, conn);
		}
	}

	// 根据 bookId 查找图书
	public BookInfo[] findBookInfosByBookId(int bookId) {
		Connection conn = DbUtils.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			String sql = "select * from BookInfo where bookId=" + bookId;
			rs = st.executeQuery(sql);
			BookInfo[] array = this.roMapping(rs);
			if (array.length <= 0) {
				return null;
			} else {
				return array;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DbUtils.close(rs, st, conn);
		}
	}

	// 根据 bookName 查找图书
	public BookInfo[] findAllBookInfosByBookName(String bookName) {
		Connection conn = DbUtils.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			String sql = "select * from BookInfo where bookName like '%"
					+ bookName + "%'";
			rs = st.executeQuery(sql);
			BookInfo[] array = this.roMapping(rs);
			return array;
		} catch (Exception e) {
			e.printStackTrace();
			return new BookInfo[0];
		} finally {
			DbUtils.close(rs, st, conn);
		}
	}

	// 分页情况下 根据 书名 查找图书
	public BookInfo[] findPagedBookInfosByBookName(int pageIndex,
			String bookName) {
		Connection conn = DbUtils.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			
			String sqlString = "select top " + DbUtils.PAGE_SIZE
					+ " * from BookInfo where";
			if (bookName == null || bookName.equals("") == true)
				sqlString = sqlString + " bookId not in";
			else
				sqlString = sqlString + "bookName like'" + bookName
						+ "%' and bookId not in";
			
			String subSqlString = " (select top " + pageIndex * DbUtils.PAGE_SIZE
					+ "bookId from BookInfo";
			if (bookName == null || bookName.equals("") == true)
				subSqlString = subSqlString + " order by bookId)";
			else
				subSqlString = subSqlString + " where bookName like'" + bookName
						+ "%' order by bookId)";
			sqlString = sqlString + subSqlString + " order by bookId";
			
			System.out.println(sqlString);
			rs = st.executeQuery(sqlString);
			BookInfo[] array = this.roMapping(rs);
			return array;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new BookInfo[0];
		} finally {
			DbUtils.close(rs, st, conn);
		}
	}
	
	public int countRowsByBookName(String bookName){
		Connection conn=DbUtils.getConnection();
		Statement st=null;
		ResultSet rs=null;
		try {
			st=conn.createStatement();
			String sql="select count(*) as countRows from BookInfo ";
			if(!(bookName==null||bookName.equals("")==true)){
				sql=sql+"where bookName like'"+bookName+"%'";
			}
			rs=st.executeQuery(sql);
			if(rs.next())
				return rs.getInt("countRows");
			else
				return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			DbUtils.close(rs, st, conn);
		}
	}

	public static void main(String[] args) {
		BookInfoDao dao = new BookInfoDao();
		//BookInfo[] books = dao.findAllBookInfos();
		/*BookInfo[] books=dao.findPagedBookInfosByBookName(1, null);
		System.out.println("kaishi");
		for (BookInfo book : books) {
			System.out.println(book.getBookId() + " " + book.getBookName());
		}*/
		System.out.println(dao.countRowsByBookName(null));
	}

}
