package egovframework.com.cmm;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FlieUtilComponentVO {

	private String fileName;
	private String filePath;
	private String fileModDate;
	private String typeDesc;

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileModDate() {
		return fileModDate;
	}

	public void setFileModDate(String fileModDate) {
		this.fileModDate = fileModDate;
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
