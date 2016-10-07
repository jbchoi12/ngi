package egovframework.let.ngi.chg.service;

import java.util.List;

import egovframework.let.ngi.chg.service.ChangeInfoDefaultVO;
import egovframework.let.ngi.chg.service.ChangeInfoVO;

/**
 * @Class Name : TnChangeInfoService.java
 * @Description : TnChangeInfo Business class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ChangeInfoService {
	
	/**
	 * tn_change_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnChangeInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertChangeInfo(ChangeInfoVO vo) throws Exception;

    String insertChangeInfoStaff(ChangeInfoVO vo) throws Exception;
	String insertChangeInfoPhone(ChangeInfoVO vo) throws Exception;

	
	String insertChangeHistory(ChangeHistVO vo) throws Exception;
	/**
	 * tn_change_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ChangeInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertSttemntInfo(SttemntInfoVO vo) throws Exception;    
    
    /**
	 * tn_change_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ChangeInfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateChangeInfo(ChangeInfoVO vo) throws Exception;
    
    /**
	 * tn_sttemnt_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SttemntInfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateSttemntInfo(SttemntInfoVO vo) throws Exception;    
    
    /**
	 * tn_change_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnChangeInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteChangeInfo(ChangeInfoVO vo) throws Exception;
    
    /**
	 * tn_change_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ChangeInfoVO
	 * @return 조회한 tn_change_info
	 * @exception Exception
	 */
    ChangeInfoVO selectChangeInfo(ChangeInfoVO vo) throws Exception;

    /**
	 * tn_sttemnt_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SttemntInfoVO
	 * @return 조회한 tn_sttemnt_info
	 * @exception Exception
	 */
    SttemntInfoVO selectSttemntInfo(SttemntInfoVO vo) throws Exception;

    /**
	 * tn_change_info, tn_sttemnt_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ChangeInfoVO
	 * @return 조회한 tn_change_info
	 * @exception Exception
	 */    
    
    ChangeSttemntInfoVO selectChangeSttemntInfo(ChangeSttemntInfoVO vo) throws Exception;


    /**
	 * tn_change_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_change_info 목록
	 * @exception Exception
	 */
    List selectChangeInfoList(ChangeInfoDefaultVO searchVO) throws Exception;

    /**
	 * tn_change_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_change_info 목록
	 * @exception Exception
	 */
    List selectChangeCntrwkList(ChangeInfoDefaultVO searchVO) throws Exception;    

    List selectChangeCntrwkRegList(ChangeInfoDefaultVO searchVO) throws Exception; 
    
    List selectChangeInfoInRectList(ChangeInfoRadiusSearchingVO searchVO) throws Exception;
    
    List selectChangeInfoTrsInRectList(ChangeInfoRadiusSearchingVO searchVO) throws Exception;
    
    List selectChangeInfoPhoneList(ChangeInfoDefaultVO searchVO) throws Exception;
    
    List selectChangeInfoTrsPhoneList(ChangeInfoDefaultVO searchVO) throws Exception;
    
	List selectChangeCntrwkListPhone(ChangeInfoDefaultVO searchVO) throws Exception;
    /**
	 * tn_change_hist 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_change_hist 목록
	 * @exception Exception
	 */
    List selectChangeHistList(ChangeHistVO vo) throws Exception;    
    
    /**
	 * tn_change_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_change_info 총 갯수
	 * @exception
	 */
    int selectChangeInfoListTotCnt(ChangeInfoDefaultVO searchVO) throws Exception;    

    /**
	 * tn_change_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_change_info 총 갯수
	 * @exception
	 */
    int selectChangeCntrwkListTotCnt(ChangeInfoDefaultVO searchVO) throws Exception;     
    
	int selectChangeCntrwkListTotCntPhone(ChangeInfoDefaultVO searchVO);
    int selectChangeCntrwkRegListTotCnt(ChangeInfoDefaultVO searchVO) throws Exception;    
    
    int selectChangeInfoListInRectTotCnt(ChangeInfoRadiusSearchingVO searchVO) throws Exception;
    int selectChangeInfoTrsListInRectTotCnt(ChangeInfoRadiusSearchingVO searchVO) throws Exception;
    
    int selectChangeInfoListTotCntPhone(ChangeInfoDefaultVO searchVO) throws Exception;
    int selectChangeInfoTrsListTotCntPhone(ChangeInfoDefaultVO searchVO) throws Exception;
    
    /**
	 * tn_change_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_change_info 총 갯수
	 * @exception
	 */
    int selectChangeInfoMaxid(ChangeInfoVO vo) throws Exception;        

    
    
    
   	// 변동보고 시작
    
    
    
    /**
	 * tn_sttemnt_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SttemntInfoVO
	 * @return 조회한 tn_sttemnt_info
	 * @exception Exception
	 */
    CntrwkInfoVO selectCntrwkInfo(CntrwkInfoVO vo) throws Exception;

    /**
	 * tn_change_info, tn_sttemnt_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ChangeInfoVO
	 * @return 조회한 tn_change_info
	 * @exception Exception
	 */    
    
    ChangeCntrwkInfoVO selectChangeCntrwkInfo(ChangeCntrwkInfoVO vo) throws Exception;    

	/**
	 * tn_change_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ChangeInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertCntrwkInfo(CntrwkInfoVO vo) throws Exception; 
    
    /**
	 * tn_sttemnt_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SttemntInfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateCntrwkInfo(CntrwkInfoVO vo) throws Exception;      
    
    void updateCntrwkRegComInfo(String selChangeInfoIds) throws Exception; 
    
    // 변동보고 끝

    // 변화정보 시작   	
    /**
	 * tn_change_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_change_info 목록
	 * @exception Exception
	 */
    List selectChangeTrsInfoList(ChangeInfoDefaultVO searchVO) throws Exception;   
    
    /**
	 * tn_change_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_change_info 총 갯수
	 * @exception
	 */
    int selectChangeTrsInfoListTotCnt(ChangeInfoDefaultVO searchVO) throws Exception;        
    
    
    /**
	 * tn_change_info, tn_sttemnt_info, tn_cntrwk_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ChangeSttemntCntrwkVO
	 * @return 조회한 tn_change_info
	 * @exception Exception
	 */    
    
    ChangeSttemntCntrwkVO selectChangeSttemntCntrwk(ChangeSttemntCntrwkVO vo) throws Exception;     
    
    // 변화정보 끝
    
    
    /**
     * tn_change_lc_point 데이터 입력
     * @param vo
     * @return
     */
    String insertGeomLcPointInfo(ChangeGeomLcVo vo) throws Exception;
    
    /**
     * tn_change_lc_ln 데이터 입력
     * @param vo
     * @return
     */
    String insertGeomLcLnInfo(ChangeGeomLcVo vo) throws Exception;
    
    /**
     * tn_change_lc_myeon 데이터 입력
     * @param vo
     */
    String insertGeomLcMyeonInfo(ChangeGeomLcVo vo) throws Exception;
    
    /**
     * tn_change_lc_point 데이터 조회.
     * @param vo
     */
    List selectGeomLcPointInfo(ChangeGeomLcVo vo) throws Exception;
    List selectGeomLcPointInfoEPSG4326(ChangeGeomLcVo vo) throws Exception;
    
    /**
     * tn_change_lc_ln 데이터 조회.
     * @param vo
     */
    List selectGeomLcLnInfo(ChangeGeomLcVo vo) throws Exception;
    List selectGeomLcLnInfoEPSG4326(ChangeGeomLcVo vo) throws Exception;
    
    /**
     * tn_change_lc_myeon 데이터 조회.
     * @param vo
     */
    List selectGeomLcMyeonInfo(ChangeGeomLcVo vo) throws Exception;
    List selectGeomLcMyeonInfoEPSG4326(ChangeGeomLcVo vo) throws Exception;
    
    
    /**
     * tn_change_lc_shp 데이터 조회.
     * @param vo
     */
    List selectGeomLcShpInfo(ChangeGeomLcVo vo) throws Exception;
    List selectGeomLcShpInfoEPSG4326(ChangeGeomLcVo vo) throws Exception;    
    
    /**
     * tn_change_lc_point 데이터 삭제.
     * @param vo
     */
    void deleteGeomLcPointInfo(ChangeGeomLcVo vo) throws Exception;
    
    /**
     * tn_change_lc_ln 데이터 삭제.
     * @param vo
     */
    void deleteGeomLcLnInfo(ChangeGeomLcVo vo) throws Exception;
    
    /**
     * tn_change_lc_myeon 데이터 삭제.
     * @param vo
     */
    void deleteGeomLcMyeonInfo(ChangeGeomLcVo vo) throws Exception;

	/**
	 * 변경신고 차트 월별 건수
	 * @param year
	 */
	List selectChangeInfoChartSttemntMonth(String year) throws Exception;
	
	/**
	 * 변경신고 차트 유형별 건수
	 * @param year
	 */
	List selectChangeInfoChartSttemntChangeTy(String year) throws Exception;
	
	/**
	 * 변경신고 차트 처리상태 건수
	 * @param year
	 */
	List selectChangeInfoChartSttemntProcessSttusSe(String year) throws Exception;
	
	/**
	 * 변경신고 차트 년도별 건수
	 */
	List selectChangeInfoChartSttemntYear() throws Exception;
	
	/**
	 * 변동신고 차트 월별 건수
	 * @param vo
	 */
	List selectChangeInfoChartCntrwkMonth(String year) throws Exception;
	
	/**
	 * 변동신고 차트 유형별 건수
	 * @param year
	 */
	List selectChangeInfoChartCntrwkChangeTy(String year) throws Exception;
	
	/**
	 * 변동신고 차트 처리상태 건수
	 * @param year
	 */
	List selectChangeInfoChartCntrwkProcessSttusSe(String year) throws Exception;
	
	/**
	 * 변동신고 차트 년도별 건수
	 */
	List selectChangeInfoChartCntrwkYear() throws Exception;
    
	/** 변화정보 차트 월별 건수 */
	List<ChangeInfoChartVO> selectChangeInfoChartFinishMonth(String year) throws Exception;
	
	/** 변화정보 차트 유형별 건수 */
	List<ChangeInfoChartVO> selectChangeInfoChartFinishChangeTy(String year) throws Exception;
	
	/** 변화정보 차트 사업지구별 건수 */
	List<ChangeInfoChartVO> selectChangeInfoChartFinishBsnsDstrc(String year) throws Exception;
	
	/** 변화정보 차트 년도별 건수 */
	List<ChangeInfoChartVO> selectChangeInfoChartFinishYear() throws Exception;

	/** 차트 조회 년도 리스트. */
	List selectChangeInfoChartListYear() throws Exception;

	/** 변경신고 차트에서 사용. 메인페이지용 */
	List selectChangeInfoChartCntJson(String area) throws Exception;
	
	/** 사업지구 리스트  */
	List selectBgnssNmList() throws Exception;	
	
}
