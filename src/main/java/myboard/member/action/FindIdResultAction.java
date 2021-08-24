package myboard.member.action;

import static common.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import common.Action;
import common.ActionForward;
import common.LoginManager;
import common.MessageMove;
import common.Validator;
import myboard.member.service.MemberService;
import vo.MemberVo;

public class FindIdResultAction implements Action {
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
		String nm = request.getParameter("nm");
		String moblphon = request.getParameter("moblphon"); 
		
		// 데이터 유효성 검사
		Validator validator = new Validator();
		if (!validator.isValidatedData(nm,MEMBER_REGEXP_NAME) || !validator.isValidatedData(moblphon,MEMBER_REGEXP_MOBILE)) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, WRONG_ACCESS);
			return null;
		}
		
		// memberVo에 데이터 세팅
		MemberVo memberVo = new MemberVo();
		memberVo.setNm(nm);
		memberVo.setMoblphon(moblphon);
		
		// 데이터베이스로 부터 입력한 이름과 휴대전화번호에 해당하는 아이디 로드
		// 아이디가 null이면 "입력한 정보에 해당하는 아이디가 존재하지 않습니다." 메세지 표시후 뒤로 가기
		MemberService svc = new MemberService();
		String id = svc.getMemberId(memberVo);
		
		if(id==null) {
			MessageMove mm = new MessageMove();
			mm.printBack(response, CANNOT_FIND_ID);
			return null;
		}
		
		// request 객체의 attribute 객체에 id 저장
		request.setAttribute("id", id);
		
		// view 이동
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/findIdResult.jsp");
		
		return forward;
	}
}
