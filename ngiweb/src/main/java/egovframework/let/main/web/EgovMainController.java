package egovframework.let.main.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.let.cop.bbs.service.BoardVO;
import egovframework.let.cop.bbs.service.EgovBBSManageService;
import egovframework.let.ngi.chg.service.ChangeInfoService;
import egovframework.let.ngi.chg.service.ChangeInfoDefaultVO;
import egovframework.let.sym.log.clg.service.EgovLoginLogService;
import egovframework.let.sym.log.clg.service.LoginLog;
import egovframework.let.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.let.sym.mnu.mpm.service.MenuManageVO;
import egovframework.let.uss.olh.faq.service.EgovFaqManageService;
import egovframework.let.uss.olh.faq.service.FaqManageDefaultVO;
import egovframework.let.uss.olh.qna.service.EgovQnaManageService;
import egovframework.let.uss.olh.qna.service.QnaManageDefaultVO;
import egovframework.let.uss.olp.qri.service.EgovQustnrRespondInfoService;
import egovframework.let.ngi.pnt.service.ScoreLogService;
import egovframework.let.ngi.pnt.service.ScoreLogDefaultVO;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.bind.annotation.CommandMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 템플릿 메인 페이지 컨트롤러 클래스(Sample 소스)
 * @author 실행환경 개발팀 JJY
 * @since 2011.08.31
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.08.31  JJY            최초 생성
 *
 * </pre>
 */
@Controller@SessionAttributes(types = ComDefaultVO.class)
public class EgovMainController {

	/**
	 * EgovBBSManageService
	 */
	@Resource(name = "EgovBBSManageService")
    private EgovBBSManageService bbsMngService;

	/** EgovMenuManageService */
	@Resource(name = "meunManageService")
    private EgovMenuManageService menuManageService;

	/** QnaManageService */
	@Resource(name = "QnaManageService")
    private EgovQnaManageService qnaManageService;	
	
	/** FaqManageService */
	@Resource(name = "FaqManageService")
    private EgovFaqManageService faqManageService;

	/** egovQustnrRespondInfoService */
	@Resource(name = "egovQustnrRespondInfoService")
	private EgovQustnrRespondInfoService egovQustnrRespondInfoService;

	/** changeInfoService */
    @Resource(name = "changeInfoService")
    private ChangeInfoService changeInfoService;	
	
	@Resource(name="EgovLoginLogService")
	private EgovLoginLogService loginLogService;

	@Resource(name="scoreLogService")
	private ScoreLogService scoreLogService;	
	
	/**
	 * 메인 페이지에서 각 업무 화면으로 연계하는 기능을 제공한다.
	 *
	 * @param request
	 * @param commandMap
	 * @exception Exception Exception
	 */
	@RequestMapping(value = "/cmm/forwardPage.do")
	public String forwardPageWithMenuNo(HttpServletRequest request, @CommandMap Map<String, Object> commandMap)
	  throws Exception{
		return "";
	}

