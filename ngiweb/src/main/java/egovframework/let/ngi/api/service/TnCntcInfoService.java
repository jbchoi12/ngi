package egovframework.let.ngi.api.service;

import java.util.List;
import egovframework.let.ngi.api.service.TnCntcInfoDefaultVO;
import egovframework.let.ngi.api.service.TnCntcInfoVO;

/**
 * @Class Name : TnCntcInfoService.java
 * @Description : TnCntcInfo Business class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TnCntcInfoService {
	
	/**
	 * tn_cntc_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnCntcInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertTnCntcInfo(TnCntcInfoVO vo) throws Exception;
    
    /**
	 * tn_cntc_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TnCntcInfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateTnCntcInfo(TnCntcInfoVO vo) throws Exception;
    
    /**
	 * tn_cntc_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnCntcInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTnCntcInfo(TnCntcInfoVO vo) throws Exception;
    
    /**
	 * tn_cntc_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TnCntcInfoVO
	 * @return 조회한 tn_cntc_info
	 * @exception Exception
	 */
    TnCntcInfoVO selectTnCntcInfo(TnCntcInfoVO vo) throws Exception;
    
    /**
	 * tn_cntc_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_cntc_info 목록
	 * @exception Exception
	 */
    List selectTnCntcInfoList(TnCntcInfoDefaultVO searchVO) throws Exception;
    
    /**
	 * tn_cntc_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_cntc_info 총 갯수
	 * @exception
	 */
    int selectTnCntcInfoListTotCnt(TnCntcInfoDefaultVO searchVO);
    
}
