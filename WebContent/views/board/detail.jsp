<%@page import="common.*"%>
<%@page import="vo.BoardVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	BoardVo vo = (BoardVo) request.getAttribute("vo");
    	String pn = request.getParameter("pn");
    	String sf = request.getParameter("sf");
    	String sk = request.getParameter("sk");
    	String sort = request.getParameter("sort");
 
    	LoginManager lm = LoginManager.getInstanc();
    	String mber_seq = lm.getMemberSequence(session);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function modifyArticle() {
		if(confirm("글을 수정하시겠습니까?")) {
			location.href = "/board/modify?pn=<%=pn%>&sf=<%=sf%>&sk=" + encodeURI('<%=sk%>') + "&sort=<%=sort%>&bseq=<%=vo.getBoard_seq()%>"
		}
	}
	
	function deleteArticle() {
		if(confirm("글을 삭제하시겠습니까?")) {
			location.href = "/board/delete?pn=<%=pn%>&sf=<%=sf%>&sk=" + encodeURI('<%=sk%>') + "&sort=<%=sort%>&bseq=<%=vo.getBoard_seq()%>"
		}
	}
	
	function goList() {
		location.href = "/board/list?pn=<%=pn%>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>"
	}
	

	
</script>
</head>
<body>
<div>
	제목 : <%=vo.getSj() %>
</div>
<div>
	작성자 : <%=vo.getMber_id() %>
</div>
<div>
	작성일시 : <%=vo.getDttm() %>
</div>
<div>
	조회수 : <%=vo.getCnt() %>
</div>
<div>
	내용 : <%=vo.getCntnt() %>
</div>
<%if (mber_seq != null && Integer.parseInt(mber_seq) == vo.getMber_seq()) { %>
<button onclick="modifyArticle()">글수정</button>
<button onclick="deleteArticle()">글삭제</button>
<%} %>
<button onclick="goList()">목록으로</button>



</body>
</html>