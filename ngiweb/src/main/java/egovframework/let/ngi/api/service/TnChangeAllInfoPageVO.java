package egovframework.let.ngi.api.service;

public class TnChangeAllInfoPageVO {

	private int pageUnit = 10; /* 검색결과 페이지당 출력 건수 */
	private int pageNum = 1; /* 현재 페이지 번호 */
	private int pageFirstNum = 1; /* 첫 페이지 번호 */
	private int pageLastNum; /* 마지막 페이지 번호 */
	private int pageTotalCnt; /* 총 페이지 수 */
	private int unitTotalCnt; /* 전체 검색결과 건수 */

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageFirstNum() {
		return pageFirstNum;
	}

	public void setPageFirstNum(int pageFirstNum) {
		this.pageFirstNum = pageFirstNum;
	}

	public int getPageLastNum() {
		return pageLastNum;
	}

	public void setPageLastNum(int pageLastNum) {
		this.pageLastNum = pageLastNum;
	}

	public int getPageTotalCnt() {
		return pageTotalCnt;
	}

	public void setPageTotalCnt(int pageTotalCnt) {
		this.pageTotalCnt = pageTotalCnt;
	}

	public int getUnitTotalCnt() {
		return unitTotalCnt;
	}

	public void setUnitTotalCnt(int unitTotalCnt) {
		this.unitTotalCnt = unitTotalCnt;
	}

}
