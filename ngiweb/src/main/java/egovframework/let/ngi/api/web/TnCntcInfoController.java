package egovframework.let.ngi.api.web;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.OpenApiKeygen;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.let.ngi.api.service.TnChangeAllInfoListResultVO;
import egovframework.let.ngi.api.service.TnChangeAllInfoPageVO;
import egovframework.let.ngi.api.service.TnChangeAllInfoVO;
import egovframework.let.ngi.api.service.TnChangeDetailInfoVO;
import egovframework.let.ngi.api.service.TnCntcInfoDefaultVO;
import egovframework.let.ngi.api.service.TnCntcInfoService;
import egovframework.let.ngi.api.service.TnCntcInfoVO;
import egovframework.let.ngi.api.service.impl.TnChangeAllInfoService;
import egovframework.let.ngi.chg.service.ChangeGeomLcVo;
import egovframework.let.ngi.chg.service.ChangeInfoService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : TnCntcInfoController.java
 * @Description : TnCntcInfo Controller class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
//@SessionAttributes(types=TnCntcInfoVO.class)
public class TnCntcInfoController {

    @Resource(name = "tnCntcInfoService")
    private TnCntcInfoService tnCntcInfoService;
    
    @Resource(name = "tnChangeAllInfoService")
    private TnChangeAllInfoService tnChangeAllInfoService;
    
    @Resource(name = "changeInfoService")
    private ChangeInfoService changeInfoService;
    
    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;    
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tn_cntc_info 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TnCntcInfoDefaultVO
	 * @return "/tnCntcInfo/TnCntcInfoList"
	 * @exception Exception
	 */
    @RequestMapping(value="/ngi/api/TnCntcInfoList.do")
    public String selectTnCntcInfoList(@ModelAttribute("searchVO") TnCntcInfoDefaultVO searchVO, 
    		ModelMap model)
            throws Exception {
    	
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List tnCntcInfoList = tnCntcInfoService.selectTnCntcInfoList(searchVO);
        model.addAttribute("resultList", tnCntcInfoList);
        
        int totCnt = tnCntcInfoService.selectTnCntcInfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/ngi/api/TnCntcInfoList";
    } 
    
    @RequestMapping("/ngi/api/tnCntcInfoView.do")
    public String tnCntcInfoView(
            @ModelAttribute("searchVO") TnCntcInfoDefaultVO searchVO,
            @ModelAttribute("loginVO") LoginVO loginVO,
            TnCntcInfoVO tnCntcInfoVO,
            Model model,
            HttpServletRequest request)
            throws Exception {
    	
//    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
//        //신고분류코드를 코드정보로부터 조회
//        vo.setCodeId("CHG017");
//        model.addAttribute("cntc_result", cmmUseService.selectCmmCodeDetail(vo));   
        
        // 로그인VO에서  사용자 정보 가져오기
        String sUserId = (String) request.getSession().getAttribute("sUserId");

        
        
        List<TnCntcInfoVO> list = null;
        if( sUserId != null ) {
        	tnCntcInfoVO.setUserId(sUserId);
            model.addAttribute("tnCntcInfoVO", tnCntcInfoVO);
            list = tnCntcInfoService.selectTnCntcInfoList(tnCntcInfoVO);
            model.addAttribute("list", list);
        }
        
        
        return "/ngi/api/TnCntcInfoView";
    }
    
    @RequestMapping("/ngi/api/tnCntcInfoRegister.do")
    public String tnCntcInfoRegister(
    		@ModelAttribute("searchVO") TnCntcInfoDefaultVO searchVO,
    		@ModelAttribute("tnCntcInfoVO") TnCntcInfoVO tnCntcInfoVO,
    		Model model,
    		SessionStatus status,
    		HttpServletRequest request)
    				throws Exception {
    	
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
    	//신고분류코드를 코드정보로부터 조회
    	vo.setCodeId("CHG017");
    	model.addAttribute("cntc_result", cmmUseService.selectCmmCodeDetail(vo));   
    	
    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");

    	model.addAttribute("loginVO", loginVO);
    	model.addAttribute("tnCntcInfoVO", new TnCntcInfoVO());
    	status.setComplete();
    	
    	return "/ngi/api/TnCntcInfoRegister";
    }
    
