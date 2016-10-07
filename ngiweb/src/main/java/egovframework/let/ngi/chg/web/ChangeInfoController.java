package egovframework.let.ngi.chg.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.util.URLEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.WKTReader;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EncodeUtil;
import egovframework.let.ngi.chg.service.ChangeCntrwkInfoVO;
import egovframework.let.ngi.chg.service.ChangeGeomLcVo;
import egovframework.let.ngi.chg.service.ChangeHistVO;
import egovframework.let.ngi.chg.service.ChangeInfoDefaultVO;
import egovframework.let.ngi.chg.service.ChangeInfoRadiusSearchingVO;
import egovframework.let.ngi.chg.service.ChangeInfoService;
import egovframework.let.ngi.chg.service.ChangeInfoVO;
import egovframework.let.ngi.chg.service.ChangeSttemntCntrwkVO;
import egovframework.let.ngi.chg.service.ChangeSttemntInfoVO;
import egovframework.let.ngi.chg.service.CntrwkInfoVO;
import egovframework.let.ngi.chg.service.SttemntInfoVO;
import egovframework.let.ngi.chg.service.TnAtchmnflVO;
import egovframework.let.ngi.chg.service.impl.TnAtchmnflService;
import egovframework.let.ngi.pnt.service.ScoreLogService;
import egovframework.let.ngi.pnt.service.ScoreLogVO;
import egovframework.let.ngi.trs.service.NgiTrsService;
import egovframework.let.ngi.udt.service.UpdateOperationVO;
import egovframework.let.ngi.udt.service.UpdateOperationService;
import egovframework.let.uss.umt.service.EgovMberManageService;
import egovframework.let.uss.umt.service.MberManageVO;
import egovframework.let.utl.fcc.service.AesUtil;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.let.ngi.trs.service.NgiTrsService;
import egovframework.let.ngi.trs.service.NgiTrsDefaultVO;
import egovframework.let.ngi.trs.service.NgiTrsEaisVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.filter.HTMLTagFilterRequestWrapper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : TnChangeInfoController.java
 * @Description : TnChangeInfo Controller class
 * @Modification Information
 * 
 * @author 이정연
 * @since 2014-09-29
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Controller
// @SessionAttributes(types=ChangeInfoVO.class)
public class ChangeInfoController {

	@Resource(name = "changeInfoService")
	private ChangeInfoService changeInfoService;

	@Resource(name = "updateOperationService")
	private UpdateOperationService updateOperationService;	

    @Resource(name = "ngiTrsService")
    private NgiTrsService ngiTrsService;
    
	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;
	
    @Resource(name = "tnAtchmnflService")
    private TnAtchmnflService tnAtchmnflService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
    /** mberManageService */
    @Resource(name = "mberManageService")
    private EgovMberManageService mberManageService;	

	@Resource(name = "scoreLogService")
	private ScoreLogService scoreLogService;    

	private Logger logger = LogManager.getLogger();  
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		// The date format to parse or output your dates
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		// Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}

