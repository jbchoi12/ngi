package egovframework.let.ngi.chg.service.impl;

import java.util.List;

import egovframework.let.ngi.chg.service.TnAtchmnflDefaultVO;
import egovframework.let.ngi.chg.service.TnAtchmnflVO;

/**
 * @Class Name : TnAtchmnflService.java
 * @Description : TnAtchmnfl Business class
 * @Modification Information
 *
 * @author a
 * @since a
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TnAtchmnflService {
	
	/**
	 * tn_atchmnfl을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnAtchmnflVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertTnAtchmnfl(TnAtchmnflVO vo) throws Exception;
    
    /**
	 * tn_atchmnfl을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TnAtchmnflVO
	 * @return void형
	 * @exception Exception
	 */
    void updateTnAtchmnfl(TnAtchmnflVO vo) throws Exception;
    
    /**
	 * tn_atchmnfl을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnAtchmnflVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTnAtchmnfl(TnAtchmnflVO vo) throws Exception;
    
    void deleteTnAtchmnflPhone(TnAtchmnflVO vo) throws Exception;
    
    /**
	 * tn_atchmnfl을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TnAtchmnflVO
	 * @return 조회한 tn_atchmnfl
	 * @exception Exception
	 */
    TnAtchmnflVO selectTnAtchmnfl(TnAtchmnflVO vo) throws Exception;
    
    /**
	 * tn_atchmnfl 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_atchmnfl 목록
	 * @exception Exception
	 */
    List selectTnAtchmnflList(TnAtchmnflDefaultVO searchVO) throws Exception;
    
    List selectTnAtchmnflListPhone(TnAtchmnflDefaultVO searchVO) throws Exception;
    
    /**
	 * tn_atchmnfl 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_atchmnfl 총 갯수
	 * @exception
	 */
    int selectTnAtchmnflListTotCnt(TnAtchmnflDefaultVO searchVO);
    
}
