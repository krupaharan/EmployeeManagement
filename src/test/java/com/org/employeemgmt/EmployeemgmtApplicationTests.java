//package com.org.employeemgmt;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.org.employeemgmt.controller.EmployeeController;
//import com.org.employeemgmt.entity.EmployeeEntity;
//import com.org.employeemgmt.service.EmployeeMgmtService;
//import com.org.employeemgmt.vo.EmployeeVO;import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(EmployeeController.class)
//class EmployeemgmtApplicationTests {
//
//	@Autowired
//	MockMvc mockMvc;
//
////	@InjectMocks
////	EmployeeController employeeController;
//
//	@MockBean
//	EmployeeMgmtService empService;
//
//	@Test
//    public void testGetEmployee() throws Exception {
//        EmployeeVO employee = new EmployeeVO();
//        employee.setEmpId(1);
//        employee.setEmpName("Krupaharan");
//        employee.setAge(28);
//        Mockito.when(empService.getEmployeeById(employee.getEmpId())).thenReturn(employee);
//        mockMvc.perform( get("/getEmployee/{employeeId}",1)
//        		.accept(MediaType.APPLICATION_JSON)
//        		.andExpect(status().isOk())
//        		.andExpect(jsonPath("$.empId").value(1))
//                .andExpect(jsonPath("$.empName").value("Krupaharan")));
//        
//    }
//	}
