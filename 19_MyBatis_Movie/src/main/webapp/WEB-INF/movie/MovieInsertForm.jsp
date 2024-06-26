<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%>
MovieInsertForm.jsp
<br>

<style>
.err {
	color: green;
}
</style>

<script>
	var f_selbox = new Array('아시아', '아프리카', '유럽', '아메리카', '오세아니아');

	var s_selbox = new Array();
	s_selbox[0] = new Array('한국', '중국', '베트남', '네팔');
	s_selbox[1] = new Array('케냐', '가나', '세네갈');
	s_selbox[2] = new Array('스페인', '영국', '덴마크', '러시아', '체코');
	s_selbox[3] = new Array('미국', '캐나다');
	s_selbox[4] = new Array('뉴질랜드', '오스트레일리아');
	var selectContinent;

	function init(movie_continent, movie_nation) {
		
		document.myform.first.options[0] = new Option("대륙 선택하세요", "");
		document.myform.second.options[0] = new Option("나라 선택하세요", "");

		//continent 변경
		for (i = 0 ; i < f_selbox.length ; i++) {
			 document.myform.first.options[i+1] = new Option(f_selbox[i],f_selbox[i]);
			if (document.myform.first.options[i + 1].value == movie_continent) {
				document.myform.first.options[i + 1].selected = true;
				selectContinent = i; // 아프리카 : 1
			}//if
		}//for

		//nation 변경
		 for(var j = 0 ; j < s_selbox[selectContinent].length ; j++){
	    	  document.myform.second.options[j+1] = new Option(s_selbox[selectContinent][j]);
	    	  if(document.myform.second.options[j+1].value == movie_nation){
	         	 document.myform.second.options[j+1].selected = true;
	          }
	      }//for
		
	}//init

	function continentChange() {
		var findex = document.myform.first.selectedIndex; //아프리카 : 2

		for (i = document.myform.second.length - 1; i > 0; i--) {
			document.myform.second.options[i] = null; //대륙을 선택하고, 다음 대륙을 선택하기 전에 기존 정보를 초기화.
		}

		for (i = 0; i < s_selbox[findex - 1].length; i++) {
			document.myform.second.options[i + 1] = new Option(s_selbox[findex - 1][i]);
		}

	}//init
</script>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		
		var use;
		var isCheck = false;
		
		$('#title_check').click(function(){		//중복체크 클릭 시
			
			isCheck = true;
			$.ajax({
				url : "title_check_proc.mv",
				
				type : "post",
				
				data : ({
					inputTitle : $("input[name=title]").val()
				}),
				
				success : function(data){
					
					flag = true;
					
					if($('input[name=title]').val() == ""){
						$('#titleMessage').html("제목이 누락되었습니다.").css("color","red").show();
						$('input[name=title]').focus();	
					}
					else if(data == "YES"){	//"yes"(X) 대문자로 넘겼기 때문에 똑같이 써줘야함.
						$("#titleMessage").html("사용가능한 제목입니다.").css("color","blue").show();
						$('input[name=title]').focus();
						use = "possible";
					}
					else{
						$("#titleMessage").html("이미 등록된 제목입니다.").css("color","red").show();
						use = "imPossible";
					}
					
				}	//success
			
			});	//ajax
			
		});	//click
		
		$('input[name=title]').keydown(function(){
			isCheck = false;
			use = "";
			
			$("#titleMessage").css('display','none');
			
		});
		
		$('#btnSubmit').click(function(){
			
			if(use == "imPossible"){
				alert("이미 사용중인 제목입니다.");
				return false;
			}
			else if(isCheck == false){
				alert("중복체크를 하세요.");
				return false;
			}
			
		});	//#btnSubmit click
		
		
	});	//ready
	
</script>


<%
String[] genre = { "액션", "스릴러", "코미디", "판타지", "애니메이션" };
String[] grade = { "19", "15", "12", "7" };
%>

<%-- 
${movie.continent} / ${movie.nation}
아프리카 / 케냐
 --%>

${movie.continent} / ${movie.nation}

<body onLoad="init('${movie.continent }','${movie.nation}')">

	<form:form commandName="movie" name="myform" action="insert.mv"
		method="post">
		<h2>영화 정보 등록 화면</h2>
		
		영화 제목 : <input type="text" name="title" value="${movie.title}">
		<input type="button" id="title_check" value="중복체크">
		<span id="titleMessage"></span>
		<form:errors path="title" cssClass="err" />
		<br>
		<br>
		
		대륙 : 
		<c:set var="c" value="${movie.continent }" />

		<select id="first" name="continent" onChange="continentChange()"></select>
		<form:errors path="continent" cssClass="err" />
		
		나라 : 
		<select id="second" name="nation"></select>
		<form:errors path="nation" cssClass="err" />

		<br>
		<br>
		
		장르 : 
		<c:set var="genre" value="<%=genre%>" />
		<c:forEach var="i" begin="0" end="${fn:length(genre)-1 }">
			<input type="checkbox" name="genre" value="${genre[i] }"
				<c:if test = "${fn:contains(movie.genre, genre[i]) }">checked</c:if>>${genre[i] }
		</c:forEach>
		<form:errors path="genre" cssClass="err"></form:errors>

		<br>
		<br>
		
		등급 : 
		<c:set var="grade" value="<%=grade%>" />
		<c:forEach var="i" begin="0" end="${fn:length(grade)-1 }">
			<input type="radio" name="grade" value="${grade[i] }"
				<c:if test = "${movie.grade eq 'grade[i]' }">checked</c:if>>${grade[i] }
		</c:forEach>
		<form:errors path="genre" cssClass="err"></form:errors>

		<br>
		<br>
		
		출연 배우 : <input type="text" name="actor" value="${movie.actor}">

		<br>
		<br>

		<input type="submit" value="삽입" id="btnSubmit">
	</form:form>

</body>
