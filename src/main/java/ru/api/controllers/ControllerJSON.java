package ru.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.api.modles.Pagination;
import ru.api.modles.Student;
import ru.api.modles.ViewModelsStudent;
import ru.api.repositories.StudentRepository;
import ru.api.services.StudentsService;

@RestController
@RequestMapping("api-json/")
public class ControllerJSON {

    private final StudentRepository studentRepository;
    private final StudentsService studentsService;

    @Autowired
    public ControllerJSON(StudentRepository studentRepositories, StudentsService studentsService) {
        this.studentRepository = studentRepositories;
        this.studentsService = studentsService;
    }

    @GetMapping("/getStudents")
    @ResponseBody
    public ViewModelsStudent findAll(@RequestBody(required = false) Pagination pagination) {
        return studentsService.getStudents(pagination);
    }

    @PutMapping("/updateStudents")
    public Student updateStudents(@RequestBody(required = false) Student student) {
        return studentsService.updateStudent(student);
    }

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody(required = false) Student student) {
        return studentsService.saveStudent(student);
    }

    @DeleteMapping("/delStudent")
    public void delStudent(@RequestBody Student student) {
        studentRepository.delete(student);
    }


}