	/**
	 * 템플릿 메인 페이지 조회
	 * @return 메인페이지 정보 Map [key : 항목명]
	 *
	 * @param request
	 * @param model
	 * @exception Exception Exception
	 */
	@RequestMapping(value = "/cmm/main/mainPage.do")
	public String getMgtMainPage(HttpServletRequest request, ModelMap model)
	  throws Exception{

		// 공지사항 메인 컨텐츠 조회 시작 ---------------------------------
		BoardVO boardVO = new BoardVO();
		boardVO.setPageUnit(5);
		boardVO.setPageSize(10);
		boardVO.setBbsId("BBSMSTR_AAAAAAAAAAAA");

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = bbsMngService.selectBoardArticles(boardVO, "BBSA02");
		model.addAttribute("notiList", map.get("resultList"));


		// 공지사항 메인컨텐츠 조회 끝 -----------------------------------

		// 신고 메인 컨텐츠 조회 시작 ---------------------------------
		
		ChangeInfoDefaultVO sttemntVO = new ChangeInfoDefaultVO();
		sttemntVO.setPageUnit(5);
		sttemntVO.setPageSize(10);

    	

		paginationInfo.setCurrentPageNo(sttemntVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(sttemntVO.getPageUnit());
		paginationInfo.setPageSize(sttemntVO.getPageSize());    	
    	
		sttemntVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sttemntVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sttemntVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		sttemntVO.setChangeCl("01");
        List tnChangeInfoList = changeInfoService.selectChangeInfoList(sttemntVO);
        model.addAttribute("sttemntList", tnChangeInfoList);    	

		// 신고 메인컨텐츠 조회 끝 -----------------------------------

		// QNA 메인 컨텐츠 조회 시작 ---------------------------------
		/** EgovPropertyService.QnaList */
		QnaManageDefaultVO searchVO = new QnaManageDefaultVO();
		searchVO.setPageUnit(5);
    	searchVO.setPageSize(10);

    	/** pageing */
    	paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        model.addAttribute("qnaList", qnaManageService.selectQnaList(searchVO));

		// QNA 메인 컨텐츠 조회 끝 -----------------------------------
        
        ScoreLogDefaultVO scoreVO = new ScoreLogDefaultVO();
        model.addAttribute("scoreList", scoreLogService.selectScoreLogList(scoreVO));        
        
		String uniqId = "";
		String ip = "";
		ip = request.getRemoteAddr();
		/* Authenticated  */
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(isAuthenticated.booleanValue()) {
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			uniqId = user.getUniqId();
    	}

        LoginLog loginLog = new LoginLog();
    	loginLog.setLoginId(uniqId);
        loginLog.setLoginIp(ip);
        loginLog.setLoginMthd("P"); // 포탈:P 모바일:M
        loginLog.setErrOccrrAt("N");
        loginLog.setErrorCode("");
        loginLogService.logInsertLoginLog(loginLog);        
        

		return "main/NgiMainView";
	}

	/**
     * Head메뉴를 조회한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "main_headG", "main_head"
     * @exception Exception
     */
    @RequestMapping(value="/sym/mms/NgiMainMenuHead.do")
    public String selectMainMenuHead(
    		@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    		@RequestParam(value="flag", required=false) String flag,
    		ModelMap model)
            throws Exception {

    	LoginVO user =
    		EgovUserDetailsHelper.isAuthenticated()? (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser():null;
    	if(EgovUserDetailsHelper.isAuthenticated() && user!=null){
    		menuManageVO.setTmp_Id(user.getId());
        	menuManageVO.setTmp_Password(user.getPssword());
        	menuManageVO.setTmp_UserSe(user.getUserSe());
        	menuManageVO.setTmp_Name(user.getName());
        	menuManageVO.setTmp_Email(user.getEmail());
        	menuManageVO.setTmp_OrgnztId(user.getOrgnztId());
        	menuManageVO.setTmp_UniqId(user.getUniqId());
    		model.addAttribute("list_headmenu", menuManageService.selectMainMenuHead(menuManageVO));
    		model.addAttribute("list_menulist", menuManageService.selectMainMenuLeft(menuManageVO));
    	}else{
    		menuManageVO.setAuthorCode("ROLE_ANONYMOUS");
    		model.addAttribute("list_headmenu", menuManageService.selectMainMenuHeadByAuthor(menuManageVO));
    		model.addAttribute("list_menulist", menuManageService.selectMainMenuLeftByAuthor(menuManageVO));
    	}

    	if(flag==null){
    		return "main/inc/NgiIncSubHeader"; // 업무화면의 상단메뉴 화면
    	}else if(flag.equals("MAIN")){
    		return "main/inc/NgiIncHeader"; // 메인화면의 상단메뉴 화면
    	}else if(flag.equals("MAP")){
    		return "main/inc/NgiIncMapHeader"; // 메인화면의 상단메뉴 화면
    	}else{
    		return "main/inc/NgiIncSubHeader"; // 업무화면의 상단메뉴 화면
    	}
    }


    /**
     * 좌측메뉴를 조회한다.
     * @param menuManageVO MenuManageVO
     * @param vStartP      String
     * @return 출력페이지정보 "main_left"
     * @exception Exception
     */
    @RequestMapping(value="/sym/mms/NgiMainMenuLeft.do")
    public String selectMainMenuLeft(
    		ModelMap model)
            throws Exception {

    	LoginVO user =
    		EgovUserDetailsHelper.isAuthenticated()? (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser():null;

    	//LoginVO user =
			//(LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	if(EgovUserDetailsHelper.isAuthenticated()){
    		//인증된 경우 처리할 사항 추가 ...
    		model.addAttribute("lastLogoutDateTime", "로그아웃 타임: 2011-11-10 11:30");
    		//최근 로그아웃 시간 등에 대한 확보 후 메인 컨텐츠로 활용
    	}

      	return "main/inc/NgiIncLeftmenu";
    }

    
    @RequestMapping(value="/main/NgiNotification.do")
    public String ngiNotification(
    		ModelMap model)
            throws Exception {
    	
      	return "main/NgiNotification";
    } 
    
    @RequestMapping(value="/main/NgiPrivacyInfo.do")
    public String ngiPrivacyInfo(
    		ModelMap model)
            throws Exception {
    	
      	return "main/NgiPrivacyInfo";
    }    
    
    @RequestMapping(value="/main/NgiServiceTerms.do")
    public String ngiServiceTerms(
    		ModelMap model)
            throws Exception {
    	
      	return "main/NgiServiceTerms";
    } 
    
}