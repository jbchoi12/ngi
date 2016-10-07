package egovframework.let.ngi.udt.service.impl;

import java.util.List;






import egovframework.let.ngi.udt.service.MapdmcVO;
import egovframework.let.ngi.udt.service.UpdateOperationVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 성과등록 관리를 위한 DAO 클래스
 * @author kka
 * @since 2014. 10. 29.
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
@Repository("updateOperationDAO")
public class UpdateOperationDAO extends EgovAbstractDAO {
	
	/**
	 * 갱신작업 정보를 등록을 한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void insertUpdateOperation(UpdateOperationVO vo) throws Exception {
		insert("updateOperationDAO.insertUpdateOperation", vo);
	}
	
	/**
	 * 갱신작업 정보를 수정한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void updateUpdateOperation(UpdateOperationVO vo) throws Exception {
		update("updateOperationDAO.updateUpdateOperation", vo);
	}
	
	/**
	 * 갱신작업에 매핑된 공사정보를 삭제한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void deleteChangeInfoMapping(UpdateOperationVO vo) throws Exception {
		delete("updateOperationDAO.deleteChangeInfoMapping", vo);
	}
	
	/**
	 * 갱신작업 정보를 삭제한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void deleteUpdateOperation(UpdateOperationVO vo) throws Exception {
		delete("updateOperationDAO.deleteUpdateOperation", vo);
	}

	/**
	 * 갱신작업 상세정보를 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public UpdateOperationVO selectUpdateOperation(UpdateOperationVO vo) throws Exception {
		return (UpdateOperationVO) selectByPk("updateOperationDAO.selectUpdateOperation", vo);
	}

	/**
	 * 갱신작업 상세정보를 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public UpdateOperationVO selectUpdateOperation2(UpdateOperationVO vo) throws Exception {
		return (UpdateOperationVO) selectByPk("updateOperationDAO.selectUpdateOperation2", vo);
	}	
	
	/**
	 * 갱신작업 목록을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<UpdateOperationVO> selectUpdateOperationList(UpdateOperationVO vo) throws Exception {
		return (List<UpdateOperationVO>) list("updateOperationDAO.selectUpdateOperationList", vo);
	}
	
	/**
	 * 갱신작업 목록 수를 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectUpdateOperationListTotCnt(UpdateOperationVO vo) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("updateOperationDAO.selectUpdateOperationListTotCnt", vo);
	}
	
	/**
	 * 갱신작업 파일을 등록한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void insertFile(UpdateOperationVO vo) throws Exception {
		insert("updateOperationDAO.insertFile", vo);
	}
	
	/**
	 * 갱신작업에 매핑되는 공사정보를 등록한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void insertChangeInfoMapping(UpdateOperationVO vo) throws Exception {
		insert("updateOperationDAO.insertChangeInfoMapping", vo);
	}
	
	/**
	 * 도엽 목록을 조회한다.
	 * @author kka
	 * @since 2014. 11. 12.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<?> selectMapdmcList(MapdmcVO vo) throws Exception {
		return (List<?>) list("updateOperationDAO.selectMapdmcList", vo);
	}
	
	/**
	 * 도엽번호 목록을 조회한다.
	 * @author kka
	 * @since 2014. 11. 12.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<?> selectMapdmcDetailList(MapdmcVO vo) throws Exception {
		return (List<?>) list("updateOperationDAO.selectMapdmcDetailList", vo);
	}
	
	/**
	 * 파일정보를 삭제한다.
	 * @author kka
	 * @since 2014. 11. 14.
	 * @param vo
	 * @throws Exception
	 */
	public void deleteFile(UpdateOperationVO vo) throws Exception {
		delete("updateOperationDAO.deleteFile", vo);
	}
	
	/**
	 * 성과 검수 이력을 삭제한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void deleteAcptncHist(UpdateOperationVO vo) throws Exception {
		delete("updateOperationDAO.deleteAcptncHist", vo);
	}
}
