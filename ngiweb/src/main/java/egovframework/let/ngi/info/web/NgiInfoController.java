package egovframework.let.ngi.info.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import egovframework.com.cmm.ComDefaultVO;

@Controller@SessionAttributes(types = ComDefaultVO.class)
public class NgiInfoController {

	
    @RequestMapping(value="/ngi/info/NgiAboutSite.do")
    public String ngiAboutSite(
    		ModelMap model)
            throws Exception {
    	
      	return "ngi/info/NgiAboutSite";
    }	
	
    @RequestMapping(value="/ngi/info/NgiProvideService.do")
    public String ngiProvideService(
    		ModelMap model)
            throws Exception {
    	
      	return "ngi/info/NgiProvideService";
    }		

    
    @RequestMapping(value="/ngi/info/NgiDirection.do")
    public String ngiDirection(
    		ModelMap model)
            throws Exception {
    	
      	return "ngi/info/NgiDirection";
    }	
	
}
