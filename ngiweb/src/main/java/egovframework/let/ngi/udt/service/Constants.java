package egovframework.let.ngi.udt.service;

/**
 * 상수 클래스
 * @author kka
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *	
 *  </pre>
 */
public class Constants {
	/** 처리상태 */
	public static final String RECEIPT = "01"; //접수중
	public static final String RECEIPT_COMPLETE = "02"; //접수완료
	public static final String COMPLEMENTARY = "03"; //보완
	public static final String HOLD = "04"; //보류
	public static final String COMPANION = "05"; //반려
	public static final String RECEIPT_CANCEL = "06"; //접수취소
	public static final String MAP_UPDATE = "07"; //지도수정중
	public static final String MAP_COMPLETE = "11"; //지도수정완료
	public static final String INSPECTING = "07"; //검수중
	public static final String INSPECTION_SUPPL = "09"; //검수보완요청
	public static final String INSPECTION_COMPLETE = "11"; //검수완료
	
	/** 변동분류 */
	public static final String STTEMNT_INFO = "01"; //변경신고 
	public static final String CNTRWK_INFO = "02"; //변동보고
	public static final String MONITORING_INFO = "03"; //변동보고
	
	/** 축척분류 */
	public static final String ONE_FIVE = "01"; //축척 1:5000
	public static final String ONE_TWENTYFIVE = "02"; //축척 1:25000
	public static final String ONE_FIFTY = "03"; //축척 1:50000
	
	/** 단위분류 */
	public static final String SQUARE_METER = "01"; //㎡
	public static final String KILLMETER = "02"; //㎞
	
	/** 메뉴분류 */
	public static final String MENU_STTEMNT = "01"; //변경신고 
	public static final String MENU_CNTRWK = "02"; //변동보고
	public static final String MENU_MONITORING = "03"; //모니터링
	public static final String MENU_SCHEDULE= "11"; //예정정보 
	public static final String MENU_TARGET = "04"; //조사대상
	public static final String MENU_RESULT = "04"; //성과
	public static final String MENU_CHANGE = "05"; //변화정보
	public static final String MENU_NOTICE = "10"; //지형고시
	public static final String MENU_RENEWAL = "12"; //갱신이력


	/** 파일저장경로 */
	public static final String FILE_SERVICE_CHANGEINFO = "change_info_file";
	public static final String FILE_IDX_CHANGEINFO_SHP = "SHP";
	public static final String FILE_IDX_CHANGEINFO_NGI_NDA = "NGI_NDA";
	public static final String FILE_IDX_CHANGEINFO_DWG_DXF_5 = "DWG_DXF_5";
	public static final String FILE_IDX_CHANGEINFO_DWG_DXF_25 = "DWG_DXF_25";
	
}
