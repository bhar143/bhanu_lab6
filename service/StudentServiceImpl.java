package com.grl.springMVC.College.service;

import com.grl.springMVC.College.entity.Student;
import com.grl.springMVC.College.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        // TODO Auto-generated method stub
        List<Student> students=studentRepository.findAll();
        return students;
    }

    @Override
    public Student findById(int theId) {
        // TODO Auto-generated method stub
        return studentRepository.findById(theId).get();
    }

    @Override
    public void save(Student theStudent) {
        // TODO Auto-generated method stub
        studentRepository.save(theStudent);

    }

    @Override
    public void deleteById(int theId) {
        // TODO Auto-generated method stub
        studentRepository.deleteById(theId);

    }

    @Override
    public List<Student> searchBy(String name, String department,String country) {
        // TODO Auto-generated method stub
        List<Student> students=studentRepository.findByNameContainsAndDepartmentContainsAndCountryAllIgnoreCase(name, department,country);
        return students;
    }



}
