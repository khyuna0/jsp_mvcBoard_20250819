<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>글쓰기</title>
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

  <div class="form-container">
    <h1>글쓰기</h1>
    <form action="insert.jsp" method="post">
      <div class="form-group">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" required>
      </div>
      <div class="form-group">
        <label for="content">내용</label>
        <textarea id="content" name="content" rows="10" required></textarea>
      </div>
      <div class="form-group">
        <label for="writer">작성자 아이디</label>
        <input type="text" id="writer" name="writer" required>
      </div>
      <div class="form-buttons">
        <button type="submit" class="btn">등록</button>
        <a href="index.jsp#board" class="btn cancel">취소</a>
      </div>
    </form>
  </div>
</body>
</html>
