package egovframework.let.uat.uia.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.let.uat.uia.service.EgovLoginService;
import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
/**
 * 일반 로그인을 처리하는 컨트롤러 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.06  박지욱          최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Controller
public class EgovLoginController {

    /** EgovLoginService */
	@Resource(name = "loginService")
    private EgovLoginService loginService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** TRACE */
    @Resource(name="leaveaTrace")
    LeaveaTrace leaveaTrace;

	/**
	 * 로그인 화면으로 들어간다
	 * @param vo - 로그인후 이동할 URL이 담긴 LoginVO
	 * @return 로그인 페이지
	 * @exception Exception
	 */
    @RequestMapping(value="/uat/uia/egovLoginUsr.do")
	public String loginUsrView(@ModelAttribute("loginVO") LoginVO loginVO,
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model)
			throws Exception {
    	return "uat/uia/EgovLoginUsr";
	}

    /**
	 * 일반(스프링 시큐리티) 로그인을 처리한다
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 로그인결과(세션정보)
	 * @exception Exception
	 */
    @RequestMapping(value="/uat/uia/actionSecurityLogin.do")
    public String actionSecurityLogin(@ModelAttribute("loginVO") LoginVO loginVO,
    		                   HttpServletRequest request, HttpServletResponse response,
    		                   ModelMap model)
            throws Exception {

    	// 1. 일반 로그인 처리
        LoginVO resultVO = loginService.actionLogin(loginVO);

        boolean loginPolicyYn = true;

        if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("") && loginPolicyYn) {

            // 2. spring security 연동
        	request.getSession().setAttribute("LoginVO", resultVO);

        	UsernamePasswordAuthenticationFilter springSecurity = null;

        	ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
        	@SuppressWarnings("rawtypes")
        	Map beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);
        	if (beans.size() > 0) {
        		springSecurity = (UsernamePasswordAuthenticationFilter)beans.values().toArray()[0];
        	} else {
        		throw new IllegalStateException("No AuthenticationProcessingFilter");
        	}

        	springSecurity.setContinueChainBeforeSuccessfulAuthentication(false);	// false 이면 chain 처리 되지 않음.. (filter가 아닌 경우 false로...)

        	springSecurity.doFilter(new RequestWrapperForSecurity(request, resultVO.getUserSe() + resultVO.getId() , resultVO.getUniqId()), response, null);

        	return "forward:/cmm/main/mainPage.do";	// 성공 시 페이지.. (redirect 불가)


        } else {

        	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
        }
    }

    @RequestMapping(value="/uat/uia/actionSecurityLoginPhone.do")
    @ResponseBody
    public void actionSecurityLoginPhone(@ModelAttribute("loginVO") LoginVO loginVO,
    		                   HttpServletRequest request, HttpServletResponse response,
    		                   ModelMap model)
            throws Exception {

    	// 1. 일반 로그인 처리
        LoginVO resultVO = loginService.actionLogin(loginVO);
        String str_resdata = "";
  		String str_error = "error";
        boolean loginPolicyYn = true;
        Map<String, Object> map_d = new HashMap<String, Object>();
        
        if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("") && loginPolicyYn) {

  			map_d.put("data",resultVO);
  			
  	    	ObjectMapper om = new ObjectMapper();
  	    	str_resdata = om.writeValueAsString(map_d);

        } else {


  	    	map_d.put(str_error, egovMessageSource.getMessage("fail.common.login"));
  	    	ObjectMapper om = new ObjectMapper();
  	    	str_resdata = om.writeValueAsString(map_d);
        }
        
  		response.setContentType("text/html; charset=utf-8");
  		PrintWriter pw = response.getWriter();
  		pw.print(str_resdata);
  		pw.flush();
    }
    
    /**
	 * 로그인 후 메인화면으로 들어간다
	 * @param
	 * @return 로그인 페이지
	 * @exception Exception
	 */
    @RequestMapping(value="/uat/uia/actionMain.do")
	public String actionMain(ModelMap model)
			throws Exception {
    	// 1. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		// 2. 메인 페이지 이동
    	return "forward:/cmm/main/mainPage.do";

	}

    /**
	 * 로그아웃한다.
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/uat/uia/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model)
			throws Exception {
    	try{
    	    request.getSession().setAttribute("LoginVO", null);
		} catch (Exception e) {
			// 1. Security 연동
			leaveaTrace.trace("fail.common.msg", this.getClass());
		}
    	return "redirect:/j_spring_security_logout";
    }
}

class RequestWrapperForSecurity extends HttpServletRequestWrapper {
	private String usrname = null;
	private String pssword = null;

	public RequestWrapperForSecurity(HttpServletRequest request, String username, String pssword) {
		super(request);

		this.usrname = username;
		this.pssword = pssword;
	}

	@Override
	public String getRequestURI() {
		return ((HttpServletRequest)super.getRequest()).getContextPath() + "/j_spring_security_check";
	}

	@Override
	public String getParameter(String name) {
        if (name.equals("j_username")) {
        	return usrname;
        }

        if (name.equals("j_password")) {
        	return pssword;
        }

        return super.getParameter(name);
    }
}