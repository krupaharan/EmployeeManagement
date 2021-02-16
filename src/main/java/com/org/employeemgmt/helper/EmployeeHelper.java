package com.org.employeemgmt.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.employeemgmt.controller.EmployeeMgmtController;
import com.org.employeemgmt.entity.EmployeeEntity;

public class EmployeeHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeMgmtController.class);

	public static List<EmployeeEntity> convertToBean(InputStream istream) {
	
		List<EmployeeEntity> employeeList = new ArrayList<EmployeeEntity>();
		LOGGER.info("ConvertToBean method called");
		try {
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(istream,"UTF-8"));
			LOGGER.info("Checkpoint #1");
			CSVParser parser = new CSVParser(fileReader, 
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withDelimiter(' ').withTrim());
			LOGGER.info("Checkpoint #2");
		    
		    Iterable<CSVRecord> csvRecords = parser.getRecords();
		    LOGGER.info("Checkpoint #3");
		    for (CSVRecord csvRecord : csvRecords) {
		    	EmployeeEntity employee = new EmployeeEntity(csvRecord.get("name"),Integer.parseInt(csvRecord.get("age")));
		    	employeeList.add(employee);
		    }
		    LOGGER.info("Checkpoint #4");
		    
		} catch(IOException ie) {
			LOGGER.info("Failed to parse the file. Please check and try again");
		}
		return employeeList;
	}
}