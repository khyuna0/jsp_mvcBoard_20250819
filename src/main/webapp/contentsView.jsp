<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>글 보기</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <header class="navbar">
    <div class="logo">쇼핑몰</div>
    <nav>
      <ul>
        <li><a href="index.jsp">홈</a></li>
        <li><a href="boardList.jsp">게시판</a></li>
      </ul>
    </nav>
  </header>

  <div class="view-container">
    <h1 class="view-title">테스트 글 제목</h1>
    <div class="view-info">
      <span>작성자: user01</span>
      <span>조회수: 15</span>
      <span>작성일: 2025-08-20</span>
    </div>
    <div class="view-content">
      <p>이곳에 글 내용이 표시됩니다.</p>
    </div>
    <div class="view-buttons">
      <a href="boardList.jsp" class="btn">목록</a>
      <a href="modyfiForm.jsp" class="btn">수정</a>
      <a href="#" class="btn delete">삭제</a>
    </div>
  </div>
</body>
</html>
