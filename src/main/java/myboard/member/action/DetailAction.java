package myboard.member.action;

import static common.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.LoginManager;
import common.MessageMove;
import myboard.member.service.MemberService;
import vo.MemberVo;

public class DetailAction implements Action{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부 확인
		 HttpSession session = request.getSession();
		 LoginManager lm = LoginManager.getInstanc();
		 String mber_seq = lm.getMemberSequence(session);
		if (mber_seq == null) {
			String requestUri = request.getRequestURI();
			session.setAttribute("targetURI", requestUri);
			MessageMove mm = new MessageMove();
			mm.printMove(response, LOGIN_NEEDED_SERVICE,"/member/login");
			
			return null;
		}
		
		// 로그인 된 mber_seq를 이용하여 db에서 회원 정보 로드
		MemberService svc = new MemberService();
		MemberVo memberVo = svc.getMemberInfo(Integer.parseInt(mber_seq));
		if (memberVo == null) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// attribute 저장
		request.setAttribute("memberVo", memberVo);
		
		// 뷰 이동
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/detail.jsp");

		return forward;
	}

}
