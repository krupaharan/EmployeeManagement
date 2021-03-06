package com.org.employeemgmt.vo;

public class UploadFileResponse extends BaseVO {
	
	private String fileName;
	
	private String uri;
	
	public UploadFileResponse() {
		
	}
	
	public UploadFileResponse(String fileName, String uri) {
		this.fileName = fileName;
		this.uri = uri;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
