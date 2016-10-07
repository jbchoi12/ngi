package egovframework.let.ngi.mng.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.let.ngi.chg.service.ChangeInfoChartVO;
import egovframework.let.ngi.chg.service.ChangeInfoService;

@Controller
public class NgiMngChartController {

	@Resource(name = "changeInfoService")
    private ChangeInfoService changeInfoService;
	
	/**
	 * 차트화면
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ngi/mng/NgiMngChartView.do")
    public String ngiMngChartView(ModelMap model) throws Exception {
		List<ChangeInfoChartVO> chartYearList = changeInfoService.selectChangeInfoChartListYear();
		model.addAttribute("yearList", chartYearList);
		
      	return "ngi/mng/NgiMngChartView";
    }	
	
	/**
	 * 변경신고 차트.
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ngi/mng/chartInfo.do", produces = "application/json")
	public @ResponseBody ModelMap safetymap_save(ModelMap model,
			@RequestParam(value = "t", required=false) String type,
			@RequestParam(value = "y", required=false) String year) throws Exception {
		
		/*
		 * type = 0 or 2 (0-변경신고차트, 1-변동보고차트, 2-변화정보차트)
		 * year = 2014 (년도)
		 */
		if( StringUtils.isEmpty(type) || StringUtils.isEmpty(year) ) {	// 빈값 체크.
			return model;
		} else {
			
			List<ChangeInfoChartVO> list = null;
			
			if( type.equals("0") ) {
				// 변경신고 차트 처리.
				
				// chart0
				list = changeInfoService.selectChangeInfoChartSttemntChangeTy(year);	// 유형별 건수.
				ModelMap chart0 = new ModelMap();
				List<String> categories = new ArrayList<String>();
				ModelMap series = new ModelMap();
				List<Integer> data = new ArrayList<Integer>();
				for (ChangeInfoChartVO changeInfoChartVO : list) {
					categories.add(changeInfoChartVO.getName());
					data.add(Integer.parseInt(changeInfoChartVO.getCount()));
				}
				series.addAttribute("name", year+"년");
				series.addAttribute("data", data);
				chart0.addAttribute("categories", categories );
				chart0.addAttribute("series", series);
				
				// chart1
				list = changeInfoService.selectChangeInfoChartSttemntMonth(year);	// 월별 건수.
				ModelMap chart1 = new ModelMap();
				categories = new ArrayList<String>();
				series = new ModelMap();
				data = new ArrayList<Integer>();
				for (ChangeInfoChartVO changeInfoChartVO : list) {
					categories.add(changeInfoChartVO.getName()+"월");
					data.add(Integer.parseInt(changeInfoChartVO.getCount()));
				}
				series.addAttribute("name", year+"년");
				series.addAttribute("data", data);
				chart1.addAttribute("categories", categories );
				chart1.addAttribute("series", series);
				
				
				// chart2
				list = changeInfoService.selectChangeInfoChartSttemntProcessSttusSe(year);	// 상태별 건수.
				ModelMap chart2 = new ModelMap();
				series = new ModelMap();
				List<Object> data2List = new ArrayList<Object>();
				List<Object> temp = null;
				for (ChangeInfoChartVO changeInfoChartVO : list) {
					temp = new ArrayList<Object>();
					temp.add(changeInfoChartVO.getName());
					temp.add(Integer.parseInt(changeInfoChartVO.getCount()));
					data2List.add(temp);
				}
				series.addAttribute("name", year+"년");
				series.addAttribute("data", data2List);
				chart2.addAttribute("series", series);
				
				
				// chart3
				list = changeInfoService.selectChangeInfoChartSttemntYear();	// 년별 건수.			
				ModelMap chart3 = new ModelMap();
				categories = new ArrayList<String>();
				series = new ModelMap();
				data = new ArrayList<Integer>();
				for (ChangeInfoChartVO changeInfoChartVO : list) {
					categories.add(changeInfoChartVO.getName()+"년");
					data.add(Integer.parseInt(changeInfoChartVO.getCount()));
				}
				series.addAttribute("name", "년도별");
				series.addAttribute("data", data);
				chart3.addAttribute("categories", categories );
				chart3.addAttribute("series", series);
				
				
				model.addAttribute("chart0", chart0);
				model.addAttribute("chart1", chart1);
				model.addAttribute("chart2", chart2);
				model.addAttribute("chart3", chart3);
					
			} else if ( type.equals("1") ) {
				// 변동보고 차트 처리.
				
				// chart0
				list = changeInfoService.selectChangeInfoChartCntrwkChangeTy(year);	// 유형별 건수.
				ModelMap chart0 = new ModelMap();
				List<String> categories = new ArrayList<String>();
				ModelMap series = new ModelMap();
				List<Integer> data = new ArrayList<Integer>();
				for (ChangeInfoChartVO changeInfoChartVO : list) {
					categories.add(changeInfoChartVO.getName());
					data.add(Integer.parseInt(changeInfoChartVO.getCount()));
				}
				series.addAttribute("name", year+"년");
				series.addAttribute("data", data);
				chart0.addAttribute("categories", categories );
				chart0.addAttribute("series", series);
				
				// chart1
				list = changeInfoService.selectChangeInfoChartCntrwkMonth(year);	// 월별 건수.
				ModelMap chart1 = new ModelMap();
				categories = new ArrayList<String>();
				series = new ModelMap();
				data = new ArrayList<Integer>();
				for (ChangeInfoChartVO changeInfoChartVO : list) {
					categories.add(changeInfoChartVO.getName()+"월");
					data.add(Integer.parseInt(changeInfoChartVO.getCount()));
				}
				series.addAttribute("name", year+"년");
				series.addAttribute("data", data);
				chart1.addAttribute("categories", categories );
				chart1.addAttribute("series", series);
				
				
				// chart2
				list = changeInfoService.selectChangeInfoChartCntrwkProcessSttusSe(year);	// 상태별 건수.
				ModelMap chart2 = new ModelMap();
				series = new ModelMap();
				List<Object> data2List = new ArrayList<Object>();
				List<Object> temp = null;
				for (ChangeInfoChartVO changeInfoChartVO : list) {
					temp = new ArrayList<Object>();
					temp.add(changeInfoChartVO.getName());
					temp.add(Integer.parseInt(changeInfoChartVO.getCount()));
					data2List.add(temp);
				}
				series.addAttribute("name", year+"년");
				series.addAttribute("data", data2List);
				chart2.addAttribute("series", series);
				
				
				// chart3
				list = changeInfoService.selectChangeInfoChartCntrwkYear();	// 년별 건수.			
				ModelMap chart3 = new ModelMap();
				categories = new ArrayList<String>();
				series = new ModelMap();
				data = new ArrayList<Integer>();
				for (ChangeInfoChartVO changeInfoChartVO : list) {
					categories.add(changeInfoChartVO.getName()+"년");
					data.add(Integer.parseInt(changeInfoChartVO.getCount()));
				}
				series.addAttribute("name", "년도별");
				series.addAttribute("data", data);
				chart3.addAttribute("categories", categories );
				chart3.addAttribute("series", series);
				
				
				model.addAttribute("chart0", chart0);
				model.addAttribute("chart1", chart1);
				model.addAttribute("chart2", chart2);
				model.addAttribute("chart3", chart3);
					
			} else if ( type.equals("2") ) { 
				// 변화정보 차트 처리.
				
				// chart0
				list = changeInfoService.selectChangeInfoChartFinishChangeTy(year);	// 유형별 건수.
				ModelMap chart0 = new ModelMap();
				List<String> categories = new ArrayList<String>();
				ModelMap series = new ModelMap();
				List<Integer> data = new ArrayList<Integer>();
				for (ChangeInfoChartVO changeInfoChartVO : list) {
					categories.add(changeInfoChartVO.getName());
					data.add(Integer.parseInt(changeInfoChartVO.getCount()));
				}
				series.addAttribute("name", year+"년");
				series.addAttribute("data", data);
				chart0.addAttribute("categories", categories );
				chart0.addAttribute("series", series);
				
				// chart1
				list = changeInfoService.selectChangeInfoChartFinishMonth(year);	// 월별 건수.
				ModelMap chart1 = new ModelMap();
				categories = new ArrayList<String>();
				series = new ModelMap();
				data = new ArrayList<Integer>();
				for (ChangeInfoChartVO changeInfoChartVO : list) {
					categories.add(changeInfoChartVO.getName()+"월");
					data.add(Integer.parseInt(changeInfoChartVO.getCount()));
				}
				series.addAttribute("name", year+"년");
				series.addAttribute("data", data);
				chart1.addAttribute("categories", categories );
				chart1.addAttribute("series", series);
				
				
				// chart2
				list = changeInfoService.selectChangeInfoChartFinishBsnsDstrc(year);	// 사업지구별 건수.
				ModelMap chart2 = new ModelMap();
				series = new ModelMap();
				List<Object> data2List = new ArrayList<Object>();
				List<Object> temp = null;
				for (ChangeInfoChartVO changeInfoChartVO : list) {
					temp = new ArrayList<Object>();
					temp.add(changeInfoChartVO.getName());
					temp.add(Integer.parseInt(changeInfoChartVO.getCount()));
					data2List.add(temp);
				}
				series.addAttribute("name", year+"년");
				series.addAttribute("data", data2List);
				chart2.addAttribute("series", series);
				
				
				// chart3
				list = changeInfoService.selectChangeInfoChartFinishYear();	// 년별 건수.			
				ModelMap chart3 = new ModelMap();
				categories = new ArrayList<String>();
				series = new ModelMap();
				data = new ArrayList<Integer>();
				for (ChangeInfoChartVO changeInfoChartVO : list) {
					categories.add(changeInfoChartVO.getName()+"년");
					data.add(Integer.parseInt(changeInfoChartVO.getCount()));
				}
				series.addAttribute("name", "년도별");
				series.addAttribute("data", data);
				chart3.addAttribute("categories", categories );
				chart3.addAttribute("series", series);
				
				
				model.addAttribute("chart0", chart0);
				model.addAttribute("chart1", chart1);
				model.addAttribute("chart2", chart2);
				model.addAttribute("chart3", chart3);
				
			}
			
			
	
		}
		
		return model;

	
	
	
	
	
	
	}
	
}
