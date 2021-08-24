package common;

public class PageManager {
	// 페이지 당 글 개수
	private int articleCountPerPage = 10;
	
	// 페이지 그룹의 페이지 개수
	private int pageGroupCount = 10;
	
	// 현재 페이지
	private int nowPageNumber;
	
	// 글 총 개수
	private int totalArticleCount;
	
	// 페이지에서 시작하는 글 번호
	private int startArticleNumber;
	
	// 페이지 총 개수
	private int totalPageCount;
	
	// 시작 페이지 번호
	private int startPageNumber;
	
	// 끝 페이지 번호
	private int endPageNumber;
	
	// 이전 페이지 번호
	private int prePageNumber;
	
	// 다음 페이지 번호
	private int nextPageNumber;

	public PageManager(int nowPageNumber, int totalArticleCount) {
		super();
		// 현재 페이지 세팅
		this.nowPageNumber = nowPageNumber;
		// 글 총 개수 세팅
		this.totalArticleCount = totalArticleCount;
		
		// 총 페이지 개수 계산
		this.totalPageCount = (int) Math.ceil((double) this.totalArticleCount / this.articleCountPerPage);
		if (this.totalPageCount < 1) {
			this.totalPageCount = 1;
		}
		
		// 현재 페이지가 페이지 개수보다 클 때 마지막 페이지로 세팅
		if (this.nowPageNumber > this.totalPageCount) {
			this.nowPageNumber = this.totalPageCount;
		}
		
		// 이전 페이지 계산
		this.prePageNumber = this.nowPageNumber - 1;
		if (this.prePageNumber < 1) {
			this.prePageNumber = 1;
		}
		
		// 다음 페이지 계산
		this.nextPageNumber = this.nowPageNumber + 1;
		if (this.nextPageNumber > this.totalPageCount) {
			this.nextPageNumber = this.totalPageCount;
		}
		
		// row번호 계산 -> limit 0,10 limit 10,10
		this.startArticleNumber = (this.nowPageNumber - 1) * this.articleCountPerPage;
		
		// 페이지 그룹의 시작 페이지번호와 끝 페이지 번호 계산
		// 1,2,3,4,5 그룹일 때 시작 : 1, 끝 : 5
		// 6,7,8,9,10 그룹일 때 시작 : 6, 끝 : 10
		// 11,12,13,14,15 그룹일 때 시작 : 11 끝 : 15
		// 16,17,18,19,20 그룹일 때 시작 : 16 끝 : 20
		this.startPageNumber = ((int) (((double) this.nowPageNumber / this.pageGroupCount + 0.9) - 1)) * this.pageGroupCount + 1;
		this.endPageNumber = this.startPageNumber + (this.pageGroupCount - 1);
		if (this.endPageNumber > this.totalPageCount) {
			this.endPageNumber = this.totalPageCount;
		}
		
		
	}

	public int getArticleCountPerPage() {
		return articleCountPerPage;
	}

	public void setArticleCountPerPage(int articleCountPerPage) {
		this.articleCountPerPage = articleCountPerPage;
	}

	public int getPageGroupCount() {
		return pageGroupCount;
	}

	public void setPageGroupCount(int pageGroupCount) {
		this.pageGroupCount = pageGroupCount;
	}

	public int getNowPageNumber() {
		return nowPageNumber;
	}

	public void setNowPageNumber(int nowPageNumber) {
		this.nowPageNumber = nowPageNumber;
	}

	public int getTotalArticleCount() {
		return totalArticleCount;
	}

	public void setTotalArticleCount(int totalArticleCount) {
		this.totalArticleCount = totalArticleCount;
	}

	public int getStartArticleNumber() {
		return startArticleNumber;
	}

	public void setStartArticleNumber(int startArticleNumber) {
		this.startArticleNumber = startArticleNumber;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getStartPageNumber() {
		return startPageNumber;
	}

	public void setStartPageNumber(int startPageNumber) {
		this.startPageNumber = startPageNumber;
	}

	public int getEndPageNumber() {
		return endPageNumber;
	}

	public void setEndPageNumber(int endPageNumber) {
		this.endPageNumber = endPageNumber;
	}

	public int getPrePageNumber() {
		return prePageNumber;
	}

	public void setPrePageNumber(int prePageNumber) {
		this.prePageNumber = prePageNumber;
	}

	public int getNextPageNumber() {
		return nextPageNumber;
	}

	public void setNextPageNumber(int nextPageNumber) {
		this.nextPageNumber = nextPageNumber;
	}
}
















