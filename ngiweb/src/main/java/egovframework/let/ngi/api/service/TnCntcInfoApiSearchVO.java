package egovframework.let.ngi.api.service;

public class TnCntcInfoApiSearchVO {

	private String key = null;
	private String type = null;
	private String changetype = null;
	private String state = null;
	private String org = null;
	private String sido = null;
	private String sigun = null;
	private String dong = null;
	private String const_name = null;
	private String const_before = null;
	private String conts_after = null;
	private String mapindex = null;
	private String bbox = null;
	private String pageUnit = null;
	private String pageNum = null;
	private String pageSort = null;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChangetype() {
		return changetype;
	}

	public void setChangetype(String changetype) {
		this.changetype = changetype;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getSigun() {
		return sigun;
	}

	public void setSigun(String sigun) {
		this.sigun = sigun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getConst_name() {
		return const_name;
	}

	public void setConst_name(String const_name) {
		this.const_name = const_name;
	}

	public String getConst_before() {
		return const_before;
	}

	public void setConst_before(String const_before) {
		this.const_before = const_before;
	}

	public String getConts_after() {
		return conts_after;
	}

	public void setConts_after(String conts_after) {
		this.conts_after = conts_after;
	}

	public String getMapindex() {
		return mapindex;
	}

	public void setMapindex(String mapindex) {
		this.mapindex = mapindex;
	}

	public String getBbox() {
		return bbox;
	}

	public void setBbox(String bbox) {
		this.bbox = bbox;
	}

	public String getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(String pageUnit) {
		this.pageUnit = pageUnit;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageSort() {
		return pageSort;
	}

	public void setPageSort(String pageSort) {
		this.pageSort = pageSort;
	}

	@Override
	public String toString() {
		return "TnCntcInfoApiSearchVO [key=" + key + ", type=" + type
				+ ", changetype=" + changetype + ", state=" + state + ", org="
				+ org + ", sido=" + sido + ", sigun=" + sigun + ", dong="
				+ dong + ", const_name=" + const_name + ", const_before="
				+ const_before + ", conts_after=" + conts_after + ", mapindex="
				+ mapindex + ", bbox=" + bbox + ", pageUnit=" + pageUnit
				+ ", pageNum=" + pageNum + ", pageSort=" + pageSort + "]";
	}

}
