package egovframework.com.cmm.service;

import java.util.List;

/**
 * 코드 관리에 대한 Service 인터페이스를 정의한다.
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
public interface CodeService {
	
	/**
	 * 그룹코드목록을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param codeId
	 * @return
	 * @throws Exception
	 */
	List<CodeVO> selectGroupCodeList() throws Exception;
	
	/**
	 * 코드목록을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param codeId
	 * @return
	 * @throws Exception
	 */
	List<CodeVO> selectCodeList(String codeId) throws Exception;

	/**
	 * 코드를 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param codeVO
	 * @return
	 * @throws Exception
	 */
	CodeVO selectCode(CodeVO codeVO) throws Exception;
	
	/**
	 * 사업지구 목록을 조회한다.
	 * @author kka
	 * @since 2014. 11. 16.
	 * @param codeId
	 * @return
	 * @throws Exception
	 */
	List<BusinessDistrictVO> selectBusinessDistrictList() throws Exception;
	
}

