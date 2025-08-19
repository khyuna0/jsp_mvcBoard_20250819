<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>글 수정</title>
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
    <h1>글 수정</h1>
    <form action="update.jsp" method="post">
      <div class="form-group">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" value="기존 제목">
      </div>
      <div class="form-group">
        <label for="content">내용</label>
        <textarea id="content" name="content" rows="10">기존 본문 내용</textarea>
      </div>
      <div class="form-group">
        <label for="writer">작성자 아이디</label>
        <input type="text" id="writer" name="writer" value="user01" readonly>
      </div>
      <div class="form-buttons">
        <button type="submit" class="btn">저장</button>
        <a href="view.jsp" class="btn cancel">취소</a>
      </div>
    </form>
  </div>
</body>
</html>
