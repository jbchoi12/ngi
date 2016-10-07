package egovframework.let.ngi.chg.service;

import java.util.Date;

public class ChangeSttemntInfoVO {

    /** change_info_id */
    private int changeInfoId;
    
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
	
    /** addr_ty */
    private String addrTy; 
    
	/** sttemnt_cl */
	private String sttemntCl; //신고분류
	
	/** telno */
	private String telno; //전화번호
	
	/** email */
	private String email; //전자우편
	
	/** sttemnt_cn */
	private String sttemntCn; //내용
	
	/** crtfc_password */
	private String crtfcPssword; //인증비밀번호
	
	/** sttemnt_ty */
	private String sttemntTy; //신고유형
	
	/** bfchg */
	private String bfchg; //변경전
	
	/** aftch */
	private String aftch; //변경후	
	
	/** 도형정보 */
	private String vectorList; // 도형정보 임시저장.
	
	/** 파일id */
	private String atchFileId;	// egov용
	
	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public int getChangeInfoId() {
		return changeInfoId;
	}

	public void setChangeInfoId(int changeInfoId) {
		this.changeInfoId = changeInfoId;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public String getChangeCl() {
		return changeCl;
	}

	public void setChangeCl(String changeCl) {
		this.changeCl = changeCl;
	}

	public String getProcessSttusSe() {
		return processSttusSe;
	}

	public void setProcessSttusSe(String processSttusSe) {
		this.processSttusSe = processSttusSe;
	}

	public String getBsnsDstrc() {
		return bsnsDstrc;
	}

	public void setBsnsDstrc(String bsnsDstrc) {
		this.bsnsDstrc = bsnsDstrc;
	}

	public String getChangeLnmAdresCn() {
		return changeLnmAdresCn;
	}

	public void setChangeLnmAdresCn(String changeLnmAdresCn) {
		this.changeLnmAdresCn = changeLnmAdresCn;
	}

	public String getChangeLnmAdresDetailCn() {
		return changeLnmAdresDetailCn;
	}

	public void setChangeLnmAdresDetailCn(String changeLnmAdresDetailCn) {
		this.changeLnmAdresDetailCn = changeLnmAdresDetailCn;
	}

	public String getChangeRnAdresCn() {
		return changeRnAdresCn;
	}

	public void setChangeRnAdresCn(String changeRnAdresCn) {
		this.changeRnAdresCn = changeRnAdresCn;
	}

	public String getChangeRnAdresDetailCn() {
		return changeRnAdresDetailCn;
	}

	public void setChangeRnAdresDetailCn(String changeRnAdresDetailCn) {
		this.changeRnAdresDetailCn = changeRnAdresDetailCn;
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

	public Date getRegistDe() {
		return registDe;
	}

	public void setRegistDe(Date registDe) {
		this.registDe = registDe;
	}

	public String getRegisterNm() {
		return registerNm;
	}

	public void setRegisterNm(String registerNm) {
		this.registerNm = registerNm;
	}

	public String getChangeSj() {
		return changeSj;
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
	
	public String getSttemntCl() {
		return sttemntCl;
	}

	public void setSttemntCl(String sttemntCl) {
		this.sttemntCl = sttemntCl;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSttemntCn() {
		return sttemntCn;
	}

	public void setSttemntCn(String sttemntCn) {
		this.sttemntCn = sttemntCn;
	}

	public String getCrtfcPssword() {
		return crtfcPssword;
	}

	public void setCrtfcPssword(String crtfcPssword) {
		this.crtfcPssword = crtfcPssword;
	}

	public String getSttemntTy() {
		return sttemntTy;
	}

	public void setSttemntTy(String sttemntTy) {
		this.sttemntTy = sttemntTy;
	}

	public String getBfchg() {
		return bfchg;
	}

	public void setBfchg(String bfchg) {
		this.bfchg = bfchg;
	}

	public String getAftch() {
		return aftch;
	}

	public void setAftch(String aftch) {
		this.aftch = aftch;
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

	@Override
	public String toString() {
		return "ChangeSttemntInfoVO [changeInfoId=" + changeInfoId
				+ ", registerId=" + registerId + ", changeCl=" + changeCl
				+ ", processSttusSe=" + processSttusSe + ", bsnsDstrc="
				+ bsnsDstrc + ", changeLnmAdresCn=" + changeLnmAdresCn
				+ ", changeLnmAdresDetailCn=" + changeLnmAdresDetailCn
				+ ", changeRnAdresCn=" + changeRnAdresCn
				+ ", changeRnAdresDetailCn=" + changeRnAdresDetailCn
				+ ", XCrdntLo=" + XCrdntLo + ", YCrdntLa=" + YCrdntLa
				+ ", registDe=" + registDe + ", registerNm=" + registerNm
				+ ", changeSj=" + changeSj + ", pnuCd=" + pnuCd + ", delSe="
				+ delSe + ", sttemntCl=" + sttemntCl + ", telno=" + telno
				+ ", email=" + email + ", sttemntCn=" + sttemntCn
				+ ", crtfcPassword=" + crtfcPssword + ", sttemntTy="
				+ sttemntTy + ", bfchg=" + bfchg + ", aftch=" + aftch
				+ ", vectorList=" + vectorList + ",addrTy=" + addrTy + "]";
	}

	
}
