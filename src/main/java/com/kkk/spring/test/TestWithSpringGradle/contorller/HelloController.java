package com.kkk.spring.test.TestWithSpringGradle.contorller;

import com.kkk.spring.test.TestWithSpringGradle.domain.Employee;
import com.kkk.spring.test.TestWithSpringGradle.repository.EmployeeRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    @Value("${profile.env.name}")
    private String profileEnvName;

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/")
    String hello() throws InterruptedException {
        Thread.sleep(100);
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        return "Hello World!";
    }

    @GetMapping("/hello/add")
    public String helloAdd(@RequestParam(name = "name", defaultValue = "World") String name) throws InterruptedException {
        System.out.println("active - " + springProfilesActive);
        System.out.println("profileEnvName - " + profileEnvName);
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("kkk");
        employee.setEmail(name + "@" + name);
        Employee employees = employeeRepository.save(employee);
        return String.format("Hello, %s", employees.getEmail());
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") String name) throws InterruptedException {
        System.out.println("mvc");
        return "Hello, World";
    }

    @RequestMapping(
            value = "/hello_world",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public String helloWorld(@RequestParam(name = "name", defaultValue = "World") String name) {
        JSONObject response = new JSONObject();
        response.put("info", "hello, Johnny");
        return response.toString();
    }
}
