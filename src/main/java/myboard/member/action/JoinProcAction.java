package myboard.member.action;


import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.BCrypt;
import common.LoginManager;
import common.MessageMove;
import common.Validator;
import myboard.member.controller.MemberController;
import myboard.member.service.MemberService;
import vo.MemberVo;

import static common.Constants.*;


public class JoinProcAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그인 여부 확인하여 로그인 되어 있을 경우 오류 메세지 출력 후 홈으로 이동
		 HttpSession session = request.getSession();
		 LoginManager lm = LoginManager.getInstanc();
		 String mber_seq = lm.getMemberSequence(session);
		if (mber_seq != null) {
			MessageMove mm = new MessageMove();
			mm.printMove(response, WRONG_ACCESS,"/");
			
			return null;
		}
		
		// 폼에서 전송된 데이터 로드
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String confirmPwd = request.getParameter("confirmPwd");
		String nm = request.getParameter("nm");
		String moblphon = request.getParameter("moblphon");
		
		// 데이터 유효성 검사
		Validator validator = new Validator();
		if (!validator.isValidatedData(id, MEMBER_REGEXP_ID)
				|| !validator.isValidatedData(pwd, MEMBER_REGEXP_PWD)
				|| confirmPwd == null || !confirmPwd.equals(pwd)
				|| !validator.isValidatedData(nm, MEMBER_REGEXP_NAME)
				|| !validator.isValidatedData(moblphon, MEMBER_REGEXP_MOBILE)) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		//아이디 중복검사
		MemberService svc = new MemberService();
		int count = svc.getMemberCountById(id);
		if(count > 0 ) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// vo에 데이터 세팅
		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		memberVo.setNm(nm);
		memberVo.setMoblphon(moblphon);
		
		// DB에 저장
		boolean isSuccess = svc.joinMember(memberVo);
		
		// DB에 저장 후 결과 전달 받아서 실패일 경우 메세지 출력 후 뒤로 가기
		
		if (!isSuccess) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, FAIL_JOIN);
			return null;
		}
		
		// 성공시 아이디를 세션 저장
		session.setAttribute("joinId", id);
		
		// 이동할 경로 세팅 -> 리다이렉트 방식으로 회원가입 결과페이지로 이동할 예정
		ActionForward forward = new ActionForward();
		
		// 리다이렉트 방식, /member/joinResult
		forward.setRedirect(true);
		forward.setPath("/member/joinResult");
		
		return forward;

		

	}
}
