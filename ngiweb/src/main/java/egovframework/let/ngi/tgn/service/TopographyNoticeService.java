package egovframework.let.ngi.tgn.service;

import java.util.List;

import org.json.simple.JSONObject;

/**
 * 지형고시 관리를 위한 Service 인터페이스
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
public interface TopographyNoticeService {
	
	/**
	 * 지형고시 목록을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<TopographyNoticeVO> selectTopographyNoticeList(TopographyNoticeVO searchVO) throws Exception;
	
	/**
	 * 지형고시 목록 수를 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param searchVO
	 * @return
	 */
	int selectTopographyNoticeListTotCnt(TopographyNoticeVO searchVO) throws Exception;
	
    /**
     * 지형고시 정보를 등록한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @throws Exception
     */
	void insertTopographyNotice(TopographyNoticeVO vo) throws Exception;

    /**
     * 지형고시 정보를 수정한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @throws Exception
     */
	void updateTopographyNotice(TopographyNoticeVO vo) throws Exception;

    /**
     * 지형고시 정보를 삭제한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @throws Exception
     */
	void deleteTopographyNotice(TopographyNoticeVO vo) throws Exception;

    /**
     * 지형고시 상세정보를 조회한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param vo
     * @return
     * @throws Exception
     */
	TopographyNoticeVO selectTopographyNotice(TopographyNoticeVO vo) throws Exception;

	
	/**
     * 렉스퍼트 브리프의 호수번호를 부여해주기 위해 마지막 번호 찾기
     * @author jbchoi
     * @since 2015. 01. 08.
     * @param 
     * @return json
     * @throws Exception
     */
	JSONObject selectBreifSeq() throws Exception;

}
