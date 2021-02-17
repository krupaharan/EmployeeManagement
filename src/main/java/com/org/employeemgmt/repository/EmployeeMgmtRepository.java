package com.org.employeemgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.employeemgmt.entity.EmployeeEntity;

@Repository
public interface EmployeeMgmtRepository extends JpaRepository<EmployeeEntity, Long> {

	EmployeeEntity findByEmpId(long empId);
	
}