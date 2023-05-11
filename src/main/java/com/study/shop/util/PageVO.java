package com.study.shop.util;

import lombok.ToString;

@ToString
public class PageVO {
	private int nowPage; //현재 선택된 페이지 번호
	private int totalDateCnt; //전체 데이터 수
	private int beginPage; //화면에 보이는 첫번째 페이지 번호
	private int endPage; //화면에 보이는 마지막 페이지 번호
	private int displayCnt; //한 페이지에 보여지는 게시글 
	private int displayPageCnt; //한번에 보여지는 페이지 수
	private boolean prev; //'이전' 버튼의 유무
	private boolean next; // '다음' 버튼의 유무
	private int startNum; // 시작 row_num
	private int endNum; // 마지막 row_num
	
	public PageVO() {
		nowPage = 1;
		displayCnt = 5;
		displayPageCnt = 3;
	}
	
	//이 메소드가 실행되면 page처리를 위한 모든 변수값 세팅
	public void setPageInfo() {
		//마지막에 보이는 페이지 번호
		endPage = displayPageCnt * (int)Math.ceil(nowPage / (double)displayPageCnt);
		
		//처음에 보이는 페이지 번호
		beginPage = endPage - displayPageCnt + 1;
		
		//전체페이지
		int totalPageCnt = (int)Math.ceil(totalDateCnt / (double)displayCnt);
		
		
		//next버튼 유무
		if(endPage < totalPageCnt) {
			next = true;
		}
		else {
			next = false;
			endPage = totalPageCnt;
		}
		
		//prev버튼 유무
		prev = beginPage == 1 ? false : true;
		
		if(totalPageCnt == 0) {
			endPage = 1;
		}
		
		
		//검색 시작과 마지막 row_num
		startNum = (nowPage - 1) * displayCnt + 1;
		endNum = nowPage * displayCnt;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalDateCnt() {
		return totalDateCnt;
	}

	public void setTotalDateCnt(int totalDateCnt) {
		this.totalDateCnt = totalDateCnt;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getDisplayCnt() {
		return displayCnt;
	}

	public void setDisplayCnt(int displayCnt) {
		this.displayCnt = displayCnt;
	}

	public int getDisplayPageCnt() {
		return displayPageCnt;
	}

	public void setDisplayPageCnt(int displayPageCnt) {
		this.displayPageCnt = displayPageCnt;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	
	
	
}
