package egovframework.let.ngi.udt.service;

public class MapdmcVO {
	private String mapdmcSe; //도엽 구분
	private String mapdmcNo; //도엽 번호
	private String mapdmcNm; //도엽 이름
	private String geometry; //geometry
	private String mapdmcGroup; //도엽 그룹
	
	public String getMapdmcSe() {
		return mapdmcSe;
	}
	public void setMapdmcSe(String mapdmcSe) {
		this.mapdmcSe = mapdmcSe;
	}
	public String getMapdmcNo() {
		return mapdmcNo;
	}
	public void setMapdmcNo(String mapdmcNo) {
		this.mapdmcNo = mapdmcNo;
	}
	public String getMapdmcNm() {
		return mapdmcNm;
	}
	public void setMapdmcNm(String mapdmcNm) {
		this.mapdmcNm = mapdmcNm;
	}
	public String getGeometry() {
		return geometry;
	}
	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}
	public String getMapdmcGroup() {
		return mapdmcGroup;
	}
	public void setMapdmcGroup(String mapdmcGroup) {
		this.mapdmcGroup = mapdmcGroup;
	}
}
