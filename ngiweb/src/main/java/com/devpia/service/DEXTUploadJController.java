package com.devpia.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import devpia.dextupload.DEXTUploadException;
import devpia.dextupload.FileDownload;
import devpia.dextupload.FileItem;
import devpia.dextupload.FileUpload;
import egovframework.let.ngi.chg.service.ChangeInfoService;
import egovframework.let.ngi.chg.service.ChangeSttemntInfoVO;
import egovframework.let.ngi.chg.service.TnAtchmnflVO;
import egovframework.let.ngi.chg.service.impl.TnAtchmnflService;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * DEXTUploadJ를 사용하여 파일 업로드/다운로드를 처리하는 컨트롤러입니다.
 * @author devpia
 * 
 */

@Controller
public class DEXTUploadJController {	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
    @Resource(name = "tnAtchmnflService")
    private TnAtchmnflService tnAtchmnflService;

    @Resource(name = "changeInfoService")
    private ChangeInfoService changeInfoService;    
    
    
	private String fileSaveSubPath = "\\dext\\";
	
	private String getUploadDirectory() {
		String fileSavePath = null;
		
		fileSavePath = propertyService.getString("Globals.fileStorePath");
		fileSavePath = fileSavePath.concat(fileSaveSubPath);
		
		File saveFolder = new File(fileSavePath);
		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}
		return fileSavePath;
	}
	
	/**
     * 어노테이션 맵핑 규칙에 따라 virtual.up 경로로 요청이 오면 파일 업로드로 판단을 하고
     * 클라이언트로부터 전송된 데이터(파일)로부터 파일을 삭제 여부를 판단합니다.
     * @param req HttpServletRequest 객체
     * @param res HttpServletResponse 객체
     * @throws Exception
     */
    @RequestMapping(value = "/virtual.up", method = RequestMethod.POST)
    public void uploadVirtualDeleted(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	req.setCharacterEncoding("utf-8");
    	res.setContentType("text/plain");
 		res.setCharacterEncoding("utf-8");
    	
    	FileUpload dextj = new FileUpload(req, res);
    	
    	String appRootPath = null;
    	// 임시 파일을 기록될 경로 및 실제 파일이 저장될 경로입니다.
    	// 윈도우 OS가 아닌 경우 운영체제 해당하는 적절한 경로를 설정합니다. (단, 해당 경로는 읽기/쓰기가 가능해야 합니다.)
    	File temp = new File(getUploadDirectory());
    	File repository = new File(getUploadDirectory());
    	
    	try {
    	    // 임시 디렉터리 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!temp.exists() || !temp.canRead() || !temp.canWrite()) {
    			throw new Exception("임시 디렉터리가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // 실제 파일 저장 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!repository.exists() || !repository.canRead() || !repository.canWrite()) {
    			throw new Exception("파일 저장소가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // DEXTUploadJ 라이선스 파일(dextuploadj.config)이 웹 응용프로그램의 루트에 있다고 가정을 합니다.
    	    // 만일 경로가 다르다면 해당 경로를 직접 지정합니다.
    	    appRootPath = req.getSession().getServletContext().getRealPath("/") + "/";
    	    dextj.setLicenseFilePath(appRootPath.concat("dextuploadj.config"));
    	    
    	 	// 파일 업로드 요청 처리를 시작합니다.
    	 	// 임시 경로를 지정하지 않으면 자바에서 제공하는 임시 경로를 사용합니다.
    	    dextj.UploadStart(temp.getAbsolutePath());
    	    
    	 	// 업로드 된 파일 아이템을 얻습니다.
    	 	// "DEXTUploadNX_VirtualDeleted"은 DEXTUploadNX에서 삭제된 가상 파일의 키값을 가지고 있습니다.
    	 	StringBuilder sb = new StringBuilder();
    	 	
    	 	res.setContentType("text/plain");
    	 	
    	 	String[] deletedVirtualFileKeys = dextj.getParameterValues("DEXTUploadNX_VirtualDeleted");
    	 	if (deletedVirtualFileKeys != null && deletedVirtualFileKeys.length > 0) {
    	 		boolean first = true;
    	 		for (String value : deletedVirtualFileKeys) {
    	 			if (first == true) {
    	 				sb.append(value);
    	 				first = false;
    	 			} else {
    	 				sb.append(", ".concat(value));
    	 			}
    	 		}
    	 		res.getWriter().write("Deleted files: ".concat(sb.toString()));
    	 	} else {
    	 		res.getWriter().write("Deleted files do not exist.");
    	 	}
    	} catch (DEXTUploadException ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} catch (Exception ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} finally {
    		// 종료 시에 반드시 자원을 해제해야 합니다.
    	 	// 그렇지 않으면 임시 파일이 삭제되지 않고 남을 수 있습니다.
    	    dextj.dispose();
    	}
    }
    
    
    
	/**
     * 어노테이션 맵핑 규칙에 따라 virtual.up 경로로 요청이 오면 파일 업로드로 판단을 하고
     * 클라이언트로부터 전송된 데이터(파일)로부터 파일을 삭제 여부를 판단합니다.
     * @param req HttpServletRequest 객체
     * @param res HttpServletResponse 객체
     * @throws Exception
     */
    @RequestMapping(value = "/virtual2.up", method = RequestMethod.POST)
    public void uploadVirtualDeleted2(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	req.setCharacterEncoding("utf-8");
    	res.setContentType("text/plain");
 		res.setCharacterEncoding("utf-8");
    	
    	FileUpload dextj = new FileUpload(req, res);
    	
    	String appRootPath = null;
    	// 임시 파일을 기록될 경로 및 실제 파일이 저장될 경로입니다.
    	// 윈도우 OS가 아닌 경우 운영체제 해당하는 적절한 경로를 설정합니다. (단, 해당 경로는 읽기/쓰기가 가능해야 합니다.)
    	File temp = new File(getUploadDirectory());
    	File repository = new File(getUploadDirectory());
    	
    	try {
    	    // 임시 디렉터리 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!temp.exists() || !temp.canRead() || !temp.canWrite()) {
    			throw new Exception("임시 디렉터리가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // 실제 파일 저장 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!repository.exists() || !repository.canRead() || !repository.canWrite()) {
    			throw new Exception("파일 저장소가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // DEXTUploadJ 라이선스 파일(dextuploadj.config)이 웹 응용프로그램의 루트에 있다고 가정을 합니다.
    	    // 만일 경로가 다르다면 해당 경로를 직접 지정합니다.
    	    appRootPath = req.getSession().getServletContext().getRealPath("/") + "/";
    	    dextj.setLicenseFilePath(appRootPath.concat("dextuploadj.config"));
    	    
    	 	// 파일 업로드 요청 처리를 시작합니다.
    	 	// 임시 경로를 지정하지 않으면 자바에서 제공하는 임시 경로를 사용합니다.
    	    dextj.UploadStart(temp.getAbsolutePath());
    	    
    	 	// 업로드 된 파일 아이템을 얻습니다.
    	 	// "DEXTUploadNX_VirtualDeleted"은 DEXTUploadNX에서 삭제된 가상 파일의 키값을 가지고 있습니다.
    	 	StringBuilder sb = new StringBuilder();
    	 	
    	 	res.setContentType("text/plain");
    	 	
    	 	String[] deletedVirtualFileKeys = dextj.getParameterValues("DEXTUploadNX_VirtualDeleted");
    	 	if (deletedVirtualFileKeys != null && deletedVirtualFileKeys.length > 0) {
    	 		boolean first = true;
    	 		for (String value : deletedVirtualFileKeys) {
    	 			if (first == true) {
    	 				sb.append(value);
    	 				first = false;
    	 			} else {
    	 				sb.append(", ".concat(value));
    	 			}
    	 		}
    	 		res.getWriter().write("Deleted files: ".concat(sb.toString()));
    	 	} else {
    	 		res.getWriter().write("Deleted files do not exist.");
    	 	}
    	} catch (DEXTUploadException ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} catch (Exception ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} finally {
    		// 종료 시에 반드시 자원을 해제해야 합니다.
    	 	// 그렇지 않으면 임시 파일이 삭제되지 않고 남을 수 있습니다.
    	    dextj.dispose();
    	}
    }
	
	/**
     * 어노테이션 맵핑 규칙에 따라 upload.up 경로로 요청이 오면 파일 업로드로 판단을 하고
     * 클라이언트로부터 전송된 데이터(파일)로부터 파일을 저장합니다.
     * @param req HttpServletRequest 객체
     * @param res HttpServletResponse 객체
     * @throws Exception
     */
    @RequestMapping(value = "/cntrwkFileUpManage_upload.up", method = RequestMethod.POST)
    public void upload(HttpServletRequest req, HttpServletResponse res, 
    		@RequestParam(value="changeInfoId", required=true) String changeInfoId,
    		@RequestParam(value="fileType", required=true) String fileType 
    		) throws Exception {
    	req.setCharacterEncoding("utf-8");
    	
    	FileUpload dextj = new FileUpload(req, res);
    	
    	String appRootPath = null;
    	// 임시 파일을 기록될 경로 및 실제 파일이 저장될 경로입니다.
    	// 윈도우 OS가 아닌 경우 운영체제 해당하는 적절한 경로를 설정합니다. (단, 해당 경로는 읽기/쓰기가 가능해야 합니다.)
    	File temp = new File(getUploadDirectory());
    	File repository = new File(getUploadDirectory());
    	
    	try {
    	    // 임시 디렉터리 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!temp.exists() || !temp.canRead() || !temp.canWrite()) {
    			throw new Exception("임시 디렉터리가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // 실제 파일 저장 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!repository.exists() || !repository.canRead() || !repository.canWrite()) {
    			throw new Exception("파일 저장소가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // DEXTUploadJ 라이선스 파일(dextuploadj.config)이 웹 응용프로그램의 루트에 있다고 가정을 합니다.
    	    // 만일 경로가 다르다면 해당 경로를 직접 지정합니다.
    	    appRootPath = req.getSession().getServletContext().getRealPath("/") + "/";
    	    dextj.setLicenseFilePath(appRootPath.concat("dextuploadj.config"));
    	    
    	 	// 파일 업로드 요청 처리를 시작합니다.
    	 	// 임시 경로를 지정하지 않으면 자바에서 제공하는 임시 경로를 사용합니다.
    	    dextj.UploadStart(temp.getAbsolutePath());
   	    
    	    File targetFolder = null;

    	 	// "DEXTUploadNX_EmptyFolderPath"은 DEXTUploadNX에서 전송된 빈 폴더(디렉터리) 경로 값을 가지고 있습니다.
    	 	String[] emptyFolderNames = dextj.getParameterValues("DEXTUploadNX_EmptyFolderPath");
    	 	if (emptyFolderNames == null) emptyFolderNames = new String[0];
    	 	for (String nextName : emptyFolderNames) {	 		
    	 		targetFolder = new File(repository, nextName);
    	 		if (!targetFolder.exists())
    	 			targetFolder.mkdir();
    	 	}
    	 	
    	 	// "DEXTUploadNX_FolderPath"은 DEXTUploadNX에서 전송된 폴더(디렉터리) 경로 값을 가지고 있습니다.
     	 	String[] folderNames = dextj.getParameterValues("DEXTUploadNX_FolderPath");
     	 	if (folderNames == null) folderNames = new String[0];
    	 	
     		// "DEXTUploadNX"은 DEXTUploadNX에서 전송된 임시 파일 데이터를 가지고 있습니다.
     		FileItem[] fileItems = dextj.getFileItemValues("DEXTUploadNX");
     		if (fileItems == null) fileItems = new FileItem[0];
     		
     		// "DEXTUploadNX_FolderPath", "DEXTUploadNX" 두 폼 이름은 폴더 업로드 시 동일한 쌍으로 전송됩니다.
     		
     	 	for (int i = 0; i < folderNames.length; i++) {	 		
     	 		targetFolder = new File(repository, folderNames[i]);
     	 		if (!targetFolder.exists())
     	 			targetFolder.mkdir();
     	 		
     	 		if (fileItems[i].IsUploaded()) {
     	 			fileItems[i].SaveAs(targetFolder.getAbsolutePath(), changeInfoId +"_" +fileItems[i].getFileName(), true);
     	 			
     	 			TnAtchmnflVO tnAtchmnflVO = getTnAtchmnflVOFromFileItem(fileItems[i], changeInfoId, fileType);
     	 			
     	 			tnAtchmnflService.deleteTnAtchmnfl(tnAtchmnflVO);
					tnAtchmnflService.insertTnAtchmnfl(tnAtchmnflVO);
     	 			
     	 		}
     	 	}

    	} catch (DEXTUploadException ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} catch (Exception ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} finally {
    		// 종료 시에 반드시 자원을 해제해야 합니다.
    	 	// 그렇지 않으면 임시 파일이 삭제되지 않고 남을 수 있습니다.
    	    dextj.dispose();
    	}
    }
    
    private TnAtchmnflVO getTnAtchmnflVOFromFileItem(FileItem fileItem, String changeInfoId, String fileType) throws Exception {
    	
    	TnAtchmnflVO tnAtchmnflVO = new TnAtchmnflVO();
    	
    	tnAtchmnflVO.setChangeInfoId(new BigDecimal(changeInfoId)); 		// 번호.
    	tnAtchmnflVO.setFileNm(fileItem.getFileName());			//파일명
    	tnAtchmnflVO.setFileMg(String.valueOf(fileItem.length()) );		// 파일 크기
    	tnAtchmnflVO.setFlpthNm(fileItem.getLastSavedFileName());		// 파일경로.
    	tnAtchmnflVO.setFileFomCodeTy(fileType); 		 // 파일형식코드, (CHG014, 01위치도, 02. 착공/설계도, 03. 준공도 
    	 
    	return tnAtchmnflVO;
    }
    
    /**
     * 어노테이션 맵핑 규칙에 따라 userdata.up 경로로 요청이 오면 파일 업로드로 판단을 하고
     * 클라이언트로부터 전송된 데이터(파일)로부터 파일을 저장합니다.
     * @param req HttpServletRequest 객체
     * @param res HttpServletResponse 객체
     * @throws Exception
     */
    @RequestMapping(value = "/userdata.up", method = RequestMethod.POST)
    public void uploadUserData(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	req.setCharacterEncoding("utf-8");
    	res.setContentType("text/plain");
 		res.setCharacterEncoding("utf-8");
    	
    	FileUpload dextj = new FileUpload(req, res);
    	
    	String appRootPath = null;
    	// 임시 파일을 기록될 경로 및 실제 파일이 저장될 경로입니다.
    	// 윈도우 OS가 아닌 경우 운영체제 해당하는 적절한 경로를 설정합니다. (단, 해당 경로는 읽기/쓰기가 가능해야 합니다.)
    	File temp = new File(getUploadDirectory());
    	File repository = new File(getUploadDirectory());
    	
    	try {
    	    // 임시 디렉터리 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!temp.exists() || !temp.canRead() || !temp.canWrite()) {
    			throw new Exception("임시 디렉터리가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // 실제 파일 저장 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!repository.exists() || !repository.canRead() || !repository.canWrite()) {
    			throw new Exception("파일 저장소가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // DEXTUploadJ 라이선스 파일(dextuploadj.config)이 웹 응용프로그램의 루트에 있다고 가정을 합니다.
    	    // 만일 경로가 다르다면 해당 경로를 직접 지정합니다.
    	    appRootPath = req.getSession().getServletContext().getRealPath("/") + "/";
    	    dextj.setLicenseFilePath(appRootPath.concat("dextuploadj.config"));
    	    
    	 	// 파일 업로드 요청 처리를 시작합니다.
    	 	// 임시 경로를 지정하지 않으면 자바에서 제공하는 임시 경로를 사용합니다.
    	    dextj.UploadStart(temp.getAbsolutePath());
    	    
    	    // "DEXTUploadNX_UserData"은 DEXTUploadNX에서 전송된 파일별 사용자 정의 데이터 값을 가지고 있습니다.
     	 	String[] userData = dextj.getParameterValues("DEXTUploadNX_UserData");
     	 	if (userData == null) userData = new String[0];
    	 	
     		// "DEXTUploadNX"은 DEXTUploadNX에서 전송된 임시 파일 데이터를 가지고 있습니다.
     		FileItem[] fileItems = dextj.getFileItemValues("DEXTUploadNX");
     		if (fileItems == null) fileItems = new FileItem[0];
     		
     		// "DEXTUploadNX_UserData", "DEXTUploadNX" 두 폼 이름은 업로드 시 동일한 쌍으로 전송됩니다.
     		
     		StringBuilder sb = new StringBuilder();
     		
     	 	for (int i = 0; i < fileItems.length; i++) { 	 		
     	 		if (fileItems[i].IsUploaded()) {
     	 			fileItems[i].Save(repository.getAbsolutePath(), true);
     	 			sb.append("file: ".concat(fileItems[i].getLastSavedFileName()).concat(", user-data: ").concat(userData[i]));
     	 			
     	 		} else {
     	 			sb.append("file: ".concat(fileItems[i].getFileName()).concat(", user-data: ").concat(userData[i]));
     	 		}
     	 	}
     	 	
     	 	res.getWriter().write(sb.toString());
     	 	
    	} catch (DEXTUploadException ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} catch (Exception ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} finally {
    		// 종료 시에 반드시 자원을 해제해야 합니다.
    	 	// 그렇지 않으면 임시 파일이 삭제되지 않고 남을 수 있습니다.
    	    dextj.dispose();
    	}
    }
    
    /**
     * 어노테이션 맵핑 규칙에 따라 formdata.up 경로로 요청이 오면 파일 업로드로 판단을 하고
     * 클라이언트로부터 전송된 데이터(파일)로부터 파일을 저장합니다.
     * @param req HttpServletRequest 객체
     * @param res HttpServletResponse 객체
     * @throws Exception
     */
    @RequestMapping(value = "/formdata.up", method = RequestMethod.POST)
    public void uploadFormData(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	req.setCharacterEncoding("utf-8");
    	res.setContentType("text/plain");
 		res.setCharacterEncoding("utf-8");
    	
    	FileUpload dextj = new FileUpload(req, res);
    	
    	String appRootPath = null;
    	// 임시 파일을 기록될 경로 및 실제 파일이 저장될 경로입니다.
    	// 윈도우 OS가 아닌 경우 운영체제 해당하는 적절한 경로를 설정합니다. (단, 해당 경로는 읽기/쓰기가 가능해야 합니다.)
    	File temp = new File(getUploadDirectory());
    	File repository = new File(getUploadDirectory());
    	
    	try {
    	    // 임시 디렉터리 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!temp.exists() || !temp.canRead() || !temp.canWrite()) {
    			throw new Exception("임시 디렉터리가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // 실제 파일 저장 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!repository.exists() || !repository.canRead() || !repository.canWrite()) {
    			throw new Exception("파일 저장소가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // DEXTUploadJ 라이선스 파일(dextuploadj.config)이 웹 응용프로그램의 루트에 있다고 가정을 합니다.
    	    // 만일 경로가 다르다면 해당 경로를 직접 지정합니다.
    	    appRootPath = req.getSession().getServletContext().getRealPath("/") + "/";
    	    dextj.setLicenseFilePath(appRootPath.concat("dextuploadj.config"));
    	    
    	 	// 파일 업로드 요청 처리를 시작합니다.
    	 	// 임시 경로를 지정하지 않으면 자바에서 제공하는 임시 경로를 사용합니다.
    	    dextj.UploadStart(temp.getAbsolutePath());
    	    
    	    // "DEXTUploadNX"은 DEXTUploadNX에서 전송된 임시 파일 데이터를 가지고 있습니다.
    	  	FileItem[] fileItems = dextj.getFileItemValues("DEXTUploadNX");
    	  	if (fileItems == null) fileItems = new FileItem[0];
    	 	// 업로드된 파일을 저장합니다.
    	 	for (FileItem item : fileItems) {
    	 		if (item.IsUploaded()) {
     	 			item.Save(repository.getAbsolutePath(), true);
     	 		} 
    	 	}
    	 	
    	 	String autoForm1Value = dextj.getParameter("auto_include_form1");
    	 	String autoForm2Value = dextj.getParameter("auto_include_form2");
    	 	String userForm1Value = dextj.getParameter("UserForm1");
    	 	String userForm2Value = dextj.getParameter("UserForm2");
    	 	
    	 	if (autoForm1Value == null) autoForm1Value = "";
    	 	if (autoForm2Value == null) autoForm2Value = "";
    	 	if (userForm1Value == null) userForm1Value = "";
    	 	if (userForm2Value == null) userForm2Value = "";
     	 	
     	 	res.getWriter().write("[Webformname : auto_include_form1, formvalue: ".concat(autoForm1Value).concat("]"));
     	 	res.getWriter().write("[Webformname : auto_include_form2, formvalue: ".concat(autoForm2Value).concat("]"));
     	 	res.getWriter().write("[forname : UserForm1, formvalue: ".concat(userForm1Value).concat("]"));
     	 	res.getWriter().write("[forname : UserForm2, formvalue: ".concat(userForm2Value).concat("]"));
     	 	
    	} catch (DEXTUploadException ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} catch (Exception ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} finally {
    		// 종료 시에 반드시 자원을 해제해야 합니다.
    	 	// 그렇지 않으면 임시 파일이 삭제되지 않고 남을 수 있습니다.
    	    dextj.dispose();
    	}
    }
    
    /**
     * 어노테이션 맵핑 규칙에 따라 exifdata.up 경로로 요청이 오면 파일 업로드로 판단을 하고
     * 클라이언트로부터 전송된 데이터(파일)로부터 파일을 저장합니다.
     * @param req HttpServletRequest 객체
     * @param res HttpServletResponse 객체
     * @throws Exception
     */
    @RequestMapping(value = "/exifdata.up", method = RequestMethod.POST)
    public void uploadExifData(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	req.setCharacterEncoding("utf-8");
    	res.setContentType("text/plain");
 		res.setCharacterEncoding("utf-8");
    	
    	FileUpload dextj = new FileUpload(req, res);
    	
    	String appRootPath = null;
    	// 임시 파일을 기록될 경로 및 실제 파일이 저장될 경로입니다.
    	// 윈도우 OS가 아닌 경우 운영체제 해당하는 적절한 경로를 설정합니다. (단, 해당 경로는 읽기/쓰기가 가능해야 합니다.)
    	File temp = new File(getUploadDirectory());
    	File repository = new File(getUploadDirectory());
    	
    	try {
    	    // 임시 디렉터리 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!temp.exists() || !temp.canRead() || !temp.canWrite()) {
    			throw new Exception("임시 디렉터리가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // 실제 파일 저장 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!repository.exists() || !repository.canRead() || !repository.canWrite()) {
    			throw new Exception("파일 저장소가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // DEXTUploadJ 라이선스 파일(dextuploadj.config)이 웹 응용프로그램의 루트에 있다고 가정을 합니다.
    	    // 만일 경로가 다르다면 해당 경로를 직접 지정합니다.
    	    appRootPath = req.getSession().getServletContext().getRealPath("/") + "/";
    	    dextj.setLicenseFilePath(appRootPath.concat("dextuploadj.config"));
    	    
    	 	// 파일 업로드 요청 처리를 시작합니다.
    	 	// 임시 경로를 지정하지 않으면 자바에서 제공하는 임시 경로를 사용합니다.
    	    dextj.UploadStart(temp.getAbsolutePath());
    	    
    	    // "DEXTUploadNX"은 DEXTUploadNX에서 전송된 임시 파일 데이터를 가지고 있습니다.
    	  	FileItem[] fileItems = dextj.getFileItemValues("DEXTUploadNX");
    	  	if (fileItems == null) fileItems = new FileItem[0];
    	  	
    	 	// "DEXTUploadNX_ExifData"은 DEXTUploadNX에서 전송된 EXIF 데이터를 가지고 있습니다.
    	  	String[] exifData = dextj.getParameterValues("DEXTUploadNX_ExifData");
    	  	if (exifData == null) exifData = new String[0];
    	  	
    	 	// 업로드된 파일을 저장합니다.
    	 	for (int i = 0; i < fileItems.length; i++) {
    	 		if (fileItems[i].IsUploaded()) {
    	 			fileItems[i].Save(repository.getAbsolutePath(), true);
    	 			res.getWriter().write("Save : ".concat(fileItems[i].getFileName()));
     	 		}
    	 		
    	 		if (exifData[i] != null && exifData[i].length() > 0) {
    	 			res.getWriter().write("[SPLIT]");
    	 			res.getWriter().write(exifData[i]);
    	 		}
    	 		
    	 		res.getWriter().write("[SPLIT]");
    	 	}
     	 	
    	} catch (DEXTUploadException ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} catch (Exception ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} finally {
    		// 종료 시에 반드시 자원을 해제해야 합니다.
    	 	// 그렇지 않으면 임시 파일이 삭제되지 않고 남을 수 있습니다.
    	    dextj.dispose();
    	}
    }
    
    /**
     * 어노테이션 맵핑 규칙에 따라 response.up 경로로 요청이 오면 파일 업로드로 판단을 하고
     * 클라이언트로부터 전송된 데이터(파일)로부터 파일을 저장합니다.
     * @param req HttpServletRequest 객체
     * @param res HttpServletResponse 객체
     * @throws Exception
     */
    @RequestMapping(value = "/response.up", method = RequestMethod.POST)
    public void uploadResponse(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	req.setCharacterEncoding("utf-8");
    	res.setContentType("text/plain");
 		res.setCharacterEncoding("utf-8");
    	
    	FileUpload dextj = new FileUpload(req, res);
    	
    	String appRootPath = null;
    	// 임시 파일을 기록될 경로 및 실제 파일이 저장될 경로입니다.
    	// 윈도우 OS가 아닌 경우 운영체제 해당하는 적절한 경로를 설정합니다. (단, 해당 경로는 읽기/쓰기가 가능해야 합니다.)
    	File temp = new File(getUploadDirectory());
    	File repository = new File(getUploadDirectory());
    	
    	try {
    	    // 임시 디렉터리 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!temp.exists() || !temp.canRead() || !temp.canWrite()) {
    			throw new Exception("임시 디렉터리가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // 실제 파일 저장 경로가 읽기/쓰기가 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
    	    if (!repository.exists() || !repository.canRead() || !repository.canWrite()) {
    			throw new Exception("파일 저장소가 존재하지 않거나 파일을 읽고/쓰기 위한 권한이 없습니다.");
    	    }
    	    
    	    // DEXTUploadJ 라이선스 파일(dextuploadj.config)이 웹 응용프로그램의 루트에 있다고 가정을 합니다.
    	    // 만일 경로가 다르다면 해당 경로를 직접 지정합니다.
    	    appRootPath = req.getSession().getServletContext().getRealPath("/") + "/";
    	    dextj.setLicenseFilePath(appRootPath.concat("dextuploadj.config"));
    	    
    	 	// 파일 업로드 요청 처리를 시작합니다.
    	 	// 임시 경로를 지정하지 않으면 자바에서 제공하는 임시 경로를 사용합니다.
    	    dextj.UploadStart(temp.getAbsolutePath());
    	    
    	    File targetFolder = null;
    	    StringBuilder responseData = new StringBuilder();
    	    	 	
    	 	// "DEXTUploadNX_EmptyFolderPath"은 DEXTUploadNX에서 전송된 빈 폴더(디렉터리) 경로 값을 가지고 있습니다.
    	 	String[] emptyFolderNames = dextj.getParameterValues("DEXTUploadNX_EmptyFolderPath");
    	 	if (emptyFolderNames == null) emptyFolderNames = new String[0];
    	 	for (String nextName : emptyFolderNames) {	 		
    	 		targetFolder = new File(repository, nextName);
    	 		if (!targetFolder.exists()) {
    	 			targetFolder.mkdir();
    	 			responseData.append("Create Folder : ".concat(targetFolder.getName()));
    	 			responseData.append("[SPLIT]");
    	 		} else {
    	 			responseData.append("Existing Folder : ".concat(targetFolder.getName()));
    	 			responseData.append("[SPLIT]");
    	 		}
    	 	}
    	 	
    	 	// "DEXTUploadNX_FolderPath"은 DEXTUploadNX에서 전송된 폴더(디렉터리) 경로 값을 가지고 있습니다.
     	 	String[] folderNames = dextj.getParameterValues("DEXTUploadNX_FolderPath");
     	 	if (folderNames == null) folderNames = new String[0];
    	 	
     		// "DEXTUploadNX"은 DEXTUploadNX에서 전송된 임시 파일 데이터를 가지고 있습니다.
     		FileItem[] fileItems = dextj.getFileItemValues("DEXTUploadNX");
     		if (fileItems == null) fileItems = new FileItem[0];
     		
     		// "DEXTUploadNX_FolderPath", "DEXTUploadNX" 두 폼 이름은 폴더 업로드 시 동일한 쌍으로 전송됩니다.
     		
     	 	for (int i = 0; i < folderNames.length; i++) {	 		
     	 		targetFolder = new File(repository, folderNames[i]);
     	 		if (!targetFolder.exists()) {
     	 			targetFolder.mkdir();
     	 			responseData.append("Create Folder : ".concat(targetFolder.getName()));
    	 			responseData.append("[SPLIT]");
     	 		} else {
     	 			responseData.append("Existing Folder : ".concat(targetFolder.getName()));
    	 			responseData.append("[SPLIT]");
     	 		}
     	 		
     	 		if (fileItems[i].IsUploaded()) {
     	 			fileItems[i].Save(targetFolder.getAbsolutePath(), true);
     	 			responseData.append("Save file : ".concat(fileItems[i].getLastSavedFileName()));
    	 			responseData.append("[SPLIT]");
     	 		}
     	 	}
     		
     	 	res.getWriter().write(responseData.toString());
     	 	
    	} catch (DEXTUploadException ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} catch (Exception ex) {
    	    throw new Exception("업로드 작업이 실패했습니다.", ex);
    	} finally {
    		// 종료 시에 반드시 자원을 해제해야 합니다.
    	 	// 그렇지 않으면 임시 파일이 삭제되지 않고 남을 수 있습니다.
    	    dextj.dispose();
    	}
    }
    
    /**
     * 어노테이션 맵핑 규칙에 따라 behind.down 경로로 요청이 오면 파일 다운로드로 판단을 하고
     * 클라이언트로 데이터(파일)를 전송합니다.
     * @param req HttpServletRequest 객체
     * @param res HttpServletResponse 객체
     * @throws Exception
     */
    @RequestMapping(value = "/behind.down", method = RequestMethod.GET)
    public void download(HttpServletRequest req, HttpServletResponse res,
    		@RequestParam(value="atchmnflId") String atchmnflId, @RequestParam(value="changeInfoId") String changeInfoId) throws Exception {
    	req.setCharacterEncoding("utf-8");
 		
 		// UTF-8 환경에서 한글 파일명을 QueryString 값에서 가져오는 법입니다. 
		//String filename = new String(req.getParameter("DownloadInfo").getBytes("8859_1"), "utf-8");
		FileDownload dextj = new FileDownload(req, res);
		
		// 실제 파일이 저장된 경로입니다.
		// 해당 경로는 읽기가 가능해야 합니다.
//		File repository = new File(req.getSession().getServletContext().getRealPath("/Datas/"));
		File repository = new File(getUploadDirectory());
    	TnAtchmnflVO searchVo = new TnAtchmnflVO();
    	searchVo.setAtchmnflId(new BigDecimal(atchmnflId));
    	searchVo.setChangeInfoId(new BigDecimal(changeInfoId));
    	
    	TnAtchmnflVO fileVO = tnAtchmnflService.selectTnAtchmnfl(searchVo);
		String filename = fileVO.getFlpthNm();
		
		try {	    
		    // 실제 파일 저장 경로가 읽기 권한을 가지고 있는 검사하려면 다음의 코드를 수행합니다.
		    if (!repository.exists() || !repository.canRead()) {
				throw new Exception("파일 저장소가 존재하지 않거나 읽기 위한 권한이 없습니다.");
		    }

		    File target = new File(repository, filename);
		    if (target.exists()) {	
		    	dextj.Download(target.getAbsolutePath(), true, false);
		    } else {
		    	res.sendError(404, filename.concat(" not found"));
		    }
		 	
		} catch (DEXTUploadException ex) {
		    throw new IOException("다운로드 작업이 실패했습니다.", ex);
		} catch (Exception ex) {
		    throw new IOException("다운로드 작업이 실패했습니다.", ex);
		} finally {}
    }
    
    /** 파일 삭제 */
    @RequestMapping(value = "/cntrwkFileUpManage_delete.down", method = RequestMethod.POST)
    public void cntrwkFileUpManage_delete(HttpServletRequest req, HttpServletResponse res,
    		@RequestParam(value="fileType") String fileType, 
    		@RequestParam(value="changeInfoId") String changeInfoId,
    		@RequestParam(value="fileName") String fileName,
    		@RequestParam(value="atchmnflId") String atchmnflId) throws Exception {
    	req.setCharacterEncoding("utf-8");
    	res.setContentType("text/html; charset=UTF-8");
    	
    	TnAtchmnflVO vo = new TnAtchmnflVO();
    	vo.setChangeInfoId(new BigDecimal(changeInfoId));
    	vo.setFileFomCodeTy(fileType);
    	vo.setFileNm(fileName);
    	vo.setAtchmnflId(new BigDecimal(atchmnflId));
		tnAtchmnflService.deleteTnAtchmnfl(vo);
		
    }
 /*
    @RequestMapping(value="/dextfile/downloadFile/{filePath}/{fileName}/down.do", method=RequestMethod.GET)
    public ModelAndView downloadFile(@PathVariable("filePath") String filePath
    		, @PathVariable("fileName") String fileName
    		, HttpServletRequest request, HttpServletResponse res, ModelMap model)
    				throws Exception {
    	request.setCharacterEncoding("utf-8");
    	res.setContentType("text/html; charset=UTF-8");
    	
    	filePath = new String(filePath.getBytes("8859_1"), "utf-8");
    	fileName = new String(fileName.getBytes("8859_1"), "utf-8"); 
    	//System.err.println(filePath);
    	//System.err.println(fileName);
    	
    	filePath = StringUtils.replace(filePath, "__", File.separatorChar+"");
    	String fileExt = StringUtils.split(request.getServletPath(),".")[1];
    	ModelAndView mav = new ModelAndView("downloadView");
    	mav.addObject("downloadFile", new File(propertyService.getString("Globals.folderStorePath")+filePath+File.separatorChar+fileName+"."+fileExt));
    	mav.addObject("fileName", fileName);
    	return mav;
    }
    */
    
	@RequestMapping(value="/dextfile/downloadFile/{filePath}/{fileName}/down.do", method=RequestMethod.GET)
	public void downloadFile(@PathVariable("filePath") String filePath
			, @PathVariable("fileName") String fileName
			, HttpServletRequest request, HttpServletResponse res, ModelMap model)
			throws Exception {
		request.setCharacterEncoding("utf-8");
    	res.setContentType("text/html; charset=UTF-8");
    	
    	//filePath = new String(filePath.getBytes("8859_1"), "utf-8");
    	//fileName = new String(fileName.getBytes("8859_1"), "utf-8"); 
    	//System.err.println(filePath);
    	//System.err.println(fileName);
    	System.out.println("다운로드 파일 경로 : " + filePath);
    	
    	String repositoryPath =StringUtils.replace( filePath+fileName,  "__", File.separatorChar+"");
    	
    	System.out.println("실제 파일: " + repositoryPath);
//		String repositoryPath =StringUtils.replace( propertyService.getString("Globals.folderStorePath")+filePath+fileName,  "__", File.separatorChar+"");
//	    File repository = new File(repositoryPath);
	    
//		Collection<File> list = FileUtils.listFiles(repository, FileFilterUtils.fileFileFilter(), DirectoryFileFilter.INSTANCE);
		
//		ModelAndView mav = new ModelAndView("downloadView");
//		mav.addObject("downloadFile", new File(repositoryPath));
//		mav.addObject("fileName", fileName);
//		return mav;
	    FileDownload dextj = new FileDownload(request, res);
	    File target = new File(repositoryPath);
	    if (target.exists()) {	 
	    	dextj.Download(target.getAbsolutePath(), true, false);
	    } else {
	    	res.sendError(404, fileName.concat(" not found"));
	    }
	}
	
	@RequestMapping(value="/dextfile/downloadFile.do", method=RequestMethod.GET)
//	public ModelAndView downloadFile2(@RequestParam("changeInfoId") String changeInfoId, @RequestParam("idx") String idx
	public void downloadFile2( HttpServletRequest req, HttpServletResponse res, ModelMap model
			,@RequestParam("filePath") String filePath, @RequestParam("filename") String filename,
			@RequestParam Map<String,Object> map )
//	public void downloadFile2( HttpServletRequest req, HttpServletResponse res, ModelMap model)
			throws Exception {
    	req.setCharacterEncoding("utf-8");
 		res.setCharacterEncoding("utf-8");
 		//System.err.println(new String(req.getParameter("filePath").getBytes("8859_1"), "utf-8") ); //방법2
// 		
// 		String filePath = URLDecoder.decode(req.getParameter("filePath"), "UTF-8");
// 		String filename = URLDecoder.decode(req.getParameter("filename"), "UTF-8");
    	
//		String repositoryPath = propertyService.getString("Globals.folderStorePath")+File.separatorChar+changeInfoId+File.separatorChar+idx;
		String repositoryPath =StringUtils.replace( propertyService.getString("Globals.folderStorePath")+filePath+filename,  "__", File.separatorChar+"");
		//System.err.println(repositoryPath);
//	    File repository = new File(repositoryPath);
	    
//		Collection<File> list = FileUtils.listFiles(repository, FileFilterUtils.fileFileFilter(), DirectoryFileFilter.INSTANCE);
		
//		ModelAndView mav = new ModelAndView("downloadView");
//		mav.addObject("downloadFile", new File(repositoryPath));
//		mav.addObject("fileName", filename);
//		return mav;
	    FileDownload dextj = new FileDownload(req, res);
	    File target = new File(repositoryPath);
	    if (target.exists()) {	
	    	dextj.Download(target.getAbsolutePath(), true, false);
	    } else {
	    	res.sendError(404, filename.concat(" not found"));
	    }
	}
    
    @RequestMapping(value="/dextfile/selectSubFileList.do", method=RequestMethod.POST, produces="application/json")
    @ResponseBody
	public ModelAndView selectSubFileList2( @RequestParam(value="idNums", required=true) String idNums , HttpServletRequest request, ModelMap model) throws Exception {
		
		ModelAndView mav = new ModelAndView("jsonView");
		
		String[] changeInfoIdArray = idNums.split(",");
		String[] idxArray = new String[]{"SHP", "NGI_NDA", "DWG_DXF_5", "DWG_DXF_25"};
		List<String[]> subFileList = new ArrayList<String[]>();
		String repositoryPath = null;
		File repository = null;
		
		for (String changeInfoId : changeInfoIdArray) {

			String realPath = "";
			String filePath = "";
			String fileName = "";
			String savePath = "";
			String saveRealPath = "";
			String sjNm = "";
			

			
			ChangeSttemntInfoVO changeSttemntInfoVO = new ChangeSttemntInfoVO();
			changeSttemntInfoVO.setChangeInfoId(Integer.parseInt(changeInfoId));			
			
			ChangeSttemntInfoVO resultcvo = changeInfoService.selectChangeSttemntInfo(changeSttemntInfoVO);
	
			sjNm = resultcvo.getChangeSj();
	
			for (String idx : idxArray) {
				repositoryPath = propertyService.getString("Globals.folderStorePath")+File.separatorChar+changeInfoId+File.separatorChar+idx;
			    repository = new File(repositoryPath);
			    log.debug("repositoryPath : " + repositoryPath);
			    Collection<File> list = null;
			    try {
			    	list = FileUtils.listFiles(repository, FileFilterUtils.fileFileFilter(), DirectoryFileFilter.INSTANCE);
					
					for(File file : list) {
						if(file.exists()) {
							fileName = file.getName();
							filePath = StringUtils.replace(file.getAbsolutePath().replace(propertyService.getString("Globals.folderStorePath"), "").replace(fileName, ""), File.separatorChar+"", "__");
							realPath = file.getAbsolutePath().replace(repositoryPath, "").replace(fileName, "");
							//savePath = changeInfoId + File.separatorChar + idx+File.separatorChar+"";
							savePath = changeInfoId + "_" + sjNm + File.separatorChar + idx+File.separatorChar+"";
							saveRealPath = StringUtils.replace(filePath, "__",  File.separatorChar+"");
							subFileList.add(new String[] {realPath, filePath, fileName, String.valueOf(file.length()), savePath, saveRealPath});
						}
					}
			    } catch(Exception e) {
			    	//e.printStackTrace();
			    	// 첨부파일없음.
			    }
				
			}
		}
		
		mav.addObject("subFileList", subFileList);
		
		return mav;
	}
    
}
