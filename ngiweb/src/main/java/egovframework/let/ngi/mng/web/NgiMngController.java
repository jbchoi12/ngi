package egovframework.let.ngi.mng.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.MapToSaveImage;
import egovframework.let.ngi.chg.service.ChangeInfoChartVO;
import egovframework.let.ngi.chg.service.ChangeInfoService;

@Controller@SessionAttributes(types = ComDefaultVO.class)
public class NgiMngController {

	@Resource(name = "changeInfoService")
    private ChangeInfoService changeInfoService;
	
    @RequestMapping(value="/ngi/mng/NgiMngView.do")
    public String ngiMngView(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	List<ChangeInfoChartVO> chartYearList = changeInfoService.selectChangeInfoChartListYear();
    	request.getParameter("changeInfoId");    	
    	model.addAttribute("changeInfoId", request.getParameter("changeInfoId"));
    	model.addAttribute("tabNum", request.getParameter("tabNum"));
    	model.addAttribute("method", request.getParameter("method"));
		model.addAttribute("yearList", chartYearList);
      	return "ngi/mng/NgiMngView";
    }	
	
    /**
	 * 2d 맵 이미지 저장.
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ngi/mng/map_saveimg.do", produces="application/json")
	public @ResponseBody ModelMap safetymap_save(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
			@RequestParam(value = "base_url") String base_url,
			@RequestParam(value = "base_x") String base_x,
			@RequestParam(value = "base_y") String base_y,
			@RequestParam(value = "base_w") String base_w,
			@RequestParam(value = "base_h") String base_h,
			@RequestParam(value = "zoom") String zoom,
			@RequestParam(value = "bbox") String bbox,
			@RequestParam(value = "wmsurl") String wmsurl,
			@RequestParam(value = "layers") String layers,
			@RequestParam(value = "vectors") String vectors
			) {

		MapToSaveImage saveImage = new MapToSaveImage();

		/**
		 *  이미지 저장.
		 * @param vworldURL : vworld basemap url
		 * @param pX : map center lon
		 * @param pY : map center lat
		 * @param pZ : map zoom level
		 * @param pW : map width
		 * @param pH : map height
		 * @param wmsURL : wms server url
		 * @param layers : 레이어명
		 * @param vectors : 벡터정보
		 * @param savePath : 이미지저장 경로.
		 * @param saveFile : 이미지저장 경로.
		 */
		ArrayList<String> layerList = new ArrayList<String>();
		String[] layerStrs = layers.split("#");
		for(String value : layerStrs) {
			layerList.add(value);
		}	// 레이어 리스트 만듬.

		ArrayList<String> vectorList = new ArrayList<String>();
		String[] vectorStrs = vectors.split("#");
		for(String wkt : vectorStrs) {
			vectorList.add(wkt);
		}	// 벡터리스트 만듬.

		
		String base64Image = saveImage.saveMapToImage(base_url, base_x, base_y, zoom, base_w, base_h, bbox, wmsurl, layerList, vectorList);	// 마지막 파일명 랜덤.
//		
		return model.addAttribute("savefile", base64Image );
//		return model.addAttribute("savefile", base64Image );
	}
	
}
