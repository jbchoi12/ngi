package egovframework.com.cmm.service;

import java.io.Serializable;

/**
 * 사업지구 관리 VO
 * @author kka
 * @since 2014. 11. 16.
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *	
 *  </pre>
 */
public class BusinessDistrictVO extends SearchVO implements Serializable {

	private static final long serialVersionUID = 5957181654739146461L;
	private Integer businessId; //사업지구 아이디
	private String businessNm; //사업지구 명
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public String getBusinessNm() {
		return businessNm;
	}
	public void setBusinessNm(String businessNm) {
		this.businessNm = businessNm;
	}
	
}
