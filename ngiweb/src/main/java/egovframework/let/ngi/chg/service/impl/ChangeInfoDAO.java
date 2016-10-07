package egovframework.let.ngi.chg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.let.ngi.chg.service.ChangeCntrwkInfoVO;
import egovframework.let.ngi.chg.service.ChangeGeomLcVo;
import egovframework.let.ngi.chg.service.ChangeHistVO;
import egovframework.let.ngi.chg.service.ChangeInfoDefaultVO;
import egovframework.let.ngi.chg.service.ChangeInfoRadiusSearchingVO;
import egovframework.let.ngi.chg.service.ChangeInfoVO;
import egovframework.let.ngi.chg.service.ChangeSttemntCntrwkVO;
import egovframework.let.ngi.chg.service.ChangeSttemntInfoVO;
import egovframework.let.ngi.chg.service.CntrwkInfoVO;
import egovframework.let.ngi.chg.service.SttemntInfoVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : ChangeInfoDAO.java
 * @Description : ChangeInfo DAO Class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-29
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Repository("changeInfoDAO")
public class ChangeInfoDAO extends EgovAbstractDAO {

	/**
	 * tn_change_info을 등록한다.
	 * 
	 * @param vo
	 *            - 등록할 정보가 담긴 ChangeInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertChangeInfo(ChangeInfoVO vo) throws Exception {
		return (String) insert("changeInfoDAO.insertChangeInfo_S", vo);
	}
	
	public String insertChangeInfoPhone(ChangeInfoVO vo) throws Exception {
		return (String) insert("changeInfoDAO.insertChangeInfoMember_S", vo);
	}
	
	public String insertChangeInfoStaff(ChangeInfoVO vo) throws Exception {
		return (String) insert("changeInfoDAO.insertChangeInfoStaff_S", vo);
	}

	
	public String insertChangeHistory(ChangeHistVO vo) throws Exception {
		return (String) insert("changeInfoDAO.insertChangeHistory_S", vo);
	}	
	
	/**
	 * tn_change_info을 등록한다.
	 * 
	 * @param vo
	 *            - 등록할 정보가 담긴 ChangeInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSttemntInfo(SttemntInfoVO vo) throws Exception {
		return (String) insert("changeInfoDAO.insertSttemntInfo_S", vo);
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
		update("changeInfoDAO.updateChangeInfo_S", vo);
	}

	/**
	 * tn_Sttemnt_info을 수정한다.
	 * 
	 * @param vo
	 *            - 수정할 정보가 담긴 SttemntInfoVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateSttemntInfo(SttemntInfoVO vo) throws Exception {
		update("changeInfoDAO.updateSttemntInfo_S", vo);
	}

    /**
     * 신고&보고 정보를 수정한다.
     * @author kka
     * @since 2014. 10. 27.
     * @param vo
     * @throws Exception
     */
    public void updateChangeHistory(ChangeHistVO vo) throws Exception {
        update("changeInfoDAO.updateChangeHistory", vo);
    }	
	
	/**
	 * tn_change_info을 삭제한다.
	 * 
	 * @param vo
	 *            - 삭제할 정보가 담긴 ChangeInfoVO
	 * @return void형
	 * @exception Exception
	 */
	public void deleteChangeInfo(ChangeInfoVO vo) throws Exception {
		delete("changeInfoDAO.deleteChangeInfo_S", vo);
	}

    /**
     * 신고&보고 이력을 삭제한다.
     * @author kka
     * @since 2014. 10. 27.
     * @param vo
     * @throws Exception
     */
    public void deleteChangeHistory(ChangeHistVO vo) throws Exception {
        delete("changeInfoDAO.deleteChangeHistry", vo);
    }	
	
	
	/**
	 * tn_change_info을 조회한다.
	 * 
	 * @param vo
	 *            - 조회할 정보가 담긴 ChangeInfoVO
	 * @return 조회한 tn_change_info
	 * @exception Exception
	 */
	public ChangeInfoVO selectChangeInfo(ChangeInfoVO vo) throws Exception {
		return (ChangeInfoVO) selectByPk("changeInfoDAO.selectChangeInfo_S", vo);
	}

