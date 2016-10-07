package egovframework.let.ngi.chg.service;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @Class Name : TnChangeInfoDefaultVO.java
 * @Description : TnChangeInfo Default VO class
 * @Modification Information
 * 
 * @author 이정연
 * @since 2014-09-29
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */
public class ChangeInfoDefaultVO implements Serializable {

	/** 검색조건 */
	private String searchCondition = "";

	private String sttemntTy = "";

	/** 검색Keyword */
	private String searchKeyword = "";

	/** 검색사용여부 */
	private String searchUseYn = "";

	/** change_cl */
	private String changeCl;

	/** process_sttus_se */
	private String processSttusSe;

	/** change_sj */
	private String changeSj;

	/** register_id */
	private String registerId;

	/** register_nm */
	private String registerNm;

	/** regist_de */
	private Date registDe;

	private String bsnsDstrc;

	private Date ntceBgnde;

	private Date ntceEndde;

	private String changeTy;
	
	private String trgetAreaNm;

	private String psitnEngnNo;

	private String planEngnNo;

	private String sidoCd;

	private String gugunCd;

	private String dongCd;

	private Date strwrkBgnde;

	private Date strwrkEndde;

	private Date competPrearngeBgnde;

	private Date competPrearngeEndde;

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

	private String location = null;

	private String dokak_select = null;

	private String dokak_select_sub0 = null;

	private String dokak_select_sub1 = null;

	private String mapBBOX = null;

	private Integer fileCode01 = null;
	private Integer fileCode02 = null;
	private Integer fileCode03 = null;

	private String cntrwkNo;
	
	private String insttNm;	
	
	public Integer getFileCode01() {
		return fileCode01;
	}

	public void setFileCode01(Integer fileCode01) {
		this.fileCode01 = fileCode01;
	}

	public Integer getFileCode02() {
		return fileCode02;
	}

	public void setFileCode02(Integer fileCode02) {
		this.fileCode02 = fileCode02;
	}

	public Integer getFileCode03() {
		return fileCode03;
	}

	public void setFileCode03(Integer fileCode03) {
		this.fileCode03 = fileCode03;
	}

	/** change_info_id */
	private Integer changeInfoId;

	public String getMapBBOX() {
		return mapBBOX;
	}

	public void setMapBBOX(String mapBBOX) {
		this.mapBBOX = mapBBOX;
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

	public String getDokak_select() {
		return dokak_select;
	}

	public void setDokak_select(String dokak_select) {
		this.dokak_select = dokak_select;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

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

	public String getChangeCl() {
		return this.changeCl;
	}

	public void setChangeCl(String changeCl) {
		this.changeCl = changeCl;
	}

	public String getProcessSttusSe() {
		return this.processSttusSe;
	}

	public void setProcessSttusSe(String processSttusSe) {
		this.processSttusSe = processSttusSe;
	}

	public String getChangeSj() {
		return this.changeSj;
	}

	public void setChangeSj(String changeSj) {
		this.changeSj = changeSj;
	}

	public String getRegisterNm() {
		return this.registerNm;
	}

	public void setRegisterNm(String registerNm) {
		this.registerNm = registerNm;
	}

	public Date getRegistDe() {
		return this.registDe;
	}

	public void setRegistDe(Date registDe) {
		this.registDe = registDe;
	}

	public String getBsnsDstrc() {
		return bsnsDstrc;
	}

	public void setBsnsDstrc(String bsnsDstrc) {
		this.bsnsDstrc = bsnsDstrc;
	}

	public Date getNtceBgnde() {
		return ntceBgnde;
	}

	public void setNtceBgnde(Date ntceBgnde) {
		this.ntceBgnde = ntceBgnde;
	}

	public Date getNtceEndde() {
		return ntceEndde;
	}

	public void setNtceEndde(Date ntceEndde) {
		this.ntceEndde = ntceEndde;
	}

	public String getPsitnEngnNo() {
		return psitnEngnNo;
	}

	public void setPsitnEngnNo(String psitnEngnNo) {
		this.psitnEngnNo = psitnEngnNo;
	}

	public String getPlanEngnNo() {
		return planEngnNo;
	}

	public void setPlanEngnNo(String planEngnNo) {
		this.planEngnNo = planEngnNo;
	}

	public String getSidoCd() {
		return sidoCd;
	}

	public void setSidoCd(String sidoCd) {
		this.sidoCd = sidoCd;
	}

	public String getGugunCd() {
		return gugunCd;
	}

	public void setGugunCd(String gugunCd) {
		this.gugunCd = gugunCd;
	}

	public String getDongCd() {
		return dongCd;
	}

	public void setDongCd(String dongCd) {
		this.dongCd = dongCd;
	}

	public Date getStrwrkBgnde() {
		return strwrkBgnde;
	}

	public void setStrwrkBgnde(Date strwrkBgnde) {
		this.strwrkBgnde = strwrkBgnde;
	}

	public Date getStrwrkEndde() {
		return strwrkEndde;
	}

	public void setStrwrkEndde(Date strwrkEndde) {
		this.strwrkEndde = strwrkEndde;
	}

	public Date getCompetPrearngeBgnde() {
		return competPrearngeBgnde;
	}

	public void setCompetPrearngeBgnde(Date competPrearngeBgnde) {
		this.competPrearngeBgnde = competPrearngeBgnde;
	}

	public Date getCompetPrearngeEndde() {
		return competPrearngeEndde;
	}

	public void setCompetPrearngeEndde(Date competPrearngeEndde) {
		this.competPrearngeEndde = competPrearngeEndde;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getSttemntTy() {
		return sttemntTy;
	}

	public void setSttemntTy(String sttemntTy) {
		this.sttemntTy = sttemntTy;
	}

	public String getChangeTy() {
		return changeTy;
	}

	public void setChangeTy(String changeTy) {
		this.changeTy = changeTy;
	}

	public String getTrgetAreaNm() {
		return trgetAreaNm;
	}

	public void setTrgetAreaNm(String trgetAreaNm) {
		this.trgetAreaNm = trgetAreaNm;
	}	
	
	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public Integer getChangeInfoId() {
		return changeInfoId;
	}

	public void setChangeInfoId(Integer changeInfoId) {
		this.changeInfoId = changeInfoId;
	}

	public String getCntrwkNo() {
		return cntrwkNo;
	}

	public void setCntrwkNo(String cntrwkNo) {
		this.cntrwkNo = cntrwkNo;
	}

	public String getInsttNm() {
		return insttNm;
	}

	public void setInsttNm(String insttNm) {
		this.insttNm = insttNm;
	}

}
