package myboard.member.action;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import myboard.member.service.MemberService;

import static common.Constants.*;

public class AjaxCheckIdAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		
		if (id == null || id.equals("") || !Pattern.matches(MEMBER_REGEXP_ID, id)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", WRONG_ACCESS);
		} else {
			MemberService svc = new MemberService();
			int count = svc.getMemberCountById(id);
			boolean result = count == 0 ? true : false;
			request.setAttribute("result", result);
			String msg = result ? ID_USABLE : ID_DUPLICATED;
			request.setAttribute("msg", msg);
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/idCheckResult.jsp");
		return forward;
	}

}
