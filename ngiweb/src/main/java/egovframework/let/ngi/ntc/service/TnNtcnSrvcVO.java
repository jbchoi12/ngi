package egovframework.let.ngi.ntc.service;

import java.util.Date;

/**
 * @Class Name : TnNtcnSrvcVO.java
 * @Description : TnNtcnSrvc VO class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class TnNtcnSrvcVO extends TnNtcnSrvcDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** ntcn_srvc_id */
    private int ntcnSrvcId;
    
    /** user_nm */
    private String userNm;
    
    /** telno */
    private String telno;
    
    /** email */
    private String email;
    
    /** rgsde */
    private Date rgsde;
    
    public int getNtcnSrvcId() {
        return this.ntcnSrvcId;
    }
    
    public void setNtcnSrvcId(int ntcnSrvcId) {
        this.ntcnSrvcId = ntcnSrvcId;
    }
    
    public String getUserNm() {
        return this.userNm;
    }
    
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
    
    public String getTelno() {
        return this.telno;
    }
    
    public void setTelno(String telno) {
        this.telno = telno;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getRgsde() {
        return this.rgsde;
    }
    
    public void setRgsde(Date rgsde) {
        this.rgsde = rgsde;
    }
    
}
