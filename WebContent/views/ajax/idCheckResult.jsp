<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	boolean result = (boolean) request.getAttribute("result");
    	String msg = (String) request.getAttribute("msg");
    %>
    {
    	"result" : "<%=result %>"
    	, "msg" : "<%=msg %>"
    }
