package com.khyuna0.dto;

public class BoardDto {
	
	private int bno; // 새로운 글 게시판 번호
	private int bnum; // 게시판 글 번호
	private String btitle; 
	private String bcontents;
	private String memberid; // 글쓴이 아이디
	private int bhit; // 글 조회수
	private String bdate; // 등록일
	
	private MemberDto memberDto; 
	
	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub		
	}

	public BoardDto(int bno, int bnum, String btitle, String bcontents, String memberid, int bhit, String bdate,
			MemberDto memberDto) {
		super();
		this.bno = bno;
		this.bnum = bnum;
		this.btitle = btitle;
		this.bcontents = bcontents;
		this.memberid = memberid;
		this.bhit = bhit;
		this.bdate = bdate;
		this.memberDto = memberDto;
	}
	
	public BoardDto(int bnum, String btitle, String bcontents, String memberid, int bhit, String bdate
			) {
		super();
		this.bnum = bnum;
		this.btitle = btitle;
		this.bcontents = bcontents;
		this.memberid = memberid;
		this.bhit = bhit;
		this.bdate = bdate;

	}
	
	public BoardDto(int bnum, String btitle, String bcontents, String memberid, int bhit, String bdate,
			MemberDto memberDto) {
		super();
		
		this.bnum = bnum;
		this.btitle = btitle;
		this.bcontents = bcontents;
		this.memberid = memberid;
		this.bhit = bhit;
		this.bdate = bdate;
		this.memberDto = memberDto;
	}
	
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontents() {
		return bcontents;
	}

	public void setBcontents(String bcontents) {
		this.bcontents = bcontents;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public int getBhit() {
		return bhit;
	}

	public void setBhit(int bhit) {
		this.bhit = bhit;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	

	
	
}
