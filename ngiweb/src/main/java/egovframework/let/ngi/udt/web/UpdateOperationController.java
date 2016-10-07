package egovframework.let.ngi.udt.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.let.ngi.chg.service.ChangeInfoVO;
import egovframework.let.ngi.chg.service.ChangeInfoService;
import egovframework.let.ngi.udt.service.Constants;
import egovframework.let.ngi.udt.service.MapdmcVO;
import egovframework.let.ngi.udt.service.UpdateOperationService;
import egovframework.let.ngi.udt.service.UpdateOperationVO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 성과등록 관리를 위한 Controller 클래스
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
public class UpdateOperationController {

	/** UpdateOperationService */
	@Resource(name="updateOperationService")
	private UpdateOperationService updateOperationService;

	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;	
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** ChangeService */
	@Resource(name = "changeInfoService")
	private ChangeInfoService changeInfoService;
	
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
	 * 갱신작업 목록을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/ngi/udt/updateOperationList.do"})
	public String updateOperationList(@ModelAttribute("searchVO") UpdateOperationVO searchVO, Model model) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		
		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("changeTy_result", cmmUseService.selectCmmCodeDetail(vo));		
		
		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG017");
		model.addAttribute("search_result", cmmUseService.selectCmmCodeDetail(vo));		
		
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
		
		List<UpdateOperationVO> list = updateOperationService.selectUpdateOperationList(searchVO);
		model.addAttribute("resultList", list);
		
		int totCnt = updateOperationService.selectUpdateOperationListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		String returnUrl = "";

		returnUrl = "/ngi/udt/updateOperationList";
		
