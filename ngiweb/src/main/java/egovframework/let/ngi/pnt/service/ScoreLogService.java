package egovframework.let.ngi.pnt.service;

import java.util.List;

public interface ScoreLogService {
	
	String insertScoreLog(ScoreLogVO vo) throws Exception;
	
	void updateScoreLog(ScoreLogVO vo) throws Exception;
	
	void deleteScoreLog(ScoreLogVO vo) throws Exception;

	ScoreLogVO selectScoreLog(ScoreLogVO vo) throws Exception;
	
	List selectScoreLogList(ScoreLogDefaultVO searchVO) throws Exception;
	
	int selectScoreLogListTotCnt(ScoreLogDefaultVO searchVO) throws Exception;

    /**
	 * selectScoreLogMaxId 최대id를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return selectScoreLogMaxId 총 갯수
	 * @exception
	 */	
	
	int selectScoreLogMaxId(ScoreLogVO vo) throws Exception;
	
}
