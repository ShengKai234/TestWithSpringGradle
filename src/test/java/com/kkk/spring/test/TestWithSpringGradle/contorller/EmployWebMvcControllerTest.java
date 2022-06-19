package com.kkk.spring.test.TestWithSpringGradle.contorller;

import com.kkk.spring.test.TestWithSpringGradle.domain.Employee;
import com.kkk.spring.test.TestWithSpringGradle.service.EmployeeService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployController.class)
class EmployWebMvcControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;


    @Test
    void useMockMvcPerformOnly_whenQueryByNameAlex2_returnJson() throws Exception {
        Employee alex = new Employee();
        alex.setName("alex2");
        alex.setEmail("alex@alex");
        given(employeeService.getEmployeeByName(alex.getName())).willReturn(alex);


        JSONObject requestObj = new JSONObject();
        requestObj.put("name", "alex2");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/employee/queryByName")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestObj.toString()))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject response = new JSONObject(result.getResponse().getContentAsString());

        assertEquals("alex2", response.getString("name"));
        assertEquals("alex@alex", response.getString("email"));
    }
}