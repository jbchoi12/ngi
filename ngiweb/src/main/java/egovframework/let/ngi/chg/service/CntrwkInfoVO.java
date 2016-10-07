package egovframework.let.ngi.chg.service;

import java.util.Date;

public class CntrwkInfoVO {

	/** change_info_id */
	private Integer changeInfoId; // 변동정보아이디

	/** engn_code */
	private String engnCode; // 기관코드

	/** cntrwk_pnttm */
	private String cntrwkPnttm; // 공사시점

	/** cntrwk_tmnl */
	private String cntrwkTmnl; // 공사종점

	/** ar */
	private String ar; // 면적

	/** extn */
	private String extn; // 연장

	/** change_trget_nm */
	private String changeTrgetNm; // 변경대상

	/** trget_bfchg_cn */
	private String trgetBfchgCn; // 대상변경전

	/** trget_aftch_cn */
	private String trgetAftchCn; // 대상변경후

	/** strwrk_de */
	private Date strwrkDe; // 착공일자

	/** compet_prearnge_de */
	private Date competPrearngeDe; // 완공예정일자

	/** last_compet_de */
	private Date lastCompetDe; // 착공완공일자

	/** compet_drw_file_stle_ty */
	private String competDrwFileStleTy; // 준공도면형태

	/** cntm */
	private String cntm; // 좌표계

	/** rm */
	private String rm; // 비고

	/** psitn_engn_no */
	private String psitnEngnNo; // 소송기관

	/** plan_engn_no */
	private String planEngnNo; // 계획기관

	/** mng_engn_no */
	private String mngEngnNo; // 감독기관

	/** charger_nm */
	private String chargerNm; // 담당자명

	/** chrg_dept_nm */
	private String chrgDeptNm; // 담당부서

	/** charger_tlphon_no */
	private String chargerTlphonNo; // 담당자전화번호

	/** charger_email */
	private String chargerEmail; // 담당자이메일

	/** acqs_mth_ty */
	private String acqsMthTy; // 취득방법

	/** sttus_ty */
	private String sttusTy; // 상태

	/** change_ty */
	private String changeTy; // 변동유형

	private String trgetAreaNm;

	private String changeSe;

	private String registTmpCd;
	
	private String cntrwkNo;
	
	private String vectorList;
	
	public String getVectorList() {
		return vectorList;
	}

	public void setVectorList(String vectorList) {
		this.vectorList = vectorList;
	}

	public Integer getChangeInfoId() {
		return changeInfoId;
	}

	public void setChangeInfoId(Integer changeInfoId) {
		this.changeInfoId = changeInfoId;
	}

	public String getEngnCode() {
		return engnCode;
	}

