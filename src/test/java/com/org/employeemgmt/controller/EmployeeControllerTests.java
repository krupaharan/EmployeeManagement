//package com.org.employeemgmt.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import com.org.employeemgmt.entity.EmployeeEntity;
//import com.org.employeemgmt.service.EmployeeMgmtService;
//import com.org.employeemgmt.vo.EmployeeVO;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//@RunWith(SpringRunner.class)
////@WebMvcTest(value = EmployeeController.class)
//@SpringBootTest
////@WithMockUser
//public class EmployeeControllerTests {
//
//	@Autowired
//	MockMvc mockMvc;
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
//       
//       
//        Mockito.when(empService.getEmployeeById(employee.getEmpId())).thenReturn(employee);
//       
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getEmployee/{employeeId}",1);
//       
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//       
//        System.out.println("Response " + result.getResponse().getContentAsString());
//         
//        String expected = "{\"empId\":\"1\", \"empName\":\"Krupaharan\", :\"age\":\"28\"}";
//        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//       
//        /*mockMvc.perform( get("/getEmployee/{employeeId}",1)
//        .accept(MediaType.APPLICATION_JSON)
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.empId").value(1))
//                .andExpect(jsonPath("$.empName").value("Krupaharan"))); */
//       
//    }
//
//	@Test
//    public void testCreateEmployee() throws Exception {
//       
//		EmployeeVO employee = new EmployeeVO();
//        employee.setEmpId(1);
//        employee.setEmpName("Krupaharan");
//        employee.setAge(28);
//       
//        EmployeeEntity empEntity = new EmployeeEntity();
//        empEntity.setEmpId(1);
//        empEntity.setEmpName("Krupaharan");
//        empEntity.setAge(28);
//        
//        String request = "{\"empId\":\"1\", \"empName\":\"Krupaharan\", :\"age\":\"28\"}";
//       
//        Mockito.when(empService.addEmployee(empEntity)).thenReturn(employee);
//       
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/create").
//        			accept(MediaType.APPLICATION_JSON).content(request);
//       
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//       
//        MockHttpServletResponse response= result.getResponse();
//        
//        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
//    }
//}