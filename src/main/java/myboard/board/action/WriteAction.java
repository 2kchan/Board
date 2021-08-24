package myboard.board.action;

import static common.Constants.LOGIN_NEEDED_SERVICE;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.LoginManager;
import common.MessageMove;

public class WriteAction implements Action {
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
			
			String requestUri = request.getRequestURI();
			requestUri += "?pn=" + pn + "&sf=" + sf + "&sk=" + sk + "&sort" + sort;
			session.setAttribute("targetURI", requestUri);
			MessageMove mm = new MessageMove();
			mm.printMove(response, LOGIN_NEEDED_SERVICE, "/member/login");
			return null;
		}

		// 경로세팅
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/writeForm.jsp");
		return forward;

	}
}
