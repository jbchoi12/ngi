package egovframework.let.ngi.tgn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.let.ngi.tgn.service.TopographyNoticeVO;


/**
 * 지형고시 관리를 위한 DAO 클래스
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
@Repository("topographyNoticeDAO")
public class TopographyNoticeDAO extends EgovAbstractDAO{

    /**
     * 지형고시 정보를 등록한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @throws Exception
     */
    public void insertTopographyNotice(TopographyNoticeVO vo) throws Exception {
         insert("topographyNoticeDAO.insertTopographyNotice", vo);
    }

    /**
     * 지형고시 정보를 수정한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @throws Exception
     */
    public void updateTopographyNotice(TopographyNoticeVO vo) throws Exception {
        update("topographyNoticeDAO.updateTopographyNotice", vo);
    }

    /**
     * 지형고시 정보를 삭제한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @throws Exception
     */
    public void deleteTopographyNotice(TopographyNoticeVO vo) throws Exception {
        delete("topographyNoticeDAO.deleteTopographyNotice", vo);
    }

    /**
     * 지형고시 정보를 상세 조회한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @return
     * @throws Exception
     */
    public TopographyNoticeVO selectTopographyNotice(TopographyNoticeVO vo) throws Exception {
        return (TopographyNoticeVO) selectByPk("topographyNoticeDAO.selectTopographyNotice", vo);
    }

    /**
     * 지형고시 목록을 조회한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @return
     * @throws Exception
     */
    public List<TopographyNoticeVO> selectTopographyNoticeList(TopographyNoticeVO vo) throws Exception {
        return (List<TopographyNoticeVO>) list("topographyNoticeDAO.selectTopographyNoticeList", vo);
    }

	/**
	 * 지형고시 목록 수를 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 */
	public int selectTopographyNoticeListTotCnt(TopographyNoticeVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("topographyNoticeDAO.selectTopographyNoticeListTotCnt", vo);
    }
	
	/**
	 * 측량성과 정보를 추가한다.
	 * @author kka
	 * @since 2014. 11. 19.
	 * @param vo
	 * @throws Exception
	 */
	public void insertUpdtInfoMapping(TopographyNoticeVO vo) throws Exception{
		insert("topographyNoticeDAO.insertUpdtInfoMapping", vo);
	}
	
	/**
	 * 측량성과 추가 정보를 삭제한다.
	 * @author kka
	 * @since 2014. 11. 19.
	 * @param vo
	 * @throws Exception
	 */
	public void deleteUpdtInfoMapping(TopographyNoticeVO vo) throws Exception {
        delete("topographyNoticeDAO.deleteUpdtInfoMapping", vo);
    }
	
	 /**
     * 렉스퍼트 브리프의 호수번호를 부여해주기 위해 마지막 번호 찾기
     * @author jbchoi
     * @since 2015. 01. 08.
     * @param 
     * @return json
     * @throws Exception
     */
	public String selectBriefSeq() throws Exception {
		return ((String) getSqlMapClientTemplate().queryForObject("topographyNoticeDAO.selectBriefSeq", ""));
	}
	
}
