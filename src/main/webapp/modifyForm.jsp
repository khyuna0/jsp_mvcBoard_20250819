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
        <li><a href="List.jsp">게시판</a></li>
      </ul>
    </nav>
  </header>

  <div class="form-container">
    <h1>글 수정</h1>
    <form action="modifyOk.do" method="post">
      <input type="hidden" name="bnum" value="${boardDto.bnum}">
      <div class="form-group">
        <label for="title">${boardDto.btitle }</label>
        <input type="text" id="title" name="title" value="${boardDto.btitle }">
      </div>
      <div class="form-group">
        <label for="content">내용</label>
        <textarea id="content" name="content" rows="10">${boardDto.bcontents }</textarea>
      </div>
      <div class="form-group">
        <label for="writer">작성자 아이디</label>
        <input type="text" id="writer" name="writer" value="${boardDto.memberid }" readonly>
      </div>
      <div class="form-buttons">
        <button type="submit" class="btn">저장</button>
        <a href="javascript:history.go(-1)" class="btn cancel">취소</a>
      </div>
    </form>
  </div>
</body>
</html>
