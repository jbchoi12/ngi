package egovframework.com.cmm.service.impl;

import java.util.List;

import egovframework.com.cmm.service.BusinessDistrictVO;
import egovframework.com.cmm.service.CodeVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 코드 관리에 대한 DAO 클래스를 정의한다.
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
@Repository("codeDAO")
public class CodeDAO extends EgovAbstractDAO {

    /**
     * 그룹코드 목록을 조회한다.
     * @author kka
     * @since 2014. 10. 29.
     * @param codeId
     * @return
     * @throws Exception
     */
    public List<CodeVO> selectGroupCodeList() throws Exception {
        return (List<CodeVO>) list("codeDAO.selectGroupCodeList", null);
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
        return (List<CodeVO>) list("codeDAO.selectCodeList", codeId);
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
        return (CodeVO) selectByPk("codeDAO.selectCode", codeVO);
    }
    
    public List<BusinessDistrictVO> selectBusinessDistrictList() throws Exception {
    	 return (List<BusinessDistrictVO>) list("codeDAO.selectBusinessDistrictList", null);
    }
}
