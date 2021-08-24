package myboard.board.dao;

import static common.jdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.PageManager;
import vo.BoardVo;
import vo.MemberVo;

public class BoardDao {
	private Connection con;

	private BoardDao() {

	}

	private static class LazyHolder {
		private static final BoardDao INSTANCE = new BoardDao();
	}

	public static BoardDao getInstance() {
		return BoardDao.LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int getArticleCount(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("select count(ibt.board_seq) from inf_board_tb as ibt where del_fl=?" + query);
			pstmt.setBoolean(1, false);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return count;
	}
	
	public ArrayList<BoardVo> getArticleList(PageManager pm, String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement
					("select ibt.board_seq"
							+ ", ibt.sj"
							+ ", ibt.cnt"
							+ ", date_format(ibt.dttm, '%Y-%m-%d %H:%i') as dttm"
							+ ", imt.id as mber_id"
							+ " from inf_board_tb as ibt"
							+ " inner join inf_mber_tb as imt on ibt.mber_seq=imt.mber_seq"
							+ " where ibt.del_fl=?" + query + " limit ?,?");
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, pm.getStartArticleNumber());
			pstmt.setInt(3, pm.getArticleCountPerPage());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setBoard_seq(rs.getInt("board_seq"));
				vo.setSj(rs.getString("sj"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setDttm(rs.getString("dttm"));
				vo.setMber_id(rs.getString("mber_id"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int writeArticle(BoardVo boardVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into inf_board_tb(sj,cntnt,mber_seq) values(?,?,?)");
			pstmt.setString(1, boardVo.getSj());
			pstmt.setString(2, boardVo.getCntnt());
			pstmt.setInt(3, boardVo.getMber_seq());
			count = pstmt.executeUpdate();	// insert delete update
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}
		return count;
	}
	
	public BoardVo getArticleDetail(int board_seq) { // 글번호,작성자아이디,제목,내용,조회수,작성일시
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo boardVo = null;

		try {
			pstmt = con.prepareStatement
					("select ibt.board_seq"
					+ ", ibt.mber_seq"		
					+ ", imt.id as mber_id"
					+ ", ibt.sj"
					+ ", ibt.cntnt"
					+ ", ibt.cnt"
					+ ", date_format(ibt.dttm, '%Y-%m-%d %H:%i') as dttm"
					+ " from inf_board_tb as ibt"
					+ " inner join inf_mber_tb as imt on ibt.mber_seq=imt.mber_seq"
					+ " where ibt.board_seq=? and ibt.del_fl=?"); 
			pstmt.setInt(1, board_seq);
			pstmt.setBoolean(2, false);
			rs = pstmt.executeQuery();	//select
			while(rs.next()) {
				boardVo = new BoardVo();
				boardVo.setBoard_seq(rs.getInt("board_seq"));
				boardVo.setMber_seq(rs.getInt("mber_seq"));
				boardVo.setMber_id(rs.getString("mber_id"));
				boardVo.setSj(rs.getString("sj"));
				boardVo.setCntnt(rs.getString("cntnt"));
				boardVo.setCnt(rs.getInt("cnt"));
				boardVo.setDttm(rs.getString("dttm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardVo;
	}
	
	public int increaseArticleCount(int bseq) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_board_tb set cnt=cnt+1 where board_seq=? and del_fl=?");
			pstmt.setInt(1, bseq);
			pstmt.setBoolean(2, false);
			count = pstmt.executeUpdate();	// insert delete update
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}
		return count;
	}
	
	public BoardVo getArticleDetail(BoardVo vo) { // 글번호,제목,내용
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo boardVo = null;

		try {
			pstmt = con.prepareStatement
					("select board_seq,sj,cntnt from inf_board_tb where board_seq=? and mber_seq=? and del_fl=?"); 
			pstmt.setInt(1, vo.getBoard_seq());
			pstmt.setInt(2, vo.getMber_seq());
			pstmt.setBoolean(3, false);
			rs = pstmt.executeQuery();	//select
			while(rs.next()) {
				boardVo = new BoardVo();
				boardVo.setBoard_seq(rs.getInt("board_seq"));
				boardVo.setSj(rs.getString("sj"));
				boardVo.setCntnt(rs.getString("cntnt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardVo;
	}
	
	public int modifyProc(BoardVo boardVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_board_tb set sj=?,cntnt=? where board_seq=? and mber_seq=? and del_fl=?");
			pstmt.setString(1, boardVo.getSj());
			pstmt.setString(2, boardVo.getCntnt());
			pstmt.setInt(3, boardVo.getBoard_seq());
			pstmt.setInt(4, boardVo.getMber_seq());
			pstmt.setBoolean(5, false);
			count = pstmt.executeUpdate();	// insert delete update
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}
		return count;
	}

	public int deleteProc(BoardVo boardVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_board_tb set del_fl=? where board_seq=? and mber_seq=?");
			pstmt.setBoolean(1, boardVo.isDel_fl());
			pstmt.setInt(2, boardVo.getBoard_seq());
			pstmt.setInt(3, boardVo.getMber_seq());
			count = pstmt.executeUpdate();	// insert delete update
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}
		return count;
	}
	
	
}











