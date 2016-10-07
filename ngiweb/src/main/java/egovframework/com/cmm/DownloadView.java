package egovframework.com.cmm;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * 파일 다운로드를 위한 클래스를 정의한다.
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
public class DownloadView extends AbstractView{
	
	public DownloadView() {
        setContentType("applicaiton/download;charset=utf-8");
    }
 
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = (File) model.get("downloadFile");
        
        response.setContentType(getContentType());
        response.setContentLength((int)file.length());
         
        String fileName = (String)model.get("fileName");
        fileName = StringUtils.replace(fileName," ","_____");
        fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        fileName = StringUtils.replace(fileName,"_____"," ");
        response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
         
        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
         
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) { try { fis.close(); } catch (Exception e2) { e2.printStackTrace();}}
        }
        out.flush();
    }

}
