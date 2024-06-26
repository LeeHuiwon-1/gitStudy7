<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
movieDetail.jsp<br>

<style>
	th{
		background: cyan;
	}
</style>

<h2 align="center">영화 상세 화면</h2>

<table border="1" align="center" width="50%" height="60%">
	<tr>
		<th>번호</th>
		<td>${movie.num }</td>
	</tr>
	
	<tr>
		<th>영화제목</th>
		<td>${movie.title}</td>
	</tr>
	
	<tr>
		<th>제작국가</th>
		<td>${movie.nation}</td>
	</tr>
	
	<tr>
		<th>장르</th>
		<td>${movie.genre}</td>
	</tr>
	
	<tr>
		<th>관람연령</th>
		<td>${movie.grade}</td>
	</tr>
	
	<tr>
		<th>배우</th>
		<td>${movie.actor}</td>
	</tr>
	
	<tr>
		<th colspan="2" align="center">
<%-- 	<a href="list.mv?pageNumber=${pageNumber}&whatColumn=${whatColumn}&keyword=${requestScope.keyword}"> 	--%>
		<a href="list.mv?pageNumber=${param.pageNumber}&whatColumn=${param.whatColumn}&keyword=${param.keyword}">
				영화 리스트 화면으로 돌아감
			</a>
		</th>
	</tr>
	
</table>