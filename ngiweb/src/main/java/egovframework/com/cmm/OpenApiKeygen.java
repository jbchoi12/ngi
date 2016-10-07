package egovframework.com.cmm;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.Validate;

public class OpenApiKeygen {

	private String MESSAGE = "Dokdo is korean territory!!";
	private String userId = null;
	private String hostname = null;
	private String port = "80";
	
	/**
	 * OpenAPI KEY 발급 생성자
	 * @param userId 사용자 ID
	 * @param hostname 사용 host주소
	 */
	public OpenApiKeygen(String userId, String hostname) throws Exception  {
		Validate.notEmpty(userId, "Parameters can't be null");
		Validate.notEmpty(hostname, "Parameters can't be null");
		this.userId = userId;
		this.hostname = hostname;
	}
	
	/**
	 * OpenAPI KEY 발급 생성자
	 * @param userId 사용자 ID
	 * @param hostname 사용 host주소
	 * @param port	사용 port번호 
	 */
	public OpenApiKeygen(String userId, String hostname, String port) throws Exception {
		Validate.notEmpty(userId, "Parameters can't be null");
		Validate.notEmpty(hostname, "Parameters can't be null");
		Validate.notEmpty(port, "Parameters can't be null");
		this.userId = userId;
		this.hostname = hostname;
		this.port = port;
	}
	
	/**
	 * openapi key (요청시 사용할 api키)
	 * @return
	 * @throws Exception
	 */
	public String getOpenApiKey() throws Exception {
		String saltkeyString = userId + hostname + port;
		return new DigestUtils().md5Hex(MESSAGE + saltkeyString);
	}
	
	/**
	 * auth을 위한 sha256키 (이메일주소 확인을 위함)
	 * @return
	 * @throws Exception
	 */
	public String getAuthKey() throws Exception {
		return new DigestUtils().sha256Hex(getOpenApiKey());
	}
	
	
}
