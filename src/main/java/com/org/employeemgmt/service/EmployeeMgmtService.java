package com.org.employeemgmt.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.org.employeemgmt.constant.ApplicationConstant;
import com.org.employeemgmt.entity.EmployeeEntity;
import com.org.employeemgmt.entity.UploadStatusEntity;
import com.org.employeemgmt.helper.EmployeeHelper;
import com.org.employeemgmt.repository.EmployeeMgmtRepository;
import com.org.employeemgmt.vo.BaseVO;
import com.org.employeemgmt.vo.EmployeeVO;

@Service
public class EmployeeMgmtService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeMgmtService.class);
	
	@Autowired
	EmployeeMgmtRepository employeeRepo;
	
	@Autowired
	UploadStatusService statusService;
	
	@Async("processExecutor")
	public CompletableFuture<List<EmployeeEntity>> save(MultipartFile file, Long requestId) {
		List<EmployeeEntity> empList= new ArrayList<EmployeeEntity>();
		try {
	      List<EmployeeEntity> employeeList = EmployeeHelper.convertToBean(file.getInputStream());
	      empList = employeeRepo.saveAll(employeeList);
	      //Update Status Message
	      statusService.updateStatusById(requestId,ApplicationConstant.COMPLETED);
	    } catch (IOException e) {
	    	statusService.updateStatusById(requestId,ApplicationConstant.FAILED);
	    	LOGGER.info("Failed to Store the file: "+ file.getOriginalFilename()+ " " + e.getMessage());
	    	empList = null;
	    }
		return CompletableFuture.completedFuture(empList);
	}
	
	public EmployeeVO addEmployee(EmployeeEntity employee) {
		EmployeeEntity empEntity = null;
		BaseVO baseVO = null;
		try {
			if(employeeRepo.existsById(employee.getEmpId())) {
				baseVO = new BaseVO(ApplicationConstant.HTTP_STATUS_CONFLICT,"Resource already exists");
				return new EmployeeVO(baseVO);
			} else {
				empEntity = employeeRepo.save(employee);
				baseVO = new BaseVO(ApplicationConstant.HTTP_STATUS_CREATED,"Resource Created");
			}
		} catch (Exception e) {
			LOGGER.info("Exception occured in addEmployee " + e.getMessage());
			baseVO = new BaseVO(ApplicationConstant.HTTP_STATUS_ISE,"Error occured in creating the resource");
			return new EmployeeVO(baseVO);
		}
		return new EmployeeVO(baseVO,empEntity.getEmpId(),empEntity.getEmpName(),empEntity.getAge());
	}
	
	public EmployeeVO updateEmployeeById(long empId,EmployeeEntity entity) {
		
		EmployeeEntity empEntity = null;
		BaseVO baseVO = null;
		try {
			empEntity = employeeRepo.findByEmpId(empId);
			if(empEntity == null) {
				baseVO = new BaseVO(ApplicationConstant.HTTP_STATUS_NOT_FOUND,"Resource Not Found");
				return new EmployeeVO(baseVO);
			} else {
				empEntity.setAge(entity.getAge());
				empEntity.setEmpName(entity.getEmpName());
				employeeRepo.save(empEntity);
				baseVO = new BaseVO(ApplicationConstant.HTTP_STATUS_OK,"Resource Updated");
			}
		} catch (Exception e) {
			LOGGER.info("Exception occured in getEmployeeById " + e.getMessage());
			baseVO = new BaseVO(ApplicationConstant.HTTP_STATUS_ISE,"Error occured in creating the resource");
			return new EmployeeVO(baseVO);
		}
		return new EmployeeVO(baseVO,empEntity.getEmpId(),empEntity.getEmpName(),empEntity.getAge());
	}
	
	public EmployeeVO deleteByEmployeeId(long empId) {
		
		EmployeeEntity empEntity = null;
		BaseVO baseVO = null;
		try {
			empEntity = employeeRepo.findByEmpId(empId);
			if(empEntity == null) {
				baseVO = new BaseVO(ApplicationConstant.HTTP_STATUS_NOT_FOUND,"Resource Not Found");
				return new EmployeeVO(baseVO);
			} else {
				employeeRepo.delete(empEntity);
				baseVO = new BaseVO(ApplicationConstant.HTTP_STATUS_OK,"Resource Deleted");
			}
		} catch (Exception e) {
			LOGGER.info("Exception occured in getEmployeeById " + e.getMessage());
			baseVO = new BaseVO(ApplicationConstant.HTTP_STATUS_ISE,"Error occured in creating the resource");
			return new EmployeeVO(baseVO);
		}
		return new EmployeeVO(baseVO,empEntity.getEmpId(),empEntity.getEmpName(),empEntity.getAge());
	}
	
	public EmployeeVO getEmployeeById(Long empId) {
		
		EmployeeEntity empEntity = null;
		BaseVO baseVO = null;
		try {
			empEntity = employeeRepo.findByEmpId(empId);
			if(empEntity == null) {
				baseVO = new BaseVO(ApplicationConstant.HTTP_STATUS_NOT_FOUND,"Resource Not Found");
				return new EmployeeVO(baseVO);
			} else {
				baseVO = new BaseVO(ApplicationConstant.HTTP_STATUS_OK,"Resource Found");
			}
		} catch (Exception e) {
			LOGGER.info("Exception occured in getEmployeeById " + e.getMessage());
			baseVO = new BaseVO(ApplicationConstant.HTTP_STATUS_ISE,"Error occured in creating the resource");
			return new EmployeeVO(baseVO);
		}
		return new EmployeeVO(baseVO,empEntity.getEmpId(),empEntity.getEmpName(),empEntity.getAge());
	}
}
