<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로그인</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <!-- 네비게이션 (index와 동일 구조) -->
  <header class="navbar">
    <div class="logo">쇼핑몰</div>
    <nav>
      <ul>
         <li><a href="index.jsp">홈</a></li>
        <li><a href="#">안내</a></li>
        <li><a href="List.do">게시판</a></li>
        <li><a href="memberJoin.do">회원가입</a></li>
        <li><a href="login.do">로그인</a></li>
      </ul>
    </nav>
  </header>

  <!-- 로그인 폼 -->
  <main class="login-container">
    <h1 class="login-title">로그인</h1>

    <!-- action은 프로젝트 라우팅에 맞게 조정 -->
    <form class="login-form" action="loginOk.do" method="post" autocomplete="on" novalidate>
      <div class="login-group">
        <label for="memberid">아이디</label>
        <input type="text" id="memberid" name="memberid" class="login-input" required placeholder="아이디 입력" autocomplete="username">
      </div>

      <div class="login-group">
        <label for="memberpw">비밀번호</label>
        <input type="password" id="memberpw" name="memberpw" class="login-input" required placeholder="비밀번호 입력" autocomplete="current-password">
      </div>

      <div class="login-actions">
        <button type="submit" class="btn primary">로그인</button>
        <a href="signup.jsp" class="btn">회원가입</a>
      </div>
      
      <div>
        <c:if test="${ param.msg == 1}">
        	<p style="color: red;">아이디 또는 비밀번호가 잘못되었습니다.</p>
        </c:if>
        
        <c:if test="${param.msg == 2 }">
        	<script>alert('로그인하신 유저만 글쓰기가 가능합니다.');</script>
        </c:if>
      </div>
            
    </form>
  </main>
</body>
</html>
