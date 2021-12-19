package com.grl.springMVC.College.repository;

import com.grl.springMVC.College.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByNameContainsAndDepartmentContainsAndCountryAllIgnoreCase(String name, String department, String country);
}
