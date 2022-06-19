package com.kkk.spring.test.TestWithSpringGradle.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.SpringVersion;

import static org.junit.jupiter.api.Assertions.*;

class HelloServiceTest {

    // Unit Test
    @Test
    void whenCallHelloMethod_thenReturnHelloWorld() {
        HelloService helloService = new HelloService();
        String response = helloService.hello("World");
        assertEquals("Hello, World", response);
    }
}