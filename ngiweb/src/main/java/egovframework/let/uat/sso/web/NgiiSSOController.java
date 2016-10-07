package egovframework.let.uat.sso.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import SafeIdentity.SSO;

import com.fasterxml.jackson.databind.ObjectMapper;


import egovframework.com.cmm.LoginVO;
import egovframework.let.ngi.pnt.service.ScoreLogService;
import egovframework.let.ngi.pnt.service.ScoreLogVO;
import egovframework.let.uat.uia.service.EgovLoginService;
import SafeIdentity.*;

import egovframework.let.sym.log.clg.service.EgovLoginLogService;
import egovframework.let.sym.log.clg.service.LoginLog;


/**
 * 
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 8. 26.
 * @version 1.0
 * @see
 *
 * <pre>
 * 개정이력(Modification Information) 
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011. 8. 26.    서준식        최초생성
 *  
 *  </pre>
 */
@Controller
public class NgiiSSOController {

	@Resource(name = "scoreLogService")
	private ScoreLogService scoreLogService; 
	@Resource(name="EgovLoginLogService")
	private EgovLoginLogService loginLogService;
	
	@RequestMapping("/uat/sso/globalLogout.do")
	public void globalLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		String serverIp = "";
    	String clientPort = "";
    	
		//serverIp = InetAddress.getLocalHost().getHostAddress();
		//clientPort = ":" + request.getServerPort();	
    	
