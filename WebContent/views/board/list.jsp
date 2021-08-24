<%@page import="java.util.ArrayList"%>
<%@page import="vo.BoardVo"%>
<%@page import="common.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	PageManager pm = (PageManager) request.getAttribute("pm");
    	ArrayList<BoardVo> list = (ArrayList<BoardVo>) request.getAttribute("list");
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
	$(document).ready(function () {
		$('#sort').val('<%=sort%>').prop('selected', true);
		$('#searchFilter').val('<%=sf%>').prop('selected', true);
	});

	function writeArticle() {
		location.href="/board/write?pn=<%=pn%>&sf=<%=sf%>&sk=" + encodeURI('<%=sk%>') + "&sort=<%=sort%>";
	}
	
	function sortArticle() {
		location.href = "/board/list?pn=1&sf=<%=sf%>&sk=" + encodeURI('<%=sk%>') + "&sort=" + $('#sort option:selected').val();
	}
	
	function searchArticle() {
		var sk = $('#searchKeyword').val();
		
		if (!sk) {
			alert('검색키워드를 입력해 주세요.');
			return;
		}
		
		if (sk.lenght < 2) {
			alert('검색키워드는 2자 이상으로 입력해 주세요.');
			return;
		}
		
		var sf = $('#searchFilter option:selected').val();
		location.href = "/board/list?pn=1&sf=" + sf + "&sk="+ encodeURI(sk) + "&sort=1";
		
	}
	
	function goIndex() {
		location.href = "/"
	}
	
	function goMain() {
		location.href = "/board/list?pn=1&sf=0&sk=&sort=1"
	}
	
</script>

<style>
	td {text-align:center; border:1px solid black;}
</style>

</head>
<body>

<select id="sort" onchange="sortArticle()">
	<option value="1">최신순</option>
	<option value="2">오래된순</option>
</select>
<table>
	<tr>
		<td>번호</td>
		<td width="200px">제목</td>
		<td width="100px">작성자</td>
		<td>조회수</td>
		<td width="150px">작성일시</td>
	</tr>
	<%if (list.size() > 0) {%>
	
		<%for(int i = 0; i < list.size(); i++) {%>
		<tr>
			<td><%=list.get(i).getBoard_seq() %></td>
			<td>
				<a href="/board/detail?pn=<%=pn%>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>&bseq=<%=list.get(i).getBoard_seq()%>"><%=list.get(i).getSj() %></a>
			</td>
			<td><%=list.get(i).getMber_id() %></td>
			<td><%=list.get(i).getCnt() %></td>
			<td><%=list.get(i).getDttm() %></td>
		</tr>
		<%} %>
	<%} else { %>
		<tr>
			<td colspan="5" style="text-align: center">내용이 없습니다.</td>
		</tr>
	<%} %>
</table>

<!-- 페이지네이션 -->
<div>
	<a href="/board/list?pn=1&sf=<%=sf %>&sk=<%=sk %>&sort=<%=sort %>"></a>
	
	<a href="/board/list?pn=<%=pm.getPrePageNumber() %>&sf=<%=sf %>&sk=<%=sk %>&sort=<%=sort %>"> < </a>
	
	<%for (int i = pm.getStartPageNumber(); i <= pm.getEndPageNumber(); i++) {%>
	<a href="/board/list?pn=<%=i %>&sf=<%=sf %>&sk=<%=sk %>&sort=<%=sort %>"> <%=i %> </a>
	<%} %>
	
	<a href="/board/list?pn=<%=pm.getNextPageNumber() %>&sf=<%=sf%>&sk=<%=sk %>&sort=<%=sort%>"> > </a>
	<a href="/board/list?pn=<%=pm.getTotalPageCount() %>&sf=<%=sf%>&sk=<%=sk %>&sort=<%=sort%>"> >> </a>
</div>

<div>
	<select id="searchFilter">
		<option value="0">전체</option>
		<option value="1">제목</option>
		<option value="2">내용</option>
	</select>
	<input type='text' id="searchKeyword" value="<%=sk%>" placeholder="2자 이상의 검색 키워드" />
	<button onclick="searchArticle()">검색</button>
</div>

<button onclick="writeArticle()">글쓰기</button>
<button onclick="goMain()">처음으로</button>
<button onclick="goIndex()">메인으로</button>


</body>
</html>