	public void setEngnCode(String engnCode) {
		this.engnCode = engnCode;
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

	public String getAr() {
		return ar;
	}

	public void setAr(String ar) {
		this.ar = ar;
	}

	public String getExtn() {
		return extn;
	}

	public void setExtn(String extn) {
		this.extn = extn;
	}

	public String getChangeTrgetNm() {
		return changeTrgetNm;
	}

	public void setChangeTrgetNm(String changeTrgetNm) {
		this.changeTrgetNm = changeTrgetNm;
	}

	public String getTrgetBfchgCn() {
		return trgetBfchgCn;
	}

	public void setTrgetBfchgCn(String trgetBfchgCn) {
		this.trgetBfchgCn = trgetBfchgCn;
	}

	public String getTrgetAftchCn() {
		return trgetAftchCn;
	}

	public void setTrgetAftchCn(String trgetAftchCn) {
		this.trgetAftchCn = trgetAftchCn;
	}

	public Date getStrwrkDe() {
		return strwrkDe;
	}

	public void setStrwrkDe(Date strwrkDe) {
		this.strwrkDe = strwrkDe;
	}

	public Date getCompetPrearngeDe() {
		return competPrearngeDe;
	}

	public void setCompetPrearngeDe(Date competPrearngeDe) {
		this.competPrearngeDe = competPrearngeDe;
	}

	public Date getLastCompetDe() {
		return lastCompetDe;
	}

	public void setLastCompetDe(Date lastCompetDe) {
		this.lastCompetDe = lastCompetDe;
	}

	public String getCompetDrwFileStleTy() {
		return competDrwFileStleTy;
	}

	public void setCompetDrwFileStleTy(String competDrwFileStleTy) {
		this.competDrwFileStleTy = competDrwFileStleTy;
	}

	public String getCntm() {
		return cntm;
	}

	public void setCntm(String cntm) {
		this.cntm = cntm;
	}

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
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

	public String getMngEngnNo() {
		return mngEngnNo;
	}

	public void setMngEngnNo(String mngEngnNo) {
		this.mngEngnNo = mngEngnNo;
	}

	public String getChargerNm() {
		return chargerNm;
	}

	public void setChargerNm(String chargerNm) {
		this.chargerNm = chargerNm;
	}

	public String getChrgDeptNm() {
		return chrgDeptNm;
	}

	public void setChrgDeptNm(String chrgDeptNm) {
		this.chrgDeptNm = chrgDeptNm;
	}

	public String getChargerTlphonNo() {
		return chargerTlphonNo;
	}

	public void setChargerTlphonNo(String chargerTlphonNo) {
		this.chargerTlphonNo = chargerTlphonNo;
	}

	public String getChargerEmail() {
		return chargerEmail;
	}

	public void setChargerEmail(String chargerEmail) {
		this.chargerEmail = chargerEmail;
	}

	public String getAcqsMthTy() {
		return acqsMthTy;
	}

	public void setAcqsMthTy(String acqsMthTy) {
		this.acqsMthTy = acqsMthTy;
	}

	public String getSttusTy() {
		return sttusTy;
	}

	public void setSttusTy(String sttusTy) {
		this.sttusTy = sttusTy;
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

	public String getChangeSe() {
		return changeSe;
	}

	public void setChangeSe(String changeSe) {
		this.changeSe = changeSe;
	}

	public String getRegistTmpCd() {
		return registTmpCd;
	}

	public void setRegistTmpCd(String registTmpCd) {
		this.registTmpCd = registTmpCd;
	}

	public String getCntrwkNo() {
		return cntrwkNo;
	}

	public void setCntrwkNo(String cntrwkNo) {
		this.cntrwkNo = cntrwkNo;
	}

	@Override
	public String toString() {
		return "CntrwkInfoVO [changeInfoId=" + changeInfoId + ", engnCode="
				+ engnCode + ", cntrwkPnttm=" + cntrwkPnttm + ", cntrwkTmnl="
				+ cntrwkTmnl + ", ar=" + ar + ", extn=" + extn
				+ ", changeTrgetNm=" + changeTrgetNm + ", trgetBfchgCn="
				+ trgetBfchgCn + ", trgetAftchCn=" + trgetAftchCn
				+ ", strwrkDe=" + strwrkDe + ", competPrearngeDe="
				+ competPrearngeDe + ", lastCompetDe=" + lastCompetDe
				+ ", competDrwFileStleTy=" + competDrwFileStleTy + ", cntm="
				+ cntm + ", rm=" + rm + ", psitnEngnNo=" + psitnEngnNo
				+ ", planEngnNo=" + planEngnNo + ", mngEngnNo=" + mngEngnNo
				+ ", chargerNm=" + chargerNm + ", chrgDeptNm=" + chrgDeptNm
				+ ", chargerTlphonNo=" + chargerTlphonNo + ", chargerEmail="
				+ chargerEmail + ", acqsMthTy=" + acqsMthTy + ", sttusTy="
				+ sttusTy + ", changeTy=" + changeTy + ", trgetAreaNm="
				+ trgetAreaNm + ", changeSe=" + changeSe + ", registTmpCd="
				+ registTmpCd + "]";
	}

}
