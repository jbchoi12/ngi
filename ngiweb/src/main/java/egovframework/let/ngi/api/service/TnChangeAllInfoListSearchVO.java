package egovframework.let.ngi.api.service;

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
/**
 * 지형변화정보 조회 요청 VO
 * @author degulv
 */
public class TnChangeAllInfoListSearchVO extends TnChangeAllInfoDefaultVO {
	
	private static final long serialVersionUID = 1L;
	
    /** 변동정보 아이디 . */
    private java.math.BigDecimal changeInfoId;
	
    /** 변동유형. */
    private java.lang.String changeCl;
    
    /** process_sttus_se 처리상태. */
    private java.lang.String processSttusSe;
    
    /** change_lnm_adres_cn 변동지번주소 */
    private java.lang.String changeLnmAdresCn;
    
    /** change_lnm_adres_detail_cn 변동지상세주소 */
    private java.lang.String changeLnmAdresDetailCn;
    
    /** change_rn_adres_cn 변동지 도로명 주소 */
    private java.lang.String changeRnAdresCn;
    
    /** change_rn_adres_detail_cn 변동지 도로명 상세주소 */
    private java.lang.String changeRnAdresDetailCn;
    
    /** pnu_cd */
    private java.lang.String pnuCd;
				    
    /** 공사명,제목. */
    private java.lang.String changeSj;
    
    /** strwrk_de 착공일자 */
    private java.sql.Date strwrkDe;
    
    /** compet_prearnge_de 완공예정일. */
    private java.sql.Date competPrearngeDe;
    
    /** last_compet_de 최종완공일 */
    private java.sql.Date lastCompetDe;
    
	/** 도엽번호 **/
    private java.lang.String mapindex;
    
    /** bbox **/
    private java.lang.String bbox;
    
    
    
    public java.math.BigDecimal getChangeInfoId() {
		return changeInfoId;
	}

	public void setChangeInfoId(java.math.BigDecimal changeInfoId) {
		this.changeInfoId = changeInfoId;
	}

	public java.lang.String getChangeSj() {
		return changeSj;
	}

	public void setChangeSj(java.lang.String changeSj) {
		this.changeSj = changeSj;
	}

	public java.lang.String getMapindex() {
		return mapindex;
	}

	public void setMapindex(java.lang.String mapindex) {
		this.mapindex = mapindex;
	}

	public java.lang.String getBbox() {
		return bbox;
	}

	public void setBbox(java.lang.String bbox) {
		this.bbox = bbox;
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
       
    public java.lang.String getPnuCd() {
        return this.pnuCd;
    }
    
    public void setPnuCd(java.lang.String pnuCd) {
        this.pnuCd = pnuCd;
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
    
}
