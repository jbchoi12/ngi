package egovframework.let.uat.sso.service.impl;

import java.io.IOException;
import java.net.InetAddress;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import SafeIdentity.*;
import egovframework.com.cmm.LoginVO;
import egovframework.let.uat.sso.service.NgiiSSOService;
import egovframework.let.uat.sso.service.impl.NgiiSSODAO;
import egovframework.let.uat.uia.service.EgovLoginService;
import egovframework.let.uss.umt.service.impl.MberManageDAO;
import egovframework.let.utl.sim.service.EgovClntInfo;


/**
 * 
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 8. 25.
 * @version 1.0
 * @see
 *
 * <pre>
 * 개정이력(Modification Information) 
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011. 8. 25.    서준식        최초생성
 *  
 *  </pre>
 */
@Service(value="egovSSOService")
public class NgiiSSOServiceImpl implements NgiiSSOService {
	
	/** EgovLoginService */
	@Resource(name = "loginService")
    private EgovLoginService loginService;

	
//	private SSOService ssoService = SSOService.getInstance();

	/**
	 * SSO 통합 인증 서버에 인증여부를 확인 하는 메서드
	 * 
	 */
	public boolean hasTokenInSSOServer(ServletRequest request,
			ServletResponse response) {
//		SSORspData rspData = ssoService.ssoGetLoginData((HttpServletRequest)request);
		SSO sso = new SSO();
		
		String sToken = request.getParameter("token");
		String uid = sso.getValueUserID();
		
		if(uid == null || uid.equals("")){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * SSO 통합 인증 서버에 인증 토큰 생성을 요청하는 메서드
	 * 
	 */
	public void requestIssueToken(ServletRequest request, ServletResponse response) throws Exception {
		
		String serverIp = "";
    	String userIp = "";
    	String rtrURL = "";
    	String clientPort = "";
    	
		serverIp = InetAddress.getLocalHost().getHostAddress();
		userIp = EgovClntInfo.getClntIP((HttpServletRequest)request);
		clientPort = ":" + request.getServerPort();	
    	rtrURL = ((HttpServletRequest)request).getRequestURI();
    	
    	
    	LoginVO loginVO = (LoginVO)((HttpServletRequest)request).getSession().getAttribute("loginVO");	
	
		//ssoService.ssoReqIssueToken((HttpServletRequest)request, // 서블릿 요청 객체
		//	    (HttpServletResponse)response,	// 서블릿 응답 객체
        //        "form-based",			// 인증 방법
        //        loginVO.getUniqId(),				// 유니크아이디
        //        loginVO.getUserSe(),			// 아이디 식별정보
        //        "",		// SSN
        //        "http://" + serverIp + clientPort + rtrURL, // return url
        //        userIp,		// client ip
        //        serverIp);			// agent ip
	}

	/**
	 * SSO 통합 인증 서버에 인증이 된 경우 인증 서버의 토큰을 활용하여 로컬 로그인을 처리하는 메서드
	 * 
	 */
	public void ssoLoginByServer(ServletRequest request,
			ServletResponse response) throws Exception {
		//SSORspData rspData = ssoService.ssoGetLoginData((HttpServletRequest)request);
		
		LoginVO loginVO = getLoginVO(request, response);
	
		//로컬 로그인 작성
		loginVO = loginService.actionLoginByEsntlId(loginVO);
		
		
		//((HttpServletRequest)request).getSession().setAttribute("uid", rspData.getUID());
		
		//스프링 시큐리티 로그인
		((HttpServletResponse)response).sendRedirect("/j_spring_security_check?j_username=" + loginVO.getUserSe() + loginVO.getId() + "&j_password=" + loginVO.getUniqId());
		//((HttpServletRequest)request).getRequestDispatcher("/j_spring_security_check?j_username=" + loginVO.getUserSe() + loginVO.getId() + "&j_password=" + loginVO.getUniqId()).forward(request, response);
		

	}
	
	
	/**
	 * 토큰 정보를 바탕으로  loginVO 객체를 생성하는 메서드
	 * 
	 */
	public LoginVO getLoginVO(ServletRequest request, ServletResponse response){
		//SSORspData rspData = ssoService.ssoGetLoginData((HttpServletRequest)request);
		
		LoginVO loginVO = new LoginVO();
		//loginVO.setUniqId(rspData.getUID());
		//loginVO.setUserSe(rspData.getCN());
		
		return  loginVO;
	}
	
	
//	public int selectUserId(String id){
//		
//		int result = ngiiSSODAO.selectUserId(id);
//		return result;
//	}
	/**
	 * SSO 인증 정보를 삭제하는 Global Logout을 처리한다.
	 * returnURL : Global Logout후 돌아가는 URL주소
	 * @throws IOException 
	 * 
	 */
	public void ssoLogout(ServletRequest request, ServletResponse response, String returnURL) throws IOException{
		((HttpServletResponse)response).sendRedirect("/exam/sso/globalLogout.do?returnURL=" + returnURL);
	}
	
	

}
