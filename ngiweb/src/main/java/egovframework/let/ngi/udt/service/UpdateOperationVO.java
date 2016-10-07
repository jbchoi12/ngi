package egovframework.let.ngi.udt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.lang.builder.ToStringBuilder;

import egovframework.let.ngi.chg.service.ChangeInfoVO;

public class UpdateOperationVO extends UpdateOperationDefaultVO {

	private static final long serialVersionUID = 1L;
	private Integer updtInfoId; //갱신 정보 아이디
	private String servcExcprofsCode; //용역 수행사 코드
	private String opertNm; //작업 명
	private String bsnsDstrc; //사업 지구
//	private Integer mapdmcA; //도엽 A
//	private Integer mapdmcB; //도엽 B
//	private Integer mapdmcC; //도엽 C
	private Integer mapdmcD; //도엽 D
	private Date mesrDe; //측량일자
	private String register; //등록자
	private Date rgsde; //등록일
	private String updusr; //수정자
	private Date updde; //수정일
	private String wrter; //작성자
	private String sttus; //상태
	
	/** 추가 정보 11.13 S *****************/
	private MapdmcVO mapdmcVO; //도엽
	
	private String mapdmcA; //도엽 A
	private String mapdmcB; //도엽 B
	private String mapdmcC; //도엽 C
	private String mapdmcNmA; //도엽명 A
	private String mapdmcNmB; //도엽명 B
	private String mapdmcNmC; //도엽명 C
	private String cntrwkPnttm; //공사시점
	private String cntrwkTmnl; //공사종점
	private String changeTy; //변동유형
	private String manp; //제원 (포털기준 타입변경 integer -> string 2014.11.26)
	private String unit; //단위
	private String cntm; //좌표계
	private String oval; //타원체
	private String trnsprclaw; //투영법
	private String trgnpt; //원점
	private String phtogrfYear; //촬영년도
	private String examinYear; //조사년도
	private String updtYear; //수정년도
	private String updtMt; //수정월
	
	private Integer tpgrphNtfcId; //지형고시아이디
	/** 추가 정보 11.13 E *****************/
	
	private List<AcptncHistVO> acptncHistList = LazyList.decorate(new ArrayList<AcptncHistVO>(),FactoryUtils.instantiateFactory(AcptncHistVO.class));
	
	private String[] changeInfoIds;
	private Integer changeInfoId;
	
	private String menuType;
	private String msg; // 알림메세지

	private String[] updtInfoIds; //멀티 검수완료
	
	private List<ChangeInfoVO> changeInfoList = LazyList.decorate(new ArrayList<ChangeInfoVO>(),FactoryUtils.instantiateFactory(ChangeInfoVO.class));
	
	private List<UpdateOperationFileVO> updateOperationFileList = LazyList.decorate(new ArrayList<UpdateOperationFileVO>(),FactoryUtils.instantiateFactory(UpdateOperationFileVO.class));
	
	/** 검색조건 start */
    private String searchBsnsDstrc; //사업지구조건
    private String searchSttus; //상태조건
    private Date searchDateSRgsde; //등록일시작
    private Date searchDateERgsde; //등록일끝
    private String searchCondition; //검색조건
    private String searchKeyword; //검색Keyword
    private String searchChangeTy; //검색유형 (추가 11.15)
    /** 검색조건 end */
    
	private String dextuploadnx_instance1_path;
	private String dextuploadnx_instance2_path;
	private String dextuploadnx_instance3_path;
	private String dextuploadnx_instance4_path;
	
	private Integer fileId;
	private String fileOrgNm;
	
	private String XCrdntLo;
	private String YCrdntLa;
	private String vectorList;
	private String location;
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	private String cntrwkNo;
	
