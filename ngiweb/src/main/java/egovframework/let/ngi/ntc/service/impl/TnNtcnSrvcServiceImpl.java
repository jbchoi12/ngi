package egovframework.let.ngi.ntc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.let.ngi.ntc.service.TnNtcnSrvcService;
import egovframework.let.ngi.ntc.service.TnNtcnSrvcDefaultVO;
import egovframework.let.ngi.ntc.service.TnNtcnSrvcVO;
import egovframework.let.ngi.ntc.service.impl.TnNtcnSrvcDAO;

/**
 * @Class Name : TnNtcnSrvcServiceImpl.java
 * @Description : TnNtcnSrvc Business Implement class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("tnNtcnSrvcService")
public class TnNtcnSrvcServiceImpl extends AbstractServiceImpl implements
        TnNtcnSrvcService {

    @Resource(name="tnNtcnSrvcDAO")
    private TnNtcnSrvcDAO tnNtcnSrvcDAO;
    
    /** ID Generation */
    //@Resource(name="{egovTnNtcnSrvcIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tn_ntcn_srvc을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnNtcnSrvcVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTnNtcnSrvc(TnNtcnSrvcVO vo) throws Exception {
    	//log.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	//log.debug(vo.toString());
    	
    	tnNtcnSrvcDAO.insertTnNtcnSrvc(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * tn_ntcn_srvc을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TnNtcnSrvcVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTnNtcnSrvc(TnNtcnSrvcVO vo) throws Exception {
        tnNtcnSrvcDAO.updateTnNtcnSrvc(vo);
    }

    /**
	 * tn_ntcn_srvc을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnNtcnSrvcVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTnNtcnSrvc(TnNtcnSrvcVO vo) throws Exception {
        tnNtcnSrvcDAO.deleteTnNtcnSrvc(vo);
    }

    /**
	 * tn_ntcn_srvc을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TnNtcnSrvcVO
	 * @return 조회한 tn_ntcn_srvc
	 * @exception Exception
	 */
    public TnNtcnSrvcVO selectTnNtcnSrvc(TnNtcnSrvcVO vo) throws Exception {
        TnNtcnSrvcVO resultVO = tnNtcnSrvcDAO.selectTnNtcnSrvc(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * tn_ntcn_srvc 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_ntcn_srvc 목록
	 * @exception Exception
	 */
    public List selectTnNtcnSrvcList(TnNtcnSrvcDefaultVO searchVO) throws Exception {
        return tnNtcnSrvcDAO.selectTnNtcnSrvcList(searchVO);
    }

    /**
	 * tn_ntcn_srvc 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_ntcn_srvc 총 갯수
	 * @exception
	 */
    public int selectTnNtcnSrvcListTotCnt(TnNtcnSrvcDefaultVO searchVO) {
		return tnNtcnSrvcDAO.selectTnNtcnSrvcListTotCnt(searchVO);
	}

    /**
	 * tn_ntcn_srvc 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_ntcn_srvc 총 갯수
	 * @exception
	 */
    public int selectTnNtcnSrvMaxid(TnNtcnSrvcVO vo) {
		return tnNtcnSrvcDAO.selectTnNtcnSrvMaxid(vo);
	}    
    
}
