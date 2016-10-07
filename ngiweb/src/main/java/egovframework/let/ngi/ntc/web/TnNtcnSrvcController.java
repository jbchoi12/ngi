package egovframework.let.ngi.ntc.web;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.com.cmm.util.EncodeUtil;
import egovframework.let.ngi.ntc.service.TnNtcnSrvcService;
import egovframework.let.ngi.ntc.service.TnNtcnSrvcDefaultVO;
import egovframework.let.ngi.ntc.service.TnNtcnSrvcVO;
import egovframework.let.utl.fcc.service.AesUtil;

/**
 * @Class Name : TnNtcnSrvcController.java
 * @Description : TnNtcnSrvc Controller class
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
@SessionAttributes(types=TnNtcnSrvcVO.class)
public class TnNtcnSrvcController {

    @Resource(name = "tnNtcnSrvcService")
    private TnNtcnSrvcService tnNtcnSrvcService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tn_ntcn_srvc 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TnNtcnSrvcDefaultVO
	 * @return "/tnNtcnSrvc/TnNtcnSrvcList"
	 * @exception Exception
	 */
    @RequestMapping(value="/ngi/ntc/TnNtcnSrvcList.do")
    public String selectTnNtcnSrvcList(@ModelAttribute("searchVO") TnNtcnSrvcDefaultVO searchVO, 
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
		
        List tnNtcnSrvcList = tnNtcnSrvcService.selectTnNtcnSrvcList(searchVO);
        model.addAttribute("resultList", tnNtcnSrvcList);
        
        int totCnt = tnNtcnSrvcService.selectTnNtcnSrvcListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/ngi/ntc/TnNtcnSrvcList";
    } 
    
    @RequestMapping("/ngi/ntc/addTnNtcnSrvcView.do")
    public String addTnNtcnSrvcView(
            @ModelAttribute("searchVO") TnNtcnSrvcDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("tnNtcnSrvcVO", new TnNtcnSrvcVO());
        return "/ngi/ntc/TnNtcnSrvcRegister";
    }

    @RequestMapping("/ngi/ntc/addTnNtcnSrvcComp.do")
    public String addTnNtcnSrvcComp(
            @ModelAttribute("searchVO") TnNtcnSrvcDefaultVO searchVO, Model model)
            throws Exception {

        model.addAttribute("message", "등록되었습니다.");    	
        return "/ngi/ntc/TnNtcnSrvcRegister";
    }    
    
    @RequestMapping("/ngi/ntc/addTnNtcnSrvc.do")
    public String addTnNtcnSrvc(
            TnNtcnSrvcVO tnNtcnSrvcVO,
            @ModelAttribute("searchVO") TnNtcnSrvcDefaultVO searchVO, SessionStatus status)
            throws Exception {
    	//int maxNtcnSrvcId = tnNtcnSrvcService.selectTnNtcnSrvMaxid(tnNtcnSrvcVO);
    	
    	//tnNtcnSrvcVO.setNtcnSrvcId(maxNtcnSrvcId);

		String strTelno = tnNtcnSrvcVO.getTelno();
		String strEmail = tnNtcnSrvcVO.getEmail();    	
    	
		EncodeUtil encodeUtil = new EncodeUtil();
		strTelno = new String(strTelno.getBytes("8859_1"),"UTF-8");
		strTelno = URLDecoder.decode(strTelno, "UTF-8");		
		strTelno = encodeUtil.StrtoUni(strTelno);
		strTelno = encodeUtil.UnitoStr(strTelno);		
		
		strEmail = new String(strEmail.getBytes("8859_1"),"UTF-8");
		strEmail = URLDecoder.decode(strEmail, "UTF-8");		
		strEmail = encodeUtil.StrtoUni(strEmail);
		strEmail = encodeUtil.UnitoStr(strEmail);			
		
		// AES 암호화/복호화 셋팅
		AesUtil aesUtil = new AesUtil(128, 10000);
		
		//이메일 암호화
		tnNtcnSrvcVO.setTelno(aesUtil.encryptDecrypy(strEmail, 1));
		tnNtcnSrvcVO.setEmail(aesUtil.encryptDecrypy(strTelno, 1));    	
    	
        tnNtcnSrvcService.insertTnNtcnSrvc(tnNtcnSrvcVO);
        status.setComplete();

        return "forward:/ngi/ntc/addTnNtcnSrvcComp.do";
        //return "forward:/ngi/ntc/TnNtcnSrvcList.do";
    }
    
    @RequestMapping("/ngi/ntc/updateTnNtcnSrvcView.do")
    public String updateTnNtcnSrvcView(
            @RequestParam("ntcnSrvcId") int ntcnSrvcId ,
            @ModelAttribute("searchVO") TnNtcnSrvcDefaultVO searchVO, Model model)
            throws Exception {
        TnNtcnSrvcVO tnNtcnSrvcVO = new TnNtcnSrvcVO();
        tnNtcnSrvcVO.setNtcnSrvcId(ntcnSrvcId);
        // 변수명은 CoC 에 따라 tnNtcnSrvcVO
        model.addAttribute(selectTnNtcnSrvc(tnNtcnSrvcVO, searchVO));
        return "/ngi/ntc/TnNtcnSrvcRegister";
    }

    @RequestMapping("/ngi/ntc/selectTnNtcnSrvc.do")
    public @ModelAttribute("tnNtcnSrvcVO")
    TnNtcnSrvcVO selectTnNtcnSrvc(
            TnNtcnSrvcVO tnNtcnSrvcVO,
            @ModelAttribute("searchVO") TnNtcnSrvcDefaultVO searchVO) throws Exception {
        return tnNtcnSrvcService.selectTnNtcnSrvc(tnNtcnSrvcVO);
    }

    @RequestMapping("/ngi/ntc/updateTnNtcnSrvc.do")
    public String updateTnNtcnSrvc(
            TnNtcnSrvcVO tnNtcnSrvcVO,
            @ModelAttribute("searchVO") TnNtcnSrvcDefaultVO searchVO, SessionStatus status)
            throws Exception {
        tnNtcnSrvcService.updateTnNtcnSrvc(tnNtcnSrvcVO);
        status.setComplete();
        return "forward:/ngi/ntc/TnNtcnSrvcList.do";
    }
    
    @RequestMapping("/ngi/ntc/deleteTnNtcnSrvc.do")
    public String deleteTnNtcnSrvc(
            TnNtcnSrvcVO tnNtcnSrvcVO,
            @ModelAttribute("searchVO") TnNtcnSrvcDefaultVO searchVO, SessionStatus status)
            throws Exception {
        tnNtcnSrvcService.deleteTnNtcnSrvc(tnNtcnSrvcVO);
        status.setComplete();
        return "forward:/ngi/ntc/TnNtcnSrvcList.do";
    }

    @RequestMapping(value="/ngi/ntc/NgiNoticeService.do")
    public String ngiNoticeService(
    		ModelMap model)
            throws Exception {
    	
      	return "ngi/ntc/NgiNoticeService";
    }    
    
}
