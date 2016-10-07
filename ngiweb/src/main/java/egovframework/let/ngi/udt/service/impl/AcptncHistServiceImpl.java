package egovframework.let.ngi.udt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import egovframework.let.ngi.chg.service.ChangeHistVO;
import egovframework.let.ngi.chg.service.ChangeInfoVO;
import egovframework.let.ngi.chg.service.impl.ChangeInfoDAO;
import egovframework.let.ngi.udt.service.Constants;
import egovframework.let.ngi.udt.service.AcptncHistService;
import egovframework.let.ngi.udt.service.AcptncHistVO;
import egovframework.let.ngi.udt.service.UpdateOperationVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 검수이력 관리를 위한 ServiceImpl 클래스
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
@Service("acptncHistService")
public class AcptncHistServiceImpl extends EgovAbstractServiceImpl implements AcptncHistService{
	
	@Resource(name="acptncHistDAO")
	private AcptncHistDAO acptncHistDAO;
	
	@Resource(name="updateOperationDAO")
	private UpdateOperationDAO updateOperationDAO;
	
	@Resource(name="changeInfoDAO")
	private ChangeInfoDAO changeInfoDAO;
	
	/**
	 * 성과의 검수이력을 등록한다.
	 * @author kka
	 * @since 2014. 11. 15.
	 * @param vo
	 * @throws Exception
	 */
	public void insertAcptncHist(AcptncHistVO vo) throws Exception {
		acptncHistDAO.insertAcptncHist(vo); //검수이력 등록
		
		UpdateOperationVO updateOperaionVO = new UpdateOperationVO();
		updateOperaionVO.setUpdtInfoId(vo.getUpdtInfoId());
		updateOperaionVO =  updateOperationDAO.selectUpdateOperation(updateOperaionVO);
		updateOperaionVO.setSttus(vo.getSttus());
		updateOperationDAO.updateUpdateOperation(updateOperaionVO); //성과정보 상태 업데이트
		
		if(StringUtils.equals(vo.getSttus(), Constants.INSPECTION_COMPLETE)) {
			
			ChangeHistVO changeHistVO = new ChangeHistVO();

			changeHistVO.setChangeInfoId(updateOperaionVO.getChangeInfoId());
			changeHistVO.setSttus(vo.getSttus());
			changeInfoDAO.updateChangeHistory(changeHistVO); //신고보고 처리상태 업데이트
			changeInfoDAO.insertChangeHistory(changeHistVO); //신고보고 이력 등록
			
		}
	}
	
	/**
	 * 갱신작업의 검수이력을 일괄 검수완료로 등록한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void insertAcptncHistMulti(UpdateOperationVO vo) throws Exception {
		
		
		AcptncHistVO acptncHistVO = new AcptncHistVO();
		acptncHistVO.setSttus(vo.getSttus());
		
		
		String[] ids = vo.getUpdtInfoIds();
		if(ids != null) {
			for(int i=0; i<ids.length; i++) {
				acptncHistVO.setUpdtInfoId(Integer.parseInt(vo.getUpdtInfoIds()[i]));
				acptncHistDAO.insertAcptncHist(acptncHistVO); //갱신이력 등록
				acptncHistDAO.updateChangeInfo(acptncHistVO); //공사정보 상태 업데이트
				vo.setUpdtInfoId(Integer.parseInt(vo.getUpdtInfoIds()[i]));
				updateOperationDAO.updateUpdateOperation(vo); //갱신정보 상태 업데이트
				
				List<ChangeInfoVO> list= acptncHistDAO.selectChangeInfoMappingList(acptncHistVO);
				ChangeHistVO changeHistVO = new ChangeHistVO();
				
				int idx = list.size();
				
				if(idx != 0) {
					
					for(int j=0; j<idx; j++) {
						
						changeHistVO.setChangeInfoId(list.get(j).getChangeInfoId());
						changeHistVO.setSttus(vo.getSttus());
						changeInfoDAO.insertChangeHistory(changeHistVO);
						
					}
				}
				
			}
		}
	}
	

}
