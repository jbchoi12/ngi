package egovframework.let.ngi.udt.service;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class UpdateOperationDefaultVO implements Serializable  {

	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 5;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    
	private String dokak_select = null;

	private String dokak_select_sub0 = null;
	
	private String dokak_select_sub1 = null;

	private String mapBBOX = null;    
    
        
	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getDokak_select() {
		return dokak_select;
	}

	public void setDokak_select(String dokak_select) {
		this.dokak_select = dokak_select;
	}

	public String getDokak_select_sub0() {
		return dokak_select_sub0;
	}

	public void setDokak_select_sub0(String dokak_select_sub0) {
		this.dokak_select_sub0 = dokak_select_sub0;
	}

	public String getDokak_select_sub1() {
		return dokak_select_sub1;
	}

	public void setDokak_select_sub1(String dokak_select_sub1) {
		this.dokak_select_sub1 = dokak_select_sub1;
	}

	public String getMapBBOX() {
		return mapBBOX;
	}

	public void setMapBBOX(String mapBBOX) {
		this.mapBBOX = mapBBOX;
	}

	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	
	
}
