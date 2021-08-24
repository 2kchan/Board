package myboard.member.action;

import static common.Constants.WRONG_ACCESS;
import static common.Constants.CHECK_LOGIN_INFO;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.BCrypt;
import common.LoginManager;
import common.MessageMove;
import common.Validator;
import myboard.member.service.MemberService;
import vo.MemberVo;

public class LoginProcAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 여부 확인
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstanc();
		String mber_seq = lm.getMemberSequence(session);

		// 로그인 되어 있으면 홈으로
		ActionForward forward = new ActionForward();
		if (mber_seq != null) {
			forward.setPath("/");
			forward.setRedirect(true);

			return forward;
		}

		// 전송된 데이터 로드
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		// 데이터 검사
		Validator validator = new Validator();
		if (validator.isEmpty(id) || validator.isEmpty(pwd)) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}

		// 아이디로 데이터베이스에서 회원정보
		MemberService svc = new MemberService();
		MemberVo memberVo = svc.getLoginInfo(id);

		// 단, 회원정보가 없거나,비밀번호가 다르면 '아이디, 비밀번호 확인해 주세요.' 메세지 표시 후 백
		if (memberVo == null || !BCrypt.checkpw(pwd, memberVo.getPwd())) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CHECK_LOGIN_INFO);
			return null;
		}

		// 로그인 처리
		lm.setSession(session, memberVo.getMber_seq());

		// 홈으로 이동
		String targetUri = (String) session.getAttribute("targetUri");
		if (targetUri != null) {
			forward.setPath(targetUri);
			session.removeAttribute("targetUri");
		} else {
			forward.setPath("/");
		}
		forward.setRedirect(true);

		return forward;
	}

}
