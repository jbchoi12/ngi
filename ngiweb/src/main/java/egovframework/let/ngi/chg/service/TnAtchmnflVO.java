package egovframework.let.ngi.chg.service;


/**
 * @Class Name : TnAtchmnflVO.java
 * @Description : TnAtchmnfl VO class
 * @Modification Information
 *
 * @author a
 * @since a
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class TnAtchmnflVO extends TnAtchmnflDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** atchmnfl_id */
    private java.math.BigDecimal atchmnflId;
    
    /** change_info_id */
    private java.math.BigDecimal changeInfoId;
    
    /** file_nm */
    private java.lang.String fileNm;
    
    /** flpth_nm */
    private java.lang.String flpthNm;
    
    /** file_lock_ennc_at */
    private java.lang.String fileLockEnncAt;
    
    /** file_fom_code_ty */
    private java.lang.String fileFomCodeTy;
    
    /** file_mg */
    private java.lang.String fileMg;
    
    public java.math.BigDecimal getAtchmnflId() {
        return this.atchmnflId;
    }
    
    public void setAtchmnflId(java.math.BigDecimal atchmnflId) {
        this.atchmnflId = atchmnflId;
    }
    
    public java.math.BigDecimal getChangeInfoId() {
        return this.changeInfoId;
    }
    
    public void setChangeInfoId(java.math.BigDecimal changeInfoId) {
        this.changeInfoId = changeInfoId;
    }
    
    public java.lang.String getFileNm() {
        return this.fileNm;
    }
    
    public void setFileNm(java.lang.String fileNm) {
        this.fileNm = fileNm;
    }
    
    public java.lang.String getFlpthNm() {
        return this.flpthNm;
    }
    
    public void setFlpthNm(java.lang.String flpthNm) {
        this.flpthNm = flpthNm;
    }
    
    public java.lang.String getFileLockEnncAt() {
        return this.fileLockEnncAt;
    }
    
    public void setFileLockEnncAt(java.lang.String fileLockEnncAt) {
        this.fileLockEnncAt = fileLockEnncAt;
    }
    
    public java.lang.String getFileFomCodeTy() {
        return this.fileFomCodeTy;
    }
    
    public void setFileFomCodeTy(java.lang.String fileFomCodeTy) {
        this.fileFomCodeTy = fileFomCodeTy;
    }
    
    public java.lang.String getFileMg() {
        return this.fileMg;
    }
    
    public void setFileMg(java.lang.String fileMg) {
        this.fileMg = fileMg;
    }

	@Override
	public String toString() {
		return "TnAtchmnflVO [atchmnflId=" + atchmnflId + ", changeInfoId="
				+ changeInfoId + ", fileNm=" + fileNm + ", flpthNm=" + flpthNm
				+ ", fileLockEnncAt=" + fileLockEnncAt + ", fileFomCodeTy="
				+ fileFomCodeTy + ", fileMg=" + fileMg + "]";
	}
    
    
}