	/**
	 * tn_change_info 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 ChangeInfoDefaultVO
	 * @return "/ngi/chg/ChangeInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/ngi/chg/ChangeInfoList.do")
	public String selectChangeInfoList( @ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			ModelMap model, HttpServletRequest request) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(5);
		searchVO.setPageSize(10);

		// 신고분류코드를 코드정보로부터 조회
		vo.setCodeId("CHG002");
		model.addAttribute("sttemntty_result", cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		model.addAttribute("process_result", cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG017");
		List<CmmnDetailCode> ll = cmmUseService.selectCmmCodeDetail(vo);

		for (int i = ll.size(); i > 0; i--) {
			if (ll.get(i - 1).getCode().equals("03"))
				ll.remove(ll.get(i - 1));
			else if (ll.get(i - 1).getCode().equals("04"))
				ll.remove(ll.get(i - 1));
		}

		model.addAttribute("search_result", ll);

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setChangeCl("01");
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List tnChangeInfoList = changeInfoService .selectChangeInfoList(searchVO);
		model.addAttribute("resultList", tnChangeInfoList);

		int totCnt = changeInfoService.selectChangeInfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/ngi/chg/ChangeInfoList";
	}

	@RequestMapping("/ngi/chg/addChangeInfoView.do")
	public String addChangeInfoView(
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			Model model) throws Exception {
		

    	LoginVO user = new LoginVO();
	    if(EgovUserDetailsHelper.isAuthenticated()){
	    user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();		
	    }
	    
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("sttemntty_result",
				cmmUseService.selectCmmCodeDetail(vo));
		MberManageVO mvo = new MberManageVO();
		mvo = mberManageService.selectMber(user.getId());

		model.addAttribute("mberManageVO", mvo);
		model.addAttribute("changeSttemntInfoVO", new ChangeSttemntInfoVO());
		return "/ngi/chg/ChangeInfoRegister";
	}

	@RequestMapping("/ngi/chg/addChangeInfo.do")
	public String addChangeInfo(final MultipartHttpServletRequest multiRequest,
			ChangeInfoVO changeInfoVO, ChangeHistVO changeHistVO, SttemntInfoVO sttemntInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status) throws Exception {
		
		List<FileVO> result = null;
		String atchFileId = "";
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "CHG_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);
		}

		int changeInfoId = changeInfoService.selectChangeInfoMaxid(changeInfoVO);

		changeInfoVO.setChangeInfoId(changeInfoId);
		changeInfoService.insertChangeInfo(changeInfoVO);
		changeHistVO.setRegister(changeInfoVO.getRegisterNm());
		changeHistVO.setChangeInfoId(changeInfoId);
		changeInfoService.insertChangeHistory(changeHistVO);
		String strTelno = sttemntInfoVO.getTelno();
		String strEmail = sttemntInfoVO.getEmail();
		
		EncodeUtil encodeUtil = new EncodeUtil();
		strTelno = new String(strTelno.getBytes("8859_1"),"UTF-8");
		strTelno = URLDecoder.decode(strTelno, "UTF-8");		
		strTelno = encodeUtil.StrtoUni(strTelno);
		strTelno = encodeUtil.UnitoStr(strTelno);		
		
		strEmail = new String(strEmail.getBytes("8859_1"),"UTF-8");
		strEmail = URLDecoder.decode(strEmail, "UTF-8");		
		strEmail = encodeUtil.StrtoUni(strEmail);
		strEmail = encodeUtil.UnitoStr(strEmail);			
		
		AesUtil aesUtil = new AesUtil(128, 10000);
		
		//이메일 암호화
		String enEmail = aesUtil.encryptDecrypy(strEmail, 1);
		String enTelno = aesUtil.encryptDecrypy(strTelno, 1);
		
		sttemntInfoVO.setChangeInfoId(changeInfoId);
		sttemntInfoVO.setTelno(enTelno);
		sttemntInfoVO.setEmail(enEmail);		
		sttemntInfoVO.setAtchFileId(atchFileId);
		changeInfoService.insertSttemntInfo(sttemntInfoVO);

		// 포인트 적용
		ScoreLogVO svo = new ScoreLogVO();
		svo.setUserId(changeInfoVO.getRegisterId());
		svo.setScore(10);
		svo.setScoreCodeTy("I");		
		scoreLogService.insertScoreLog(svo);
		

		// geometry 정보 읽음.
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		WKTReader reader = new WKTReader(geometryFactory);
		String geomStr = sttemntInfoVO.getVectorList();

		// 도형정보 db에 저장.
		
		String[] geomList = geomStr.split("\\|");
		
		for (String data : geomList) {
			
			Geometry geom = reader.read(data);
			
			if(geom != null) {
				String type = geom.getGeometryType();
				ChangeGeomLcVo vo = new ChangeGeomLcVo();
				vo.setChangeInfoId(changeInfoId);
				if (type.equals("Point")) {
					vo.setLocation(geom.toText());
					changeInfoService.insertGeomLcPointInfo(vo);
				} else if (type.equals("LineString")) {
					vo.setLocation(geom.toText());
					changeInfoService.insertGeomLcLnInfo(vo);
				} else if (type.equals("Polygon")) {
					vo.setLocation(geom.toText());
					changeInfoService.insertGeomLcMyeonInfo(vo);
				}
			}
			
		} // 도형정보 db에 저장 끝/.
		status.setComplete();
		return "redirect:/ngi/chg/ChangeInfoList.do";

	}

	@RequestMapping("/ngi/chg/addChangeInfoPhoneJson.do")
	public void addChangeInfoPhoneJson(
			final MultipartHttpServletRequest multiRequest,
			ChangeInfoVO changeInfoVO, ChangeHistVO changeHistVO, SttemntInfoVO sttemntInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status) throws Exception {

		List<FileVO> result = null;
		String atchFileId = "";

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "CHG_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);
		}

		int changeInfoId = changeInfoService.selectChangeInfoMaxid(changeInfoVO);

		changeInfoVO.setChangeInfoId(changeInfoId);
		changeInfoService.insertChangeInfoPhone(changeInfoVO);
		
		changeHistVO.setRegister(changeInfoVO.getRegisterNm());
		changeHistVO.setChangeInfoId(changeInfoId);
		changeInfoService.insertChangeHistory(changeHistVO);
		
		sttemntInfoVO.setChangeInfoId(changeInfoId);
		sttemntInfoVO.setAtchFileId(atchFileId);
		changeInfoService.insertSttemntInfo(sttemntInfoVO);

		// 포인트 적용
		ScoreLogVO svo = new ScoreLogVO();
		svo.setUserId(changeInfoVO.getRegisterId());
		svo.setScore(10);
		svo.setScoreCodeTy("I");		
		scoreLogService.insertScoreLog(svo);
		
		try {
			// geometry 정보 읽음.
			GeometryFactory geometryFactory = JTSFactoryFinder
					.getGeometryFactory();
			WKTReader reader = new WKTReader(geometryFactory);
			String geomStr = sttemntInfoVO.getVectorList();

			// 도형정보 db에 저장.
			String[] geomList = geomStr.split("\\|");
			for (String data : geomList) {
				Geometry geom = reader.read(data);
				if(geom != null) {
					String type = geom.getGeometryType();
					ChangeGeomLcVo vo = new ChangeGeomLcVo();
					vo.setChangeInfoId(changeInfoId);
					if (type.equals("Point")) {
						vo.setLocation(geom.toText());
						changeInfoService.insertGeomLcPointInfo(vo);
					} else if (type.equals("LineString")) {
						vo.setLocation(geom.toText());
						changeInfoService.insertGeomLcLnInfo(vo);
					} else if (type.equals("Polygon")) {
						vo.setLocation(geom.toText());
						changeInfoService.insertGeomLcMyeonInfo(vo);
					}
				}
			} // 도형정보 db에 저장 끝/.

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		status.setComplete();

	}

	@RequestMapping("/ngi/chg/addChangeCntrwkInfoPhone.do")
	public void addChangeCntrwkInfoPhone(
			final MultipartHttpServletRequest multiRequest,
			ChangeInfoVO changeInfoVO, CntrwkInfoVO cntrwkInfoVO,
			SttemntInfoVO sttemntInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status) throws Exception {

		List<FileVO> result = null;
//		String atchFileId = "";

		int changeInfoId = changeInfoService.selectChangeInfoMaxid(changeInfoVO);
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			result = fileUtil.saveCompanyReportFileInf(files, changeInfoId , "CHG_", 0, "", "");			 
//			atchFileId = fileMngService.insertFileInfs(result);
		}



		changeInfoVO.setChangeInfoId(changeInfoId);
		changeInfoService.insertChangeInfoStaff(changeInfoVO);

		if (result != null) {
			for (FileVO fileVO : result) {
		    	TnAtchmnflVO tnAtchmnflVO = new TnAtchmnflVO();
		    	
		    	tnAtchmnflVO.setChangeInfoId(new BigDecimal(changeInfoId));
		    	tnAtchmnflVO.setFileNm(fileVO.getOrignlFileNm());
		    	tnAtchmnflVO.setFileMg(String.valueOf(fileVO.fileMg) );
		    	tnAtchmnflVO.setFlpthNm(fileVO.getStreFileNm());
		    	tnAtchmnflVO.setFileFomCodeTy("01");
		    	 
				tnAtchmnflService.insertTnAtchmnfl(tnAtchmnflVO);
			}
		}
		
		sttemntInfoVO.setChangeInfoId(changeInfoId);
//		sttemntInfoVO.setAtchFileId(atchFileId);
//		changeInfoService.insertSttemntInfo(sttemntInfoVO);

		cntrwkInfoVO.setChangeInfoId(changeInfoId);

		changeInfoService.insertCntrwkInfo(cntrwkInfoVO);

		try {
			// geometry 정보 읽음.
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			WKTReader reader = new WKTReader(geometryFactory);
			String geomStr = sttemntInfoVO.getVectorList();

			// 도형정보 db에 저장.
			String[] geomList = geomStr.split("\\|");
			for (String data : geomList) {
				Geometry geom = reader.read(data);
				String type = geom.getGeometryType();
				ChangeGeomLcVo vo = new ChangeGeomLcVo();
				vo.setChangeInfoId(changeInfoId);
				if (type.equals("Point")) {
					vo.setLocation(geom.toText());
					changeInfoService.insertGeomLcPointInfo(vo);
				} else if (type.equals("LineString")) {
					vo.setLocation(geom.toText());
					changeInfoService.insertGeomLcLnInfo(vo);
				} else if (type.equals("Polygon")) {
					vo.setLocation(geom.toText());
					changeInfoService.insertGeomLcMyeonInfo(vo);
				}
			} // 도형정보 db에 저장 끝/.

		} catch (Exception ex) {

		}

		status.setComplete();

	}

	@RequestMapping("/ngi/chg/ChangeInfoInqire.do")
	public String ChangeInfoInqire(@RequestParam("changeInfoId") int changeInfoId,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			Model model) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		ChangeSttemntInfoVO changeSttemntInfoVO = new ChangeSttemntInfoVO();
		changeSttemntInfoVO.setChangeInfoId(changeInfoId);

		ChangeHistVO changeHistVO = new ChangeHistVO();
		changeHistVO.setChangeInfoId(changeInfoId);

		model.addAttribute("hist_result", changeInfoService.selectChangeHistList(changeHistVO));

		// 변수명은 CoC 에 따라 tnChangeInfoVO
		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("sttemntty_result", cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		model.addAttribute("process_result", cmmUseService.selectCmmCodeDetail(vo));
		
		changeSttemntInfoVO = selectChangeSttemntInfo(changeSttemntInfoVO, searchVO);
		
		// AES 암호화/복호화 셋팅
		AesUtil aesUtil = new AesUtil(128, 10000);
		//이메일, 전화번호 복호화
		changeSttemntInfoVO.setEmail(aesUtil.encryptDecrypy(changeSttemntInfoVO.getEmail(), 2));
		changeSttemntInfoVO.setTelno(aesUtil.encryptDecrypy(changeSttemntInfoVO.getTelno(), 2));
		
		// geometry 정보 가져오기.
		ChangeGeomLcVo geomVo = new ChangeGeomLcVo();
		geomVo.setChangeInfoId(changeInfoId);
		StringBuffer vectorLists = new StringBuffer();
		vectorLists.append("");

		List<ChangeGeomLcVo> pointList = changeInfoService.selectGeomLcPointInfo(geomVo);
		List<ChangeGeomLcVo> lineList = changeInfoService.selectGeomLcLnInfo(geomVo);
		List<ChangeGeomLcVo> polygonList = changeInfoService.selectGeomLcMyeonInfo(geomVo);
		for (ChangeGeomLcVo point : pointList) {
			vectorLists.append(point.getLocation() + "|");
		}
		for (ChangeGeomLcVo line : lineList) {
			vectorLists.append(line.getLocation() + "|");
		}
		for (ChangeGeomLcVo polygon : polygonList) {
			vectorLists.append(polygon.getLocation() + "|");
		}

		if (vectorLists.lastIndexOf("|") > 0)
			vectorLists.deleteCharAt(vectorLists.lastIndexOf("|"));

		changeSttemntInfoVO.setVectorList(vectorLists.toString());
		// geometry 정보 가져오기 끝..

		model.addAttribute(changeSttemntInfoVO);
		return "/ngi/chg/ChangeInfoInqire";
	}

	@RequestMapping("/ngi/chg/ChangeInfoPrint.do")
	public String ChangeInfoPrint(@RequestParam("changeInfoId") int changeInfoId,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			Model model) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		ChangeSttemntInfoVO changeSttemntInfoVO = new ChangeSttemntInfoVO();
		changeSttemntInfoVO.setChangeInfoId(changeInfoId);

		ChangeHistVO changeHistVO = new ChangeHistVO();
		changeHistVO.setChangeInfoId(changeInfoId);

		model.addAttribute("hist_result", changeInfoService.selectChangeHistList(changeHistVO));

		// 변수명은 CoC 에 따라 tnChangeInfoVO
		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("sttemntty_result", cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		model.addAttribute("process_result", cmmUseService.selectCmmCodeDetail(vo));

		changeSttemntInfoVO = selectChangeSttemntInfo(changeSttemntInfoVO, searchVO);

		// geometry 정보 가져오기.
		ChangeGeomLcVo geomVo = new ChangeGeomLcVo();
		geomVo.setChangeInfoId(changeInfoId);
		StringBuffer vectorLists = new StringBuffer();
		vectorLists.append("");

		List<ChangeGeomLcVo> pointList = changeInfoService.selectGeomLcPointInfo(geomVo);
		List<ChangeGeomLcVo> lineList = changeInfoService.selectGeomLcLnInfo(geomVo);
		List<ChangeGeomLcVo> polygonList = changeInfoService.selectGeomLcMyeonInfo(geomVo);
		for (ChangeGeomLcVo point : pointList) {
			vectorLists.append(point.getLocation() + "|");
		}
		for (ChangeGeomLcVo line : lineList) {
			vectorLists.append(line.getLocation() + "|");
		}
		for (ChangeGeomLcVo polygon : polygonList) {
			vectorLists.append(polygon.getLocation() + "|");
		}

		if (vectorLists.lastIndexOf("|") > 0)
			vectorLists.deleteCharAt(vectorLists.lastIndexOf("|"));

		changeSttemntInfoVO.setVectorList(vectorLists.toString());
		// geometry 정보 가져오기 끝..

		model.addAttribute(changeSttemntInfoVO);
		return "/ngi/chg/ChangeInfoPrint";
	}	
	
	@RequestMapping("/ngi/chg/updateChangeInfoView.do")
	public String updateChangeInfoView(
			final MultipartHttpServletRequest multiRequest,
			@RequestParam("changeInfoId") int changeInfoId,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			Model model) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		ChangeSttemntInfoVO changeSttemntInfoVO = new ChangeSttemntInfoVO();
		changeSttemntInfoVO.setChangeInfoId(changeInfoId);

		// 변수명은 CoC 에 따라 tnChangeInfoVO
		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("sttemntty_result",
				cmmUseService.selectCmmCodeDetail(vo));

		changeSttemntInfoVO = selectChangeSttemntInfo(changeSttemntInfoVO,
				searchVO);
		
		// AES 암호화/복호화 셋팅
		AesUtil aesUtil = new AesUtil(128, 10000);
				
		//이메일 암호화
		changeSttemntInfoVO.setEmail(aesUtil.encryptDecrypy(changeSttemntInfoVO.getEmail(), 2));
		changeSttemntInfoVO.setTelno(aesUtil.encryptDecrypy(changeSttemntInfoVO.getTelno(), 2));
		
		String atchFileId = changeSttemntInfoVO.getAtchFileId();

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			if ("".equals(atchFileId)) {
				List<FileVO> result = fileUtil.parseFileInf(files, "CHG_", 0, atchFileId, "");
				atchFileId = fileMngService.insertFileInfs(result);
				changeSttemntInfoVO.setAtchFileId(atchFileId);
			} else {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int cnt = fileMngService.getMaxFileSN(fvo);
				List<FileVO> _result = fileUtil.parseFileInf(files, "CHG_", cnt, atchFileId, "");
				fileMngService.updateFileInfs(_result);
			}
		}

		// geometry 정보 가져오기.
		ChangeGeomLcVo geomVo = new ChangeGeomLcVo();
		geomVo.setChangeInfoId(changeInfoId);
		StringBuffer vectorLists = new StringBuffer();
		vectorLists.append("");

		List<ChangeGeomLcVo> pointList = changeInfoService.selectGeomLcPointInfo(geomVo);
		List<ChangeGeomLcVo> lineList = changeInfoService.selectGeomLcLnInfo(geomVo);
		List<ChangeGeomLcVo> polygonList = changeInfoService.selectGeomLcMyeonInfo(geomVo);
		for (ChangeGeomLcVo point : pointList) {
			vectorLists.append(point.getLocation() + "|");
		}
		for (ChangeGeomLcVo line : lineList) {
			vectorLists.append(line.getLocation() + "|");
		}
		for (ChangeGeomLcVo polygon : polygonList) {
			vectorLists.append(polygon.getLocation() + "|");
		}

		if (vectorLists.lastIndexOf("|") > 0)
			vectorLists.deleteCharAt(vectorLists.lastIndexOf("|"));

		changeSttemntInfoVO.setVectorList(vectorLists.toString());
		// geometry 정보 가져오기 끝..

		model.addAttribute(changeSttemntInfoVO);
		return "/ngi/chg/ChangeInfoUpdt";
	}

	@RequestMapping("/ngi/chg/selectChangeSttemntInfo.do")
	public @ModelAttribute("changeSttemntInfoVO")
	ChangeSttemntInfoVO selectChangeSttemntInfo(
			ChangeSttemntInfoVO changeSttemntInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoService.selectChangeSttemntInfo(changeSttemntInfoVO);
	}

	@RequestMapping("/ngi/chg/selectChangeInfo.do")
	public @ModelAttribute("ChangeInfoVO")
	ChangeInfoVO selectChangeInfo(ChangeInfoVO changeInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoService.selectChangeInfo(changeInfoVO);
	}

	@RequestMapping("/ngi/chg/updateChangeInfo.do")
	public String updateChangeInfo(ChangeInfoVO changeInfoVO,
			SttemntInfoVO sttemntInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status) throws Exception {

		changeInfoService.updateChangeInfo(changeInfoVO);
		
		
		String strTelno = sttemntInfoVO.getTelno();
		String strEmail = sttemntInfoVO.getEmail();
		
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
		
		//이메일, 전화번호 암호화
		sttemntInfoVO.setTelno(aesUtil.encryptDecrypy(strTelno, 1));
		sttemntInfoVO.setEmail(aesUtil.encryptDecrypy(strEmail, 1));		
		
		changeInfoService.updateSttemntInfo(sttemntInfoVO);

		// geometry 정보 읽음.
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		WKTReader reader = new WKTReader(geometryFactory);
		String geomStr = sttemntInfoVO.getVectorList();
		int changeInfoId = sttemntInfoVO.getChangeInfoId();

		// 도형정보 db에서 삭제.
		ChangeGeomLcVo vo = new ChangeGeomLcVo();
		vo.setChangeInfoId(changeInfoId);
		changeInfoService.deleteGeomLcPointInfo(vo); // delete
		changeInfoService.deleteGeomLcLnInfo(vo);
		changeInfoService.deleteGeomLcMyeonInfo(vo);

		// 도형정보 db에 저장.
		String[] geomList = geomStr.split("\\|");
		for (String data : geomList) {
			Geometry geom = reader.read(data);
			String type = geom.getGeometryType();

			if (type.equals("Point")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcPointInfo(vo); // insert
			} else if (type.equals("LineString")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcLnInfo(vo);
			} else if (type.equals("Polygon")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcMyeonInfo(vo);
			}
		} // 도형정보 db에 저장 끝/.

		searchVO = new ChangeInfoDefaultVO();
		status.setComplete();
		return "redirect:/ngi/chg/ChangeInfoList.do";
	}

	@RequestMapping("/ngi/chg/updateChangeInfoMember.do")
	public String updateChangeInfoMember(
			final MultipartHttpServletRequest multiRequest,
			ChangeInfoVO changeInfoVO,
			SttemntInfoVO sttemntInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status) throws Exception {

		changeInfoService.updateChangeInfo(changeInfoVO);
		changeInfoService.updateSttemntInfo(sttemntInfoVO);		
		

		String geomStr = sttemntInfoVO.getVectorList();
		int changeInfoId = sttemntInfoVO.getChangeInfoId();

		ChangeSttemntInfoVO changeSttemntInfoVO = new ChangeSttemntInfoVO();
		changeSttemntInfoVO.setChangeInfoId(changeInfoId);
		
		String atchFileId = sttemntInfoVO.getAtchFileId();

		try {
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				if ("".equals(atchFileId)) {
					List<FileVO> result = fileUtil.parseFileInf(files, "CHG_", 0, atchFileId, "");
					atchFileId = fileMngService.insertFileInfs(result);
					sttemntInfoVO.setAtchFileId(atchFileId);
					changeInfoService.updateSttemntInfo(sttemntInfoVO);
				} else {
					FileVO fvo = new FileVO();
					fvo.setAtchFileId(atchFileId);
					int cnt = 0;
					try {
						cnt = fileMngService.getMaxFileSN(fvo);
					} catch(Exception ex) {
						
					}
					List<FileVO> _result = fileUtil.parseFileInf(files, "CHG_", cnt, atchFileId, "");
					fileMngService.updateFileInfs(_result);
				}
			}
		} catch(Exception ex) {
		
		}
		
		// geometry 정보 읽음.
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		WKTReader reader = new WKTReader(geometryFactory);
		
		// 도형정보 db에서 삭제.
		ChangeGeomLcVo vo = new ChangeGeomLcVo();
		vo.setChangeInfoId(changeInfoId);
		changeInfoService.deleteGeomLcPointInfo(vo); // delete
		changeInfoService.deleteGeomLcLnInfo(vo);
		changeInfoService.deleteGeomLcMyeonInfo(vo);

		// 도형정보 db에 저장.
		String[] geomList = geomStr.split("\\|");
		for (String data : geomList) {
			Geometry geom = reader.read(data);
			String type = geom.getGeometryType();

			if (type.equals("Point")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcPointInfo(vo); // insert
			} else if (type.equals("LineString")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcLnInfo(vo);
			} else if (type.equals("Polygon")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcMyeonInfo(vo);
			}
		} // 도형정보 db에 저장 끝/.

		searchVO = new ChangeInfoDefaultVO();
		status.setComplete();
		return "redirect:/ngi/chg/ChangeInfoList.do";
	}
	
	@RequestMapping("/ngi/chg/updateChangeInfoCompany.do")
	public String updateChangeInfoCompany(
			final MultipartHttpServletRequest multiRequest,
			ChangeInfoVO changeInfoVO,
			SttemntInfoVO sttemntInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status) throws Exception {

		changeInfoService.updateChangeInfo(changeInfoVO);
		//changeInfoService.updateSttemntInfo(sttemntInfoVO);

		List<FileVO> result = null;
//		String atchFileId = "";


		

		String geomStr = sttemntInfoVO.getVectorList();
		int changeInfoId = sttemntInfoVO.getChangeInfoId();
		try {
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				result = fileUtil.saveCompanyReportFileInf(files, changeInfoId , "CHG_", 0, "", "");			 
	//			atchFileId = fileMngService.insertFileInfs(result);
			}

			if (result != null) {
				for (FileVO fileVO : result) {
			    	TnAtchmnflVO tnAtchmnflVO = new TnAtchmnflVO();
			    	
			    	tnAtchmnflVO.setChangeInfoId(new BigDecimal(changeInfoId));
			    	tnAtchmnflVO.setFileNm(fileVO.getOrignlFileNm());
			    	tnAtchmnflVO.setFileMg(String.valueOf(fileVO.fileMg) );
			    	tnAtchmnflVO.setFlpthNm(fileVO.getStreFileNm());
			    	tnAtchmnflVO.setFileFomCodeTy("01");
			    	 
					tnAtchmnflService.insertTnAtchmnfl(tnAtchmnflVO);
				}
			}
		} catch(Exception ex){
			
		}
		
		// geometry 정보 읽음.
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		WKTReader reader = new WKTReader(geometryFactory);
		
		// 도형정보 db에서 삭제.
		ChangeGeomLcVo vo = new ChangeGeomLcVo();
		vo.setChangeInfoId(changeInfoId);
		changeInfoService.deleteGeomLcPointInfo(vo); // delete
		changeInfoService.deleteGeomLcLnInfo(vo);
		changeInfoService.deleteGeomLcMyeonInfo(vo);

		// 도형정보 db에 저장.
		String[] geomList = geomStr.split("\\|");
		for (String data : geomList) {
			Geometry geom = reader.read(data);
			String type = geom.getGeometryType();

			if (type.equals("Point")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcPointInfo(vo); // insert
			} else if (type.equals("LineString")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcLnInfo(vo);
			} else if (type.equals("Polygon")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcMyeonInfo(vo);
			}
		} // 도형정보 db에 저장 끝/.

		searchVO = new ChangeInfoDefaultVO();
		status.setComplete();
		return "redirect:/ngi/chg/ChangeInfoList.do";
	}
	
	/**
	 * tn_change_info 에서 삭제한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 changeInfoVO
	 * @return "/ngi/chg/ChangeInfoList"
	 * @exception Exception
	 */
	@RequestMapping("/ngi/chg/deleteChangeInfo.do")
	public String deleteChangeInfo(ChangeInfoVO changeInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status) throws Exception {
		changeInfoService.deleteChangeInfo(changeInfoVO);

		searchVO = new ChangeInfoDefaultVO();
		status.setComplete();
		return "redirect:/ngi/chg/ChangeInfoList.do";
	}

	// 변동보고 관련 함수 시작

	
	@RequestMapping("/ngi/chg/deleteChangeRegList.do")
	public String deleteChangeRegList(ChangeInfoVO changeInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status) throws Exception {
		changeInfoService.deleteChangeInfo(changeInfoVO);

		searchVO = new ChangeInfoDefaultVO();
		status.setComplete();
		return "redirect:/ngi/chg/ChangeCntrwkRegList.do";
	}
	
	
	
	/**
	 * 변동보고 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 ChangeInfoDefaultVO
	 * @return "/ngi/chg/ChangeInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/ngi/chg/ChangeCntrwkList.do")
	public String selectChangeCntrwkList(@ModelAttribute("searchVO") ChangeInfoVO searchVO, ModelMap model) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		
		/** EgovPropertyService.sample */
		searchVO.setPageUnit(5);
		searchVO.setPageSize(10);

		// 신고분류코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("changeTy_result", cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		model.addAttribute("process_result", cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG017");
		model.addAttribute("search_result", cmmUseService.selectCmmCodeDetail(vo));

		// 소속기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG018");
		model.addAttribute("psitnEngn_result", cmmUseService.selectCmmCodeDetail(vo));

		// 계획기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG019");
		model.addAttribute("planEngn_result", cmmUseService.selectCmmCodeDetail(vo));
		
		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setChangeCl("02");
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List ChangeCntrwkList = changeInfoService.selectChangeCntrwkList(searchVO);
		model.addAttribute("resultList", ChangeCntrwkList);

