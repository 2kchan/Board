package myboard.member.action;

import static common.Constants.FAIL_JOIN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.MessageMove;

public class JoinResultAction implements Action{
@Override
public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	// 세션에서 id 키워드로 id 로드
	HttpSession session = request.getSession();
	String id = (String)session.getAttribute("joinId");
	
	// id == null 이면 홈으로 
	if (id==null) {
		MessageMove mm = new MessageMove();
		mm.printMove(response, FAIL_JOIN, "/");
		return null;
	}
	
	// id 저장
	request.setAttribute("id", id);
	
	// 경로 세팅
	ActionForward forward = new ActionForward();
	forward.setPath("/views/member/joinResult.jsp");
	
	return forward;
}
}
