package myboard.member.action;

import static common.Constants.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import common.Action;
import common.ActionForward;
import common.LoginManager;
import common.MessageMove;
import common.Validator;
import myboard.member.service.MemberService;
import vo.MemberVo;

public class ModifyProcAction implements Action{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인여부 확인
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstanc();
		String mber_seq = lm.getMemberSequence(session);
		if (mber_seq == null) {
			session.setAttribute("targetURI", "/member/modifyProc");
			MessageMove mm = new MessageMove();
			mm.printMove(response, LOGIN_NEEDED_SERVICE,"/member/login");
			
			return null;
		}
		
		// Parameter 데이터 로드
		String nm = request.getParameter("nm");
		String moblphon = request.getParameter("moblphon");
		
		// 데이터 유효성 확인
		Validator validator = new Validator();
		if (!validator.isValidatedData(nm,MEMBER_REGEXP_NAME) || !validator.isValidatedData(moblphon,MEMBER_REGEXP_MOBILE)) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// VO 데이터 세팅
		MemberVo memberVo = new MemberVo();
		memberVo.setMber_seq(Integer.parseInt(mber_seq));
		memberVo.setNm(nm);
		memberVo.setMoblphon(moblphon);
		
		// svc를 통해 데이터베이스 저장
		MemberService svc = new MemberService();
		boolean isSuccess = svc.getModifyMember(memberVo);
		if(!isSuccess) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, FAIL_MODIFY_MEMBER);
			return null;
		}
		
		// 경로 세팅
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/member/detail");
		
		return forward;
	}

}
