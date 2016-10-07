package egovframework.let.ngi.trs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.let.ngi.trs.service.NgiTrsDefaultVO;
import egovframework.let.ngi.trs.service.NgiTrsEaisVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("ngiTrsDAO")
public class NgiTrsDAO extends EgovAbstractDAO {


    public List selectNgiTrsEaisList(NgiTrsDefaultVO searchVO) throws Exception {
        return list("ngiTrsDAO.selectNgiTrsEaisList_D", searchVO);
    }


    public int selectNgiTrsEaisTotCnt(NgiTrsDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("ngiTrsDAO.selectNgiTrsEaisTotCnt_S", searchVO);
    }

    public List selectNgiTrsEaisDateList(NgiTrsDefaultVO searchVO) throws Exception {
        return list("ngiTrsDAO.selectNgiTrsEaisDateList", searchVO);
    }    
    
    public List selectNgiTrsKaisList(NgiTrsDefaultVO searchVO) throws Exception {
        return list("ngiTrsDAO.selectNgiTrsKaisList_D", searchVO);
    }


    public int selectNgiTrsKaisTotCnt(NgiTrsDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("ngiTrsDAO.selectNgiTrsKaisTotCnt_S", searchVO);
    }  

    public List selectNgiTrsKaisDateList(NgiTrsDefaultVO searchVO) throws Exception {
        return list("ngiTrsDAO.selectNgiTrsKaisDateList", searchVO);
    }     
    
}
