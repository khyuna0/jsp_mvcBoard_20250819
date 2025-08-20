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
	  <c:forEach items="${bDtos}" var="bDto">
	    <tr>
	      <td>${bDto.bnum}</td> <!-- 글번호 -->
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
      <a href="#">&laquo;</a>
      <a href="#" class="active">1</a>
      <a href="#">2</a>
      <a href="#">3</a>
      <a href="#">&raquo;</a>
    </div>
  </section>
</body>
</html>
