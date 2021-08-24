package myboard.board.action;

import static common.Constants.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import common.MessageMove;
import common.PageManager;
import common.Validator;
import myboard.board.service.BoardService;
import myboard.member.service.MemberService;
import vo.BoardVo;

public class ListAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 페이지 정보 데이터 로드
		Validator validator = new Validator();
		
		// 페이지 번호 데이터 로드 및 유효성 검사
		String pn = request.getParameter("pn");
		if (!validator.isValidatedData(pn, MEMBER_REGEXP_NUMBER) || Integer.parseInt(pn) < 1) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// 검색 필터 데이터 로드 및 유효성 검사
		String sf = request.getParameter("sf");
		if (!validator.isValidatedData(sf, MEMBER_REGEXP_NUMBER)) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// 검색 키워드 데이터 로드 및 유효성 검사
		String sk = request.getParameter("sk");
		if (sk == null || (!sk.equals("") && !validator.isValidatedData(sk, MEMBER_REGEXP_SK))) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// 정렬 데이터 로드 및 유효성 검사
		String sort = request.getParameter("sort");
		if (!validator.isValidatedData(sort, MEMBER_REGEXP_NUMBER) || Integer.parseInt(sort) < 1) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// 쿼리 조합
		String query = makeQuery(sf, sk, sort);
		
		BoardService svc = new BoardService();
		// 글 총 개수 로드
		int articleCount = svc.getArticleCount(query);
		
		// 페이지 계산하는 클래스의 객체 생성(생성 시 페이지 정보 다 계산)
		PageManager pm = new PageManager(Integer.parseInt(pn), articleCount);
		
		// 페이지에 해당하는 글 목록 로드
		ArrayList<BoardVo> list = svc.getArticleList(pm, query);
		
		// request 데이터 세팅
		request.setAttribute("list", list);
		request.setAttribute("pm", pm);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/list.jsp");
		return forward;
	}
	
	private String makeQuery(String sf, String sk, String sort) {
		String query = "";
		if (!sk.equals("")) {
			sk = "'%" + sk + "%'";
			if(sf.equals("1")) {
				query = " and ibt.sj like " + sk;
			} else if (sf.equals("2")) {
				query = " and ibt.cntnt like " + sk;
			} else {
				query = " and (ibt.sj like " + sk + " or ibt.cntnt like " + sk + ")";
			}
		}
		
		if (sort.equals("2")) {
			query += " order by ibt.board_seq asc";
		} else {
			query += " order by ibt.board_seq desc";
		}
		
		return query;
	}
}

























