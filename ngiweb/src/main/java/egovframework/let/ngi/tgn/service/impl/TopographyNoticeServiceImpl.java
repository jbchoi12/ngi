package egovframework.let.ngi.tgn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import egovframework.let.ngi.chg.service.ChangeHistVO;
import egovframework.let.ngi.chg.service.ChangeInfoVO;
import egovframework.let.ngi.tgn.service.TopographyNoticeService;
import egovframework.let.ngi.tgn.service.TopographyNoticeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 지형고시 관리를 위한 ServiceImpl 클래스
 * @author kka
 * @since 2014. 11. 26.
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
@Service("topographyNoticeService")
public class TopographyNoticeServiceImpl extends EgovAbstractServiceImpl implements TopographyNoticeService{
	
	@Resource(name="topographyNoticeDAO")
	private TopographyNoticeDAO topographyNoticeDAO;
	
    /**
     * 지형고시 목록을 조회한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @return
     * @throws Exception
     */
    public List<TopographyNoticeVO> selectTopographyNoticeList(TopographyNoticeVO vo) throws Exception {
    	return topographyNoticeDAO.selectTopographyNoticeList(vo);
    }
    
    /**
     * 지형고시 목록 수를 조회한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @return
     */
    public int selectTopographyNoticeListTotCnt(TopographyNoticeVO vo) throws Exception{
    	return topographyNoticeDAO.selectTopographyNoticeListTotCnt(vo);
    }
    
    /**
     * 지형고시 정보를 등록한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @throws Exception
     */
    public void insertTopographyNotice(TopographyNoticeVO vo) throws Exception {

    	topographyNoticeDAO.insertTopographyNotice(vo); //지형고시 정보 등록
    	 
    	String[] ids = vo.getUpdtInfoIds();
		if(ids != null) {
			for(int i=0; i<ids.length; i++) {
				vo.setUpdtInfoId(Integer.parseInt(vo.getUpdtInfoIds()[i]));
				topographyNoticeDAO.insertUpdtInfoMapping(vo); //측량성과 매핑정보 추가
				
			}
		}
    }

    /**
     * 지형고시 정보를 수정한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @throws Exception
     */
    public void updateTopographyNotice(TopographyNoticeVO vo) throws Exception {
    	
    	topographyNoticeDAO.deleteUpdtInfoMapping(vo); //측량성과 매핑정보 삭제
        
        String[] ids = vo.getUpdtInfoIds();
		if(ids != null) {
			for(int i=0; i<ids.length; i++) {
				vo.setUpdtInfoId(Integer.parseInt(vo.getUpdtInfoIds()[i]));
				topographyNoticeDAO.insertUpdtInfoMapping(vo); //측량성과 매핑정보 추가
				
			}
		}
		
        topographyNoticeDAO.updateTopographyNotice(vo); //지형고시 정보 수정
    }

    /**
     * 지형고시 정보를 삭제한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @throws Exception
     */
    public void deleteTopographyNotice(TopographyNoticeVO vo) throws Exception {
    	topographyNoticeDAO.deleteUpdtInfoMapping(vo); //측량성과 매핑정보 삭제
        topographyNoticeDAO.deleteTopographyNotice(vo); //지형고시 정보 삭제
    }

    /**
     * 지형고시 상세정보를 조회한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @return
     * @throws Exception
     */
    public TopographyNoticeVO selectTopographyNotice(TopographyNoticeVO vo) throws Exception {
        TopographyNoticeVO resultVO = topographyNoticeDAO.selectTopographyNotice(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    
    /**
     * 렉스퍼트 브리프의 호수번호를 부여해주기 위해 마지막 번호 찾기
     * @author jbchoi
     * @since 2015. 01. 08.
     * @param 
     * @return json
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject selectBreifSeq() throws Exception {
		
		String briefSeq = topographyNoticeDAO.selectBriefSeq();
		
		JSONObject obj = new JSONObject();
		obj.put("briefSeq", briefSeq);
		
		return obj;
	}

}
