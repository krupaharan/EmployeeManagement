package com.org.employeemgmt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.org.employeemgmt.constant.ApplicationConstant;
import com.org.employeemgmt.entity.EmployeeEntity;
import com.org.employeemgmt.entity.UploadStatusEntity;
import com.org.employeemgmt.service.EmployeeMgmtService;
import com.org.employeemgmt.service.UploadStatusService;
import com.org.employeemgmt.vo.UploadFileResponse;

@RestController
@RequestMapping("/api/employees/")
public class EmployeeMgmtController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeMgmtController.class);
	
	private HashMap<Long, Future<List<EmployeeEntity>>> fObjects = new HashMap<Long, Future<List<EmployeeEntity>>>();
	
	@Autowired
	EmployeeMgmtService empService;
	
	@Autowired
	UploadStatusService statusService;

	@PostMapping("/upload")
	public ResponseEntity<UploadFileResponse> upload(@RequestParam("file") MultipartFile file) {
		
		String uploadStatusURI = null;
		ResponseEntity<UploadFileResponse> responseEntity = null;
		UploadFileResponse resp = null;
		
		if(file != null) {
			try { 
				UploadStatusEntity statusEntity = statusService.saveStatus(
								new UploadStatusEntity(file.getOriginalFilename(), ApplicationConstant.SUBMITTED,"Saving in Progress"));
				uploadStatusURI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/employees/status/").
								path(Long.toString(statusEntity.getReqId())).toUriString();
				
				Future<List<EmployeeEntity>> employeeList = empService.save(file,statusEntity.getReqId());
				fObjects.put(statusEntity.getReqId(), employeeList);
				
				resp =  new UploadFileResponse(file.getOriginalFilename(),uploadStatusURI);
				resp.setStatus(statusEntity.getStatus());
				resp.setStatusMessage(statusEntity.getMessage());
				responseEntity = new ResponseEntity<>(resp, HttpStatus.ACCEPTED);
				
			} catch(Exception e) {
				resp =  new UploadFileResponse(file.getOriginalFilename(),uploadStatusURI);
				resp.setStatus(ApplicationConstant.FAILED);
				resp.setStatusMessage(e.getMessage());
				responseEntity = new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} 
		return responseEntity;
	}
	
	@GetMapping("/status/{requestId}")
	public ResponseEntity<UploadFileResponse> getStatusById(@PathVariable long requestId) {
		
		ResponseEntity<UploadFileResponse> responseEntity = null;
		UploadFileResponse resp = null;
		
		if(!fObjects.containsKey(requestId)) {
			resp =  new UploadFileResponse();
			resp.setStatus(ApplicationConstant.FAILED);
			resp.setStatusMessage("No data available");
			return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
		}
		try {
			Future<List<EmployeeEntity>> futureList = fObjects.get(requestId);
			if(futureList.isDone()) {
				List<EmployeeEntity> employeeList = futureList.get();
				if(employeeList == null || employeeList.isEmpty()) {
					resp =  new UploadFileResponse();
					resp.setStatus(ApplicationConstant.FAILED);
					resp.setStatusMessage("Failed to process the request. Please resubmit");
					responseEntity = new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
				} else {
					UploadStatusEntity fileStatus = statusService.getStatusById(requestId);
					resp =  new UploadFileResponse(fileStatus.getFileName(),null);
					resp.setStatus(fileStatus.getStatus());
					resp.setStatusMessage(fileStatus.getMessage());
					responseEntity = new ResponseEntity<>(resp, HttpStatus.OK);
				}
			} else {
				resp =  new UploadFileResponse();
				resp.setStatus(ApplicationConstant.SUBMITTED);
				resp.setStatusMessage("Saving in progress");
				responseEntity = new ResponseEntity<>(resp, HttpStatus.PROCESSING);
			}
		} catch(Exception e) {
			resp =  new UploadFileResponse();
			resp.setStatus(ApplicationConstant.FAILED);
			resp.setStatusMessage(e.getMessage());
			responseEntity = new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}