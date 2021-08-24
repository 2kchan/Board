package myboard.member.action;

import static common.Constants.WRONG_ACCESS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.LoginManager;
import common.MessageMove;

public class LoginAction implements Action{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session = request.getSession();
		 LoginManager lm = LoginManager.getInstanc();
		 String mber_seq = lm.getMemberSequence(session);
		 
		 // 로그인 되어 있으면 홈으로
		 // 안되어 있으면 로그인 폼 페이지로
		 ActionForward forward = new ActionForward();
		if (mber_seq != null) {
			forward.setPath("/");
			forward.setRedirect(true);
		} else {
			forward.setPath("/views/member/loginForm.jsp");
		}

		return forward;
	}

}
