package egovframework.let.ngi.chg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.let.ngi.chg.service.TnAtchmnflDAO;
import egovframework.let.ngi.chg.service.TnAtchmnflDefaultVO;
import egovframework.let.ngi.chg.service.TnAtchmnflVO;
import egovframework.let.ngi.chg.service.impl.TnAtchmnflService;

/**
 * @Class Name : TnAtchmnflServiceImpl.java
 * @Description : TnAtchmnfl Business Implement class
 * @Modification Information
 *
 * @author a
 * @since a
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("tnAtchmnflService")
public class TnAtchmnflServiceImpl extends AbstractServiceImpl implements
        TnAtchmnflService {

    @Resource(name="tnAtchmnflDAO")
    private TnAtchmnflDAO tnAtchmnflDAO;
    
    /** ID Generation */
    //@Resource(name="{egovTnAtchmnflIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tn_atchmnfl을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TnAtchmnflVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTnAtchmnfl(TnAtchmnflVO vo) throws Exception {
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	
    	tnAtchmnflDAO.insertTnAtchmnfl(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * tn_atchmnfl을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TnAtchmnflVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTnAtchmnfl(TnAtchmnflVO vo) throws Exception {
        tnAtchmnflDAO.updateTnAtchmnfl(vo);
    }

    /**
	 * tn_atchmnfl을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TnAtchmnflVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTnAtchmnfl(TnAtchmnflVO vo) throws Exception {
        tnAtchmnflDAO.deleteTnAtchmnfl(vo);
    }

    public void deleteTnAtchmnflPhone(TnAtchmnflVO vo) throws Exception {
        tnAtchmnflDAO.deleteTnAtchmnflPhone(vo);
    }
    
    /**
	 * tn_atchmnfl을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TnAtchmnflVO
	 * @return 조회한 tn_atchmnfl
	 * @exception Exception
	 */
    public TnAtchmnflVO selectTnAtchmnfl(TnAtchmnflVO vo) throws Exception {
        TnAtchmnflVO resultVO = tnAtchmnflDAO.selectTnAtchmnfl(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * tn_atchmnfl 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_atchmnfl 목록
	 * @exception Exception
	 */
    public List selectTnAtchmnflList(TnAtchmnflDefaultVO searchVO) throws Exception {
        return tnAtchmnflDAO.selectTnAtchmnflList(searchVO);
    }

    public List selectTnAtchmnflListPhone(TnAtchmnflDefaultVO searchVO) throws Exception {
        return tnAtchmnflDAO.selectTnAtchmnflListPhone(searchVO);
    }
    
    /**
	 * tn_atchmnfl 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tn_atchmnfl 총 갯수
	 * @exception
	 */
    public int selectTnAtchmnflListTotCnt(TnAtchmnflDefaultVO searchVO) {
		return tnAtchmnflDAO.selectTnAtchmnflListTotCnt(searchVO);
	}
    
}
