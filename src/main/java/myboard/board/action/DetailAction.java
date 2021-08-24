package myboard.board.action;

import static common.Constants.CANNOT_WRITE_ARTICLE;
import static common.Constants.LOGIN_NEEDED_SERVICE;
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
import vo.BoardVo;

public class DetailAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 글번호,작성자아이디,제목,내용,조회수,작성일시

		// bseq 데이터 로드
		String board_seq = request.getParameter("bseq");

		// 유효성 검사
		Validator validator = new Validator();
		if (!validator.isValidatedData(board_seq, MEMBER_REGEXP_NUMBER) || Integer.parseInt(board_seq) < 1) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}

		// 조회수 +1
		BoardService svc = new BoardService();
		boolean isSuccess = svc.increaseArticleCount(Integer.parseInt(board_seq));
		if (!isSuccess) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CANNOT_WRITE_ARTICLE);
			return null;
		}

		// bseq 데이터로 글 정보(글번호, 회원seq, 회원아이디, 제목, 내용, 조회수, 작성자)
		BoardVo boardVo = svc.getArticleDetail(Integer.parseInt(board_seq));
		if (boardVo == null) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CANNOT_GET_ARTICLE);
			return null;
		}

		// request에 글정보 저장
		request.setAttribute("vo", boardVo);

		// 경로 설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/detail.jsp");
		return forward;
	}

}
