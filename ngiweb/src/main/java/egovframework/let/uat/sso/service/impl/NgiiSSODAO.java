package egovframework.let.uat.sso.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.let.uat.sso.service.SSOVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("NgiiSSODAO")
public class NgiiSSODAO extends EgovAbstractDAO {


	
    public String insertMber(SSOVO vo){
        return (String)insert("NgiiSSODAO.insertSSOUser", vo);
    }	
	

	public int selectUserId(String id) {
		return (Integer) getSqlMapClientTemplate().queryForObject("NgiiSSODAO.selectUserId", id);
	}
	
}
