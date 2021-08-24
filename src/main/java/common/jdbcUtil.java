package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class jdbcUtil {
	public static Connection getConnection() {
		Connection con = null;

		try {
			// 초기 컨텍스트 파일 로드를 위한 초기화
			Context initCtx = new InitialContext();

			// context.xml 파일 불러와서 envCtx에 저장
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			// envCtx에서 name이 "jdbc/MariaDB" <Resource/>내용 로드하여
			// DataSource 객체에 저장
			DataSource ds = (DataSource) envCtx.lookup("jdbc/MariaDB");

			// DataSource 객체의 정보로 Connection 설정
			con = ds.getConnection();

			// Connection의 오토 커밋 off
			con.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void commit(Connection con) {
		if (con != null) {
			try {
				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
