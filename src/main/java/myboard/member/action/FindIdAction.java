package myboard.member.action;

import static common.Constants.WRONG_ACCESS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.LoginManager;
import common.MessageMove;

public class FindIdAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 검사 (로그인 되어있으면 문제)
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstanc();
		String mber_seq = lm.getMemberSequence(session);
		if (mber_seq != null) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);

			return null;
		}
		// view 이동
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/findIdForm.jsp");
		return forward;
	}

}
