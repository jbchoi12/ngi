package egovframework.com.cmm;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import egovframework.com.cmm.service.BusinessDistrictVO;
import egovframework.com.cmm.service.CodeService;
import egovframework.com.cmm.service.CodeVO;

import org.apache.commons.lang.StringUtils;

/**
 * Autowired로 bean을 가져오기 곤란한 static method에서 bean을 사용하기 위한 것
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
@WebListener
public class Config implements ServletContextListener {
	
	public static Map<String, List<CodeVO>> CODEGROUP = new HashMap<String, List<CodeVO>>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("Constants", new JspConstants()); 
        
        CodeService codeService = (CodeService)StaticContextAccessor.getBean("codeService");
        try {
	        List<CodeVO> groupCodeList = codeService.selectGroupCodeList();
	        for(CodeVO codeVO : groupCodeList) {
	        	CODEGROUP.put(codeVO.getCode(), codeService.selectCodeList(codeVO.getCode()));
	        }
	        
	        sce.getServletContext().setAttribute("CodeConstants", CODEGROUP); 
        }catch (Exception e) {
        	e.setStackTrace(null);
        }
        
        try {
	        List<BusinessDistrictVO> businessDistrictList = codeService.selectBusinessDistrictList();
	        
	        sce.getServletContext().setAttribute("BusinessDistrictConstants", businessDistrictList); 
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    	
    }
}

class JspConstants extends HashMap<String, String> {

    public JspConstants() {
        Class<?> c = Constants.class;
        Field[] fields = c.getDeclaredFields();
        for(Field field : fields) {
            int modifier = field.getModifiers();
            if(Modifier.isPublic(modifier) && Modifier.isStatic(modifier) && Modifier.isFinal(modifier)) {
                try {
                    put(field.getName(), (String)field.get(null));
                } catch(IllegalAccessException ignored) {
                	ignored.printStackTrace();
                }
            }
        }
    }

    @Override
    public String get(Object key) {
        String result = super.get(key);
        if(StringUtils.isEmpty(result)) {
            throw new IllegalArgumentException("Check key! The key is wrong, no such constant!");
        }
        return result;
    }
}