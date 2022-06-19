package com.kkk.spring.test.TestWithSpringGradle.service;

import com.kkk.spring.test.TestWithSpringGradle.domain.Employee;
import com.kkk.spring.test.TestWithSpringGradle.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }

    public Employee insertEmployee(String name, String email) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        return employeeRepository.save(employee);
    }
}
