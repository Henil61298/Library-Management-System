package com.example.Libmansys.Controllers;

import com.example.Libmansys.Entities.Student;
import com.example.Libmansys.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){

        return studentService.addStudent(student);
    }

}