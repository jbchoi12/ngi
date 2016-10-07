package egovframework.let.ngi.pnt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.ngi.pnt.service.ScoreLogDefaultVO;
import egovframework.let.ngi.pnt.service.ScoreLogService;
import egovframework.let.ngi.pnt.service.ScoreLogVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("scoreLogService")
public class ScoreLogServiceImpl extends AbstractServiceImpl implements ScoreLogService {

    @Resource(name="scoreLogDAO")
    private ScoreLogDAO scoreLogDAO;
    
    public String insertScoreLog(ScoreLogVO vo) throws Exception {
		scoreLogDAO.insertScoreLog(vo);
		return null;
	};
	
	public void updateScoreLog(ScoreLogVO vo) throws Exception{
		scoreLogDAO.updateScoreLog(vo);
	};
	
	public void deleteScoreLog(ScoreLogVO vo) throws Exception{
		scoreLogDAO.deleteScoreLog(vo);
	};

	public ScoreLogVO selectScoreLog(ScoreLogVO vo) throws Exception{
		ScoreLogVO resultVO = scoreLogDAO.selectScoreLog(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	};
	
	public List selectScoreLogList(ScoreLogDefaultVO searchVO) throws Exception{
		return scoreLogDAO.selectScoreLogList(searchVO);
	};
	
	public int selectScoreLogListTotCnt(ScoreLogDefaultVO searchVO) throws Exception{
		return scoreLogDAO.selectScoreLogListTotCnt(searchVO);
	};

    /**
	 * selectScoreLogMaxId 최대id를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return selectScoreLogMaxId 총 갯수
	 * @exception
	 */	
	
	public int selectScoreLogMaxId(ScoreLogVO vo) throws Exception{
		return scoreLogDAO.selectScoreLogMaxId(vo);
	};    
    
	
}
