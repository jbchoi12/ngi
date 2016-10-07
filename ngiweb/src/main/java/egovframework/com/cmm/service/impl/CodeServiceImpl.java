package egovframework.com.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import egovframework.com.cmm.service.BusinessDistrictVO;
import egovframework.com.cmm.service.CodeService;
import egovframework.com.cmm.service.CodeVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 코드 관리에 대한 ServiceImpl 클래스를 정의한다.
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
@Service("codeService")
public class CodeServiceImpl extends EgovAbstractServiceImpl implements CodeService{

	/** CodeDAO */
	@Resource(name="codeDAO")
	private CodeDAO codeDAO;

	/**
     * 그룹코드 목록을 조회한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param codeId
     * @return
     * @throws Exception
     */
    public List<CodeVO> selectGroupCodeList() throws Exception {
    	return codeDAO.selectGroupCodeList();
    }

	/**
     * 코드 목록을 조회한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param codeId
     * @return
     * @throws Exception
     */
    public List<CodeVO> selectCodeList(String codeId) throws Exception {
    	return codeDAO.selectCodeList(codeId);
    }

    /**
     * 코드를 조회한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param codeVO
     * @return
     * @throws Exception
     */
    public CodeVO selectCode(CodeVO codeVO) throws Exception {
    	return codeDAO.selectCode(codeVO);
    }

	/**
	 * 사업지구 목록을 조회한다.
	 * @author kka
	 * @since 2014. 11. 16.
	 * @return
	 * @throws Exception
	 */
	public List<BusinessDistrictVO> selectBusinessDistrictList() throws Exception {
		return codeDAO.selectBusinessDistrictList();
	}

}
