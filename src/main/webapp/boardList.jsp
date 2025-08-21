<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>쇼핑몰 - 게시판</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>

  <!-- 네비게이션 -->
  <header class="navbar">
    <div class="logo">쇼핑몰</div>
    <nav>
      <ul>
        <li><a href="index.jsp">홈</a></li>
        <li><a href="List.do">게시판</a></li>
        <li><a href="memberJoin.do">회원가입</a></li>
        <li><a href="login.do">로그인</a></li>
      </ul>
    </nav>
  </header>

  <!-- 메인 히어로 -->
  <section class="hero">
    <h1>즐거운 쇼핑 라이프</h1>
    <p>최고의 상품과 서비스를 제공합니다.</p>
  </section>

  <!-- 게시판 목록 -->
  <section id="board" class="board-container">
    <h1>게시판</h1>
	<span>
		<c:if test="${not empty sessionScope.sessionId }">
			${sessionScope.sessionId} 님 로그인 중
		</c:if>
	</span>
    <!-- 상단 버튼 -->
    <div class="board-header">
	  <a href="boardWrite.do" class="btn-write">글쓰기</a>
	</div>
	<!-- 게시판 글 검색창 -->
    <form action="List.do" method="get">
    	<select name="searchType">
    		<option value="btitle">제목</option>
    		<option value="bcontents">내용</option>
    		<option value="B.memberid">작성자</option>
    	</select>
    	<input type="text" name="searchKeyword" placeholder="검색어 입력">
    	<input type="submit" value="검색">
    </form>
    
    <!-- 목록 테이블 -->
    <table class="board-table">
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>이메일</th>
          <th>조회수</th>
          <th>날짜</th>
        </tr>
      </thead>
      <tbody>
       	<tbody>
	  <c:forEach items="${bDtos}" var="bDto" >
	    <tr>
	      <td>${bDto.bno}</td> <!-- 글번호 -->
	      <td>
	      	<c:choose>
		      	<c:when test="${fn:length(bDto.btitle) > 10}"> <!-- 글제목 -->
		      	<a href="View.do?bnum=${bDto.bnum}">${fn:substring(bDto.btitle,0,10)}...</a>
		      	</c:when>
		      	<c:otherwise>
						<a href="View.do?bnum=${bDto.bnum}">${bDto.btitle}</a>
				</c:otherwise>
	      	</c:choose>
	      </td>
	      <td>${bDto.memberid}</td> <!-- 작성자 -->
	      <td>${bDto.memberDto.memberemail}</td><!-- 작성자 이메일-->
	      <td>${bDto.bhit}</td> <!-- 조회수 -->
	      <td>${fn:substring(bDto.bdate,0,10)}</td> <!-- 작성일 -->
	    </tr>
	  </c:forEach>
      </tbody>
    </table>

    <!-- 페이지네이션 -->
    <div class="pagination">
    <!-- 맨 처음 글로 이동 -->
    <c:if test="${currentPage > 1 }">
      <a href="List.do?page=${1 }">&laquo;</a>
    </c:if>  
    <!-- 이전 페이지 그룹으로 이동 -->
    <c:if test="${startPage > 1 }">
      <a href="List.do?page=${startPage -1 }">&lt;</a>
    </c:if>  
    
      	<c:forEach begin="${startPage }" end="${endPage }" var="i"> 
      		<c:choose>
		      	<c:when test="${i == currentPage }"> 
		      		<a href="List.do?page=${i}" class="active"><b style="color: navy;">${i}</b></a>
		      	</c:when>
		      	<c:otherwise>
		      		<a href="List.do?page=${i}" class="active">${i}</a>
		      	</c:otherwise>	
		    </c:choose>  	
      	</c:forEach>	

    <!-- 다음 페이지 그룹으로 이동 -->
    <c:if test="${endPage < totalPage }">
      <a href="List.do?page=${endPage +1 }">&gt;</a>
    </c:if> 
    
      	 <!-- 맨 마지막 글로 이동 -->
    <c:if test="${currentPage < totalPage }">
      <a href="List.do?page=${totalPage}">&raquo;</a>
    </c:if>
      
    </div>
  </section>
</body>
</html>
