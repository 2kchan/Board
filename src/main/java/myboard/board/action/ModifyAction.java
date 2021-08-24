package myboard.board.action;

import static common.Constants.*;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.LoginManager;
import common.MessageMove;
import common.Validator;
import myboard.board.service.BoardService;
import vo.BoardVo;

public class ModifyAction implements Action{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 유무확인
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstanc();
		String mber_seq = lm.getMemberSequence(session);
		if (mber_seq == null) {
			String pn = request.getParameter("pn");
			String sf = request.getParameter("sf");
			String sk = URLEncoder.encode(request.getParameter("sk"), "UTF-8");
			String sort = request.getParameter("sort");
			String board_seq = request.getParameter("bseq");
			
			String requestUri = request.getRequestURI();
			requestUri += "?pn=" + pn + "&sf=" + sf + "&sk=" + sk + "&sort" + sort + "&bseq=" + board_seq;
			session.setAttribute("targetURI", requestUri);
			MessageMove mm = new MessageMove();
			mm.printMove(response, LOGIN_NEEDED_SERVICE, "/member/login");
			return null;
		}
		
		// boardseq 받아오기
		String board_seq = request.getParameter("bseq");
		
		// 유효성 검사 (bseq -> null, 빈값, 숫자인지, 0보다 큰지
		Validator validator = new Validator();
		if (!validator.isValidatedData(board_seq,MEMBER_REGEXP_NUMBER) || Integer.parseInt(board_seq) < 1) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// boardseq,mberseq vo에 데이터 담아서 이동
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_seq(Integer.parseInt(board_seq));
		boardVo.setMber_seq(Integer.parseInt(mber_seq));
		
		// boardVo를 이용하여 글 상세 정보(board_seq, sj, cntnt)
		BoardService svc = new BoardService();
		boardVo=svc.getArticleDetail(boardVo);
		if (boardVo == null) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CANNOT_GET_ARTICLE);
			return null;
		}
			
		// 글정보 저장
		request.setAttribute("vo", boardVo);
		
		// modifyform 이동
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/modifyForm.jsp");
		return forward;
	}
}