	/**
	 * tn_change_info을 조회한다.
	 * 
	 * @param vo
	 *            - 조회할 정보가 담긴 ChangeInfoVO
	 * @return 조회한 tn_change_info
	 * @exception Exception
	 */
	public SttemntInfoVO selectSttemntInfo(SttemntInfoVO vo) throws Exception {
		return (SttemntInfoVO) selectByPk("changeInfoDAO.selectSttemntInfo_S",
				vo);
	}

	public ChangeSttemntInfoVO selectChangeSttemntInfo(ChangeSttemntInfoVO vo)
			throws Exception {
		return (ChangeSttemntInfoVO) selectByPk(
				"changeInfoDAO.selectChangeSttemntInfo_S", vo);
	}

	/**
	 * tn_change_info 목록을 조회한다.
	 * 
	 * @param searchMap
	 *            - 조회할 정보가 담긴 Map
	 * @return tn_change_info 목록
	 * @exception Exception
	 */
	public List selectChangeInfoList(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return list("changeInfoDAO.selectChangeInfoList_D", searchVO);
	}

	/**
	 * tn_change_info 목록을 조회한다.
	 * 
	 * @param searchMap
	 *            - 조회할 정보가 담긴 Map
	 * @return tn_change_info 목록
	 * @exception Exception
	 */
	public List selectChangeCntrwkList(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return list("changeInfoDAO.selectChangeCntrwkList_D", searchVO);
	}
	
	public List selectChangeCntrwkListPhone(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return list("changeInfoDAO.selectChangeCntrwkListPhone_D", searchVO);
	}
	
	public List selectChangeCntrwkRegList(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return list("changeInfoDAO.selectChangeCntrwkRegList_D", searchVO);
	}	

	public List selectChangeInfoListInRect(ChangeInfoRadiusSearchingVO searchVO)
			throws Exception {
		return list("changeInfoDAO.selectChangeInfoInRectList_D", searchVO);
	}
	
	public List selectChangeInfoTrsListInRect(ChangeInfoRadiusSearchingVO searchVO)
			throws Exception {
		return list("changeInfoDAO.selectChangeInfoTrsInRectList_D", searchVO);
	}

	public List selectChangeInfoListPhone(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return list("changeInfoDAO.selectChangeInfoList_Phone_D", searchVO);
	}

	public List selectChangeInfoTrsListPhone(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return list("changeInfoDAO.selectChangeInfoTrsList_Phone_D", searchVO);
	}
	
	/**
	 * tn_change_info 목록을 조회한다.
	 * 
	 * @param searchMap
	 *            - 조회할 정보가 담긴 Map
	 * @return tn_change_info 목록
	 * @exception Exception
	 */
	public List selectChangeHistList(ChangeHistVO vo) throws Exception {
		return list("changeInfoDAO.selectChangeHistList_D", vo);
	}

