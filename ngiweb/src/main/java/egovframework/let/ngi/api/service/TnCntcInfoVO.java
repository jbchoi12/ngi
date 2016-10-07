package egovframework.let.ngi.api.service;

/**
 * @Class Name : TnCntcInfoVO.java
 * @Description : TnCntcInfo VO class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-19
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */
public class TnCntcInfoVO extends TnCntcInfoDefaultVO {
	private static final long serialVersionUID = 1L;

	/** cntc_info_id */
	private java.math.BigDecimal cntcInfoId;

	/** charger_nm */
	private java.lang.String chargerNm;

	/** sys_nm */
	private java.lang.String sysNm;

	/** telno */
	private java.lang.String telno;

	/** email */
	private java.lang.String email;

	/** cntc_ty */
	private java.lang.String cntcTy;

	/** applc_domn_nm */
	private java.lang.String applcDomnNm;

	/** useprps */
	private java.lang.String useprps;

	/** rgsde */
	private java.sql.Date rgsde;

	/** crtfc_code_se */
	private java.lang.String crtfcCodeSe;

	/** issu_de */
	private java.sql.Date issuDe;

	/** confm_at */
	private java.lang.String confmAt = "N";

	private String userId;
	
	private String host;
	
	private String port;
	
	private String protocol;
	
	private String authKey;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getAuth_key() {
		return authKey;
	}

	public void setAuth_key(String authKey) {
		this.authKey = authKey;
	}

	public java.math.BigDecimal getCntcInfoId() {
		return this.cntcInfoId;
	}

	public void setCntcInfoId(java.math.BigDecimal cntcInfoId) {
		this.cntcInfoId = cntcInfoId;
	}

	public java.lang.String getChargerNm() {
		return this.chargerNm;
	}

	public void setChargerNm(java.lang.String chargerNm) {
		this.chargerNm = chargerNm;
	}

	public java.lang.String getSysNm() {
		return this.sysNm;
	}

	public void setSysNm(java.lang.String sysNm) {
		this.sysNm = sysNm;
	}

	public java.lang.String getTelno() {
		return this.telno;
	}

	public void setTelno(java.lang.String telno) {
		this.telno = telno;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getCntcTy() {
		return this.cntcTy;
	}

	public void setCntcTy(java.lang.String cntcTy) {
		this.cntcTy = cntcTy;
	}

	public java.lang.String getApplcDomnNm() {
		return this.applcDomnNm;
	}

	public void setApplcDomnNm(java.lang.String applcDomnNm) {
		this.applcDomnNm = applcDomnNm;
	}

	public java.lang.String getUseprps() {
		return this.useprps;
	}

	public void setUseprps(java.lang.String useprps) {
		this.useprps = useprps;
	}

	public java.sql.Date getRgsde() {
		return this.rgsde;
	}

	public void setRgsde(java.sql.Date rgsde) {
		this.rgsde = rgsde;
	}

	public java.lang.String getCrtfcCodeSe() {
		return this.crtfcCodeSe;
	}

	public void setCrtfcCodeSe(java.lang.String crtfcCodeSe) {
		this.crtfcCodeSe = crtfcCodeSe;
	}

	public java.sql.Date getIssuDe() {
		return this.issuDe;
	}

	public void setIssuDe(java.sql.Date issuDe) {
		this.issuDe = issuDe;
	}

	public java.lang.String getConfmAt() {
		return this.confmAt;
	}

	public void setConfmAt(java.lang.String confmAt) {
		this.confmAt = confmAt;
	}

	@Override
	public String toString() {
		return "TnCntcInfoVO [cntcInfoId=" + cntcInfoId + ", chargerNm="
				+ chargerNm + ", sysNm=" + sysNm + ", telno=" + telno
				+ ", email=" + email + ", cntcTy=" + cntcTy + ", applcDomnNm="
				+ applcDomnNm + ", useprps=" + useprps + ", rgsde=" + rgsde
				+ ", crtfcCodeSe=" + crtfcCodeSe + ", issuDe=" + issuDe
				+ ", confmAt=" + confmAt + ", userId=" + userId + ", host="
				+ host + ", port=" + port + ", protocol=" + protocol
				+ ", authKey=" + authKey + "]";
	}

	
	

}
