package egovframework.let.ngi.trs.web;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import egovframework.com.cmm.FlieUtilComponent;
import egovframework.let.ngi.trs.service.NgiTrsDefaultVO;
import egovframework.let.ngi.trs.service.NgiTrsEaisVO;
import egovframework.let.ngi.trs.service.NgiTrsService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;




@Controller
@SessionAttributes(types=NgiTrsEaisVO.class)
public class NgiTrsController {
	
    @Resource(name = "ngiTrsService")
    private NgiTrsService ngiTrsService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Autowired
    private FlieUtilComponent flieUtilComponent;
    
    @RequestMapping(value="/ngi/trs/NgiTrsInfo.do")
    public String ngiTrsInfo(
    		ModelMap model)
            throws Exception {
    	
      	return "ngi/trs/NgiTrsInfo";
    }    

    @RequestMapping(value="/ngi/trs/NgiTrsList.do")
    public String ngiTrsList(@ModelAttribute("searchVO") NgiTrsDefaultVO searchVO,
    		ModelMap model, HttpServletRequest request)
            throws Exception {
    	
    	/** EgovPropertyService.sample */
		searchVO.setPageUnit(10);
		searchVO.setPageSize(10);
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List ngiTrsEaisList = ngiTrsService.selectNgiTrsEaisList(searchVO);
        model.addAttribute("resultList", ngiTrsEaisList);
        
        int totCnt = ngiTrsService.selectNgiTrsEaisTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);    	
    	
      	return "ngi/trs/NgiTrsList";
    }      
    
    /*
    @RequestMapping(value="/ngi/trs/NgiTrsDownList.do")
    public String ngiTrsDownList(@ModelAttribute("searchVO") NgiTrsDefaultVO searchVO,
    		ModelMap model, HttpServletRequest request)
            throws Exception {
    	
    	/// EgovPropertyService.sample   
		searchVO.setPageUnit(10);
		searchVO.setPageSize(10);
    	
    	/// pageing  
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List ngiTrsEaisList = ngiTrsService.selectNgiTrsEaisList(searchVO);
        model.addAttribute("resultList", ngiTrsEaisList);
        
        int totCnt = ngiTrsService.selectNgiTrsEaisTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);    	
    	
      	return "ngi/trs/NgiTrsDownList";
    } 
    */
    
    
    // 새주소 세움터 파일 다운로드 리스트.
    String searchCondition = "";
    @RequestMapping(value="/ngi/trs/NgiTrsDownList.do")
    public String NgiTrsDownList(@ModelAttribute("searchVO") NgiTrsDefaultVO searchVO,
    		ModelMap model, HttpServletRequest request)
            throws Exception {
    	
    	/** EgovPropertyService.sample */
		searchVO.setPageUnit(10);
		searchVO.setPageSize(10);
        
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List resultList = null;
		int totalRecordCount = 0;
		
		
		if(!"".equals(searchVO.getSearchCondition())){
			searchCondition = searchVO.getSearchCondition();
		}
		
		// 파일 리스트 가져오기.
		//if(searchVO.getSearchCondition().equals("00") || searchVO.getSearchCondition().equals("")) {	// 세움터
		if(searchCondition.equals("00") || searchCondition.equals("")) {	// 세움터
			resultList = flieUtilComponent.getEaisFileList(searchVO.getFirstIndex(), searchVO.getLastIndex());
			//totalRecordCount = flieUtilComponent.getEaisFileCount();
			totalRecordCount = 30;
		//} else if (searchVO.getSearchCondition().equals("01")) {	// 새주소.
		} else if (searchCondition.equals("01")) {	// 새주소.
			resultList = flieUtilComponent.getKaisFileList(searchVO.getFirstIndex(), searchVO.getLastIndex());
			//totalRecordCount = flieUtilComponent.getKaisFileCount();
			totalRecordCount = 30;
		}
		
		
		paginationInfo.setTotalRecordCount(totalRecordCount);
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("searchCondition", searchCondition);
        model.addAttribute("paginationInfo", paginationInfo);    	
        
        
      	return "ngi/trs/NgiTrsDownList";
    } 
    
    // 새주소 세움터 파일다운로드.
    @RequestMapping(value="/ngi/trs/NgiTrsDown.do", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void NgiTrsDown(HttpServletResponse response
    	,@RequestParam(value="type", required=true)String type
    	,@RequestParam(value="name", required=true)String name
    	) throws Exception {
    	
    	response.setHeader("Content-Disposition", "attachment; filename=" + name);
    	response.setHeader("Content-Transfer-Encoding", "binary");
    	response.setContentType("application/download; utf-8");
    	
    	name = name.replaceAll("[:\\\\/*?|<>]", "");
    	
    	File file = null;
    	if( type.equals("eais") ) {
    		file = new File(flieUtilComponent.getEaisDirectoryPath()+File.separatorChar+name);	// 세움터.
    	} else if ( type.equals("kais") ) {
    		file = new File(flieUtilComponent.getKaisDirectoryPath()+File.separatorChar+name);	// 새주소
    	}
    	
    	FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        response.flushBuffer();
    }
}
