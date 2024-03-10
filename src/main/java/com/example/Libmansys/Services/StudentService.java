package com.example.Libmansys.Services;

import com.example.Libmansys.Entities.Student;
import com.example.Libmansys.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student){
        studentRepository.save(student);
        return "Student has been saved successfully";
    }
}
