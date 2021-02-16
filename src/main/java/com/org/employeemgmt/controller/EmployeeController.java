package com.org.employeemgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.org.employeemgmt.entity.EmployeeEntity;
import com.org.employeemgmt.service.EmployeeMgmtService;
import com.org.employeemgmt.vo.EmployeeVO;

/** Basic CRUD Operations on Employee **/
@RestController
@RequestMapping("/api/employees/")
public class EmployeeController {

	@Autowired
	EmployeeMgmtService empService;
	
	@GetMapping("/getEmployee/{employeeId}")
	public ResponseEntity<EmployeeVO> getByEmployeeId(@PathVariable("employeeId") long empId) {
		
		EmployeeVO empVO = empService.getEmployeeById(empId);
		return new ResponseEntity<>(empVO,getHttpResponse(empVO.getStatus()));
	}
	
	@PostMapping("/create")
	public ResponseEntity<EmployeeVO> addEmployee(@RequestBody EmployeeEntity empEntity) {
		
		EmployeeVO empVO = empService.addEmployee(empEntity);
		//String resourceURI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/employees/getEmployee/{employeeId}").
		//path(Long.toString(empEntity.getEmpId())).toUriString();
		return new ResponseEntity<>(empVO,getHttpResponse(empVO.getStatus()));
	}
	
	@PutMapping("/update/{employeeId}")
	public ResponseEntity<EmployeeVO> updateByEmployeeId(@PathVariable("employeeId") long empId,
										@RequestBody EmployeeEntity empEntity) {
		
		EmployeeVO empVO = empService.updateEmployeeById(empId,empEntity);
		return new ResponseEntity<>(empVO,getHttpResponse(empVO.getStatus()));
	}
	
	@DeleteMapping("/delete/{employeeId}")
	public ResponseEntity<EmployeeVO> deleteByEmployeeId(@PathVariable("employeeId") long empId) {
		
		EmployeeVO empVO = empService.deleteByEmployeeId(empId);
		return new ResponseEntity<>(empVO,getHttpResponse(empVO.getStatus()));
	}
	
	private HttpStatus getHttpResponse(String response) {
		
		HttpStatus httpStatus = null;
		switch(response)
		{
		case "NOT FOUND":
			httpStatus = HttpStatus.NOT_FOUND;
			break;
		case "CONFLICT":
			httpStatus = HttpStatus.CONFLICT;
			break;
		case "CREATED":
			httpStatus = HttpStatus.CREATED;
			break;
		case "Ok":
			httpStatus = HttpStatus.OK;
			break;
		default:
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}	
		return httpStatus;
	}
}
