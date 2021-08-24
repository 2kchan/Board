package common;

import static common.Constants.WRONG_ACCESS;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class MessageMove {
	// 메세지 출력 후 뒤로가기
	public void printBack(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + message + "'); history.back();</script>");
		out.close();
	}

	// 메세지 출력 후 이동 href
	public void printMove(HttpServletResponse response, String message, String path) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + message + "'); location.href='" + path + "';</script>");
		out.close();
	}

	// 메세지 출력 후 리플레이스 replace
	public void printreplace(HttpServletResponse response, String message, String path) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + message + "'); location.replace('" + path + "');</script>");
		out.close();
	}
}
