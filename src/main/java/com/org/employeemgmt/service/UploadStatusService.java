package com.org.employeemgmt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.employeemgmt.constant.ApplicationConstant;
import com.org.employeemgmt.controller.EmployeeMgmtController;
import com.org.employeemgmt.entity.UploadStatusEntity;
import com.org.employeemgmt.repository.UploadStatusRepository;

@Service
public class UploadStatusService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UploadStatusService.class);
	
	@Autowired
	UploadStatusRepository statusRepo;
	
	public UploadStatusEntity saveStatus(UploadStatusEntity statusEntity) {
		return statusRepo.save(statusEntity);
	}
	
	public UploadStatusEntity getStatusById(Long requestId) {
		return statusRepo.findByReqId(requestId);
	}
	
	public void updateStatusById(Long requestId,String status) {
		try {
			UploadStatusEntity statusEntity = statusRepo.findByReqId(requestId);
			statusEntity.setStatus(status);
			statusEntity.setMessage("Saved Successfully");
			statusRepo.save(statusEntity);
		} catch(Exception e) {
			LOGGER.info("Exception occured in updateStatusById() " + e.getMessage());
		}
	}
}
