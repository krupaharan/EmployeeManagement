package com.org.employeemgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.employeemgmt.entity.UploadStatusEntity;

@Repository
public interface UploadStatusRepository extends JpaRepository<UploadStatusEntity, Long>{

	UploadStatusEntity findByReqId(Long requestId);

}
