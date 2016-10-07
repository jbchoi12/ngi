package egovframework.let.uat.sso.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.let.uat.sso.service.NgiiSSOService;


/**
 * 
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 8. 29.
 * @version 1.0
 * @see
 *
 * <pre>
 * 개정이력(Modification Information) 
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011. 8. 29.    서준식        최초생성
 *  
 *  </pre>
 */

public class NgiiSSOLogoutFilter implements Filter{
	private FilterConfig config;
	
	protected final static Log LOG = LogFactory.getLog(NgiiSSOLogoutFilter.class);

	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		NgiiSSOService ngiiSSOService = (NgiiSSOService) act.getBean("ngiiSSOService");
		
		String returnURL = config.getInitParameter("returnURL");

		((HttpServletRequest)request).getSession().setAttribute("loginVO", null);
		ngiiSSOService.ssoLogout(request, response, ((HttpServletRequest)request).getContextPath() + returnURL);
		
		
		
	
			
	}

	public void init(FilterConfig filterConfig) throws ServletException {

		this.config = filterConfig;

		
	}
}
