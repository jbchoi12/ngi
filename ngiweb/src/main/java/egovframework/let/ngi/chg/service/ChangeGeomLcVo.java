package egovframework.let.ngi.chg.service;

/**
 * 변화정보 geometry line, point, polygon
 * 
 * @author degulv
 *
 */
public class ChangeGeomLcVo {

	private int lcPointInfoId; // 포인트 번호.
	private int lcLnInfoId; // 선 번호.
	private int lcMyeonInfoId; // 폴리곤 번호.
	private int shpId; // 폴리곤 번호.


	private int changeInfoId; // 변화정보 번호.
	private String location; // geometry line (wkt)

	public int getLcPointInfoId() {
		return lcPointInfoId;
	}

	public void setLcPointInfoId(int lcPointInfoId) {
		this.lcPointInfoId = lcPointInfoId;
	}

	public int getLcLnInfoId() {
		return lcLnInfoId;
	}

	public void setLcLnInfoId(int lcLnInfoId) {
		this.lcLnInfoId = lcLnInfoId;
	}

	public int getLcMyeonInfoId() {
		return lcMyeonInfoId;
	}

	public void setLcMyeonInfoId(int lcMyeonInfoId) {
		this.lcMyeonInfoId = lcMyeonInfoId;
	}
	public int getShpId() {
		return shpId;
	}

	public void setShpId(int shpId) {
		this.shpId = shpId;
	}
	public int getChangeInfoId() {
		return changeInfoId;
	}

	public void setChangeInfoId(int changeInfoId) {
		this.changeInfoId = changeInfoId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "ChangeGeomLcVo [lcPointInfoId=" + lcPointInfoId
				+ ", lcLnInfoId=" + lcLnInfoId + ", lcMyeonInfoId="
				+ lcMyeonInfoId + ", changeInfoId=" + changeInfoId
				+ ", location=" + location + "]";
	}

	
}
