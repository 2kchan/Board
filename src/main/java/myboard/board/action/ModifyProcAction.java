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

public class ModifyProcAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstanc();
		String mber_seq = lm.getMemberSequence(session);
		if (mber_seq == null) {
			String pn = request.getParameter("pn");
			String sf = request.getParameter("sf");
			String sk = URLEncoder.encode(request.getParameter("sk"), "UTF-8");
			String sort = request.getParameter("sort");
			String bseq = request.getParameter("bseq");
			
			String requestUri = request.getRequestURI();
			requestUri += "?pn=" + pn + "&sf=" + sf + "&sk=" + sk + "&sort" + sort + "&bseq=" + bseq;
			session.setAttribute("targetURI", requestUri);
			MessageMove mm = new MessageMove();
			mm.printMove(response, LOGIN_NEEDED_SERVICE, "/member/login");
			return null;
		}
		
		// form데이터 받아오기
		String board_seq = request.getParameter("bseq");
		String sj = request.getParameter("sj");
		String cntnt = request.getParameter("cntnt");

		// 유효성 검사
		Validator validator = new Validator();
		if (!validator.isValidatedData(sj,BOARD_REGEXP_SJ) || validator.isEmpty(cntnt)
				|| !validator.isValidatedData(board_seq,MEMBER_REGEXP_NUMBER) || Integer.parseInt(board_seq) < 1) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// Vo세팅(board_seq, mber_seq, sj, cntnt)
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_seq(Integer.parseInt(board_seq));
		boardVo.setMber_seq(Integer.parseInt(mber_seq));
		boardVo.setSj(sj);
		boardVo.setCntnt(cntnt);
		
		// 데이터베이스에 업데이트
		BoardService svc = new BoardService();
		boolean isSuccess = svc.modifyProc(boardVo);
		if (!isSuccess) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CANNOT_MODIFY_ARTICLE);
			return null;
		}
		
		// 경로설정
		String pn = request.getParameter("pn");
		String sf = request.getParameter("sf");
		String sk = URLEncoder.encode(request.getParameter("sk"), "UTF-8");;
		String sort = request.getParameter("sort");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/board/detail?pn="+pn+"&sf="+sf+"&sk="+sk+"&sort="+sort+"&bseq="+board_seq);
		
		return forward;
	}
}
