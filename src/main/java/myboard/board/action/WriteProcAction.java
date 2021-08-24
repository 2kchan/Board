package myboard.board.action;

import static common.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.LoginManager;
import common.MessageMove;
import common.Validator;
import myboard.board.service.BoardService;
import vo.*;

import static common.Constants.*;

public class WriteProcAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
		HttpSession session = request.getSession();
		 LoginManager lm = LoginManager.getInstanc();
		 String mber_seq = lm.getMemberSequence(session);
		if (mber_seq == null) {
			MessageMove mm = new MessageMove();
			mm.printMove(response, LOGIN_NEEDED_SERVICE,"/member/login");
			
			return null;
		}
		
		// 데이터 정보 받아오기
		String sj = request.getParameter("sj");
		String cntnt = request.getParameter("cntnt");
		
		// 제목,내용 유효성 검사
		Validator validator = new Validator();
		if (!validator.isValidatedData(sj,BOARD_REGEXP_SJ) || validator.isEmpty(cntnt)) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// DB 연결, 삽입 (VO)
		BoardVo boardVo = new BoardVo();
		boardVo.setMber_seq(Integer.parseInt(mber_seq));
		boardVo.setSj(sj);
		boardVo.setCntnt(cntnt);
		
		BoardService svc = new BoardService();
		boolean isSuccess = svc.writeArticle(boardVo);
		if (!isSuccess) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CANNOT_WRITE_ARTICLE);
			return null;
		}
		// 경로세팅
		ActionForward forward = new ActionForward();
		forward.setPath("/board/list?pn=1&sf=0&sk=&sort=1");
		forward.setRedirect(true);

		return forward;
	}
}