	public String getCntrwkNo() {
		return cntrwkNo;
	}
	public void setCntrwkNo(String cntrwkNo) {
		this.cntrwkNo = cntrwkNo;
	}
	public String getXCrdntLo() {
		return XCrdntLo;
	}
	public void setXCrdntLo(String xCrdntLo) {
		XCrdntLo = xCrdntLo;
	}
	public String getYCrdntLa() {
		return YCrdntLa;
	}
	public void setYCrdntLa(String yCrdntLa) {
		YCrdntLa = yCrdntLa;
	}
	public String getVectorList() {
		return vectorList;
	}
	public void setVectorList(String vectorList) {
		this.vectorList = vectorList;
	}
	public Integer getUpdtInfoId() {
		return updtInfoId;
	}
	public void setUpdtInfoId(Integer updtInfoId) {
		this.updtInfoId = updtInfoId;
	}
	public String getServcExcprofsCode() {
		return servcExcprofsCode;
	}
	public void setServcExcprofsCode(String servcExcprofsCode) {
		this.servcExcprofsCode = servcExcprofsCode;
	}
	public String getOpertNm() {
		return opertNm;
	}
	public void setOpertNm(String opertNm) {
		this.opertNm = opertNm;
	}
	public String getBsnsDstrc() {
		return bsnsDstrc;
	}
	public void setBsnsDstrc(String bsnsDstrc) {
		this.bsnsDstrc = bsnsDstrc;
	}
	public Integer getMapdmcD() {
		return mapdmcD;
	}
	public void setMapdmcD(Integer mapdmcD) {
		this.mapdmcD = mapdmcD;
	}
	public Date getMesrDe() {
		return mesrDe;
	}
	public void setMesrDe(Date mesrDe) {
		this.mesrDe = mesrDe;
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
	public String getWrter() {
		return wrter;
	}
	public void setWrter(String wrter) {
		this.wrter = wrter;
	}
	public String getSttus() {
		return sttus;
	}
	public void setSttus(String sttus) {
		this.sttus = sttus;
	}
	public MapdmcVO getMapdmcVO() {
		return mapdmcVO;
	}
	public void setMapdmcVO(MapdmcVO mapdmcVO) {
		this.mapdmcVO = mapdmcVO;
	}
	public String getMapdmcA() {
		return mapdmcA;
	}
	public void setMapdmcA(String mapdmcA) {
		this.mapdmcA = mapdmcA;
	}
	public String getMapdmcB() {
		return mapdmcB;
	}
	public void setMapdmcB(String mapdmcB) {
		this.mapdmcB = mapdmcB;
	}
	public String getMapdmcC() {
		return mapdmcC;
	}
	public void setMapdmcC(String mapdmcC) {
		this.mapdmcC = mapdmcC;
	}
	public String getMapdmcNmA() {
		return mapdmcNmA;
	}
	public void setMapdmcNmA(String mapdmcNmA) {
		this.mapdmcNmA = mapdmcNmA;
	}
	public String getMapdmcNmB() {
		return mapdmcNmB;
	}
	public void setMapdmcNmB(String mapdmcNmB) {
		this.mapdmcNmB = mapdmcNmB;
	}
	public String getMapdmcNmC() {
		return mapdmcNmC;
	}
	public void setMapdmcNmC(String mapdmcNmC) {
		this.mapdmcNmC = mapdmcNmC;
	}
	public String getCntrwkPnttm() {
		return cntrwkPnttm;
	}
	public void setCntrwkPnttm(String cntrwkPnttm) {
		this.cntrwkPnttm = cntrwkPnttm;
	}
	public String getCntrwkTmnl() {
		return cntrwkTmnl;
	}
	public void setCntrwkTmnl(String cntrwkTmnl) {
		this.cntrwkTmnl = cntrwkTmnl;
	}
	public String getChangeTy() {
		return changeTy;
	}
	public void setChangeTy(String changeTy) {
		this.changeTy = changeTy;
	}
	public String getManp() {
		return manp;
	}
	public void setManp(String manp) {
		this.manp = manp;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCntm() {
		return cntm;
	}
	public void setCntm(String cntm) {
		this.cntm = cntm;
	}
	public String getOval() {
		return oval;
	}
	public void setOval(String oval) {
		this.oval = oval;
	}
	public String getTrnsprclaw() {
		return trnsprclaw;
	}
	public void setTrnsprclaw(String trnsprclaw) {
		this.trnsprclaw = trnsprclaw;
	}
	public String getTrgnpt() {
		return trgnpt;
	}
	public void setTrgnpt(String trgnpt) {
		this.trgnpt = trgnpt;
	}
	public String getPhtogrfYear() {
		return phtogrfYear;
	}
	public void setPhtogrfYear(String phtogrfYear) {
		this.phtogrfYear = phtogrfYear;
	}
	public String getExaminYear() {
		return examinYear;
	}
	public void setExaminYear(String examinYear) {
		this.examinYear = examinYear;
	}
	public String getUpdtYear() {
		return updtYear;
	}
	public void setUpdtYear(String updtYear) {
		this.updtYear = updtYear;
	}
	public String getUpdtMt() {
		return updtMt;
	}
	public void setUpdtMt(String updtMt) {
		this.updtMt = updtMt;
	}
	public List<AcptncHistVO> getAcptncHistList() {
		return acptncHistList;
	}
	public void setAcptncHistList(List<AcptncHistVO> acptncHistList) {
		this.acptncHistList = acptncHistList;
	}
	public String[] getChangeInfoIds() {
		return changeInfoIds;
	}
	public void setChangeInfoIds(String[] changeInfoIds) {
		this.changeInfoIds = changeInfoIds;
	}
	public Integer getChangeInfoId() {
		return changeInfoId;
	}
	public void setChangeInfoId(Integer changeInfoId) {
		this.changeInfoId = changeInfoId;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String[] getUpdtInfoIds() {
		return updtInfoIds;
	}
	public void setUpdtInfoIds(String[] updtInfoIds) {
		this.updtInfoIds = updtInfoIds;
	}
	public List<ChangeInfoVO> getChangeInfoList() {
		return changeInfoList;
	}
	public void setChangeInfoList(List<ChangeInfoVO> changeInfoList) {
		this.changeInfoList = changeInfoList;
	}
	public List<UpdateOperationFileVO> getUpdateOperationFileList() {
		return updateOperationFileList;
	}
	public void setUpdateOperationFileList(
			List<UpdateOperationFileVO> updateOperationFileList) {
		this.updateOperationFileList = updateOperationFileList;
	}
	public String getSearchBsnsDstrc() {
		return searchBsnsDstrc;
	}
	public void setSearchBsnsDstrc(String searchBsnsDstrc) {
		this.searchBsnsDstrc = searchBsnsDstrc;
	}
	public String getSearchSttus() {
		return searchSttus;
	}
	public void setSearchSttus(String searchSttus) {
		this.searchSttus = searchSttus;
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
	public String getDextuploadnx_instance1_path() {
		return dextuploadnx_instance1_path;
	}
	public void setDextuploadnx_instance1_path(String dextuploadnx_instance1_path) {
		this.dextuploadnx_instance1_path = dextuploadnx_instance1_path;
	}
	public String getDextuploadnx_instance2_path() {
		return dextuploadnx_instance2_path;
	}
	public void setDextuploadnx_instance2_path(String dextuploadnx_instance2_path) {
		this.dextuploadnx_instance2_path = dextuploadnx_instance2_path;
	}
	public String getDextuploadnx_instance3_path() {
		return dextuploadnx_instance3_path;
	}
	public void setDextuploadnx_instance3_path(String dextuploadnx_instance3_path) {
		this.dextuploadnx_instance3_path = dextuploadnx_instance3_path;
	}
	public String getDextuploadnx_instance4_path() {
		return dextuploadnx_instance4_path;
	}
	public void setDextuploadnx_instance4_path(String dextuploadnx_instance4_path) {
		this.dextuploadnx_instance4_path = dextuploadnx_instance4_path;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFileOrgNm() {
		return fileOrgNm;
	}
	public void setFileOrgNm(String fileOrgNm) {
		this.fileOrgNm = fileOrgNm;
	}
	public String getSearchChangeTy() {
		return searchChangeTy;
	}
	public void setSearchChangeTy(String searchChangeTy) {
		this.searchChangeTy = searchChangeTy;
	}
	public Integer getTpgrphNtfcId() {
		return tpgrphNtfcId;
	}
	public void setTpgrphNtfcId(Integer tpgrphNtfcId) {
		this.tpgrphNtfcId = tpgrphNtfcId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
