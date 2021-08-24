package myboard.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import common.Action;
import myboard.member.action.AjaxCheckIdAction;

import myboard.member.action.DetailAction;
import myboard.member.action.FindIdAction;
import myboard.member.action.FindIdResultAction;
import myboard.member.action.FindPasswordAction;
import myboard.member.action.JoinAction;
import myboard.member.action.JoinProcAction;
import myboard.member.action.JoinResultAction;
import myboard.member.action.LoginAction;
import myboard.member.action.LoginProcAction;
import myboard.member.action.LogoutAction;
import myboard.member.action.ModifyAction;
import myboard.member.action.ModifyProcAction;
import myboard.member.action.ModifyPwdAction;
import myboard.member.action.ModifyPwdResultAction;
import myboard.member.action.PasswordCheckAction;
import myboard.member.action.PasswordModifyAction;
import myboard.member.action.LeaveResultAction;
import myboard.member.action.LeaveAction;
import myboard.member.action.ModifyPasswordResultAction;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String command = uri.substring("/member/".length());

		ActionForward forward = null;

		if (command.equals("join")) {
			Action action = new JoinAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("ajaxCheckId")) {
			Action action = new AjaxCheckIdAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("joinProc")) {
			Action action = new JoinProcAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 	else if (command.equals("joinResult")) {
			Action action = new JoinResultAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (command.equals("login")) {
			Action action = new LoginAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("loginProc")) {
			Action action = new LoginProcAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("logout")) {
			Action action = new LogoutAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("detail")) {
			Action action = new DetailAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("modify/check/password")) {
			Action action = new PasswordCheckAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("modify")) {
			Action action = new ModifyAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("modifyProc")) {
			Action action = new ModifyProcAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("find/id")) {
			Action action = new FindIdAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("find/id/result")) {
			Action action = new FindIdResultAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("find/password")) {
			Action action = new FindPasswordAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("password/modify")) {
			Action action = new PasswordModifyAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("modify/password/result")) {
			Action action = new ModifyPasswordResultAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("leave")) {
			Action action = new LeaveAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("leaveResult")) {
			Action action = new LeaveResultAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("modifyPwd")) {
			Action action = new ModifyPwdAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("modifyPwd/Result")) {
			Action action = new ModifyPwdResultAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		if (forward != null) {
			if (forward.isRedirect()) {
				// 리다이렉트 방식
				response.sendRedirect(forward.getPath());
			} else {
				// 디스팻처방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}

	}

}
