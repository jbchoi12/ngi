package egovframework.com.cmm.web;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.bind.annotation.CommandMap;


/**
 * 파일 다운로드를 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.3.25  이삼섭          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Controller
public class EgovFileDownloadController {

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EgovFileDownloadController.class);

    /**
     * 브라우저 구분 얻기.
     *
     * @param request
     * @return
     */
    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Trident") > -1) {	// IE11 문자열 깨짐 방지
            return "Trident";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }

    /**
     * Disposition 지정하기.
     *
     * @param filename
     * @param request
     * @param response
     * @throws Exception
     */
    private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) {		// IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			//throw new RuntimeException("Not supported browser");
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)){
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
    }

    /**
     * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
     *
     * @param commandMap
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/cmm/fms/FileDown.do")
    public void cvplFileDownload(@CommandMap Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

    String atchFileId = (String)commandMap.get("atchFileId");
	String fileSn = (String)commandMap.get("fileSn");

	//Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	Boolean isAuthenticated = true;
	if (isAuthenticated) {

	    FileVO fileVO = new FileVO();
	    fileVO.setAtchFileId(atchFileId);
	    fileVO.setFileSn(fileSn);
	    FileVO fvo = fileService.selectFileInf(fileVO);

	    File uFile = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
	    int fSize = (int)uFile.length();

	    if (fSize > 0) {
		String mimetype = "application/x-msdownload";

		//response.setBufferSize(fSize);	// OutOfMemeory 발생
		response.setContentType(mimetype);
		//response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
		setDisposition(fvo.getOrignlFileNm(), request, response);
		response.setContentLength(fSize);

		/*
		 * FileCopyUtils.copy(in, response.getOutputStream());
		 * in.close();
		 * response.getOutputStream().flush();
		 * response.getOutputStream().close();
		 */
		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
		    in = new BufferedInputStream(new FileInputStream(uFile));
		    out = new BufferedOutputStream(response.getOutputStream());

		    FileCopyUtils.copy(in, out);
		    out.flush();
		} catch (Exception ex) {
		    // 다음 Exception 무시 처리
		    // Connection reset by peer: socket write error
			LOGGER.debug("IGNORED: {}", ex.getMessage());
		} finally {
		    if (in != null) {
			try {
			    in.close();
			} catch (Exception ignore) {
				LOGGER.debug("IGNORED: {}", ignore.getMessage());
			}
		    }
		    if (out != null) {
			try {
			    out.close();
			} catch (Exception ignore) {
				LOGGER.debug("IGNORED: {}", ignore.getMessage());
			}
		    }
		}

	    } else {
		response.setContentType("application/x-msdownload");

		PrintWriter printwriter = response.getWriter();
		printwriter.println("<html>");
		printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fvo.getOrignlFileNm() + "</h2>");
		printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
		printwriter.println("<br><br><br>&copy; webAccess");
		printwriter.println("</html>");
		printwriter.flush();
		printwriter.close();
	    }
	}
    }
    
    
    /**
     * Getting file for phone
     *
     * @param commandMap
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/cmm/fms/FileDownPhone.do")
    public void cvplFileDownloadPhone(@CommandMap Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String atchFileId = (String)commandMap.get("atchFileId");
    	String fileSn = (String)commandMap.get("fileSn");



	    FileVO fileVO = new FileVO();
	    fileVO.setAtchFileId(atchFileId);
	    fileVO.setFileSn(fileSn);
	    FileVO fvo = fileService.selectFileInf(fileVO);

	    File uFile = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
	    int fSize = (int)uFile.length();

	    if (fSize > 0) {
		String mimetype = "application/x-msdownload";

		//response.setBufferSize(fSize);	// OutOfMemeory 발생
		response.setContentType(mimetype);
		//response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
		setDisposition(fvo.getOrignlFileNm(), request, response);
		response.setContentLength(fSize);

		/*
		 * FileCopyUtils.copy(in, response.getOutputStream());
		 * in.close();
		 * response.getOutputStream().flush();
		 * response.getOutputStream().close();
		 */
		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
		    in = new BufferedInputStream(new FileInputStream(uFile));
		    out = new BufferedOutputStream(response.getOutputStream());

		    FileCopyUtils.copy(in, out);
		    out.flush();
		} catch (Exception ex) {
		    // 다음 Exception 무시 처리
		    // Connection reset by peer: socket write error
			LOGGER.debug("IGNORED: {}", ex.getMessage());
		} finally {
		    if (in != null) {
			try {
			    in.close();
			} catch (Exception ignore) {
				LOGGER.debug("IGNORED: {}", ignore.getMessage());
			}
		    }
		    if (out != null) {
			try {
			    out.close();
			} catch (Exception ignore) {
				LOGGER.debug("IGNORED: {}", ignore.getMessage());
			}
		    }
		}

	    } else {
		response.setContentType("application/x-msdownload");

		PrintWriter printwriter = response.getWriter();
		printwriter.println("<html>");
		printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fvo.getOrignlFileNm() + "</h2>");
		printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
		printwriter.println("<br><br><br>&copy; webAccess");
		printwriter.println("</html>");
		printwriter.flush();
		printwriter.close();
	    }
    }
    
	@RequestMapping("/cmm/fms/getImagePhone.do")
	public void getImagePhone(@CommandMap Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String atchFileId = (String)commandMap.get("atchFileId");
    	String fileSn = (String)commandMap.get("fileSn");
    	String strWidth  = (String)commandMap.get("width");


	    FileVO fileVO = new FileVO();
	    fileVO.setAtchFileId(atchFileId);
	    fileVO.setFileSn(fileSn);

	    FileVO fvo = fileService.selectFileInf(fileVO);

	    File file = new File(fvo.getFileStreCours(), fvo.getStreFileNm());

		FileInputStream fis = null;

		BufferedInputStream in = null;

		ByteArrayOutputStream bStream = null;

		try {

			bStream = new ByteArrayOutputStream();

			if (strWidth != null && !strWidth.equals("")) {

				int width = Integer.parseInt(strWidth);

				BufferedImage bi = ImageIO.read(file);

				int itype = bi.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
						: bi.getType();

				int height = (int) (((float) bi.getHeight() / (float) bi
						.getWidth()) * width);

				BufferedImage resizedImage = new BufferedImage(width, height,
						itype);

				Graphics2D g = resizedImage.createGraphics();

				g.drawImage(bi, 0, 0, width, height, null);

				g.dispose();

				ImageIO.write(resizedImage,
						fvo.getFileExtsn().toLowerCase(), bStream);

			} else {

				fis = new FileInputStream(file);

				in = new BufferedInputStream(fis);

				int imgByte;

				while ((imgByte = in.read()) != -1) {

					bStream.write(imgByte);

				}

			}

			String type = "";

			if (fvo.getFileExtsn() != null
					&& !"".equals(fvo.getFileExtsn())) {

				/*
				 * 
				 * if ("jpg".equals(fileVO.getFileExtsn().toLowerCase())) {
				 * 
				 * type = "image/jpeg";
				 * 
				 * } else {
				 * 
				 * type = "image/" + fileVO.getFileExtsn().toLowerCase();
				 * 
				 * }
				 */

				type = "image/" + fvo.getFileExtsn().toLowerCase();

			} else {

			}

			response.setHeader("Content-Type", type);

			response.setContentLength(bStream.size());

			bStream.writeTo(response.getOutputStream());

			response.getOutputStream().flush();

			response.getOutputStream().close();

			// 2011.10.10 보안점검 후속조치 끝

		} finally {

			if (bStream != null) {

				try {

					bStream.close();

				} catch (Exception ignore) {

				}

			}

			if (in != null) {

				try {

					in.close();

				} catch (Exception ignore) {


				}

			}

			if (fis != null) {

				try {

					fis.close();

				} catch (Exception ignore) {
					

				}

			}

		}

	}

    @RequestMapping(value = "/cmm/fms/FileDownPhoneCompany.do")
    public void cvplFileDownloadPhoneCompany(@CommandMap Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String filePath = (String)commandMap.get("filePath");
    	String originalFileName = filePath;


    	String storePathString = propertyService.getString("Globals.fileStorePath");

	    File uFile = new File(storePathString, filePath);
	    int fSize = (int)uFile.length();

	    if (fSize > 0) {
		String mimetype = "application/x-msdownload";

		//response.setBufferSize(fSize);	// OutOfMemeory 발생
		response.setContentType(mimetype);
		//response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
		setDisposition(originalFileName, request, response);
		response.setContentLength(fSize);

		/*
		 * FileCopyUtils.copy(in, response.getOutputStream());
		 * in.close();
		 * response.getOutputStream().flush();
		 * response.getOutputStream().close();
		 */
		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
		    in = new BufferedInputStream(new FileInputStream(uFile));
		    out = new BufferedOutputStream(response.getOutputStream());

		    FileCopyUtils.copy(in, out);
		    out.flush();
		} catch (Exception ex) {
		    // 다음 Exception 무시 처리
		    // Connection reset by peer: socket write error
			LOGGER.debug("IGNORED: {}", ex.getMessage());
		} finally {
		    if (in != null) {
			try {
			    in.close();
			} catch (Exception ignore) {
				LOGGER.debug("IGNORED: {}", ignore.getMessage());
			}
		    }
		    if (out != null) {
			try {
			    out.close();
			} catch (Exception ignore) {
				LOGGER.debug("IGNORED: {}", ignore.getMessage());
			}
		    }
		}

	    } else {
		response.setContentType("application/x-msdownload");

		PrintWriter printwriter = response.getWriter();
		printwriter.println("<html>");
		printwriter.println("<br><br><br><h2>Could not get file name:<br>" + originalFileName + "</h2>");
		printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
		printwriter.println("<br><br><br>&copy; webAccess");
		printwriter.println("</html>");
		printwriter.flush();
		printwriter.close();
	    }
    }
    
	@RequestMapping("/cmm/fms/getImagePhoneCompany.do")
	public void getImagePhoneCompany(@CommandMap Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String strWidth  = (String)commandMap.get("width");


    	String filePath = (String)commandMap.get("filePath");
    	String originalFileName = filePath;


    	String storePathString = propertyService.getString("Globals.fileStorePath");

	    File file = new File(storePathString, filePath);

		FileInputStream fis = null;

		BufferedInputStream in = null;

		ByteArrayOutputStream bStream = null;
		String fileExtension = filePath.substring(originalFileName.lastIndexOf(".")+1, originalFileName.length());
		try {

			bStream = new ByteArrayOutputStream();

			if (strWidth != null && !strWidth.equals("")) {

				int width = Integer.parseInt(strWidth);

				BufferedImage bi = ImageIO.read(file);

				int itype = bi.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
						: bi.getType();

				int height = (int) (((float) bi.getHeight() / (float) bi
						.getWidth()) * width);

				BufferedImage resizedImage = new BufferedImage(width, height,
						itype);

				Graphics2D g = resizedImage.createGraphics();

				g.drawImage(bi, 0, 0, width, height, null);

				g.dispose();

				ImageIO.write(resizedImage,
						fileExtension.toLowerCase(), bStream);

			} else {

				fis = new FileInputStream(file);

				in = new BufferedInputStream(fis);

				int imgByte;

				while ((imgByte = in.read()) != -1) {

					bStream.write(imgByte);

				}

			}

			String type = "";

			if (fileExtension != null
					&& !"".equals(fileExtension)) {

				/*
				 * 
				 * if ("jpg".equals(fileVO.getFileExtsn().toLowerCase())) {
				 * 
				 * type = "image/jpeg";
				 * 
				 * } else {
				 * 
				 * type = "image/" + fileVO.getFileExtsn().toLowerCase();
				 * 
				 * }
				 */

				type = "image/" + fileExtension.toLowerCase();

			} else {

			}

			response.setHeader("Content-Type", type);

			response.setContentLength(bStream.size());

			bStream.writeTo(response.getOutputStream());

			response.getOutputStream().flush();

			response.getOutputStream().close();

			// 2011.10.10 보안점검 후속조치 끝

		} finally {

			if (bStream != null) {

				try {

					bStream.close();

				} catch (Exception ignore) {

				}

			}

			if (in != null) {

				try {

					in.close();

				} catch (Exception ignore) {


				}

			}

			if (fis != null) {

				try {

					fis.close();

				} catch (Exception ignore) {
					

				}

			}

		}

	}
}
