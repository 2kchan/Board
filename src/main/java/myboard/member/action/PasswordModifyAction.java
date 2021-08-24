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

public class PasswordModifyAction implements Action {
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
		String id = request.getParameter("id");
		String nm = request.getParameter("nm");
		String moblphon = request.getParameter("moblphon");
		
		// 데이터 유효성 검사
		Validator validator = new Validator();
		if (!validator.isValidatedData(id, MEMBER_REGEXP_ID) || !validator.isValidatedData(nm,MEMBER_REGEXP_NAME) || !validator.isValidatedData(moblphon,MEMBER_REGEXP_MOBILE)) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}

		// memberVo에 데이터 세팅
		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setNm(nm);
		memberVo.setMoblphon(moblphon);

		// 데이터베이스에서 검색
		// 로드할 데이터 mber_seq;
		MemberService svc = new MemberService();
		int seq = svc.getMemberSequence(memberVo);
		
		if(seq==0) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CANNOT_FIND_MEMBER);
			return null;
		}
		// session에 저장
		session.setAttribute("mber_seq", seq);
		
		// view 이동
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/passwordModifyForm.jsp");

		return forward;
	}
}