    	session.invalidate();
    	//response.sendRedirect("http://192.168.100.222:7070/egovsso/pmi-logout-url.html?pmi-logout-url=http://192.168.100.222:7070/egovsso/pmi-logout.html&returl=" + "http://" + serverIp + clientPort + returnURL);
		response.sendRedirect("http://www.ngii.go.kr/kor/logOut/logOut.do?rbsIdx=4");
		
	}
	
	
	@RequestMapping("/uat/sso/loginPostProcess.do")
	public void loginPostProcess(@RequestParam("token") String token, HttpSession session, HttpServletRequest request, HttpServletResponse response)throws Exception{
	
		String sToken = token;
		String sUid = "";
		String sUserNm = "";
		String sEmail = "";
		String sTel = "";
		String sAddr = "";
		String sDtlAddr = "";
		String sOrganNm = "";
		
		if(!sToken.isEmpty()){
			SSO sso = new SSO();
			sso.setPortNumber(7100);
			int nResult = sso.verifyToken(sToken);
			if(nResult >= 0){
				sUid = sso.getValueUserID();
				sUserNm = sso.getValue("getUserName");
				sEmail = sso.getValue("getEmail");
				sTel = sso.getValue("getTel");
				sAddr = sso.getValue("getAddr");
				sDtlAddr = sso.getValue("getDtlAddr");
				sOrganNm = sso.getValue("getOrganNM");	
				

				session.setAttribute("sUserId", sUid);
				session.setAttribute("sUserNm", sUserNm);
				session.setAttribute("sUserEmail", sEmail);
				session.setAttribute("sUserTel", sTel);
				session.setAttribute("sUserOrganNm", sOrganNm);
			}
			
			// 포인트 적용
			ScoreLogVO svo = new ScoreLogVO();
			svo.setUserId(sUid);
			svo.setScore(5);
			svo.setScoreCodeTy("L");		
			scoreLogService.insertScoreLog(svo);			
	
		}
		

		String returnUrl="../../cmm/main/mainPage.do";
		response.sendRedirect(returnUrl);
	}
	
	@RequestMapping("/uat/sso/loginPostProcess2.do")
	public void loginPostProcess2(@RequestParam("token") String token, HttpSession session, HttpServletRequest request, HttpServletResponse response)throws Exception{
	
		String sToken = token;
		String sUid = "";
		String sUserNm = "";
		String sEmail = "";
		String sTel = "";
		String sAddr = "";
		String sDtlAddr = "";
		String sOrganNm = "";
		
		if(!sToken.isEmpty()){
			SSO sso = new SSO();
			sso.setPortNumber(7100);
			int nResult = sso.verifyToken(sToken);
			if(nResult >= 0){
				sUid = sso.getValueUserID();
				sUserNm = sso.getValue("getUserName");
				sEmail = sso.getValue("getEmail");
				sTel = sso.getValue("getTel");
				sAddr = sso.getValue("getAddr");
				sDtlAddr = sso.getValue("getDtlAddr");
				sOrganNm = sso.getValue("getOrganNM");	
				

				session.setAttribute("sUserId", sUid);
				session.setAttribute("sUserNm", sUserNm);
				session.setAttribute("sUserEmail", sEmail);
				session.setAttribute("sUserTel", sTel);
				session.setAttribute("sUserOrganNm", sOrganNm);
			}
			
			// 포인트 적용
			ScoreLogVO svo = new ScoreLogVO();
			svo.setUserId(sUid);
			svo.setScore(5);
			svo.setScoreCodeTy("L");		
			scoreLogService.insertScoreLog(svo);			
	
		}
		

		String returnUrl="../../ngi/mng/NgiMngView.do?tabNum=1&method=input";
		response.sendRedirect(returnUrl);
	}
	
	@RequestMapping("/uat/sso/loginTestProcess.do")
	public void loginTestProcess(HttpSession session, HttpServletRequest request, HttpServletResponse response)throws Exception{
	
		String sUid = "";
		String sUserNm = "";
		String sEmail = "";
		String sTel = "";
		String sAddr = "";
		String sDtlAddr = "";
		String sOrganNm = "";
		



				sUid = "test";
				sUserNm = "테스트";
				sEmail = "test@test.com";
				sTel = "1";
				sAddr = "2";
				sDtlAddr = "3";
				sOrganNm = "4";	
				

				session.setAttribute("sUserId", sUid);
				session.setAttribute("sUserNm", sUserNm);
				session.setAttribute("sUserEmail", sEmail);
				session.setAttribute("sUserTel", sTel);
				session.setAttribute("sUserOrganNm", sOrganNm);

			
			// 포인트 적용

		String returnUrl="../../cmm/main/mainPage.do";
		response.sendRedirect(returnUrl);
	}	
	
	
	@RequestMapping("/uat/sso/loginPostProcessPhone.do")
	@ResponseBody
	public void loginPostProcessPhone(@RequestParam("token") String token, HttpSession session, HttpServletRequest request, HttpServletResponse response)throws Exception{
	
		String sToken = token;
		String sUid = "";
		String sUserNm = "";
		String sEmail = "";
		String sTel = "";
		String sAddr = "";
		String sDtlAddr = "";
		String sOrganNm = "";
		Map<String, Object> map_d = new HashMap<String, Object>();
		String str_resdata = "";
		if(!sToken.isEmpty()){
			SSO sso = new SSO();
			sso.setPortNumber(7100);
			int nResult = sso.verifyToken(sToken);
			if(nResult >= 0){
				sUid = sso.getValueUserID();
				sUserNm = sso.getValue("getUserName");
				sEmail = sso.getValue("getEmail");
				sTel = sso.getValue("getTel");
				sAddr = sso.getValue("getAddr");
				sDtlAddr = sso.getValue("getDtlAddr");
				sOrganNm = sso.getValue("getOrganNM");
			}
			
			// 포인트 적용
			ScoreLogVO svo = new ScoreLogVO();
			svo.setUserId(sUid);
			svo.setScore(5);
			svo.setScoreCodeTy("L");		
			scoreLogService.insertScoreLog(svo);	
		}
		
		map_d.put("sUid", sUid);
		map_d.put("sUserNm", sUserNm);
		map_d.put("sEmail", sEmail);
		map_d.put("sTel", sTel);
		map_d.put("sAddr", sAddr);
		map_d.put("sDtlAddr", sDtlAddr);
		map_d.put("sOrganNm", sOrganNm);

		ObjectMapper om = new ObjectMapper();
		str_resdata = om.writeValueAsString(map_d);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(str_resdata);
		pw.flush();
	}
	
	@RequestMapping("/uat/sso/logUserLoginFromPhone.do")
	@ResponseBody
	public void logUserLoginFromPhone(@RequestParam("uniqId") String uniqId, HttpSession session, HttpServletRequest request, HttpServletResponse response)throws Exception{
		// QNA 메인 컨텐츠 조회 끝 -----------------------------------
		String ip = request.getRemoteAddr();
        LoginLog loginLog = new LoginLog();
    	loginLog.setLoginId(uniqId);
        loginLog.setLoginIp(ip);
        loginLog.setLoginMthd("M"); // 포탈:P 모바일:M
        loginLog.setErrOccrrAt("N");
        loginLog.setErrorCode("");
        if (loginLogService != null) {
        	loginLogService.logInsertLoginLog(loginLog);  
        }
        
		Map<String, Object> map_d = new HashMap<String, Object>();
		map_d.put("result", "ok");
        ObjectMapper om = new ObjectMapper();
        String str_resdata = om.writeValueAsString(map_d);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(str_resdata);
		pw.flush();
	}
	
}
