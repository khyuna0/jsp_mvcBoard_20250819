package com.khyuna0.dto;

public class BoardMemberDto { 
	
	private int bnum; // 게시판 글 번호
	private String btitle; 
	private String bcontents;
	private String memberid; // 글쓴이 아이디
	private String memberemail; // 글쓴이 이메일
	private int bhit; // 글 조회수
	private String bdate; // 등록일
	public BoardMemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardMemberDto(int bnum, String btitle, String bcontents, String memberid, String memberemail, int bhit,
			String bdate) {
		super();
		this.bnum = bnum;
		this.btitle = btitle;
		this.bcontents = bcontents;
		this.memberid = memberid;
		this.memberemail = memberemail;
		this.bhit = bhit;
		this.bdate = bdate;
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
	public String getMemberemail() {
		return memberemail;
	}
	public void setMemberemail(String memberemail) {
		this.memberemail = memberemail;
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
	
	
	
}
