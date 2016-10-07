package egovframework.let.ngi.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.let.ngi.api.service.impl.TnChangeAllInfoService;
import egovframework.let.ngi.api.service.TnChangeAllInfoDefaultVO;
import egovframework.let.ngi.api.service.TnChangeAllInfoListSearchVO;
import egovframework.let.ngi.api.service.TnChangeAllInfoVO;
import egovframework.let.ngi.api.service.TnChangeDetailInfoVO;
import egovframework.let.ngi.api.service.impl.TnChangeAllInfoDAO;

/**
 * @Class Name : TnChangeAllInfoServiceImpl.java
 * @Description : TnChangeAllInfo Business Implement class
 * @Modification Information
 *
 * @author a
 * @since a
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("tnChangeAllInfoService")
public class TnChangeAllInfoServiceImpl extends EgovAbstractServiceImpl implements
        TnChangeAllInfoService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TnChangeAllInfoServiceImpl.class);

    @Resource(name="tnChangeAllInfoDAO")
    private TnChangeAllInfoDAO tnChangeAllInfoDAO;
    
    /** ID Generation */
    //@Resource(name="{egovTnChangeAllInfoIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tn_change_all_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnChangeAllInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTnChangeAllInfo(TnChangeAllInfoVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	tnChangeAllInfoDAO.insertTnChangeAllInfo(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * tn_change_all_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TnChangeAllInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTnChangeAllInfo(TnChangeAllInfoVO vo) throws Exception {
        tnChangeAllInfoDAO.updateTnChangeAllInfo(vo);
    }

    /**
	 * tn_change_all_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnChangeAllInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTnChangeAllInfo(TnChangeAllInfoVO vo) throws Exception {
        tnChangeAllInfoDAO.deleteTnChangeAllInfo(vo);
    }

    /**
	 * tn_change_all_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TnChangeAllInfoVO
	 * @return 조회한 tn_change_all_info
	 * @exception Exception
	 */
    public TnChangeAllInfoVO selectTnChangeAllInfo(TnChangeAllInfoVO vo) throws Exception {
    	TnChangeAllInfoVO resultVO = tnChangeAllInfoDAO.selectTnChangeAllInfo(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * tn_change_all_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_change_all_info 목록
	 * @exception Exception
	 */
    public List<?> selectTnChangeAllInfoList(TnChangeAllInfoVO searchVO) throws Exception {
        return tnChangeAllInfoDAO.selectTnChangeAllInfoList(searchVO);
    }

    /**
	 * tn_change_all_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_change_all_info 총 갯수
	 * @exception
	 */
    public int selectTnChangeAllInfoListTotCnt(TnChangeAllInfoVO searchVO) {
		return tnChangeAllInfoDAO.selectTnChangeAllInfoListTotCnt(searchVO);
	}

}
