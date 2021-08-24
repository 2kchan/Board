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

public class LeaveResultAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부 확인 - 로그인 안되어있으면 메세지 출력
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstanc();
		String mber_seq = lm.getMemberSequence(session);
		if (mber_seq == null) {
			String requestUri = request.getRequestURI();
			session.setAttribute("targetURI", requestUri);
			MessageMove mm = new MessageMove();
			mm.printMove(response, LOGIN_NEEDED_SERVICE, "/member/login");

			return null;
		}
		// 파라미터로 데이터 가져오기
		String pwd = request.getParameter("pwd");
		
		// 유효성 검사
		Validator validator = new Validator();
		if (validator.isEmpty(pwd)) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);

			return null;
		}
		
		// mber_seq로 부터 회원정보 로드
		// 비밀정보 확인
		MemberService svc = new MemberService();
		MemberVo memberVo = svc.getMemberInfo(Integer.parseInt(mber_seq));
		if (memberVo == null || !BCrypt.checkpw(pwd, memberVo.getPwd())) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CHECK_PASSWORD);
			return null;
		}
		
		// mber_seq에 해당하는 회원 정보에 del_fl을 true로 업데이트
		boolean isSuccess = svc.getLeaveResult(Integer.parseInt(mber_seq));
		if(!isSuccess) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CANNOT_LEAVE);
			return null;
		}		
		
		//로그아웃 처리
		lm.removeSession(lm.getMemberSequence(session));
		
		//경로 설정
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/");

		return forward;
	}
}
