package egovframework.let.ngi.tgn.web;

import java.io.File;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
//import kr.go.ngi.common.Constants;
//import kr.go.ngi.common.util.CommonUtil;
import egovframework.com.cmm.LoginVO;
import egovframework.let.ngi.tgn.service.TopographyNoticeService;
import egovframework.let.ngi.tgn.service.TopographyNoticeVO;
import egovframework.let.ngi.udt.service.UpdateOperationService;
import egovframework.let.ngi.udt.service.UpdateOperationVO;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 지형고시 관리를 위한 Controller 클래스
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
@Controller
@SessionAttributes("LOGIN_USER")
public class TopographyNoticeController {
	
	/** TopographyNoticeService */
	@Resource(name = "topographyNoticeService")
	private TopographyNoticeService topographyNoticeService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	/** UpdateOperationService */
	@Resource(name="updateOperationService")
	private UpdateOperationService updateOperationService;
	
	/** EgovFileMngUtil */
	@Resource(name = "EgovFileMngUtil") 
	private EgovFileMngUtil fileUtil;
	
	/** EgovFileMngService */
	@Resource(name = "EgovFileMngService") 
	private EgovFileMngService fileMngService;
	
	protected Log log = LogFactory.getLog(this.getClass());

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	            //The date format to parse or output your dates
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            //Create a new CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	            //Register it as custom editor for the Date type
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	/**
	 * 지형고시 상세화면을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ngi/ntc/NgiGeoNtcnView.do")
	public String topographyNoticeView(@ModelAttribute("searchVO") TopographyNoticeVO searchVO, Model model) throws Exception {
		model.addAttribute("topographyNoticeVO", selectTopographyNotice(searchVO));
		return "/ngi/ntc/NgiGeoNtcnView";
	}

	/**
	 * 지형고시 목록을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ngi/ntc/NgiGeoNtcnList.do")
	public String selectTopographyNoticeList(@ModelAttribute("searchVO") TopographyNoticeVO searchVO, ModelMap model) throws Exception {
		/** EgovPropertyService.board */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<TopographyNoticeVO> list = topographyNoticeService.selectTopographyNoticeList(searchVO);
		model.addAttribute("resultList", list);
		
