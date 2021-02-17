package com.org.employeemgmt.vo;

import java.io.Serializable;

public class BaseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	
	private String statusMessage;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}
