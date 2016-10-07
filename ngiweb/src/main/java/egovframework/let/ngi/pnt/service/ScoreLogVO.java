package egovframework.let.ngi.pnt.service;

public class ScoreLogVO extends ScoreLogDefaultVO {
    private static final long serialVersionUID = 1L;
    
    private Integer scoreId;
    private String userId;
    private Integer score;
    private String scoreOccrrncDe;
    private String scoreCodeTy;
    
	public Integer getScoreId() {
		return scoreId;
	}
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getScoreOccrrncDe() {
		return scoreOccrrncDe;
	}
	public void setScoreOccrrncDe(String scoreOccrrncDe) {
		this.scoreOccrrncDe = scoreOccrrncDe;
	}
	public String getScoreCodeTy() {
		return scoreCodeTy;
	}
	public void setScoreCodeTy(String scoreCodeTy) {
		this.scoreCodeTy = scoreCodeTy;
	}
    
    
}
