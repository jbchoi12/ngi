package egovframework.let.ngi.tgn.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

import egovframework.let.ngi.tgn.service.TopographyNoticeDefaultVO;
import egovframework.let.ngi.udt.service.AcptncHistVO;
import egovframework.let.ngi.udt.service.UpdateOperationVO;

/**
 * 지형고시 관리를 위한 VO 클래스
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
public class TopographyNoticeVO extends TopographyNoticeDefaultVO {
	
	private static final long serialVersionUID = -6121555429182700356L;
	private Integer tpgrphNtfcId; //지형고시아이디
	private String ntfcNm; //고시명
	private String ntfcNo; //고시번호
	private String chrgDept; //담당부서
	private String telno; //전화번호
	private Date mesrPdBegin; //측량기간시작
	private Date mesrPdEnd; //측량기간끝
	private String mesrArea; //측량지역
	private String etcMatter; //기타사항
	private String cstdyPlace; //보관장소
	private Integer mapdmcA; //도엽A
	private Integer mapdmcB; //도엽
	private Integer mapdmcC; //도엽
	private Integer mapdmcD; //도엽
	private String register; //등록자
	private Date rgsde; //등록일
	private String updusr; //수정자
	private Date updde; //수정일
	private Date ntfcDe; //고시일자
	
	/** 11.15 추가 S */
	private String servcExcprofsCode; //용역 수행사 코드
	private String bsnsDstrc; //사업 지구
	private Integer bsnsDstrcId; //사업 지구 아이디
	private String deptCode; //용역수행사 부서코드
	
	private String updtInfoIds[]; //성과정보
	private Integer updtInfoId;
	
	private String atchFileId; //첨부파일아이디
	private String birefFileNm; //브리프 파일 이름
	
	private List<UpdateOperationVO> updtInfoMappingList = LazyList.decorate(new ArrayList<UpdateOperationVO>(),FactoryUtils.instantiateFactory(UpdateOperationVO.class));
	
	/** 11.15 추가 E */
	
	 /** 검색조건 start */
    private String searchCondition; //검색조건
    private String searchKeyword; //검색Keyword
    private Date searchDateSMesrPd; //측량기간시작조건
    private Date searchDateEMesrPd; //측량기간끝조건
    private Date searchDateSNtfcDe; //고시일자시작조건
    private Date searchDateENtfcDe; //고시일자끝조건
    
    /** 11.15 추가 S */
    private String searchBsnsDstrc; //사업지구조건
    private Integer searchBsnsDstrcId; //사업지구조건
    private Date searchDateSRgsde; //등록일시작조건
    private Date searchDateERgsde; //등록일끝조건
    /** 11.15 추가 E */
    /** 검색조건 end */
    
	public Integer getTpgrphNtfcId() {
		return tpgrphNtfcId;
	}
	public void setTpgrphNtfcId(Integer tpgrphNtfcId) {
		this.tpgrphNtfcId = tpgrphNtfcId;
	}
	public String getNtfcNm() {
		return ntfcNm;
	}
	public void setNtfcNm(String ntfcNm) {
		this.ntfcNm = ntfcNm;
	}
	public String getNtfcNo() {
		return ntfcNo;
	}
	public void setNtfcNo(String ntfcNo) {
		this.ntfcNo = ntfcNo;
	}
	public String getChrgDept() {
		return chrgDept;
	}
	public void setChrgDept(String chrgDept) {
		this.chrgDept = chrgDept;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public Date getMesrPdBegin() {
		return mesrPdBegin;
	}
	public void setMesrPdBegin(Date mesrPdBegin) {
		this.mesrPdBegin = mesrPdBegin;
	}
	public Date getMesrPdEnd() {
		return mesrPdEnd;
	}
	public void setMesrPdEnd(Date mesrPdEnd) {
		this.mesrPdEnd = mesrPdEnd;
	}
	public String getMesrArea() {
		return mesrArea;
	}
	public void setMesrArea(String mesrArea) {
		this.mesrArea = mesrArea;
	}
	public String getEtcMatter() {
		return etcMatter;
	}
	public void setEtcMatter(String etcMatter) {
		this.etcMatter = etcMatter;
	}
	public String getCstdyPlace() {
		return cstdyPlace;
	}
	public void setCstdyPlace(String cstdyPlace) {
		this.cstdyPlace = cstdyPlace;
	}
	public Integer getMapdmcA() {
		return mapdmcA;
	}
	public void setMapdmcA(Integer mapdmcA) {
		this.mapdmcA = mapdmcA;
	}
	public Integer getMapdmcB() {
		return mapdmcB;
	}
	public void setMapdmcB(Integer mapdmcB) {
		this.mapdmcB = mapdmcB;
	}
	public Integer getMapdmcC() {
		return mapdmcC;
	}
	public void setMapdmcC(Integer mapdmcC) {
		this.mapdmcC = mapdmcC;
	}
	public Integer getMapdmcD() {
		return mapdmcD;
	}
	public void setMapdmcD(Integer mapdmcD) {
		this.mapdmcD = mapdmcD;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public Date getRgsde() {
		return rgsde;
	}
	public void setRgsde(Date rgsde) {
		this.rgsde = rgsde;
	}
	public String getUpdusr() {
		return updusr;
	}
	public void setUpdusr(String updusr) {
		this.updusr = updusr;
	}
	public Date getUpdde() {
		return updde;
	}
	public void setUpdde(Date updde) {
		this.updde = updde;
	}
	public Date getNtfcDe() {
		return ntfcDe;
	}
	public void setNtfcDe(Date ntfcDe) {
		this.ntfcDe = ntfcDe;
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
	public Date getSearchDateSMesrPd() {
		return searchDateSMesrPd;
	}
	public void setSearchDateSMesrPd(Date searchDateSMesrPd) {
		this.searchDateSMesrPd = searchDateSMesrPd;
	}
	public Date getSearchDateEMesrPd() {
		return searchDateEMesrPd;
	}
	public void setSearchDateEMesrPd(Date searchDateEMesrPd) {
		this.searchDateEMesrPd = searchDateEMesrPd;
	}
	public Date getSearchDateSNtfcDe() {
		return searchDateSNtfcDe;
	}
	public void setSearchDateSNtfcDe(Date searchDateSNtfcDe) {
		this.searchDateSNtfcDe = searchDateSNtfcDe;
	}
	public Date getSearchDateENtfcDe() {
		return searchDateENtfcDe;
	}
	public void setSearchDateENtfcDe(Date searchDateENtfcDe) {
		this.searchDateENtfcDe = searchDateENtfcDe;
	}
	public String getSearchBsnsDstrc() {
		return searchBsnsDstrc;
	}
	public void setSearchBsnsDstrc(String searchBsnsDstrc) {
		this.searchBsnsDstrc = searchBsnsDstrc;
	}
	public Date getSearchDateSRgsde() {
		return searchDateSRgsde;
	}
	public void setSearchDateSRgsde(Date searchDateSRgsde) {
		this.searchDateSRgsde = searchDateSRgsde;
	}
	public Date getSearchDateERgsde() {
		return searchDateERgsde;
	}
	public void setSearchDateERgsde(Date searchDateERgsde) {
		this.searchDateERgsde = searchDateERgsde;
	}
	public String getServcExcprofsCode() {
		return servcExcprofsCode;
	}
	public void setServcExcprofsCode(String servcExcprofsCode) {
		this.servcExcprofsCode = servcExcprofsCode;
	}
	public String getBsnsDstrc() {
		return bsnsDstrc;
	}
	public void setBsnsDstrc(String bsnsDstrc) {
		this.bsnsDstrc = bsnsDstrc;
	}
	public String[] getUpdtInfoIds() {
		return updtInfoIds;
	}
	public void setUpdtInfoIds(String[] updtInfoIds) {
		this.updtInfoIds = updtInfoIds;
	}
	public Integer getUpdtInfoId() {
		return updtInfoId;
	}
	public void setUpdtInfoId(Integer updtInfoId) {
		this.updtInfoId = updtInfoId;
	}
	public List<UpdateOperationVO> getUpdtInfoMappingList() {
		return updtInfoMappingList;
	}
	public void setUpdtInfoMappingList(List<UpdateOperationVO> updtInfoMappingList) {
		this.updtInfoMappingList = updtInfoMappingList;
	}
	public Integer getBsnsDstrcId() {
		return bsnsDstrcId;
	}
	public void setBsnsDstrcId(Integer bsnsDstrcId) {
		this.bsnsDstrcId = bsnsDstrcId;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public Integer getSearchBsnsDstrcId() {
		return searchBsnsDstrcId;
	}
	public void setSearchBsnsDstrcId(Integer searchBsnsDstrcId) {
		this.searchBsnsDstrcId = searchBsnsDstrcId;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getBirefFileNm() {
		return birefFileNm;
	}
	public void setBirefFileNm(String birefFileNm) {
		this.birefFileNm = birefFileNm;
	}
}

