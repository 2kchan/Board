package myboard.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import myboard.board.action.DeleteAction;
import myboard.board.action.DetailAction;
import myboard.board.action.ListAction;
import myboard.board.action.ModifyAction;
import myboard.board.action.ModifyProcAction;
import myboard.board.action.WriteAction;
import myboard.board.action.WriteProcAction;



@WebServlet("/board/*")
public class BoardFrontController extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

    public BoardFrontController() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String command = uri.substring("/board/".length());

		ActionForward forward = null;

		if (command.equals("list")) {
			Action action = new ListAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("write")) {
			Action action = new WriteAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("writeProc")) {
			Action action = new WriteProcAction();
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
		} else if (command.equals("delete")) {
			Action action = new DeleteAction();
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
