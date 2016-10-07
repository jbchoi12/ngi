package egovframework.let.ngi.api.service;

import java.util.Date;

/**
 * @Class Name : TnChangeAllInfoVO.java
 * @Description : TnChangeAllInfo VO class
 * @Modification Information
 *
 * @author a
 * @since a
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class TnChangeAllInfoVO extends TnChangeAllInfoDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** change_info_id */
    private java.math.BigDecimal changeInfoId;
    
    /** register_id */
    private java.lang.String registerId = "";
    
    /** change_cl */
    private java.lang.String changeCl = "";
    
    /** process_sttus_se */
    private java.lang.String processSttusSe = "";
    
    /** bsns_dstrc */
    private java.lang.String bsnsDstrc = "";
    
    /** change_lnm_adres_cn */
    private java.lang.String changeLnmAdresCn = "";
    
    /** change_lnm_adres_detail_cn */
    private java.lang.String changeLnmAdresDetailCn = "";
    
    /** change_rn_adres_cn */
    private java.lang.String changeRnAdresCn = "";
    
    /** change_rn_adres_detail_cn */
    private java.lang.String changeRnAdresDetailCn = "";
    
    /** regist_de */
    private java.sql.Date registDe;
    
    /** register_nm */
    private java.lang.String registerNm = "";
    
    /** change_sj */
    private java.lang.String changeSj = "";
    
    /** x_crdnt_lo */
    private java.lang.String XCrdntLo = "";
    
    /** y_crdnt_la */
    private java.lang.String YCrdntLa = "";
    
    /** pnu_cd */
    private java.lang.String pnuCd = "";
    
    /** del_se */
    private java.lang.String delSe = "";
    
    /** engn_code */
    private java.lang.String engnCode = "";
    
    /** cntrwk_pnttm */
    private java.lang.String cntrwkPnttm = "";
    
    /** cntrwk_tmnl */
    private java.lang.String cntrwkTmnl = "";
    
    /** ar */
    private String ar = "";
    
    /** extn */
    private String extn = "";
    
    /** change_trget_nm */
    private java.lang.String changeTrgetNm = "";
    
    /** trget_bfchg_cn */
    private java.lang.String trgetBfchgCn = "";
    
    /** trget_aftch_cn */
    private java.lang.String trgetAftchCn = "";
    
    /** strwrk_de */
    private java.sql.Date strwrkDe;
    
    /** compet_prearnge_de */
    private java.sql.Date competPrearngeDe;
    
    /** last_compet_de */
    private java.sql.Date lastCompetDe;
    
	private java.sql.Timestamp lastCompetBgnde;

	private java.sql.Timestamp lastCompetEndde;    
    
    /** compet_drw_file_stle_ty */
    private java.lang.String competDrwFileStleTy = "";
    
    /** cntm */
    private java.lang.String cntm = "";
    
    /** rm */
    private java.lang.String rm = "";
    
    /** psitn_engn_no */
    private java.lang.String psitnEngnNo = "";
    
    /** plan_engn_no */
    private java.lang.String planEngnNo = "";
    
    /** mng_engn_no */
    private java.lang.String mngEngnNo = "";
    
    /** charger_nm */
    private java.lang.String chargerNm = "";
    
    /** chrg_dept_nm */
    private java.lang.String chrgDeptNm = "";
    
    /** charger_tlphon_no */
    private java.lang.String chargerTlphonNo = "";
    
    /** charger_email */
    private java.lang.String chargerEmail = "";
    
    /** acqs_mth_ty */
    private java.lang.String acqsMthTy = "";
    
    /** sttus_ty */
    private java.lang.String sttusTy = "";
    
    /** change_ty */
    private java.lang.String changeTy = "";
    
    
    /** regist_path */
    private java.lang.String registPath;
    
    /** reflct_prarnde */
    private java.sql.Timestamp reflctPrarnde;
    
    /** trnsfer_se */
    private java.lang.String trnsferSe;
    
    /** addr_ty */
    private java.lang.String addrTy;
    
    /** trget_area_nm */
    private java.lang.String trgetAreaNm;
    
    /** change_se */
    private java.lang.String changeSe;
    
    /** regist_tmp_cd */
    private java.lang.String registTmpCd;
    
    /** atch_file_id */
    private java.math.BigDecimal atchFileId;
    
    /** cntrwk_no */
    private java.lang.String cntrwkNo;
    
    private String bbox;
    
   
    public String getBbox() {
		return bbox;
	}

	public void setBbox(String bbox) {
		this.bbox = bbox;
	}

	public java.lang.String getRegistPath() {
		return registPath;
	}

	public void setRegistPath(java.lang.String registPath) {
		this.registPath = registPath;
	}

	public java.sql.Timestamp getReflctPrarnde() {
		return reflctPrarnde;
	}

	public void setReflctPrarnde(java.sql.Timestamp reflctPrarnde) {
		this.reflctPrarnde = reflctPrarnde;
	}

	public java.lang.String getTrnsferSe() {
		return trnsferSe;
	}

	public void setTrnsferSe(java.lang.String trnsferSe) {
		this.trnsferSe = trnsferSe;
	}

	public java.lang.String getAddrTy() {
		return addrTy;
	}

	public void setAddrTy(java.lang.String addrTy) {
		this.addrTy = addrTy;
	}

	public java.lang.String getTrgetAreaNm() {
		return trgetAreaNm;
	}

	public void setTrgetAreaNm(java.lang.String trgetAreaNm) {
		this.trgetAreaNm = trgetAreaNm;
	}

	public java.lang.String getChangeSe() {
		return changeSe;
	}

	public void setChangeSe(java.lang.String changeSe) {
		this.changeSe = changeSe;
	}

	public java.lang.String getRegistTmpCd() {
		return registTmpCd;
	}

	public void setRegistTmpCd(java.lang.String registTmpCd) {
		this.registTmpCd = registTmpCd;
	}

	public java.math.BigDecimal getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(java.math.BigDecimal atchFileId) {
		this.atchFileId = atchFileId;
	}

	public java.lang.String getCntrwkNo() {
		return cntrwkNo;
	}

	public void setCntrwkNo(java.lang.String cntrwkNo) {
		this.cntrwkNo = cntrwkNo;
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

	public java.math.BigDecimal getChangeInfoId() {
        return this.changeInfoId;
    }
    
    public void setChangeInfoId(java.math.BigDecimal changeInfoId) {
        this.changeInfoId = changeInfoId;
    }
    
    public java.lang.String getRegisterId() {
        return this.registerId;
    }
    
    public void setRegisterId(java.lang.String registerId) {
        this.registerId = registerId;
    }
    
    public java.lang.String getChangeCl() {
        return this.changeCl;
    }
    
    public void setChangeCl(java.lang.String changeCl) {
        this.changeCl = changeCl;
    }
    
    public java.lang.String getProcessSttusSe() {
        return this.processSttusSe;
    }
    
    public void setProcessSttusSe(java.lang.String processSttusSe) {
        this.processSttusSe = processSttusSe;
    }
    
    public java.lang.String getBsnsDstrc() {
        return this.bsnsDstrc;
    }
    
    public void setBsnsDstrc(java.lang.String bsnsDstrc) {
        this.bsnsDstrc = bsnsDstrc;
    }
    
    public java.lang.String getChangeLnmAdresCn() {
        return this.changeLnmAdresCn;
    }
    
    public void setChangeLnmAdresCn(java.lang.String changeLnmAdresCn) {
        this.changeLnmAdresCn = changeLnmAdresCn;
    }
    
    public java.lang.String getChangeLnmAdresDetailCn() {
        return this.changeLnmAdresDetailCn;
    }
    
    public void setChangeLnmAdresDetailCn(java.lang.String changeLnmAdresDetailCn) {
        this.changeLnmAdresDetailCn = changeLnmAdresDetailCn;
    }
    
    public java.lang.String getChangeRnAdresCn() {
        return this.changeRnAdresCn;
    }
    
    public void setChangeRnAdresCn(java.lang.String changeRnAdresCn) {
        this.changeRnAdresCn = changeRnAdresCn;
    }
    
    public java.lang.String getChangeRnAdresDetailCn() {
        return this.changeRnAdresDetailCn;
    }
    
    public void setChangeRnAdresDetailCn(java.lang.String changeRnAdresDetailCn) {
        this.changeRnAdresDetailCn = changeRnAdresDetailCn;
    }
    
    public java.sql.Date getRegistDe() {
        return this.registDe;
    }
    
    public void setRegistDe(java.sql.Date registDe) {
        this.registDe = registDe;
    }
    
    public java.lang.String getRegisterNm() {
        return this.registerNm;
    }
    
    public void setRegisterNm(java.lang.String registerNm) {
        this.registerNm = registerNm;
    }
    
    public java.lang.String getChangeSj() {
        return this.changeSj;
    }
    
    public void setChangeSj(java.lang.String changeSj) {
        this.changeSj = changeSj;
    }
    
    public java.lang.String getXCrdntLo() {
        return this.XCrdntLo;
    }
    
    public void setXCrdntLo(java.lang.String XCrdntLo) {
        this.XCrdntLo = XCrdntLo;
    }
    
    public java.lang.String getYCrdntLa() {
        return this.YCrdntLa;
    }
    
    public void setYCrdntLa(java.lang.String YCrdntLa) {
        this.YCrdntLa = YCrdntLa;
    }
    
    public java.lang.String getPnuCd() {
        return this.pnuCd;
    }
    
    public void setPnuCd(java.lang.String pnuCd) {
        this.pnuCd = pnuCd;
    }
    
    public java.lang.String getDelSe() {
        return this.delSe;
    }
    
    public void setDelSe(java.lang.String delSe) {
        this.delSe = delSe;
    }
    
    public java.lang.String getEngnCode() {
        return this.engnCode;
    }
    
    public void setEngnCode(java.lang.String engnCode) {
        this.engnCode = engnCode;
    }
    
    public java.lang.String getCntrwkPnttm() {
        return this.cntrwkPnttm;
    }
    
    public void setCntrwkPnttm(java.lang.String cntrwkPnttm) {
        this.cntrwkPnttm = cntrwkPnttm;
    }
    
    public java.lang.String getCntrwkTmnl() {
        return this.cntrwkTmnl;
    }
    
    public void setCntrwkTmnl(java.lang.String cntrwkTmnl) {
        this.cntrwkTmnl = cntrwkTmnl;
    }
    
    
    public java.lang.String getChangeTrgetNm() {
        return this.changeTrgetNm;
    }
    
    public void setChangeTrgetNm(java.lang.String changeTrgetNm) {
        this.changeTrgetNm = changeTrgetNm;
    }
    
    public java.lang.String getTrgetBfchgCn() {
        return this.trgetBfchgCn;
    }
    
    public void setTrgetBfchgCn(java.lang.String trgetBfchgCn) {
        this.trgetBfchgCn = trgetBfchgCn;
    }
    
    public java.lang.String getTrgetAftchCn() {
        return this.trgetAftchCn;
    }
    
    public void setTrgetAftchCn(java.lang.String trgetAftchCn) {
        this.trgetAftchCn = trgetAftchCn;
    }
    
    public java.sql.Date getStrwrkDe() {
        return this.strwrkDe;
    }
    
    public void setStrwrkDe(java.sql.Date strwrkDe) {
        this.strwrkDe = strwrkDe;
    }
    
    public java.sql.Date getCompetPrearngeDe() {
        return this.competPrearngeDe;
    }
    
    public void setCompetPrearngeDe(java.sql.Date competPrearngeDe) {
        this.competPrearngeDe = competPrearngeDe;
    }
    
    public java.sql.Date getLastCompetDe() {
        return this.lastCompetDe;
    }
    
    public void setLastCompetDe(java.sql.Date lastCompetDe) {
        this.lastCompetDe = lastCompetDe;
    }
    
    public java.sql.Timestamp getLastCompetBgnde() {
		return lastCompetBgnde;
	}

	public void setLastCompetBgnde(java.sql.Timestamp lastCompetBgnde) {
		this.lastCompetBgnde = lastCompetBgnde;
	}

	public java.sql.Timestamp getLastCompetEndde() {
		return lastCompetEndde;
	}

	public void setLastCompetEndde(java.sql.Timestamp lastCompetEndde) {
		this.lastCompetEndde = lastCompetEndde;
	}

	public java.lang.String getCompetDrwFileStleTy() {
        return this.competDrwFileStleTy;
    }
    
    public void setCompetDrwFileStleTy(java.lang.String competDrwFileStleTy) {
        this.competDrwFileStleTy = competDrwFileStleTy;
    }
    
    public java.lang.String getCntm() {
        return this.cntm;
    }
    
    public void setCntm(java.lang.String cntm) {
        this.cntm = cntm;
    }
    
    public java.lang.String getRm() {
        return this.rm;
    }
    
    public void setRm(java.lang.String rm) {
        this.rm = rm;
    }
    
    public java.lang.String getPsitnEngnNo() {
        return this.psitnEngnNo;
    }
    
    public void setPsitnEngnNo(java.lang.String psitnEngnNo) {
        this.psitnEngnNo = psitnEngnNo;
    }
    
    public java.lang.String getPlanEngnNo() {
        return this.planEngnNo;
    }
    
    public void setPlanEngnNo(java.lang.String planEngnNo) {
        this.planEngnNo = planEngnNo;
    }
    
    public java.lang.String getMngEngnNo() {
        return this.mngEngnNo;
    }
    
    public void setMngEngnNo(java.lang.String mngEngnNo) {
        this.mngEngnNo = mngEngnNo;
    }
    
    public java.lang.String getChargerNm() {
        return this.chargerNm;
    }
    
    public void setChargerNm(java.lang.String chargerNm) {
        this.chargerNm = chargerNm;
    }
    
    public java.lang.String getChrgDeptNm() {
        return this.chrgDeptNm;
    }
    
    public void setChrgDeptNm(java.lang.String chrgDeptNm) {
        this.chrgDeptNm = chrgDeptNm;
    }
    
    public java.lang.String getChargerTlphonNo() {
        return this.chargerTlphonNo;
    }
    
    public void setChargerTlphonNo(java.lang.String chargerTlphonNo) {
        this.chargerTlphonNo = chargerTlphonNo;
    }
    
    public java.lang.String getChargerEmail() {
        return this.chargerEmail;
    }
    
    public void setChargerEmail(java.lang.String chargerEmail) {
        this.chargerEmail = chargerEmail;
    }
    
    public java.lang.String getAcqsMthTy() {
        return this.acqsMthTy;
    }
    
    public void setAcqsMthTy(java.lang.String acqsMthTy) {
        this.acqsMthTy = acqsMthTy;
    }
    
    public java.lang.String getSttusTy() {
        return this.sttusTy;
    }
    
    public void setSttusTy(java.lang.String sttusTy) {
        this.sttusTy = sttusTy;
    }
    
    public java.lang.String getChangeTy() {
        return this.changeTy;
    }
    
    public void setChangeTy(java.lang.String changeTy) {
        this.changeTy = changeTy;
    }
    
}
