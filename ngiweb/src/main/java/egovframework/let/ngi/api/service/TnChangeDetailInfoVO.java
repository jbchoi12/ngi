package egovframework.let.ngi.api.service;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 상세정보 api용.
 * 
 * @author degulv
 *
 */
public class TnChangeDetailInfoVO {

	private String cntrwkNo = "";
	private String changeSj = "";
	private String changeTy = "";
	private String processSttusSe = "";
	private String psitnEngnNo = "";
	private String planEngnNo = "";
	private String mngEngnNo = "";
	private Date strwrkDe;
	private Date competPrearngeDe;
	private Date lastCompetDe;
	private String chargerNm = "";
	private String chrgDeptNm = "";
	private String chargerTlphonNo = "";
	private String chargerEmail = "";
	private String cntm = "";
	private String rm = "";
	private String changeRnAdresCn = "";
	private String cntrwkPnttm = "";
	private String cntrwkTmnl = "";
	private String ar = "";
	private String extn = "";
	private String trgetBfchgCn = "";
	private String trgetAftchCn = "";
	private HashMap<String, Object> geom;

	public String getCntrwkNo() {
		return cntrwkNo;
	}

	public void setCntrwkNo(String cntrwkNo) {
		this.cntrwkNo = cntrwkNo;
	}

	public String getChangeSj() {
		return changeSj;
	}

	public void setChangeSj(String changeSj) {
		this.changeSj = changeSj;
	}

	public String getChangeTy() {
		return changeTy;
	}

	public void setChangeTy(String changeTy) {
		this.changeTy = changeTy;
	}

	public String getProcessSttusSe() {
		return processSttusSe;
	}

	public void setProcessSttusSe(String processSttusSe) {
		this.processSttusSe = processSttusSe;
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

	public String getChangeRnAdresCn() {
		return changeRnAdresCn;
	}

	public void setChangeRnAdresCn(String changeRnAdresCn) {
		this.changeRnAdresCn = changeRnAdresCn;
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



	public HashMap<String, Object> getGeom() {
		return geom;
	}

	public void setGeom(HashMap<String, Object> geom) {
		this.geom = geom;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
