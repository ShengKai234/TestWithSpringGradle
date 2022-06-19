package com.kkk.spring.test.TestWithSpringGradle.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="person",uniqueConstraints= {@UniqueConstraint(columnNames="Id")})
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Employee(String name) {
//        this.name = name;
//    }
}
