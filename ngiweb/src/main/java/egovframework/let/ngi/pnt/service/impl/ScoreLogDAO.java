package egovframework.let.ngi.pnt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.let.ngi.pnt.service.ScoreLogDefaultVO;
import egovframework.let.ngi.pnt.service.ScoreLogVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("scoreLogDAO")
public class ScoreLogDAO extends EgovAbstractDAO {
	
	public String insertScoreLog(ScoreLogVO vo) throws Exception{
		return (String)insert("scoreLogDAO.insertScoreLog_S", vo);
	};
	
	public void updateScoreLog(ScoreLogVO vo) throws Exception{
		update("scoreLogDAO.updateScoreLog_S", vo);
	};
	
	public void deleteScoreLog(ScoreLogVO vo) throws Exception{
		delete("scoreLogDAO.deleteScoreLog_S", vo);
	};

	public ScoreLogVO selectScoreLog(ScoreLogVO vo) throws Exception{
		 return (ScoreLogVO) selectByPk("scoreLogDAO.selectScoreLog_S", vo);
	};
	
	public List selectScoreLogList(ScoreLogDefaultVO searchVO) throws Exception{
		return list("scoreLogDAO.selectScoreLogList_D", searchVO);
	};
	
	public int selectScoreLogListTotCnt(ScoreLogDefaultVO searchVO) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("scoreLogDAO.selectScoreLogListTotCnt_S", searchVO);
	};

	public int selectScoreLogMaxId(ScoreLogVO vo) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("scoreLogDAO.selectScoreLogMaxid", vo);
	};	
	

}
