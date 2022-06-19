package com.kkk.spring.test.TestWithSpringGradle.contorller;

import com.kkk.spring.test.TestWithSpringGradle.domain.Employee;
import com.kkk.spring.test.TestWithSpringGradle.service.EmployeeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "/employee/register", produces = "application/json")
    public String regisrer(@RequestBody String json) {
        JSONObject requestJosn = new JSONObject(json);
        String name = requestJosn.getString("name");
        String email = requestJosn.getString("email");
        Employee employee = employeeService.insertEmployee(name, email);
        System.out.println(employee.getName());
        System.out.println(employee.getEmail());
        return "success";
    }

    @PostMapping(value = "/employee/queryByName", produces = "application/json")
    public String query(@RequestBody String json) {
        JSONObject requestJosn = new JSONObject(json);
        String name = requestJosn.getString("name");
        Employee employee = employeeService.getEmployeeByName(name);
        JSONObject response = new JSONObject();
        response.put("name", employee.getName());
        response.put("email", employee.getEmail());
        return response.toString();
    }
}
