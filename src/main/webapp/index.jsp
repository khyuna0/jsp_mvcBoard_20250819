<%@ page contentType="text/html; charset=UTF-8" %>
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
        <li><a href="#">안내</a></li>
        <li><a href="#">공지사항</a></li>
        <li><a href="boardList.do">게시판</a></li>
      </ul>
    </nav>
  </header>

  <!-- 메인 히어로 -->
  <section class="hero">
    <h1>건강하고 즐거운 쇼핑!</h1>
    <p>최고의 상품과 서비스를 제공합니다.</p>
  </section>

  <!-- 게시판 -->
  <section id="board" class="board-container">
    <h1>게시판</h1>
    <div class="board-header">
      <a href="boardWrite.do" class="btn-write">글쓰기</a>
    </div>
    <table>
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>내용</th>
          <th>작성자 아이디</th>
          <th>조회수</th>
          <th>날짜</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td class="title"><a href="contentsView.jsp">테스트 글 제목</a></td>
          <td>내용 미리보기</td>
          <td>user01</td>
          <td>12</td>
          <td>2025-08-20</td>
        </tr>
      </tbody>
    </table>
  </section>
</body>
</html>
