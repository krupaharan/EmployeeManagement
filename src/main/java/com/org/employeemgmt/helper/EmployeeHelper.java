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
		try {
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(istream,"UTF-8"));
			CSVParser parser = new CSVParser(fileReader, 
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withDelimiter(' ').withTrim());
			
		    Iterable<CSVRecord> csvRecords = parser.getRecords();
		    for (CSVRecord csvRecord : csvRecords) {
		    	EmployeeEntity employee = new EmployeeEntity(csvRecord.get("name"),Integer.parseInt(csvRecord.get("age")));
		    	employeeList.add(employee);
		    }
		    
		} catch(IOException ie) {
			LOGGER.info("Failed to parse the file. Please check and try again");
		}
		return employeeList;
	}
}