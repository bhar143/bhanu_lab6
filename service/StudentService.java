package com.grl.springMVC.College.service;

import com.grl.springMVC.College.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAll();

    public Student findById(int theId);

    public void save(Student theStudent);

    public void deleteById(int theId);

    public List<Student> searchBy(String name, String department,String country);

}