    @RequestMapping("/ngi/api/addTnCntcInfo.do")
    public String addTnCntcInfo(
            TnCntcInfoVO tnCntcInfoVO,
            @ModelAttribute("searchVO") TnCntcInfoDefaultVO searchVO, SessionStatus status,
            Model model, HttpServletRequest request)
            throws Exception {
    	
    	// 로그인VO에서  사용자 정보 가져오기
        //LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        
    	String spec = tnCntcInfoVO.getApplcDomnNm();	// url
		URL url = new URL(spec);
		
    	String userid = request.getParameter("userid");
    	String host = url.getHost();
    	String port = url.getPort() == -1 ? "80" : String.valueOf(url.getPort());
    	
    	OpenApiKeygen keygen = new OpenApiKeygen(userid, host, port);
    	String genApiKey = keygen.getOpenApiKey();	
    	String genAuthKey = keygen.getAuthKey();	
        
    	tnCntcInfoVO.setUserId(userid);
    	tnCntcInfoVO.setCrtfcCodeSe(genApiKey);	// apikey
    	tnCntcInfoVO.setAuth_key(genAuthKey);	// 이메일로 인증받을 키.(이메일로 전송)
    	tnCntcInfoVO.setConfmAt("Y");
    	tnCntcInfoVO.setProtocol(url.getProtocol());
    	tnCntcInfoVO.setHost(host);
    	tnCntcInfoVO.setPort(port);
    	
    	int cnt = tnCntcInfoService.selectTnCntcInfoListTotCnt(tnCntcInfoVO);
    	if(cnt>0) {
    		model.addAttribute("message", "이미 발급된키입니다.");
    		model.addAttribute("tnCntcInfoVO", new TnCntcInfoVO());
    	}
    	else {
    		tnCntcInfoService.insertTnCntcInfo(tnCntcInfoVO);
    		model.addAttribute("message", "신청이 완료되었습니다.");
    		model.addAttribute("tnCntcInfoVO", tnCntcInfoVO);
    	}
        status.setComplete();
        
        
        return "/ngi/api/addTnCntcInfoComp";
        //return "forward:/ngi/api/TnCntcInfoList.do";
    }
    
    @RequestMapping("/ngi/api/updateTnCntcInfoView.do")
    public String updateTnCntcInfoView(
            @RequestParam("cntcInfoId") java.math.BigDecimal cntcInfoId ,
            @ModelAttribute("searchVO") TnCntcInfoDefaultVO searchVO, Model model)
            throws Exception {
        TnCntcInfoVO tnCntcInfoVO = new TnCntcInfoVO();
        tnCntcInfoVO.setCntcInfoId(cntcInfoId);
        // 변수명은 CoC 에 따라 tnCntcInfoVO
        model.addAttribute(selectTnCntcInfo(tnCntcInfoVO, searchVO));
        return "/ngi/api/TnCntcInfoRegister";
    }

    @RequestMapping("/ngi/api/selectTnCntcInfo.do")
    public @ModelAttribute("tnCntcInfoVO")
    TnCntcInfoVO selectTnCntcInfo(
            TnCntcInfoVO tnCntcInfoVO,
            @ModelAttribute("searchVO") TnCntcInfoDefaultVO searchVO) throws Exception {
        return tnCntcInfoService.selectTnCntcInfo(tnCntcInfoVO);
    }

    @RequestMapping("/ngi/api/updateTnCntcInfo.do")
    public String updateTnCntcInfo(
            TnCntcInfoVO tnCntcInfoVO,
            @ModelAttribute("searchVO") TnCntcInfoDefaultVO searchVO, SessionStatus status)
            throws Exception {
        tnCntcInfoService.updateTnCntcInfo(tnCntcInfoVO);
        status.setComplete();
        return "forward:/ngi/api/TnCntcInfoList.do";
    }
    
