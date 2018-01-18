package pro05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookShopDao {

	public void insert(BookVo vo2) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "insert into bookshop values (seq_bookshop_id.nextval, ? , ? , ? ,?,?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo2.getTitle());
			pstmt.setString(2, vo2.getPubs());
			pstmt.setString(3, vo2.getPubDate());
			pstmt.setString(4, vo2.getAuthorName());
			pstmt.setString(5, vo2.getStateCode());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

	public List<BookVo> showListAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BookVo> bList = new ArrayList<>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "select id, title, pubs, pub_date, author_name, state_code" + " from bookshop ";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookVo vo2 = new BookVo();
				int bookId = rs.getInt("id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				String authorName = rs.getString("author_name");
				String stateCode = rs.getString("state_code");
				vo2.setId(bookId);
				vo2.setTitle(title);
				vo2.setPubs(pubs);
				vo2.setPubDate(pubDate);
				vo2.setAuthorName(authorName);
				if (stateCode.equals("0")) {
					vo2.setStateCode("대여중");
				} else {
					vo2.setStateCode("재고있음");
				}
				bList.add(vo2);

			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return bList;
	}

	public void rent(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "update bookshop set state_code=? where id=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, Integer.parseInt("0"));
			pstmt.setInt(2, num);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

}
