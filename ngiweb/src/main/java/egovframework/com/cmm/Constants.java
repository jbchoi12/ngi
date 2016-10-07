package egovframework.com.cmm;

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
	
	/** 측지계 디폴트 */
	public static final String GEO_WORLD = "04" ; // 세계측지계
	
	/** 모니터링 상태 분류 _2015.4.21 유정우 */
	public static final String MON_TEMP_RECEIPT = "01";			//임시접수
	public static final String MON_RECEIPT_COMPLETE = "02";		//접수완료
	public static final String MON_RECEIPT_CANCEL = "06";		//접수취소
	public static final String MON_COMPLEMENTARY_CALL = "03";	//보완요청
	
	/** 메뉴분류 */
	/*public static final String MENU_STTEMNT = "01"; //변경신고 
	public static final String MENU_CNTRWK = "02"; //변동보고
	public static final String MENU_MONITORING = "03"; //모니터링
	public static final String MENU_SCHEDULE= "11"; //예정정보 
	public static final String MENU_TARGET = "04"; //조사대상
	public static final String MENU_RESULT = "04"; //성과
	public static final String MENU_CHANGE = "05"; //변화정보
	public static final String MENU_NOTICE = "10"; //지형고시
	public static final String MENU_RENEWAL = "12"; //갱신이력
*/
	public static final String MENU_NGI_MNG = "100"; //신고&보고 관리
	public static final String MENU_STTEMNT = "101"; //신고 관리
	public static final String MENU_CNTRWK = "102"; //보고 관리
	public static final String MENU_MONITORING = "103"; //모니터링 관리
	public static final String MENU_SCHEDULE = "104"; //예정정보 관리
	public static final String MENU_JOB_MNG = "200"; //작업 관리
	public static final String MENU_TARGET = "201"; //조사대상 관리
	public static final String MENU_RESULT = "202"; //성과 등록
	public static final String MENU_RESULT_INSPECT = "203"; //성과 검수
	public static final String MENU_CHANGE_MNG = "300"; //변화정보 관리
	public static final String MENU_CHANGE = "301"; //변화정보 관리
	public static final String MENU_TOPOGRAPHY_MNG = "400"; //지형고시 관리
	public static final String MENU_TOPOGRAPHY = "401"; //지형고시 조회/관리
	public static final String MENU_STATISTICS_MNG = "500"; //갱신이력&통계
	public static final String MENU_RENEWAL = "501"; //갱신이력 조회
	public static final String MENU_CHANGE_STATS = "502"; //변화정보 통계
	public static final String MENU_SCHEDULE_STATS = "503"; //예정정보 통계
	public static final String MENU_MONITORING_STATS = "504"; //모니터링 통계
	public static final String MENU_CNTRWK_STATS = "505"; //보고 통계
	public static final String MENU_STTEMNT_STATS = "506"; //신고 통계
	public static final String MENU_SYSTEM_MNG = "600"; //운영관리
	public static final String MENU_BUSINESS = "601"; //사업지구 관리
	public static final String MENU_SERVC_MNG = "602"; //용역수행사 관리
	public static final String MENU_USER_MNG = "603"; //사용자 관리
	public static final String MENU_MENU_MNG = "604"; //메뉴 관리
	public static final String MENU_CONNECT_INFO = "605"; //접속 현황
	public static final String MENU_CONNECT_HIST = "606"; //접속 이력
	public static final String MENU_SYSTEM_HIST = "607"; //운영 이력
	public static final String MENU_CODE_MNG = "608"; //코드 관리
	public static final String MENU_INSTITUTION = "609"; //기관 관리
	public static final String MENU_API = "610"; //API 관리
	public static final String MENU_BOARD_MNG = "700"; //게시판 관리
	public static final String MENU_BBS = "701"; //공지사항
	public static final String MENU_QNA = "702"; //QnA 관리
	public static final String MENU_QNA_REPLY = "703"; //QnA 답변관리
	public static final String MENU_FAQ = "704"; //FAQ 관리
	public static final String MENU_SNS_MNG = "800"; //sns관리
	public static final String MENU_FACEBOOK = "801"; //페이스북 관리
	public static final String MENU_TWITTER = "802"; //트위터 관리
	public static final String MENU_MAP = "900"; //지도

	/** 파일저장경로 */
	public static final String FILE_SERVICE_CHANGEINFO = "change_info_file";
	public static final String FILE_IDX_CHANGEINFO_SHP = "SHP";
	public static final String FILE_IDX_CHANGEINFO_NGI_NDA = "NGI_NDA";
	public static final String FILE_IDX_CHANGEINFO_DWG_DXF_5 = "DWG_DXF_5";
	public static final String FILE_IDX_CHANGEINFO_DWG_DXF_25 = "DWG_DXF_25";
	
	/** 파일 유형*/
	public static final String FILE_TYPE_SHP = "01"; //SHP
	public static final String FILE_TYPE_NGI_NDA = "02"; //1:5000 구조화(NGI,NDA)
	public static final String FILE_TYPE_DWG_DXF_5 = "03"; //1:5000 정위치(DWG,DXF)
	public static final String FILE_TYPE_DWG_DXF_25 = "04"; //1:25000 정위치(DWG,DXF)
	
	public static final String GRAD_ADMINISTRATOR = "0001"; //등급 관리자
	public static final String GRAD_SUPERVISOR = "0002"; //등급 감독관
	public static final String GRAD_SERVICE = "0003"; //등급 용역사
	public static final String GRAD_SERVICE_MASTER = "0004"; //등급 용역사마스터
	public static final String GRAD_DIGITAL_MAP = "0005"; //등급 연속수치
	
}
