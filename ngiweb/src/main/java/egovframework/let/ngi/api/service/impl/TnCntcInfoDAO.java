package egovframework.let.ngi.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.let.ngi.api.service.TnCntcInfoVO;
import egovframework.let.ngi.api.service.TnCntcInfoDefaultVO;

/**
 * @Class Name : TnCntcInfoDAO.java
 * @Description : TnCntcInfo DAO Class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("tnCntcInfoDAO")
public class TnCntcInfoDAO extends EgovAbstractDAO {

	/**
	 * tn_cntc_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnCntcInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTnCntcInfo(TnCntcInfoVO vo) throws Exception {
        return (String)insert("tnCntcInfoDAO.insertTnCntcInfo_S", vo);
    }

    /**
	 * tn_cntc_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TnCntcInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTnCntcInfo(TnCntcInfoVO vo) throws Exception {
        update("tnCntcInfoDAO.updateTnCntcInfo_S", vo);
    }

    /**
	 * tn_cntc_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnCntcInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTnCntcInfo(TnCntcInfoVO vo) throws Exception {
        delete("tnCntcInfoDAO.deleteTnCntcInfo_S", vo);
    }

    /**
	 * tn_cntc_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TnCntcInfoVO
	 * @return 조회한 tn_cntc_info
	 * @exception Exception
	 */
    public TnCntcInfoVO selectTnCntcInfo(TnCntcInfoVO vo) throws Exception {
        return (TnCntcInfoVO) selectByPk("tnCntcInfoDAO.selectTnCntcInfo_S", vo);
    }

    /**
	 * tn_cntc_info 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tn_cntc_info 목록
	 * @exception Exception
	 */
    public List selectTnCntcInfoList(TnCntcInfoDefaultVO searchVO) throws Exception {
        return list("tnCntcInfoDAO.selectTnCntcInfoList_D", searchVO);
    }

    /**
	 * tn_cntc_info 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tn_cntc_info 총 갯수
	 * @exception
	 */
    public int selectTnCntcInfoListTotCnt(TnCntcInfoDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("tnCntcInfoDAO.selectTnCntcInfoListTotCnt_S", searchVO);
    }

}
