package egovframework.let.ngi.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.let.ngi.api.service.TnCntcInfoService;
import egovframework.let.ngi.api.service.TnCntcInfoDefaultVO;
import egovframework.let.ngi.api.service.TnCntcInfoVO;
import egovframework.let.ngi.api.service.impl.TnCntcInfoDAO;

/**
 * @Class Name : TnCntcInfoServiceImpl.java
 * @Description : TnCntcInfo Business Implement class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("tnCntcInfoService")
public class TnCntcInfoServiceImpl extends AbstractServiceImpl implements
        TnCntcInfoService {

    @Resource(name="tnCntcInfoDAO")
    private TnCntcInfoDAO tnCntcInfoDAO;
    
    /** ID Generation */
    //@Resource(name="{egovTnCntcInfoIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tn_cntc_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnCntcInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTnCntcInfo(TnCntcInfoVO vo) throws Exception {
    	// log.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	// log.debug(vo.toString());
    	
    	tnCntcInfoDAO.insertTnCntcInfo(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * tn_cntc_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TnCntcInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTnCntcInfo(TnCntcInfoVO vo) throws Exception {
        tnCntcInfoDAO.updateTnCntcInfo(vo);
    }

    /**
	 * tn_cntc_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnCntcInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTnCntcInfo(TnCntcInfoVO vo) throws Exception {
        tnCntcInfoDAO.deleteTnCntcInfo(vo);
    }

    /**
	 * tn_cntc_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TnCntcInfoVO
	 * @return 조회한 tn_cntc_info
	 * @exception Exception
	 */
    public TnCntcInfoVO selectTnCntcInfo(TnCntcInfoVO vo) throws Exception {
        TnCntcInfoVO resultVO = tnCntcInfoDAO.selectTnCntcInfo(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * tn_cntc_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_cntc_info 목록
	 * @exception Exception
	 */
    public List selectTnCntcInfoList(TnCntcInfoDefaultVO searchVO) throws Exception {
        return tnCntcInfoDAO.selectTnCntcInfoList(searchVO);
    }

    /**
	 * tn_cntc_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_cntc_info 총 갯수
	 * @exception
	 */
    public int selectTnCntcInfoListTotCnt(TnCntcInfoDefaultVO searchVO) {
		return tnCntcInfoDAO.selectTnCntcInfoListTotCnt(searchVO);
	}
    
}
