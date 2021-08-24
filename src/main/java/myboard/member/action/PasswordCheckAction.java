package myboard.member.action;

import static common.Constants.LOGIN_NEEDED_SERVICE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.LoginManager;
import common.MessageMove;

public class PasswordCheckAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부 확인
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstanc();
		String mber_seq = lm.getMemberSequence(session);
		if (mber_seq == null) {
			String requestUri = request.getRequestURI();
			session.setAttribute("targetUri", requestUri);
			MessageMove mm = new MessageMove();
			mm.printMove(response, LOGIN_NEEDED_SERVICE, "/member/login");

			return null;
		}
		
		// 뷰 이동
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/checkPasswordForm.jsp");
		return forward;
	}
}
