package ru.api.controllers;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.api.entity.Student;
import ru.api.jpa.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class Controller {

    private final StudentRepository studentRepository;

    @Autowired
    public Controller(StudentRepository studentRepositories) {
        this.studentRepository = studentRepositories;
    }

    @GetMapping("/getStudents")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @PostMapping("/chanStudent")
    public Student findAll(@RequestBody Student student) {
        studentRepository.update(student.getId(),
                student.getName(),
                student.getPassport());
        return studentRepository.findById(student.getId()).get();
    }

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @DeleteMapping("/delStudent")
    public void delStudent(@RequestBody Student student) {
        studentRepository.delete(student);
    }


}
