package egovframework.let.ngi.udt.service;

/**
 * 검수이력 관리를 위한 Service 인터페이스
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
public interface AcptncHistService {
	
	/**
	 * 성과의 검수이력을 등록한다.
	 * @author kka
	 * @since 2014. 11. 15.
	 * @param vo
	 * @throws Exception
	 */
	public void insertAcptncHist(AcptncHistVO vo) throws Exception;
	
	/**
	 * 갱신작업의 검수이력을 일괄 검수완료로 등록한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param updateOperaionVO
	 * @throws Exception
	 */
	public void insertAcptncHistMulti(UpdateOperationVO updateOperaionVO) throws Exception;

}
