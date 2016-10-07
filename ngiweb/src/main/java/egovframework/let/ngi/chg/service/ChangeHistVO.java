package egovframework.let.ngi.chg.service;


import java.util.Date;

public class ChangeHistVO {

	private String sttus; //상태
	//private String changeTy; //변동유형
	//private String reflctPrarnde; //반영 예정일
	private String opinion; //의견
	private String register; //등록자
	private String rgsde; //등록일
	private String sttemntCl; //신고 분류
	private int changeHistId; //변동 이력 아이디
	private int changeInfoId; //변동 아이디
	public String getSttus() {
		return sttus;
	}
	public void setSttus(String sttus) {
		this.sttus = sttus;
	}
//	public String getChangeTy() {
//		return changeTy;
//	}
//	public void setChangeTy(String changeTy) {
//		this.changeTy = changeTy;
//	}
//	public String getReflctPrarnde() {
//		return reflctPrarnde;
//	}
//	public void setReflctPrarnde(String reflctPrarnde) {
//		this.reflctPrarnde = reflctPrarnde;
//	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public String getRgsde() {
		return rgsde;
	}
	public void setRgsde(String rgsde) {
		this.rgsde = rgsde;
	}
	public String getSttemntCl() {
		return sttemntCl;
	}
	public void setSttemntCl(String sttemntCl) {
		this.sttemntCl = sttemntCl;
	}
	public int getChangeHistId() {
		return changeHistId;
	}
	public void setChangeHistId(int changeHistId) {
		this.changeHistId = changeHistId;
	}
	public int getChangeInfoId() {
		return changeInfoId;
	}
	public void setChangeInfoId(int changeInfoId) {
		this.changeInfoId = changeInfoId;
	}

	
	
}

