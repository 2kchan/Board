<%@page import="vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	BoardVo vo = (BoardVo) request.getAttribute("vo");
    	String pn = request.getParameter("pn");
  		String sf = request.getParameter("sf");
  	 	String sk = request.getParameter("sk");
   		String sort = request.getParameter("sort");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous">
</script>
<script>
	function cancelModify() {
		location.href = "/board/detail?pn=<%=pn%>&sf=<%=sf%>&sk=" + encodeURI('<%=sk%>') + "&sort=<%=sort%>&bseq=<%=vo.getBoard_seq()%>"
	}
	
	function modifyArticle() {
		var sj = $('#sj').val();
		var cntnt = $('#cntnt').val();
		
		if(!sj) {
			alert('제목을 입력해 주세요.');
			$('#sj').focus();
			return;
		}
		
		if(!cntnt) {
			alert('내용을 입력해 주세요.');
			$('#cntnt').focus();
			return;
		}
		
		if(sj.length > 50) {
			alert('제목은 50자 이내로 작성해 주세요.');
			$('#sj').focus();
			return;
		}
		
		$('#modifyForm').attr('action', '/board/modifyProc?pn=<%=pn%>&sf=<%=sf%>&sk=' + encodeURI('<%=sk%>') + '&sort=<%=sort%>&bseq=<%=vo.getBoard_seq()%>');
		$('#modifyForm').submit();
	}
</script>
	
</head>
<body>
	<form method="post" id="modifyForm">
		<div>
			제목 <input type="text" id="sj" name="sj" maxlength="50" value=<%=vo.getSj()%> />
		</div>
		
		<div>
			내용 <textarea name="cntnt" id="cntnt" cols="30" rows="10"><%=vo.getCntnt()%></textarea>
		</div>
	</form>
	<button onclick="modifyArticle()">수정</button>
	<button onclick="cancelModify()">취소</button>
</body>
</html>