		int totCnt = changeInfoService.selectChangeCntrwkListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "/ngi/chg/ChangeCntrwkList";
	}

	/**
	 * 변동보고 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 ChangeInfoDefaultVO
	 * @return "/ngi/chg/ChangeInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/ngi/chg/ChangeCntrwkRegList.do")
	public String selectChangeCntrwkRegList(
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			ModelMap model, HttpSession session) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(10);
		searchVO.setPageSize(10);

		// 신고분류코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("changeTy_result",
				cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		model.addAttribute("process_result",
				cmmUseService.selectCmmCodeDetail(vo));

		// 소속기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG018");
		model.addAttribute("psitnEngn_result",
				cmmUseService.selectCmmCodeDetail(vo));

		// 계획기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG019");
		model.addAttribute("planEngn_result",
				cmmUseService.selectCmmCodeDetail(vo));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setChangeCl("02");
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//searchVO.setRegisterId((String)session.getAttribute("sUserId"));
		searchVO.setRegisterId("jbchoi");
		List ChangeCntrwkRegList = changeInfoService.selectChangeCntrwkRegList(searchVO);
		model.addAttribute("resultList", ChangeCntrwkRegList);

		int totCnt = changeInfoService.selectChangeCntrwkRegListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/ngi/chg/ChangeCntrwkRegList";
	}

	@RequestMapping("/ngi/chg/addChangeCntrwkInfo.do")
	public String addChangeCntrwkInfo(
			final MultipartHttpServletRequest multiRequest,
			ChangeInfoVO changeInfoVO, CntrwkInfoVO cntrwkInfoVO,			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status) throws Exception {

		int changeInfoId = changeInfoService.selectChangeInfoMaxid(changeInfoVO);

		changeInfoVO.setChangeInfoId(changeInfoId);
		changeInfoService.insertChangeInfo(changeInfoVO);

		/////Calendar cal = new GregorianCalendar();
		/////int iyear = cal.get(Calendar.YEAR);
		//////String cntrwkNo = Integer.toString(iyear) + String.format("%06d",changeInfoId);
		cntrwkInfoVO.setChangeInfoId(changeInfoId);
		/////cntrwkInfoVO.setCntrwkNo(cntrwkNo);
		
		// AES 암호화/복호화 셋팅
		AesUtil aesUtil = new AesUtil(128, 10000);
		
		//이메일 암호화
		String enEmail = aesUtil.encryptDecrypy(cntrwkInfoVO.getChargerEmail(), 1);
		String enTelno = aesUtil.encryptDecrypy(cntrwkInfoVO.getChargerTlphonNo(), 1);
		
		cntrwkInfoVO.setChargerEmail(enEmail);
		cntrwkInfoVO.setChargerTlphonNo(enTelno);
		
		changeInfoService.insertCntrwkInfo(cntrwkInfoVO);
		
		// geometry 정보 읽음.
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		WKTReader reader = new WKTReader(geometryFactory);
		String geomStr = cntrwkInfoVO.getVectorList();

		// 도형정보 db에서 삭제.
		ChangeGeomLcVo vo = new ChangeGeomLcVo();
		vo.setChangeInfoId(changeInfoId);
		changeInfoService.deleteGeomLcPointInfo(vo); // delete
		changeInfoService.deleteGeomLcLnInfo(vo);
		changeInfoService.deleteGeomLcMyeonInfo(vo);

		// 도형정보 db에 저장.
		String[] geomList = geomStr.split("\\|");
		for (String data : geomList) {
			Geometry geom = reader.read(data);
			
			if(geom != null) {
				String type = geom.getGeometryType();
	
				if (type.equals("Point")) {
					vo.setLocation(geom.toText());
					changeInfoService.insertGeomLcPointInfo(vo); // insert
				} else if (type.equals("LineString")) {
					vo.setLocation(geom.toText());
					changeInfoService.insertGeomLcLnInfo(vo);
				} else if (type.equals("Polygon")) {
					vo.setLocation(geom.toText());
					changeInfoService.insertGeomLcMyeonInfo(vo);
				}
			}
		} // 도형정보 db에 저장 끝/.
		

		status.setComplete();
		return "redirect:/ngi/chg/ChangeCntrwkRegList.do";
		//return "forward:/ngi/chg/addChangeCntrwkView.do";
	}

	@RequestMapping("/ngi/chg/addChangeCntrwkInfoPre.do")
	public String addChangeCntrwkInfoPre(
			final MultipartHttpServletRequest multiRequest,
			ChangeInfoVO changeInfoVO, CntrwkInfoVO cntrwkInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status,Model model) throws Exception {

		int changeInfoId = changeInfoService.selectChangeInfoMaxid(changeInfoVO);

		changeInfoVO.setChangeInfoId(changeInfoId);
		changeInfoService.insertChangeInfo(changeInfoVO);

		//Calendar cal = new GregorianCalendar();
		//int iyear = cal.get(Calendar.YEAR);
		//String cntrwkNo = Integer.toString(iyear) + String.format("%06d",changeInfoId);
		cntrwkInfoVO.setChangeInfoId(changeInfoId);
		//cntrwkInfoVO.setCntrwkNo(cntrwkNo);
		changeInfoService.insertCntrwkInfo(cntrwkInfoVO);
		
		// geometry 정보 읽음.
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		WKTReader reader = new WKTReader(geometryFactory);
		String geomStr = cntrwkInfoVO.getVectorList();

		// 도형정보 db에서 삭제.
		ChangeGeomLcVo vo = new ChangeGeomLcVo();
		vo.setChangeInfoId(changeInfoId);
		changeInfoService.deleteGeomLcPointInfo(vo); // delete
		changeInfoService.deleteGeomLcLnInfo(vo);
		changeInfoService.deleteGeomLcMyeonInfo(vo);

		// 도형정보 db에 저장.
		String[] geomList = geomStr.split("\\|");
		for (String data : geomList) {
			Geometry geom = reader.read(data);
			if(geom != null) {
				String type = geom.getGeometryType();
	
				if (type.equals("Point")) {
					vo.setLocation(geom.toText());
					changeInfoService.insertGeomLcPointInfo(vo); // insert
				} else if (type.equals("LineString")) {
					vo.setLocation(geom.toText());
					changeInfoService.insertGeomLcLnInfo(vo);
				} else if (type.equals("Polygon")) {
					vo.setLocation(geom.toText());
					changeInfoService.insertGeomLcMyeonInfo(vo);
				}
			}
		} // 도형정보 db에 저장 끝/.
		


		
		ComDefaultCodeVO cvo = new ComDefaultCodeVO();

		ChangeCntrwkInfoVO changeCntrwkInfoVO = new ChangeCntrwkInfoVO();
		changeCntrwkInfoVO.setChangeInfoId(changeInfoId);

		// 변수명은 CoC 에 따라 tnChangeInfoVO
		// 신고유형코드를 코드정보로부터 조회
		cvo.setCodeId("CHG003");
		model.addAttribute("changeTy_result", cmmUseService.selectCmmCodeDetail(cvo));

		// 처리상태코드를 코드정보로부터 조회
		cvo.setCodeId("CHG004");
		model.addAttribute("process_result", cmmUseService.selectCmmCodeDetail(cvo));

		// 좌표계코드를 코드정보로부터 조회
		cvo.setCodeId("CHG010");
		model.addAttribute("cntm_result", cmmUseService.selectCmmCodeDetail(cvo));

		// 취득방법코드를 코드정보로부터 조회
		cvo.setCodeId("CHG021");
		model.addAttribute("acqsMthTy_result", cmmUseService.selectCmmCodeDetail(cvo));

		// 소속기관코드를 코드정보로부터 조회
		cvo.setCodeId("CHG018");
		model.addAttribute("psitnEngn_result", cmmUseService.selectCmmCodeDetail(cvo));

		// 계획기관코드를 코드정보로부터 조회
		cvo.setCodeId("CHG019");
		model.addAttribute("planEngn_result", cmmUseService.selectCmmCodeDetail(cvo));
		
		// 관리기관코드를 코드정보로부터 조회
		cvo.setCodeId("CHG020");
		model.addAttribute("mngEngn_result", cmmUseService.selectCmmCodeDetail(cvo));		

		changeCntrwkInfoVO = selectChangeCntrwkInfo(changeCntrwkInfoVO, searchVO);
		
		// geometry 정보 가져오기.
		ChangeGeomLcVo geomVo = new ChangeGeomLcVo();
		geomVo.setChangeInfoId(changeInfoId);
		StringBuffer vectorLists = new StringBuffer();
		vectorLists.append("");

		List<ChangeGeomLcVo> pointList = changeInfoService.selectGeomLcPointInfo(geomVo);
		List<ChangeGeomLcVo> lineList = changeInfoService.selectGeomLcLnInfo(geomVo);
		List<ChangeGeomLcVo> polygonList = changeInfoService.selectGeomLcMyeonInfo(geomVo);
		for (ChangeGeomLcVo point : pointList) {
			vectorLists.append(point.getLocation() + "|");
		}
		for (ChangeGeomLcVo line : lineList) {
			vectorLists.append(line.getLocation() + "|");
		}
		for (ChangeGeomLcVo polygon : polygonList) {
			vectorLists.append(polygon.getLocation() + "|");
		}

		if (vectorLists.lastIndexOf("|") > 0)
			vectorLists.deleteCharAt(vectorLists.lastIndexOf("|"));

		changeCntrwkInfoVO.setVectorList(vectorLists.toString());
		// geometry 정보 가져오기 끝..

		model.addAttribute(changeCntrwkInfoVO);
		return "/ngi/chg/ChangeCntrwkRegUpdt";		
		
	}	
	
	private String getCellString(Object object) {
		String resultValue = null;
		if( object instanceof org.apache.poi.hssf.usermodel.HSSFCell ) { // xls
			HSSFCell cell = (HSSFCell) object;
			if(cell != null){
				
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC:
					resultValue = String.valueOf(cell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					//logger.error("CELL_TYPE_FORMULA - " + cell.getNumericCellValue());
					resultValue = String.valueOf(cell.getNumericCellValue());
	                break;
	            case HSSFCell.CELL_TYPE_STRING:
//	        	  	logger.error("CELL_TYPE_STRING - " + cell.getStringCellValue());
	            	resultValue = cell.getStringCellValue();
	                break;
	            case HSSFCell.CELL_TYPE_BLANK:
//	            	logger.error("CELL_TYPE_BLANK - " + cell.getBooleanCellValue());
	            	resultValue = "";
	                break;
	            case HSSFCell.CELL_TYPE_ERROR :
//	            	logger.error("CELL_TYPE_ERROR - " + cell.getErrorCellValue());
	            	resultValue  = null;
	                break;
	            default:
	            	break;
				}
			}
		} else if ( object instanceof org.apache.poi.xssf.usermodel.XSSFCell  ) { // xlsx
			XSSFCell cell = (XSSFCell) object;
			if(cell != null){
				
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_NUMERIC:
					resultValue = String.valueOf(cell.getNumericCellValue());
					break;
				case XSSFCell.CELL_TYPE_FORMULA:
					//logger.error("CELL_TYPE_FORMULA - " + cell.getNumericCellValue());
					resultValue = String.valueOf(cell.getNumericCellValue());
	                break;
	            case XSSFCell.CELL_TYPE_STRING:
//	        	  	logger.error("CELL_TYPE_STRING - " + cell.getStringCellValue());
	            	resultValue = cell.getStringCellValue();
	                break;
	            case XSSFCell.CELL_TYPE_BLANK:
//	            	logger.error("CELL_TYPE_BLANK - " + cell.getBooleanCellValue());
	            	resultValue = "";
	                break;
	            case XSSFCell.CELL_TYPE_ERROR :
//	            	logger.error("CELL_TYPE_ERROR - " + cell.getErrorCellValue());
	            	resultValue  = null;
	                break;
	            default:
	            	break;
				}
			}
		}
		
		return resultValue;
	}
	
	private ModelMap excelCellMakeToInsertVO(Object object) throws Exception {
		
		ModelMap modelMap = null;
		ChangeInfoVO changeInfoVO = null;
		CntrwkInfoVO cntrwkInfoVO = null;
		
		if( object instanceof org.apache.poi.hssf.usermodel.HSSFRow ) { // xls
			
			modelMap = new ModelMap();
			changeInfoVO = new ChangeInfoVO();
			cntrwkInfoVO = new CntrwkInfoVO();
			HSSFRow row = (HSSFRow)object;
			
			ComDefaultCodeVO vo = null;
			
			//변경정보.
	    	changeInfoVO.setChangeCl("02");	// 
	    	changeInfoVO.setProcessSttusSe("01");	// 처리상태. changeInfoVO.setProcessSttusSe(getCellString(row.getCell(1)));

	    	vo = new ComDefaultCodeVO();
	    	vo.setCodeId("CHG023");
			vo.setCodeNm(getCellString(row.getCell(7)));
			changeInfoVO.setAddrTy( cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 주소유형
			
	    	changeInfoVO.setChangeSj(getCellString(row.getCell(6)));		// 공사명.
	    	changeInfoVO.setDelSe("0");
	    	changeInfoVO.setRegistPath("1");
	    	
	    	//공사정보
	    	cntrwkInfoVO.setTrgetAreaNm(getCellString(row.getCell(8)));  //대상지역
	    	cntrwkInfoVO.setCntrwkPnttm(getCellString(row.getCell(9)));	 //공사시점.
	    	cntrwkInfoVO.setCntrwkTmnl(getCellString(row.getCell(10)));	// 공사종점
	    	
	    	vo = new ComDefaultCodeVO();
	    	
	    	vo.setCodeId("CHG001");
			vo.setCodeNm(getCellString(row.getCell(1)));
	    	cntrwkInfoVO.setChangeSe(cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 변동여부.
	    	
	    	vo.setCodeId("CHG003");
			vo.setCodeNm(getCellString(row.getCell(5)));
	    	cntrwkInfoVO.setChangeTy( cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 변동유형.
	    	
	    	vo = new ComDefaultCodeVO();
	    	vo.setCodeId("CHG018");
			vo.setCodeNm(getCellString(row.getCell(2)));
	    	cntrwkInfoVO.setPsitnEngnNo( cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 소속기관.
	    	
	    	vo = new ComDefaultCodeVO();
	    	vo.setCodeId("CHG019");
			vo.setCodeNm(getCellString(row.getCell(3)));
	    	cntrwkInfoVO.setPlanEngnNo( cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 계획기관.
	    	
	    	vo = new ComDefaultCodeVO();
	    	vo.setCodeId("CHG020");
			vo.setCodeNm(getCellString(row.getCell(4)));
	    	cntrwkInfoVO.setMngEngnNo( cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 감독기관.
	    	
	    	cntrwkInfoVO.setAr(getCellString(row.getCell(11)));	// 면적.
	    	cntrwkInfoVO.setExtn(getCellString(row.getCell(12)));	// 연장.
	    	cntrwkInfoVO.setTrgetBfchgCn(getCellString(row.getCell(13)));	//  대상변경전.
	    	cntrwkInfoVO.setTrgetAftchCn(getCellString(row.getCell(14)));	//  대상변경후 .
	    	
	    	cntrwkInfoVO.setStrwrkDe(getCellString(row.getCell(15)).isEmpty() ? null : new SimpleDateFormat("yyyy/MM/dd").parse(getCellString(row.getCell(15))) );	// 착공일..
	    	cntrwkInfoVO.setCompetPrearngeDe(getCellString(row.getCell(16)).isEmpty() ? null : new SimpleDateFormat("yyyy/MM/dd").parse(getCellString(row.getCell(16))));	// 완공예정...
	    	cntrwkInfoVO.setLastCompetDe(getCellString(row.getCell(17)).isEmpty() ? null : new SimpleDateFormat("yyyy/MM/dd").parse(getCellString(row.getCell(17))));	// 최종완공일..
	    	
	    	cntrwkInfoVO.setChrgDeptNm(getCellString(row.getCell(18)));	// 부서명.
	    	cntrwkInfoVO.setChargerNm(getCellString(row.getCell(19)));	// 담당자명.
	    	
	    	cntrwkInfoVO.setChargerTlphonNo(getCellString(row.getCell(20)));	// tel
	    	cntrwkInfoVO.setChargerEmail(getCellString(row.getCell(21)));	// email.
	    	
	    	vo = new ComDefaultCodeVO();
	    	vo.setCodeId("CHG010");
			vo.setCodeNm(getCellString(row.getCell(22)));
	    	cntrwkInfoVO.setCntm( cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 좌표계 .
	    	
	    	cntrwkInfoVO.setRm(getCellString(row.getCell(23)));	// 비고
	    	
	    	modelMap.addAttribute("changeInfoVO", changeInfoVO);
	    	modelMap.addAttribute("cntrwkInfoVO", cntrwkInfoVO);
	    	
		} else if ( object instanceof org.apache.poi.xssf.usermodel.XSSFRow ) { // xlsx
			
			modelMap = new ModelMap();
			changeInfoVO = new ChangeInfoVO();
			cntrwkInfoVO = new CntrwkInfoVO();
			XSSFRow row = (XSSFRow)object;
			
			ComDefaultCodeVO vo = null;
			
			//변경정보.
	    	changeInfoVO.setChangeCl("02");	// 
	    	changeInfoVO.setProcessSttusSe("01");	// 처리상태. changeInfoVO.setProcessSttusSe(getCellString(row.getCell(1)));
	    	
	    	vo = new ComDefaultCodeVO();
	    	vo.setCodeId("CHG023");
			vo.setCodeNm(getCellString(row.getCell(7)));
			changeInfoVO.setAddrTy( cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 주소유형

	    	changeInfoVO.setChangeSj(getCellString(row.getCell(6)));		// 공사명.
	    	changeInfoVO.setDelSe("0");
	    	changeInfoVO.setRegistPath("1");
	    	
	    	//공사정보
	    	cntrwkInfoVO.setTrgetAreaNm(getCellString(row.getCell(8)));  //대상지역
	    	cntrwkInfoVO.setCntrwkPnttm(getCellString(row.getCell(9)));	 //공사시점.
	    	cntrwkInfoVO.setCntrwkTmnl(getCellString(row.getCell(10)));	// 공사종점
	    	
	    	vo = new ComDefaultCodeVO();
	    	
	    	vo.setCodeId("CHG001");
			vo.setCodeNm(getCellString(row.getCell(1)));
	    	cntrwkInfoVO.setChangeSe(cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 변동여부.
	    	
	    	vo.setCodeId("CHG003");
			vo.setCodeNm(getCellString(row.getCell(5)));
	    	cntrwkInfoVO.setChangeTy( cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 변동유형.
	    	
	    	vo = new ComDefaultCodeVO();
	    	vo.setCodeId("CHG018");
			vo.setCodeNm(getCellString(row.getCell(2)));
	    	cntrwkInfoVO.setPsitnEngnNo( cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 소속기관.
	    	
	    	vo = new ComDefaultCodeVO();
	    	vo.setCodeId("CHG019");
			vo.setCodeNm(getCellString(row.getCell(3)));
	    	cntrwkInfoVO.setPlanEngnNo( cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 계획기관.
	    	
	    	vo = new ComDefaultCodeVO();
	    	vo.setCodeId("CHG020");
			vo.setCodeNm(getCellString(row.getCell(4)));
	    	cntrwkInfoVO.setMngEngnNo( cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 감독기관.
	    	
	    	cntrwkInfoVO.setAr(getCellString(row.getCell(11)));	// 면적.
	    	cntrwkInfoVO.setExtn(getCellString(row.getCell(12)));	// 연장.
	    	cntrwkInfoVO.setTrgetBfchgCn(getCellString(row.getCell(13)));	//  대상변경전.
	    	cntrwkInfoVO.setTrgetAftchCn(getCellString(row.getCell(14)));	//  대상변경후 .
	    	cntrwkInfoVO.setStrwrkDe(getCellString(row.getCell(15)).isEmpty() ? null : new SimpleDateFormat("yyyy/MM/dd").parse(getCellString(row.getCell(15))) );	// 착공일..
	    	cntrwkInfoVO.setCompetPrearngeDe(getCellString(row.getCell(16)).isEmpty() ? null : new SimpleDateFormat("yyyy/MM/dd").parse(getCellString(row.getCell(16))));	// 완공예정...
	    	cntrwkInfoVO.setLastCompetDe(getCellString(row.getCell(17)).isEmpty() ? null : new SimpleDateFormat("yyyy/MM/dd").parse(getCellString(row.getCell(17))));	// 최종완공일..
	    	
	    	cntrwkInfoVO.setChrgDeptNm(getCellString(row.getCell(18)));	// 부서명.
	    	cntrwkInfoVO.setChargerNm(getCellString(row.getCell(19)));	// 담당자명.
	    	
	    	cntrwkInfoVO.setChargerTlphonNo(getCellString(row.getCell(20)));	// tel
	    	cntrwkInfoVO.setChargerEmail(getCellString(row.getCell(21)));	// email.
	    	
	    	vo = new ComDefaultCodeVO();
	    	vo.setCodeId("CHG010");
			vo.setCodeNm(getCellString(row.getCell(22)));
	    	cntrwkInfoVO.setCntm( cmmUseService.selectCmmCodeDetail(vo).get(0).getCode() );	// 좌표계 .
	    	
	    	cntrwkInfoVO.setRm(getCellString(row.getCell(23)));	// 비고
	    	
	    	modelMap.addAttribute("changeInfoVO", changeInfoVO);
	    	modelMap.addAttribute("cntrwkInfoVO", cntrwkInfoVO);
	    	
		}
		
		return modelMap;
	}
	
	@RequestMapping("/ngi/chg/addChangeCntrwkExcelInfo.do")
    public String addChangeCntrwkExcelInfo(final MultipartHttpServletRequest multiRequest, SessionStatus status, HttpServletRequest request,
            @ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO, ModelMap model, HttpSession session)
            throws Exception {
		//LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		List<FileVO> fileList = null;
		FileVO fileVO = null;
		int fileRowCount = 0; // 처리된 행 수.
		
		// AES 암호화/복호화 셋팅
		AesUtil aesUtil = new AesUtil(128, 10000);
		
		if(!files.isEmpty()){
			fileList = fileUtil.parseFileInf(files, "temp_excel_", 0, "", "");
			fileVO = fileList.get(0);
			
			if( fileVO != null ) {
				POIFSFileSystem fileSystem = null;
  				HSSFWorkbook xlsWork = null;	// xls
  				XSSFWorkbook xlsxWork = null;	// xlsx
  				
  				String excelFile = fileVO.getFileStreCours()+fileVO.getStreFileNm();
  				
  				try {
  					if( fileVO.getFileExtsn().equals("xls") ) {
  						fileSystem = new POIFSFileSystem(new FileInputStream(new File(excelFile)));
  						xlsWork = new HSSFWorkbook(fileSystem);
  						int sheetNum = xlsWork.getNumberOfSheets();
  						
  						if(sheetNum>0) {
							HSSFSheet sheet = xlsWork.getSheetAt(0);
							int rows = sheet.getPhysicalNumberOfRows();

							for( int rownum = 2; rownum < rows; rownum++){
								
								HSSFRow row = sheet.getRow(rownum);
								if(row != null){
									int cells = row.getPhysicalNumberOfCells();
								     
								    if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
								    	
								    	model.addAttribute("message", "\n# row = " + row.getRowNum() );// + " / cells = " + cells);
								    	
								    	ModelMap voModel = excelCellMakeToInsertVO(row);
								    	
								    	int changeInfoId = changeInfoService.selectChangeInfoMaxid(new ChangeInfoVO());
										
								    	ChangeInfoVO changeInfoVO = (ChangeInfoVO) voModel.get("changeInfoVO");
								    	CntrwkInfoVO cntrwkInfoVO = (CntrwkInfoVO) voModel.get("cntrwkInfoVO");
										
										changeInfoVO.setChangeInfoId(changeInfoId);
								    	//changeInfoVO.setRegisterId((String)session.getAttribute("sUserId"));
								    	//changeInfoVO.setRegisterNm((String)session.getAttribute("sUserNm"));
										changeInfoVO.setRegisterId("jbchoi");
								    	changeInfoVO.setRegisterNm("이경민");
								    	
								    	cntrwkInfoVO.setChangeInfoId(changeInfoId);
								    	cntrwkInfoVO.setRegistTmpCd("1");
								    	
										//이메일/전화번호 암호화
										cntrwkInfoVO.setChargerEmail(aesUtil.encryptDecrypy(cntrwkInfoVO.getChargerEmail(), 1));
										cntrwkInfoVO.setChargerTlphonNo(aesUtil.encryptDecrypy(cntrwkInfoVO.getChargerTlphonNo(), 1));
										
										
										// 특수문자를 html특수문자로 DB저장
										//EgovStringUtil egovString = new EgovStringUtil();
										//changeInfoVO.setChangeSj(egovString.getSpclStrCnvr(changeInfoVO.getChangeSj()));
										//cntrwkInfoVO.setRm(egovString.getSpclStrCnvr(cntrwkInfoVO.getRm()));

								    	
								    	changeInfoService.insertChangeInfo(changeInfoVO);
										changeInfoService.insertCntrwkInfo(cntrwkInfoVO);
										fileRowCount++;
								    } else {
								    	break;
								    }
								}
							}
  						}
//						model.addAttribute("filetype", "xls" );
//      				  	model.addAttribute("filename", excelFile );
  				  	} else if ( fileVO.getFileExtsn().equals("xlsx") ) {
  				  		xlsxWork = new XSSFWorkbook(new FileInputStream(new File(excelFile)));

						XSSFSheet sheet = xlsxWork.getSheetAt(0);

						int rows = sheet.getPhysicalNumberOfRows();

						for (int rownum = 2; rownum < rows; rownum++) {
							XSSFRow row = sheet.getRow(rownum);

							if (row != null) {
								int cells = row.getPhysicalNumberOfCells();

								if(row.getCell(1).getCellType() == XSSFCell.CELL_TYPE_STRING) {
									
									model.addAttribute("message", "\n# row = " + row.getRowNum() );// + " / cells = " + cells);
							    	
							    	ModelMap voModel = excelCellMakeToInsertVO(row);
							    	
							    	int changeInfoId = changeInfoService.selectChangeInfoMaxid(new ChangeInfoVO());
									
							    	ChangeInfoVO changeInfoVO = (ChangeInfoVO) voModel.get("changeInfoVO");
							    	CntrwkInfoVO cntrwkInfoVO = (CntrwkInfoVO) voModel.get("cntrwkInfoVO");
									
							    	changeInfoVO.setChangeInfoId(changeInfoId);
							    	changeInfoVO.setRegisterId((String)session.getAttribute("sUserId"));
							    	changeInfoVO.setRegisterNm((String)session.getAttribute("sUserNm"));
							    	
							    	cntrwkInfoVO.setChangeInfoId(changeInfoId);
							    	cntrwkInfoVO.setRegistTmpCd("1");
							    	
							    	changeInfoService.insertChangeInfo(changeInfoVO);
									changeInfoService.insertCntrwkInfo(cntrwkInfoVO);
									
							    	fileRowCount++;
								}
								else {
									break;
								}
							}
						}
  				  	}
  					model.addAttribute("filetype", "xlsx" );
				  	model.addAttribute("filename", excelFile );
				  	model.addAttribute("fileRowCount", fileRowCount);
				  	model.addAttribute("message", "\n# row = " + fileRowCount);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("filetype", "xlsx" );
				  	model.addAttribute("filename", excelFile );
				  	model.addAttribute("fileRowCount", fileRowCount);
					model.addAttribute("exception", e.getMessage());
				}
			}
		}

		/**
		
		int changeInfoId = changeInfoService.selectChangeInfoMaxid(changeInfoVO);

		changeInfoVO.setChangeInfoId(changeInfoId);
		changeInfoService.insertChangeInfo(changeInfoVO);

		cntrwkInfoVO.setChangeInfoId(changeInfoId);

		changeInfoService.insertCntrwkInfo(cntrwkInfoVO);
		
		 */
        status.setComplete();
        
        return "/ngi/chg/ChangeCntrwkExcelDone";
    }     

	@RequestMapping("/ngi/chg/updateChangeCntrwkInfo.do")
	public String updateChangeCntrwkInfo(ChangeInfoVO changeInfoVO,
			CntrwkInfoVO cntrwkInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status) throws Exception {

		// AES 암호화/복호화 셋팅
		AesUtil aesUtil = new AesUtil(128, 10000);
				
		//이메일 암호화
		String enEmail = aesUtil.encryptDecrypy(cntrwkInfoVO.getChargerEmail(), 1);
		String enTelno = aesUtil.encryptDecrypy(cntrwkInfoVO.getChargerTlphonNo(), 1);
		cntrwkInfoVO.setChargerEmail(enEmail);
		cntrwkInfoVO.setChargerTlphonNo(enTelno);
		
		changeInfoService.updateChangeInfo(changeInfoVO);
		changeInfoService.updateCntrwkInfo(cntrwkInfoVO);

		// geometry 정보 읽음.
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		WKTReader reader = new WKTReader(geometryFactory);
		String geomStr = cntrwkInfoVO.getVectorList();
		int changeInfoId = cntrwkInfoVO.getChangeInfoId();

		// 도형정보 db에서 삭제.
		ChangeGeomLcVo vo = new ChangeGeomLcVo();
		vo.setChangeInfoId(changeInfoId);
		changeInfoService.deleteGeomLcPointInfo(vo); // delete
		changeInfoService.deleteGeomLcLnInfo(vo);
		changeInfoService.deleteGeomLcMyeonInfo(vo);

		// 도형정보 db에 저장.
		String[] geomList = geomStr.split("\\|");
		for (String data : geomList) {
			Geometry geom = reader.read(data);
			String type = geom.getGeometryType();

			if (type.equals("Point")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcPointInfo(vo); // insert
			} else if (type.equals("LineString")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcLnInfo(vo);
			} else if (type.equals("Polygon")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcMyeonInfo(vo);
			}
		} // 도형정보 db에 저장 끝/.
		
		searchVO = new ChangeInfoDefaultVO();
		status.setComplete();
		return "forward:/ngi/chg/ChangeCntrwkList.do";
	}

	@RequestMapping("/ngi/chg/updateChangeCntrwkRegInfo.do")
	public String updateChangeCntrwkRegInfo(ChangeInfoVO changeInfoVO,
			CntrwkInfoVO cntrwkInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status) throws Exception {

		changeInfoService.updateChangeInfo(changeInfoVO);
		cntrwkInfoVO.setRegistTmpCd("1");
		changeInfoService.updateCntrwkInfo(cntrwkInfoVO);
		
		// geometry 정보 읽음.
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		WKTReader reader = new WKTReader(geometryFactory);
		String geomStr = cntrwkInfoVO.getVectorList();
		int changeInfoId = cntrwkInfoVO.getChangeInfoId();

		// 도형정보 db에서 삭제.
		ChangeGeomLcVo vo = new ChangeGeomLcVo();
		vo.setChangeInfoId(changeInfoId);
		changeInfoService.deleteGeomLcPointInfo(vo); // delete
		changeInfoService.deleteGeomLcLnInfo(vo);
		changeInfoService.deleteGeomLcMyeonInfo(vo);

		// 도형정보 db에 저장.
		String[] geomList = geomStr.split("\\|");
		for (String data : geomList) {
			Geometry geom = reader.read(data);
			String type = geom.getGeometryType();

			if (type.equals("Point")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcPointInfo(vo); // insert
			} else if (type.equals("LineString")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcLnInfo(vo);
			} else if (type.equals("Polygon")) {
				vo.setLocation(geom.toText());
				changeInfoService.insertGeomLcMyeonInfo(vo);
			}
		} // 도형정보 db에 저장 끝/.

		searchVO = new ChangeInfoDefaultVO();
		status.setComplete();
		return "redirect:/ngi/chg/ChangeCntrwkRegList.do";
	}

	@RequestMapping("/ngi/chg/updateChangeCntrwkRegComInfo.do")
	public String updateChangeCntrwkRegComInfo(
			@RequestParam("selChangeInfoIds") String selChangeInfoIds, 
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			SessionStatus status) throws Exception {

		changeInfoService.updateCntrwkRegComInfo(selChangeInfoIds);

		searchVO = new ChangeInfoDefaultVO();
		status.setComplete();
		return "forward:/ngi/chg/ChangeCntrwkRegList.do";
	}	
	
	
	@RequestMapping("/ngi/chg/selectChangeCntrwkInfo.do")
	public @ModelAttribute("changeCntrwkInfoVO")
	ChangeCntrwkInfoVO selectChangeCntrwkInfo(
			ChangeCntrwkInfoVO changeCntrwkInfoVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoService.selectChangeCntrwkInfo(changeCntrwkInfoVO);
	}

	@RequestMapping("/ngi/chg/ChangeCntrwkInqire.do")
	public String ChangeCntrwkInqire(
			//final MultipartHttpServletRequest multiRequest,
			@RequestParam("changeInfoId") int changeInfoId,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			Model model) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		ChangeCntrwkInfoVO changeCntrwkInfoVO = new ChangeCntrwkInfoVO();
		changeCntrwkInfoVO.setChangeInfoId(changeInfoId);

		ChangeHistVO changeHistVO = new ChangeHistVO();
		changeHistVO.setChangeInfoId(changeInfoId);

		model.addAttribute("hist_result", changeInfoService.selectChangeHistList(changeHistVO));

		// 변수명은 CoC 에 따라 tnChangeInfoVO

		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("changeTy_result", cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		model.addAttribute("process_result", cmmUseService.selectCmmCodeDetail(vo));

		// 좌표계코드를 코드정보로부터 조회
		vo.setCodeId("CHG010");
		model.addAttribute("cntm_result", cmmUseService.selectCmmCodeDetail(vo));

		// 취득방법코드를 코드정보로부터 조회
		vo.setCodeId("CHG021");
		model.addAttribute("acqsMthTy_result", cmmUseService.selectCmmCodeDetail(vo));

		// 소속기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG018");
		model.addAttribute("psitnEngn_result", cmmUseService.selectCmmCodeDetail(vo));

		// 계획기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG019");
		model.addAttribute("planEngn_result", cmmUseService.selectCmmCodeDetail(vo));
		
		// 관리기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG020");
		model.addAttribute("mngEngn_result", cmmUseService.selectCmmCodeDetail(vo));	
		
		// geometry 정보 가져오기.
		ChangeGeomLcVo geomVo = new ChangeGeomLcVo();
		geomVo.setChangeInfoId(changeInfoId);
		StringBuffer vectorLists = new StringBuffer();
		vectorLists.append("");

		List<ChangeGeomLcVo> pointList = changeInfoService.selectGeomLcPointInfo(geomVo);
		List<ChangeGeomLcVo> lineList = changeInfoService.selectGeomLcLnInfo(geomVo);
		List<ChangeGeomLcVo> polygonList = changeInfoService.selectGeomLcMyeonInfo(geomVo);
		for (ChangeGeomLcVo point : pointList) {
			vectorLists.append(point.getLocation() + "|");
		}
		for (ChangeGeomLcVo line : lineList) {
			vectorLists.append(line.getLocation() + "|");
		}
		for (ChangeGeomLcVo polygon : polygonList) {
			vectorLists.append(polygon.getLocation() + "|");
		}

		if (vectorLists.lastIndexOf("|") > 0)
			vectorLists.deleteCharAt(vectorLists.lastIndexOf("|"));

		// geometry 정보 가져오기 끝..
		
		changeCntrwkInfoVO = selectChangeCntrwkInfo(changeCntrwkInfoVO, searchVO);
		changeCntrwkInfoVO.setVectorList(vectorLists.toString());
		
		// AES 암호화/복호화 셋팅
		AesUtil aesUtil = new AesUtil(128, 10000);
		
		// 이메일, 전화번호 복호화
		changeCntrwkInfoVO.setChargerEmail(aesUtil.encryptDecrypy(changeCntrwkInfoVO.getChargerEmail(), 2));
		changeCntrwkInfoVO.setChargerTlphonNo(aesUtil.encryptDecrypy(changeCntrwkInfoVO.getChargerTlphonNo(), 2));
		
		model.addAttribute(changeCntrwkInfoVO);
		return "/ngi/chg/ChangeCntrwkInqire";
	}

	@RequestMapping("/ngi/chg/updateChangeCntrwkView.do")
	public String updateChangeCntrwkView(
			final MultipartHttpServletRequest multiRequest,
			@RequestParam("changeInfoId") int changeInfoId,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			Model model) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		ChangeCntrwkInfoVO changeCntrwkInfoVO = new ChangeCntrwkInfoVO();
		changeCntrwkInfoVO.setChangeInfoId(changeInfoId);

		// 변수명은 CoC 에 따라 tnChangeInfoVO
		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("changeTy_result", cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		model.addAttribute("process_result", cmmUseService.selectCmmCodeDetail(vo));

		// 좌표계코드를 코드정보로부터 조회
		vo.setCodeId("CHG010");
		model.addAttribute("cntm_result", cmmUseService.selectCmmCodeDetail(vo));

		// 취득방법코드를 코드정보로부터 조회
		vo.setCodeId("CHG021");
		model.addAttribute("acqsMthTy_result", cmmUseService.selectCmmCodeDetail(vo));

		// 소속기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG018");
		model.addAttribute("psitnEngn_result", cmmUseService.selectCmmCodeDetail(vo));

		// 계획기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG019");
		model.addAttribute("planEngn_result", cmmUseService.selectCmmCodeDetail(vo));
		
		// 관리기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG020");
		model.addAttribute("mngEngn_result", cmmUseService.selectCmmCodeDetail(vo));		

		// geometry 정보 가져오기.
		ChangeGeomLcVo geomVo = new ChangeGeomLcVo();
		geomVo.setChangeInfoId(changeInfoId);
		StringBuffer vectorLists = new StringBuffer();
		vectorLists.append("");
	
		List<ChangeGeomLcVo> pointList = changeInfoService.selectGeomLcPointInfo(geomVo);
		List<ChangeGeomLcVo> lineList = changeInfoService.selectGeomLcLnInfo(geomVo);
		List<ChangeGeomLcVo> polygonList = changeInfoService.selectGeomLcMyeonInfo(geomVo);
		for (ChangeGeomLcVo point : pointList) {
			vectorLists.append(point.getLocation() + "|");
		}
		for (ChangeGeomLcVo line : lineList) {
			vectorLists.append(line.getLocation() + "|");
		}
		for (ChangeGeomLcVo polygon : polygonList) {
			vectorLists.append(polygon.getLocation() + "|");
		}
	
		if (vectorLists.lastIndexOf("|") > 0)
			vectorLists.deleteCharAt(vectorLists.lastIndexOf("|"));
	
		// geometry 정보 가져오기 끝..
		
		changeCntrwkInfoVO = selectChangeCntrwkInfo(changeCntrwkInfoVO, searchVO);
		changeCntrwkInfoVO.setVectorList(vectorLists.toString());
		
		// AES 암호화/복호화 셋팅
		AesUtil aesUtil = new AesUtil(128, 10000);
		
		String deEmail = aesUtil.encryptDecrypy(changeCntrwkInfoVO.getChargerEmail(), 2);
		String deTelNo = aesUtil.encryptDecrypy(changeCntrwkInfoVO.getChargerTlphonNo(), 2);
		
		changeCntrwkInfoVO.setChargerEmail(deEmail);
		changeCntrwkInfoVO.setChargerTlphonNo(deTelNo);
		
		model.addAttribute(changeCntrwkInfoVO);
		return "/ngi/chg/ChangeCntrwkUpdt";
	}

	@RequestMapping("/ngi/chg/updateChangeCntrwkRegView.do")
	public String updateChangeCntrwkRegView(
			final MultipartHttpServletRequest multiRequest,
			@RequestParam("changeInfoId") int changeInfoId,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			Model model) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		ChangeCntrwkInfoVO changeCntrwkInfoVO = new ChangeCntrwkInfoVO();
		changeCntrwkInfoVO.setChangeInfoId(changeInfoId);

		// 변수명은 CoC 에 따라 tnChangeInfoVO
		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("changeTy_result", cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		model.addAttribute("process_result", cmmUseService.selectCmmCodeDetail(vo));

		// 좌표계코드를 코드정보로부터 조회
		vo.setCodeId("CHG010");
		model.addAttribute("cntm_result", cmmUseService.selectCmmCodeDetail(vo));

		// 취득방법코드를 코드정보로부터 조회
		vo.setCodeId("CHG021");
		model.addAttribute("acqsMthTy_result", cmmUseService.selectCmmCodeDetail(vo));

		// 소속기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG018");
		model.addAttribute("psitnEngn_result", cmmUseService.selectCmmCodeDetail(vo));

		// 계획기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG019");
		model.addAttribute("planEngn_result", cmmUseService.selectCmmCodeDetail(vo));
		
		// 관리기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG020");
		model.addAttribute("mngEngn_result", cmmUseService.selectCmmCodeDetail(vo));		

		changeCntrwkInfoVO = selectChangeCntrwkInfo(changeCntrwkInfoVO, searchVO);
		
		// AES 암호화/복호화 셋팅
		AesUtil aesUtil = new AesUtil(128, 10000);
		// 전화번호, 이메일 복호화
		changeCntrwkInfoVO.setChargerEmail(aesUtil.encryptDecrypy(changeCntrwkInfoVO.getChargerEmail(), 2));
		changeCntrwkInfoVO.setChargerTlphonNo(aesUtil.encryptDecrypy(changeCntrwkInfoVO.getChargerTlphonNo(), 2));
		
		// geometry 정보 가져오기.
		ChangeGeomLcVo geomVo = new ChangeGeomLcVo();
		geomVo.setChangeInfoId(changeInfoId);
		StringBuffer vectorLists = new StringBuffer();
		vectorLists.append("");

		List<ChangeGeomLcVo> pointList = changeInfoService.selectGeomLcPointInfo(geomVo);
		List<ChangeGeomLcVo> lineList = changeInfoService.selectGeomLcLnInfo(geomVo);
		List<ChangeGeomLcVo> polygonList = changeInfoService.selectGeomLcMyeonInfo(geomVo);
		for (ChangeGeomLcVo point : pointList) {
			vectorLists.append(point.getLocation() + "|");
		}
		for (ChangeGeomLcVo line : lineList) {
			vectorLists.append(line.getLocation() + "|");
		}
		for (ChangeGeomLcVo polygon : polygonList) {
			vectorLists.append(polygon.getLocation() + "|");
		}

		if (vectorLists.lastIndexOf("|") > 0)
			vectorLists.deleteCharAt(vectorLists.lastIndexOf("|"));

		changeCntrwkInfoVO.setVectorList(vectorLists.toString());
		// geometry 정보 가져오기 끝..

		model.addAttribute(changeCntrwkInfoVO);
		return "/ngi/chg/ChangeCntrwkRegUpdt";
	}
	
	
	@RequestMapping("/ngi/chg/addChangeCntrwkView.do")
	public String addChangeCntrwkView(
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			Model model) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("changeTy_result", cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		model.addAttribute("process_result", cmmUseService.selectCmmCodeDetail(vo));

		// 좌표계코드를 코드정보로부터 조회
		vo.setCodeId("CHG010");
		model.addAttribute("cntm_result", cmmUseService.selectCmmCodeDetail(vo));

		// 취득방법코드를 코드정보로부터 조회
		vo.setCodeId("CHG021");
		model.addAttribute("acqsMthTy_result", cmmUseService.selectCmmCodeDetail(vo));

		// 소속기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG018");
		model.addAttribute("psitnEngn_result", cmmUseService.selectCmmCodeDetail(vo));

		// 계획기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG019");
		model.addAttribute("planEngn_result", cmmUseService.selectCmmCodeDetail(vo));
		
		// 관리기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG020");
		model.addAttribute("mngEngn_result", cmmUseService.selectCmmCodeDetail(vo));

		model.addAttribute("changeCntrwkInfoVO", new ChangeCntrwkInfoVO());
		
		return "/ngi/chg/ChangeCntrwkRegister";
	}

	@RequestMapping("/ngi/chg/addChangeCntrwkExcelView.do")
	public String addChangeCntrwkExcelView(
			HttpServletRequest request,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			ChangeCntrwkInfoVO changeCntrwkInfoVO,
			Model model) throws Exception {
		// 로그인VO에서  사용자 정보 가져오기
    	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");

    	model.addAttribute("loginVO", loginVO);
    	
		return "/ngi/chg/addChangeCntrwkExcelView";
	}

	@RequestMapping("/ngi/chg/getInfoCntrwkViewJson.do")
	@ResponseBody
	public void getInfoCntrwkViewJson(
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			Model model, HttpServletResponse response) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		String str_resdata = "";
		String str_error = "error";
		try {
			Map<String, Object> map_d = new HashMap<String, Object>();

			vo.setCodeId("CHG003");
			map_d.put("changeTy_result", cmmUseService.selectCmmCodeDetail(vo));

			vo.setCodeId("CHG004");
			map_d.put("process_result", cmmUseService.selectCmmCodeDetail(vo));

			vo.setCodeId("CHG010");
			map_d.put("cntm_result", cmmUseService.selectCmmCodeDetail(vo));

			vo.setCodeId("CHG021");
			map_d.put("acqsMthTy_result", cmmUseService.selectCmmCodeDetail(vo));

			vo.setCodeId("CHG020");
			map_d.put("psitnEngn_result", cmmUseService.selectCmmCodeDetail(vo));

			vo.setCodeId("CHG019");
			map_d.put("planEngn_result", cmmUseService.selectCmmCodeDetail(vo));

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

	// 변동보고 관련 함수 끝

	@RequestMapping("/ngi/chg/selectChangeCntrwkInqireJson.do")
	@ResponseBody
	public void selectChangeCntrwkInqireJson(
			@RequestParam("changeInfoId") int changeInfoId,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			Model model, HttpServletResponse response) throws Exception {

		String str_resdata = "";
		String str_error = "error";
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		ChangeCntrwkInfoVO changeCntrwkInfoVO = new ChangeCntrwkInfoVO();
		changeCntrwkInfoVO.setChangeInfoId(changeInfoId);

		try {
			Map<String, Object> map_d = new HashMap<String, Object>();

			changeCntrwkInfoVO = selectChangeCntrwkInfo(changeCntrwkInfoVO,
					searchVO);
			map_d.put("data", changeCntrwkInfoVO);

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

	// 변화정보 관련 함수 시작

	/**
	 * 변화정보 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 TnChangeInfoDefaultVO
	 * @return "/ngi/chg/ChangeTrsInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/ngi/chg/selectChangeCntrwkListJson.do", produces="application/json")
	@ResponseBody
	public ModelMap selectChangeCntrwkListJson(
			@ModelAttribute("searchVO") ChangeInfoVO searchVO, @RequestParam(value="p_id") String p_id,
			ModelMap model, HttpServletRequest request) throws Exception {
		/** EgovPropertyService.sample */
		searchVO.setPageUnit(5);
		searchVO.setPageSize(10);
		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setCntrwkNo(p_id);
		searchVO.setChangeCl("02");
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List ChangeCntrwkList = changeInfoService.selectChangeCntrwkList(searchVO);
		model.addAttribute("resultList", ChangeCntrwkList);

		int totCnt = changeInfoService.selectChangeCntrwkListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return model;
	}
	
	@RequestMapping(value = "/ngi/chg/ChangeTrsInfoList.do")
	public String selectChangeTrsInfoList(
			@ModelAttribute("searchVO") ChangeInfoVO searchVO,
			ModelMap model, HttpServletRequest request) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(5);
		searchVO.setPageSize(10);

		// 신고분류코드를 코드정보로부터 조회
		vo.setCodeId("CHG002");
		model.addAttribute("sttemntty_result",cmmUseService.selectCmmCodeDetail(vo));
		
		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("changeTy_result", cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		model.addAttribute("process_result", cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG017");
		model.addAttribute("search_result", cmmUseService.selectCmmCodeDetail(vo));
		

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setProcessSttusSe("11");
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List ChangeTrsInfoList = changeInfoService.selectChangeTrsInfoList(searchVO);
		model.addAttribute("resultList", ChangeTrsInfoList);
		
		List BgnsNmList = changeInfoService.selectBgnssNmList();
		model.addAttribute("bgnsNmList", BgnsNmList);
		
		int totCnt = changeInfoService.selectChangeTrsInfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/ngi/chg/ChangeTrsInfoList";
	}

	@RequestMapping(value = "/ngi/chg/ChangeEaisInfoList.do")
	public String selectChangeEaisInfoList(
			@ModelAttribute("searchVO") NgiTrsDefaultVO searchVO,
			ModelMap model, HttpServletRequest request) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(5);
		searchVO.setPageSize(10);

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());


		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List ngiTrsEaisDateList = ngiTrsService.selectNgiTrsEaisDateList(searchVO);
        model.addAttribute("resultDateList", ngiTrsEaisDateList);		
		
        List ngiTrsEaisList = ngiTrsService.selectNgiTrsEaisList(searchVO);
        model.addAttribute("resultList", ngiTrsEaisList);
        
        int totCnt = ngiTrsService.selectNgiTrsEaisTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo); 

		return "/ngi/chg/ChangeEaisInfoList";
	}	

	@RequestMapping(value = "/ngi/chg/ChangeKaisInfoList.do")
	public String selectChangeKaisInfoList(
			@ModelAttribute("searchVO") NgiTrsDefaultVO searchVO,
			ModelMap model, HttpServletRequest request) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(5);
		searchVO.setPageSize(10);

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());


		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List ngiTrsKaisDateList = ngiTrsService.selectNgiTrsKaisDateList(searchVO);
        model.addAttribute("resultDateList", ngiTrsKaisDateList);		
		
        List ngiTrsKaisList = ngiTrsService.selectNgiTrsKaisList(searchVO);
        model.addAttribute("resultList", ngiTrsKaisList);
        
        int totCnt = ngiTrsService.selectNgiTrsKaisTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo); 

		return "/ngi/chg/ChangeKaisInfoList";
	}	
	
	
	@RequestMapping("/ngi/chg/selectChangeSttemntCntrwk.do")
	public @ModelAttribute("changeCntrwkInfoVO")
	ChangeSttemntCntrwkVO selectChangeSttemntCntrwk(
			ChangeSttemntCntrwkVO changeSttemntCntrwkVO,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO)
			throws Exception {
		return changeInfoService
				.selectChangeSttemntCntrwk(changeSttemntCntrwkVO);
	}

	@RequestMapping("/ngi/chg/ChangeTrsInfoInqire.do")
	public String ChangeTrsInfoInqire(
			//final MultipartHttpServletRequest multiRequest,
			@RequestParam("changeInfoId") int changeInfoId,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			@ModelAttribute("searchVO2") UpdateOperationVO searchVO2,
			Model model) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		ChangeSttemntCntrwkVO changeSttemntCntrwkVO = new ChangeSttemntCntrwkVO();
		changeSttemntCntrwkVO.setChangeInfoId(changeInfoId);

		ChangeHistVO changeHistVO = new ChangeHistVO();
		changeHistVO.setChangeInfoId(changeInfoId);
		
		
		UpdateOperationVO updateOperationVO = new UpdateOperationVO();
		updateOperationVO.setChangeInfoId(changeInfoId);
		

		model.addAttribute("hist_result",
				changeInfoService.selectChangeHistList(changeHistVO));

		// 변수명은 CoC 에 따라 tnChangeInfoVO

		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		model.addAttribute("changeTy_result",
				cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		model.addAttribute("process_result",
				cmmUseService.selectCmmCodeDetail(vo));

		// 좌표계코드를 코드정보로부터 조회
		vo.setCodeId("CHG010");
		model.addAttribute("cntm_result", cmmUseService.selectCmmCodeDetail(vo));

		// 취득방법코드를 코드정보로부터 조회
		vo.setCodeId("CHG021");
		model.addAttribute("acqsMthTy_result",
				cmmUseService.selectCmmCodeDetail(vo));

		// 소속기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG018");
		model.addAttribute("psitnEngn_result",
				cmmUseService.selectCmmCodeDetail(vo));

		// 계획기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG019");
		model.addAttribute("planEngn_result",
				cmmUseService.selectCmmCodeDetail(vo));

		// 관리기관코드를 코드정보로부터 조회
		vo.setCodeId("CHG020");
		model.addAttribute("mngEngn_result", cmmUseService.selectCmmCodeDetail(vo));			
		
		changeSttemntCntrwkVO = selectChangeSttemntCntrwk(changeSttemntCntrwkVO, searchVO);
		updateOperationVO = updateOperationService.selectUpdateOperation2(searchVO2);
		
		
		vo = new ComDefaultCodeVO();
		vo.setCodeId("CHG003");
		vo.setCode(updateOperationVO.getChangeTy());
		updateOperationVO.setChangeTy(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		vo.setCodeId("MNG010");
		vo.setCode(updateOperationVO.getCntm());
		updateOperationVO.setCntm(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		vo.setCodeId("MNG021");
		vo.setCode(updateOperationVO.getOval());
		updateOperationVO.setOval(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		vo.setCodeId("MNG022");
		vo.setCode(updateOperationVO.getTrnsprclaw());
		updateOperationVO.setTrnsprclaw(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		vo.setCodeId("MNG023");
		vo.setCode(updateOperationVO.getTrgnpt());
		updateOperationVO.setTrgnpt(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		vo.setCodeId("MNG024");
		vo.setCode(updateOperationVO.getUnit());
		updateOperationVO.setUnit(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());

		
		// geometry 정보 가져오기.
		ChangeGeomLcVo geomVo = new ChangeGeomLcVo();
		geomVo.setChangeInfoId(changeInfoId);
		StringBuffer vectorLists = new StringBuffer();
		vectorLists.append("");
		
		List<ChangeGeomLcVo> shpList = changeInfoService.selectGeomLcShpInfo(geomVo);

		for (ChangeGeomLcVo shp : shpList) {
			vectorLists.append(shp.getLocation() + "|");
		}


		if (vectorLists.lastIndexOf("|") > 0)
			vectorLists.deleteCharAt(vectorLists.lastIndexOf("|"));		
		
		// AES 암호화/복호화 셋팅
		AesUtil aesUtil = new AesUtil(128, 10000);
		
		String deEmail = aesUtil.encryptDecrypy(changeSttemntCntrwkVO.getChargerEmail(), 2);
		String deTelNo = aesUtil.encryptDecrypy(changeSttemntCntrwkVO.getChargerTlphonNo(), 2);
		
		changeSttemntCntrwkVO.setChargerEmail(deEmail);
		changeSttemntCntrwkVO.setChargerTlphonNo(deTelNo);
		
		updateOperationVO.setVectorList(vectorLists.toString());
		
		model.addAttribute("updateOperationVO", updateOperationVO);
		model.addAttribute(changeSttemntCntrwkVO);
		return "/ngi/chg/ChangeTrsInfoInqire";
	}

	// 변화정보 관련 함수 끝

	@RequestMapping(value = "/ngi/chg/GetChangeInfoListJson.do")
	@ResponseBody
	public void selectChangeInfoJson(Model model, HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO)
			throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		/** EgovPropertyService.sample */
		searchVO.setPageUnit(5);
		searchVO.setPageSize(10);

		// 신고분류코드를 코드정보로부터 조회
		// vo.setCodeId("CHG002");
		// model.addAttribute("sttemntty_result",
		// cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		// model.addAttribute("process_result",
		// cmmUseService.selectCmmCodeDetail(vo));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List tnChangeInfoList = changeInfoService
				.selectChangeInfoPhoneList(searchVO);
		// model.addAttribute("resultList", tnChangeInfoList);

		int totCnt = changeInfoService
				.selectChangeInfoListTotCntPhone(searchVO);
		// paginationInfo.setTotalRecordCount(totCnt);
		// model.addAttribute("paginationInfo", paginationInfo);

		String str_resdata = "";
		String str_json_data = "data";
		String str_error = "error";
		String str_total = "total";
		String str_display = "display";
		String str_start = "start";
		try {
			Map<String, Object> map_d = new HashMap<String, Object>();
			if (!tnChangeInfoList.isEmpty()) {
				map_d.put(str_json_data, tnChangeInfoList);
				map_d.put(str_total, totCnt);
				map_d.put(str_display, tnChangeInfoList.size());
			} else {
				map_d.put(str_json_data, "");
				map_d.put(str_total, 0);
				map_d.put(str_display, 0);
			}
			map_d.put(str_start, searchVO.getPageIndex());
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
	
	
	@RequestMapping(value = "/ngi/chg/selectChangeCntrwkListJson.do")
	@ResponseBody
	public void selectChangeCntrwkListJson(
			HttpServletResponse response,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			ModelMap model) throws Exception {
//		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(5);
		searchVO.setPageSize(10);

		String str_resdata = "";
		String str_json_data = "data";
		String str_error = "error";
		String str_total = "total";
		String str_display = "display";
		String str_start = "start";
		

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setChangeCl("03");
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List ChangeCntrwkList = changeInfoService
				.selectChangeCntrwkListPhone(searchVO);

		int totCnt = changeInfoService.selectChangeCntrwkListTotCntPhone(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		try {
			Map<String, Object> map_d = new HashMap<String, Object>();
			if (!ChangeCntrwkList.isEmpty()) {
				map_d.put(str_json_data, ChangeCntrwkList);
				map_d.put(str_total, totCnt);
				map_d.put(str_display, ChangeCntrwkList.size());
			} else {
				map_d.put(str_json_data, "");
				map_d.put(str_total, 0);
				map_d.put(str_display, 0);
			}
			map_d.put(str_start, searchVO.getPageIndex());
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

	@RequestMapping(value = "/ngi/chg/ChangeTrsInfoListJson.do")
	@ResponseBody
	public void selectChangeTrsInfoListJson(
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(5);
		searchVO.setPageSize(10);

		// 신고분류코드를 코드정보로부터 조회
		// vo.setCodeId("CHG002");
		// model.addAttribute("sttemntty_result",
		// cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		// model.addAttribute("process_result",
		// cmmUseService.selectCmmCodeDetail(vo));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setProcessSttusSe("11");
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List tnChangeInfoList = changeInfoService
				.selectChangeInfoTrsPhoneList(searchVO);
		// model.addAttribute("resultList", tnChangeInfoList);

		int totCnt = changeInfoService
				.selectChangeInfoTrsListTotCntPhone(searchVO);
		String str_resdata = "";
		String str_json_data = "data";
		String str_error = "error";
		String str_total = "total";
		String str_display = "display";
		String str_start = "start";
		try {
			Map<String, Object> map_d = new HashMap<String, Object>();
			if (!tnChangeInfoList.isEmpty()) {
				map_d.put(str_json_data, tnChangeInfoList);
				map_d.put(str_total, totCnt);
				map_d.put(str_display, tnChangeInfoList.size());
			} else {
				map_d.put(str_json_data, "");
				map_d.put(str_total, 0);
				map_d.put(str_display, 0);
			}
			map_d.put(str_start, searchVO.getPageIndex());
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

	@RequestMapping(value = "/ngi/chg/GetChangeInfoTrsInRectListJson.do")
	@ResponseBody
	public void selectChangeInfoTrsInRectJson(Model model,
			HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("searchVO") ChangeInfoRadiusSearchingVO searchVO)
			throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		/** EgovPropertyService.sample */
		searchVO.setPageUnit(5);
		searchVO.setPageSize(10);

		// 신고분류코드를 코드정보로부터 조회
		// vo.setCodeId("CHG002");
		// model.addAttribute("sttemntty_result",
		// cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		// model.addAttribute("process_result",
		// cmmUseService.selectCmmCodeDetail(vo));

		int totCnt = changeInfoService
				.selectChangeInfoTrsListInRectTotCnt(searchVO);

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(totCnt);
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setProcessSttusSe("11");
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List tnChangeInfoList = changeInfoService
				.selectChangeInfoTrsInRectList(searchVO);
		// model.addAttribute("resultList", tnChangeInfoList);

		// paginationInfo.setTotalRecordCount(totCnt);
		// model.addAttribute("paginationInfo", paginationInfo);

		String str_resdata = "";
		String str_json_data = "data";
		String str_error = "error";
		String str_total = "total";
		String str_display = "display";
		String str_start = "start";
		try {
			Map<String, Object> map_d = new HashMap<String, Object>();
			if (!tnChangeInfoList.isEmpty()) {
				map_d.put(str_json_data, tnChangeInfoList);
				map_d.put(str_total, totCnt);
				map_d.put(str_display, tnChangeInfoList.size());
			} else {
				map_d.put(str_json_data, "");
				map_d.put(str_total, 0);
				map_d.put(str_display, 0);
			}
			map_d.put(str_start, searchVO.getPageIndex());
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

	@RequestMapping(value = "/ngi/chg/GetChangeInfoInRectListJson.do")
	@ResponseBody
	public void selectChangeInfoInRectJson(Model model,
			HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("searchVO") ChangeInfoRadiusSearchingVO searchVO)
			throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		/** EgovPropertyService.sample */
		searchVO.setPageUnit(5);
		searchVO.setPageSize(10);

		// 신고분류코드를 코드정보로부터 조회
		// vo.setCodeId("CHG002");
		// model.addAttribute("sttemntty_result",
		// cmmUseService.selectCmmCodeDetail(vo));

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		// model.addAttribute("process_result",
		// cmmUseService.selectCmmCodeDetail(vo));

		int totCnt = changeInfoService
				.selectChangeInfoListInRectTotCnt(searchVO);

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(totCnt);
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List tnChangeInfoList = changeInfoService
				.selectChangeInfoInRectList(searchVO);
		// model.addAttribute("resultList", tnChangeInfoList);

		// paginationInfo.setTotalRecordCount(totCnt);
		// model.addAttribute("paginationInfo", paginationInfo);

		String str_resdata = "";
		String str_json_data = "data";
		String str_error = "error";
		String str_total = "total";
		String str_display = "display";
		String str_start = "start";
		try {
			Map<String, Object> map_d = new HashMap<String, Object>();
			if (!tnChangeInfoList.isEmpty()) {
				map_d.put(str_json_data, tnChangeInfoList);
				map_d.put(str_total, totCnt);
				map_d.put(str_display, tnChangeInfoList.size());
			} else {
				map_d.put(str_json_data, "");
				map_d.put(str_total, 0);
				map_d.put(str_display, 0);
			}
			map_d.put(str_start, searchVO.getPageIndex());
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

	@RequestMapping("/ngi/chg/GetChangeInfoViewJson.do")
	@ResponseBody
	public void getChangeInfoViewJson(
			@RequestParam("changeInfoId") int changeInfoId,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		ChangeSttemntInfoVO changeSttemntInfoVO = new ChangeSttemntInfoVO();
		changeSttemntInfoVO.setChangeInfoId(changeInfoId);
		// 변수명은 CoC 에 따라 tnChangeInfoVO
		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		// model.addAttribute("sttemntty_result",
		// cmmUseService.selectCmmCodeDetail(vo));
		//
		// model.addAttribute(selectChangeSttemntInfo(changeSttemntInfoVO,
		// searchVO));

		String str_resdata = "";
		String str_json_data = "data";
		String str_error = "error";
		String str_points = "points";
		String str_lines = "lines";
		String str_polygons = "polygons";

		ChangeGeomLcVo vo1 = new ChangeGeomLcVo();
		vo1.setChangeInfoId(changeInfoId);
		try {
			Map<String, Object> map_d = new HashMap<String, Object>();
			map_d.put(str_json_data,
					selectChangeSttemntInfo(changeSttemntInfoVO, searchVO));
			map_d.put(str_points, changeInfoService.selectGeomLcPointInfo(vo1));
			map_d.put(str_lines, changeInfoService.selectGeomLcLnInfo(vo1));
			map_d.put(str_polygons,
					changeInfoService.selectGeomLcMyeonInfo(vo1));

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

	@RequestMapping("/ngi/chg/GetOneChangeInfoJson.do")
	@ResponseBody
	public void getOneChangeInfoJson(HttpServletRequest request,
			@RequestParam("changeInfoId") int changeInfoId,
			@ModelAttribute("searchVO") ChangeInfoDefaultVO searchVO,
			HttpServletResponse response) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		ChangeSttemntInfoVO changeSttemntInfoVO = new ChangeSttemntInfoVO();
		changeSttemntInfoVO.setChangeInfoId(changeInfoId);

		// 변수명은 CoC 에 따라 tnChangeInfoVO
		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		// model.addAttribute("sttemntty_result",
		// cmmUseService.selectCmmCodeDetail(vo));

		changeSttemntInfoVO = selectChangeSttemntInfo(changeSttemntInfoVO,
				searchVO);

		String atchFileId = changeSttemntInfoVO.getAtchFileId();

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		List<FileVO> files = fileMngService.selectFileInfs(fileVO);

		// geometry 정보 가져오기.
		ChangeGeomLcVo geomVo = new ChangeGeomLcVo();
		geomVo.setChangeInfoId(changeInfoId);
		StringBuffer vectorLists = new StringBuffer();
		vectorLists.append("");

		List<ChangeGeomLcVo> pointList = changeInfoService
				.selectGeomLcPointInfo(geomVo);
		List<ChangeGeomLcVo> lineList = changeInfoService
				.selectGeomLcLnInfo(geomVo);
		List<ChangeGeomLcVo> polygonList = changeInfoService
				.selectGeomLcMyeonInfo(geomVo);
		for (ChangeGeomLcVo point : pointList) {
			vectorLists.append(point.getLocation() + "|");
		}
		for (ChangeGeomLcVo line : lineList) {
			vectorLists.append(line.getLocation() + "|");
		}
		for (ChangeGeomLcVo polygon : polygonList) {
			vectorLists.append(polygon.getLocation() + "|");
		}

		if (vectorLists.lastIndexOf("|") > 0)
			vectorLists.deleteCharAt(vectorLists.lastIndexOf("|"));

		changeSttemntInfoVO.setVectorList(vectorLists.toString());
		// geometry 정보 가져오기 끝..

		// model.addAttribute(changeSttemntInfoVO);

		String str_resdata = "";
		String str_json_data = "data";
		String str_error = "error";
		String str_files = "files";
		try {
			Map<String, Object> map_d = new HashMap<String, Object>();
			map_d.put(str_json_data, changeSttemntInfoVO);
			map_d.put(str_files, files);
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

	@RequestMapping("/ngi/chg/GetSttemnttyJson.do")
	@ResponseBody
	public void getSttemnttyJson(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		// 변수명은 CoC 에 따라 tnChangeInfoVO
		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		String str_resdata = "";
		String str_json_data = "data";
		String str_error = "error";
		try {
			Map<String, Object> map_d = new HashMap<String, Object>();
			map_d.put(str_json_data, cmmUseService.selectCmmCodeDetail(vo));
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

	@RequestMapping("/ngi/chg/GetProcessJson.do")
	@ResponseBody
	public void getProcessJson(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		// 변수명은 CoC 에 따라 tnChangeInfoVO
		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		String str_resdata = "";
		String str_json_data = "data";
		String str_error = "error";
		try {
			Map<String, Object> map_d = new HashMap<String, Object>();
			map_d.put(str_json_data, cmmUseService.selectCmmCodeDetail(vo));
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
	
	/** 파일 다운로드 **/
	@RequestMapping("/ngi/chg/cntrwkFileVirtualManageView.do")
	public String cntrwkFileVirtualManageView(
			@RequestParam(value="changeInfoId", required=true) String changeInfoId,
			@RequestParam(value="fileType", required=true) String fileType,
			TnAtchmnflVO searchVO, ChangeSttemntInfoVO changeSttemntInfoSearchVO,
			HttpSession session, Model model) throws Exception {
		
		LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
		
		searchVO.setChangeInfoId(new BigDecimal(changeInfoId));
		searchVO.setFileFomCodeTy(fileType);
		List fileInfoList = tnAtchmnflService.selectTnAtchmnflList(searchVO);

		changeSttemntInfoSearchVO.setChangeInfoId(Integer.parseInt(changeInfoId));
		String registerId= changeInfoService.selectChangeSttemntInfo(changeSttemntInfoSearchVO).getRegisterId();

		String textTitle="";
		if(fileType.equals("01") ) textTitle = "위치도";
		else if(fileType.equals("02")) textTitle = "착공도";
		else if(fileType.equals("03")) textTitle = "준공도";
		
		model.addAttribute("textTitle", textTitle);
		model.addAttribute("loginVO", loginVO);
		model.addAttribute("registerId", registerId);
		model.addAttribute("changeInfoId", changeInfoId);
		model.addAttribute("fileType", fileType);
		model.addAttribute("fileInfoList", fileInfoList);
		
		return "/ngi/chg/cntrwkFileVirtualManageView";
	}
	
	/** 변동보고 첨부파일 일괄 다운로드 **/
	@RequestMapping(value="/ngi/chg/cntrwkFileDownloadView.do", method=RequestMethod.POST)
	public String cntrwkFileDownloadView(
			@RequestParam(value="idNums", required=true) String idNums,
			TnAtchmnflVO searchVO, HttpSession session, Model model) throws Exception {
		
		String[] idsArray = idNums.split(",");
		List fileInfoList = new ArrayList();
		List tempList = null;
		for(int index=0;index<idsArray.length;index++) {
			searchVO = new TnAtchmnflVO();
			searchVO.setChangeInfoId(new BigDecimal(idsArray[index]));
			tempList = tnAtchmnflService.selectTnAtchmnflList(searchVO);
			fileInfoList.addAll(tempList);
		}
		model.addAttribute("textTitle", "첨부파일");
		model.addAttribute("fileInfoList", fileInfoList);
		
		return "/ngi/chg/cntrwkFileDownloadView";
	}
	
	/** 변화정보 첨부파일 일괄 다운로드 **/
	@RequestMapping(value="/ngi/chg/trsinfoFileDownloadView.do", method=RequestMethod.POST)
	public String trsinfoFileDownloadView(
			@RequestParam(value="idNums", required=true) String idNums,
			UpdateOperationVO searchVO, HttpSession session, Model model) throws Exception {
		
		model.addAttribute("textTitle", "첨부파일");
		model.addAttribute("idNums", idNums);
		
		return "/ngi/chg/trsinfoFileDownloadView";
	}

	/** 파일 업로드 **/
	@RequestMapping("/ngi/chg/cntrwkFileUpManageView.do")
	public String cntrwkFileUpManageView(
			@RequestParam(value="changeInfoId", required=true) String changeInfoId,
			@RequestParam(value="fileType", required=true) String fileType,
			Model model) throws Exception {
					
		String textTitle="";
		if(fileType.equals("01") ) textTitle = "위치도";
		else if(fileType.equals("02")) textTitle = "착공도";
		else if(fileType.equals("03")) textTitle = "준공도";
		
		model.addAttribute("textTitle", textTitle);
		model.addAttribute("changeInfoId", changeInfoId);
		model.addAttribute("fileType", fileType);
		
		return "/ngi/chg/cntrwkFileUpManageView";
	}
	
	/** 공사정보 엑셀 일괄 다운로드 미리보기. **/
	@RequestMapping(value="/ngi/chg/cntrwkExcelDownloadView.do", method=RequestMethod.POST)
	public String cntrwkExcelDownloadView(
			@RequestParam(value="idNums", required=true) String idNums,
			ChangeInfoDefaultVO searchVO, HttpSession session, Model model) throws Exception {
		
		String[] idsArray = idNums.split(",");
		List cntrwkInfoList = new ArrayList();
		ModelMap tempModel = null;
		int changeInfoId = 0;
		for(int index=0;index<idsArray.length;index++) {
			changeInfoId = Integer.parseInt(idsArray[index]);
			tempModel = getChangeCntrwkInfo(changeInfoId);
			cntrwkInfoList.add(tempModel);
		}
//		model.addAttribute("textTitle", "첨부파일");
//		model.addAttribute("fileInfoList", fileInfoList);
		
		model.addAttribute("cntrwkInfoList", cntrwkInfoList);
		model.addAttribute("idNums", idNums);
		
		return "/ngi/chg/cntrwkExcelDownloadView";
	}
	
	/** 변화정보 엑셀 일괄 다운로드 미리보기. **/
	@RequestMapping(value="/ngi/chg/trsinfoExcelDownloadView.do", method=RequestMethod.POST)
	public String trsinfoExcelDownloadView(
			@RequestParam(value="idNums", required=true) String idNums,
			ChangeInfoDefaultVO searchVO, HttpSession session, Model model) throws Exception {
		
		String[] idsArray = idNums.split(",");
		List trsInfoList = new ArrayList();
		ModelMap tempModel = null;
		int changeInfoId = 0;
		for(int index=0;index<idsArray.length;index++) {
			changeInfoId = Integer.parseInt(idsArray[index]);
			tempModel = getChangeTrsInfo(changeInfoId);
			trsInfoList.add(tempModel);
		}
//		model.addAttribute("textTitle", "첨부파일");
//		model.addAttribute("fileInfoList", fileInfoList);
		
		model.addAttribute("trsInfoList", trsInfoList);
		model.addAttribute("idNums", idNums);
		
		return "/ngi/chg/trsinfoExcelDownloadView";
	}
	
	/** 변화정보 가져오기 */
	private ModelMap getChangeTrsInfo(int changeInfoId) throws Exception {

		UpdateOperationVO searchVO2 = new UpdateOperationVO();
		searchVO2.setChangeInfoId(changeInfoId);

		ModelMap model = new ModelMap();
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		UpdateOperationVO updateOperationVO = new UpdateOperationVO();
		updateOperationVO.setChangeInfoId(changeInfoId);
		
		updateOperationVO = updateOperationService.selectUpdateOperation2(searchVO2);

		vo.setCodeId("MNG024");
		vo.setCode(updateOperationVO.getUnit());
		updateOperationVO.setUnit(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		vo.setCodeId("MNG008");
		vo.setCode(updateOperationVO.getSttus());
		updateOperationVO.setSttus(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		
		vo.setCodeId("CHG003");
		vo.setCode(updateOperationVO.getChangeTy());
		updateOperationVO.setChangeTy(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		
		vo.setCodeId("MNG010");
		vo.setCode(updateOperationVO.getCntm());
		updateOperationVO.setCntm(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		
		vo.setCodeId("MNG021");
		vo.setCode(updateOperationVO.getOval());
		updateOperationVO.setOval(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		
		vo.setCodeId("MNG022");
		vo.setCode(updateOperationVO.getTrnsprclaw());
		updateOperationVO.setTrnsprclaw(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		vo.setCodeId("MNG023");
		vo.setCode(updateOperationVO.getTrgnpt());
		updateOperationVO.setTrgnpt(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		model.addAttribute("updateOperationVO", updateOperationVO);
		
		return model;
	}
	
	/** 변경신고 엑셀 일괄 다운로드 미리보기. **/
	@RequestMapping(value="/ngi/chg/changeInfoExcelDownloadView.do", method=RequestMethod.POST)
	public String changeInfoExcelDownloadView(
			@RequestParam(value="idNums", required=true) String idNums,
			ChangeInfoDefaultVO searchVO, HttpSession session, Model model) throws Exception {
		
		String[] idsArray = idNums.split(",");
		List changeInfoList = new ArrayList();
		ModelMap tempModel = null;
		int changeInfoId = 0;
		for(int index=0;index<idsArray.length;index++) {
			changeInfoId = Integer.parseInt(idsArray[index]);
			tempModel = getChangeInfo(changeInfoId);
			changeInfoList.add(tempModel);
		}
//		model.addAttribute("textTitle", "첨부파일");
//		model.addAttribute("fileInfoList", fileInfoList);
		
		model.addAttribute("changeInfoList", changeInfoList);
		model.addAttribute("idNums", idNums);
		
		return "/ngi/chg/changeInfoExcelDownloadView";
	}
	
	/** 변경신고 엑셀 일괄 다운로드 **/
	@RequestMapping(value="/ngi/chg/changeFileDownloadDone.do")
	public void changeFileDownloadDone(
			@RequestParam(value="idNums", required=true) String idNums,
			HSSFWorkbook xlsWork, ChangeInfoDefaultVO searchVO,  HttpSession session,  HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		File saveTempFile = File.createTempFile("temp_excel_all_", ".xls"); 
		
		/* db에서 데이터 가져옴 */
		String[] idsArray = idNums.split(",");
		List changeInfoList = new ArrayList();
		ModelMap tempModel = null;
		int changeInfoId = 0;
		for(int index=0;index<idsArray.length;index++) {
			changeInfoId = Integer.parseInt(idsArray[index]);
			tempModel = getChangeInfo(changeInfoId);
			changeInfoList.add(tempModel);
		}
//		model.addAttribute("changeInfoList", changeInfoList);
		/* db에서 데이터 가져옴 끝. */

		String excelFile = request.getSession().getServletContext().getRealPath("/") + "chinfo_data_sample.xls";
		FileCopyUtils.copy(new File(excelFile), saveTempFile);	// 엑셀파일 임시파일로 복사.
		
		POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(saveTempFile));
		xlsWork = new HSSFWorkbook(fileSystem);
		
		HSSFSheet sheet = xlsWork.getSheetAt(0);
		
		ChangeSttemntInfoVO changeSttemntInfoVO = null;
		ModelMap data = null;
		HSSFRow row = null;
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		int rowNum = 1; // 2행 부터 데이터 넣기.
		for (Object obj : changeInfoList) {
			data = (ModelMap)obj;
			changeSttemntInfoVO =  (ChangeSttemntInfoVO) data.get("changeSttemntInfoVO");
			row = sheet.getRow(rowNum);	
			row.getCell(0).setCellValue( changeSttemntInfoVO.getRegisterNm() );		// 신고자명
			row.getCell(1).setCellValue( changeSttemntInfoVO.getTelno() );				// 전화번호
			row.getCell(2).setCellValue( changeSttemntInfoVO.getEmail() );		// 전자우편
			row.getCell(3).setCellValue( changeSttemntInfoVO.getRegistDe() != null ? ft.format(changeSttemntInfoVO.getRegistDe()) : "" );		// 착공일
			row.getCell(4).setCellValue( changeSttemntInfoVO.getChangeSj() );		// 신고제목
			row.getCell(5).setCellValue( (String) data.get("sttemntty_result") );		// 유형
			row.getCell(6).setCellValue( changeSttemntInfoVO.getChangeRnAdresCn() );		// 변동지 주소
			row.getCell(7).setCellValue( changeSttemntInfoVO.getSttemntCn() );		// 신고내용
			rowNum++;
		}
		/* 엑셀 파일에 넣기 끝. */
		response.setHeader("Content-Disposition", "attachment;filename=\""+"chinfo_data.xls"+"\";");
		response.setContentType("application/ms-excel; charset=UTF-8");
        response.setHeader("Content-Transfer-Encoding", "binary");
         
		xlsWork.write(response.getOutputStream());
		response.flushBuffer();
		try {
			saveTempFile.deleteOnExit();
		} finally {
			saveTempFile.delete();
		}
	}
	
	
	/** 공사정보 엑셀 일괄 다운로드. **/
	@RequestMapping(value="/ngi/chg/cntrwkFileDownloadDone.do")
	public void cntrwkFileDownloadDone(
			@RequestParam(value="idNums", required=true) String idNums,
			@RequestParam(value="fileURL", required=true) String fileURL,
			HSSFWorkbook xlsWork, ChangeInfoDefaultVO searchVO,  HttpSession session,  HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		
		File saveTempFile = File.createTempFile("temp_excel_all_", ".xls"); 

		/* db에서 데이터 가져옴 */
		String[] idsArray = idNums.split(",");
		List cntrwkInfoList = new ArrayList();
		ModelMap tempModel = null;
		int changeInfoId = 0;
		for(int index=0;index<idsArray.length;index++) {
			changeInfoId = Integer.parseInt(idsArray[index]);
			tempModel = getChangeCntrwkInfo(changeInfoId);
			cntrwkInfoList.add(tempModel);
		}
//		model.addAttribute("cntrwkInfoList", cntrwkInfoList);
		/* db에서 데이터 가져옴 끝. */

		/* 엑셀 파일에 넣기 */
		String excelFile = request.getSession().getServletContext().getRealPath("/") + fileURL;
		FileCopyUtils.copy(new File(excelFile), saveTempFile);	// 엑셀파일 임시파일로 복사.
		
		POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(saveTempFile));
		xlsWork = new HSSFWorkbook(fileSystem);
		
		HSSFSheet sheet = xlsWork.getSheetAt(0);
//		int rows = sheet.getPhysicalNumberOfRows();

		ModelMap data = null;
		HSSFRow row = null;
		int rowNum = 2; // 3 행 부터 데이터 넣기.
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		ChangeCntrwkInfoVO changeCntrwkInfoVO = null;
		for (Object obj : cntrwkInfoList) {
			data = (ModelMap)obj;
			row = sheet.getRow(rowNum);
			changeCntrwkInfoVO =  (ChangeCntrwkInfoVO) data.get("changeCntrwkInfoVO");
			//row = sheet.createRow(rowNum);	
			row.getCell(0).setCellValue( changeCntrwkInfoVO.getCntrwkNo() );		// 관리번호
			row.getCell(1).setCellValue("");		// 변경여부
			row.getCell(2).setCellValue( (String) data.get("psitnEngn_result") );		// 광역시도 및 국토청
			row.getCell(3).setCellValue( (String) data.get("planEngn_result") );		// 계획기관
			row.getCell(4).setCellValue( (String) data.get("mngEngn_result") );		// 감독기관
			row.getCell(5).setCellValue( (String) data.get("changeTy_result") );		// 변동유형
			row.getCell(6).setCellValue( changeCntrwkInfoVO.getChangeSj() );		// 공사명
			row.getCell(7).setCellValue( changeCntrwkInfoVO.getAddrTy() );		// 주소유형
			row.getCell(8).setCellValue( changeCntrwkInfoVO.getTrgetAreaNm() );		// 대상위치
			row.getCell(9).setCellValue( changeCntrwkInfoVO.getCntrwkPnttm() );		// 공사시점
			row.getCell(10).setCellValue( changeCntrwkInfoVO.getCntrwkTmnl() );		// 공사종점
			row.getCell(11).setCellValue( changeCntrwkInfoVO.getAr() );		// 면적
			row.getCell(12).setCellValue( changeCntrwkInfoVO.getExtn() );		// 연장
			row.getCell(13).setCellValue( changeCntrwkInfoVO.getTrgetBfchgCn() );		// 대상변경전
			row.getCell(14).setCellValue( changeCntrwkInfoVO.getTrgetAftchCn() );		// 대상변경후
			row.getCell(15).setCellValue( changeCntrwkInfoVO.getStrwrkDe() != null ? ft.format(changeCntrwkInfoVO.getStrwrkDe()) : "" );		// 착공일
			row.getCell(16).setCellValue( changeCntrwkInfoVO.getCompetPrearngeDe() != null ? ft.format(changeCntrwkInfoVO.getCompetPrearngeDe()) : "" );		// 완공예정일
			row.getCell(17).setCellValue( changeCntrwkInfoVO.getLastCompetDe() != null ? ft.format(changeCntrwkInfoVO.getLastCompetDe()) : "" );		// 최종완공일
			row.getCell(18).setCellValue( changeCntrwkInfoVO.getChrgDeptNm() );		// 부서명
			row.getCell(19).setCellValue( changeCntrwkInfoVO.getChargerNm() );		// 담당자
			row.getCell(20).setCellValue( changeCntrwkInfoVO.getChargerTlphonNo() );		// 연락처
			row.getCell(21).setCellValue( changeCntrwkInfoVO.getChargerEmail() );		// e-mail
			row.getCell(22).setCellValue( (String) data.get("cntm_result") );		// 좌표계
			row.getCell(23).setCellValue( changeCntrwkInfoVO.getRm() );		// 비고
			
			rowNum++;
		}
		/* 엑셀 파일에 넣기 끝. */
		response.setHeader("Content-Disposition", "attachment;filename=\""+"cntrwk_info_down.xls"+"\";");
		response.setContentType("application/ms-excel; charset=UTF-8");
        response.setHeader("Content-Transfer-Encoding", "binary");
         
        xlsWork.write(response.getOutputStream());
        response.flushBuffer();
        try {
        	saveTempFile.deleteOnExit();
        } finally {
        	saveTempFile.delete();
        }
	}
	
	/** 변화정보 엑셀 일괄 다운로드 . **/
	@RequestMapping(value="/ngi/chg/trsinfoFileDownloadDone.do")
	public void trsinfoFileDownloadDone(
			@RequestParam(value="idNums", required=true) String idNums,
			@RequestParam(value="fileURL", required=true) String fileURL,
			HSSFWorkbook xlsWork, HttpSession session,  HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		
		File saveTempFile = File.createTempFile("temp_excel_all_", ".xls"); 
		
		/* db에서 데이터 가져옴 */
		String[] idsArray = idNums.split(",");
		List trsInfoList = new ArrayList();
		ModelMap tempModel = null;
		int changeInfoId = 0;
		for(int index=0;index<idsArray.length;index++) {
			changeInfoId = Integer.parseInt(idsArray[index]);
			tempModel = getChangeTrsInfo(changeInfoId);
			trsInfoList.add(tempModel);
		}
		/* db에서 데이터 가져옴 끝. */
		
		/* 엑셀 파일에 넣기 */
		String excelFile = request.getSession().getServletContext().getRealPath("/") + fileURL;
		FileCopyUtils.copy(new File(excelFile), saveTempFile);	// 엑셀파일 임시파일로 복사.
		
		POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(saveTempFile));
		xlsWork = new HSSFWorkbook(fileSystem);
		
		HSSFSheet sheet = xlsWork.getSheetAt(0);
//		int rows = sheet.getPhysicalNumberOfRows();

		ModelMap data = null;
		HSSFRow row = null;
		int rowNum = 2; // 3 행 부터 데이터 넣기.
//		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		UpdateOperationVO updateOperationVO = null;
		for (Object obj : trsInfoList) {
			data = (ModelMap)obj;
			row = sheet.getRow(rowNum);
			updateOperationVO =  (UpdateOperationVO) data.get("updateOperationVO");
			
			row.getCell(0).setCellValue( updateOperationVO.getOpertNm() );		// 제목.
			row.getCell(1).setCellValue( updateOperationVO.getBsnsDstrc() );		// 사업지구
			row.getCell(2).setCellValue( updateOperationVO.getServcExcprofsCode() );		// 용역사
			row.getCell(3).setCellValue( updateOperationVO.getUpdusr() );		// 등록자
			row.getCell(4).setCellValue( updateOperationVO.getSttus() );		// 상태
			
			row.getCell(5).setCellValue( "1:5,000" );											// 1:5000 구조화 - 축척
			row.getCell(6).setCellValue( updateOperationVO.getMapdmcNmA() );		// 1:5000 구조화 - 도엽
			row.getCell(7).setCellValue( updateOperationVO.getMapdmcA() );			// 1:5000 구조화 - 도엽번호
			row.getCell(8).setCellValue( "1:5,000" );											// 1:5000 정위치 - 축척
			row.getCell(9).setCellValue( updateOperationVO.getMapdmcNmB() );		// 1:5000 정위치 - 도엽
			row.getCell(10).setCellValue( updateOperationVO.getMapdmcB() );			// 1:5000 정위치 - 도엽번호
			
			row.getCell(11).setCellValue( "1:25,000" );											// 1:25000 정위치 - 축척
			row.getCell(12).setCellValue( updateOperationVO.getMapdmcNmC() );		// 1:25000 정위치 - 도엽
			row.getCell(13).setCellValue( updateOperationVO.getMapdmcC() );			// 1:25000 정위치 - 도엽번호
			
			row.getCell(14).setCellValue( updateOperationVO.getCntrwkPnttm() );		// 위치시점
			row.getCell(15).setCellValue( updateOperationVO.getCntrwkTmnl() );		// 위치종점
			row.getCell(16).setCellValue( updateOperationVO.getChangeTy() );			// 유형
			row.getCell(17).setCellValue( updateOperationVO.getManp()+updateOperationVO.getUnit() );	// 제원	
			row.getCell(18).setCellValue( updateOperationVO.getCntm() );	//	좌표계
			row.getCell(19).setCellValue( updateOperationVO.getOval() );		// 타원체
			row.getCell(20).setCellValue( updateOperationVO.getTrnsprclaw() );		// 투영법
			row.getCell(21).setCellValue( updateOperationVO.getTrgnpt() );		// 원점.
			row.getCell(22).setCellValue( updateOperationVO.getPhtogrfYear() );		// 촬영년도
			row.getCell(23).setCellValue( updateOperationVO.getExaminYear() );		// 조사년도
			row.getCell(24).setCellValue( updateOperationVO.getUpdtYear()+"-"+updateOperationVO.getUpdtMt()  );	// 수정년월
			
			rowNum++;
		}
		/* 엑셀 파일에 넣기 끝. */
		response.setHeader("Content-Disposition", "attachment;filename=\""+"trsinfo_down.xls"+"\";");
		response.setContentType("application/ms-excel; charset=UTF-8");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		xlsWork.write(response.getOutputStream());
		response.flushBuffer();
		try {
			saveTempFile.deleteOnExit();
		} finally {
			saveTempFile.delete();
		}
	}
	
	/* 엑셀일괄 다운로드(변경신고)를 위해 서비스 호출 */
	private ModelMap getChangeInfo(int changeInfoId) throws Exception {
		ModelMap model = new ModelMap();
		
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		ChangeInfoDefaultVO searchVO = new ChangeInfoDefaultVO();
		searchVO.setChangeInfoId(changeInfoId);

		ChangeSttemntInfoVO changeSttemntInfoVO = new ChangeSttemntInfoVO();
		changeSttemntInfoVO.setChangeInfoId(changeInfoId);

		ChangeHistVO changeHistVO = new ChangeHistVO();
		changeHistVO.setChangeInfoId(changeInfoId);

		changeSttemntInfoVO = selectChangeSttemntInfo(changeSttemntInfoVO, searchVO);
//		model.addAttribute("hist_result", changeInfoService.selectChangeHistList(changeHistVO));

		// 신고유형코드를 코드정보로부터 조회
		vo.setCodeId("CHG003");
		vo.setCode(changeSttemntInfoVO.getSttemntTy());
		model.addAttribute("sttemntty_result", cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());

		// 처리상태코드를 코드정보로부터 조회
		vo.setCodeId("CHG004");
		vo.setCode(changeSttemntInfoVO.getProcessSttusSe());
		model.addAttribute("process_result", cmmUseService.selectCmmCodeDetail(vo));
		
		AesUtil aesUtil = new AesUtil(128, 10000);
		EgovStringUtil egovString = new EgovStringUtil();
		//이메일, 전화번호 암호화
		changeSttemntInfoVO.setEmail(aesUtil.encryptDecrypy((String) changeSttemntInfoVO.getEmail(), 2));
		changeSttemntInfoVO.setTelno(aesUtil.encryptDecrypy(changeSttemntInfoVO.getTelno(), 2));
		// html 특수문자 변환 
		//changeSttemntInfoVO.setChangeSj(egovString.getHtmlStrCnvr(changeSttemntInfoVO.getChangeSj()));
		//changeSttemntInfoVO.setSttemntCn(egovString.getHtmlStrCnvr(changeSttemntInfoVO.getSttemntCn()));
		
		model.addAttribute(changeSttemntInfoVO);
		return model;
	}
	
	/* 엑셀일괄 다운로드(변동보고)를 위해 서비스 호출 */
	private ModelMap getChangeCntrwkInfo(int changeInfoId) throws Exception {
		logger.debug("####  엑셀일괄 다운로드를 위해 서비스 호출 #######");
		ModelMap model = new ModelMap();
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		
		ChangeCntrwkInfoVO changeCntrwkInfoVO = new ChangeCntrwkInfoVO();
		changeCntrwkInfoVO.setChangeInfoId(changeInfoId);

		ChangeHistVO changeHistVO = new ChangeHistVO();
		changeHistVO.setChangeInfoId(changeInfoId);

		ChangeInfoDefaultVO searchVO = new ChangeInfoDefaultVO();
		searchVO.setChangeInfoId(changeInfoId);
		changeCntrwkInfoVO = selectChangeCntrwkInfo(changeCntrwkInfoVO, searchVO );
		
		model.addAttribute("hist_result", changeInfoService.selectChangeHistList(changeHistVO));

		// 신고유형코드를 코드정보로부터 조회
		vo = new ComDefaultCodeVO();
		vo.setCodeId("CHG003");
		vo.setCode(changeCntrwkInfoVO.getChangeTy());
		model.addAttribute("changeTy_result", cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());

		// 좌표계코드를 코드정보로부터 조회
		vo = new ComDefaultCodeVO();
		vo.setCodeId("CHG010");
		vo.setCode(changeCntrwkInfoVO.getCntm());
		model.addAttribute("cntm_result", cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());

		// 소속기관코드를 코드정보로부터 조회
		vo = new ComDefaultCodeVO();
		vo.setCodeId("CHG018");
		vo.setCode(changeCntrwkInfoVO.getPsitnEngnNo());
		model.addAttribute("psitnEngn_result", cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());

		// 계획기관코드를 코드정보로부터 조회
		vo = new ComDefaultCodeVO();
		vo.setCodeId("CHG019");
		vo.setCode(changeCntrwkInfoVO.getPlanEngnNo());
		model.addAttribute("planEngn_result", cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());
		
		// 관리기관코드를 코드정보로부터 조회
		vo = new ComDefaultCodeVO();
		vo.setCodeId("CHG020");
		vo.setCode(changeCntrwkInfoVO.getMngEngnNo());
		model.addAttribute("mngEngn_result", cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());	
		
		// 관리기관코드를 코드정보로부터 조회
		vo = new ComDefaultCodeVO();
		vo.setCodeId("CHG023");
		vo.setCode(changeCntrwkInfoVO.getAddrTy());
		changeCntrwkInfoVO.setAddrTy(cmmUseService.selectCmmCodeDetail(vo).get(0).getCodeNm());	
		
		// geometry 정보 가져오기.
		ChangeGeomLcVo geomVo = new ChangeGeomLcVo();
		geomVo.setChangeInfoId(changeInfoId);
		StringBuffer vectorLists = new StringBuffer();
		vectorLists.append("");

		List<ChangeGeomLcVo> pointList = changeInfoService.selectGeomLcPointInfo(geomVo);
		List<ChangeGeomLcVo> lineList = changeInfoService.selectGeomLcLnInfo(geomVo);
		List<ChangeGeomLcVo> polygonList = changeInfoService.selectGeomLcMyeonInfo(geomVo);
		for (ChangeGeomLcVo point : pointList) {
			vectorLists.append(point.getLocation() + "|");
		}
		for (ChangeGeomLcVo line : lineList) {
			vectorLists.append(line.getLocation() + "|");
		}
		for (ChangeGeomLcVo polygon : polygonList) {
			vectorLists.append(polygon.getLocation() + "|");
		}

		if (vectorLists.lastIndexOf("|") > 0)
			vectorLists.deleteCharAt(vectorLists.lastIndexOf("|"));
		// geometry 정보 가져오기 끝..
		
		changeCntrwkInfoVO.setVectorList(vectorLists.toString());
		
		AesUtil aesUtil = new AesUtil(128, 10000);
		EgovStringUtil egovString = new EgovStringUtil();
		//이메일, 전화번호 암호화
		changeCntrwkInfoVO.setChargerEmail(aesUtil.encryptDecrypy(changeCntrwkInfoVO.getChargerEmail(), 2));
		changeCntrwkInfoVO.setChargerTlphonNo(aesUtil.encryptDecrypy(changeCntrwkInfoVO.getChargerTlphonNo(), 2));
		// html 특수문자 변환 (egov 제공 util 메소드 사용)
		//changeCntrwkInfoVO.setChangeSj(egovString.getHtmlStrCnvr(changeCntrwkInfoVO.getChangeSj()));
		//changeCntrwkInfoVO.setRm(egovString.getHtmlStrCnvr(changeCntrwkInfoVO.getRm()));

		model.addAttribute(changeCntrwkInfoVO);
		
		return model;
	}
	
	/** 변경신고 차트에서 사용. 메인페이지용 */
	@RequestMapping(value="/ngi/chg/changeInfoChartCntJson.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public ModelMap selectChangeInfoChartCntJson(
			@RequestParam("area") String area,
			ModelMap model, HttpServletResponse response) throws Exception {

		List list = changeInfoService.selectChangeInfoChartCntJson(area);

		model.addAttribute("result",list);
		
		return model;
		
	}
	
}
