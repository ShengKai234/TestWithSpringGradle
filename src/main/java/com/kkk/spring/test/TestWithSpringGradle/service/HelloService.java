package com.kkk.spring.test.TestWithSpringGradle.service;

public class HelloService {
    public String hello(String name) {
        return String.format("Hello, %s", name);
    }
}
