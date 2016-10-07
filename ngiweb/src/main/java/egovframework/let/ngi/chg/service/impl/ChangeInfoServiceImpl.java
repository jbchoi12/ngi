package egovframework.let.ngi.chg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.ngi.chg.service.ChangeCntrwkInfoVO;
import egovframework.let.ngi.chg.service.ChangeGeomLcVo;
import egovframework.let.ngi.chg.service.ChangeHistVO;
import egovframework.let.ngi.chg.service.ChangeInfoDefaultVO;
import egovframework.let.ngi.chg.service.ChangeInfoRadiusSearchingVO;
import egovframework.let.ngi.chg.service.ChangeInfoService;
import egovframework.let.ngi.chg.service.ChangeInfoVO;
import egovframework.let.ngi.chg.service.ChangeSttemntCntrwkVO;
import egovframework.let.ngi.chg.service.ChangeSttemntInfoVO;
import egovframework.let.ngi.chg.service.CntrwkInfoVO;
import egovframework.let.ngi.chg.service.SttemntInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Class Name : TnChangeInfoServiceImpl.java
 * @Description : TnChangeInfo Business Implement class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-29
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Service("changeInfoService")
public class ChangeInfoServiceImpl extends AbstractServiceImpl implements
		ChangeInfoService {

	@Resource(name = "changeInfoDAO")
	private ChangeInfoDAO changeInfoDAO;

	/** ID Generation */
	// @Resource(name="{egovTnChangeInfoIdGnrService}")
	// private EgovIdGnrService egovIdGnrService;

	/**
	 * tn_change_info을 등록한다.
	 * 
	 * @param vo
	 *            - 등록할 정보가 담긴 TnChangeInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertChangeInfo(ChangeInfoVO vo) throws Exception {
		// log.debug(vo.toString());

		/** ID Generation Service */
		// TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
		// String id = egovIdGnrService.getNextStringId();
		// vo.setId(id);
		// log.debug(vo.toString());
		changeInfoDAO.insertChangeInfo(vo);
		// TODO 해당 테이블 정보에 맞게 수정
		return null;
	}

	public String insertChangeHistory(ChangeHistVO vo) throws Exception {
		// log.debug(vo.toString());

		/** ID Generation Service */
		// TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
		// String id = egovIdGnrService.getNextStringId();
		// vo.setId(id);
		// log.debug(vo.toString());
		changeInfoDAO.insertChangeHistory(vo);
		// TODO 해당 테이블 정보에 맞게 수정
		return null;
	}	
	
	public String insertChangeInfoPhone(ChangeInfoVO vo) throws Exception {
		// log.debug(vo.toString());

		/** ID Generation Service */
		// TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
		// String id = egovIdGnrService.getNextStringId();
		// vo.setId(id);
		// log.debug(vo.toString());
		changeInfoDAO.insertChangeInfoPhone(vo);
		// TODO 해당 테이블 정보에 맞게 수정
		return null;
	}
	
	public String insertChangeInfoStaff(ChangeInfoVO vo) throws Exception {
		// log.debug(vo.toString());

		/** ID Generation Service */
		// TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
		// String id = egovIdGnrService.getNextStringId();
		// vo.setId(id);
		// log.debug(vo.toString());
		changeInfoDAO.insertChangeInfoStaff(vo);
		// TODO 해당 테이블 정보에 맞게 수정
		return null;
	}
	
	/**
	 * tn_change_info을 등록한다.
	 * 
	 * @param vo
	 *            - 등록할 정보가 담긴 TnChangeInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSttemntInfo(SttemntInfoVO vo) throws Exception {
		// log.debug(vo.toString());

		/** ID Generation Service */
		// TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
		// String id = egovIdGnrService.getNextStringId();
		// vo.setId(id);
		// log.debug(vo.toString());

		changeInfoDAO.insertSttemntInfo(vo);
		// TODO 해당 테이블 정보에 맞게 수정
		return null;
	}

	/**
	 * tn_change_info을 수정한다.
	 * 
	 * @param vo
	 *            - 수정할 정보가 담긴 ChangeInfoVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateChangeInfo(ChangeInfoVO vo) throws Exception {
		changeInfoDAO.updateChangeInfo(vo);
	}

	/**
	 * tn_sttemnt_info을 수정한다.
	 * 
	 * @param vo
	 *            - 수정할 정보가 담긴 SttemntInfoVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateSttemntInfo(SttemntInfoVO vo) throws Exception {
		changeInfoDAO.updateSttemntInfo(vo);
	}

	/**
	 * tn_change_info을 삭제한다.
	 * 
	 * @param vo
	 *            - 삭제할 정보가 담긴 TnChangeInfoVO
	 * @return void형
	 * @exception Exception
	 */
	public void deleteChangeInfo(ChangeInfoVO vo) throws Exception {
		changeInfoDAO.deleteChangeInfo(vo);
	}

	/**
	 * tn_change_info을 조회한다.
	 * 
	 * @param vo
	 *            - 조회할 정보가 담긴 TnChangeInfoVO
	 * @return 조회한 tn_change_info
	 * @exception Exception
	 */
	public ChangeInfoVO selectChangeInfo(ChangeInfoVO vo) throws Exception {
		ChangeInfoVO resultVO = changeInfoDAO.selectChangeInfo(vo);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * tn_change_info을 조회한다.
	 * 
	 * @param vo
	 *            - 조회할 정보가 담긴 TnChangeInfoVO
	 * @return 조회한 tn_change_info
	 * @exception Exception
	 */
	public SttemntInfoVO selectSttemntInfo(SttemntInfoVO vo) throws Exception {
		SttemntInfoVO resultVO = changeInfoDAO.selectSttemntInfo(vo);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	public ChangeSttemntInfoVO selectChangeSttemntInfo(ChangeSttemntInfoVO vo)
			throws Exception {
		ChangeSttemntInfoVO resultVO = changeInfoDAO.selectChangeSttemntInfo(vo);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * tn_change_info 목록을 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return tn_change_info 목록
	 * @exception Exception
	 */
	public List selectChangeInfoList(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoDAO.selectChangeInfoList(searchVO);
	}

	/**
	 * tn_change_info 목록을 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return tn_change_info 목록
	 * @exception Exception
	 */
	public List selectChangeCntrwkList(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoDAO.selectChangeCntrwkList(searchVO);
	}

	public List selectChangeCntrwkListPhone(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoDAO.selectChangeCntrwkListPhone(searchVO);
	}
	
	public List selectChangeCntrwkRegList(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoDAO.selectChangeCntrwkRegList(searchVO);
	}	
	
	/**
	 * tn_change_hist 목록을 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return tn_change_hist 목록
	 * @exception Exception
	 */
	public List selectChangeHistList(ChangeHistVO vo) throws Exception {
		return changeInfoDAO.selectChangeHistList(vo);
	}

	/**
	 * tn_change_info 총 갯수를 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return tn_change_info 총 갯수
	 * @exception
	 */
	public int selectChangeInfoListTotCnt(ChangeInfoDefaultVO searchVO) {
		return changeInfoDAO.selectChangeInfoListTotCnt(searchVO);
	}

	/**
	 * tn_change_info 총 갯수를 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return tn_change_info 총 갯수
	 * @exception
	 */
	public int selectChangeCntrwkListTotCnt(ChangeInfoDefaultVO searchVO) {
		return changeInfoDAO.selectChangeCntrwkListTotCnt(searchVO);
	}
	
	public int selectChangeCntrwkListTotCntPhone(ChangeInfoDefaultVO searchVO) {
		return changeInfoDAO.selectChangeCntrwkListTotCntPhone(searchVO);
	}
	
	public int selectChangeCntrwkRegListTotCnt(ChangeInfoDefaultVO searchVO) {
		return changeInfoDAO.selectChangeCntrwkRegListTotCnt(searchVO);
	}	

	/**
	 * tn_change_info 총 갯수를 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return tn_change_info 총 갯수
	 * @exception
	 */
	public int selectChangeInfoMaxid(ChangeInfoVO vo) {
		return changeInfoDAO.selectChangeInfoMaxid(vo);
	}

	// 변동보고 시작

	/**
	 * tn_change_info을 조회한다.
	 * 
	 * @param vo
	 *            - 조회할 정보가 담긴 TnChangeInfoVO
	 * @return 조회한 tn_change_info
	 * @exception Exception
	 */
	public CntrwkInfoVO selectCntrwkInfo(CntrwkInfoVO vo) throws Exception {
		CntrwkInfoVO resultVO = changeInfoDAO.selectCntrwkInfo(vo);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	public ChangeCntrwkInfoVO selectChangeCntrwkInfo(ChangeCntrwkInfoVO vo)
			throws Exception {
		ChangeCntrwkInfoVO resultVO = changeInfoDAO.selectChangeCntrwkInfo(vo);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * tn_change_info을 등록한다.
	 * 
	 * @param vo
	 *            - 등록할 정보가 담긴 TnChangeInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCntrwkInfo(CntrwkInfoVO vo) throws Exception {
		// log.debug(vo.toString());

		/** ID Generation Service */
		// TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
		// String id = egovIdGnrService.getNextStringId();
		// vo.setId(id);
		// log.debug(vo.toString());

		changeInfoDAO.insertCntrwkInfo(vo);
		// TODO 해당 테이블 정보에 맞게 수정
		return null;
	}

	/**
	 * tn_sttemnt_info을 수정한다.
	 * 
	 * @param vo
	 *            - 수정할 정보가 담긴 SttemntInfoVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateCntrwkInfo(CntrwkInfoVO vo) throws Exception {
		changeInfoDAO.updateCntrwkInfo(vo);
	}

	public void updateCntrwkRegComInfo(String selChangeInfoIds) throws Exception {
		
		String [] changeInfoId = selChangeInfoIds.split(",");
		ChangeHistVO vo = new ChangeHistVO();

		for (int i=0; i<changeInfoId.length ; i++){
			if(changeInfoId[i].length()>0)
				changeInfoDAO.updateCntrwkRegComInfo(changeInfoId[i]);
				//vo.setRegister(register);
				vo.setChangeInfoId(Integer.parseInt(changeInfoId[i]));
				changeInfoDAO.insertChangeHistory(vo);
		}		

	}	
	
	// 변동보고 끝

	// 변화정보 시작
	/**
	 * tn_change_info 목록을 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return tn_change_info 목록
	 * @exception Exception
	 */
	public List selectChangeTrsInfoList(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoDAO.selectChangeTrsInfoList(searchVO);
	}

	/**
	 * tn_change_info 총 갯수를 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return tn_change_info 총 갯수
	 * @exception
	 */
	public int selectChangeTrsInfoListTotCnt(ChangeInfoDefaultVO searchVO) {
		return changeInfoDAO.selectChangeTrsInfoListTotCnt(searchVO);
	}

	public ChangeSttemntCntrwkVO selectChangeSttemntCntrwk(
			ChangeSttemntCntrwkVO vo) throws Exception {
		ChangeSttemntCntrwkVO resultVO = changeInfoDAO.selectChangeSttemntCntrwk(vo);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	// 변화정보 끝

	/**
	 * tn_change_lc_point 조회.
	 * 
	 * @param vo
	 * @return
	 */
	public List selectGeomLcPointInfo(ChangeGeomLcVo vo) throws Exception {
		return changeInfoDAO.selectGeomLcPointInfo(vo);
	}

	/**
	 * tn_change_lc_ln 조회.
	 * 
	 * @param vo
	 * @return
	 */
	public List selectGeomLcLnInfo(ChangeGeomLcVo vo) throws Exception {
		return changeInfoDAO.selectGeomLcLnInfo(vo);
	}

	/**
	 * tn_change_lc_myeon 조회.
	 * 
	 * @param vo
	 * @return
	 */
	public List selectGeomLcMyeonInfo(ChangeGeomLcVo vo) throws Exception {
		return changeInfoDAO.selectGeomLcMyeonInfo(vo);
	}

	/**
	 * tn_change_lc_myeon 조회.
	 * 
	 * @param vo
	 * @return
	 */
	public List selectGeomLcShpInfo(ChangeGeomLcVo vo) throws Exception {
		return changeInfoDAO.selectGeomLcShpInfo(vo);
	}	
	
	/**
	 * tn_change_lc_point 조회.
	 * 
	 * @param vo
	 * @return
	 */
	public List selectGeomLcPointInfoEPSG4326(ChangeGeomLcVo vo)
			throws Exception {
		return changeInfoDAO.selectGeomLcPointInfoEPSG4326(vo);
	}

	/**
	 * tn_change_lc_ln 조회.
	 * 
	 * @param vo
	 * @return
	 */
	public List selectGeomLcLnInfoEPSG4326(ChangeGeomLcVo vo) throws Exception {
		return changeInfoDAO.selectGeomLcLnInfoEPSG4326(vo);
	}

	/**
	 * tn_change_lc_myeon 조회.
	 * 
	 * @param vo
	 * @return
	 */
	public List selectGeomLcMyeonInfoEPSG4326(ChangeGeomLcVo vo)
			throws Exception {
		return changeInfoDAO.selectGeomLcMyeonInfoEPSG4326(vo);
	}
	
	/**
	 * tn_change_lc_myeon 조회.
	 * 
	 * @param vo
	 * @return
	 */
	public List selectGeomLcShpInfoEPSG4326(ChangeGeomLcVo vo)
			throws Exception {
		return changeInfoDAO.selectGeomLcShpInfoEPSG4326(vo);
	}	

	/**
	 * tn_change_lc_point 입력.
	 * 
	 * @param vo
	 * @return
	 */
	public String insertGeomLcPointInfo(ChangeGeomLcVo vo) throws Exception {
		return changeInfoDAO.insertGeomLcPointInfo(vo);
	}

	/**
	 * tn_change_lc_ln 입력.
	 * 
	 * @param vo
	 * @return
	 */
	public String insertGeomLcLnInfo(ChangeGeomLcVo vo) throws Exception {
		return changeInfoDAO.insertGeomLcLnInfo(vo);
	}

	/**
	 * tn_change_lc_myeon 입력.
	 * 
	 * @param vo
	 * @return
	 */
	public String insertGeomLcMyeonInfo(ChangeGeomLcVo vo) throws Exception {
		return changeInfoDAO.insertGeomLcMyeonInfo(vo);
	}

	/**
	 * tn_change_lc_point 삭제 .
	 * 
	 * @param vo
	 * @return
	 */
	public void deleteGeomLcPointInfo(ChangeGeomLcVo vo) throws Exception {
		changeInfoDAO.deleteGeomLcPointInfo(vo);
	}

	/**
	 * tn_change_lc_ln 삭제 .
	 * 
	 * @param vo
	 * @return
	 */
	public void deleteGeomLcLnInfo(ChangeGeomLcVo vo) throws Exception {
		changeInfoDAO.deleteGeomLcLnInfo(vo);
	}

	/**
	 * tn_change_lc_myeon 삭제 .
	 * 
	 * @param vo
	 * @return
	 */
	public void deleteGeomLcMyeonInfo(ChangeGeomLcVo vo) throws Exception {
		changeInfoDAO.deleteGeomLcMyeonInfo(vo);
	}

	@Override
	public List selectChangeInfoInRectList(ChangeInfoRadiusSearchingVO searchVO)
			throws Exception {
		return changeInfoDAO.selectChangeInfoListInRect(searchVO);
	}

	@Override
	public List selectChangeInfoTrsInRectList(ChangeInfoRadiusSearchingVO searchVO)
			throws Exception {
		return changeInfoDAO.selectChangeInfoTrsListInRect(searchVO);
	}
	
	
	@Override
	public int selectChangeInfoListInRectTotCnt(
			ChangeInfoRadiusSearchingVO searchVO) throws Exception {
		return changeInfoDAO.selectChangeInfoListInRectTotCnt(searchVO);
	}
	
	@Override
	public int selectChangeInfoTrsListInRectTotCnt(
			ChangeInfoRadiusSearchingVO searchVO) throws Exception {
		return changeInfoDAO.selectChangeInfoTrsListInRectTotCnt(searchVO);
	}
	

	@Override
	public List selectChangeInfoPhoneList(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoDAO.selectChangeInfoListPhone(searchVO);
	}

	@Override
	public List selectChangeInfoTrsPhoneList(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoDAO.selectChangeInfoTrsListPhone(searchVO);
	}
	
	@Override
	public int selectChangeInfoListTotCntPhone(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoDAO.selectChangeInfoListTotCntPhone(searchVO);
	}

	@Override
	public int selectChangeInfoTrsListTotCntPhone(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoDAO.selectChangeInfoTrsListTotCntPhone(searchVO);
	}
	
	
	/**
	 * 변경신고 차트 월별 건수
	 */
	@Override
	public List selectChangeInfoChartSttemntMonth(String year) throws Exception {
		return changeInfoDAO.selectChangeInfoChartSttemntMonth(year);
	}

	/**
	 * 변경신고 차트 유형별 건수
	 */
	@Override
	public List selectChangeInfoChartSttemntChangeTy(String year)
			throws Exception {
		return changeInfoDAO.selectChangeInfoChartSttemntChangeTy(year);
	}

	/**
	 * 변경신고 차트 처리상태 건수
	 */
	@Override
	public List selectChangeInfoChartSttemntProcessSttusSe(String year)
			throws Exception {
		return changeInfoDAO.selectChangeInfoChartSttemntProcessSttusSe(year);
	}

	/**
	 * 변경신고 차트 년도별 건수
	 */
	@Override
	public List selectChangeInfoChartSttemntYear() throws Exception {
		return changeInfoDAO.selectChangeInfoChartSttemntYear();
	}

	/**
	 * 변동신고 차트 월별 건수
	 */
	@Override
	public List selectChangeInfoChartCntrwkMonth(String year) throws Exception {
		return changeInfoDAO.selectChangeInfoChartCntrwkMonth(year);
	}

	/**
	 * 변동신고 차트 유형별 건수
	 */
	@Override
	public List selectChangeInfoChartCntrwkChangeTy(String year)
			throws Exception {
		return changeInfoDAO.selectChangeInfoChartCntrwkChangeTy(year);
	}

	/**
	 * 변동신고 차트 처리상태 건수
	 */
	@Override
	public List selectChangeInfoChartCntrwkProcessSttusSe(String year)
			throws Exception {
		return changeInfoDAO.selectChangeInfoChartCntrwkProcessSttusSe(year);
	}

	/**
	 * 변동신고 차트 년도별 건수
	 */
	@Override
	public List selectChangeInfoChartCntrwkYear() throws Exception {
		return changeInfoDAO.selectChangeInfoChartCntrwkYear();
	}

	/**
	 * 차트 조회 년도 리스트
	 */
	@Override
	public List selectChangeInfoChartListYear() throws Exception {
		return changeInfoDAO.selectChangeInfoChartListYear();
	}

	/** 변화정보 차트 월별 건수 */
	@Override
	public List selectChangeInfoChartFinishMonth(String year) throws Exception {
		return changeInfoDAO.selectChangeInfoChartFinishMonth(year);
	}

	/** 변화정보 차트 유형별 건수 */
	@Override
	public List selectChangeInfoChartFinishChangeTy(String year)
			throws Exception {
		return changeInfoDAO.selectChangeInfoChartFinishChangeTy(year);
	}

	/** 변화정보 차트 사업지구별 건수 */
	@Override
	public List selectChangeInfoChartFinishBsnsDstrc(String year)
			throws Exception {
		return changeInfoDAO.selectChangeInfoChartFinishBsnsDstrc(year);
	}

	/** 변화정보 차트 년도별 건수 */
	@Override
	public List selectChangeInfoChartFinishYear() throws Exception {
		return changeInfoDAO.selectChangeInfoChartFinishYear();
	}

	/** 변경신고 차트에서 사용. 메인페이지용 */
	@Override
	public List selectChangeInfoChartCntJson(String area) throws Exception {
		return changeInfoDAO.selectChangeInfoChartCntJson(area); 
		
	}
	/** 사업지구 리스트  */
	@Override
	public List selectBgnssNmList() throws Exception {
		return changeInfoDAO.selectBgnssNmList();
	}	
}
