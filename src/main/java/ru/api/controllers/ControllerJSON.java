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
    public ViewModelsStudent findAll(@RequestBody Pagination pagination) {
        return studentsService.getStudents(pagination);
    }

    @PutMapping("/updateStudents")
    public Student updateStudents(@RequestBody Student student) {
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
