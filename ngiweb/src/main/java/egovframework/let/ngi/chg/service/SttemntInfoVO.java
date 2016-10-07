package egovframework.let.ngi.chg.service;

public class SttemntInfoVO {

	
	/** change_info_id */
	private Integer changeInfoId; //변동정보아이디
	
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
	private String atchFileId;
	
	
	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

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
	
	@Override
	public String toString() {
		return "SttemntInfoVO [changeInfoId=" + changeInfoId + ", sttemntCl="
				+ sttemntCl + ", telno=" + telno + ", email=" + email
				+ ", sttemntCn=" + sttemntCn + ", crtfcPassword="
				+ crtfcPssword + ", sttemntTy=" + sttemntTy + ", bfchg="
				+ bfchg + ", aftch=" + aftch + ", vectorList=" + vectorList
				+ "]";
	}
	
}
