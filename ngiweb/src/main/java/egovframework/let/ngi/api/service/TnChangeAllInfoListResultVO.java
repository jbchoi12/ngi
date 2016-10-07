package egovframework.let.ngi.api.service;

import java.util.HashMap;

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
 * 지형변화정보 조회 요청 결과 VO
 * 
 * @author degulv
 */
public class TnChangeAllInfoListResultVO {
	private String cntrwkNo = "";
	private String changeSj = "";
	private String changeTy = "";
	private String processSttusSe = "";
	private String psitnEngnNo = "";
	private HashMap<String, Object> geom;

	public String getCntrwkNo() {
		return cntrwkNo;
	}

	public void setCntrwkNo(String cntrwkNo) {
		this.cntrwkNo = cntrwkNo;
	}

	public String getChangeSj() {
		return changeSj;
	}

	public void setChangeSj(String changeSj) {
		this.changeSj = changeSj;
	}

	public String getChangeTy() {
		return changeTy;
	}

	public void setChangeTy(String changeTy) {
		this.changeTy = changeTy;
	}

	public String getProcessSttusSe() {
		return processSttusSe;
	}

	public void setProcessSttusSe(String processSttusSe) {
		this.processSttusSe = processSttusSe;
	}

	public String getPsitnEngnNo() {
		return psitnEngnNo;
	}

	public void setPsitnEngnNo(String psitnEngnNo) {
		this.psitnEngnNo = psitnEngnNo;
	}

	public HashMap<String, Object> getGeom() {
		return geom;
	}

	public void setGeom(HashMap<String, Object> geom) {
		this.geom = geom;
	}


}
