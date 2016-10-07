package egovframework.let.ngi.udt.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.let.ngi.udt.service.AcptncHistService;
import egovframework.let.ngi.udt.service.AcptncHistVO;
import egovframework.let.ngi.udt.service.UpdateOperationVO;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 검수이력을 관리하기 위한 Controller 클래스
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
public class AcptncHistController {
	
	/** AcptncHistService */
	@Resource(name="acptncHistService")
	private AcptncHistService acptncHistService;
	
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
	 * 성과의 검수이력을 등록한다.
	 * @author kka
	 * @since 2014. 11. 15.
	 * @param searchVO
	 * @param updateOperaionVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateOperation/registerAcptncHist.do")
	public ModelAndView registerAcptncHist(@ModelAttribute("searchVO") AcptncHistVO searchVO, UpdateOperationVO updateOperaionVO, HttpServletRequest request) throws Exception {
		
		acptncHistService.insertAcptncHist(searchVO);
		
		RedirectView view = new RedirectView(request.getContextPath()+"/updateOperation/updateOperationView.do");
		view.setExposeModelAttributes(true);
		view.addStaticAttribute("updtInfoId", searchVO.getUpdtInfoId());
		view.addStaticAttribute("menuType", updateOperaionVO.getMenuType());
		view.addStaticAttribute("msg", "저장 되었습니다.");
		return new ModelAndView(view);
	}
	
	/**
	 * 갱신작업의 검수이력을 일괄 검수완료로 등록한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param searchVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateOperation/registerAcptncHistMulti.do")
	public ModelAndView registerAcptncHistMulti(@ModelAttribute("searchVO") UpdateOperationVO searchVO, HttpServletRequest request) throws Exception {
		acptncHistService.insertAcptncHistMulti(searchVO);
		
		RedirectView view = new RedirectView(request.getContextPath()+"/updateOperation/updateOperationList.do");
		view.setExposeModelAttributes(true);
		view.addStaticAttribute("menuType", searchVO.getMenuType());
		view.addStaticAttribute("msg", "저장 되었습니다.");
		return new ModelAndView(view);
	}

}
