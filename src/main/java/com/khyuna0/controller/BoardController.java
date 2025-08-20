package com.khyuna0.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.khyuna0.dao.BoardDao;
import com.khyuna0.dto.BoardDto;


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
		List<BoardDto> bDtos = new ArrayList<BoardDto>();

		if(comm.equals("/List.do")) { // 게시판 모든 글 목록 보기 요청
			bDtos = boardDao.boardList();
			request.setAttribute("bDtos", bDtos);
			viewPage = "boardList.jsp";	
		} else if (comm.equals("/boardWrite.do")) { // 게시판 글쓰기
			viewPage = "boardWrite.jsp";
		} else if (comm.equals("/modifyForm.do")) { // 글 수정
			viewPage = "modifyForm.jsp";
		} else if (comm.equals("/delete.do")) { // 글 삭제 후 글 목록 이동
			viewPage = "List.do";
		} else if (comm.equals("/View.do")) { // 글 목록에서 선택된 글 목록 내용이 보여지는 페이지로 이동
			String bnum = request.getParameter("bnum");
			BoardDto boardDto = boardDao.contentView(bnum);
			//System.out.println(boardDto);
			
			if (boardDto != null ) {
				request.setAttribute("boardDto", boardDto);
				viewPage = "contentsView.jsp"; 
			} else {
				request.setAttribute("errorMsg", "존재하지 않는 페이지입니다.");
				viewPage = "contentsView.jsp"; 
			}
			
				
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