    @RequestMapping("/ngi/api/deleteTnCntcInfo.do")
    public String deleteTnCntcInfo(
            TnCntcInfoVO tnCntcInfoVO,
            @RequestParam("keynum") BigDecimal keynum,
            @ModelAttribute("searchVO") TnCntcInfoDefaultVO searchVO, SessionStatus status)
            throws Exception {
    	tnCntcInfoVO.setCntcInfoId(keynum);
        tnCntcInfoService.deleteTnCntcInfo(tnCntcInfoVO);
        status.setComplete();
        return "forward:/ngi/api/tnCntcInfoView.do";
    }

    @RequestMapping(value="/ngi/api/NgiApiIntroduce.do")
    public String ngiProvideService(
    		ModelMap model)
            throws Exception {
    	
      	return "ngi/api/NgiApiIntroduce";
    }      
    
    @RequestMapping(value="/ngi/api/NgiApiReperence.do")
    public String ngiApiReperence(
    		ModelMap model)
            throws Exception {
    	
      	return "ngi/api/NgiApiReperence";
    } 
    
    @RequestMapping(value="/ngi/api/NgiApiExample.do")
    public String ngiApiExample(
    		ModelMap model)
            throws Exception {
    	
      	return "ngi/api/NgiApiExample";
    }     
    
