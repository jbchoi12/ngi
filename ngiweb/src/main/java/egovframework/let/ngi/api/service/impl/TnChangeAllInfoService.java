package egovframework.let.ngi.api.service.impl;

import java.util.List;

import egovframework.let.ngi.api.service.TnChangeAllInfoDefaultVO;
import egovframework.let.ngi.api.service.TnChangeAllInfoListSearchVO;
import egovframework.let.ngi.api.service.TnChangeAllInfoVO;
import egovframework.let.ngi.api.service.TnChangeDetailInfoVO;

/**
 * @Class Name : TnChangeAllInfoService.java
 * @Description : TnChangeAllInfo Business class
 * @Modification Information
 *
 * @author a
 * @since a
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TnChangeAllInfoService {
	
	/**
	 * tn_change_all_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnChangeAllInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertTnChangeAllInfo(TnChangeAllInfoVO vo) throws Exception;
    
    /**
	 * tn_change_all_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TnChangeAllInfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateTnChangeAllInfo(TnChangeAllInfoVO vo) throws Exception;
    
    /**
	 * tn_change_all_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnChangeAllInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTnChangeAllInfo(TnChangeAllInfoVO vo) throws Exception;
    
    /**
	 * tn_change_all_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TnChangeAllInfoVO
	 * @return 조회한 tn_change_all_info
	 * @exception Exception
	 */
    TnChangeAllInfoVO selectTnChangeAllInfo(TnChangeAllInfoVO vo) throws Exception;
    
    /**
	 * tn_change_all_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_change_all_info 목록
	 * @exception Exception
	 */
    List selectTnChangeAllInfoList(TnChangeAllInfoVO searchVO) throws Exception;
    
    /**
	 * tn_change_all_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_change_all_info 총 갯수
	 * @exception
	 */
    int selectTnChangeAllInfoListTotCnt(TnChangeAllInfoVO searchVO);
    
}
