package ru.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.api.entity.Student;
import ru.api.jpa.StudentRepository;

import java.util.List;

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

    @PutMapping("/updateStudents")
    public Object updateStudents(@RequestParam("id") String idString,
                                 @RequestParam(value = "name", defaultValue = "") String name,
                                 @RequestParam(value = "passport", defaultValue = "") String passport) {
        Integer id = 0;
        try {
            id = Integer.getInteger(idString);
        } catch (Exception e) {
            e.printStackTrace();
            return "Bad ID";
        }
        studentRepository.update(id, name, passport);
        return studentRepository.findById(id).get();
    }

    @PostMapping("/addStudent")
    public Object addStudent(@RequestParam(value = "name", defaultValue = "") String name,
                             @RequestParam(value = "passport", defaultValue = "") String passport) {
        Student student = new Student();
        student.setName(name);
        student.setPassport(passport);
        return studentRepository.save(student);
    }

    @DeleteMapping("/delStudent")
    public void delStudent(@RequestParam("id") String idString) {
        Integer id = 0;
        try {
            id = Integer.getInteger(idString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        studentRepository.delete(studentRepository.findById(id).get());
    }


}
