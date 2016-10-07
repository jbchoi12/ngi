package egovframework.let.ngi.udt.service.impl;

import java.util.List;

import egovframework.let.ngi.chg.service.ChangeInfoVO;
import egovframework.let.ngi.udt.service.AcptncHistVO;
import egovframework.let.ngi.udt.service.UpdateOperationVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 검수이력 관리를 위한 DAO 클래스
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
@Repository("acptncHistDAO")
public class AcptncHistDAO extends EgovAbstractDAO{
	
	/**
	 * 성과의 검수이력을 등록한다.
	 * @author kka
	 * @since 2014. 11. 15.
	 * @param vo
	 * @throws Exception
	 */
	public void insertAcptncHist(AcptncHistVO vo) throws Exception {
		insert("acptncHistDAO.insertAcptncHist", vo);
	}
	
	/**
	 * 갱신이력 정보를 수정한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void updateAcptncHist(AcptncHistVO vo) throws Exception {
		update("acptncHistDAO.updateAcptncHist", vo);
	}
	
	/**
	 * 갱신이력 목록을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AcptncHistVO> selectAcptncHistList(UpdateOperationVO vo) throws Exception {
		return (List<AcptncHistVO>) list("acptncHistDAO.selectAcptncHistList", vo);
	}
	
	/**
	 * 매핑된 공사정보의 처리상태를 변경한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void updateChangeInfo(AcptncHistVO vo) throws Exception {
		update("acptncHistDAO.updateChangeInfo", vo);
	}
	
	/**
	 * 매핑된 공사정보의 이력을 삭제한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void deleteChangeHist(AcptncHistVO vo) throws Exception {
		delete("acptncHistDAO.deleteChangeHist", vo);
	}
	
	/**
	 * 매핑된 공사정보 목록을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ChangeInfoVO> selectChangeInfoMappingList(AcptncHistVO vo) throws Exception {
		return (List<ChangeInfoVO>) list("acptncHistDAO.selectChangeInfoMappingList", vo);
	}
	
}