		return returnUrl;
	}
	
	/**
	 * 갱신작업정보 상세정보 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ngi/udt/selectUpdateOperation.do")
	UpdateOperationVO selectUpdateOperation(@ModelAttribute("searchVO") UpdateOperationVO searchVO) throws Exception {
		return updateOperationService.selectUpdateOperation(searchVO);
		
		
	}
	
	/**
	 * 갱신작업정보 상세 화면을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ngi/udt/updateOperationInqire.do")
	public String updateOperationInqire(@ModelAttribute("searchVO") UpdateOperationVO searchVO, Model model) throws Exception {

		model.addAttribute("updateOperationVO", selectUpdateOperation(searchVO));

		String returnUrl = "";
		returnUrl = "/ngi/udt/updateOperationInqire";
		return returnUrl;
		
	}
	
	/**
	 * 성과 등록 화면을 조회한다.
	 * @author kka
	 * @since 2014. 11. 14.
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ngi/udt/registerUpdateOperationView.do")
	public String registerUpdateOperationView(@ModelAttribute("updateOperationVO") UpdateOperationVO searchVO, Model model) throws Exception {
		
		if(searchVO.getChangeInfoId() != null && searchVO.getUpdtInfoId() == null) {
			ChangeInfoVO changeInfoVO = new ChangeInfoVO();
			changeInfoVO.setChangeInfoId(searchVO.getChangeInfoId());
			model.addAttribute("changeInfoVO", changeInfoService.selectChangeInfo(changeInfoVO));
			model.addAttribute("updateOperationVO", searchVO);
		}
		
		List<?> list;
		Map<String, String> map = new HashMap<String, String>();
		MapdmcVO mapdmcVO = new MapdmcVO();
		
		mapdmcVO.setMapdmcSe(Constants.ONE_FIVE);
		list = updateOperationService.selectMapdmcList(mapdmcVO);
		model.addAttribute("mapdmcSe5000List", list); //1:5000 도엽이름 목록
		
//		map = (Map<String, String>) list.get(0);
//		mapdmcVO.setMapdmcGroup(map.get("mapdmcNo"));
//		list = updateOperationService.selectMapdmcDetailList(mapdmcVO);
//		model.addAttribute("mapdmcNo5000List", list); //1:5000 도엽이름 첫번째 도엽번호 목록
		
		mapdmcVO.setMapdmcSe(Constants.ONE_TWENTYFIVE);
		list = updateOperationService.selectMapdmcList(mapdmcVO);
		model.addAttribute("mapdmcSe25000List", list); //1:25000 도엽이름 목록
		
//		map = (Map<String, String>) list.get(0);
//		mapdmcVO.setMapdmcGroup(map.get("mapdmcNo"));
//		list = updateOperationService.selectMapdmcDetailList(mapdmcVO);
//		model.addAttribute("mapdmcNo25000List", list); //1:25000 도엽이름 첫번째 도엽번호 목록
				
		if(searchVO.getUpdtInfoId() != null) {
			model.addAttribute("updateOperationVO",selectUpdateOperation(searchVO));
		}else {
			model.addAttribute("updateOperationVO_new",new UpdateOperationVO());
		}
		return "/ngi/udt/updateOperationRegister";
	}
	
	/**
	 * 도엽번호 조회한다.
	 * @author kka
	 * @since 2014. 11. 14.
	 * @param mapdmcVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ngi/udt/selectMapdmcDetailList.do")
	public ModelAndView selectMapdmcDetailList(@ModelAttribute("mapdmcVO") MapdmcVO mapdmcVO, HttpServletRequest request, ModelMap model) throws Exception {
			
		ModelAndView mav = new ModelAndView("jsonView");
		
		List<?> mapdmcNolist = updateOperationService.selectMapdmcDetailList(mapdmcVO);
		
		mav.addObject("mapdmcNolist", mapdmcNolist);
		
		return mav;
	}
	
	/**
	 * 갱신작업정보를 등록 또는 수정한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param request
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping("/ngi/udt/registerUpdateOperation.do")
	public ModelAndView registerUpdateOperation(HttpServletRequest request, @ModelAttribute("searchVO") UpdateOperaionVO searchVO) throws Exception {
		String[] changeInfoIds = request.getParameterValues("changeInfoIdArr");
		
		searchVO.setChangeInfoIds(changeInfoIds);
		if(searchVO.getUpdtInfoId() != null) {
			updateOperationService.updateUpdateOpertation(searchVO);
		}else {
			updateOperationService.insertUpdateOperation(searchVO);
		}
		RedirectView view = new RedirectView(request.getContextPath()+"/updateOperation/updateOperationView.do");
		view.setExposeModelAttributes(true);
		view.addStaticAttribute("updtInfoId", searchVO.getUpdtInfoId());
		view.addStaticAttribute("menuType", searchVO.getMenuType());
		view.addStaticAttribute("msg", "저장 되었습니다.");
		return new ModelAndView(view);
	}*/
	
	/**
	 * 성과 정보를 등록 및 수정 한다.
	 * @author kka
	 * @since 2014. 11. 14.
	 * @param request
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ngi/udt/registerUpdateOperation.do")
	public ModelAndView registerUpdateOperation(@ModelAttribute("searchVO") UpdateOperationVO searchVO ,HttpServletRequest request) throws Exception {
		
		if(searchVO.getUpdtInfoId() != null) {
			updateOperationService.updateUpdateOpertation(searchVO);
		}else {
			updateOperationService.insertUpdateOperation(searchVO);
		}
		RedirectView view = new RedirectView(request.getContextPath()+"/updateOperation/updateOperationView.do");
		view.setExposeModelAttributes(true);
		view.addStaticAttribute("updtInfoId", searchVO.getUpdtInfoId());
		view.addStaticAttribute("menuType", searchVO.getMenuType());
		view.addStaticAttribute("msg", "저장 되었습니다.");
		return new ModelAndView(view);
	}
	
	/**
	 * 성과 정보를 삭제한다
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param request
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ngi/udt/deleteUpdateOperation.do")
	public ModelAndView deleteUpdateOperation(@ModelAttribute("updateOperationVO") UpdateOperationVO searchVO, HttpServletRequest request) throws Exception {
		//System.out.println("[controller getChangeInfoId]"+searchVO.getChangeInfoId());
		//System.out.println("[controller getUpdtInfoId]"+searchVO.getUpdtInfoId());
		updateOperationService.deleteUpdateOperation(searchVO);
		RedirectView view = new RedirectView(request.getContextPath()+"/ngi/udt/updateOperationList.do");
		view.setExposeModelAttributes(true);
		view.addStaticAttribute("menuType", searchVO.getMenuType());
		
		return new ModelAndView(view);
	}
	
	/**
	 * 갱신작업의 검수이력을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/udt/updateOperationAdjustPop.do")
	public String updateOperationAdjustPop(@ModelAttribute("searchVO") UpdateOperationVO searchVO, Model model) throws Exception {
		
		model.addAttribute("updateOperationVO",selectUpdateOperation(searchVO));
		
		return "popup/udt/updateOperationAdjustPop";
	}
	

	
}
