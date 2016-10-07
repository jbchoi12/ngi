package egovframework.let.ngi.trs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;




import egovframework.let.ngi.trs.service.NgiTrsDefaultVO;
import egovframework.let.ngi.trs.service.NgiTrsService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("ngiTrsService")
public class NgiTrsServiceImpl extends AbstractServiceImpl implements
NgiTrsService {
	
    @Resource(name="ngiTrsDAO")
    private NgiTrsDAO ngiTrsDAO;
    

	public List selectNgiTrsEaisList(NgiTrsDefaultVO searchVO) throws Exception {
		return ngiTrsDAO.selectNgiTrsEaisList(searchVO);
	}


	public int selectNgiTrsEaisTotCnt(NgiTrsDefaultVO searchVO) {
		return ngiTrsDAO.selectNgiTrsEaisTotCnt(searchVO);
	}    

	public List selectNgiTrsEaisDateList(NgiTrsDefaultVO searchVO) throws Exception {
		return ngiTrsDAO.selectNgiTrsEaisDateList(searchVO);
	}	
	
	public List selectNgiTrsKaisList(NgiTrsDefaultVO searchVO) throws Exception {
		return ngiTrsDAO.selectNgiTrsKaisList(searchVO);
	}


	public int selectNgiTrsKaisTotCnt(NgiTrsDefaultVO searchVO) {
		return ngiTrsDAO.selectNgiTrsKaisTotCnt(searchVO);
	}   	

	public List selectNgiTrsKaisDateList(NgiTrsDefaultVO searchVO) throws Exception {
		return ngiTrsDAO.selectNgiTrsKaisDateList(searchVO);
	}	
	
}
