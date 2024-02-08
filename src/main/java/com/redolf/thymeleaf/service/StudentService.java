package com.redolf.thymeleaf.service;


import com.redolf.thymeleaf.model.Student;
import com.redolf.thymeleaf.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository repository;

    public void saveStudent(Student student){
        System.out.println(student);
        repository.save(student);
    }

    public Optional<Student> findStudentByReference(String reference){
        return repository.findByReference(reference);
    }

    public List<Student> findAllStudents(){
        return repository.findAll();
    }

    public List<String> categories(){
        List<String> list = new ArrayList<>();
        list.add("Undergraduate");
        list.add("Postgraduate");
        list.add("Diploma");
        return list;
    }

    public  void deleteStudent(int id){
        repository.deleteById(id);
    }


}
