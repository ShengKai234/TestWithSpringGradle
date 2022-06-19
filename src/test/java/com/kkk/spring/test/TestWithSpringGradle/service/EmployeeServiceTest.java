package com.kkk.spring.test.TestWithSpringGradle.service;

import com.kkk.spring.test.TestWithSpringGradle.domain.Employee;
import com.kkk.spring.test.TestWithSpringGradle.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@DataJpaTest
class EmployeeServiceTest {

    // Mock Bean 自動配置 -> @Autowired EmployeeService
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public EmployeeService employeeService() {
            return new EmployeeService();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    // Mock EmployeeService dependency for EmployeeRepository
    // this is fake
    @MockBean
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        System.out.println("Set up expect return object etc ...");
        Employee alex = new Employee();
        alex.setName("alex");
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);   // fake method in EmployeeService
    }

    @Test
    void giveName_whenQueryEmployeeByName_thenReturnName() {
        String name = "alex";
        Employee found = employeeService.getEmployeeByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }
}