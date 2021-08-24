package myboard.member.action;

import static common.Constants.CANNOT_LEAVE;
import static common.Constants.CHECK_PASSWORD;
import static common.Constants.LOGIN_NEEDED_SERVICE;
import static common.Constants.MEMBER_REGEXP_PWD;
import static common.Constants.WRONG_ACCESS;

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

public class ModifyPwdResultAction implements Action {
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
		
		// 데이터 파라미터로 받아오기
		String oldpwd = request.getParameter("oldpwd");
		String pwd = request.getParameter("pwd");
		String confirmPwd = request.getParameter("confirmPwd");
		
		// 데이터 유효성 검사
		Validator validator = new Validator();
		if (!validator.isValidatedData(pwd, MEMBER_REGEXP_PWD) 
				|| !validator.isValidatedData(pwd, MEMBER_REGEXP_PWD)
				|| confirmPwd == null || !confirmPwd.equals(pwd)) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// 데이터베이스 정보와 비밀번호 비교
		MemberService svc = new MemberService();
		MemberVo memberVo = svc.getMemberInfo(Integer.parseInt(mber_seq));
		if (memberVo == null || !BCrypt.checkpw(oldpwd, memberVo.getPwd())) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CHECK_PASSWORD);
			return null;
		}
		
		// MemberVo 세팅
		memberVo.setMber_seq(Integer.parseInt(mber_seq));
		memberVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		
		// mber_seq에 해당하는 회원 정보에 pwd을 변경
		boolean isSuccess = svc.getModifyPwd(memberVo);
		if(!isSuccess) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CANNOT_LEAVE);
			return null;
		}	
		
		//로그아웃 처리
		lm.removeSession(lm.getMemberSequence(session));
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/views/member/modiftPwdResult.jsp");
		
		return forward;
	}

}