    @RequestMapping(value="/ngi/api/search.do", produces="application/json")
    @ResponseBody
	public ModelMap ngiApiRequest(
			ModelMap model,
			@RequestParam Map<String,String> params,
			HttpServletRequest req, HttpServletResponse res)
			throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	TnCntcInfoVO tnCntcInfoVO;
    	if( params.get("key") == null ) {
    		map.put("error", "필수값이 누락되었습니다. : key");	// api 체크.
    	} else if( params.get("key").length() > 0 ) {
    		tnCntcInfoVO = new TnCntcInfoVO();
			tnCntcInfoVO.setCrtfcCodeSe(params.get("key"));
			TnCntcInfoVO infoVO = null;
			try {
				infoVO = tnCntcInfoService.selectTnCntcInfo(tnCntcInfoVO);	
				if(infoVO.getConfmAt().equals("Y")) {
					//사용할수있는 키. 처리.
		    		if( params.get("type") == null) {
			    		map.put("error", "필수값이 누락되었습니다. : type");	// type 체크.
			    	} else {
			    		if( !params.get("type").equals("list") && !params.get("type").equals("detail") ) { // type값 체크.
							map.put("error", "잘못된 요청입니다. : type");
			    		} else { 
			    			if ( params.get("type").equals("detail") ) {	// 상세정보 요청일떄.
			    				
			    				ComDefaultCodeVO codeVO = null;
			    				
			    				if( params.get("cntrwkNo") == null ) {	// changeid 체크.
			    		    		map.put("error", "필수값이 누락되었습니다. : cntrwkNo");
			    		    	} else if ( params.get("cntrwkNo").length() == 0 ) {
			    		    		map.put("error", "잘못된 요청입니다. : cntrwkNo");
			    		    	} else {
			    		    		//map.put("list", "상세정보 요청처리");
			    		    		
			    		    		TnChangeAllInfoVO searchVO = new TnChangeAllInfoVO();
			    		    		searchVO.setCntrwkNo( params.get("cntrwkNo") );
			    		    		TnChangeAllInfoVO resultVO = tnChangeAllInfoService.selectTnChangeAllInfo(searchVO);
			    		    		
			    		    		
			    		    		searchVO.setChangeTy(params.get("changeTy") != null ? params.get("changeTy") : null);	// 변동유형.
				    				//searchVO.setProcessSttusSe(params.get("processSttusSe") != null ? params.get("processSttusSe") : null );  // 처리상태코드를 코드정보로부터 조회
				    				//searchVO.setCntrwkNo(params.get("cntrwkNo") != null ? params.get("cntrwkNo") : null );  // 공사번호.
				    				
				    				codeVO = new ComDefaultCodeVO();
				    				codeVO.setCodeId("CHG003"); codeVO.setCode(resultVO.getChangeTy());
				    				resultVO.setChangeTy(cmmUseService.selectCmmCodeDetail(codeVO).get(0).getCodeNm());
									
									codeVO.setCodeId("CHG004"); codeVO.setCode(resultVO.getProcessSttusSe());
									resultVO.setProcessSttusSe(cmmUseService.selectCmmCodeDetail(codeVO).get(0).getCodeNm());
									
									codeVO.setCodeId("CHG018"); codeVO.setCode(resultVO.getPsitnEngnNo());
									resultVO.setPsitnEngnNo(cmmUseService.selectCmmCodeDetail(codeVO).get(0).getCodeNm());
									
									codeVO.setCodeId("CHG019"); codeVO.setCode(resultVO.getPlanEngnNo());
									resultVO.setPlanEngnNo(cmmUseService.selectCmmCodeDetail(codeVO).get(0).getCodeNm());
									
									codeVO.setCodeId("CHG020"); codeVO.setCode(resultVO.getMngEngnNo());
									resultVO.setMngEngnNo(cmmUseService.selectCmmCodeDetail(codeVO).get(0).getCodeNm());
									
									codeVO.setCodeId("CHG010"); codeVO.setCode(resultVO.getCntm());
									resultVO.setCntm(cmmUseService.selectCmmCodeDetail(codeVO).get(0).getCodeNm());
			    		    		
			    		    		/** 리턴 데이터 만듬. **/ 
			    		    		TnChangeDetailInfoVO detailInfoVO = new TnChangeDetailInfoVO();
			    		    		//detailInfoVO.setChangeInfoId(resultVO.getChangeInfoId());

			    		    		detailInfoVO.setCntrwkNo(resultVO.getCntrwkNo());
									detailInfoVO.setChangeTy(resultVO.getChangeTy() != null ? resultVO.getChangeTy() : "" );
									detailInfoVO.setChangeRnAdresCn(resultVO.getChangeRnAdresCn() != null ? resultVO.getChangeRnAdresCn() : "" );
									//detailInfoVO.setPnuCd(resultVO.getPnuCd() != null ? resultVO.getPnuCd() : "" );
									detailInfoVO.setChangeSj(resultVO.getChangeSj() != null ? resultVO.getChangeSj() : "" );
									detailInfoVO.setStrwrkDe(resultVO.getStrwrkDe());
									detailInfoVO.setCompetPrearngeDe(resultVO.getCompetPrearngeDe());
									detailInfoVO.setLastCompetDe(resultVO.getLastCompetDe());
									detailInfoVO.setProcessSttusSe(resultVO.getProcessSttusSe() != null ? resultVO.getProcessSttusSe() : "" );
									detailInfoVO.setPsitnEngnNo(resultVO.getPsitnEngnNo() != null ? resultVO.getPsitnEngnNo() : "" );
									detailInfoVO.setPlanEngnNo(resultVO.getPlanEngnNo() != null ? resultVO.getPlanEngnNo() : "" );
									detailInfoVO.setMngEngnNo(resultVO.getMngEngnNo() != null ? resultVO.getMngEngnNo() : "" );
									detailInfoVO.setChargerNm(resultVO.getChargerNm() != null ? resultVO.getChargerNm() : "" );
									detailInfoVO.setChrgDeptNm(resultVO.getChrgDeptNm() != null ? resultVO.getChrgDeptNm() : "" );
									detailInfoVO.setChargerTlphonNo(resultVO.getChargerTlphonNo() != null ? resultVO.getChargerTlphonNo() : "" );
									detailInfoVO.setChargerEmail(resultVO.getChargerEmail() != null ? resultVO.getChargerEmail() : "" );
									detailInfoVO.setCntm(resultVO.getCntm() != null ? resultVO.getCntm() : "" );
									detailInfoVO.setRm(resultVO.getRm() != null ? resultVO.getRm() : "" );
									detailInfoVO.setCntrwkPnttm(resultVO.getCntrwkPnttm() != null ? resultVO.getCntrwkPnttm() : "" );
									detailInfoVO.setCntrwkTmnl(resultVO.getCntrwkTmnl() != null ? resultVO.getCntrwkTmnl() : "" );
									detailInfoVO.setAr(resultVO.getAr() != null ? resultVO.getAr() : "" );
									detailInfoVO.setExtn(resultVO.getExtn() != null ? resultVO.getExtn() : "" );
									detailInfoVO.setTrgetBfchgCn(resultVO.getTrgetBfchgCn() != null ? resultVO.getTrgetBfchgCn() : "" );
									detailInfoVO.setTrgetAftchCn(resultVO.getTrgetAftchCn() != null ? resultVO.getTrgetAftchCn() : "" );
									
			    		    		/** geom **/
									ChangeGeomLcVo geomVo = new ChangeGeomLcVo();
									geomVo.setChangeInfoId(resultVO.getChangeInfoId().intValue());
									HashMap<String, Object> map_d = new HashMap<String, Object>();
									map_d.put("proj", "EPSG:4326");
									
									List<ChangeGeomLcVo> changeGeomLcVoList = changeInfoService.selectGeomLcPointInfoEPSG4326(geomVo);
									List geomList = new ArrayList<String>();
									for(ChangeGeomLcVo v:changeGeomLcVoList) {
										geomList.add(v.getLocation());
									}
									map_d.put("points", geomList);
									
									changeGeomLcVoList = changeInfoService.selectGeomLcLnInfoEPSG4326(geomVo);
									geomList = new ArrayList<String>();
									for(ChangeGeomLcVo v:changeGeomLcVoList) {
										geomList.add(v.getLocation());
									}
									map_d.put("lines", geomList);
									
									changeGeomLcVoList = changeInfoService.selectGeomLcMyeonInfoEPSG4326(geomVo);
									geomList = new ArrayList<String>();
									for(ChangeGeomLcVo v:changeGeomLcVoList) {
										geomList.add(v.getLocation());
									}
									map_d.put("polygons", geomList);
									
									changeGeomLcVoList = changeInfoService.selectGeomLcShpInfo(geomVo);
									geomList = new ArrayList<String>();
									for(ChangeGeomLcVo v:changeGeomLcVoList) {
										geomList.add(v.getLocation());
									}
									map_d.put("shp", geomList);									
									
									
									detailInfoVO.setGeom(map_d);
			    		    		/** geom end **/
									
									
									map.put("detailData", detailInfoVO); // 상세정보 최종 담기.
			    		    	}
			    				
			    			} else {
			    				//map.put("data", "리스트요청처리");
			    				
			    				TnChangeAllInfoVO searchVO = new TnChangeAllInfoVO();
								/** EgovPropertyService.sample */
			    				
			    				/* pageing */
			    				TnChangeAllInfoPageVO pageVO = new TnChangeAllInfoPageVO();
			    				pageVO.setPageNum(params.get("pageNum") == null ? pageVO.getPageNum() : Integer.parseInt(params.get("pageNum")) );	// 현재페이지 번호.
			    				pageVO.setPageUnit(params.get("pageUnit") == null ? pageVO.getPageUnit() : Integer.parseInt(params.get("pageUnit")));	//
			    				
			    				searchVO.setFirstIndex(  (pageVO.getPageNum()-1) * pageVO.getPageUnit() );
			    				searchVO.setRecordCountPerPage( pageVO.getPageUnit() );
			    				
			    				//searchVO.setChangeCl("02");
			    				searchVO.setBbox(params.get("bbox"));
			    				
			    				searchVO.setChangeTy(params.get("changeTy") != null ? params.get("changeTy") : null);	// 변동유형.
			    				//searchVO.setProcessSttusSe(params.get("processSttusSe") != null ? params.get("processSttusSe") : null );  // 처리상태코드를 코드정보로부터 조회
			    				//searchVO.setCntrwkNo(params.get("cntrwkNo") != null ? params.get("cntrwkNo") : null );  // 공사번호.
			    				
			    				searchVO.setLastCompetBgnde(params.get("lastCompetBgnde") != null ? Timestamp.valueOf(params.get("lastCompetBgnde")+" 00:00:00") : null);	// 시작일.
			    				searchVO.setLastCompetEndde(params.get("lastCompetEndde") != null ? Timestamp.valueOf(params.get("lastCompetEndde")+" 00:00:00") : null);	// 완료일.

			    				ComDefaultCodeVO codeVO = null;
			    				//model.addAttribute("sttemntty_result", cmmUseService.selectCmmCodeDetail(vo));
			    				
			    				List<TnChangeAllInfoVO> tnChangeAllInfoList = null;
			    				List<TnChangeAllInfoListResultVO> tnResultList = new ArrayList<TnChangeAllInfoListResultVO>();	// 리턴 배열.
			    				
		    					tnChangeAllInfoList = tnChangeAllInfoService.selectTnChangeAllInfoList(searchVO);
								TnChangeAllInfoVO tnChangeAllInfoVO = null;
								TnChangeAllInfoListResultVO tnChangeAllInfoListResultVO = null;
								
								for(int index=0;index<tnChangeAllInfoList.size();index++) {
									
									tnChangeAllInfoVO = tnChangeAllInfoList.get(index);
									codeVO = new ComDefaultCodeVO();
					    			codeVO.setCodeId("CHG003"); codeVO.setCode(tnChangeAllInfoVO.getChangeTy());
					    			tnChangeAllInfoVO.setChangeTy(cmmUseService.selectCmmCodeDetail(codeVO).get(0).getCodeNm());
									
									codeVO.setCodeId("CHG004"); codeVO.setCode(tnChangeAllInfoVO.getProcessSttusSe());
									tnChangeAllInfoVO.setProcessSttusSe(cmmUseService.selectCmmCodeDetail(codeVO).get(0).getCodeNm());
									
									codeVO.setCodeId("CHG018"); codeVO.setCode(tnChangeAllInfoVO.getPsitnEngnNo());
									tnChangeAllInfoVO.setPsitnEngnNo(cmmUseService.selectCmmCodeDetail(codeVO).get(0).getCodeNm());
									
									// 리턴 데이터 만듬.
									tnChangeAllInfoListResultVO = new TnChangeAllInfoListResultVO();
									//tnChangeAllInfoListResultVO.setChangeInfoId(tnChangeAllInfoVO.getChangeInfoId());
									tnChangeAllInfoListResultVO.setCntrwkNo(tnChangeAllInfoVO.getCntrwkNo());
									tnChangeAllInfoListResultVO.setChangeTy(tnChangeAllInfoVO.getChangeTy() != null ? tnChangeAllInfoVO.getChangeTy() : "" );
									tnChangeAllInfoListResultVO.setProcessSttusSe(tnChangeAllInfoVO.getProcessSttusSe() != null ? tnChangeAllInfoVO.getProcessSttusSe() : "" );
									tnChangeAllInfoListResultVO.setPsitnEngnNo(tnChangeAllInfoVO.getPsitnEngnNo() != null ? tnChangeAllInfoVO.getPsitnEngnNo() : "" );
									//tnChangeAllInfoListResultVO.setChangeLnmAdresCn(tnChangeAllInfoVO.getChangeLnmAdresCn() != null ? tnChangeAllInfoVO.getChangeLnmAdresCn() : "" );
									//tnChangeAllInfoListResultVO.setChangeLnmAdresDetailCn(tnChangeAllInfoVO.getChangeLnmAdresDetailCn() != null ? tnChangeAllInfoVO.getChangeLnmAdresDetailCn() : "" );
									//tnChangeAllInfoListResultVO.setChangeRnAdresCn(tnChangeAllInfoVO.getChangeRnAdresCn() != null ? tnChangeAllInfoVO.getChangeRnAdresCn() : "" );
									//tnChangeAllInfoListResultVO.setChangeRnAdresDetailCn(tnChangeAllInfoVO.getChangeRnAdresDetailCn() != null ? tnChangeAllInfoVO.getChangeRnAdresDetailCn() : "" );
									//tnChangeAllInfoListResultVO.setPnuCd(tnChangeAllInfoVO.getPnuCd() != null ? tnChangeAllInfoVO.getPnuCd() : "" );
									tnChangeAllInfoListResultVO.setChangeSj(tnChangeAllInfoVO.getChangeSj() != null ? tnChangeAllInfoVO.getChangeSj() : "" );
									//tnChangeAllInfoListResultVO.setStrwrkDe(tnChangeAllInfoVO.getStrwrkDe());
									//tnChangeAllInfoListResultVO.setCompetPrearngeDe(tnChangeAllInfoVO.getCompetPrearngeDe());
									//tnChangeAllInfoListResultVO.setLastCompetDe(tnChangeAllInfoVO.getLastCompetDe());
									
									/* geom */
									ChangeGeomLcVo geomVo = new ChangeGeomLcVo();
									geomVo.setChangeInfoId(tnChangeAllInfoVO.getChangeInfoId().intValue());
									HashMap<String, Object> map_d = new HashMap<String, Object>();
									map_d.put("proj", "EPSG:4326");
									
									List<ChangeGeomLcVo> changeGeomLcVoList = changeInfoService.selectGeomLcPointInfoEPSG4326(geomVo);
									List geomList = new ArrayList<String>();
									for(ChangeGeomLcVo v:changeGeomLcVoList) {
										geomList.add(v.getLocation());
									}
									map_d.put("points", geomList);
									
									changeGeomLcVoList = changeInfoService.selectGeomLcLnInfoEPSG4326(geomVo);
									geomList = new ArrayList<String>();
									for(ChangeGeomLcVo v:changeGeomLcVoList) {
										geomList.add(v.getLocation());
									}
									map_d.put("lines", geomList);
									
									changeGeomLcVoList = changeInfoService.selectGeomLcMyeonInfoEPSG4326(geomVo);
									geomList = new ArrayList<String>();
									for(ChangeGeomLcVo v:changeGeomLcVoList) {
										geomList.add(v.getLocation());
									}
									map_d.put("polygons", geomList);
									
									changeGeomLcVoList = changeInfoService.selectGeomLcShpInfo(geomVo);
									geomList = new ArrayList<String>();
									for(ChangeGeomLcVo v:changeGeomLcVoList) {
										geomList.add(v.getLocation());
									}
									map_d.put("shp", geomList);										
									
									tnChangeAllInfoListResultVO.setGeom(map_d);
									
									tnResultList.add(tnChangeAllInfoListResultVO);
								}
			    		        map.put("listData", tnResultList);

			    		        /* 페이지 수 계산 */
			    		        int totCnt = tnChangeAllInfoService.selectTnChangeAllInfoListTotCnt(searchVO);
			    				pageVO.setUnitTotalCnt(totCnt);		// 전체 레코드 수  
			    				pageVO.setPageFirstNum(1); 			// 첫 페이지 번호.
			    				pageVO.setPageLastNum( (int)Math.ceil( (double)pageVO.getUnitTotalCnt() / (double)pageVO.getPageUnit()) );  		// 마지막 페이지 번호.
			    				pageVO.setPageTotalCnt( pageVO.getPageLastNum() ); 		// 총 페이지 수.
			    				map.put("pageInfo", pageVO);
			    			}
			    		}
			    	}
					
				} else {
					map.put("error", "잘못된 키입니다. : key");	
				}
			} catch ( Exception e ) {
				map.put("error", "잘못된 요청입니다. : param error");	
				e.printStackTrace();
			}
    	} else {
    		
    	}
    	
    	model.addAttribute("data", map);
    	
    	return model;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

