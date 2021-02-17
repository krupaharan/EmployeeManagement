package com.org.employeemgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="upload_status")
public class UploadStatusEntity {

	@Id
	@GeneratedValue(generator = "SEQ_THREAD_ID", strategy= GenerationType.SEQUENCE)
	@SequenceGenerator(name= "SEQ_THREAD_ID", sequenceName = "REQID_SEQ")
	@Column(name="req_id", unique=true, nullable=false)
	private long reqId;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="status")
	private String status;
	
	@Column(name = "message")
	private String message;
	
	public UploadStatusEntity() {
		
	}

	public UploadStatusEntity(String fileName, String status, String message) {
		super();
		this.fileName = fileName;
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getReqId() {
		return reqId;
	}

	public void setReqId(long reqId) {
		this.reqId = reqId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}