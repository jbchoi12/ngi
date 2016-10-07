package egovframework.let.ngi.chg.service;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @Class Name : TnChangeInfoVO.java
 * @Description : TnChangeInfo VO class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class ChangeInfoVO extends ChangeInfoDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** change_info_id */
    private Integer changeInfoId;
    
    /** register_id */
    private String registerId;
    
    /** change_cl */
    private String changeCl;
    
    /** process_sttus_se */
    private String processSttusSe;
    
    /** bsns_dstrc */
    private String bsnsDstrc;
    
    /** change_lnm_adres_cn */
    private String changeLnmAdresCn;
    
    /** change_lnm_adres_detail_cn */
    private String changeLnmAdresDetailCn;
    
    /** change_rn_adres_cn */
    private String changeRnAdresCn;
    
    /** change_rn_adres_detail_cn */
    private String changeRnAdresDetailCn;
    
    /** x_crdnt_lo */
    private String XCrdntLo;
    
    /** y_crdnt_la */
    private String YCrdntLa;
    
    /** regist_de */
    private Date registDe;
    
    /** register_nm */
    private String registerNm;
    
    /** change_sj */
    private String changeSj;

    /** pnu_cd */
    private String pnuCd;
    
    /** del_se */
    private String delSe;   

    /** regist_path */
    private String registPath;    

    private String vectorList;
    
    /** addr_ty */
    private String addrTy;  
    
    private String cntrwkNo;
    
    private String atchFileId;
    
    public String getCntrwkNo() {
		return cntrwkNo;
	}

	public void setCntrwkNo(String cntrwkNo) {
		this.cntrwkNo = cntrwkNo;
	}

	public Integer getChangeInfoId() {
		return changeInfoId;
	}

	public void setChangeInfoId(Integer changeInfoId) {
		this.changeInfoId = changeInfoId;
	}

	public String getRegisterId() {
        return this.registerId;
    }
    
    public void setRegisterId(String registerId) {
        this.registerId = registerId;
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
    
    public String getBsnsDstrc() {
        return this.bsnsDstrc;
    }
    
    public void setBsnsDstrc(String bsnsDstrc) {
        this.bsnsDstrc = bsnsDstrc;
    }
    
    public String getChangeLnmAdresCn() {
        return this.changeLnmAdresCn;
    }
    
    public void setChangeLnmAdresCn(String changeLnmAdresCn) {
        this.changeLnmAdresCn = changeLnmAdresCn;
    }
    
    public String getChangeLnmAdresDetailCn() {
        return this.changeLnmAdresDetailCn;
    }
    
    public void setChangeLnmAdresDetailCn(String changeLnmAdresDetailCn) {
        this.changeLnmAdresDetailCn = changeLnmAdresDetailCn;
    }
    
    public String getChangeRnAdresCn() {
        return this.changeRnAdresCn;
    }
    
    public void setChangeRnAdresCn(String changeRnAdresCn) {
        this.changeRnAdresCn = changeRnAdresCn;
    }
    
    public String getChangeRnAdresDetailCn() {
        return this.changeRnAdresDetailCn;
    }
    
    public void setChangeRnAdresDetailCn(String changeRnAdresDetailCn) {
        this.changeRnAdresDetailCn = changeRnAdresDetailCn;
    }
    
    public String getXCrdntLo() {
        return this.XCrdntLo;
    }
    
    public void setXCrdntLo(String XCrdntLo) {
        this.XCrdntLo = XCrdntLo;
    }
    
    public String getYCrdntLa() {
        return this.YCrdntLa;
    }
    
    public void setYCrdntLa(String YCrdntLa) {
        this.YCrdntLa = YCrdntLa;
    }
    
    public Date getRegistDe() {
        return this.registDe;
    }
    
    public void setRegistDe(Date registDe) {
        this.registDe = registDe;
    }
    
    public String getRegisterNm() {
        return this.registerNm;
    }
    
    public void setRegisterNm(String registerNm) {
        this.registerNm = registerNm;
    }
    
    public String getChangeSj() {
        return this.changeSj;
    }
    
    public void setChangeSj(String changeSj) {
        this.changeSj = changeSj;
    }

    public String getPnuCd() {
		return pnuCd;
	}

	public void setPnuCd(String pnuCd) {
		this.pnuCd = pnuCd;
	}

	public String getDelSe() {
		return delSe;
	}

	public void setDelSe(String delSe) {
		this.delSe = delSe;
	}

    public String getRegistPath() {
		return registPath;
	}

	public void setRegistPath(String registPath) {
		this.registPath = registPath;
	}
	
	public String getVectorList() {
		return vectorList;
	}

	public void setVectorList(String vectorList) {
		this.vectorList = vectorList;
	}

	public String getAddrTy() {
		return addrTy;
	}

	public void setAddrTy(String addrTy) {
		this.addrTy = addrTy;
	}
	
	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}    
    
	
}
