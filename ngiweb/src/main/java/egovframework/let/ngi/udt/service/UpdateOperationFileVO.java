package egovframework.let.ngi.udt.service;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class UpdateOperationFileVO {

	private Integer fileId;
	private String fileNm;
	private String flpth;
	private String atchTy;
	private String register;
	private Date rgsde;
	private String deleteAt;
	private Integer nttId;
	private Integer fileSize;
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getFlpth() {
		return flpth;
	}
	public void setFlpth(String flpth) {
		this.flpth = flpth;
	}
	public String getAtchTy() {
		return atchTy;
	}
	public void setAtchTy(String atchTy) {
		this.atchTy = atchTy;
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
	public String getDeleteAt() {
		return deleteAt;
	}
	public void setDeleteAt(String deleteAt) {
		this.deleteAt = deleteAt;
	}
	public Integer getNttId() {
		return nttId;
	}
	public void setNttId(Integer nttId) {
		this.nttId = nttId;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
