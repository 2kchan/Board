package myboard.board.service;

import static common.jdbcUtil.close;
import static common.jdbcUtil.commit;
import static common.jdbcUtil.getConnection;
import static common.jdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import common.PageManager;
import myboard.board.dao.BoardDao;
import myboard.member.dao.MemberDao;
import vo.BoardVo;
import vo.MemberVo;

public class BoardService {
	public int getArticleCount(String query) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getArticleCount(query);
		close(con);
		return count;
	}
	public ArrayList<BoardVo> getArticleList(PageManager pm, String query) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<BoardVo> list = dao.getArticleList(pm, query);
		close(con);
		return list;
	}

	public boolean writeArticle(BoardVo boardVo) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.writeArticle(boardVo);
		boolean isSuccess = false;
		
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	public BoardVo getArticleDetail(int board_seq) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		BoardVo boardVo = dao.getArticleDetail(board_seq);
		close(con);
		return boardVo;
	}
	
	public boolean increaseArticleCount(int board_seq) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.increaseArticleCount(board_seq);
		boolean isSuccess = false;
		
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	} 
	
	public BoardVo getArticleDetail(BoardVo boardVo) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boardVo = dao.getArticleDetail(boardVo);
		close(con);
		return boardVo;
	}
	
	public boolean modifyProc (BoardVo boardVo) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.modifyProc(boardVo);
		boolean isSuccess = false;
		
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	public boolean deleteProc (BoardVo boardVo) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.deleteProc(boardVo);
		boolean isSuccess = false;
		
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	
}
