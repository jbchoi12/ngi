package egovframework.let.ngi.udt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.let.ngi.chg.service.ChangeHistVO;
import egovframework.let.ngi.chg.service.ChangeInfoVO;
import egovframework.let.ngi.chg.service.impl.ChangeInfoDAO;
import egovframework.let.ngi.udt.service.Constants;
import egovframework.let.ngi.udt.service.AcptncHistVO;
import egovframework.let.ngi.udt.service.MapdmcVO;
import egovframework.let.ngi.udt.service.UpdateOperationVO;
import egovframework.let.ngi.udt.service.UpdateOperationService;

/**
 * 성과등록관리를 위한 ServiceImpl 클래스
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
@Service("updateOperationService")
public class UpdateOperationServiceImpl extends EgovAbstractServiceImpl implements UpdateOperationService {

	@Resource(name="updateOperationDAO")
	private UpdateOperationDAO updateOperationDAO;
	
	@Resource(name="acptncHistDAO")
	private AcptncHistDAO acptncHistDAO;
	
	@Resource(name="changeInfoDAO")
	private ChangeInfoDAO changeInfoDAO;
	
	
	/**
	 * 갱신작업 정보를 등록한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	/*public void insertUpdateOperation(UpdateOperaionVO vo) throws Exception {
		updateOperationDAO.insertUpdateOperation(vo); //갱신정보 등록
		
		if(vo.getChangeInfoIds() != null) {
			
			ChangeHistVO changeHistVO = new ChangeHistVO();

		
			for(int i=0; i<vo.getChangeInfoIds().length; i++) {
				vo.setChangeInfoId(Integer.parseInt(vo.getChangeInfoIds()[i]));
				updateOperationDAO.insertChangeInfoMapping(vo); //갱신정보 매핑 공사정보 등록
				changeHistVO.setChangeInfoId(Integer.parseInt(vo.getChangeInfoIds()[i]));
				changeHistVO.setSttus(vo.getSttus());
				changeInfoDAO.insertChangeHist(changeHistVO);
			}
		}
		
		vo.setFileOrgNm(vo.getDextuploadnx_instance1_path());
		updateOperationDAO.insertFile(vo);
		vo.setFileOrgNm(vo.getDextuploadnx_instance2_path());
		updateOperationDAO.insertFile(vo);
		vo.setFileOrgNm(vo.getDextuploadnx_instance3_path());
		updateOperationDAO.insertFile(vo);
		vo.setFileOrgNm(vo.getDextuploadnx_instance4_path());
		updateOperationDAO.insertFile(vo);
		
		AcptncHistVO acptncHistVO = new AcptncHistVO();
		acptncHistVO.setUpdtInfoId(vo.getUpdtInfoId());
		acptncHistVO.setSttus(vo.getSttus());
		acptncHistDAO.insertAcptncHist(acptncHistVO); //갱신이력 등록
		acptncHistDAO.updateChangeInfo(acptncHistVO); //공사정보 매핑 처리상태 업데이트
	}*/
	
	/**
	 * 성과 정보를 등록한다.
	 * @author kka
	 * @since 2014. 11. 14.
	 * @param vo
	 * @throws Exception
	 */
	public void insertUpdateOperation(UpdateOperationVO vo) throws Exception {
		updateOperationDAO.insertUpdateOperation(vo); //갱신정보 등록
		
		AcptncHistVO acptncHistVO = new AcptncHistVO();
		acptncHistVO.setUpdtInfoId(vo.getUpdtInfoId());
		acptncHistVO.setSttus(vo.getSttus());
		acptncHistDAO.insertAcptncHist(acptncHistVO); //검수이력 등록
		
		ChangeHistVO changeHistVO = new ChangeHistVO();

		changeHistVO.setChangeInfoId(vo.getChangeInfoId());
		changeHistVO.setSttus(vo.getSttus());
		changeInfoDAO.updateChangeHistory(changeHistVO); //신고보고 처리상태 업데이트
		changeInfoDAO.insertChangeHistory(changeHistVO); //신고보고 이력 등록
		
		vo.setFileOrgNm(vo.getDextuploadnx_instance1_path());
		updateOperationDAO.insertFile(vo);
		vo.setFileOrgNm(vo.getDextuploadnx_instance2_path());
		updateOperationDAO.insertFile(vo);
		vo.setFileOrgNm(vo.getDextuploadnx_instance3_path());
		updateOperationDAO.insertFile(vo);
		vo.setFileOrgNm(vo.getDextuploadnx_instance4_path());
		updateOperationDAO.insertFile(vo);
	}
	
	/**
	 * 갱신작업 정보를 수정한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	/*public void updateUpdateOpertation(UpdateOperationVO vo) throws Exception {
		updateOperationDAO.updateUpdateOperation(vo); //갱신작업 수정
		
		AcptncHistVO acptncHistVO = new AcptncHistVO();
		acptncHistVO.setUpdtInfoId(vo.getUpdtInfoId());
		
		if(vo.getChangeInfoIds() != null) {
			
			ChangeHistVO changeHistVO = new ChangeHistVO();
			acptncHistVO.setSttus("02");
			acptncHistDAO.updateChangeInfo(acptncHistVO); //공사정보 매핑 처리상태 접수완료 업데이트
	
			updateOperationDAO.deleteChangeInfoMapping(vo); //기등록된 공사정보 삭제
			
			acptncHistDAO.deleteChangeHist(acptncHistVO); //기등록된 신고&보고이력 삭제 (처리상태:지도수정중)
			
			for(int i=0; i<vo.getChangeInfoIds().length; i++) {
				vo.setChangeInfoId(Integer.parseInt(vo.getChangeInfoIds()[i]));
				updateOperationDAO.insertChangeInfoMapping(vo); //공사정보등록
				
				changeHistVO.setChangeInfoId(Integer.parseInt(vo.getChangeInfoIds()[i]));
				changeHistVO.setSttus(vo.getSttus());
				changeInfoDAO.insertChangeHist(changeHistVO);
			}
		}
		
		acptncHistVO.setSttus("07");
		acptncHistDAO.updateChangeInfo(acptncHistVO); //공사정보 매핑 처리상태 업데이트
	}*/
	
	/**
	 * 성과 정보를 수정한다.
	 * @author kka
	 * @since 2014. 11. 18.
	 * @param vo
	 * @throws Exception
	 */
	public void updateUpdateOpertation(UpdateOperationVO vo) throws Exception {
		
		updateOperationDAO.updateUpdateOperation(vo); //성과정보 수정
		
	}
	
	/**
	 * 갱신작업 정보를 삭제한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	/*public void deleteUpdateOperation(UpdateOperaionVO vo) throws Exception {
		
		AcptncHistVO acptncHistVO = new AcptncHistVO();
		acptncHistVO.setUpdtInfoId(vo.getUpdtInfoId());
		
		if(vo.getChangeInfoIds() != null) {
			acptncHistVO.setSttus("02");
			acptncHistDAO.updateChangeInfo(acptncHistVO); //공사정보 매핑 처리상태 업데이트
			updateOperationDAO.deleteChangeInfoMapping(vo); //기등록된 공사정보 삭제
			
		}
		
		updateOperationDAO.deleteUpdateOperation(vo); //갱신정보 삭제
		
	}*/
	
	/**
	 * 성과 정보를 삭제한다.
	 * @author kka
	 * @since 2014. 11. 14.
	 * @param vo
	 * @throws Exception
	 */
	public void deleteUpdateOperation(UpdateOperationVO vo) throws Exception {
		
		ChangeHistVO changeHistVO = new ChangeHistVO();
		changeHistVO.setChangeInfoId(vo.getChangeInfoId());
		
		changeHistVO.setSttus(Constants.RECEIPT_COMPLETE);
		changeInfoDAO.updateChangeHistory(changeHistVO); //신고보고 처리상태 업데이트
		
		changeHistVO.setSttus(Constants.MAP_UPDATE);
		changeInfoDAO.deleteChangeHistory(changeHistVO); //신고보고 이력 삭제
		
		updateOperationDAO.deleteFile(vo); //파일삭제
		
		updateOperationDAO.deleteUpdateOperation(vo); //성과정보 삭제
		
		updateOperationDAO.deleteAcptncHist(vo); //성과 검수이력 삭제
		
	}

	/**
	 * 갱신작업의 상세정보를 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public UpdateOperationVO selectUpdateOperation(UpdateOperationVO vo) throws Exception {
		UpdateOperationVO resultVO = updateOperationDAO.selectUpdateOperation(vo);
        
        if (resultVO == null) {
            throw processException("info.nodata.msg");
        }
        return resultVO;
	}

	
	/**
	 * 갱신작업의 상세정보를 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public UpdateOperationVO selectUpdateOperation2(UpdateOperationVO vo) throws Exception {
		UpdateOperationVO resultVO = updateOperationDAO.selectUpdateOperation2(vo);
        
        if (resultVO == null) {
            throw processException("info.nodata.msg");
        }
        return resultVO;
	}	
	
	/**
	 * 갱신작업의 목록을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<UpdateOperationVO> selectUpdateOperationList(UpdateOperationVO vo) throws Exception {
		return updateOperationDAO.selectUpdateOperationList(vo);
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
		return updateOperationDAO.selectUpdateOperationListTotCnt(vo);
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
		return updateOperationDAO.selectMapdmcList(vo);
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
		return updateOperationDAO.selectMapdmcDetailList(vo);
	}
	
}
