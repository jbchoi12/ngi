package egovframework.let.ngi.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.let.ngi.api.service.TnChangeAllInfoListSearchVO;
import egovframework.let.ngi.api.service.TnChangeAllInfoVO;
import egovframework.let.ngi.api.service.TnChangeAllInfoDefaultVO;
import egovframework.let.ngi.api.service.TnChangeDetailInfoVO;

/**
 * @Class Name : TnChangeAllInfoDAO.java
 * @Description : TnChangeAllInfo DAO Class
 * @Modification Information
 *
 * @author a
 * @since a
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("tnChangeAllInfoDAO")
public class TnChangeAllInfoDAO extends EgovAbstractDAO {

	/**
	 * tn_change_all_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnChangeAllInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTnChangeAllInfo(TnChangeAllInfoVO vo) throws Exception {
        return (String)insert("tnChangeAllInfoDAO.insertTnChangeAllInfo_S", vo);
    }

    /**
	 * tn_change_all_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TnChangeAllInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTnChangeAllInfo(TnChangeAllInfoVO vo) throws Exception {
        update("tnChangeAllInfoDAO.updateTnChangeAllInfo_S", vo);
    }

    /**
	 * tn_change_all_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnChangeAllInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTnChangeAllInfo(TnChangeAllInfoVO vo) throws Exception {
        delete("tnChangeAllInfoDAO.deleteTnChangeAllInfo_S", vo);
    }

    /**
	 * tn_change_all_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TnChangeAllInfoVO
	 * @return 조회한 tn_change_all_info
	 * @exception Exception
	 */
    public TnChangeAllInfoVO selectTnChangeAllInfo(TnChangeAllInfoVO vo) throws Exception {
        return (TnChangeAllInfoVO) selectByPk("tnChangeAllInfoDAO.selectTnChangeAllInfo_S", vo);
    }

    /**
	 * tn_change_all_info 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tn_change_all_info 목록
	 * @exception Exception
	 */
    public List<?> selectTnChangeAllInfoList(TnChangeAllInfoVO searchVO) throws Exception {
        return list("tnChangeAllInfoDAO.selectTnChangeAllInfoList_D", searchVO);
    }

    /**
	 * tn_change_all_info 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tn_change_all_info 총 갯수
	 * @exception
	 */
    public int selectTnChangeAllInfoListTotCnt(TnChangeAllInfoVO searchVO) {
        return (Integer)selectByPk("tnChangeAllInfoDAO.selectTnChangeAllInfoListTotCnt_S", searchVO);
    }

}
