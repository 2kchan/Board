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

public class ModifyAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstanc();
		String mber_seq = lm.getMemberSequence(session);
		if (mber_seq == null) {
			session.setAttribute("targetURI", "/member/modify/check/password");
			MessageMove mm = new MessageMove();
			mm.printMove(response, LOGIN_NEEDED_SERVICE,"/member/login");
			
			return null;
		}
		
		// 데이터 로드
		String pwd = request.getParameter("pwd");
		
		// 유효성 확인
		Validator validator = new Validator();
		if (validator.isEmpty(pwd)) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// mber_seq를 이용해서 비밀번호 로드
		MemberService svc = new MemberService();
		MemberVo memberVo = svc.getMemberInfo(Integer.parseInt(mber_seq));
		if (memberVo == null) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// 비밀번호 확인
		if (!BCrypt.checkpw(pwd, memberVo.getPwd())) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CHECK_PASSWORD);
			return null;
		}
		
		// 정보 저장
		request.setAttribute("memberVo", memberVo);
		
		// 경로 설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/modifyForm.jsp");
		
		return forward;
	}
}
