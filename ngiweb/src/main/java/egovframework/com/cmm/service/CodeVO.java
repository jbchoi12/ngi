package egovframework.com.cmm.service;

import java.io.Serializable;


/**
 * 코드 관리를 위한 상세 VO 클래스를 정의한다.
 * @author kka
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *	
 *  </pre>
 */
public class CodeVO extends SearchVO implements Serializable {
	private static final long serialVersionUID = -7184736475163501066L;
	
	private String code;
	private String codeNm;
	
	//코드관리메뉴
	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색조건 */
    private String searchConditionComm = "";
    
    /** 검색Keyword */
    private String searchKeywordComm = "";
    
    /** 검색조건 */
    private String searchConditionDetail = "";
    
    /** 검색Keyword */
    private String searchKeywordDetail = "";
    
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    
    public String getSearchConditionComm() {
		return searchConditionComm;
	}

	public void setSearchConditionComm(String searchConditionComm) {
		this.searchConditionComm = searchConditionComm;
	}

	public String getSearchKeywordComm() {
		return searchKeywordComm;
	}

	public void setSearchKeywordComm(String searchKeywordComm) {
		this.searchKeywordComm = searchKeywordComm;
	}

	public String getSearchConditionDetail() {
		return searchConditionDetail;
	}

	public void setSearchConditionDetail(String searchConditionDetail) {
		this.searchConditionDetail = searchConditionDetail;
	}

	public String getSearchKeywordDetail() {
		return searchKeywordDetail;
	}

	public void setSearchKeywordDetail(String searchKeywordDetail) {
		this.searchKeywordDetail = searchKeywordDetail;
	}

	/*
	 * 분류코드
	 */
	private String clCode = "";
	
	/*
	 * 분류코드명
	 */
    private String clCodeNm = "";
    
    /*
     * 분류코드설명
     */
    private String clCodeDc = "";
    
    /*
	 * 코드ID
	 */
	private String codeId = "";
	
	/*
	 * 코드ID명
	 */
	private String codeIdNm = "";
	
	/*
	 * 코드ID설명
	 */
	private String codeIdDc = "";
	
	private String DetailcodeId = "";
    
    public String getDetailcodeId() {
		return DetailcodeId;
	}

	public void setDetailcodeId(String detailcodeId) {
		DetailcodeId = detailcodeId;
	}

	public String getCodeIdNm() {
		return codeIdNm;
	}

	public void setCodeIdNm(String codeIdNm) {
		this.codeIdNm = codeIdNm;
	}

	public String getCodeIdDc() {
		return codeIdDc;
	}

	public void setCodeIdDc(String codeIdDc) {
		this.codeIdDc = codeIdDc;
	}

	/*
     * 사용여부
     */
    private String useAt = "";
    
    /*
     * 최초등록자ID
     */
    private String frstRegisterId = "";
    
    /*
     * 최종수정자ID
     */
    private String lastUpdusrId   = "";
    
    
    private String plag   = "";
    
    
    private String codeDc   = "";

	
	public String getCodeDc() {
		return codeDc;
	}

	public void setCodeDc(String codeDc) {
		this.codeDc = codeDc;
	}

	public String getPlag() {
		return plag;
	}

	public void setPlag(String plag) {
		this.plag = plag;
	}

	public CodeVO() {
		
	}
	
	public CodeVO(String codeId, String code) {
		this.codeId = codeId;
		this.code = code;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
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

	public String getClCode() {
		return clCode;
	}

	public void setClCode(String clCode) {
		this.clCode = clCode;
	}

	public String getClCodeNm() {
		return clCodeNm;
	}

	public void setClCodeNm(String clCodeNm) {
		this.clCodeNm = clCodeNm;
	}

	public String getClCodeDc() {
		return clCodeDc;
	}

	public void setClCodeDc(String clCodeDc) {
		this.clCodeDc = clCodeDc;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
