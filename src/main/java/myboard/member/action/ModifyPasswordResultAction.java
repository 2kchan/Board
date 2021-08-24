package myboard.member.action;

import static common.Constants.*;


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

public class ModifyPasswordResultAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부 확인하여 로그인 되어 있을 경우 오류 메세지 출력 후 홈으로 이동
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstanc();
		String mber_seq = lm.getMemberSequence(session);
		if (mber_seq != null) {
			MessageMove mm = new MessageMove();
			mm.printMove(response, WRONG_ACCESS, "/");

			return null;
		}

		// request 객체에서 parameter 데이터 로드
		String pwd = request.getParameter("pwd");
		String confirmPwd = request.getParameter("confirmPwd");

		// 데이터 유효성 검사
		Validator validator = new Validator();
		if (!validator.isValidatedData(pwd, MEMBER_REGEXP_PWD)
				|| confirmPwd == null || !confirmPwd.equals(pwd)) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}

		// 세션에서 seq 가져오기
		int seq = session.getAttribute("mber_seq") != null ? (int)session.getAttribute("mber_seq") : 0;
		if (seq == 0) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		session.removeAttribute("mber_seq");
		
		// MemberVo에 데이터 세팅
				// password 암호화
		MemberVo memberVo = new MemberVo();
		memberVo.setMber_seq(seq);
		memberVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		
		// 데이터베이스 업데이트
		MemberService svc = new MemberService();
		boolean isSuccess = svc.getModifyPasswordResult(memberVo);
		if(!isSuccess) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CANNOT_MODIFY_PASSWORD);
			return null;
		}
		
		// view 이동
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/modifyPasswordResult.jsp");

		return forward;
	}

}
