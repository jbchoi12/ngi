package egovframework.let.ngi.ntc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.let.ngi.ntc.service.TnNtcnSrvcVO;
import egovframework.let.ngi.ntc.service.TnNtcnSrvcDefaultVO;

/**
 * @Class Name : TnNtcnSrvcDAO.java
 * @Description : TnNtcnSrvc DAO Class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("tnNtcnSrvcDAO")
public class TnNtcnSrvcDAO extends EgovAbstractDAO {

	/**
	 * tn_ntcn_srvc을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnNtcnSrvcVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTnNtcnSrvc(TnNtcnSrvcVO vo) throws Exception {
        return (String)insert("tnNtcnSrvcDAO.insertTnNtcnSrvc_S", vo);
    }

    /**
	 * tn_ntcn_srvc을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TnNtcnSrvcVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTnNtcnSrvc(TnNtcnSrvcVO vo) throws Exception {
        update("tnNtcnSrvcDAO.updateTnNtcnSrvc_S", vo);
    }

    /**
	 * tn_ntcn_srvc을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnNtcnSrvcVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTnNtcnSrvc(TnNtcnSrvcVO vo) throws Exception {
        delete("tnNtcnSrvcDAO.deleteTnNtcnSrvc_S", vo);
    }

    /**
	 * tn_ntcn_srvc을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TnNtcnSrvcVO
	 * @return 조회한 tn_ntcn_srvc
	 * @exception Exception
	 */
    public TnNtcnSrvcVO selectTnNtcnSrvc(TnNtcnSrvcVO vo) throws Exception {
        return (TnNtcnSrvcVO) selectByPk("tnNtcnSrvcDAO.selectTnNtcnSrvc_S", vo);
    }

    /**
	 * tn_ntcn_srvc 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tn_ntcn_srvc 목록
	 * @exception Exception
	 */
    public List selectTnNtcnSrvcList(TnNtcnSrvcDefaultVO searchVO) throws Exception {
        return list("tnNtcnSrvcDAO.selectTnNtcnSrvcList_D", searchVO);
    }

    /**
	 * tn_ntcn_srvc 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tn_ntcn_srvc 총 갯수
	 * @exception
	 */
    public int selectTnNtcnSrvcListTotCnt(TnNtcnSrvcDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("tnNtcnSrvcDAO.selectTnNtcnSrvcListTotCnt_S", searchVO);
    }
    
    /**
     * tn_ntcn_srvc 최대값을 구한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    public int selectTnNtcnSrvMaxid(TnNtcnSrvcVO vo){
    	return (Integer)getSqlMapClientTemplate().queryForObject("tnNtcnSrvcDAO.selectTnNtcnSrvMaxid", vo);
    }    
    

}
