package com.kkk.spring.test.TestWithSpringGradle.contorller;

import com.kkk.spring.test.TestWithSpringGradle.service.EmployeeService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("IT")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeService employeeService;


    @Test
    void useMockMvcPerformOnly_whenCreateNewEmployThroughAllLayer_returnOK() throws Exception {
        JSONObject requestObj = new JSONObject();
        requestObj.put("name", "alex");
        requestObj.put("email", "alex@alex");

        mvc.perform(MockMvcRequestBuilders.post("/employee/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestObj.toString()))
                .andExpect(status().isOk())
                .andExpect(
                        content().string("success"));
    }

    @BeforeEach
    void setUp() {
        employeeService.insertEmployee("alex2", "alex@alex");
    }

    @Test
    void useMockMvcPerformOnly_whenQueryByNameAlex2_returnJson() throws Exception {
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