<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>

<style>
	th{
		background: cyan;
	}
</style>

<script type="text/javascript">
	function insert(){
		location.href="insert.mv";
	}
</script>

movieList.jsp<br>

<center>
	<h2>영화 리스트 화면(해당 레코드 갯수:${pageInfo.totalCount}) </h2>
	<h3 align="center">현재 선택한 페이지 번호 : ${pageInfo.pageNumber}</h3>
	<form action="list.mv" method="get">
		<select name = "whatColumn">
			<option value="all">전체 검색
			<option value="genre">장르
			<option value="grade">관람연령
			<option value="actor">배우
		</select>
		<input type="text" name="keyword" value="">
		<input type="submit" value="검색">
	</form>
</center>

<table border="1" align="center" width="60%">

	<tr>
		<td colspan="9"><input type="button" value="삽입" onClick="insert()"></td>
	</tr>

	<tr align="center">
		<th>번호</th>
		<th>제목</th>
		<th>대륙</th>
		<th>국가</th>
		<th>장르</th>
		<th>관람연령</th>
		<th>배우</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
	
	<c:forEach var="movie" items="${movieLists}">
		<tr align="center">
			<td>${movie.num}</td>
			<td><a href="detail.mv?num=${movie.num}&pageNumber=${pageInfo.pageNumber}&whatColumn=${pageInfo.whatColumn}&keyword=${pageInfo.keyword}">${movie.title}</a></td>
			<td>${movie.continent}</td>
			<td>${movie.nation}</td>
			<td>${movie.genre}</td>
			<td>${movie.grade}</td>
			<td>${movie.actor}</td>
			<td>
				<input type="button" value="수정" onClick="location.href='update.mv?num=${movie.num }&pageNumber=${pageInfo.pageNumber}&whatColumn=${whatColumn}&keyword=${keyword}'">
			</td>
			<td>
				<a href="delete.mv?num=${movie.num }&pageNumber=${pageInfo.pageNumber}&whatColumn=${whatColumn}&keyword=${keyword}">
					삭제
				</a>
			</td>
		</tr>
	</c:forEach>
	
</table>

<p align="center">
	${pageInfo.pagingHtml }
</p>