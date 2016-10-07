package egovframework.let.ngi.ntc.service;

import java.util.List;
import egovframework.let.ngi.ntc.service.TnNtcnSrvcDefaultVO;
import egovframework.let.ngi.ntc.service.TnNtcnSrvcVO;

/**
 * @Class Name : TnNtcnSrvcService.java
 * @Description : TnNtcnSrvc Business class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TnNtcnSrvcService {
	
	/**
	 * tn_ntcn_srvc을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnNtcnSrvcVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertTnNtcnSrvc(TnNtcnSrvcVO vo) throws Exception;
    
    /**
	 * tn_ntcn_srvc을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TnNtcnSrvcVO
	 * @return void형
	 * @exception Exception
	 */
    void updateTnNtcnSrvc(TnNtcnSrvcVO vo) throws Exception;
    
    /**
	 * tn_ntcn_srvc을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnNtcnSrvcVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTnNtcnSrvc(TnNtcnSrvcVO vo) throws Exception;
    
    /**
	 * tn_ntcn_srvc을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TnNtcnSrvcVO
	 * @return 조회한 tn_ntcn_srvc
	 * @exception Exception
	 */
    TnNtcnSrvcVO selectTnNtcnSrvc(TnNtcnSrvcVO vo) throws Exception;
    
    /**
	 * tn_ntcn_srvc 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_ntcn_srvc 목록
	 * @exception Exception
	 */
    List selectTnNtcnSrvcList(TnNtcnSrvcDefaultVO searchVO) throws Exception;
    
    /**
	 * tn_ntcn_srvc 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_ntcn_srvc 총 갯수
	 * @exception
	 */
    int selectTnNtcnSrvcListTotCnt(TnNtcnSrvcDefaultVO searchVO);
    
    /**
	 * tn_ntcn_srvc 최대id를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_ntcn_srvc 총 갯수
	 * @exception
	 */
    int selectTnNtcnSrvMaxid(TnNtcnSrvcVO vo);
    
}
