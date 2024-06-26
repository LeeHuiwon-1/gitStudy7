<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
movie_start.jsp<br>

list.mv -> MovieListController => movieList.jsp로 이동

<%
	String viewPage = request.getContextPath() + "/list.mv";
	response.sendRedirect(viewPage);
%>