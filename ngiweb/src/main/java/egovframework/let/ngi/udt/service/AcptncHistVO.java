package egovframework.let.ngi.udt.service;

import java.util.Date;

public class AcptncHistVO {
	private Integer acptncHistId; //검수이력아이디
	private Integer updtInfoId; //갱신정보아이디
	private String sttus; //상태
	private String opinion; //의견
	private String register; //등록자
	private Date rgsde; //등록일
	
	public Integer getAcptncHistId() {
		return acptncHistId;
	}
	public void setAcptncHistId(Integer acptncHistId) {
		this.acptncHistId = acptncHistId;
	}
	public Integer getUpdtInfoId() {
		return updtInfoId;
	}
	public void setUpdtInfoId(Integer updtInfoId) {
		this.updtInfoId = updtInfoId;
	}
	public String getSttus() {
		return sttus;
	}
	public void setSttus(String sttus) {
		this.sttus = sttus;
	}
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
	public Date getRgsde() {
		return rgsde;
	}
	public void setRgsde(Date rgsde) {
		this.rgsde = rgsde;
	}
}
