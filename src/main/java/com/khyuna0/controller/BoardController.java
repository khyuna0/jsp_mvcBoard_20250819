package com.khyuna0.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.khyuna0.dao.BoardDao;
import com.khyuna0.dao.MemberDao;
import com.khyuna0.dto.BoardDto;
import com.khyuna0.dto.BoardMemberDto;


@WebServlet("*.do")
public class BoardController extends HttpServlet {

    public BoardController() {
        super();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String comm = uri.substring(conPath.length()); // 최종 요청 값
		String viewPage = null;
		
		BoardDao boardDao = new BoardDao();
		MemberDao memberDao = new MemberDao();
		HttpSession session = null;
		
		BoardDto boardDto = new BoardDto();
		List<BoardDto> bDtos = new ArrayList<BoardDto>();
		List<BoardMemberDto> bmDtos = new ArrayList<BoardMemberDto>();
		
		// 로그인
		if (comm.equals("/login.do")) { // 로그인
			viewPage = "login.jsp";
		
		// 로그인 처리	
		}else if(comm.equals("/loginOk.do")) {	
			request.setCharacterEncoding("utf-8");
			String loginId = request.getParameter("memberid");
			String loginPw = request.getParameter("memberpw");
			int loginFlag = memberDao.loginCheck(loginId, loginPw);
			// loginResult = 1 -> 로그인 성공
			if (loginFlag == 1) {
				session = request.getSession();
				session.setAttribute("sessionId", loginId);
			} else {
				response.sendRedirect("login.do?msg=1");
				return;
			}
			viewPage = "List.do";
			
		// 게시판 모든 글 목록 보기 요청	
		}else if(comm.equals("/List.do")) { 
		    int page = 1;
		    
		    String p = request.getParameter("page");
		    if (p != null && !p.isBlank()) { // 유저가 보고싶은 페이지 번호를 클릭함
		        page = Integer.parseInt(p);
		    } else { // 참이면 링크타고 들어옴
		        page = 1;
		    }

		    String searchType = request.getParameter("searchType");
		    String searchKeyword = request.getParameter("searchKeyword");
		    String kw = (searchKeyword == null) ? "" : searchKeyword.strip();

		    int totalBoardCount;
		    if (searchType != null && searchKeyword != null && !kw.isEmpty()) { // 유저가 검색 결과 리스트 원함

		        totalBoardCount = boardDao.countBoard(); // ← 임시 대체. DAO에 검색용 count 추가하면 교체.
		    } else {
		        totalBoardCount = boardDao.countBoard();
		    }

		
		    int totalPage = (int) Math.ceil((double) totalBoardCount / BoardDao.PAGE_SIZE);
		    if (totalPage <= 0) totalPage = 1;
		    if (page < 1) page = 1;
		    if (page > totalPage) page = totalPage;

		    
		    int startPage = (((page - 1) / BoardDao.PAGE_GROUP_SIZE) * BoardDao.PAGE_GROUP_SIZE) + 1;
		    int endPage = startPage + BoardDao.PAGE_GROUP_SIZE - 1;
		    if (endPage > totalPage) endPage = totalPage;

		    
		    if (searchType != null && searchKeyword != null && !kw.isEmpty()) { // 유저가 검색 결과 리스트 원함
		        bDtos = boardDao.SearchBoardList(page, searchType, kw);
		    } else { // 모든 게시판 글 리스트 원함
		        bDtos = boardDao.boardList(page);
		    }

		    
		    request.setAttribute("bDtos", bDtos);
		    request.setAttribute("currentPage", page);       // 유저가 현재 선택한 페이지 번호
		    request.setAttribute("totalPage", totalPage);    // 총 글의 갯수로 표현될 전체 페이지의 수 (글이 37개면 4가 전달)
		    request.setAttribute("startPage", startPage);
		    request.setAttribute("endPage", endPage);

		    viewPage = "boardList.jsp"; 
	
		// 게시판 글쓰기	
		} else if (comm.equals("/boardWrite.do")) { 
			session = request.getSession();
			String sid = (String) session.getAttribute("sessionId");
			if ( sid != null ) {
				viewPage = "boardWrite.jsp";
			} else {
				response.sendRedirect("login.do?msg=2");
				return;
			}
		// 글 수정	
		} else if (comm.equals("/modifyForm.do")) { 
			String bnum = request.getParameter("bnum"); // 수정하려고 하는 글의 글번호 가져옴
			boardDto = boardDao.contentView(bnum);
			request.setAttribute("boardDto", boardDto);

			viewPage = "modifyForm.jsp";
		// 글 수정 한 후 글 리스트로 이동하기 (수정 처리)	
		} else if (comm.equals("/modifyOk.do")) { 
			request.setCharacterEncoding("utf-8");
			
			String bnum = request.getParameter("bnum"); // 수정할 글 번호
			String btitle = request.getParameter("title"); // 수정할 글 제목
			String bcontents = request.getParameter("content"); // 내용
			//String memberid = request.getParameter("writer"); // 작성자
			
			boardDao.boardUpdate(bnum, btitle, bcontents);

			//viewPage = "List.do";
			viewPage = "View.do";
			
		// 글 삭제 후 글 목록 이동		
		} else if (comm.equals("/delete.do")) { 
			
			request.setCharacterEncoding("utf-8");
			String bnum = request.getParameter("bnum"); // 삭제할 글 번호
			boardDao.boardDelete(bnum); 
			viewPage = "List.do";
		// 글 목록에서 선택된 글 목록 내용이 보여지는 페이지로 이동	
		} else if (comm.equals("/View.do")) { 
			String bnum = request.getParameter("bnum");
			// 조회수
			boardDao.updateBhit(bnum);
			boardDto = boardDao.contentView(bnum);
			//System.out.println(boardDto);
			
			if (boardDto != null ) {
				request.setAttribute("boardDto", boardDto);
				
			} else {
				request.setAttribute("errorMsg", "존재하지 않는 글입니다."); 
				
				// response.sendRedirect("contentsView.jsp?msg=1"); -> (다른 방법) 페이지 강제이동과 함께 파라미터 값 넘기기 
				// 강제이동된 페이지에서 getParameter ~ ... != null
			}
			viewPage = "contentsView.jsp"; 
		
		// 글 수정 업데이트	
		} else if (comm.equals("/writeOk.do")) {
			
			request.setCharacterEncoding("utf-8");
			
			String btitle = request.getParameter("title");
			String bcontents = request.getParameter("content");
			String memberid = request.getParameter("writer");
			
			boardDao.boardWrite(btitle,bcontents,memberid, 0);
			response.sendRedirect("List.do");
			return; // 멈춤
		}
		
		
		else {
			viewPage = "index.jsp";
		}

		// ↓ boardList.jsp 에게 웹 서블릿에서 제작한 request 객체를 전달한 후 뷰페이지로 포워딩 하는 코드
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); 
		dispatcher.forward(request, response);

		
	}
	
}
