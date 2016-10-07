package egovframework.let.ngi.chg.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.let.ngi.chg.service.TnAtchmnflDefaultVO;

/**
 * @Class Name : TnAtchmnflDAO.java
 * @Description : TnAtchmnfl DAO Class
 * @Modification Information
 *
 * @author a
 * @since a
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("tnAtchmnflDAO")
public class TnAtchmnflDAO extends EgovAbstractDAO {

	/**
	 * tn_atchmnfl을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnAtchmnflVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTnAtchmnfl(TnAtchmnflVO vo) throws Exception {
        return (String)insert("tnAtchmnflDAO.insertTnAtchmnfl_S", vo);
    }

    /**
	 * tn_atchmnfl을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TnAtchmnflVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTnAtchmnfl(TnAtchmnflVO vo) throws Exception {
        update("tnAtchmnflDAO.updateTnAtchmnfl_S", vo);
    }

    /**
	 * tn_atchmnfl을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnAtchmnflVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTnAtchmnfl(TnAtchmnflVO vo) throws Exception {
        delete("tnAtchmnflDAO.deleteTnAtchmnfl_S", vo);
    }

    public void deleteTnAtchmnflPhone(TnAtchmnflVO vo) throws Exception {
        delete("tnAtchmnflDAO.deleteTnAtchmnflPhone_S", vo);
    }
    
    /**
	 * tn_atchmnfl을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TnAtchmnflVO
	 * @return 조회한 tn_atchmnfl
	 * @exception Exception
	 */
    public TnAtchmnflVO selectTnAtchmnfl(TnAtchmnflVO vo) throws Exception {
        return (TnAtchmnflVO) selectByPk("tnAtchmnflDAO.selectTnAtchmnfl_S", vo);
    }

    /**
	 * tn_atchmnfl 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tn_atchmnfl 목록
	 * @exception Exception
	 */
    public List selectTnAtchmnflList(TnAtchmnflDefaultVO searchVO) throws Exception {
        return list("tnAtchmnflDAO.selectTnAtchmnflList_D", searchVO);
    }

    public List selectTnAtchmnflListPhone(TnAtchmnflDefaultVO searchVO) throws Exception {
        return list("tnAtchmnflDAO.selectTnAtchmnflListPhone_D", searchVO);
    }
    
    /**
	 * tn_atchmnfl 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tn_atchmnfl 총 갯수
	 * @exception
	 */
    public int selectTnAtchmnflListTotCnt(TnAtchmnflDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("tnAtchmnflDAO.selectTnAtchmnflListTotCnt_S", searchVO);
    }

}
