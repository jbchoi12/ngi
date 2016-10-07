package egovframework.let.ngi.trs.service;

import java.util.List;

public interface NgiTrsService {

    List selectNgiTrsEaisList(NgiTrsDefaultVO searchVO) throws Exception;
    
    int selectNgiTrsEaisTotCnt(NgiTrsDefaultVO searchVO);

    List selectNgiTrsEaisDateList(NgiTrsDefaultVO searchVO) throws Exception;    
    
    List selectNgiTrsKaisList(NgiTrsDefaultVO searchVO) throws Exception;
    
    int selectNgiTrsKaisTotCnt(NgiTrsDefaultVO searchVO);
    
    List selectNgiTrsKaisDateList(NgiTrsDefaultVO searchVO) throws Exception; 
    
}
