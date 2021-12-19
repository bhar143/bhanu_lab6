package com.grl.springMVC.College.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="student")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="department")
    private String department;

    @Column(name="country")
    private String country;

    public Student() {

    }

    public Student(String name, String department, String country) {
        super();
        this.name = name;
        this.department = department;
        this.country = country;
    }



}
