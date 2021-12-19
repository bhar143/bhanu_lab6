package com.grl.springMVC.College.controller;

import com.grl.springMVC.College.entity.Student;
import com.grl.springMVC.College.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;



    // add mapping for "/list"

    @RequestMapping("/list")
    public String listStudents(Model theModel) {


        // get Students from db
        List<Student> theStudents = studentService.findAll();

        // add to the spring model
        theModel.addAttribute("Students", theStudents);


        return "list-Students";
    }

    @RequestMapping("/showFormForAdd")
    @PreAuthorize("ADMIN")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Student theStudent = new Student();

        theModel.addAttribute("Student", theStudent);

        return "Student-form";
    }


    @RequestMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") int theId,
                                    Model theModel) {

        // get the Student from the service
        Student theStudent = studentService.findById(theId);


        // set Student as a model attribute to pre-populate the form
        theModel.addAttribute("Student", theStudent);

        // send over to our form
        return "Student-form";
    }


    @PostMapping("/save")
    public String saveStudent(@RequestParam("id") int id,
                           @RequestParam("name") String name,@RequestParam("department") String department,@RequestParam("country") String country) {

        System.out.println(id);
        Student theStudent;
        if(id!=0)
        {
            theStudent=studentService.findById(id);
            theStudent.setName(name);
            theStudent.setDepartment(department);
            theStudent.setCountry(country);
        }
        else
            theStudent=new Student(name, department, country);
        // save the Student
        studentService.save(theStudent);


        // use a redirect to prevent duplicate submissions
        return "redirect:/students/list";

    }



    @RequestMapping("/delete")
    public String delete(@RequestParam("studentId") int theId) {

        // delete the Student
        studentService.deleteById(theId);

        // redirect to /Students/list
        return "redirect:/students/list";

    }


    @RequestMapping("/search")
    public String search(@RequestParam("name") String name,
                         @RequestParam("department") String department,
                         @RequestParam("country") String country,
                                 Model theModel) {

        // check names, if both are empty then just give list of all Students

        if (name.trim().isEmpty() && department.trim().isEmpty() && country.trim().isEmpty()) {
            return "redirect:/students/list";
        }
        else {
            // else, search by first name and last name
            List<Student> theStudents =
                    studentService.searchBy(name, department,country);

            // add to the spring model
            theModel.addAttribute("Students", theStudents);

            // send to list-Students
            return "list-Students";
        }

    }

    @RequestMapping(value = "/403")
    public ModelAndView accesssDenied(Principal user) {

        ModelAndView model = new ModelAndView();

        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }

        model.setViewName("403");
        return model;

    }
}