	/**
	 * tn_change_info 총 갯수를 조회한다.
	 * 
	 * @param searchMap
	 *            - 조회할 정보가 담긴 Map
	 * @return tn_change_info 총 갯수
	 * @exception
	 */
	public int selectChangeInfoListTotCnt(ChangeInfoDefaultVO searchVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"changeInfoDAO.selectChangeInfoListTotCnt_S", searchVO);
	}

	/**
	 * tn_change_info 총 갯수를 조회한다.
	 * 
	 * @param searchMap
	 *            - 조회할 정보가 담긴 Map
	 * @return tn_change_info 총 갯수
	 * @exception
	 */
	public int selectChangeCntrwkListTotCnt(ChangeInfoDefaultVO searchVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"changeInfoDAO.selectChangeCntrwkListTotCnt_S", searchVO);
	}
	
	public int selectChangeCntrwkListTotCntPhone(ChangeInfoDefaultVO searchVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"changeInfoDAO.selectChangeCntrwkListTotCnt_S_Phone", searchVO);
	}
	
	public int selectChangeCntrwkRegListTotCnt(ChangeInfoDefaultVO searchVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"changeInfoDAO.selectChangeCntrwkRegListTotCnt_S", searchVO);
	}	

	public int selectChangeInfoListTotCntPhone(ChangeInfoDefaultVO searchVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"changeInfoDAO.selectChangeInfoListTotCnt_Phone_S", searchVO);
	}

	public int selectChangeInfoTrsListTotCntPhone(ChangeInfoDefaultVO searchVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"changeInfoDAO.selectChangeInfoTrsListTotCnt_Phone_S", searchVO);
	}
	
	public int selectChangeInfoListInRectTotCnt(
			ChangeInfoRadiusSearchingVO searchVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"changeInfoDAO.selectChangeInfoListInRectTotCnt_S", searchVO);
	}

	public int selectChangeInfoTrsListInRectTotCnt(
			ChangeInfoRadiusSearchingVO searchVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"changeInfoDAO.selectChangeInfoTrListInRectTotCnt_S", searchVO);
	}
	
	/**
	 * tn_change_info 총 갯수를 조회한다.
	 * 
	 * @param searchMap
	 *            - 조회할 정보가 담긴 Map
	 * @return tn_change_info 총 갯수
	 * @exception
	 */
	public int selectChangeInfoMaxid(ChangeInfoVO vo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"changeInfoDAO.selectChangeInfoMaxid", vo);
	}

	// 변동보고 시작

	/**
	 * tn_change_info을 조회한다.
	 * 
	 * @param vo
	 *            - 조회할 정보가 담긴 ChangeInfoVO
	 * @return 조회한 tn_change_info
	 * @exception Exception
	 */
	public CntrwkInfoVO selectCntrwkInfo(CntrwkInfoVO vo) throws Exception {
		return (CntrwkInfoVO) selectByPk("changeInfoDAO.selectCntrwkInfo_S", vo);
	}

	public ChangeCntrwkInfoVO selectChangeCntrwkInfo(ChangeCntrwkInfoVO vo)
			throws Exception {
		return (ChangeCntrwkInfoVO) selectByPk(
				"changeInfoDAO.selectChangeCntrwkInfo_S", vo);
	}

	/**
	 * tn_change_info을 등록한다.
	 * 
	 * @param vo
	 *            - 등록할 정보가 담긴 ChangeInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCntrwkInfo(CntrwkInfoVO vo) throws Exception {
		return (String) insert("changeInfoDAO.insertCntrwkInfo_S", vo);
	}

	/**
	 * tn_Sttemnt_info을 수정한다.
	 * 
	 * @param vo
	 *            - 수정할 정보가 담긴 SttemntInfoVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateCntrwkInfo(CntrwkInfoVO vo) throws Exception {
		update("changeInfoDAO.updateCntrwkInfo_S", vo);
	}

	public void updateCntrwkRegComInfo(String selChangeInfoIds) throws Exception {
		update("changeInfoDAO.updateCntrwkRegComInfo_S", selChangeInfoIds);
	}	
	
	// 변동보고 끝

	// 변화정보 시작

	/**
	 * tn_change_info 목록을 조회한다.
	 * 
	 * @param searchMap
	 *            - 조회할 정보가 담긴 Map
	 * @return tn_change_info 목록
	 * @exception Exception
	 */
	public List selectChangeTrsInfoList(ChangeInfoDefaultVO searchVO)
			throws Exception {
		return list("changeInfoDAO.selectChangeTrsInfoList_D", searchVO);
	}

	/**
	 * tn_change_info 총 갯수를 조회한다.
	 * 
	 * @param searchMap
	 *            - 조회할 정보가 담긴 Map
	 * @return tn_change_info 총 갯수
	 * @exception
	 */
	public int selectChangeTrsInfoListTotCnt(ChangeInfoDefaultVO searchVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"changeInfoDAO.selectChangeTrsInfoListTotCnt_S", searchVO);
	}

	public ChangeSttemntCntrwkVO selectChangeSttemntCntrwk(
			ChangeSttemntCntrwkVO vo) throws Exception {
		return (ChangeSttemntCntrwkVO) selectByPk(
				"changeInfoDAO.selectChangeSttemntCntrwk_S", vo);
	}

	// 변화정보 끝

	/**
	 * tn_change_lc_point geom 데이터 조회.
	 * 
	 * @param vo
	 * @return tn_change_lc_point 데이터.
	 */
	public List selectGeomLcPointInfo(ChangeGeomLcVo vo) throws Exception {
		return list("changeInfoDAO.selectGeomLcPointInfo", vo);
	}

	/**
	 * tn_change_lc_ln geom 데이터 조회.
	 * 
	 * @param vo
	 * @return tn_change_lc_ln 데이터.
	 */
	public List selectGeomLcLnInfo(ChangeGeomLcVo vo) throws Exception {
		return list("changeInfoDAO.selectGeomLcLnInfo", vo);
	}

	/**
	 * tn_change_lc_myeon geom 데이터 조회.
	 * 
	 * @param vo
	 * @return tn_change_lc_myeon 데이터.
	 */
	public List selectGeomLcMyeonInfo(ChangeGeomLcVo vo) throws Exception {
		return list("changeInfoDAO.selectGeomLcMyeonInfo", vo);
	}

	/**
	 * tn_change_lc_myeon geom 데이터 조회.
	 * 
	 * @param vo
	 * @return tn_change_lc_myeon 데이터.
	 */
	public List selectGeomLcShpInfo(ChangeGeomLcVo vo) throws Exception {
		return list("changeInfoDAO.selectGeomLcShpInfo", vo);
	}	
	
	/**
	 * tn_change_lc_point geom 데이터 조회.
	 * 
	 * @param vo
	 * @return tn_change_lc_point 데이터.
	 */
	public List selectGeomLcPointInfoEPSG4326(ChangeGeomLcVo vo)
			throws Exception {
		return list("changeInfoDAO.selectGeomLcPointInfoEPSG4326", vo);
	}

	/**
	 * tn_change_lc_ln geom 데이터 조회.
	 * 
	 * @param vo
	 * @return tn_change_lc_ln 데이터.
	 */
	public List selectGeomLcLnInfoEPSG4326(ChangeGeomLcVo vo) throws Exception {
		return list("changeInfoDAO.selectGeomLcLnInfoEPSG4326", vo);
	}

	/**
	 * tn_change_lc_myeon geom 데이터 조회.
	 * 
	 * @param vo
	 * @return tn_change_lc_myeon 데이터.
	 */
	public List selectGeomLcMyeonInfoEPSG4326(ChangeGeomLcVo vo)
			throws Exception {
		return list("changeInfoDAO.selectGeomLcMyeonInfoEPSG4326", vo);
	}

	/**
	 * tn_change_lc_myeon geom 데이터 조회.
	 * 
	 * @param vo
	 * @return tn_change_lc_myeon 데이터.
	 */
	public List selectGeomLcShpInfoEPSG4326(ChangeGeomLcVo vo)
			throws Exception {
		return list("changeInfoDAO.selectGeomLcShpInfoEPSG4326", vo);
	}	
	
	/**
	 * tn_change_lc_point geom 등록
	 * 
	 * @param vo
	 * @return 등록결과
	 * @throws Exception
	 */
	public String insertGeomLcPointInfo(ChangeGeomLcVo vo) throws Exception {
		return (String) insert("changeInfoDAO.insertGeomLcPointInfo", vo);
	}

	/**
	 * tn_change_lc_ln geom 등록
	 * 
	 * @param vo
	 * @return 등록결과
	 * @throws Exception
	 */
	public String insertGeomLcLnInfo(ChangeGeomLcVo vo) throws Exception {
		return (String) insert("changeInfoDAO.insertGeomLcLnInfo", vo);
	}

	/**
	 * tn_change_lc_myeon geom 등록
	 * 
	 * @param vo
	 * @return 등록결과
	 * @throws Exception
	 */
	public String insertGeomLcMyeonInfo(ChangeGeomLcVo vo) throws Exception {
		return (String) insert("changeInfoDAO.insertGeomLcMyeonInfo", vo);
	}

	/**
	 * tn_change_lc_point geom 데이터 삭제.
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public void deleteGeomLcPointInfo(ChangeGeomLcVo vo) throws Exception {
		delete("changeInfoDAO.deleteGeomLcPointInfo", vo);
	}

	/**
	 * tn_change_lc_ln geom 데이터 삭제.
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public void deleteGeomLcLnInfo(ChangeGeomLcVo vo) throws Exception {
		delete("changeInfoDAO.deleteGeomLcLnInfo", vo);
	}

	/**
	 * tn_change_lc_myeon geom 데이터 삭제.
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public void deleteGeomLcMyeonInfo(ChangeGeomLcVo vo) throws Exception {
		delete("changeInfoDAO.deleteGeomLcMyeonInfo", vo);
	}

	/**
	 * 변경신고 차트 월별 건수
	 */
	public List selectChangeInfoChartSttemntMonth(String year) throws Exception {
		return list("changeInfoDAO.selectChangeInfoChartSttemntMonth", year);
	}

	/**
	 * 변경신고 차트 유형별 건수
	 */
	public List selectChangeInfoChartSttemntChangeTy(String year)
			throws Exception {
		return list("changeInfoDAO.selectChangeInfoChartSttemntChangeTy", year);
	}

	/**
	 * 변경신고 차트 처리상태 건수
	 */
	public List selectChangeInfoChartSttemntProcessSttusSe(String year)
			throws Exception {
		return list("changeInfoDAO.selectChangeInfoChartSttemntProcessSttusSe",
				year);
	}

	/**
	 * 변경신고 차트 년도별 건수
	 */
	public List selectChangeInfoChartSttemntYear() throws Exception {
		return list("changeInfoDAO.selectChangeInfoChartSttemntYear", null);
	}

	/**
	 * 변동신고 차트 월별 건수
	 */
	public List selectChangeInfoChartCntrwkMonth(String year) throws Exception {
		return list("changeInfoDAO.selectChangeInfoChartCntrwkMonth", year);
	}

	/**
	 * 변동신고 차트 유형별 건수
	 */
	public List selectChangeInfoChartCntrwkChangeTy(String year)
			throws Exception {
		return list("changeInfoDAO.selectChangeInfoChartCntrwkChangeTy", year);
	}

	/**
	 * 변동신고 차트 처리상태 건수
	 */
	public List selectChangeInfoChartCntrwkProcessSttusSe(String year)
			throws Exception {
		return list("changeInfoDAO.selectChangeInfoChartCntrwkProcessSttusSe",
				year);
	}

	/**
	 * 변동신고 차트 년도별 건수
	 */
	public List selectChangeInfoChartCntrwkYear() throws Exception {
		return list("changeInfoDAO.selectChangeInfoChartCntrwkYear", null);
	}

	/** 변화정보 차트 월별 건수 */
	public List selectChangeInfoChartFinishMonth(String year) {
		return list("changeInfoDAO.selectChangeInfoChartFinishMonth", year);
	}

	/** 변화정보 차트 유형별 건수 */
	public List selectChangeInfoChartFinishChangeTy(String year) {
		return list("changeInfoDAO.selectChangeInfoChartFinishChangeTy", year);
	}

	/** 변화정보 차트 사업지구별 건수 */
	public List selectChangeInfoChartFinishBsnsDstrc(String year) {
		return list("changeInfoDAO.selectChangeInfoChartFinishBsnsDstrc", year);
	}

	/** 변화정보 차트 년도별 건수 */
	public List selectChangeInfoChartFinishYear() {
		return list("changeInfoDAO.selectChangeInfoChartFinishYear", null);
	}

	/**
	 * 차트 조회 년도 리스트.
	 */
	public List selectChangeInfoChartListYear() {
		return list("changeInfoDAO.selectChangeInfoChartListYear", null);
	}

	/** 변경신고 차트에서 사용. 메인페이지용 */
	public List selectChangeInfoChartCntJson(String area) {
		return list("changeInfoDAO.selectChangeInfoChartCntJson", area);
	}

	/** 사업지구 리스트 */
	public List selectBgnssNmList() {
		return list("changeInfoDAO.selectBgnssNmList", null);
	}	
	
}
