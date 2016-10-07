package egovframework.com.cmm.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import egovframework.com.cmm.Config;
import egovframework.com.cmm.service.CodeVO;

import org.apache.commons.lang.StringUtils;


/**
 * 코드관리를 위한 클래스를 정의한다.
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
public class CodeValueTag extends TagSupport {

	private String codeType;
	private String codeLvl="1";
	private String codeCd;
	private String emptyStr="";
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCodeLvl() {
		return codeLvl;
	}
	public void setCodeLvl(String codeLvl) {
		this.codeLvl = codeLvl;
	}
	public String getCodeCd() {
		return codeCd;
	}
	public void setCodeCd(String codeCd) {
		this.codeCd = codeCd;
	}
	public String getEmptyStr() {
		return emptyStr;
	}
	public void setEmptyStr(String emptyStr) {
		this.emptyStr = emptyStr;
	}
	
	
	public int doStartTag() throws JspException {
        try {
            this.pageContext.getOut().println(getCodeNm());            
        }catch(Exception ex) {
            throw new JspException(ex);
        }
        
        return SKIP_BODY;
    }
	
	private String getCodeNm() throws Exception{
		CodeVO code = null;
        List<CodeVO> codeList = Config.CODEGROUP.get(codeType);
        for(CodeVO codeVO : codeList) {
        	if(StringUtils.equals(codeVO.getCode(), codeCd)) {
        		code = codeVO;
        		break;
        	}
        }
        if(code != null){
        	return code.getCodeNm();
        }else{
        	return emptyStr;
        }
   }
	
}
