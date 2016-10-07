;;

function _getNXSamplesURL(vSamplePath) {
	// Path 입력받아 vDEXTUploadNX_Domain 설치 경로를 조합하여 접근 가능한 샘플 전체경로를 반환하는 함수입니다.
	// 샘플에 포함된 여럼 소스에서 참조되는 함수입니다.
	// 잘못 수정되었을 경우 샘플이 정상적으로 실행되지 않을 수 있습니다.

	var vRet = "";

	vRet += "http://";
	vRet += vDEXTUploadNX_Domain;
	//vRet += "/응용프로그램 또는 가상디렉터리 명"; // 응용프로그램(or 가상디렉토리) 등록시 사용된 이름
	vRet += "/ngiweb"; // 응용프로그램(or 가상디렉토리) 등록시 사용된 이름	
	vRet += vSamplePath;

	return vRet;
};