		int totCnt = topographyNoticeService.selectTopographyNoticeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		return "/ngi/ntc/NgiGeoNtcnList";
	}
	
	/**
	 * 지형고시 등록화면을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/topographyNoticeMng/topographyNotice/registerTopographyNoticeView.do")
	public String registerTopographyNoticeView(@ModelAttribute("searchVO") TopographyNoticeVO searchVO, Model model) throws Exception {
		
		String[] ids = searchVO.getUpdtInfoIds();
		
		// 오늘 날짜 가져오기
		GregorianCalendar gCalendar = new GregorianCalendar();
		String year, month, date;
		year = "" + gCalendar.get(Calendar.YEAR);
		month = String.format("%02d", gCalendar.get(Calendar.MONTH) + 1);
		date = String.format("%02d", gCalendar.get(Calendar.DAY_OF_MONTH));
		
		if(ids != null) {
			UpdateOperationVO tempVO = new UpdateOperationVO();
			tempVO.setFirstIndex(0);
			tempVO.setRecordCountPerPage(searchVO.getUpdtInfoIds().length);
			tempVO.setUpdtInfoIds(searchVO.getUpdtInfoIds());
			//tempVO.setMenuType(Constants.MENU_TOPOGRAPHY);
			List<UpdateOperationVO> list = updateOperationService.selectUpdateOperationList(tempVO);
			model.addAttribute("seletedList", list);
		}
		if(searchVO.getTpgrphNtfcId() != null) {
			model.addAttribute(selectTopographyNotice(searchVO));
		}else {
			model.addAttribute(new TopographyNoticeVO());
		}
		
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", date);
		
		return "/topographynotice/topographyNoticeRegister";
	}
	
	/**
	 * 렉스퍼트 브리프의 호수번호를 부여해주기 위해 마지막 번호 찾기
	 * @author jbchoi
	 * @since 2015. 01. 08.
	 * @param 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/topographyNoticeMng/topographyNotice/rexpertRegisterSeq.do")
	public void selectMapType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JSONObject resultData;
		resultData = topographyNoticeService.selectBreifSeq();
		
		response.setContentType( "text/x-json; charset=UTF-8"); 
		Writer writer;
		writer = response.getWriter();
		writer.write(resultData.toJSONString());
        writer.flush();
	}
	
	
	
	
	/**
	 * 지형고시 상세정보를 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/topographyNoticeMng/topographyNotice/selectTopographyNotice.do")
	public TopographyNoticeVO selectTopographyNotice(@ModelAttribute("searchVO") TopographyNoticeVO searchVO) throws Exception {
		log.debug("[selectTopographyNotice] searchVO.getTpgrphNtfcId() : " + searchVO.getTpgrphNtfcId());
		return topographyNoticeService.selectTopographyNotice(searchVO);
	}
	
	/**
	 * 지형고시 정보를 등록한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param searchVO
	 * @param bindingResult
	 * @param model
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/topographyNoticeMng/topographyNotice/registerTopographyNotice.do")
	public ModelAndView registerTopographyNotice(@ModelAttribute("searchVO") TopographyNoticeVO searchVO
			,HttpServletRequest request, final MultipartHttpServletRequest multiRequest
			,@ModelAttribute("LOGIN_USER") LoginVO loginVO)
			throws Exception {
		
		//searchVO.setServcExcprofsCode(loginVO.getInsttCode());
		//searchVO.setDeptCode(loginVO.getDeptCode());
		
		//searchVO.setRegister(loginVO.getUserId());
		//searchVO.setUpdusr(loginVO.getUserId());
		
		//사업지구id
		//searchVO.setBsnsDstrcId(loginVO.getBsnsDstrcIdOrg());
		searchVO.setBsnsDstrcId(null);
		
		List<FileVO> result = null;
		String atchFileId = "";
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "TOP_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		searchVO.setAtchFileId(atchFileId);
		
		if(searchVO.getTpgrphNtfcId() != null) {
			topographyNoticeService.updateTopographyNotice(searchVO);
		}else {
			topographyNoticeService.insertTopographyNotice(searchVO);
		}

		RedirectView view = new RedirectView(request.getContextPath()+"/topographyNoticeMng/topographyNotice/topographyNoticeView.do");
		view.setExposeModelAttributes(true);
		view.addStaticAttribute("tpgrphNtfcId",searchVO.getTpgrphNtfcId() );
		view.addStaticAttribute("msg", "저장 되었습니다.");
		return new ModelAndView(view);
	}
	
	/**
	 * 지형고시 정보를 삭제한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param topographyNoticeVO
	 * @param searchVO
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/topographyNoticeMng/topographyNotice/deleteTopographyNotice.do")
	public ModelAndView deleteTopographyNotice(@ModelAttribute("searchVO") TopographyNoticeVO searchVO, HttpServletRequest request) throws Exception {
		
		topographyNoticeService.deleteTopographyNotice(searchVO);
		
		
		RedirectView view = new RedirectView(request.getContextPath()+"/topographyNoticeMng/topographyNotice/topographyNoticeList.do");
		view.setExposeModelAttributes(true);
		
		return new ModelAndView(view);

	}
	
	/**
	 * 고시성과 파일 다운로드 목록(연속수치)
	 * @author kka
	 * @since 2014. 12. 12.
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/digitalMap/digitalMap/digitalMapList.do")
	public String selectDigitalMapList(@ModelAttribute("searchVO") TopographyNoticeVO searchVO, ModelMap model) throws Exception {
		/** EgovPropertyService.board */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<TopographyNoticeVO> list = topographyNoticeService.selectTopographyNoticeList(searchVO);
		model.addAttribute("resultList", list);
		
		int totCnt = topographyNoticeService.selectTopographyNoticeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		return "/topographynotice/digitalMapList";
	}
	
	@RequestMapping("/digitalMap/digitalMap/selectDigitalMap.do")
	public ModelAndView selectDigitalMap(@ModelAttribute("searchVO") TopographyNoticeVO searchVO) throws Exception {
		
		TopographyNoticeVO topographyNoticeVO = topographyNoticeService.selectTopographyNotice(searchVO);
		
		String[] changeInfoIds = new String[topographyNoticeVO.getUpdtInfoMappingList().size()];
		for(int i=0; i<changeInfoIds.length; i++) {
			changeInfoIds[i] = String.valueOf(topographyNoticeVO.getUpdtInfoMappingList().get(i).getChangeInfoId());
		}
		
		ModelAndView mav = new ModelAndView("zipView");
		mav.addObject("fileName", topographyNoticeVO.getNtfcNm()+"_"+DateUtil.formatDate(topographyNoticeVO.getRgsde(),"yyyyMMdd")+".zip");
		mav.addObject("changeInfoIds", changeInfoIds);
		
		return mav;
	}	
	
	/**
	 * 국토변화 브리프 메일 전송 팝업
	 * @author 윤관호
	 * @since 2015. 01. 16.
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/topographyNoticeMng/topographyNotice/topographyNoticePopup.do")
	public String topographyNoticePopup(@ModelAttribute("searchVO") TopographyNoticeVO searchVO, ModelMap model) throws Exception {
		/** EgovPropertyService.board */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<TopographyNoticeVO> list = topographyNoticeService.selectTopographyNoticeList(searchVO);
		model.addAttribute("resultList", list);
		
		int totCnt = topographyNoticeService.selectTopographyNoticeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		return "popup/topographynotice/topographyNoticePopup";
	}
	/** 국토변화 브리프 파일 다운 로드
	* @author 윤관호
	* @since 2015. 01. 16.
	* @param searchVO
	* @param model
	* @return
	* @throws Exception
	*/
	@RequestMapping(value = "/topographyNoticeMng/topographyNotice/topographyNoticefiledown.do")
	public ModelAndView topographyNoticefiledown(@RequestParam("fileNm") String fileNm, HttpServletRequest request, ModelMap model) throws Exception {
		
		ModelAndView mav = new ModelAndView("downloadView");
		mav.addObject("downloadFile", new File(propertiesService.getString("Globals.fileRexhwpStorePath")+fileNm));// 로컬
		
		log.debug("[fileDownloadView] fileNm : " +fileNm);
		mav.addObject("fileName", fileNm);
		return mav;
	}
	
}
