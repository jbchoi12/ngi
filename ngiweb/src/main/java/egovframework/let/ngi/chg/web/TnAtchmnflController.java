package egovframework.let.ngi.chg.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.let.ngi.chg.service.TnAtchmnflDefaultVO;
import egovframework.let.ngi.chg.service.TnAtchmnflVO;
import egovframework.let.ngi.chg.service.impl.TnAtchmnflService;

/**
 * @Class Name : TnAtchmnflController.java
 * @Description : TnAtchmnfl Controller class
 * @Modification Information
 *
 * @author a
 * @since a
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@SessionAttributes(types=TnAtchmnflVO.class)
public class TnAtchmnflController {

    @Resource(name = "tnAtchmnflService")
    private TnAtchmnflService tnAtchmnflService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tn_atchmnfl 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TnAtchmnflDefaultVO
	 * @return "/tnAtchmnfl/TnAtchmnflList"
	 * @exception Exception
	 */
    @RequestMapping(value="/tnAtchmnfl/TnAtchmnflList.do")
    public String selectTnAtchmnflList(@ModelAttribute("searchVO") TnAtchmnflDefaultVO searchVO, 
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
		
        List tnAtchmnflList = tnAtchmnflService.selectTnAtchmnflList(searchVO);
        model.addAttribute("resultList", tnAtchmnflList);
        
        int totCnt = tnAtchmnflService.selectTnAtchmnflListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/tnAtchmnfl/TnAtchmnflList";
    } 
    
    @RequestMapping(value="/tnAtchmnfl/TnAtchmnflListJson.do")
	@ResponseBody
    public void selectTnAtchmnflListJson(@ModelAttribute("searchVO") TnAtchmnflDefaultVO searchVO, HttpServletResponse response,
    		ModelMap model)
            throws Exception {
    	
		String str_resdata = "";
		String str_error = "error";
		
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing */
//    	PaginationInfo paginationInfo = new PaginationInfo();
//		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
//		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
//		paginationInfo.setPageSize(searchVO.getPageSize());
		
//		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
//		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		try {
	        List tnAtchmnflList = tnAtchmnflService.selectTnAtchmnflListPhone(searchVO);
//	        model.addAttribute("resultList", tnAtchmnflList);
	        
	        //int totCnt = tnAtchmnflService.selectTnAtchmnflListTotCnt(searchVO);
			//paginationInfo.setTotalRecordCount(totCnt);
//	        model.addAttribute("paginationInfo", paginationInfo);
	        
			Map<String, Object> map_d = new HashMap<String, Object>();

			map_d.put("data", tnAtchmnflList);

			ObjectMapper om = new ObjectMapper();
			str_resdata = om.writeValueAsString(map_d);
			
		} catch (Exception ex) {
			//System.out.println(this.getClass().toString() + " Exception : " + ex.toString());
			Map<String, Object> map_d = new HashMap<String, Object>();
			map_d.put(str_error, "Error : " + ex.toString());
			ObjectMapper om = new ObjectMapper();
			str_resdata = om.writeValueAsString(map_d);
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(str_resdata);
		pw.flush();
        
    }
    
    @RequestMapping("/tnAtchmnfl/addTnAtchmnflView.do")
    public String addTnAtchmnflView(
            @ModelAttribute("searchVO") TnAtchmnflDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("tnAtchmnflVO", new TnAtchmnflVO());
        return "/tnAtchmnfl/TnAtchmnflRegister";
    }
    
    @RequestMapping("/tnAtchmnfl/addTnAtchmnfl.do")
    public String addTnAtchmnfl(
            TnAtchmnflVO tnAtchmnflVO,
            @ModelAttribute("searchVO") TnAtchmnflDefaultVO searchVO, SessionStatus status)
            throws Exception {
        tnAtchmnflService.insertTnAtchmnfl(tnAtchmnflVO);
        status.setComplete();
        return "forward:/tnAtchmnfl/TnAtchmnflList.do";
    }
    
    @RequestMapping("/tnAtchmnfl/updateTnAtchmnflView.do")
    public String updateTnAtchmnflView(
            @RequestParam("atchmnflId") java.math.BigDecimal atchmnflId ,
            @ModelAttribute("searchVO") TnAtchmnflDefaultVO searchVO, Model model)
            throws Exception {
        TnAtchmnflVO tnAtchmnflVO = new TnAtchmnflVO();
        tnAtchmnflVO.setAtchmnflId(atchmnflId);
        // 변수명은 CoC 에 따라 tnAtchmnflVO
        model.addAttribute(selectTnAtchmnfl(tnAtchmnflVO, searchVO));
        return "/tnAtchmnfl/TnAtchmnflRegister";
    }

    @RequestMapping("/tnAtchmnfl/selectTnAtchmnfl.do")
    public @ModelAttribute("tnAtchmnflVO")
    TnAtchmnflVO selectTnAtchmnfl(
            TnAtchmnflVO tnAtchmnflVO,
            @ModelAttribute("searchVO") TnAtchmnflDefaultVO searchVO) throws Exception {
        return tnAtchmnflService.selectTnAtchmnfl(tnAtchmnflVO);
    }

    @RequestMapping("/tnAtchmnfl/updateTnAtchmnfl.do")
    public String updateTnAtchmnfl(
            TnAtchmnflVO tnAtchmnflVO,
            @ModelAttribute("searchVO") TnAtchmnflDefaultVO searchVO, SessionStatus status)
            throws Exception {
        tnAtchmnflService.updateTnAtchmnfl(tnAtchmnflVO);
        status.setComplete();
        return "forward:/tnAtchmnfl/TnAtchmnflList.do";
    }
    
    @RequestMapping("/tnAtchmnfl/deleteTnAtchmnfl.do")
    public String deleteTnAtchmnfl(
            TnAtchmnflVO tnAtchmnflVO,
            @ModelAttribute("searchVO") TnAtchmnflDefaultVO searchVO, SessionStatus status)
            throws Exception {
        tnAtchmnflService.deleteTnAtchmnfl(tnAtchmnflVO);
        status.setComplete();
        return "forward:/tnAtchmnfl/TnAtchmnflList.do";
    }

    @RequestMapping("/tnAtchmnfl/deleteTnAtchmnflPhone.do")
    public void deleteTnAtchmnflPhone(
            TnAtchmnflVO tnAtchmnflVO,
            @ModelAttribute("searchVO") TnAtchmnflDefaultVO searchVO, SessionStatus status)
            throws Exception {
        tnAtchmnflService.deleteTnAtchmnflPhone(tnAtchmnflVO);
        status.setComplete();
        //return "forward:/tnAtchmnfl/TnAtchmnflList.do";
    }
}
