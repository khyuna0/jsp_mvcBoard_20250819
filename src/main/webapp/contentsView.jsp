<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<%
	if (request.getAttribute("errorMsg") != null ) { // 웹 서블릿에서 넘겨준 값을 뺄때는 getAttribute 사용
		String msg = request.getAttribute("errorMsg").toString();
		out.println("<script>alert('" +msg+ "');window.location.href='List.do';</script>");
	}
	
%>
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
        <li><a href="List.do">게시판</a></li>
      </ul>
    </nav>
  </header>

  <div class="view-container">
    <h1 class="view-title">${boardDto.btitle }</h1>
    <div class="view-info">
      <span>작성자: ${boardDto.memberid } </span> |
      <span>조회수: ${boardDto.bhit } </span> |
      <span>작성자 이메일: ${boardDto.memberDto.memberemail } </span> |
      <span>작성일: ${boardDto.bdate } </span> |
    </div>
    <div class="view-content">
      <p>${boardDto.bcontents }.</p>
    </div>
    <div class="view-buttons">
      <a href="List.do" class="btn">목록</a>
     <c:if test="${sessionScope.sessionId == boardDto.memberid }">
	     <a href="modifyForm.do?bnum=${boardDto.bnum}" class="btn">수정</a>
	     <a href="delete.do?bnum=${boardDto.bnum}" class="btn delete">삭제</a>
      </c:if> 

      
    </div>
  </div>
</body>
</html>
